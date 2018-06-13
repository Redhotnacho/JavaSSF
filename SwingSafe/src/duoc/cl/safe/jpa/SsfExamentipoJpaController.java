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
import duoc.cl.safe.entity.SsfExamen;
import duoc.cl.safe.entity.SsfExamentipo;
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
public class SsfExamentipoJpaController implements Serializable {

    public SsfExamentipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfExamentipo ssfExamentipo) throws PreexistingEntityException, Exception {
        if (ssfExamentipo.getSsfExamenList() == null) {
            ssfExamentipo.setSsfExamenList(new ArrayList<SsfExamen>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfExamen> attachedSsfExamenList = new ArrayList<SsfExamen>();
            for (SsfExamen ssfExamenListSsfExamenToAttach : ssfExamentipo.getSsfExamenList()) {
                ssfExamenListSsfExamenToAttach = em.getReference(ssfExamenListSsfExamenToAttach.getClass(), ssfExamenListSsfExamenToAttach.getId());
                attachedSsfExamenList.add(ssfExamenListSsfExamenToAttach);
            }
            ssfExamentipo.setSsfExamenList(attachedSsfExamenList);
            em.persist(ssfExamentipo);
            for (SsfExamen ssfExamenListSsfExamen : ssfExamentipo.getSsfExamenList()) {
                SsfExamentipo oldIdExamentipoOfSsfExamenListSsfExamen = ssfExamenListSsfExamen.getIdExamentipo();
                ssfExamenListSsfExamen.setIdExamentipo(ssfExamentipo);
                ssfExamenListSsfExamen = em.merge(ssfExamenListSsfExamen);
                if (oldIdExamentipoOfSsfExamenListSsfExamen != null) {
                    oldIdExamentipoOfSsfExamenListSsfExamen.getSsfExamenList().remove(ssfExamenListSsfExamen);
                    oldIdExamentipoOfSsfExamenListSsfExamen = em.merge(oldIdExamentipoOfSsfExamenListSsfExamen);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfExamentipo(ssfExamentipo.getId()) != null) {
                throw new PreexistingEntityException("SsfExamentipo " + ssfExamentipo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfExamentipo ssfExamentipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfExamentipo persistentSsfExamentipo = em.find(SsfExamentipo.class, ssfExamentipo.getId());
            List<SsfExamen> ssfExamenListOld = persistentSsfExamentipo.getSsfExamenList();
            List<SsfExamen> ssfExamenListNew = ssfExamentipo.getSsfExamenList();
            List<String> illegalOrphanMessages = null;
            for (SsfExamen ssfExamenListOldSsfExamen : ssfExamenListOld) {
                if (!ssfExamenListNew.contains(ssfExamenListOldSsfExamen)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SsfExamen " + ssfExamenListOldSsfExamen + " since its idExamentipo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SsfExamen> attachedSsfExamenListNew = new ArrayList<SsfExamen>();
            for (SsfExamen ssfExamenListNewSsfExamenToAttach : ssfExamenListNew) {
                ssfExamenListNewSsfExamenToAttach = em.getReference(ssfExamenListNewSsfExamenToAttach.getClass(), ssfExamenListNewSsfExamenToAttach.getId());
                attachedSsfExamenListNew.add(ssfExamenListNewSsfExamenToAttach);
            }
            ssfExamenListNew = attachedSsfExamenListNew;
            ssfExamentipo.setSsfExamenList(ssfExamenListNew);
            ssfExamentipo = em.merge(ssfExamentipo);
            for (SsfExamen ssfExamenListNewSsfExamen : ssfExamenListNew) {
                if (!ssfExamenListOld.contains(ssfExamenListNewSsfExamen)) {
                    SsfExamentipo oldIdExamentipoOfSsfExamenListNewSsfExamen = ssfExamenListNewSsfExamen.getIdExamentipo();
                    ssfExamenListNewSsfExamen.setIdExamentipo(ssfExamentipo);
                    ssfExamenListNewSsfExamen = em.merge(ssfExamenListNewSsfExamen);
                    if (oldIdExamentipoOfSsfExamenListNewSsfExamen != null && !oldIdExamentipoOfSsfExamenListNewSsfExamen.equals(ssfExamentipo)) {
                        oldIdExamentipoOfSsfExamenListNewSsfExamen.getSsfExamenList().remove(ssfExamenListNewSsfExamen);
                        oldIdExamentipoOfSsfExamenListNewSsfExamen = em.merge(oldIdExamentipoOfSsfExamenListNewSsfExamen);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfExamentipo.getId();
                if (findSsfExamentipo(id) == null) {
                    throw new NonexistentEntityException("The ssfExamentipo with id " + id + " no longer exists.");
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
            SsfExamentipo ssfExamentipo;
            try {
                ssfExamentipo = em.getReference(SsfExamentipo.class, id);
                ssfExamentipo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfExamentipo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SsfExamen> ssfExamenListOrphanCheck = ssfExamentipo.getSsfExamenList();
            for (SsfExamen ssfExamenListOrphanCheckSsfExamen : ssfExamenListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SsfExamentipo (" + ssfExamentipo + ") cannot be destroyed since the SsfExamen " + ssfExamenListOrphanCheckSsfExamen + " in its ssfExamenList field has a non-nullable idExamentipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ssfExamentipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfExamentipo> findSsfExamentipoEntities() {
        return findSsfExamentipoEntities(true, -1, -1);
    }

    public List<SsfExamentipo> findSsfExamentipoEntities(int maxResults, int firstResult) {
        return findSsfExamentipoEntities(false, maxResults, firstResult);
    }

    private List<SsfExamentipo> findSsfExamentipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfExamentipo.class));
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

    public SsfExamentipo findSsfExamentipo(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfExamentipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfExamentipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfExamentipo> rt = cq.from(SsfExamentipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
