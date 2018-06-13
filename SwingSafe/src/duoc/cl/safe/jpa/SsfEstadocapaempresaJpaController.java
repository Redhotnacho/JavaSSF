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
import duoc.cl.safe.entity.SsfCapacitacionempresa;
import duoc.cl.safe.entity.SsfEstadocapaempresa;
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
public class SsfEstadocapaempresaJpaController implements Serializable {

    public SsfEstadocapaempresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfEstadocapaempresa ssfEstadocapaempresa) throws PreexistingEntityException, Exception {
        if (ssfEstadocapaempresa.getSsfCapacitacionempresaList() == null) {
            ssfEstadocapaempresa.setSsfCapacitacionempresaList(new ArrayList<SsfCapacitacionempresa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfCapacitacionempresa> attachedSsfCapacitacionempresaList = new ArrayList<SsfCapacitacionempresa>();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresaToAttach : ssfEstadocapaempresa.getSsfCapacitacionempresaList()) {
                ssfCapacitacionempresaListSsfCapacitacionempresaToAttach = em.getReference(ssfCapacitacionempresaListSsfCapacitacionempresaToAttach.getClass(), ssfCapacitacionempresaListSsfCapacitacionempresaToAttach.getId());
                attachedSsfCapacitacionempresaList.add(ssfCapacitacionempresaListSsfCapacitacionempresaToAttach);
            }
            ssfEstadocapaempresa.setSsfCapacitacionempresaList(attachedSsfCapacitacionempresaList);
            em.persist(ssfEstadocapaempresa);
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresa : ssfEstadocapaempresa.getSsfCapacitacionempresaList()) {
                SsfEstadocapaempresa oldIdEstadocapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa = ssfCapacitacionempresaListSsfCapacitacionempresa.getIdEstadocapacitacion();
                ssfCapacitacionempresaListSsfCapacitacionempresa.setIdEstadocapacitacion(ssfEstadocapaempresa);
                ssfCapacitacionempresaListSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListSsfCapacitacionempresa);
                if (oldIdEstadocapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa != null) {
                    oldIdEstadocapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresaListSsfCapacitacionempresa);
                    oldIdEstadocapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa = em.merge(oldIdEstadocapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfEstadocapaempresa(ssfEstadocapaempresa.getId()) != null) {
                throw new PreexistingEntityException("SsfEstadocapaempresa " + ssfEstadocapaempresa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfEstadocapaempresa ssfEstadocapaempresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEstadocapaempresa persistentSsfEstadocapaempresa = em.find(SsfEstadocapaempresa.class, ssfEstadocapaempresa.getId());
            List<SsfCapacitacionempresa> ssfCapacitacionempresaListOld = persistentSsfEstadocapaempresa.getSsfCapacitacionempresaList();
            List<SsfCapacitacionempresa> ssfCapacitacionempresaListNew = ssfEstadocapaempresa.getSsfCapacitacionempresaList();
            List<SsfCapacitacionempresa> attachedSsfCapacitacionempresaListNew = new ArrayList<SsfCapacitacionempresa>();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach : ssfCapacitacionempresaListNew) {
                ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach = em.getReference(ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach.getClass(), ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach.getId());
                attachedSsfCapacitacionempresaListNew.add(ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach);
            }
            ssfCapacitacionempresaListNew = attachedSsfCapacitacionempresaListNew;
            ssfEstadocapaempresa.setSsfCapacitacionempresaList(ssfCapacitacionempresaListNew);
            ssfEstadocapaempresa = em.merge(ssfEstadocapaempresa);
            for (SsfCapacitacionempresa ssfCapacitacionempresaListOldSsfCapacitacionempresa : ssfCapacitacionempresaListOld) {
                if (!ssfCapacitacionempresaListNew.contains(ssfCapacitacionempresaListOldSsfCapacitacionempresa)) {
                    ssfCapacitacionempresaListOldSsfCapacitacionempresa.setIdEstadocapacitacion(null);
                    ssfCapacitacionempresaListOldSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListOldSsfCapacitacionempresa);
                }
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListNewSsfCapacitacionempresa : ssfCapacitacionempresaListNew) {
                if (!ssfCapacitacionempresaListOld.contains(ssfCapacitacionempresaListNewSsfCapacitacionempresa)) {
                    SsfEstadocapaempresa oldIdEstadocapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa = ssfCapacitacionempresaListNewSsfCapacitacionempresa.getIdEstadocapacitacion();
                    ssfCapacitacionempresaListNewSsfCapacitacionempresa.setIdEstadocapacitacion(ssfEstadocapaempresa);
                    ssfCapacitacionempresaListNewSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListNewSsfCapacitacionempresa);
                    if (oldIdEstadocapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa != null && !oldIdEstadocapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa.equals(ssfEstadocapaempresa)) {
                        oldIdEstadocapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresaListNewSsfCapacitacionempresa);
                        oldIdEstadocapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa = em.merge(oldIdEstadocapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfEstadocapaempresa.getId();
                if (findSsfEstadocapaempresa(id) == null) {
                    throw new NonexistentEntityException("The ssfEstadocapaempresa with id " + id + " no longer exists.");
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
            SsfEstadocapaempresa ssfEstadocapaempresa;
            try {
                ssfEstadocapaempresa = em.getReference(SsfEstadocapaempresa.class, id);
                ssfEstadocapaempresa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfEstadocapaempresa with id " + id + " no longer exists.", enfe);
            }
            List<SsfCapacitacionempresa> ssfCapacitacionempresaList = ssfEstadocapaempresa.getSsfCapacitacionempresaList();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresa : ssfCapacitacionempresaList) {
                ssfCapacitacionempresaListSsfCapacitacionempresa.setIdEstadocapacitacion(null);
                ssfCapacitacionempresaListSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListSsfCapacitacionempresa);
            }
            em.remove(ssfEstadocapaempresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfEstadocapaempresa> findSsfEstadocapaempresaEntities() {
        return findSsfEstadocapaempresaEntities(true, -1, -1);
    }

    public List<SsfEstadocapaempresa> findSsfEstadocapaempresaEntities(int maxResults, int firstResult) {
        return findSsfEstadocapaempresaEntities(false, maxResults, firstResult);
    }

    private List<SsfEstadocapaempresa> findSsfEstadocapaempresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfEstadocapaempresa.class));
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

    public SsfEstadocapaempresa findSsfEstadocapaempresa(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfEstadocapaempresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfEstadocapaempresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfEstadocapaempresa> rt = cq.from(SsfEstadocapaempresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
