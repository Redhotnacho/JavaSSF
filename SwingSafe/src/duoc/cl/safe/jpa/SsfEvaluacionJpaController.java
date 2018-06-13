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
import duoc.cl.safe.entity.SsfEmpresa;
import duoc.cl.safe.entity.SsfEvaluacion;
import duoc.cl.safe.entity.SsfEvaluacionestado;
import duoc.cl.safe.entity.SsfEvaluaciontipo;
import duoc.cl.safe.entity.SsfEvaluacionparametro;
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
public class SsfEvaluacionJpaController implements Serializable {

    public SsfEvaluacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfEvaluacion ssfEvaluacion) throws PreexistingEntityException, Exception {
        if (ssfEvaluacion.getSsfEvaluacionparametroList() == null) {
            ssfEvaluacion.setSsfEvaluacionparametroList(new ArrayList<SsfEvaluacionparametro>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEmpresa idEmpresa = ssfEvaluacion.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getId());
                ssfEvaluacion.setIdEmpresa(idEmpresa);
            }
            SsfEvaluacionestado idEvaluacionestado = ssfEvaluacion.getIdEvaluacionestado();
            if (idEvaluacionestado != null) {
                idEvaluacionestado = em.getReference(idEvaluacionestado.getClass(), idEvaluacionestado.getId());
                ssfEvaluacion.setIdEvaluacionestado(idEvaluacionestado);
            }
            SsfEvaluaciontipo idEvaluaciontipo = ssfEvaluacion.getIdEvaluaciontipo();
            if (idEvaluaciontipo != null) {
                idEvaluaciontipo = em.getReference(idEvaluaciontipo.getClass(), idEvaluaciontipo.getId());
                ssfEvaluacion.setIdEvaluaciontipo(idEvaluaciontipo);
            }
            List<SsfEvaluacionparametro> attachedSsfEvaluacionparametroList = new ArrayList<SsfEvaluacionparametro>();
            for (SsfEvaluacionparametro ssfEvaluacionparametroListSsfEvaluacionparametroToAttach : ssfEvaluacion.getSsfEvaluacionparametroList()) {
                ssfEvaluacionparametroListSsfEvaluacionparametroToAttach = em.getReference(ssfEvaluacionparametroListSsfEvaluacionparametroToAttach.getClass(), ssfEvaluacionparametroListSsfEvaluacionparametroToAttach.getId());
                attachedSsfEvaluacionparametroList.add(ssfEvaluacionparametroListSsfEvaluacionparametroToAttach);
            }
            ssfEvaluacion.setSsfEvaluacionparametroList(attachedSsfEvaluacionparametroList);
            em.persist(ssfEvaluacion);
            if (idEmpresa != null) {
                idEmpresa.getSsfEvaluacionList().add(ssfEvaluacion);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idEvaluacionestado != null) {
                idEvaluacionestado.getSsfEvaluacionList().add(ssfEvaluacion);
                idEvaluacionestado = em.merge(idEvaluacionestado);
            }
            if (idEvaluaciontipo != null) {
                idEvaluaciontipo.getSsfEvaluacionList().add(ssfEvaluacion);
                idEvaluaciontipo = em.merge(idEvaluaciontipo);
            }
            for (SsfEvaluacionparametro ssfEvaluacionparametroListSsfEvaluacionparametro : ssfEvaluacion.getSsfEvaluacionparametroList()) {
                SsfEvaluacion oldIdEvaluacionOfSsfEvaluacionparametroListSsfEvaluacionparametro = ssfEvaluacionparametroListSsfEvaluacionparametro.getIdEvaluacion();
                ssfEvaluacionparametroListSsfEvaluacionparametro.setIdEvaluacion(ssfEvaluacion);
                ssfEvaluacionparametroListSsfEvaluacionparametro = em.merge(ssfEvaluacionparametroListSsfEvaluacionparametro);
                if (oldIdEvaluacionOfSsfEvaluacionparametroListSsfEvaluacionparametro != null) {
                    oldIdEvaluacionOfSsfEvaluacionparametroListSsfEvaluacionparametro.getSsfEvaluacionparametroList().remove(ssfEvaluacionparametroListSsfEvaluacionparametro);
                    oldIdEvaluacionOfSsfEvaluacionparametroListSsfEvaluacionparametro = em.merge(oldIdEvaluacionOfSsfEvaluacionparametroListSsfEvaluacionparametro);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfEvaluacion(ssfEvaluacion.getId()) != null) {
                throw new PreexistingEntityException("SsfEvaluacion " + ssfEvaluacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfEvaluacion ssfEvaluacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEvaluacion persistentSsfEvaluacion = em.find(SsfEvaluacion.class, ssfEvaluacion.getId());
            SsfEmpresa idEmpresaOld = persistentSsfEvaluacion.getIdEmpresa();
            SsfEmpresa idEmpresaNew = ssfEvaluacion.getIdEmpresa();
            SsfEvaluacionestado idEvaluacionestadoOld = persistentSsfEvaluacion.getIdEvaluacionestado();
            SsfEvaluacionestado idEvaluacionestadoNew = ssfEvaluacion.getIdEvaluacionestado();
            SsfEvaluaciontipo idEvaluaciontipoOld = persistentSsfEvaluacion.getIdEvaluaciontipo();
            SsfEvaluaciontipo idEvaluaciontipoNew = ssfEvaluacion.getIdEvaluaciontipo();
            List<SsfEvaluacionparametro> ssfEvaluacionparametroListOld = persistentSsfEvaluacion.getSsfEvaluacionparametroList();
            List<SsfEvaluacionparametro> ssfEvaluacionparametroListNew = ssfEvaluacion.getSsfEvaluacionparametroList();
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getId());
                ssfEvaluacion.setIdEmpresa(idEmpresaNew);
            }
            if (idEvaluacionestadoNew != null) {
                idEvaluacionestadoNew = em.getReference(idEvaluacionestadoNew.getClass(), idEvaluacionestadoNew.getId());
                ssfEvaluacion.setIdEvaluacionestado(idEvaluacionestadoNew);
            }
            if (idEvaluaciontipoNew != null) {
                idEvaluaciontipoNew = em.getReference(idEvaluaciontipoNew.getClass(), idEvaluaciontipoNew.getId());
                ssfEvaluacion.setIdEvaluaciontipo(idEvaluaciontipoNew);
            }
            List<SsfEvaluacionparametro> attachedSsfEvaluacionparametroListNew = new ArrayList<SsfEvaluacionparametro>();
            for (SsfEvaluacionparametro ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach : ssfEvaluacionparametroListNew) {
                ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach = em.getReference(ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach.getClass(), ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach.getId());
                attachedSsfEvaluacionparametroListNew.add(ssfEvaluacionparametroListNewSsfEvaluacionparametroToAttach);
            }
            ssfEvaluacionparametroListNew = attachedSsfEvaluacionparametroListNew;
            ssfEvaluacion.setSsfEvaluacionparametroList(ssfEvaluacionparametroListNew);
            ssfEvaluacion = em.merge(ssfEvaluacion);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getSsfEvaluacionList().remove(ssfEvaluacion);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getSsfEvaluacionList().add(ssfEvaluacion);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idEvaluacionestadoOld != null && !idEvaluacionestadoOld.equals(idEvaluacionestadoNew)) {
                idEvaluacionestadoOld.getSsfEvaluacionList().remove(ssfEvaluacion);
                idEvaluacionestadoOld = em.merge(idEvaluacionestadoOld);
            }
            if (idEvaluacionestadoNew != null && !idEvaluacionestadoNew.equals(idEvaluacionestadoOld)) {
                idEvaluacionestadoNew.getSsfEvaluacionList().add(ssfEvaluacion);
                idEvaluacionestadoNew = em.merge(idEvaluacionestadoNew);
            }
            if (idEvaluaciontipoOld != null && !idEvaluaciontipoOld.equals(idEvaluaciontipoNew)) {
                idEvaluaciontipoOld.getSsfEvaluacionList().remove(ssfEvaluacion);
                idEvaluaciontipoOld = em.merge(idEvaluaciontipoOld);
            }
            if (idEvaluaciontipoNew != null && !idEvaluaciontipoNew.equals(idEvaluaciontipoOld)) {
                idEvaluaciontipoNew.getSsfEvaluacionList().add(ssfEvaluacion);
                idEvaluaciontipoNew = em.merge(idEvaluaciontipoNew);
            }
            for (SsfEvaluacionparametro ssfEvaluacionparametroListOldSsfEvaluacionparametro : ssfEvaluacionparametroListOld) {
                if (!ssfEvaluacionparametroListNew.contains(ssfEvaluacionparametroListOldSsfEvaluacionparametro)) {
                    ssfEvaluacionparametroListOldSsfEvaluacionparametro.setIdEvaluacion(null);
                    ssfEvaluacionparametroListOldSsfEvaluacionparametro = em.merge(ssfEvaluacionparametroListOldSsfEvaluacionparametro);
                }
            }
            for (SsfEvaluacionparametro ssfEvaluacionparametroListNewSsfEvaluacionparametro : ssfEvaluacionparametroListNew) {
                if (!ssfEvaluacionparametroListOld.contains(ssfEvaluacionparametroListNewSsfEvaluacionparametro)) {
                    SsfEvaluacion oldIdEvaluacionOfSsfEvaluacionparametroListNewSsfEvaluacionparametro = ssfEvaluacionparametroListNewSsfEvaluacionparametro.getIdEvaluacion();
                    ssfEvaluacionparametroListNewSsfEvaluacionparametro.setIdEvaluacion(ssfEvaluacion);
                    ssfEvaluacionparametroListNewSsfEvaluacionparametro = em.merge(ssfEvaluacionparametroListNewSsfEvaluacionparametro);
                    if (oldIdEvaluacionOfSsfEvaluacionparametroListNewSsfEvaluacionparametro != null && !oldIdEvaluacionOfSsfEvaluacionparametroListNewSsfEvaluacionparametro.equals(ssfEvaluacion)) {
                        oldIdEvaluacionOfSsfEvaluacionparametroListNewSsfEvaluacionparametro.getSsfEvaluacionparametroList().remove(ssfEvaluacionparametroListNewSsfEvaluacionparametro);
                        oldIdEvaluacionOfSsfEvaluacionparametroListNewSsfEvaluacionparametro = em.merge(oldIdEvaluacionOfSsfEvaluacionparametroListNewSsfEvaluacionparametro);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfEvaluacion.getId();
                if (findSsfEvaluacion(id) == null) {
                    throw new NonexistentEntityException("The ssfEvaluacion with id " + id + " no longer exists.");
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
            SsfEvaluacion ssfEvaluacion;
            try {
                ssfEvaluacion = em.getReference(SsfEvaluacion.class, id);
                ssfEvaluacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfEvaluacion with id " + id + " no longer exists.", enfe);
            }
            SsfEmpresa idEmpresa = ssfEvaluacion.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getSsfEvaluacionList().remove(ssfEvaluacion);
                idEmpresa = em.merge(idEmpresa);
            }
            SsfEvaluacionestado idEvaluacionestado = ssfEvaluacion.getIdEvaluacionestado();
            if (idEvaluacionestado != null) {
                idEvaluacionestado.getSsfEvaluacionList().remove(ssfEvaluacion);
                idEvaluacionestado = em.merge(idEvaluacionestado);
            }
            SsfEvaluaciontipo idEvaluaciontipo = ssfEvaluacion.getIdEvaluaciontipo();
            if (idEvaluaciontipo != null) {
                idEvaluaciontipo.getSsfEvaluacionList().remove(ssfEvaluacion);
                idEvaluaciontipo = em.merge(idEvaluaciontipo);
            }
            List<SsfEvaluacionparametro> ssfEvaluacionparametroList = ssfEvaluacion.getSsfEvaluacionparametroList();
            for (SsfEvaluacionparametro ssfEvaluacionparametroListSsfEvaluacionparametro : ssfEvaluacionparametroList) {
                ssfEvaluacionparametroListSsfEvaluacionparametro.setIdEvaluacion(null);
                ssfEvaluacionparametroListSsfEvaluacionparametro = em.merge(ssfEvaluacionparametroListSsfEvaluacionparametro);
            }
            em.remove(ssfEvaluacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfEvaluacion> findSsfEvaluacionEntities() {
        return findSsfEvaluacionEntities(true, -1, -1);
    }

    public List<SsfEvaluacion> findSsfEvaluacionEntities(int maxResults, int firstResult) {
        return findSsfEvaluacionEntities(false, maxResults, firstResult);
    }

    private List<SsfEvaluacion> findSsfEvaluacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfEvaluacion.class));
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

    public SsfEvaluacion findSsfEvaluacion(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfEvaluacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfEvaluacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfEvaluacion> rt = cq.from(SsfEvaluacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
