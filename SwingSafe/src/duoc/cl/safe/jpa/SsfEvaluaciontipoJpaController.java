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
import duoc.cl.safe.entity.SsfEvaluaciontipo;
import java.util.ArrayList;
import java.util.List;
import duoc.cl.safe.entity.SsfParametro;
import duoc.cl.safe.jpa.exceptions.IllegalOrphanException;
import duoc.cl.safe.jpa.exceptions.NonexistentEntityException;
import duoc.cl.safe.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nacho
 */
public class SsfEvaluaciontipoJpaController implements Serializable {

    public SsfEvaluaciontipoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfEvaluaciontipo ssfEvaluaciontipo) throws PreexistingEntityException, Exception {
        if (ssfEvaluaciontipo.getSsfEvaluacionList() == null) {
            ssfEvaluaciontipo.setSsfEvaluacionList(new ArrayList<SsfEvaluacion>());
        }
        if (ssfEvaluaciontipo.getSsfParametroList() == null) {
            ssfEvaluaciontipo.setSsfParametroList(new ArrayList<SsfParametro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfEvaluacion> attachedSsfEvaluacionList = new ArrayList<SsfEvaluacion>();
            for (SsfEvaluacion ssfEvaluacionListSsfEvaluacionToAttach : ssfEvaluaciontipo.getSsfEvaluacionList()) {
                ssfEvaluacionListSsfEvaluacionToAttach = em.getReference(ssfEvaluacionListSsfEvaluacionToAttach.getClass(), ssfEvaluacionListSsfEvaluacionToAttach.getId());
                attachedSsfEvaluacionList.add(ssfEvaluacionListSsfEvaluacionToAttach);
            }
            ssfEvaluaciontipo.setSsfEvaluacionList(attachedSsfEvaluacionList);
            List<SsfParametro> attachedSsfParametroList = new ArrayList<SsfParametro>();
            for (SsfParametro ssfParametroListSsfParametroToAttach : ssfEvaluaciontipo.getSsfParametroList()) {
                ssfParametroListSsfParametroToAttach = em.getReference(ssfParametroListSsfParametroToAttach.getClass(), ssfParametroListSsfParametroToAttach.getId());
                attachedSsfParametroList.add(ssfParametroListSsfParametroToAttach);
            }
            ssfEvaluaciontipo.setSsfParametroList(attachedSsfParametroList);
            em.persist(ssfEvaluaciontipo);
            for (SsfEvaluacion ssfEvaluacionListSsfEvaluacion : ssfEvaluaciontipo.getSsfEvaluacionList()) {
                SsfEvaluaciontipo oldIdEvaluaciontipoOfSsfEvaluacionListSsfEvaluacion = ssfEvaluacionListSsfEvaluacion.getIdEvaluaciontipo();
                ssfEvaluacionListSsfEvaluacion.setIdEvaluaciontipo(ssfEvaluaciontipo);
                ssfEvaluacionListSsfEvaluacion = em.merge(ssfEvaluacionListSsfEvaluacion);
                if (oldIdEvaluaciontipoOfSsfEvaluacionListSsfEvaluacion != null) {
                    oldIdEvaluaciontipoOfSsfEvaluacionListSsfEvaluacion.getSsfEvaluacionList().remove(ssfEvaluacionListSsfEvaluacion);
                    oldIdEvaluaciontipoOfSsfEvaluacionListSsfEvaluacion = em.merge(oldIdEvaluaciontipoOfSsfEvaluacionListSsfEvaluacion);
                }
            }
            for (SsfParametro ssfParametroListSsfParametro : ssfEvaluaciontipo.getSsfParametroList()) {
                SsfEvaluaciontipo oldIdEvaluaciontipoOfSsfParametroListSsfParametro = ssfParametroListSsfParametro.getIdEvaluaciontipo();
                ssfParametroListSsfParametro.setIdEvaluaciontipo(ssfEvaluaciontipo);
                ssfParametroListSsfParametro = em.merge(ssfParametroListSsfParametro);
                if (oldIdEvaluaciontipoOfSsfParametroListSsfParametro != null) {
                    oldIdEvaluaciontipoOfSsfParametroListSsfParametro.getSsfParametroList().remove(ssfParametroListSsfParametro);
                    oldIdEvaluaciontipoOfSsfParametroListSsfParametro = em.merge(oldIdEvaluaciontipoOfSsfParametroListSsfParametro);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfEvaluaciontipo(ssfEvaluaciontipo.getId()) != null) {
                throw new PreexistingEntityException("SsfEvaluaciontipo " + ssfEvaluaciontipo + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfEvaluaciontipo ssfEvaluaciontipo) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEvaluaciontipo persistentSsfEvaluaciontipo = em.find(SsfEvaluaciontipo.class, ssfEvaluaciontipo.getId());
            List<SsfEvaluacion> ssfEvaluacionListOld = persistentSsfEvaluaciontipo.getSsfEvaluacionList();
            List<SsfEvaluacion> ssfEvaluacionListNew = ssfEvaluaciontipo.getSsfEvaluacionList();
            List<SsfParametro> ssfParametroListOld = persistentSsfEvaluaciontipo.getSsfParametroList();
            List<SsfParametro> ssfParametroListNew = ssfEvaluaciontipo.getSsfParametroList();
            List<String> illegalOrphanMessages = null;
            for (SsfParametro ssfParametroListOldSsfParametro : ssfParametroListOld) {
                if (!ssfParametroListNew.contains(ssfParametroListOldSsfParametro)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SsfParametro " + ssfParametroListOldSsfParametro + " since its idEvaluaciontipo field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SsfEvaluacion> attachedSsfEvaluacionListNew = new ArrayList<SsfEvaluacion>();
            for (SsfEvaluacion ssfEvaluacionListNewSsfEvaluacionToAttach : ssfEvaluacionListNew) {
                ssfEvaluacionListNewSsfEvaluacionToAttach = em.getReference(ssfEvaluacionListNewSsfEvaluacionToAttach.getClass(), ssfEvaluacionListNewSsfEvaluacionToAttach.getId());
                attachedSsfEvaluacionListNew.add(ssfEvaluacionListNewSsfEvaluacionToAttach);
            }
            ssfEvaluacionListNew = attachedSsfEvaluacionListNew;
            ssfEvaluaciontipo.setSsfEvaluacionList(ssfEvaluacionListNew);
            List<SsfParametro> attachedSsfParametroListNew = new ArrayList<SsfParametro>();
            for (SsfParametro ssfParametroListNewSsfParametroToAttach : ssfParametroListNew) {
                ssfParametroListNewSsfParametroToAttach = em.getReference(ssfParametroListNewSsfParametroToAttach.getClass(), ssfParametroListNewSsfParametroToAttach.getId());
                attachedSsfParametroListNew.add(ssfParametroListNewSsfParametroToAttach);
            }
            ssfParametroListNew = attachedSsfParametroListNew;
            ssfEvaluaciontipo.setSsfParametroList(ssfParametroListNew);
            ssfEvaluaciontipo = em.merge(ssfEvaluaciontipo);
            for (SsfEvaluacion ssfEvaluacionListOldSsfEvaluacion : ssfEvaluacionListOld) {
                if (!ssfEvaluacionListNew.contains(ssfEvaluacionListOldSsfEvaluacion)) {
                    ssfEvaluacionListOldSsfEvaluacion.setIdEvaluaciontipo(null);
                    ssfEvaluacionListOldSsfEvaluacion = em.merge(ssfEvaluacionListOldSsfEvaluacion);
                }
            }
            for (SsfEvaluacion ssfEvaluacionListNewSsfEvaluacion : ssfEvaluacionListNew) {
                if (!ssfEvaluacionListOld.contains(ssfEvaluacionListNewSsfEvaluacion)) {
                    SsfEvaluaciontipo oldIdEvaluaciontipoOfSsfEvaluacionListNewSsfEvaluacion = ssfEvaluacionListNewSsfEvaluacion.getIdEvaluaciontipo();
                    ssfEvaluacionListNewSsfEvaluacion.setIdEvaluaciontipo(ssfEvaluaciontipo);
                    ssfEvaluacionListNewSsfEvaluacion = em.merge(ssfEvaluacionListNewSsfEvaluacion);
                    if (oldIdEvaluaciontipoOfSsfEvaluacionListNewSsfEvaluacion != null && !oldIdEvaluaciontipoOfSsfEvaluacionListNewSsfEvaluacion.equals(ssfEvaluaciontipo)) {
                        oldIdEvaluaciontipoOfSsfEvaluacionListNewSsfEvaluacion.getSsfEvaluacionList().remove(ssfEvaluacionListNewSsfEvaluacion);
                        oldIdEvaluaciontipoOfSsfEvaluacionListNewSsfEvaluacion = em.merge(oldIdEvaluaciontipoOfSsfEvaluacionListNewSsfEvaluacion);
                    }
                }
            }
            for (SsfParametro ssfParametroListNewSsfParametro : ssfParametroListNew) {
                if (!ssfParametroListOld.contains(ssfParametroListNewSsfParametro)) {
                    SsfEvaluaciontipo oldIdEvaluaciontipoOfSsfParametroListNewSsfParametro = ssfParametroListNewSsfParametro.getIdEvaluaciontipo();
                    ssfParametroListNewSsfParametro.setIdEvaluaciontipo(ssfEvaluaciontipo);
                    ssfParametroListNewSsfParametro = em.merge(ssfParametroListNewSsfParametro);
                    if (oldIdEvaluaciontipoOfSsfParametroListNewSsfParametro != null && !oldIdEvaluaciontipoOfSsfParametroListNewSsfParametro.equals(ssfEvaluaciontipo)) {
                        oldIdEvaluaciontipoOfSsfParametroListNewSsfParametro.getSsfParametroList().remove(ssfParametroListNewSsfParametro);
                        oldIdEvaluaciontipoOfSsfParametroListNewSsfParametro = em.merge(oldIdEvaluaciontipoOfSsfParametroListNewSsfParametro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfEvaluaciontipo.getId();
                if (findSsfEvaluaciontipo(id) == null) {
                    throw new NonexistentEntityException("The ssfEvaluaciontipo with id " + id + " no longer exists.");
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
            SsfEvaluaciontipo ssfEvaluaciontipo;
            try {
                ssfEvaluaciontipo = em.getReference(SsfEvaluaciontipo.class, id);
                ssfEvaluaciontipo.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfEvaluaciontipo with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SsfParametro> ssfParametroListOrphanCheck = ssfEvaluaciontipo.getSsfParametroList();
            for (SsfParametro ssfParametroListOrphanCheckSsfParametro : ssfParametroListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SsfEvaluaciontipo (" + ssfEvaluaciontipo + ") cannot be destroyed since the SsfParametro " + ssfParametroListOrphanCheckSsfParametro + " in its ssfParametroList field has a non-nullable idEvaluaciontipo field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SsfEvaluacion> ssfEvaluacionList = ssfEvaluaciontipo.getSsfEvaluacionList();
            for (SsfEvaluacion ssfEvaluacionListSsfEvaluacion : ssfEvaluacionList) {
                ssfEvaluacionListSsfEvaluacion.setIdEvaluaciontipo(null);
                ssfEvaluacionListSsfEvaluacion = em.merge(ssfEvaluacionListSsfEvaluacion);
            }
            em.remove(ssfEvaluaciontipo);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfEvaluaciontipo> findSsfEvaluaciontipoEntities() {
        return findSsfEvaluaciontipoEntities(true, -1, -1);
    }

    public List<SsfEvaluaciontipo> findSsfEvaluaciontipoEntities(int maxResults, int firstResult) {
        return findSsfEvaluaciontipoEntities(false, maxResults, firstResult);
    }

    private List<SsfEvaluaciontipo> findSsfEvaluaciontipoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfEvaluaciontipo.class));
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

    public SsfEvaluaciontipo findSsfEvaluaciontipo(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfEvaluaciontipo.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfEvaluaciontipoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfEvaluaciontipo> rt = cq.from(SsfEvaluaciontipo.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
