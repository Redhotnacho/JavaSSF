/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.jpa;

import duoc.cl.safe.entity.SsfAdjunto;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import duoc.cl.safe.entity.SsfAtencionmedica;
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
public class SsfAdjuntoJpaController implements Serializable {

    public SsfAdjuntoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfAdjunto ssfAdjunto) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfAtencionmedica idAtencionmedica = ssfAdjunto.getIdAtencionmedica();
            if (idAtencionmedica != null) {
                idAtencionmedica = em.getReference(idAtencionmedica.getClass(), idAtencionmedica.getId());
                ssfAdjunto.setIdAtencionmedica(idAtencionmedica);
            }
            em.persist(ssfAdjunto);
            if (idAtencionmedica != null) {
                idAtencionmedica.getSsfAdjuntoList().add(ssfAdjunto);
                idAtencionmedica = em.merge(idAtencionmedica);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfAdjunto(ssfAdjunto.getId()) != null) {
                throw new PreexistingEntityException("SsfAdjunto " + ssfAdjunto + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfAdjunto ssfAdjunto) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfAdjunto persistentSsfAdjunto = em.find(SsfAdjunto.class, ssfAdjunto.getId());
            SsfAtencionmedica idAtencionmedicaOld = persistentSsfAdjunto.getIdAtencionmedica();
            SsfAtencionmedica idAtencionmedicaNew = ssfAdjunto.getIdAtencionmedica();
            if (idAtencionmedicaNew != null) {
                idAtencionmedicaNew = em.getReference(idAtencionmedicaNew.getClass(), idAtencionmedicaNew.getId());
                ssfAdjunto.setIdAtencionmedica(idAtencionmedicaNew);
            }
            ssfAdjunto = em.merge(ssfAdjunto);
            if (idAtencionmedicaOld != null && !idAtencionmedicaOld.equals(idAtencionmedicaNew)) {
                idAtencionmedicaOld.getSsfAdjuntoList().remove(ssfAdjunto);
                idAtencionmedicaOld = em.merge(idAtencionmedicaOld);
            }
            if (idAtencionmedicaNew != null && !idAtencionmedicaNew.equals(idAtencionmedicaOld)) {
                idAtencionmedicaNew.getSsfAdjuntoList().add(ssfAdjunto);
                idAtencionmedicaNew = em.merge(idAtencionmedicaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfAdjunto.getId();
                if (findSsfAdjunto(id) == null) {
                    throw new NonexistentEntityException("The ssfAdjunto with id " + id + " no longer exists.");
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
            SsfAdjunto ssfAdjunto;
            try {
                ssfAdjunto = em.getReference(SsfAdjunto.class, id);
                ssfAdjunto.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfAdjunto with id " + id + " no longer exists.", enfe);
            }
            SsfAtencionmedica idAtencionmedica = ssfAdjunto.getIdAtencionmedica();
            if (idAtencionmedica != null) {
                idAtencionmedica.getSsfAdjuntoList().remove(ssfAdjunto);
                idAtencionmedica = em.merge(idAtencionmedica);
            }
            em.remove(ssfAdjunto);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfAdjunto> findSsfAdjuntoEntities() {
        return findSsfAdjuntoEntities(true, -1, -1);
    }

    public List<SsfAdjunto> findSsfAdjuntoEntities(int maxResults, int firstResult) {
        return findSsfAdjuntoEntities(false, maxResults, firstResult);
    }

    private List<SsfAdjunto> findSsfAdjuntoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfAdjunto.class));
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

    public SsfAdjunto findSsfAdjunto(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfAdjunto.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfAdjuntoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfAdjunto> rt = cq.from(SsfAdjunto.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
