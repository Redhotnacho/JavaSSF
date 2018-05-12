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
import duoc.cl.safe.entity.SsfEvaluaciontipo;
import duoc.cl.safe.entity.SsfEvaluacionparametro;
import duoc.cl.safe.entity.SsfParametro;
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
public class SsfParametroJpaController implements Serializable {

    public SsfParametroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfParametro ssfParametro) throws PreexistingEntityException, Exception {
        if (ssfParametro.getSsfEvaluacionparametroList() == null) {
            ssfParametro.setSsfEvaluacionparametroList(new ArrayList<SsfEvaluacionparametro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEvaluaciontipo idEvaluaciontipo = ssfParametro.getIdEvaluaciontipo();
            if (idEvaluaciontipo != null) {
                idEvaluaciontipo = em.getReference(idEvaluaciontipo.getClass(), idEvaluaciontipo.getId());
                ssfParametro.setIdEvaluaciontipo(idEvaluaciontipo);
            }
            List<SsfEvaluacionparametro> attachedSsfEvaluacionparametroList = new ArrayList<SsfEvaluacionparametro>();
            for (SsfEvaluacionparametro ssfEvaluacionparametroListSsfEvaluacionparametroToAttach : ssfParametro.getSsfEvaluacionparametroList()) {
                ssfEvaluacionparametroListSsfEvaluacionparametroToAttach = em.getReference(ssfEvaluacionparametroListSsfEvaluacionparametroToAttach.getClass(), ssfEvaluacionparametroListSsfEvaluacionparametroToAttach.getId());
                attachedSsfEvaluacionparametroList.add(ssfEvaluacionparametroListSsfEvaluacionparametroToAttach);
            }
            ssfParametro.setSsfEvaluacionparametroList(attachedSsfEvaluacionparametroList);
            em.persist(ssfParametro);
            if (idEvaluaciontipo != null) {
                idEvaluaciontipo.getSsfParametroList().add(ssfParametro);
                idEvaluaciontipo = em.merge(idEvaluaciontipo);
            }
            for (SsfEvaluacionparametro ssfEvaluacionparametroListSsfEvaluacionparametro : ssfParametro.getSsfEvaluacionparametroList()) {
                SsfParametro oldIdParametroOfSsfEvaluacionparametroListSsfEvaluacionparametro = ssfEvaluacionparametroListSsfEvaluacionparametro.getIdParametro();
                ssfEvaluacionparametroListSsfEvaluacionparametro.setIdParametro(ssfParametro);
                ssfEvaluacionparametroListSsfEvaluacionparametro = em.merge(ssfEvaluacionparametroListSsfEvaluacionparametro);
                if (oldIdParametroOfSsfEvaluacionparametroListSsfEvaluacionparametro != null) {
                    oldIdParametroOfSsfEvaluacionparametroListSsfEvaluacionparametro.getSsfEvaluacionparametroList().remove(ssfEvaluacionparametroListSsfEvaluacionparametro);
                    oldIdParametroOfSsfEvaluacionparametroListSsfEvaluacionparametro = em.merge(oldIdParametroOfSsfEvaluacionparametroListSsfEvaluacionparametro);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfParametro(ssfParametro.getId()) != null) {
                throw new PreexistingEntityException("SsfParametro " + ssfParametro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfParametro ssfParametro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfParametro persistentSsfParametro = em.find(SsfParametro.class, ssfParametro.getId());
            SsfEvaluaciontipo idEvaluaciontipoOld = persistentSsfParametro.getIdEvaluaciontipo();
            SsfEvaluaciontipo idEvaluaciontipoNew = ssfParametro.getIdEvaluaciontipo();
            List<SsfEvaluacionparametro> ssfEvaluacionparametroListOld = persistentSsfParametro.getSsfEvaluacionparametroList();
            List<SsfEvaluacionparametro> ssfEvaluacionparametroListNew = ssfParametro.getSsfEvaluacionparametroList();
            if (idEvaluaciontipoNew != null) {
                idEvaluaciontipoNew = em.getReference(idEvaluaciontipoNew.getClass(), idEvaluaciontipoNew.getId());
                ssfParametro.setIdEvaluaciontipo(idEvaluaciontipoNew);
            }
            List<SsfEvaluacionparametro> attachedSsfEvaluacionparametroListNew = new ArrayList<SsfEvaluacionparametro>();
            for (SsfEvaluacionparametro ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach : ssfEvaluacionparametroListNew) {
                ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach = em.getReference(ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach.getClass(), ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach.getId());
                attachedSsfEvaluacionparametroListNew.add(ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach);
            }
            ssfEvaluacionparametroListNew = attachedSsfEvaluacionparametroListNew;
            ssfParametro.setSsfEvaluacionparametroList(ssfEvaluacionparametroListNew);
            ssfParametro = em.merge(ssfParametro);
            if (idEvaluaciontipoOld != null && !idEvaluaciontipoOld.equals(idEvaluaciontipoNew)) {
                idEvaluaciontipoOld.getSsfParametroList().remove(ssfParametro);
                idEvaluaciontipoOld = em.merge(idEvaluaciontipoOld);
            }
            if (idEvaluaciontipoNew != null && !idEvaluaciontipoNew.equals(idEvaluaciontipoOld)) {
                idEvaluaciontipoNew.getSsfParametroList().add(ssfParametro);
                idEvaluaciontipoNew = em.merge(idEvaluaciontipoNew);
            }
            for (SsfEvaluacionparametro ssfEvaluacionparametroListOldSsfEvaluacionparametro : ssfEvaluacionparametroListOld) {
                if (!ssfEvaluacionparametroListNew.contains(ssfEvaluacionparametroListOldSsfEvaluacionparametro)) {
                    ssfEvaluacionparametroListOldSsfEvaluacionparametro.setIdParametro(null);
                    ssfEvaluacionparametroListOldSsfEvaluacionparametro = em.merge(ssfEvaluacionparametroListOldSsfEvaluacionparametro);
                }
            }
            for (SsfEvaluacionparametro ssfEvaluacionparametroListNewSsfEvaluacionparametro : ssfEvaluacionparametroListNew) {
                if (!ssfEvaluacionparametroListOld.contains(ssfEvaluacionparametroListNewSsfEvaluacionparametro)) {
                    SsfParametro oldIdParametroOfSsfEvaluacionparametroListNewSsfEvaluacionparametro = ssfEvaluacionparametroListNewSsfEvaluacionparametro.getIdParametro();
                    ssfEvaluacionparametroListNewSsfEvaluacionparametro.setIdParametro(ssfParametro);
                    ssfEvaluacionparametroListNewSsfEvaluacionparametro = em.merge(ssfEvaluacionparametroListNewSsfEvaluacionparametro);
                    if (oldIdParametroOfSsfEvaluacionparametroListNewSsfEvaluacionparametro != null && !oldIdParametroOfSsfEvaluacionparametroListNewSsfEvaluacionparametro.equals(ssfParametro)) {
                        oldIdParametroOfSsfEvaluacionparametroListNewSsfEvaluacionparametro.getSsfEvaluacionparametroList().remove(ssfEvaluacionparametroListNewSsfEvaluacionparametro);
                        oldIdParametroOfSsfEvaluacionparametroListNewSsfEvaluacionparametro = em.merge(oldIdParametroOfSsfEvaluacionparametroListNewSsfEvaluacionparametro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfParametro.getId();
                if (findSsfParametro(id) == null) {
                    throw new NonexistentEntityException("The ssfParametro with id " + id + " no longer exists.");
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
            SsfParametro ssfParametro;
            try {
                ssfParametro = em.getReference(SsfParametro.class, id);
                ssfParametro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfParametro with id " + id + " no longer exists.", enfe);
            }
            SsfEvaluaciontipo idEvaluaciontipo = ssfParametro.getIdEvaluaciontipo();
            if (idEvaluaciontipo != null) {
                idEvaluaciontipo.getSsfParametroList().remove(ssfParametro);
                idEvaluaciontipo = em.merge(idEvaluaciontipo);
            }
            List<SsfEvaluacionparametro> ssfEvaluacionparametroList = ssfParametro.getSsfEvaluacionparametroList();
            for (SsfEvaluacionparametro ssfEvaluacionparametroListSsfEvaluacionparametro : ssfEvaluacionparametroList) {
                ssfEvaluacionparametroListSsfEvaluacionparametro.setIdParametro(null);
                ssfEvaluacionparametroListSsfEvaluacionparametro = em.merge(ssfEvaluacionparametroListSsfEvaluacionparametro);
            }
            em.remove(ssfParametro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfParametro> findSsfParametroEntities() {
        return findSsfParametroEntities(true, -1, -1);
    }

    public List<SsfParametro> findSsfParametroEntities(int maxResults, int firstResult) {
        return findSsfParametroEntities(false, maxResults, firstResult);
    }

    private List<SsfParametro> findSsfParametroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfParametro.class));
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

    public SsfParametro findSsfParametro(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfParametro.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfParametroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfParametro> rt = cq.from(SsfParametro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
