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
import duoc.cl.safe.entity.SsfCapacitacion;
import duoc.cl.safe.entity.SsfCapacitaciontipo;
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
public class SsfCapacitaciontipoJpaController implements Serializable {

    public SsfCapacitaciontipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfCapacitaciontipo ssfCapacitaciontipo) throws PreexistingEntityException, Exception {
        if (ssfCapacitaciontipo.getSsfCapacitacionList() == null) {
            ssfCapacitaciontipo.setSsfCapacitacionList(new ArrayList<SsfCapacitacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfCapacitacion> attachedSsfCapacitacionList = new ArrayList<SsfCapacitacion>();
            for (SsfCapacitacion ssfCapacitacionListSsfCapacitacionToAttach : ssfCapacitaciontipo.getSsfCapacitacionList()) {
                ssfCapacitacionListSsfCapacitacionToAttach = em.getReference(ssfCapacitacionListSsfCapacitacionToAttach.getClass(), ssfCapacitacionListSsfCapacitacionToAttach.getId());
                attachedSsfCapacitacionList.add(ssfCapacitacionListSsfCapacitacionToAttach);
            }
            ssfCapacitaciontipo.setSsfCapacitacionList(attachedSsfCapacitacionList);
            em.persist(ssfCapacitaciontipo);
            for (SsfCapacitacion ssfCapacitacionListSsfCapacitacion : ssfCapacitaciontipo.getSsfCapacitacionList()) {
                SsfCapacitaciontipo oldIdCapacitaciontipoOfSsfCapacitacionListSsfCapacitacion = ssfCapacitacionListSsfCapacitacion.getIdCapacitaciontipo();
                ssfCapacitacionListSsfCapacitacion.setIdCapacitaciontipo(ssfCapacitaciontipo);
                ssfCapacitacionListSsfCapacitacion = em.merge(ssfCapacitacionListSsfCapacitacion);
                if (oldIdCapacitaciontipoOfSsfCapacitacionListSsfCapacitacion != null) {
                    oldIdCapacitaciontipoOfSsfCapacitacionListSsfCapacitacion.getSsfCapacitacionList().remove(ssfCapacitacionListSsfCapacitacion);
                    oldIdCapacitaciontipoOfSsfCapacitacionListSsfCapacitacion = em.merge(oldIdCapacitaciontipoOfSsfCapacitacionListSsfCapacitacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfCapacitaciontipo(ssfCapacitaciontipo.getId()) != null) {
                throw new PreexistingEntityException("SsfCapacitaciontipo " + ssfCapacitaciontipo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfCapacitaciontipo ssfCapacitaciontipo) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCapacitaciontipo persistentSsfCapacitaciontipo = em.find(SsfCapacitaciontipo.class, ssfCapacitaciontipo.getId());
            List<SsfCapacitacion> ssfCapacitacionListOld = persistentSsfCapacitaciontipo.getSsfCapacitacionList();
            List<SsfCapacitacion> ssfCapacitacionListNew = ssfCapacitaciontipo.getSsfCapacitacionList();
            List<SsfCapacitacion> attachedSsfCapacitacionListNew = new ArrayList<SsfCapacitacion>();
            for (SsfCapacitacion ssfCapacitacionListNewSsfCapacitacionToAttach : ssfCapacitacionListNew) {
                ssfCapacitacionListNewSsfCapacitacionToAttach = em.getReference(ssfCapacitacionListNewSsfCapacitacionToAttach.getClass(), ssfCapacitacionListNewSsfCapacitacionToAttach.getId());
                attachedSsfCapacitacionListNew.add(ssfCapacitacionListNewSsfCapacitacionToAttach);
            }
            ssfCapacitacionListNew = attachedSsfCapacitacionListNew;
            ssfCapacitaciontipo.setSsfCapacitacionList(ssfCapacitacionListNew);
            ssfCapacitaciontipo = em.merge(ssfCapacitaciontipo);
            for (SsfCapacitacion ssfCapacitacionListOldSsfCapacitacion : ssfCapacitacionListOld) {
                if (!ssfCapacitacionListNew.contains(ssfCapacitacionListOldSsfCapacitacion)) {
                    ssfCapacitacionListOldSsfCapacitacion.setIdCapacitaciontipo(null);
                    ssfCapacitacionListOldSsfCapacitacion = em.merge(ssfCapacitacionListOldSsfCapacitacion);
                }
            }
            for (SsfCapacitacion ssfCapacitacionListNewSsfCapacitacion : ssfCapacitacionListNew) {
                if (!ssfCapacitacionListOld.contains(ssfCapacitacionListNewSsfCapacitacion)) {
                    SsfCapacitaciontipo oldIdCapacitaciontipoOfSsfCapacitacionListNewSsfCapacitacion = ssfCapacitacionListNewSsfCapacitacion.getIdCapacitaciontipo();
                    ssfCapacitacionListNewSsfCapacitacion.setIdCapacitaciontipo(ssfCapacitaciontipo);
                    ssfCapacitacionListNewSsfCapacitacion = em.merge(ssfCapacitacionListNewSsfCapacitacion);
                    if (oldIdCapacitaciontipoOfSsfCapacitacionListNewSsfCapacitacion != null && !oldIdCapacitaciontipoOfSsfCapacitacionListNewSsfCapacitacion.equals(ssfCapacitaciontipo)) {
                        oldIdCapacitaciontipoOfSsfCapacitacionListNewSsfCapacitacion.getSsfCapacitacionList().remove(ssfCapacitacionListNewSsfCapacitacion);
                        oldIdCapacitaciontipoOfSsfCapacitacionListNewSsfCapacitacion = em.merge(oldIdCapacitaciontipoOfSsfCapacitacionListNewSsfCapacitacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfCapacitaciontipo.getId();
                if (findSsfCapacitaciontipo(id) == null) {
                    throw new NonexistentEntityException("The ssfCapacitaciontipo with id " + id + " no longer exists.");
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
            SsfCapacitaciontipo ssfCapacitaciontipo;
            try {
                ssfCapacitaciontipo = em.getReference(SsfCapacitaciontipo.class, id);
                ssfCapacitaciontipo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfCapacitaciontipo with id " + id + " no longer exists.", enfe);
            }
            List<SsfCapacitacion> ssfCapacitacionList = ssfCapacitaciontipo.getSsfCapacitacionList();
            for (SsfCapacitacion ssfCapacitacionListSsfCapacitacion : ssfCapacitacionList) {
                ssfCapacitacionListSsfCapacitacion.setIdCapacitaciontipo(null);
                ssfCapacitacionListSsfCapacitacion = em.merge(ssfCapacitacionListSsfCapacitacion);
            }
            em.remove(ssfCapacitaciontipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfCapacitaciontipo> findSsfCapacitaciontipoEntities() {
        return findSsfCapacitaciontipoEntities(true, -1, -1);
    }

    public List<SsfCapacitaciontipo> findSsfCapacitaciontipoEntities(int maxResults, int firstResult) {
        return findSsfCapacitaciontipoEntities(false, maxResults, firstResult);
    }

    private List<SsfCapacitaciontipo> findSsfCapacitaciontipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfCapacitaciontipo.class));
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

    public SsfCapacitaciontipo findSsfCapacitaciontipo(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfCapacitaciontipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfCapacitaciontipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfCapacitaciontipo> rt = cq.from(SsfCapacitaciontipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
