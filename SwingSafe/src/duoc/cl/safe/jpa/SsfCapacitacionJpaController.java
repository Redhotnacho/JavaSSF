/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.jpa;

import duoc.cl.safe.entity.SsfCapacitacion;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import duoc.cl.safe.entity.SsfCapacitaciontipo;
import duoc.cl.safe.entity.SsfCertificado;
import java.util.ArrayList;
import java.util.List;
import duoc.cl.safe.entity.SsfCapacitacionempresa;
import duoc.cl.safe.jpa.exceptions.NonexistentEntityException;
import duoc.cl.safe.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nacho
 */
public class SsfCapacitacionJpaController implements Serializable {

    public SsfCapacitacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfCapacitacion ssfCapacitacion) throws PreexistingEntityException, Exception {
        if (ssfCapacitacion.getSsfCertificadoList() == null) {
            ssfCapacitacion.setSsfCertificadoList(new ArrayList<SsfCertificado>());
        }
        if (ssfCapacitacion.getSsfCapacitacionempresaList() == null) {
            ssfCapacitacion.setSsfCapacitacionempresaList(new ArrayList<SsfCapacitacionempresa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCapacitaciontipo idCapacitaciontipo = ssfCapacitacion.getIdCapacitaciontipo();
            if (idCapacitaciontipo != null) {
                idCapacitaciontipo = em.getReference(idCapacitaciontipo.getClass(), idCapacitaciontipo.getId());
                ssfCapacitacion.setIdCapacitaciontipo(idCapacitaciontipo);
            }
            List<SsfCertificado> attachedSsfCertificadoList = new ArrayList<SsfCertificado>();
            for (SsfCertificado ssfCertificadoListSsfCertificadoToAttach : ssfCapacitacion.getSsfCertificadoList()) {
                ssfCertificadoListSsfCertificadoToAttach = em.getReference(ssfCertificadoListSsfCertificadoToAttach.getClass(), ssfCertificadoListSsfCertificadoToAttach.getId());
                attachedSsfCertificadoList.add(ssfCertificadoListSsfCertificadoToAttach);
            }
            ssfCapacitacion.setSsfCertificadoList(attachedSsfCertificadoList);
            List<SsfCapacitacionempresa> attachedSsfCapacitacionempresaList = new ArrayList<SsfCapacitacionempresa>();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresaToAttach : ssfCapacitacion.getSsfCapacitacionempresaList()) {
                ssfCapacitacionempresaListSsfCapacitacionempresaToAttach = em.getReference(ssfCapacitacionempresaListSsfCapacitacionempresaToAttach.getClass(), ssfCapacitacionempresaListSsfCapacitacionempresaToAttach.getId());
                attachedSsfCapacitacionempresaList.add(ssfCapacitacionempresaListSsfCapacitacionempresaToAttach);
            }
            ssfCapacitacion.setSsfCapacitacionempresaList(attachedSsfCapacitacionempresaList);
            em.persist(ssfCapacitacion);
            if (idCapacitaciontipo != null) {
                idCapacitaciontipo.getSsfCapacitacionList().add(ssfCapacitacion);
                idCapacitaciontipo = em.merge(idCapacitaciontipo);
            }
            for (SsfCertificado ssfCertificadoListSsfCertificado : ssfCapacitacion.getSsfCertificadoList()) {
                SsfCapacitacion oldIdCapacitacionOfSsfCertificadoListSsfCertificado = ssfCertificadoListSsfCertificado.getIdCapacitacion();
                ssfCertificadoListSsfCertificado.setIdCapacitacion(ssfCapacitacion);
                ssfCertificadoListSsfCertificado = em.merge(ssfCertificadoListSsfCertificado);
                if (oldIdCapacitacionOfSsfCertificadoListSsfCertificado != null) {
                    oldIdCapacitacionOfSsfCertificadoListSsfCertificado.getSsfCertificadoList().remove(ssfCertificadoListSsfCertificado);
                    oldIdCapacitacionOfSsfCertificadoListSsfCertificado = em.merge(oldIdCapacitacionOfSsfCertificadoListSsfCertificado);
                }
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresa : ssfCapacitacion.getSsfCapacitacionempresaList()) {
                SsfCapacitacion oldIdCapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa = ssfCapacitacionempresaListSsfCapacitacionempresa.getIdCapacitacion();
                ssfCapacitacionempresaListSsfCapacitacionempresa.setIdCapacitacion(ssfCapacitacion);
                ssfCapacitacionempresaListSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListSsfCapacitacionempresa);
                if (oldIdCapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa != null) {
                    oldIdCapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresaListSsfCapacitacionempresa);
                    oldIdCapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa = em.merge(oldIdCapacitacionOfSsfCapacitacionempresaListSsfCapacitacionempresa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfCapacitacion(ssfCapacitacion.getId()) != null) {
                throw new PreexistingEntityException("SsfCapacitacion " + ssfCapacitacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfCapacitacion ssfCapacitacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCapacitacion persistentSsfCapacitacion = em.find(SsfCapacitacion.class, ssfCapacitacion.getId());
            SsfCapacitaciontipo idCapacitaciontipoOld = persistentSsfCapacitacion.getIdCapacitaciontipo();
            SsfCapacitaciontipo idCapacitaciontipoNew = ssfCapacitacion.getIdCapacitaciontipo();
            List<SsfCertificado> ssfCertificadoListOld = persistentSsfCapacitacion.getSsfCertificadoList();
            List<SsfCertificado> ssfCertificadoListNew = ssfCapacitacion.getSsfCertificadoList();
            List<SsfCapacitacionempresa> ssfCapacitacionempresaListOld = persistentSsfCapacitacion.getSsfCapacitacionempresaList();
            List<SsfCapacitacionempresa> ssfCapacitacionempresaListNew = ssfCapacitacion.getSsfCapacitacionempresaList();
            if (idCapacitaciontipoNew != null) {
                idCapacitaciontipoNew = em.getReference(idCapacitaciontipoNew.getClass(), idCapacitaciontipoNew.getId());
                ssfCapacitacion.setIdCapacitaciontipo(idCapacitaciontipoNew);
            }
            List<SsfCertificado> attachedSsfCertificadoListNew = new ArrayList<SsfCertificado>();
            for (SsfCertificado ssfCertificadoListNewSsfCertificadoToAttach : ssfCertificadoListNew) {
                ssfCertificadoListNewSsfCertificadoToAttach = em.getReference(ssfCertificadoListNewSsfCertificadoToAttach.getClass(), ssfCertificadoListNewSsfCertificadoToAttach.getId());
                attachedSsfCertificadoListNew.add(ssfCertificadoListNewSsfCertificadoToAttach);
            }
            ssfCertificadoListNew = attachedSsfCertificadoListNew;
            ssfCapacitacion.setSsfCertificadoList(ssfCertificadoListNew);
            List<SsfCapacitacionempresa> attachedSsfCapacitacionempresaListNew = new ArrayList<SsfCapacitacionempresa>();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach : ssfCapacitacionempresaListNew) {
                ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach = em.getReference(ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach.getClass(), ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach.getId());
                attachedSsfCapacitacionempresaListNew.add(ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach);
            }
            ssfCapacitacionempresaListNew = attachedSsfCapacitacionempresaListNew;
            ssfCapacitacion.setSsfCapacitacionempresaList(ssfCapacitacionempresaListNew);
            ssfCapacitacion = em.merge(ssfCapacitacion);
            if (idCapacitaciontipoOld != null && !idCapacitaciontipoOld.equals(idCapacitaciontipoNew)) {
                idCapacitaciontipoOld.getSsfCapacitacionList().remove(ssfCapacitacion);
                idCapacitaciontipoOld = em.merge(idCapacitaciontipoOld);
            }
            if (idCapacitaciontipoNew != null && !idCapacitaciontipoNew.equals(idCapacitaciontipoOld)) {
                idCapacitaciontipoNew.getSsfCapacitacionList().add(ssfCapacitacion);
                idCapacitaciontipoNew = em.merge(idCapacitaciontipoNew);
            }
            for (SsfCertificado ssfCertificadoListOldSsfCertificado : ssfCertificadoListOld) {
                if (!ssfCertificadoListNew.contains(ssfCertificadoListOldSsfCertificado)) {
                    ssfCertificadoListOldSsfCertificado.setIdCapacitacion(null);
                    ssfCertificadoListOldSsfCertificado = em.merge(ssfCertificadoListOldSsfCertificado);
                }
            }
            for (SsfCertificado ssfCertificadoListNewSsfCertificado : ssfCertificadoListNew) {
                if (!ssfCertificadoListOld.contains(ssfCertificadoListNewSsfCertificado)) {
                    SsfCapacitacion oldIdCapacitacionOfSsfCertificadoListNewSsfCertificado = ssfCertificadoListNewSsfCertificado.getIdCapacitacion();
                    ssfCertificadoListNewSsfCertificado.setIdCapacitacion(ssfCapacitacion);
                    ssfCertificadoListNewSsfCertificado = em.merge(ssfCertificadoListNewSsfCertificado);
                    if (oldIdCapacitacionOfSsfCertificadoListNewSsfCertificado != null && !oldIdCapacitacionOfSsfCertificadoListNewSsfCertificado.equals(ssfCapacitacion)) {
                        oldIdCapacitacionOfSsfCertificadoListNewSsfCertificado.getSsfCertificadoList().remove(ssfCertificadoListNewSsfCertificado);
                        oldIdCapacitacionOfSsfCertificadoListNewSsfCertificado = em.merge(oldIdCapacitacionOfSsfCertificadoListNewSsfCertificado);
                    }
                }
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListOldSsfCapacitacionempresa : ssfCapacitacionempresaListOld) {
                if (!ssfCapacitacionempresaListNew.contains(ssfCapacitacionempresaListOldSsfCapacitacionempresa)) {
                    ssfCapacitacionempresaListOldSsfCapacitacionempresa.setIdCapacitacion(null);
                    ssfCapacitacionempresaListOldSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListOldSsfCapacitacionempresa);
                }
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListNewSsfCapacitacionempresa : ssfCapacitacionempresaListNew) {
                if (!ssfCapacitacionempresaListOld.contains(ssfCapacitacionempresaListNewSsfCapacitacionempresa)) {
                    SsfCapacitacion oldIdCapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa = ssfCapacitacionempresaListNewSsfCapacitacionempresa.getIdCapacitacion();
                    ssfCapacitacionempresaListNewSsfCapacitacionempresa.setIdCapacitacion(ssfCapacitacion);
                    ssfCapacitacionempresaListNewSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListNewSsfCapacitacionempresa);
                    if (oldIdCapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa != null && !oldIdCapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa.equals(ssfCapacitacion)) {
                        oldIdCapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresaListNewSsfCapacitacionempresa);
                        oldIdCapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa = em.merge(oldIdCapacitacionOfSsfCapacitacionempresaListNewSsfCapacitacionempresa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfCapacitacion.getId();
                if (findSsfCapacitacion(id) == null) {
                    throw new NonexistentEntityException("The ssfCapacitacion with id " + id + " no longer exists.");
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
            SsfCapacitacion ssfCapacitacion;
            try {
                ssfCapacitacion = em.getReference(SsfCapacitacion.class, id);
                ssfCapacitacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfCapacitacion with id " + id + " no longer exists.", enfe);
            }
            SsfCapacitaciontipo idCapacitaciontipo = ssfCapacitacion.getIdCapacitaciontipo();
            if (idCapacitaciontipo != null) {
                idCapacitaciontipo.getSsfCapacitacionList().remove(ssfCapacitacion);
                idCapacitaciontipo = em.merge(idCapacitaciontipo);
            }
            List<SsfCertificado> ssfCertificadoList = ssfCapacitacion.getSsfCertificadoList();
            for (SsfCertificado ssfCertificadoListSsfCertificado : ssfCertificadoList) {
                ssfCertificadoListSsfCertificado.setIdCapacitacion(null);
                ssfCertificadoListSsfCertificado = em.merge(ssfCertificadoListSsfCertificado);
            }
            List<SsfCapacitacionempresa> ssfCapacitacionempresaList = ssfCapacitacion.getSsfCapacitacionempresaList();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresa : ssfCapacitacionempresaList) {
                ssfCapacitacionempresaListSsfCapacitacionempresa.setIdCapacitacion(null);
                ssfCapacitacionempresaListSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListSsfCapacitacionempresa);
            }
            em.remove(ssfCapacitacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfCapacitacion> findSsfCapacitacionEntities() {
        return findSsfCapacitacionEntities(true, -1, -1);
    }

    public List<SsfCapacitacion> findSsfCapacitacionEntities(int maxResults, int firstResult) {
        return findSsfCapacitacionEntities(false, maxResults, firstResult);
    }

    private List<SsfCapacitacion> findSsfCapacitacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfCapacitacion.class));
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

    public SsfCapacitacion findSsfCapacitacion(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfCapacitacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfCapacitacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfCapacitacion> rt = cq.from(SsfCapacitacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
