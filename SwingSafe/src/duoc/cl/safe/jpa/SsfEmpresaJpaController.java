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
import java.util.ArrayList;
import java.util.List;
import duoc.cl.safe.entity.SsfCapacitacionempresa;
import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.entity.SsfAlumno;
import duoc.cl.safe.entity.SsfEmpresa;
import duoc.cl.safe.jpa.exceptions.NonexistentEntityException;
import duoc.cl.safe.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nacho
 */
public class SsfEmpresaJpaController implements Serializable {

    public SsfEmpresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfEmpresa ssfEmpresa) throws PreexistingEntityException, Exception {
        if (ssfEmpresa.getSsfEvaluacionList() == null) {
            ssfEmpresa.setSsfEvaluacionList(new ArrayList<SsfEvaluacion>());
        }
        if (ssfEmpresa.getSsfCapacitacionempresaList() == null) {
            ssfEmpresa.setSsfCapacitacionempresaList(new ArrayList<SsfCapacitacionempresa>());
        }
        if (ssfEmpresa.getSsfUsuarioList() == null) {
            ssfEmpresa.setSsfUsuarioList(new ArrayList<SsfUsuario>());
        }
        if (ssfEmpresa.getSsfAlumnoList() == null) {
            ssfEmpresa.setSsfAlumnoList(new ArrayList<SsfAlumno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfEvaluacion> attachedSsfEvaluacionList = new ArrayList<SsfEvaluacion>();
            for (SsfEvaluacion ssfEvaluacionListSsfEvaluacionToAttach : ssfEmpresa.getSsfEvaluacionList()) {
                ssfEvaluacionListSsfEvaluacionToAttach = em.getReference(ssfEvaluacionListSsfEvaluacionToAttach.getClass(), ssfEvaluacionListSsfEvaluacionToAttach.getId());
                attachedSsfEvaluacionList.add(ssfEvaluacionListSsfEvaluacionToAttach);
            }
            ssfEmpresa.setSsfEvaluacionList(attachedSsfEvaluacionList);
            List<SsfCapacitacionempresa> attachedSsfCapacitacionempresaList = new ArrayList<SsfCapacitacionempresa>();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresaToAttach : ssfEmpresa.getSsfCapacitacionempresaList()) {
                ssfCapacitacionempresaListSsfCapacitacionempresaToAttach = em.getReference(ssfCapacitacionempresaListSsfCapacitacionempresaToAttach.getClass(), ssfCapacitacionempresaListSsfCapacitacionempresaToAttach.getId());
                attachedSsfCapacitacionempresaList.add(ssfCapacitacionempresaListSsfCapacitacionempresaToAttach);
            }
            ssfEmpresa.setSsfCapacitacionempresaList(attachedSsfCapacitacionempresaList);
            List<SsfUsuario> attachedSsfUsuarioList = new ArrayList<SsfUsuario>();
            for (SsfUsuario ssfUsuarioListSsfUsuarioToAttach : ssfEmpresa.getSsfUsuarioList()) {
                ssfUsuarioListSsfUsuarioToAttach = em.getReference(ssfUsuarioListSsfUsuarioToAttach.getClass(), ssfUsuarioListSsfUsuarioToAttach.getId());
                attachedSsfUsuarioList.add(ssfUsuarioListSsfUsuarioToAttach);
            }
            ssfEmpresa.setSsfUsuarioList(attachedSsfUsuarioList);
            List<SsfAlumno> attachedSsfAlumnoList = new ArrayList<SsfAlumno>();
            for (SsfAlumno ssfAlumnoListSsfAlumnoToAttach : ssfEmpresa.getSsfAlumnoList()) {
                ssfAlumnoListSsfAlumnoToAttach = em.getReference(ssfAlumnoListSsfAlumnoToAttach.getClass(), ssfAlumnoListSsfAlumnoToAttach.getId());
                attachedSsfAlumnoList.add(ssfAlumnoListSsfAlumnoToAttach);
            }
            ssfEmpresa.setSsfAlumnoList(attachedSsfAlumnoList);
            em.persist(ssfEmpresa);
            for (SsfEvaluacion ssfEvaluacionListSsfEvaluacion : ssfEmpresa.getSsfEvaluacionList()) {
                SsfEmpresa oldIdEmpresaOfSsfEvaluacionListSsfEvaluacion = ssfEvaluacionListSsfEvaluacion.getIdEmpresa();
                ssfEvaluacionListSsfEvaluacion.setIdEmpresa(ssfEmpresa);
                ssfEvaluacionListSsfEvaluacion = em.merge(ssfEvaluacionListSsfEvaluacion);
                if (oldIdEmpresaOfSsfEvaluacionListSsfEvaluacion != null) {
                    oldIdEmpresaOfSsfEvaluacionListSsfEvaluacion.getSsfEvaluacionList().remove(ssfEvaluacionListSsfEvaluacion);
                    oldIdEmpresaOfSsfEvaluacionListSsfEvaluacion = em.merge(oldIdEmpresaOfSsfEvaluacionListSsfEvaluacion);
                }
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresa : ssfEmpresa.getSsfCapacitacionempresaList()) {
                SsfEmpresa oldIdEmpresaOfSsfCapacitacionempresaListSsfCapacitacionempresa = ssfCapacitacionempresaListSsfCapacitacionempresa.getIdEmpresa();
                ssfCapacitacionempresaListSsfCapacitacionempresa.setIdEmpresa(ssfEmpresa);
                ssfCapacitacionempresaListSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListSsfCapacitacionempresa);
                if (oldIdEmpresaOfSsfCapacitacionempresaListSsfCapacitacionempresa != null) {
                    oldIdEmpresaOfSsfCapacitacionempresaListSsfCapacitacionempresa.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresaListSsfCapacitacionempresa);
                    oldIdEmpresaOfSsfCapacitacionempresaListSsfCapacitacionempresa = em.merge(oldIdEmpresaOfSsfCapacitacionempresaListSsfCapacitacionempresa);
                }
            }
            for (SsfUsuario ssfUsuarioListSsfUsuario : ssfEmpresa.getSsfUsuarioList()) {
                SsfEmpresa oldIdEmpresaOfSsfUsuarioListSsfUsuario = ssfUsuarioListSsfUsuario.getIdEmpresa();
                ssfUsuarioListSsfUsuario.setIdEmpresa(ssfEmpresa);
                ssfUsuarioListSsfUsuario = em.merge(ssfUsuarioListSsfUsuario);
                if (oldIdEmpresaOfSsfUsuarioListSsfUsuario != null) {
                    oldIdEmpresaOfSsfUsuarioListSsfUsuario.getSsfUsuarioList().remove(ssfUsuarioListSsfUsuario);
                    oldIdEmpresaOfSsfUsuarioListSsfUsuario = em.merge(oldIdEmpresaOfSsfUsuarioListSsfUsuario);
                }
            }
            for (SsfAlumno ssfAlumnoListSsfAlumno : ssfEmpresa.getSsfAlumnoList()) {
                SsfEmpresa oldIdEmpresaOfSsfAlumnoListSsfAlumno = ssfAlumnoListSsfAlumno.getIdEmpresa();
                ssfAlumnoListSsfAlumno.setIdEmpresa(ssfEmpresa);
                ssfAlumnoListSsfAlumno = em.merge(ssfAlumnoListSsfAlumno);
                if (oldIdEmpresaOfSsfAlumnoListSsfAlumno != null) {
                    oldIdEmpresaOfSsfAlumnoListSsfAlumno.getSsfAlumnoList().remove(ssfAlumnoListSsfAlumno);
                    oldIdEmpresaOfSsfAlumnoListSsfAlumno = em.merge(oldIdEmpresaOfSsfAlumnoListSsfAlumno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfEmpresa(ssfEmpresa.getId()) != null) {
                throw new PreexistingEntityException("SsfEmpresa " + ssfEmpresa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfEmpresa ssfEmpresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEmpresa persistentSsfEmpresa = em.find(SsfEmpresa.class, ssfEmpresa.getId());
            List<SsfEvaluacion> ssfEvaluacionListOld = persistentSsfEmpresa.getSsfEvaluacionList();
            List<SsfEvaluacion> ssfEvaluacionListNew = ssfEmpresa.getSsfEvaluacionList();
            List<SsfCapacitacionempresa> ssfCapacitacionempresaListOld = persistentSsfEmpresa.getSsfCapacitacionempresaList();
            List<SsfCapacitacionempresa> ssfCapacitacionempresaListNew = ssfEmpresa.getSsfCapacitacionempresaList();
            List<SsfUsuario> ssfUsuarioListOld = persistentSsfEmpresa.getSsfUsuarioList();
            List<SsfUsuario> ssfUsuarioListNew = ssfEmpresa.getSsfUsuarioList();
            List<SsfAlumno> ssfAlumnoListOld = persistentSsfEmpresa.getSsfAlumnoList();
            List<SsfAlumno> ssfAlumnoListNew = ssfEmpresa.getSsfAlumnoList();
            List<SsfEvaluacion> attachedSsfEvaluacionListNew = new ArrayList<SsfEvaluacion>();
            for (SsfEvaluacion ssfEvaluacionListNewSsfEvaluacionToAttach : ssfEvaluacionListNew) {
                ssfEvaluacionListNewSsfEvaluacionToAttach = em.getReference(ssfEvaluacionListNewSsfEvaluacionToAttach.getClass(), ssfEvaluacionListNewSsfEvaluacionToAttach.getId());
                attachedSsfEvaluacionListNew.add(ssfEvaluacionListNewSsfEvaluacionToAttach);
            }
            ssfEvaluacionListNew = attachedSsfEvaluacionListNew;
            ssfEmpresa.setSsfEvaluacionList(ssfEvaluacionListNew);
            List<SsfCapacitacionempresa> attachedSsfCapacitacionempresaListNew = new ArrayList<SsfCapacitacionempresa>();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach : ssfCapacitacionempresaListNew) {
                ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach = em.getReference(ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach.getClass(), ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach.getId());
                attachedSsfCapacitacionempresaListNew.add(ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach);
            }
            ssfCapacitacionempresaListNew = attachedSsfCapacitacionempresaListNew;
            ssfEmpresa.setSsfCapacitacionempresaList(ssfCapacitacionempresaListNew);
            List<SsfUsuario> attachedSsfUsuarioListNew = new ArrayList<SsfUsuario>();
            for (SsfUsuario ssfUsuarioListNewSsfUsuarioToAttach : ssfUsuarioListNew) {
                ssfUsuarioListNewSsfUsuarioToAttach = em.getReference(ssfUsuarioListNewSsfUsuarioToAttach.getClass(), ssfUsuarioListNewSsfUsuarioToAttach.getId());
                attachedSsfUsuarioListNew.add(ssfUsuarioListNewSsfUsuarioToAttach);
            }
            ssfUsuarioListNew = attachedSsfUsuarioListNew;
            ssfEmpresa.setSsfUsuarioList(ssfUsuarioListNew);
            List<SsfAlumno> attachedSsfAlumnoListNew = new ArrayList<SsfAlumno>();
            for (SsfAlumno ssfAlumnoListNewSsfAlumnoToAttach : ssfAlumnoListNew) {
                ssfAlumnoListNewSsfAlumnoToAttach = em.getReference(ssfAlumnoListNewSsfAlumnoToAttach.getClass(), ssfAlumnoListNewSsfAlumnoToAttach.getId());
                attachedSsfAlumnoListNew.add(ssfAlumnoListNewSsfAlumnoToAttach);
            }
            ssfAlumnoListNew = attachedSsfAlumnoListNew;
            ssfEmpresa.setSsfAlumnoList(ssfAlumnoListNew);
            ssfEmpresa = em.merge(ssfEmpresa);
            for (SsfEvaluacion ssfEvaluacionListOldSsfEvaluacion : ssfEvaluacionListOld) {
                if (!ssfEvaluacionListNew.contains(ssfEvaluacionListOldSsfEvaluacion)) {
                    ssfEvaluacionListOldSsfEvaluacion.setIdEmpresa(null);
                    ssfEvaluacionListOldSsfEvaluacion = em.merge(ssfEvaluacionListOldSsfEvaluacion);
                }
            }
            for (SsfEvaluacion ssfEvaluacionListNewSsfEvaluacion : ssfEvaluacionListNew) {
                if (!ssfEvaluacionListOld.contains(ssfEvaluacionListNewSsfEvaluacion)) {
                    SsfEmpresa oldIdEmpresaOfSsfEvaluacionListNewSsfEvaluacion = ssfEvaluacionListNewSsfEvaluacion.getIdEmpresa();
                    ssfEvaluacionListNewSsfEvaluacion.setIdEmpresa(ssfEmpresa);
                    ssfEvaluacionListNewSsfEvaluacion = em.merge(ssfEvaluacionListNewSsfEvaluacion);
                    if (oldIdEmpresaOfSsfEvaluacionListNewSsfEvaluacion != null && !oldIdEmpresaOfSsfEvaluacionListNewSsfEvaluacion.equals(ssfEmpresa)) {
                        oldIdEmpresaOfSsfEvaluacionListNewSsfEvaluacion.getSsfEvaluacionList().remove(ssfEvaluacionListNewSsfEvaluacion);
                        oldIdEmpresaOfSsfEvaluacionListNewSsfEvaluacion = em.merge(oldIdEmpresaOfSsfEvaluacionListNewSsfEvaluacion);
                    }
                }
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListOldSsfCapacitacionempresa : ssfCapacitacionempresaListOld) {
                if (!ssfCapacitacionempresaListNew.contains(ssfCapacitacionempresaListOldSsfCapacitacionempresa)) {
                    ssfCapacitacionempresaListOldSsfCapacitacionempresa.setIdEmpresa(null);
                    ssfCapacitacionempresaListOldSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListOldSsfCapacitacionempresa);
                }
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListNewSsfCapacitacionempresa : ssfCapacitacionempresaListNew) {
                if (!ssfCapacitacionempresaListOld.contains(ssfCapacitacionempresaListNewSsfCapacitacionempresa)) {
                    SsfEmpresa oldIdEmpresaOfSsfCapacitacionempresaListNewSsfCapacitacionempresa = ssfCapacitacionempresaListNewSsfCapacitacionempresa.getIdEmpresa();
                    ssfCapacitacionempresaListNewSsfCapacitacionempresa.setIdEmpresa(ssfEmpresa);
                    ssfCapacitacionempresaListNewSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListNewSsfCapacitacionempresa);
                    if (oldIdEmpresaOfSsfCapacitacionempresaListNewSsfCapacitacionempresa != null && !oldIdEmpresaOfSsfCapacitacionempresaListNewSsfCapacitacionempresa.equals(ssfEmpresa)) {
                        oldIdEmpresaOfSsfCapacitacionempresaListNewSsfCapacitacionempresa.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresaListNewSsfCapacitacionempresa);
                        oldIdEmpresaOfSsfCapacitacionempresaListNewSsfCapacitacionempresa = em.merge(oldIdEmpresaOfSsfCapacitacionempresaListNewSsfCapacitacionempresa);
                    }
                }
            }
            for (SsfUsuario ssfUsuarioListOldSsfUsuario : ssfUsuarioListOld) {
                if (!ssfUsuarioListNew.contains(ssfUsuarioListOldSsfUsuario)) {
                    ssfUsuarioListOldSsfUsuario.setIdEmpresa(null);
                    ssfUsuarioListOldSsfUsuario = em.merge(ssfUsuarioListOldSsfUsuario);
                }
            }
            for (SsfUsuario ssfUsuarioListNewSsfUsuario : ssfUsuarioListNew) {
                if (!ssfUsuarioListOld.contains(ssfUsuarioListNewSsfUsuario)) {
                    SsfEmpresa oldIdEmpresaOfSsfUsuarioListNewSsfUsuario = ssfUsuarioListNewSsfUsuario.getIdEmpresa();
                    ssfUsuarioListNewSsfUsuario.setIdEmpresa(ssfEmpresa);
                    ssfUsuarioListNewSsfUsuario = em.merge(ssfUsuarioListNewSsfUsuario);
                    if (oldIdEmpresaOfSsfUsuarioListNewSsfUsuario != null && !oldIdEmpresaOfSsfUsuarioListNewSsfUsuario.equals(ssfEmpresa)) {
                        oldIdEmpresaOfSsfUsuarioListNewSsfUsuario.getSsfUsuarioList().remove(ssfUsuarioListNewSsfUsuario);
                        oldIdEmpresaOfSsfUsuarioListNewSsfUsuario = em.merge(oldIdEmpresaOfSsfUsuarioListNewSsfUsuario);
                    }
                }
            }
            for (SsfAlumno ssfAlumnoListOldSsfAlumno : ssfAlumnoListOld) {
                if (!ssfAlumnoListNew.contains(ssfAlumnoListOldSsfAlumno)) {
                    ssfAlumnoListOldSsfAlumno.setIdEmpresa(null);
                    ssfAlumnoListOldSsfAlumno = em.merge(ssfAlumnoListOldSsfAlumno);
                }
            }
            for (SsfAlumno ssfAlumnoListNewSsfAlumno : ssfAlumnoListNew) {
                if (!ssfAlumnoListOld.contains(ssfAlumnoListNewSsfAlumno)) {
                    SsfEmpresa oldIdEmpresaOfSsfAlumnoListNewSsfAlumno = ssfAlumnoListNewSsfAlumno.getIdEmpresa();
                    ssfAlumnoListNewSsfAlumno.setIdEmpresa(ssfEmpresa);
                    ssfAlumnoListNewSsfAlumno = em.merge(ssfAlumnoListNewSsfAlumno);
                    if (oldIdEmpresaOfSsfAlumnoListNewSsfAlumno != null && !oldIdEmpresaOfSsfAlumnoListNewSsfAlumno.equals(ssfEmpresa)) {
                        oldIdEmpresaOfSsfAlumnoListNewSsfAlumno.getSsfAlumnoList().remove(ssfAlumnoListNewSsfAlumno);
                        oldIdEmpresaOfSsfAlumnoListNewSsfAlumno = em.merge(oldIdEmpresaOfSsfAlumnoListNewSsfAlumno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfEmpresa.getId();
                if (findSsfEmpresa(id) == null) {
                    throw new NonexistentEntityException("The ssfEmpresa with id " + id + " no longer exists.");
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
            SsfEmpresa ssfEmpresa;
            try {
                ssfEmpresa = em.getReference(SsfEmpresa.class, id);
                ssfEmpresa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfEmpresa with id " + id + " no longer exists.", enfe);
            }
            List<SsfEvaluacion> ssfEvaluacionList = ssfEmpresa.getSsfEvaluacionList();
            for (SsfEvaluacion ssfEvaluacionListSsfEvaluacion : ssfEvaluacionList) {
                ssfEvaluacionListSsfEvaluacion.setIdEmpresa(null);
                ssfEvaluacionListSsfEvaluacion = em.merge(ssfEvaluacionListSsfEvaluacion);
            }
            List<SsfCapacitacionempresa> ssfCapacitacionempresaList = ssfEmpresa.getSsfCapacitacionempresaList();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresa : ssfCapacitacionempresaList) {
                ssfCapacitacionempresaListSsfCapacitacionempresa.setIdEmpresa(null);
                ssfCapacitacionempresaListSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListSsfCapacitacionempresa);
            }
            List<SsfUsuario> ssfUsuarioList = ssfEmpresa.getSsfUsuarioList();
            for (SsfUsuario ssfUsuarioListSsfUsuario : ssfUsuarioList) {
                ssfUsuarioListSsfUsuario.setIdEmpresa(null);
                ssfUsuarioListSsfUsuario = em.merge(ssfUsuarioListSsfUsuario);
            }
            List<SsfAlumno> ssfAlumnoList = ssfEmpresa.getSsfAlumnoList();
            for (SsfAlumno ssfAlumnoListSsfAlumno : ssfAlumnoList) {
                ssfAlumnoListSsfAlumno.setIdEmpresa(null);
                ssfAlumnoListSsfAlumno = em.merge(ssfAlumnoListSsfAlumno);
            }
            em.remove(ssfEmpresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfEmpresa> findSsfEmpresaEntities() {
        return findSsfEmpresaEntities(true, -1, -1);
    }

    public List<SsfEmpresa> findSsfEmpresaEntities(int maxResults, int firstResult) {
        return findSsfEmpresaEntities(false, maxResults, firstResult);
    }

    private List<SsfEmpresa> findSsfEmpresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfEmpresa.class));
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

    public SsfEmpresa findSsfEmpresa(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfEmpresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfEmpresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfEmpresa> rt = cq.from(SsfEmpresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
