/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.jpa;

import duoc.cl.safe.entity.SsfCentromedico;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import duoc.cl.safe.entity.SsfMedico;
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
public class SsfCentromedicoJpaController implements Serializable {

    public SsfCentromedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfCentromedico ssfCentromedico) throws PreexistingEntityException, Exception {
        if (ssfCentromedico.getSsfMedicoList() == null) {
            ssfCentromedico.setSsfMedicoList(new ArrayList<SsfMedico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfMedico> attachedSsfMedicoList = new ArrayList<SsfMedico>();
            for (SsfMedico ssfMedicoListSsfMedicoToAttach : ssfCentromedico.getSsfMedicoList()) {
                ssfMedicoListSsfMedicoToAttach = em.getReference(ssfMedicoListSsfMedicoToAttach.getClass(), ssfMedicoListSsfMedicoToAttach.getId());
                attachedSsfMedicoList.add(ssfMedicoListSsfMedicoToAttach);
            }
            ssfCentromedico.setSsfMedicoList(attachedSsfMedicoList);
            em.persist(ssfCentromedico);
            for (SsfMedico ssfMedicoListSsfMedico : ssfCentromedico.getSsfMedicoList()) {
                SsfCentromedico oldIdCentromedicoOfSsfMedicoListSsfMedico = ssfMedicoListSsfMedico.getIdCentromedico();
                ssfMedicoListSsfMedico.setIdCentromedico(ssfCentromedico);
                ssfMedicoListSsfMedico = em.merge(ssfMedicoListSsfMedico);
                if (oldIdCentromedicoOfSsfMedicoListSsfMedico != null) {
                    oldIdCentromedicoOfSsfMedicoListSsfMedico.getSsfMedicoList().remove(ssfMedicoListSsfMedico);
                    oldIdCentromedicoOfSsfMedicoListSsfMedico = em.merge(oldIdCentromedicoOfSsfMedicoListSsfMedico);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfCentromedico(ssfCentromedico.getId()) != null) {
                throw new PreexistingEntityException("SsfCentromedico " + ssfCentromedico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfCentromedico ssfCentromedico) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCentromedico persistentSsfCentromedico = em.find(SsfCentromedico.class, ssfCentromedico.getId());
            List<SsfMedico> ssfMedicoListOld = persistentSsfCentromedico.getSsfMedicoList();
            List<SsfMedico> ssfMedicoListNew = ssfCentromedico.getSsfMedicoList();
            List<String> illegalOrphanMessages = null;
            for (SsfMedico ssfMedicoListOldSsfMedico : ssfMedicoListOld) {
                if (!ssfMedicoListNew.contains(ssfMedicoListOldSsfMedico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SsfMedico " + ssfMedicoListOldSsfMedico + " since its idCentromedico field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SsfMedico> attachedSsfMedicoListNew = new ArrayList<SsfMedico>();
            for (SsfMedico ssfMedicoListNewSsfMedicoToAttach : ssfMedicoListNew) {
                ssfMedicoListNewSsfMedicoToAttach = em.getReference(ssfMedicoListNewSsfMedicoToAttach.getClass(), ssfMedicoListNewSsfMedicoToAttach.getId());
                attachedSsfMedicoListNew.add(ssfMedicoListNewSsfMedicoToAttach);
            }
            ssfMedicoListNew = attachedSsfMedicoListNew;
            ssfCentromedico.setSsfMedicoList(ssfMedicoListNew);
            ssfCentromedico = em.merge(ssfCentromedico);
            for (SsfMedico ssfMedicoListNewSsfMedico : ssfMedicoListNew) {
                if (!ssfMedicoListOld.contains(ssfMedicoListNewSsfMedico)) {
                    SsfCentromedico oldIdCentromedicoOfSsfMedicoListNewSsfMedico = ssfMedicoListNewSsfMedico.getIdCentromedico();
                    ssfMedicoListNewSsfMedico.setIdCentromedico(ssfCentromedico);
                    ssfMedicoListNewSsfMedico = em.merge(ssfMedicoListNewSsfMedico);
                    if (oldIdCentromedicoOfSsfMedicoListNewSsfMedico != null && !oldIdCentromedicoOfSsfMedicoListNewSsfMedico.equals(ssfCentromedico)) {
                        oldIdCentromedicoOfSsfMedicoListNewSsfMedico.getSsfMedicoList().remove(ssfMedicoListNewSsfMedico);
                        oldIdCentromedicoOfSsfMedicoListNewSsfMedico = em.merge(oldIdCentromedicoOfSsfMedicoListNewSsfMedico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfCentromedico.getId();
                if (findSsfCentromedico(id) == null) {
                    throw new NonexistentEntityException("The ssfCentromedico with id " + id + " no longer exists.");
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
            SsfCentromedico ssfCentromedico;
            try {
                ssfCentromedico = em.getReference(SsfCentromedico.class, id);
                ssfCentromedico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfCentromedico with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SsfMedico> ssfMedicoListOrphanCheck = ssfCentromedico.getSsfMedicoList();
            for (SsfMedico ssfMedicoListOrphanCheckSsfMedico : ssfMedicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SsfCentromedico (" + ssfCentromedico + ") cannot be destroyed since the SsfMedico " + ssfMedicoListOrphanCheckSsfMedico + " in its ssfMedicoList field has a non-nullable idCentromedico field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ssfCentromedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfCentromedico> findSsfCentromedicoEntities() {
        return findSsfCentromedicoEntities(true, -1, -1);
    }

    public List<SsfCentromedico> findSsfCentromedicoEntities(int maxResults, int firstResult) {
        return findSsfCentromedicoEntities(false, maxResults, firstResult);
    }

    private List<SsfCentromedico> findSsfCentromedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfCentromedico.class));
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

    public SsfCentromedico findSsfCentromedico(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfCentromedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfCentromedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfCentromedico> rt = cq.from(SsfCentromedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
