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
import duoc.cl.safe.entity.SsfEmpresa;
import duoc.cl.safe.entity.SsfEstadocapaempresa;
import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.entity.SsfAlumnocapaempresa;
import java.util.ArrayList;
import java.util.List;
import duoc.cl.safe.entity.SsfCapacitaciondia;
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
public class SsfCapacitacionempresaJpaController implements Serializable {

    public SsfCapacitacionempresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfCapacitacionempresa ssfCapacitacionempresa) throws PreexistingEntityException, Exception {
        if (ssfCapacitacionempresa.getSsfAlumnocapaempresaList() == null) {
            ssfCapacitacionempresa.setSsfAlumnocapaempresaList(new ArrayList<SsfAlumnocapaempresa>());
        }
        if (ssfCapacitacionempresa.getSsfCapacitaciondiaList() == null) {
            ssfCapacitacionempresa.setSsfCapacitaciondiaList(new ArrayList<SsfCapacitaciondia>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCapacitacion idCapacitacion = ssfCapacitacionempresa.getIdCapacitacion();
            if (idCapacitacion != null) {
                idCapacitacion = em.getReference(idCapacitacion.getClass(), idCapacitacion.getId());
                ssfCapacitacionempresa.setIdCapacitacion(idCapacitacion);
            }
            SsfEmpresa idEmpresa = ssfCapacitacionempresa.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getId());
                ssfCapacitacionempresa.setIdEmpresa(idEmpresa);
            }
            SsfEstadocapaempresa idEstadocapacitacion = ssfCapacitacionempresa.getIdEstadocapacitacion();
            if (idEstadocapacitacion != null) {
                idEstadocapacitacion = em.getReference(idEstadocapacitacion.getClass(), idEstadocapacitacion.getId());
                ssfCapacitacionempresa.setIdEstadocapacitacion(idEstadocapacitacion);
            }
            SsfUsuario idUsuario = ssfCapacitacionempresa.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getId());
                ssfCapacitacionempresa.setIdUsuario(idUsuario);
            }
            List<SsfAlumnocapaempresa> attachedSsfAlumnocapaempresaList = new ArrayList<SsfAlumnocapaempresa>();
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach : ssfCapacitacionempresa.getSsfAlumnocapaempresaList()) {
                ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach = em.getReference(ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach.getClass(), ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach.getId());
                attachedSsfAlumnocapaempresaList.add(ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach);
            }
            ssfCapacitacionempresa.setSsfAlumnocapaempresaList(attachedSsfAlumnocapaempresaList);
            List<SsfCapacitaciondia> attachedSsfCapacitaciondiaList = new ArrayList<SsfCapacitaciondia>();
            for (SsfCapacitaciondia ssfCapacitaciondiaListSsfCapacitaciondiaToAttach : ssfCapacitacionempresa.getSsfCapacitaciondiaList()) {
                ssfCapacitaciondiaListSsfCapacitaciondiaToAttach = em.getReference(ssfCapacitaciondiaListSsfCapacitaciondiaToAttach.getClass(), ssfCapacitaciondiaListSsfCapacitaciondiaToAttach.getId());
                attachedSsfCapacitaciondiaList.add(ssfCapacitaciondiaListSsfCapacitaciondiaToAttach);
            }
            ssfCapacitacionempresa.setSsfCapacitaciondiaList(attachedSsfCapacitaciondiaList);
            em.persist(ssfCapacitacionempresa);
            if (idCapacitacion != null) {
                idCapacitacion.getSsfCapacitacionempresaList().add(ssfCapacitacionempresa);
                idCapacitacion = em.merge(idCapacitacion);
            }
            if (idEmpresa != null) {
                idEmpresa.getSsfCapacitacionempresaList().add(ssfCapacitacionempresa);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idEstadocapacitacion != null) {
                idEstadocapacitacion.getSsfCapacitacionempresaList().add(ssfCapacitacionempresa);
                idEstadocapacitacion = em.merge(idEstadocapacitacion);
            }
            if (idUsuario != null) {
                idUsuario.getSsfCapacitacionempresaList().add(ssfCapacitacionempresa);
                idUsuario = em.merge(idUsuario);
            }
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListSsfAlumnocapaempresa : ssfCapacitacionempresa.getSsfAlumnocapaempresaList()) {
                SsfCapacitacionempresa oldIdCapaempresaOfSsfAlumnocapaempresaListSsfAlumnocapaempresa = ssfAlumnocapaempresaListSsfAlumnocapaempresa.getIdCapaempresa();
                ssfAlumnocapaempresaListSsfAlumnocapaempresa.setIdCapaempresa(ssfCapacitacionempresa);
                ssfAlumnocapaempresaListSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListSsfAlumnocapaempresa);
                if (oldIdCapaempresaOfSsfAlumnocapaempresaListSsfAlumnocapaempresa != null) {
                    oldIdCapaempresaOfSsfAlumnocapaempresaListSsfAlumnocapaempresa.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresaListSsfAlumnocapaempresa);
                    oldIdCapaempresaOfSsfAlumnocapaempresaListSsfAlumnocapaempresa = em.merge(oldIdCapaempresaOfSsfAlumnocapaempresaListSsfAlumnocapaempresa);
                }
            }
            for (SsfCapacitaciondia ssfCapacitaciondiaListSsfCapacitaciondia : ssfCapacitacionempresa.getSsfCapacitaciondiaList()) {
                SsfCapacitacionempresa oldIdCapaempresaOfSsfCapacitaciondiaListSsfCapacitaciondia = ssfCapacitaciondiaListSsfCapacitaciondia.getIdCapaempresa();
                ssfCapacitaciondiaListSsfCapacitaciondia.setIdCapaempresa(ssfCapacitacionempresa);
                ssfCapacitaciondiaListSsfCapacitaciondia = em.merge(ssfCapacitaciondiaListSsfCapacitaciondia);
                if (oldIdCapaempresaOfSsfCapacitaciondiaListSsfCapacitaciondia != null) {
                    oldIdCapaempresaOfSsfCapacitaciondiaListSsfCapacitaciondia.getSsfCapacitaciondiaList().remove(ssfCapacitaciondiaListSsfCapacitaciondia);
                    oldIdCapaempresaOfSsfCapacitaciondiaListSsfCapacitaciondia = em.merge(oldIdCapaempresaOfSsfCapacitaciondiaListSsfCapacitaciondia);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfCapacitacionempresa(ssfCapacitacionempresa.getId()) != null) {
                throw new PreexistingEntityException("SsfCapacitacionempresa " + ssfCapacitacionempresa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfCapacitacionempresa ssfCapacitacionempresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCapacitacionempresa persistentSsfCapacitacionempresa = em.find(SsfCapacitacionempresa.class, ssfCapacitacionempresa.getId());
            SsfCapacitacion idCapacitacionOld = persistentSsfCapacitacionempresa.getIdCapacitacion();
            SsfCapacitacion idCapacitacionNew = ssfCapacitacionempresa.getIdCapacitacion();
            SsfEmpresa idEmpresaOld = persistentSsfCapacitacionempresa.getIdEmpresa();
            SsfEmpresa idEmpresaNew = ssfCapacitacionempresa.getIdEmpresa();
            SsfEstadocapaempresa idEstadocapacitacionOld = persistentSsfCapacitacionempresa.getIdEstadocapacitacion();
            SsfEstadocapaempresa idEstadocapacitacionNew = ssfCapacitacionempresa.getIdEstadocapacitacion();
            SsfUsuario idUsuarioOld = persistentSsfCapacitacionempresa.getIdUsuario();
            SsfUsuario idUsuarioNew = ssfCapacitacionempresa.getIdUsuario();
            List<SsfAlumnocapaempresa> ssfAlumnocapaempresaListOld = persistentSsfCapacitacionempresa.getSsfAlumnocapaempresaList();
            List<SsfAlumnocapaempresa> ssfAlumnocapaempresaListNew = ssfCapacitacionempresa.getSsfAlumnocapaempresaList();
            List<SsfCapacitaciondia> ssfCapacitaciondiaListOld = persistentSsfCapacitacionempresa.getSsfCapacitaciondiaList();
            List<SsfCapacitaciondia> ssfCapacitaciondiaListNew = ssfCapacitacionempresa.getSsfCapacitaciondiaList();
            if (idCapacitacionNew != null) {
                idCapacitacionNew = em.getReference(idCapacitacionNew.getClass(), idCapacitacionNew.getId());
                ssfCapacitacionempresa.setIdCapacitacion(idCapacitacionNew);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getId());
                ssfCapacitacionempresa.setIdEmpresa(idEmpresaNew);
            }
            if (idEstadocapacitacionNew != null) {
                idEstadocapacitacionNew = em.getReference(idEstadocapacitacionNew.getClass(), idEstadocapacitacionNew.getId());
                ssfCapacitacionempresa.setIdEstadocapacitacion(idEstadocapacitacionNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getId());
                ssfCapacitacionempresa.setIdUsuario(idUsuarioNew);
            }
            List<SsfAlumnocapaempresa> attachedSsfAlumnocapaempresaListNew = new ArrayList<SsfAlumnocapaempresa>();
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach : ssfAlumnocapaempresaListNew) {
                ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach = em.getReference(ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach.getClass(), ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach.getId());
                attachedSsfAlumnocapaempresaListNew.add(ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach);
            }
            ssfAlumnocapaempresaListNew = attachedSsfAlumnocapaempresaListNew;
            ssfCapacitacionempresa.setSsfAlumnocapaempresaList(ssfAlumnocapaempresaListNew);
            List<SsfCapacitaciondia> attachedSsfCapacitaciondiaListNew = new ArrayList<SsfCapacitaciondia>();
            for (SsfCapacitaciondia ssfCapacitaciondiaListNewSsfCapacitaciondiaToAttach : ssfCapacitaciondiaListNew) {
                ssfCapacitaciondiaListNewSsfCapacitaciondiaToAttach = em.getReference(ssfCapacitaciondiaListNewSsfCapacitaciondiaToAttach.getClass(), ssfCapacitaciondiaListNewSsfCapacitaciondiaToAttach.getId());
                attachedSsfCapacitaciondiaListNew.add(ssfCapacitaciondiaListNewSsfCapacitaciondiaToAttach);
            }
            ssfCapacitaciondiaListNew = attachedSsfCapacitaciondiaListNew;
            ssfCapacitacionempresa.setSsfCapacitaciondiaList(ssfCapacitaciondiaListNew);
            ssfCapacitacionempresa = em.merge(ssfCapacitacionempresa);
            if (idCapacitacionOld != null && !idCapacitacionOld.equals(idCapacitacionNew)) {
                idCapacitacionOld.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresa);
                idCapacitacionOld = em.merge(idCapacitacionOld);
            }
            if (idCapacitacionNew != null && !idCapacitacionNew.equals(idCapacitacionOld)) {
                idCapacitacionNew.getSsfCapacitacionempresaList().add(ssfCapacitacionempresa);
                idCapacitacionNew = em.merge(idCapacitacionNew);
            }
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresa);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getSsfCapacitacionempresaList().add(ssfCapacitacionempresa);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idEstadocapacitacionOld != null && !idEstadocapacitacionOld.equals(idEstadocapacitacionNew)) {
                idEstadocapacitacionOld.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresa);
                idEstadocapacitacionOld = em.merge(idEstadocapacitacionOld);
            }
            if (idEstadocapacitacionNew != null && !idEstadocapacitacionNew.equals(idEstadocapacitacionOld)) {
                idEstadocapacitacionNew.getSsfCapacitacionempresaList().add(ssfCapacitacionempresa);
                idEstadocapacitacionNew = em.merge(idEstadocapacitacionNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresa);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getSsfCapacitacionempresaList().add(ssfCapacitacionempresa);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListOldSsfAlumnocapaempresa : ssfAlumnocapaempresaListOld) {
                if (!ssfAlumnocapaempresaListNew.contains(ssfAlumnocapaempresaListOldSsfAlumnocapaempresa)) {
                    ssfAlumnocapaempresaListOldSsfAlumnocapaempresa.setIdCapaempresa(null);
                    ssfAlumnocapaempresaListOldSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListOldSsfAlumnocapaempresa);
                }
            }
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListNewSsfAlumnocapaempresa : ssfAlumnocapaempresaListNew) {
                if (!ssfAlumnocapaempresaListOld.contains(ssfAlumnocapaempresaListNewSsfAlumnocapaempresa)) {
                    SsfCapacitacionempresa oldIdCapaempresaOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa = ssfAlumnocapaempresaListNewSsfAlumnocapaempresa.getIdCapaempresa();
                    ssfAlumnocapaempresaListNewSsfAlumnocapaempresa.setIdCapaempresa(ssfCapacitacionempresa);
                    ssfAlumnocapaempresaListNewSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListNewSsfAlumnocapaempresa);
                    if (oldIdCapaempresaOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa != null && !oldIdCapaempresaOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa.equals(ssfCapacitacionempresa)) {
                        oldIdCapaempresaOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresaListNewSsfAlumnocapaempresa);
                        oldIdCapaempresaOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa = em.merge(oldIdCapaempresaOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa);
                    }
                }
            }
            for (SsfCapacitaciondia ssfCapacitaciondiaListOldSsfCapacitaciondia : ssfCapacitaciondiaListOld) {
                if (!ssfCapacitaciondiaListNew.contains(ssfCapacitaciondiaListOldSsfCapacitaciondia)) {
                    ssfCapacitaciondiaListOldSsfCapacitaciondia.setIdCapaempresa(null);
                    ssfCapacitaciondiaListOldSsfCapacitaciondia = em.merge(ssfCapacitaciondiaListOldSsfCapacitaciondia);
                }
            }
            for (SsfCapacitaciondia ssfCapacitaciondiaListNewSsfCapacitaciondia : ssfCapacitaciondiaListNew) {
                if (!ssfCapacitaciondiaListOld.contains(ssfCapacitaciondiaListNewSsfCapacitaciondia)) {
                    SsfCapacitacionempresa oldIdCapaempresaOfSsfCapacitaciondiaListNewSsfCapacitaciondia = ssfCapacitaciondiaListNewSsfCapacitaciondia.getIdCapaempresa();
                    ssfCapacitaciondiaListNewSsfCapacitaciondia.setIdCapaempresa(ssfCapacitacionempresa);
                    ssfCapacitaciondiaListNewSsfCapacitaciondia = em.merge(ssfCapacitaciondiaListNewSsfCapacitaciondia);
                    if (oldIdCapaempresaOfSsfCapacitaciondiaListNewSsfCapacitaciondia != null && !oldIdCapaempresaOfSsfCapacitaciondiaListNewSsfCapacitaciondia.equals(ssfCapacitacionempresa)) {
                        oldIdCapaempresaOfSsfCapacitaciondiaListNewSsfCapacitaciondia.getSsfCapacitaciondiaList().remove(ssfCapacitaciondiaListNewSsfCapacitaciondia);
                        oldIdCapaempresaOfSsfCapacitaciondiaListNewSsfCapacitaciondia = em.merge(oldIdCapaempresaOfSsfCapacitaciondiaListNewSsfCapacitaciondia);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfCapacitacionempresa.getId();
                if (findSsfCapacitacionempresa(id) == null) {
                    throw new NonexistentEntityException("The ssfCapacitacionempresa with id " + id + " no longer exists.");
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
            SsfCapacitacionempresa ssfCapacitacionempresa;
            try {
                ssfCapacitacionempresa = em.getReference(SsfCapacitacionempresa.class, id);
                ssfCapacitacionempresa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfCapacitacionempresa with id " + id + " no longer exists.", enfe);
            }
            SsfCapacitacion idCapacitacion = ssfCapacitacionempresa.getIdCapacitacion();
            if (idCapacitacion != null) {
                idCapacitacion.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresa);
                idCapacitacion = em.merge(idCapacitacion);
            }
            SsfEmpresa idEmpresa = ssfCapacitacionempresa.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresa);
                idEmpresa = em.merge(idEmpresa);
            }
            SsfEstadocapaempresa idEstadocapacitacion = ssfCapacitacionempresa.getIdEstadocapacitacion();
            if (idEstadocapacitacion != null) {
                idEstadocapacitacion.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresa);
                idEstadocapacitacion = em.merge(idEstadocapacitacion);
            }
            SsfUsuario idUsuario = ssfCapacitacionempresa.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresa);
                idUsuario = em.merge(idUsuario);
            }
            List<SsfAlumnocapaempresa> ssfAlumnocapaempresaList = ssfCapacitacionempresa.getSsfAlumnocapaempresaList();
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListSsfAlumnocapaempresa : ssfAlumnocapaempresaList) {
                ssfAlumnocapaempresaListSsfAlumnocapaempresa.setIdCapaempresa(null);
                ssfAlumnocapaempresaListSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListSsfAlumnocapaempresa);
            }
            List<SsfCapacitaciondia> ssfCapacitaciondiaList = ssfCapacitacionempresa.getSsfCapacitaciondiaList();
            for (SsfCapacitaciondia ssfCapacitaciondiaListSsfCapacitaciondia : ssfCapacitaciondiaList) {
                ssfCapacitaciondiaListSsfCapacitaciondia.setIdCapaempresa(null);
                ssfCapacitaciondiaListSsfCapacitaciondia = em.merge(ssfCapacitaciondiaListSsfCapacitaciondia);
            }
            em.remove(ssfCapacitacionempresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfCapacitacionempresa> findSsfCapacitacionempresaEntities() {
        return findSsfCapacitacionempresaEntities(true, -1, -1);
    }

    public List<SsfCapacitacionempresa> findSsfCapacitacionempresaEntities(int maxResults, int firstResult) {
        return findSsfCapacitacionempresaEntities(false, maxResults, firstResult);
    }

    private List<SsfCapacitacionempresa> findSsfCapacitacionempresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfCapacitacionempresa.class));
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

    public SsfCapacitacionempresa findSsfCapacitacionempresa(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfCapacitacionempresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfCapacitacionempresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfCapacitacionempresa> rt = cq.from(SsfCapacitacionempresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
