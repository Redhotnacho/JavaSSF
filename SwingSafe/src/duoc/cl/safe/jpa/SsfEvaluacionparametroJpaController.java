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
import duoc.cl.safe.entity.SsfEvaluacionparametro;
import duoc.cl.safe.entity.SsfParametro;
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
public class SsfEvaluacionparametroJpaController implements Serializable {

    public SsfEvaluacionparametroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfEvaluacionparametro ssfEvaluacionparametro) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEvaluacion idEvaluacion = ssfEvaluacionparametro.getIdEvaluacion();
            if (idEvaluacion != null) {
                idEvaluacion = em.getReference(idEvaluacion.getClass(), idEvaluacion.getId());
                ssfEvaluacionparametro.setIdEvaluacion(idEvaluacion);
            }
            SsfParametro idParametro = ssfEvaluacionparametro.getIdParametro();
            if (idParametro != null) {
                idParametro = em.getReference(idParametro.getClass(), idParametro.getId());
                ssfEvaluacionparametro.setIdParametro(idParametro);
            }
            em.persist(ssfEvaluacionparametro);
            if (idEvaluacion != null) {
                idEvaluacion.getSsfEvaluacionparametroList().add(ssfEvaluacionparametro);
                idEvaluacion = em.merge(idEvaluacion);
            }
            if (idParametro != null) {
                idParametro.getSsfEvaluacionparametroList().add(ssfEvaluacionparametro);
                idParametro = em.merge(idParametro);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfEvaluacionparametro(ssfEvaluacionparametro.getId()) != null) {
                throw new PreexistingEntityException("SsfEvaluacionparametro " + ssfEvaluacionparametro + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfEvaluacionparametro ssfEvaluacionparametro) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEvaluacionparametro persistentSsfEvaluacionparametro = em.find(SsfEvaluacionparametro.class, ssfEvaluacionparametro.getId());
            SsfEvaluacion idEvaluacionOld = persistentSsfEvaluacionparametro.getIdEvaluacion();
            SsfEvaluacion idEvaluacionNew = ssfEvaluacionparametro.getIdEvaluacion();
            SsfParametro idParametroOld = persistentSsfEvaluacionparametro.getIdParametro();
            SsfParametro idParametroNew = ssfEvaluacionparametro.getIdParametro();
            if (idEvaluacionNew != null) {
                idEvaluacionNew = em.getReference(idEvaluacionNew.getClass(), idEvaluacionNew.getId());
                ssfEvaluacionparametro.setIdEvaluacion(idEvaluacionNew);
            }
            if (idParametroNew != null) {
                idParametroNew = em.getReference(idParametroNew.getClass(), idParametroNew.getId());
                ssfEvaluacionparametro.setIdParametro(idParametroNew);
            }
            ssfEvaluacionparametro = em.merge(ssfEvaluacionparametro);
            if (idEvaluacionOld != null && !idEvaluacionOld.equals(idEvaluacionNew)) {
                idEvaluacionOld.getSsfEvaluacionparametroList().remove(ssfEvaluacionparametro);
                idEvaluacionOld = em.merge(idEvaluacionOld);
            }
            if (idEvaluacionNew != null && !idEvaluacionNew.equals(idEvaluacionOld)) {
                idEvaluacionNew.getSsfEvaluacionparametroList().add(ssfEvaluacionparametro);
                idEvaluacionNew = em.merge(idEvaluacionNew);
            }
            if (idParametroOld != null && !idParametroOld.equals(idParametroNew)) {
                idParametroOld.getSsfEvaluacionparametroList().remove(ssfEvaluacionparametro);
                idParametroOld = em.merge(idParametroOld);
            }
            if (idParametroNew != null && !idParametroNew.equals(idParametroOld)) {
                idParametroNew.getSsfEvaluacionparametroList().add(ssfEvaluacionparametro);
                idParametroNew = em.merge(idParametroNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfEvaluacionparametro.getId();
                if (findSsfEvaluacionparametro(id) == null) {
                    throw new NonexistentEntityException("The ssfEvaluacionparametro with id " + id + " no longer exists.");
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
            SsfEvaluacionparametro ssfEvaluacionparametro;
            try {
                ssfEvaluacionparametro = em.getReference(SsfEvaluacionparametro.class, id);
                ssfEvaluacionparametro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfEvaluacionparametro with id " + id + " no longer exists.", enfe);
            }
            SsfEvaluacion idEvaluacion = ssfEvaluacionparametro.getIdEvaluacion();
            if (idEvaluacion != null) {
                idEvaluacion.getSsfEvaluacionparametroList().remove(ssfEvaluacionparametro);
                idEvaluacion = em.merge(idEvaluacion);
            }
            SsfParametro idParametro = ssfEvaluacionparametro.getIdParametro();
            if (idParametro != null) {
                idParametro.getSsfEvaluacionparametroList().remove(ssfEvaluacionparametro);
                idParametro = em.merge(idParametro);
            }
            em.remove(ssfEvaluacionparametro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfEvaluacionparametro> findSsfEvaluacionparametroEntities() {
        return findSsfEvaluacionparametroEntities(true, -1, -1);
    }

    public List<SsfEvaluacionparametro> findSsfEvaluacionparametroEntities(int maxResults, int firstResult) {
        return findSsfEvaluacionparametroEntities(false, maxResults, firstResult);
    }

    private List<SsfEvaluacionparametro> findSsfEvaluacionparametroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfEvaluacionparametro.class));
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

    public SsfEvaluacionparametro findSsfEvaluacionparametro(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfEvaluacionparametro.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfEvaluacionparametroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfEvaluacionparametro> rt = cq.from(SsfEvaluacionparametro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
