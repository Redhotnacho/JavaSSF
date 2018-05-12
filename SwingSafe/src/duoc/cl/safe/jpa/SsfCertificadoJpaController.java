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
import duoc.cl.safe.entity.SsfAlumnocapaempresa;
import duoc.cl.safe.entity.SsfCertificado;
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
public class SsfCertificadoJpaController implements Serializable {

    public SsfCertificadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfCertificado ssfCertificado) throws PreexistingEntityException, Exception {
        if (ssfCertificado.getSsfAlumnocapaempresaList() == null) {
            ssfCertificado.setSsfAlumnocapaempresaList(new ArrayList<SsfAlumnocapaempresa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCapacitacion idCapacitacion = ssfCertificado.getIdCapacitacion();
            if (idCapacitacion != null) {
                idCapacitacion = em.getReference(idCapacitacion.getClass(), idCapacitacion.getId());
                ssfCertificado.setIdCapacitacion(idCapacitacion);
            }
            List<SsfAlumnocapaempresa> attachedSsfAlumnocapaempresaList = new ArrayList<SsfAlumnocapaempresa>();
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach : ssfCertificado.getSsfAlumnocapaempresaList()) {
                ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach = em.getReference(ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach.getClass(), ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach.getId());
                attachedSsfAlumnocapaempresaList.add(ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach);
            }
            ssfCertificado.setSsfAlumnocapaempresaList(attachedSsfAlumnocapaempresaList);
            em.persist(ssfCertificado);
            if (idCapacitacion != null) {
                idCapacitacion.getSsfCertificadoList().add(ssfCertificado);
                idCapacitacion = em.merge(idCapacitacion);
            }
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListSsfAlumnocapaempresa : ssfCertificado.getSsfAlumnocapaempresaList()) {
                SsfCertificado oldIdCertificadoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa = ssfAlumnocapaempresaListSsfAlumnocapaempresa.getIdCertificado();
                ssfAlumnocapaempresaListSsfAlumnocapaempresa.setIdCertificado(ssfCertificado);
                ssfAlumnocapaempresaListSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListSsfAlumnocapaempresa);
                if (oldIdCertificadoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa != null) {
                    oldIdCertificadoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresaListSsfAlumnocapaempresa);
                    oldIdCertificadoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa = em.merge(oldIdCertificadoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfCertificado(ssfCertificado.getId()) != null) {
                throw new PreexistingEntityException("SsfCertificado " + ssfCertificado + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfCertificado ssfCertificado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCertificado persistentSsfCertificado = em.find(SsfCertificado.class, ssfCertificado.getId());
            SsfCapacitacion idCapacitacionOld = persistentSsfCertificado.getIdCapacitacion();
            SsfCapacitacion idCapacitacionNew = ssfCertificado.getIdCapacitacion();
            List<SsfAlumnocapaempresa> ssfAlumnocapaempresaListOld = persistentSsfCertificado.getSsfAlumnocapaempresaList();
            List<SsfAlumnocapaempresa> ssfAlumnocapaempresaListNew = ssfCertificado.getSsfAlumnocapaempresaList();
            if (idCapacitacionNew != null) {
                idCapacitacionNew = em.getReference(idCapacitacionNew.getClass(), idCapacitacionNew.getId());
                ssfCertificado.setIdCapacitacion(idCapacitacionNew);
            }
            List<SsfAlumnocapaempresa> attachedSsfAlumnocapaempresaListNew = new ArrayList<SsfAlumnocapaempresa>();
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach : ssfAlumnocapaempresaListNew) {
                ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach = em.getReference(ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach.getClass(), ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach.getId());
                attachedSsfAlumnocapaempresaListNew.add(ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach);
            }
            ssfAlumnocapaempresaListNew = attachedSsfAlumnocapaempresaListNew;
            ssfCertificado.setSsfAlumnocapaempresaList(ssfAlumnocapaempresaListNew);
            ssfCertificado = em.merge(ssfCertificado);
            if (idCapacitacionOld != null && !idCapacitacionOld.equals(idCapacitacionNew)) {
                idCapacitacionOld.getSsfCertificadoList().remove(ssfCertificado);
                idCapacitacionOld = em.merge(idCapacitacionOld);
            }
            if (idCapacitacionNew != null && !idCapacitacionNew.equals(idCapacitacionOld)) {
                idCapacitacionNew.getSsfCertificadoList().add(ssfCertificado);
                idCapacitacionNew = em.merge(idCapacitacionNew);
            }
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListOldSsfAlumnocapaempresa : ssfAlumnocapaempresaListOld) {
                if (!ssfAlumnocapaempresaListNew.contains(ssfAlumnocapaempresaListOldSsfAlumnocapaempresa)) {
                    ssfAlumnocapaempresaListOldSsfAlumnocapaempresa.setIdCertificado(null);
                    ssfAlumnocapaempresaListOldSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListOldSsfAlumnocapaempresa);
                }
            }
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListNewSsfAlumnocapaempresa : ssfAlumnocapaempresaListNew) {
                if (!ssfAlumnocapaempresaListOld.contains(ssfAlumnocapaempresaListNewSsfAlumnocapaempresa)) {
                    SsfCertificado oldIdCertificadoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa = ssfAlumnocapaempresaListNewSsfAlumnocapaempresa.getIdCertificado();
                    ssfAlumnocapaempresaListNewSsfAlumnocapaempresa.setIdCertificado(ssfCertificado);
                    ssfAlumnocapaempresaListNewSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListNewSsfAlumnocapaempresa);
                    if (oldIdCertificadoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa != null && !oldIdCertificadoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa.equals(ssfCertificado)) {
                        oldIdCertificadoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresaListNewSsfAlumnocapaempresa);
                        oldIdCertificadoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa = em.merge(oldIdCertificadoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfCertificado.getId();
                if (findSsfCertificado(id) == null) {
                    throw new NonexistentEntityException("The ssfCertificado with id " + id + " no longer exists.");
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
            SsfCertificado ssfCertificado;
            try {
                ssfCertificado = em.getReference(SsfCertificado.class, id);
                ssfCertificado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfCertificado with id " + id + " no longer exists.", enfe);
            }
            SsfCapacitacion idCapacitacion = ssfCertificado.getIdCapacitacion();
            if (idCapacitacion != null) {
                idCapacitacion.getSsfCertificadoList().remove(ssfCertificado);
                idCapacitacion = em.merge(idCapacitacion);
            }
            List<SsfAlumnocapaempresa> ssfAlumnocapaempresaList = ssfCertificado.getSsfAlumnocapaempresaList();
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListSsfAlumnocapaempresa : ssfAlumnocapaempresaList) {
                ssfAlumnocapaempresaListSsfAlumnocapaempresa.setIdCertificado(null);
                ssfAlumnocapaempresaListSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListSsfAlumnocapaempresa);
            }
            em.remove(ssfCertificado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfCertificado> findSsfCertificadoEntities() {
        return findSsfCertificadoEntities(true, -1, -1);
    }

    public List<SsfCertificado> findSsfCertificadoEntities(int maxResults, int firstResult) {
        return findSsfCertificadoEntities(false, maxResults, firstResult);
    }

    private List<SsfCertificado> findSsfCertificadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfCertificado.class));
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

    public SsfCertificado findSsfCertificado(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfCertificado.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfCertificadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfCertificado> rt = cq.from(SsfCertificado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
