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
import duoc.cl.safe.entity.SsfEvaluacion;
import duoc.cl.safe.entity.SsfEvaluacionestado;
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
public class SsfEvaluacionestadoJpaController implements Serializable {

    public SsfEvaluacionestadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfEvaluacionestado ssfEvaluacionestado) throws PreexistingEntityException, Exception {
        if (ssfEvaluacionestado.getSsfEvaluacionList() == null) {
            ssfEvaluacionestado.setSsfEvaluacionList(new ArrayList<SsfEvaluacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfEvaluacion> attachedSsfEvaluacionList = new ArrayList<SsfEvaluacion>();
            for (SsfEvaluacion ssfEvaluacionListSsfEvaluacionToAttach : ssfEvaluacionestado.getSsfEvaluacionList()) {
                ssfEvaluacionListSsfEvaluacionToAttach = em.getReference(ssfEvaluacionListSsfEvaluacionToAttach.getClass(), ssfEvaluacionListSsfEvaluacionToAttach.getId());
                attachedSsfEvaluacionList.add(ssfEvaluacionListSsfEvaluacionToAttach);
            }
            ssfEvaluacionestado.setSsfEvaluacionList(attachedSsfEvaluacionList);
            em.persist(ssfEvaluacionestado);
            for (SsfEvaluacion ssfEvaluacionListSsfEvaluacion : ssfEvaluacionestado.getSsfEvaluacionList()) {
                SsfEvaluacionestado oldIdEvaluacionestadoOfSsfEvaluacionListSsfEvaluacion = ssfEvaluacionListSsfEvaluacion.getIdEvaluacionestado();
                ssfEvaluacionListSsfEvaluacion.setIdEvaluacionestado(ssfEvaluacionestado);
                ssfEvaluacionListSsfEvaluacion = em.merge(ssfEvaluacionListSsfEvaluacion);
                if (oldIdEvaluacionestadoOfSsfEvaluacionListSsfEvaluacion != null) {
                    oldIdEvaluacionestadoOfSsfEvaluacionListSsfEvaluacion.getSsfEvaluacionList().remove(ssfEvaluacionListSsfEvaluacion);
                    oldIdEvaluacionestadoOfSsfEvaluacionListSsfEvaluacion = em.merge(oldIdEvaluacionestadoOfSsfEvaluacionListSsfEvaluacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfEvaluacionestado(ssfEvaluacionestado.getId()) != null) {
                throw new PreexistingEntityException("SsfEvaluacionestado " + ssfEvaluacionestado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfEvaluacionestado ssfEvaluacionestado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEvaluacionestado persistentSsfEvaluacionestado = em.find(SsfEvaluacionestado.class, ssfEvaluacionestado.getId());
            List<SsfEvaluacion> ssfEvaluacionListOld = persistentSsfEvaluacionestado.getSsfEvaluacionList();
            List<SsfEvaluacion> ssfEvaluacionListNew = ssfEvaluacionestado.getSsfEvaluacionList();
            List<SsfEvaluacion> attachedSsfEvaluacionListNew = new ArrayList<SsfEvaluacion>();
            for (SsfEvaluacion ssfEvaluacionListNewSsfEvaluacionToAttach : ssfEvaluacionListNew) {
                ssfEvaluacionListNewSsfEvaluacionToAttach = em.getReference(ssfEvaluacionListNewSsfEvaluacionToAttach.getClass(), ssfEvaluacionListNewSsfEvaluacionToAttach.getId());
                attachedSsfEvaluacionListNew.add(ssfEvaluacionListNewSsfEvaluacionToAttach);
            }
            ssfEvaluacionListNew = attachedSsfEvaluacionListNew;
            ssfEvaluacionestado.setSsfEvaluacionList(ssfEvaluacionListNew);
            ssfEvaluacionestado = em.merge(ssfEvaluacionestado);
            for (SsfEvaluacion ssfEvaluacionListOldSsfEvaluacion : ssfEvaluacionListOld) {
                if (!ssfEvaluacionListNew.contains(ssfEvaluacionListOldSsfEvaluacion)) {
                    ssfEvaluacionListOldSsfEvaluacion.setIdEvaluacionestado(null);
                    ssfEvaluacionListOldSsfEvaluacion = em.merge(ssfEvaluacionListOldSsfEvaluacion);
                }
            }
            for (SsfEvaluacion ssfEvaluacionListNewSsfEvaluacion : ssfEvaluacionListNew) {
                if (!ssfEvaluacionListOld.contains(ssfEvaluacionListNewSsfEvaluacion)) {
                    SsfEvaluacionestado oldIdEvaluacionestadoOfSsfEvaluacionListNewSsfEvaluacion = ssfEvaluacionListNewSsfEvaluacion.getIdEvaluacionestado();
                    ssfEvaluacionListNewSsfEvaluacion.setIdEvaluacionestado(ssfEvaluacionestado);
                    ssfEvaluacionListNewSsfEvaluacion = em.merge(ssfEvaluacionListNewSsfEvaluacion);
                    if (oldIdEvaluacionestadoOfSsfEvaluacionListNewSsfEvaluacion != null && !oldIdEvaluacionestadoOfSsfEvaluacionListNewSsfEvaluacion.equals(ssfEvaluacionestado)) {
                        oldIdEvaluacionestadoOfSsfEvaluacionListNewSsfEvaluacion.getSsfEvaluacionList().remove(ssfEvaluacionListNewSsfEvaluacion);
                        oldIdEvaluacionestadoOfSsfEvaluacionListNewSsfEvaluacion = em.merge(oldIdEvaluacionestadoOfSsfEvaluacionListNewSsfEvaluacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfEvaluacionestado.getId();
                if (findSsfEvaluacionestado(id) == null) {
                    throw new NonexistentEntityException("The ssfEvaluacionestado with id " + id + " no longer exists.");
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
            SsfEvaluacionestado ssfEvaluacionestado;
            try {
                ssfEvaluacionestado = em.getReference(SsfEvaluacionestado.class, id);
                ssfEvaluacionestado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfEvaluacionestado with id " + id + " no longer exists.", enfe);
            }
            List<SsfEvaluacion> ssfEvaluacionList = ssfEvaluacionestado.getSsfEvaluacionList();
            for (SsfEvaluacion ssfEvaluacionListSsfEvaluacion : ssfEvaluacionList) {
                ssfEvaluacionListSsfEvaluacion.setIdEvaluacionestado(null);
                ssfEvaluacionListSsfEvaluacion = em.merge(ssfEvaluacionListSsfEvaluacion);
            }
            em.remove(ssfEvaluacionestado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfEvaluacionestado> findSsfEvaluacionestadoEntities() {
        return findSsfEvaluacionestadoEntities(true, -1, -1);
    }

    public List<SsfEvaluacionestado> findSsfEvaluacionestadoEntities(int maxResults, int firstResult) {
        return findSsfEvaluacionestadoEntities(false, maxResults, firstResult);
    }

    private List<SsfEvaluacionestado> findSsfEvaluacionestadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfEvaluacionestado.class));
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

    public SsfEvaluacionestado findSsfEvaluacionestado(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfEvaluacionestado.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfEvaluacionestadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfEvaluacionestado> rt = cq.from(SsfEvaluacionestado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
