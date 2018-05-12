/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.entity.SsfAlumno;
import duoc.cl.safe.entity.SsfPersona;
import duoc.cl.safe.jpa.exceptions.IllegalOrphanException;
import duoc.cl.safe.jpa.exceptions.NonexistentEntityException;
import duoc.cl.safe.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nacho
 */
public class SsfPersonaJpaController implements Serializable {

    public SsfPersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfPersona ssfPersona) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfUsuario ssfUsuario = ssfPersona.getSsfUsuario();
            if (ssfUsuario != null) {
                ssfUsuario = em.getReference(ssfUsuario.getClass(), ssfUsuario.getId());
                ssfPersona.setSsfUsuario(ssfUsuario);
            }
            SsfAlumno ssfAlumno = ssfPersona.getSsfAlumno();
            if (ssfAlumno != null) {
                ssfAlumno = em.getReference(ssfAlumno.getClass(), ssfAlumno.getId());
                ssfPersona.setSsfAlumno(ssfAlumno);
            }
            em.persist(ssfPersona);
            if (ssfUsuario != null) {
                SsfPersona oldIdPersonaOfSsfUsuario = ssfUsuario.getIdPersona();
                if (oldIdPersonaOfSsfUsuario != null) {
                    oldIdPersonaOfSsfUsuario.setSsfUsuario(null);
                    oldIdPersonaOfSsfUsuario = em.merge(oldIdPersonaOfSsfUsuario);
                }
                ssfUsuario.setIdPersona(ssfPersona);
                ssfUsuario = em.merge(ssfUsuario);
            }
            if (ssfAlumno != null) {
                SsfPersona oldIdPersonaOfSsfAlumno = ssfAlumno.getIdPersona();
                if (oldIdPersonaOfSsfAlumno != null) {
                    oldIdPersonaOfSsfAlumno.setSsfAlumno(null);
                    oldIdPersonaOfSsfAlumno = em.merge(oldIdPersonaOfSsfAlumno);
                }
                ssfAlumno.setIdPersona(ssfPersona);
                ssfAlumno = em.merge(ssfAlumno);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfPersona(ssfPersona.getId()) != null) {
                throw new PreexistingEntityException("SsfPersona " + ssfPersona + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfPersona ssfPersona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfPersona persistentSsfPersona = em.find(SsfPersona.class, ssfPersona.getId());
            SsfUsuario ssfUsuarioOld = persistentSsfPersona.getSsfUsuario();
            SsfUsuario ssfUsuarioNew = ssfPersona.getSsfUsuario();
            SsfAlumno ssfAlumnoOld = persistentSsfPersona.getSsfAlumno();
            SsfAlumno ssfAlumnoNew = ssfPersona.getSsfAlumno();
            List<String> illegalOrphanMessages = null;
            if (ssfUsuarioOld != null && !ssfUsuarioOld.equals(ssfUsuarioNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain SsfUsuario " + ssfUsuarioOld + " since its idPersona field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (ssfUsuarioNew != null) {
                ssfUsuarioNew = em.getReference(ssfUsuarioNew.getClass(), ssfUsuarioNew.getId());
                ssfPersona.setSsfUsuario(ssfUsuarioNew);
            }
            if (ssfAlumnoNew != null) {
                ssfAlumnoNew = em.getReference(ssfAlumnoNew.getClass(), ssfAlumnoNew.getId());
                ssfPersona.setSsfAlumno(ssfAlumnoNew);
            }
            ssfPersona = em.merge(ssfPersona);
            if (ssfUsuarioNew != null && !ssfUsuarioNew.equals(ssfUsuarioOld)) {
                SsfPersona oldIdPersonaOfSsfUsuario = ssfUsuarioNew.getIdPersona();
                if (oldIdPersonaOfSsfUsuario != null) {
                    oldIdPersonaOfSsfUsuario.setSsfUsuario(null);
                    oldIdPersonaOfSsfUsuario = em.merge(oldIdPersonaOfSsfUsuario);
                }
                ssfUsuarioNew.setIdPersona(ssfPersona);
                ssfUsuarioNew = em.merge(ssfUsuarioNew);
            }
            if (ssfAlumnoOld != null && !ssfAlumnoOld.equals(ssfAlumnoNew)) {
                ssfAlumnoOld.setIdPersona(null);
                ssfAlumnoOld = em.merge(ssfAlumnoOld);
            }
            if (ssfAlumnoNew != null && !ssfAlumnoNew.equals(ssfAlumnoOld)) {
                SsfPersona oldIdPersonaOfSsfAlumno = ssfAlumnoNew.getIdPersona();
                if (oldIdPersonaOfSsfAlumno != null) {
                    oldIdPersonaOfSsfAlumno.setSsfAlumno(null);
                    oldIdPersonaOfSsfAlumno = em.merge(oldIdPersonaOfSsfAlumno);
                }
                ssfAlumnoNew.setIdPersona(ssfPersona);
                ssfAlumnoNew = em.merge(ssfAlumnoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfPersona.getId();
                if (findSsfPersona(id) == null) {
                    throw new NonexistentEntityException("The ssfPersona with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfPersona ssfPersona;
            try {
                ssfPersona = em.getReference(SsfPersona.class, id);
                ssfPersona.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfPersona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            SsfUsuario ssfUsuarioOrphanCheck = ssfPersona.getSsfUsuario();
            if (ssfUsuarioOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SsfPersona (" + ssfPersona + ") cannot be destroyed since the SsfUsuario " + ssfUsuarioOrphanCheck + " in its ssfUsuario field has a non-nullable idPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SsfAlumno ssfAlumno = ssfPersona.getSsfAlumno();
            if (ssfAlumno != null) {
                ssfAlumno.setIdPersona(null);
                ssfAlumno = em.merge(ssfAlumno);
            }
            em.remove(ssfPersona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfPersona> findSsfPersonaEntities() {
        return findSsfPersonaEntities(true, -1, -1);
    }

    public List<SsfPersona> findSsfPersonaEntities(int maxResults, int firstResult) {
        return findSsfPersonaEntities(false, maxResults, firstResult);
    }

    private List<SsfPersona> findSsfPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfPersona.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public SsfPersona findSsfPersona(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfPersona.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfPersona> rt = cq.from(SsfPersona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
