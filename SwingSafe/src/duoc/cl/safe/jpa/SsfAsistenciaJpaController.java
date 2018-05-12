/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.jpa;

import duoc.cl.safe.entity.SsfAsistencia;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import duoc.cl.safe.entity.SsfCapacitaciondia;
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
public class SsfAsistenciaJpaController implements Serializable {

    public SsfAsistenciaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfAsistencia ssfAsistencia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCapacitaciondia idCapacitaciondia = ssfAsistencia.getIdCapacitaciondia();
            if (idCapacitaciondia != null) {
                idCapacitaciondia = em.getReference(idCapacitaciondia.getClass(), idCapacitaciondia.getId());
                ssfAsistencia.setIdCapacitaciondia(idCapacitaciondia);
            }
            em.persist(ssfAsistencia);
            if (idCapacitaciondia != null) {
                idCapacitaciondia.getSsfAsistenciaList().add(ssfAsistencia);
                idCapacitaciondia = em.merge(idCapacitaciondia);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfAsistencia(ssfAsistencia.getId()) != null) {
                throw new PreexistingEntityException("SsfAsistencia " + ssfAsistencia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfAsistencia ssfAsistencia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfAsistencia persistentSsfAsistencia = em.find(SsfAsistencia.class, ssfAsistencia.getId());
            SsfCapacitaciondia idCapacitaciondiaOld = persistentSsfAsistencia.getIdCapacitaciondia();
            SsfCapacitaciondia idCapacitaciondiaNew = ssfAsistencia.getIdCapacitaciondia();
            if (idCapacitaciondiaNew != null) {
                idCapacitaciondiaNew = em.getReference(idCapacitaciondiaNew.getClass(), idCapacitaciondiaNew.getId());
                ssfAsistencia.setIdCapacitaciondia(idCapacitaciondiaNew);
            }
            ssfAsistencia = em.merge(ssfAsistencia);
            if (idCapacitaciondiaOld != null && !idCapacitaciondiaOld.equals(idCapacitaciondiaNew)) {
                idCapacitaciondiaOld.getSsfAsistenciaList().remove(ssfAsistencia);
                idCapacitaciondiaOld = em.merge(idCapacitaciondiaOld);
            }
            if (idCapacitaciondiaNew != null && !idCapacitaciondiaNew.equals(idCapacitaciondiaOld)) {
                idCapacitaciondiaNew.getSsfAsistenciaList().add(ssfAsistencia);
                idCapacitaciondiaNew = em.merge(idCapacitaciondiaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfAsistencia.getId();
                if (findSsfAsistencia(id) == null) {
                    throw new NonexistentEntityException("The ssfAsistencia with id " + id + " no longer exists.");
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
            SsfAsistencia ssfAsistencia;
            try {
                ssfAsistencia = em.getReference(SsfAsistencia.class, id);
                ssfAsistencia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfAsistencia with id " + id + " no longer exists.", enfe);
            }
            SsfCapacitaciondia idCapacitaciondia = ssfAsistencia.getIdCapacitaciondia();
            if (idCapacitaciondia != null) {
                idCapacitaciondia.getSsfAsistenciaList().remove(ssfAsistencia);
                idCapacitaciondia = em.merge(idCapacitaciondia);
            }
            em.remove(ssfAsistencia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfAsistencia> findSsfAsistenciaEntities() {
        return findSsfAsistenciaEntities(true, -1, -1);
    }

    public List<SsfAsistencia> findSsfAsistenciaEntities(int maxResults, int firstResult) {
        return findSsfAsistenciaEntities(false, maxResults, firstResult);
    }

    private List<SsfAsistencia> findSsfAsistenciaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfAsistencia.class));
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

    public SsfAsistencia findSsfAsistencia(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfAsistencia.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfAsistenciaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfAsistencia> rt = cq.from(SsfAsistencia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
