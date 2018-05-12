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
import duoc.cl.safe.entity.SsfAtencionmedica;
import duoc.cl.safe.entity.SsfFichamedica;
import duoc.cl.safe.entity.SsfFichamedicaatencion;
import duoc.cl.safe.jpa.exceptions.NonexistentEntityException;
import duoc.cl.safe.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nacho
 */
public class SsfFichamedicaatencionJpaController implements Serializable {

    public SsfFichamedicaatencionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfFichamedicaatencion ssfFichamedicaatencion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfAtencionmedica idAtencionmedica = ssfFichamedicaatencion.getIdAtencionmedica();
            if (idAtencionmedica != null) {
                idAtencionmedica = em.getReference(idAtencionmedica.getClass(), idAtencionmedica.getId());
                ssfFichamedicaatencion.setIdAtencionmedica(idAtencionmedica);
            }
            SsfFichamedica idFichamedica = ssfFichamedicaatencion.getIdFichamedica();
            if (idFichamedica != null) {
                idFichamedica = em.getReference(idFichamedica.getClass(), idFichamedica.getId());
                ssfFichamedicaatencion.setIdFichamedica(idFichamedica);
            }
            em.persist(ssfFichamedicaatencion);
            if (idAtencionmedica != null) {
                idAtencionmedica.getSsfFichamedicaatencionList().add(ssfFichamedicaatencion);
                idAtencionmedica = em.merge(idAtencionmedica);
            }
            if (idFichamedica != null) {
                idFichamedica.getSsfFichamedicaatencionList().add(ssfFichamedicaatencion);
                idFichamedica = em.merge(idFichamedica);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfFichamedicaatencion(ssfFichamedicaatencion.getId()) != null) {
                throw new PreexistingEntityException("SsfFichamedicaatencion " + ssfFichamedicaatencion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfFichamedicaatencion ssfFichamedicaatencion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfFichamedicaatencion persistentSsfFichamedicaatencion = em.find(SsfFichamedicaatencion.class, ssfFichamedicaatencion.getId());
            SsfAtencionmedica idAtencionmedicaOld = persistentSsfFichamedicaatencion.getIdAtencionmedica();
            SsfAtencionmedica idAtencionmedicaNew = ssfFichamedicaatencion.getIdAtencionmedica();
            SsfFichamedica idFichamedicaOld = persistentSsfFichamedicaatencion.getIdFichamedica();
            SsfFichamedica idFichamedicaNew = ssfFichamedicaatencion.getIdFichamedica();
            if (idAtencionmedicaNew != null) {
                idAtencionmedicaNew = em.getReference(idAtencionmedicaNew.getClass(), idAtencionmedicaNew.getId());
                ssfFichamedicaatencion.setIdAtencionmedica(idAtencionmedicaNew);
            }
            if (idFichamedicaNew != null) {
                idFichamedicaNew = em.getReference(idFichamedicaNew.getClass(), idFichamedicaNew.getId());
                ssfFichamedicaatencion.setIdFichamedica(idFichamedicaNew);
            }
            ssfFichamedicaatencion = em.merge(ssfFichamedicaatencion);
            if (idAtencionmedicaOld != null && !idAtencionmedicaOld.equals(idAtencionmedicaNew)) {
                idAtencionmedicaOld.getSsfFichamedicaatencionList().remove(ssfFichamedicaatencion);
                idAtencionmedicaOld = em.merge(idAtencionmedicaOld);
            }
            if (idAtencionmedicaNew != null && !idAtencionmedicaNew.equals(idAtencionmedicaOld)) {
                idAtencionmedicaNew.getSsfFichamedicaatencionList().add(ssfFichamedicaatencion);
                idAtencionmedicaNew = em.merge(idAtencionmedicaNew);
            }
            if (idFichamedicaOld != null && !idFichamedicaOld.equals(idFichamedicaNew)) {
                idFichamedicaOld.getSsfFichamedicaatencionList().remove(ssfFichamedicaatencion);
                idFichamedicaOld = em.merge(idFichamedicaOld);
            }
            if (idFichamedicaNew != null && !idFichamedicaNew.equals(idFichamedicaOld)) {
                idFichamedicaNew.getSsfFichamedicaatencionList().add(ssfFichamedicaatencion);
                idFichamedicaNew = em.merge(idFichamedicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfFichamedicaatencion.getId();
                if (findSsfFichamedicaatencion(id) == null) {
                    throw new NonexistentEntityException("The ssfFichamedicaatencion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfFichamedicaatencion ssfFichamedicaatencion;
            try {
                ssfFichamedicaatencion = em.getReference(SsfFichamedicaatencion.class, id);
                ssfFichamedicaatencion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfFichamedicaatencion with id " + id + " no longer exists.", enfe);
            }
            SsfAtencionmedica idAtencionmedica = ssfFichamedicaatencion.getIdAtencionmedica();
            if (idAtencionmedica != null) {
                idAtencionmedica.getSsfFichamedicaatencionList().remove(ssfFichamedicaatencion);
                idAtencionmedica = em.merge(idAtencionmedica);
            }
            SsfFichamedica idFichamedica = ssfFichamedicaatencion.getIdFichamedica();
            if (idFichamedica != null) {
                idFichamedica.getSsfFichamedicaatencionList().remove(ssfFichamedicaatencion);
                idFichamedica = em.merge(idFichamedica);
            }
            em.remove(ssfFichamedicaatencion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfFichamedicaatencion> findSsfFichamedicaatencionEntities() {
        return findSsfFichamedicaatencionEntities(true, -1, -1);
    }

    public List<SsfFichamedicaatencion> findSsfFichamedicaatencionEntities(int maxResults, int firstResult) {
        return findSsfFichamedicaatencionEntities(false, maxResults, firstResult);
    }

    private List<SsfFichamedicaatencion> findSsfFichamedicaatencionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfFichamedicaatencion.class));
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

    public SsfFichamedicaatencion findSsfFichamedicaatencion(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfFichamedicaatencion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfFichamedicaatencionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfFichamedicaatencion> rt = cq.from(SsfFichamedicaatencion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
