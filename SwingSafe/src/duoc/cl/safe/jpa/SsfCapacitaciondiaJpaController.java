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
import duoc.cl.safe.entity.SsfAsistencia;
import duoc.cl.safe.entity.SsfCapacitaciondia;
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
public class SsfCapacitaciondiaJpaController implements Serializable {

    public SsfCapacitaciondiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfCapacitaciondia ssfCapacitaciondia) throws PreexistingEntityException, Exception {
        if (ssfCapacitaciondia.getSsfAsistenciaList() == null) {
            ssfCapacitaciondia.setSsfAsistenciaList(new ArrayList<SsfAsistencia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCapacitacionempresa idCapaempresa = ssfCapacitaciondia.getIdCapaempresa();
            if (idCapaempresa != null) {
                idCapaempresa = em.getReference(idCapaempresa.getClass(), idCapaempresa.getId());
                ssfCapacitaciondia.setIdCapaempresa(idCapaempresa);
            }
            List<SsfAsistencia> attachedSsfAsistenciaList = new ArrayList<SsfAsistencia>();
            for (SsfAsistencia ssfAsistenciaListSsfAsistenciaToAttach : ssfCapacitaciondia.getSsfAsistenciaList()) {
                ssfAsistenciaListSsfAsistenciaToAttach = em.getReference(ssfAsistenciaListSsfAsistenciaToAttach.getClass(), ssfAsistenciaListSsfAsistenciaToAttach.getId());
                attachedSsfAsistenciaList.add(ssfAsistenciaListSsfAsistenciaToAttach);
            }
            ssfCapacitaciondia.setSsfAsistenciaList(attachedSsfAsistenciaList);
            em.persist(ssfCapacitaciondia);
            if (idCapaempresa != null) {
                idCapaempresa.getSsfCapacitaciondiaList().add(ssfCapacitaciondia);
                idCapaempresa = em.merge(idCapaempresa);
            }
            for (SsfAsistencia ssfAsistenciaListSsfAsistencia : ssfCapacitaciondia.getSsfAsistenciaList()) {
                SsfCapacitaciondia oldIdCapacitaciondiaOfSsfAsistenciaListSsfAsistencia = ssfAsistenciaListSsfAsistencia.getIdCapacitaciondia();
                ssfAsistenciaListSsfAsistencia.setIdCapacitaciondia(ssfCapacitaciondia);
                ssfAsistenciaListSsfAsistencia = em.merge(ssfAsistenciaListSsfAsistencia);
                if (oldIdCapacitaciondiaOfSsfAsistenciaListSsfAsistencia != null) {
                    oldIdCapacitaciondiaOfSsfAsistenciaListSsfAsistencia.getSsfAsistenciaList().remove(ssfAsistenciaListSsfAsistencia);
                    oldIdCapacitaciondiaOfSsfAsistenciaListSsfAsistencia = em.merge(oldIdCapacitaciondiaOfSsfAsistenciaListSsfAsistencia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfCapacitaciondia(ssfCapacitaciondia.getId()) != null) {
                throw new PreexistingEntityException("SsfCapacitaciondia " + ssfCapacitaciondia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfCapacitaciondia ssfCapacitaciondia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCapacitaciondia persistentSsfCapacitaciondia = em.find(SsfCapacitaciondia.class, ssfCapacitaciondia.getId());
            SsfCapacitacionempresa idCapaempresaOld = persistentSsfCapacitaciondia.getIdCapaempresa();
            SsfCapacitacionempresa idCapaempresaNew = ssfCapacitaciondia.getIdCapaempresa();
            List<SsfAsistencia> ssfAsistenciaListOld = persistentSsfCapacitaciondia.getSsfAsistenciaList();
            List<SsfAsistencia> ssfAsistenciaListNew = ssfCapacitaciondia.getSsfAsistenciaList();
            if (idCapaempresaNew != null) {
                idCapaempresaNew = em.getReference(idCapaempresaNew.getClass(), idCapaempresaNew.getId());
                ssfCapacitaciondia.setIdCapaempresa(idCapaempresaNew);
            }
            List<SsfAsistencia> attachedSsfAsistenciaListNew = new ArrayList<SsfAsistencia>();
            for (SsfAsistencia ssfAsistenciaListNewSsfAsistenciaToAttach : ssfAsistenciaListNew) {
                ssfAsistenciaListNewSsfAsistenciaToAttach = em.getReference(ssfAsistenciaListNewSsfAsistenciaToAttach.getClass(), ssfAsistenciaListNewSsfAsistenciaToAttach.getId());
                attachedSsfAsistenciaListNew.add(ssfAsistenciaListNewSsfAsistenciaToAttach);
            }
            ssfAsistenciaListNew = attachedSsfAsistenciaListNew;
            ssfCapacitaciondia.setSsfAsistenciaList(ssfAsistenciaListNew);
            ssfCapacitaciondia = em.merge(ssfCapacitaciondia);
            if (idCapaempresaOld != null && !idCapaempresaOld.equals(idCapaempresaNew)) {
                idCapaempresaOld.getSsfCapacitaciondiaList().remove(ssfCapacitaciondia);
                idCapaempresaOld = em.merge(idCapaempresaOld);
            }
            if (idCapaempresaNew != null && !idCapaempresaNew.equals(idCapaempresaOld)) {
                idCapaempresaNew.getSsfCapacitaciondiaList().add(ssfCapacitaciondia);
                idCapaempresaNew = em.merge(idCapaempresaNew);
            }
            for (SsfAsistencia ssfAsistenciaListOldSsfAsistencia : ssfAsistenciaListOld) {
                if (!ssfAsistenciaListNew.contains(ssfAsistenciaListOldSsfAsistencia)) {
                    ssfAsistenciaListOldSsfAsistencia.setIdCapacitaciondia(null);
                    ssfAsistenciaListOldSsfAsistencia = em.merge(ssfAsistenciaListOldSsfAsistencia);
                }
            }
            for (SsfAsistencia ssfAsistenciaListNewSsfAsistencia : ssfAsistenciaListNew) {
                if (!ssfAsistenciaListOld.contains(ssfAsistenciaListNewSsfAsistencia)) {
                    SsfCapacitaciondia oldIdCapacitaciondiaOfSsfAsistenciaListNewSsfAsistencia = ssfAsistenciaListNewSsfAsistencia.getIdCapacitaciondia();
                    ssfAsistenciaListNewSsfAsistencia.setIdCapacitaciondia(ssfCapacitaciondia);
                    ssfAsistenciaListNewSsfAsistencia = em.merge(ssfAsistenciaListNewSsfAsistencia);
                    if (oldIdCapacitaciondiaOfSsfAsistenciaListNewSsfAsistencia != null && !oldIdCapacitaciondiaOfSsfAsistenciaListNewSsfAsistencia.equals(ssfCapacitaciondia)) {
                        oldIdCapacitaciondiaOfSsfAsistenciaListNewSsfAsistencia.getSsfAsistenciaList().remove(ssfAsistenciaListNewSsfAsistencia);
                        oldIdCapacitaciondiaOfSsfAsistenciaListNewSsfAsistencia = em.merge(oldIdCapacitaciondiaOfSsfAsistenciaListNewSsfAsistencia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfCapacitaciondia.getId();
                if (findSsfCapacitaciondia(id) == null) {
                    throw new NonexistentEntityException("The ssfCapacitaciondia with id " + id + " no longer exists.");
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
            SsfCapacitaciondia ssfCapacitaciondia;
            try {
                ssfCapacitaciondia = em.getReference(SsfCapacitaciondia.class, id);
                ssfCapacitaciondia.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfCapacitaciondia with id " + id + " no longer exists.", enfe);
            }
            SsfCapacitacionempresa idCapaempresa = ssfCapacitaciondia.getIdCapaempresa();
            if (idCapaempresa != null) {
                idCapaempresa.getSsfCapacitaciondiaList().remove(ssfCapacitaciondia);
                idCapaempresa = em.merge(idCapaempresa);
            }
            List<SsfAsistencia> ssfAsistenciaList = ssfCapacitaciondia.getSsfAsistenciaList();
            for (SsfAsistencia ssfAsistenciaListSsfAsistencia : ssfAsistenciaList) {
                ssfAsistenciaListSsfAsistencia.setIdCapacitaciondia(null);
                ssfAsistenciaListSsfAsistencia = em.merge(ssfAsistenciaListSsfAsistencia);
            }
            em.remove(ssfCapacitaciondia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfCapacitaciondia> findSsfCapacitaciondiaEntities() {
        return findSsfCapacitaciondiaEntities(true, -1, -1);
    }

    public List<SsfCapacitaciondia> findSsfCapacitaciondiaEntities(int maxResults, int firstResult) {
        return findSsfCapacitaciondiaEntities(false, maxResults, firstResult);
    }

    private List<SsfCapacitaciondia> findSsfCapacitaciondiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfCapacitaciondia.class));
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

    public SsfCapacitaciondia findSsfCapacitaciondia(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfCapacitaciondia.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfCapacitaciondiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfCapacitaciondia> rt = cq.from(SsfCapacitaciondia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
