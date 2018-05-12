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
import duoc.cl.safe.entity.SsfMedico;
import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.entity.SsfFichamedicaatencion;
import java.util.ArrayList;
import java.util.List;
import duoc.cl.safe.entity.SsfAdjunto;
import duoc.cl.safe.entity.SsfAtencionmedica;
import duoc.cl.safe.jpa.exceptions.NonexistentEntityException;
import duoc.cl.safe.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nacho
 */
public class SsfAtencionmedicaJpaController implements Serializable {

    public SsfAtencionmedicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfAtencionmedica ssfAtencionmedica) throws PreexistingEntityException, Exception {
        if (ssfAtencionmedica.getSsfFichamedicaatencionList() == null) {
            ssfAtencionmedica.setSsfFichamedicaatencionList(new ArrayList<SsfFichamedicaatencion>());
        }
        if (ssfAtencionmedica.getSsfAdjuntoList() == null) {
            ssfAtencionmedica.setSsfAdjuntoList(new ArrayList<SsfAdjunto>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfMedico idMedico = ssfAtencionmedica.getIdMedico();
            if (idMedico != null) {
                idMedico = em.getReference(idMedico.getClass(), idMedico.getId());
                ssfAtencionmedica.setIdMedico(idMedico);
            }
            SsfUsuario idUsuario = ssfAtencionmedica.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getId());
                ssfAtencionmedica.setIdUsuario(idUsuario);
            }
            List<SsfFichamedicaatencion> attachedSsfFichamedicaatencionList = new ArrayList<SsfFichamedicaatencion>();
            for (SsfFichamedicaatencion ssfFichamedicaatencionListSsfFichamedicaatencionToAttach : ssfAtencionmedica.getSsfFichamedicaatencionList()) {
                ssfFichamedicaatencionListSsfFichamedicaatencionToAttach = em.getReference(ssfFichamedicaatencionListSsfFichamedicaatencionToAttach.getClass(), ssfFichamedicaatencionListSsfFichamedicaatencionToAttach.getId());
                attachedSsfFichamedicaatencionList.add(ssfFichamedicaatencionListSsfFichamedicaatencionToAttach);
            }
            ssfAtencionmedica.setSsfFichamedicaatencionList(attachedSsfFichamedicaatencionList);
            List<SsfAdjunto> attachedSsfAdjuntoList = new ArrayList<SsfAdjunto>();
            for (SsfAdjunto ssfAdjuntoListSsfAdjuntoToAttach : ssfAtencionmedica.getSsfAdjuntoList()) {
                ssfAdjuntoListSsfAdjuntoToAttach = em.getReference(ssfAdjuntoListSsfAdjuntoToAttach.getClass(), ssfAdjuntoListSsfAdjuntoToAttach.getId());
                attachedSsfAdjuntoList.add(ssfAdjuntoListSsfAdjuntoToAttach);
            }
            ssfAtencionmedica.setSsfAdjuntoList(attachedSsfAdjuntoList);
            em.persist(ssfAtencionmedica);
            if (idMedico != null) {
                idMedico.getSsfAtencionmedicaList().add(ssfAtencionmedica);
                idMedico = em.merge(idMedico);
            }
            if (idUsuario != null) {
                idUsuario.getSsfAtencionmedicaList().add(ssfAtencionmedica);
                idUsuario = em.merge(idUsuario);
            }
            for (SsfFichamedicaatencion ssfFichamedicaatencionListSsfFichamedicaatencion : ssfAtencionmedica.getSsfFichamedicaatencionList()) {
                SsfAtencionmedica oldIdAtencionmedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion = ssfFichamedicaatencionListSsfFichamedicaatencion.getIdAtencionmedica();
                ssfFichamedicaatencionListSsfFichamedicaatencion.setIdAtencionmedica(ssfAtencionmedica);
                ssfFichamedicaatencionListSsfFichamedicaatencion = em.merge(ssfFichamedicaatencionListSsfFichamedicaatencion);
                if (oldIdAtencionmedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion != null) {
                    oldIdAtencionmedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion.getSsfFichamedicaatencionList().remove(ssfFichamedicaatencionListSsfFichamedicaatencion);
                    oldIdAtencionmedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion = em.merge(oldIdAtencionmedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion);
                }
            }
            for (SsfAdjunto ssfAdjuntoListSsfAdjunto : ssfAtencionmedica.getSsfAdjuntoList()) {
                SsfAtencionmedica oldIdAtencionmedicaOfSsfAdjuntoListSsfAdjunto = ssfAdjuntoListSsfAdjunto.getIdAtencionmedica();
                ssfAdjuntoListSsfAdjunto.setIdAtencionmedica(ssfAtencionmedica);
                ssfAdjuntoListSsfAdjunto = em.merge(ssfAdjuntoListSsfAdjunto);
                if (oldIdAtencionmedicaOfSsfAdjuntoListSsfAdjunto != null) {
                    oldIdAtencionmedicaOfSsfAdjuntoListSsfAdjunto.getSsfAdjuntoList().remove(ssfAdjuntoListSsfAdjunto);
                    oldIdAtencionmedicaOfSsfAdjuntoListSsfAdjunto = em.merge(oldIdAtencionmedicaOfSsfAdjuntoListSsfAdjunto);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfAtencionmedica(ssfAtencionmedica.getId()) != null) {
                throw new PreexistingEntityException("SsfAtencionmedica " + ssfAtencionmedica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfAtencionmedica ssfAtencionmedica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfAtencionmedica persistentSsfAtencionmedica = em.find(SsfAtencionmedica.class, ssfAtencionmedica.getId());
            SsfMedico idMedicoOld = persistentSsfAtencionmedica.getIdMedico();
            SsfMedico idMedicoNew = ssfAtencionmedica.getIdMedico();
            SsfUsuario idUsuarioOld = persistentSsfAtencionmedica.getIdUsuario();
            SsfUsuario idUsuarioNew = ssfAtencionmedica.getIdUsuario();
            List<SsfFichamedicaatencion> ssfFichamedicaatencionListOld = persistentSsfAtencionmedica.getSsfFichamedicaatencionList();
            List<SsfFichamedicaatencion> ssfFichamedicaatencionListNew = ssfAtencionmedica.getSsfFichamedicaatencionList();
            List<SsfAdjunto> ssfAdjuntoListOld = persistentSsfAtencionmedica.getSsfAdjuntoList();
            List<SsfAdjunto> ssfAdjuntoListNew = ssfAtencionmedica.getSsfAdjuntoList();
            if (idMedicoNew != null) {
                idMedicoNew = em.getReference(idMedicoNew.getClass(), idMedicoNew.getId());
                ssfAtencionmedica.setIdMedico(idMedicoNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getId());
                ssfAtencionmedica.setIdUsuario(idUsuarioNew);
            }
            List<SsfFichamedicaatencion> attachedSsfFichamedicaatencionListNew = new ArrayList<SsfFichamedicaatencion>();
            for (SsfFichamedicaatencion ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach : ssfFichamedicaatencionListNew) {
                ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach = em.getReference(ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach.getClass(), ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach.getId());
                attachedSsfFichamedicaatencionListNew.add(ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach);
            }
            ssfFichamedicaatencionListNew = attachedSsfFichamedicaatencionListNew;
            ssfAtencionmedica.setSsfFichamedicaatencionList(ssfFichamedicaatencionListNew);
            List<SsfAdjunto> attachedSsfAdjuntoListNew = new ArrayList<SsfAdjunto>();
            for (SsfAdjunto ssfAdjuntoListNewSsfAdjuntoToAttach : ssfAdjuntoListNew) {
                ssfAdjuntoListNewSsfAdjuntoToAttach = em.getReference(ssfAdjuntoListNewSsfAdjuntoToAttach.getClass(), ssfAdjuntoListNewSsfAdjuntoToAttach.getId());
                attachedSsfAdjuntoListNew.add(ssfAdjuntoListNewSsfAdjuntoToAttach);
            }
            ssfAdjuntoListNew = attachedSsfAdjuntoListNew;
            ssfAtencionmedica.setSsfAdjuntoList(ssfAdjuntoListNew);
            ssfAtencionmedica = em.merge(ssfAtencionmedica);
            if (idMedicoOld != null && !idMedicoOld.equals(idMedicoNew)) {
                idMedicoOld.getSsfAtencionmedicaList().remove(ssfAtencionmedica);
                idMedicoOld = em.merge(idMedicoOld);
            }
            if (idMedicoNew != null && !idMedicoNew.equals(idMedicoOld)) {
                idMedicoNew.getSsfAtencionmedicaList().add(ssfAtencionmedica);
                idMedicoNew = em.merge(idMedicoNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getSsfAtencionmedicaList().remove(ssfAtencionmedica);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getSsfAtencionmedicaList().add(ssfAtencionmedica);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (SsfFichamedicaatencion ssfFichamedicaatencionListOldSsfFichamedicaatencion : ssfFichamedicaatencionListOld) {
                if (!ssfFichamedicaatencionListNew.contains(ssfFichamedicaatencionListOldSsfFichamedicaatencion)) {
                    ssfFichamedicaatencionListOldSsfFichamedicaatencion.setIdAtencionmedica(null);
                    ssfFichamedicaatencionListOldSsfFichamedicaatencion = em.merge(ssfFichamedicaatencionListOldSsfFichamedicaatencion);
                }
            }
            for (SsfFichamedicaatencion ssfFichamedicaatencionListNewSsfFichamedicaatencion : ssfFichamedicaatencionListNew) {
                if (!ssfFichamedicaatencionListOld.contains(ssfFichamedicaatencionListNewSsfFichamedicaatencion)) {
                    SsfAtencionmedica oldIdAtencionmedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion = ssfFichamedicaatencionListNewSsfFichamedicaatencion.getIdAtencionmedica();
                    ssfFichamedicaatencionListNewSsfFichamedicaatencion.setIdAtencionmedica(ssfAtencionmedica);
                    ssfFichamedicaatencionListNewSsfFichamedicaatencion = em.merge(ssfFichamedicaatencionListNewSsfFichamedicaatencion);
                    if (oldIdAtencionmedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion != null && !oldIdAtencionmedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion.equals(ssfAtencionmedica)) {
                        oldIdAtencionmedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion.getSsfFichamedicaatencionList().remove(ssfFichamedicaatencionListNewSsfFichamedicaatencion);
                        oldIdAtencionmedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion = em.merge(oldIdAtencionmedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion);
                    }
                }
            }
            for (SsfAdjunto ssfAdjuntoListOldSsfAdjunto : ssfAdjuntoListOld) {
                if (!ssfAdjuntoListNew.contains(ssfAdjuntoListOldSsfAdjunto)) {
                    ssfAdjuntoListOldSsfAdjunto.setIdAtencionmedica(null);
                    ssfAdjuntoListOldSsfAdjunto = em.merge(ssfAdjuntoListOldSsfAdjunto);
                }
            }
            for (SsfAdjunto ssfAdjuntoListNewSsfAdjunto : ssfAdjuntoListNew) {
                if (!ssfAdjuntoListOld.contains(ssfAdjuntoListNewSsfAdjunto)) {
                    SsfAtencionmedica oldIdAtencionmedicaOfSsfAdjuntoListNewSsfAdjunto = ssfAdjuntoListNewSsfAdjunto.getIdAtencionmedica();
                    ssfAdjuntoListNewSsfAdjunto.setIdAtencionmedica(ssfAtencionmedica);
                    ssfAdjuntoListNewSsfAdjunto = em.merge(ssfAdjuntoListNewSsfAdjunto);
                    if (oldIdAtencionmedicaOfSsfAdjuntoListNewSsfAdjunto != null && !oldIdAtencionmedicaOfSsfAdjuntoListNewSsfAdjunto.equals(ssfAtencionmedica)) {
                        oldIdAtencionmedicaOfSsfAdjuntoListNewSsfAdjunto.getSsfAdjuntoList().remove(ssfAdjuntoListNewSsfAdjunto);
                        oldIdAtencionmedicaOfSsfAdjuntoListNewSsfAdjunto = em.merge(oldIdAtencionmedicaOfSsfAdjuntoListNewSsfAdjunto);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfAtencionmedica.getId();
                if (findSsfAtencionmedica(id) == null) {
                    throw new NonexistentEntityException("The ssfAtencionmedica with id " + id + " no longer exists.");
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
            SsfAtencionmedica ssfAtencionmedica;
            try {
                ssfAtencionmedica = em.getReference(SsfAtencionmedica.class, id);
                ssfAtencionmedica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfAtencionmedica with id " + id + " no longer exists.", enfe);
            }
            SsfMedico idMedico = ssfAtencionmedica.getIdMedico();
            if (idMedico != null) {
                idMedico.getSsfAtencionmedicaList().remove(ssfAtencionmedica);
                idMedico = em.merge(idMedico);
            }
            SsfUsuario idUsuario = ssfAtencionmedica.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getSsfAtencionmedicaList().remove(ssfAtencionmedica);
                idUsuario = em.merge(idUsuario);
            }
            List<SsfFichamedicaatencion> ssfFichamedicaatencionList = ssfAtencionmedica.getSsfFichamedicaatencionList();
            for (SsfFichamedicaatencion ssfFichamedicaatencionListSsfFichamedicaatencion : ssfFichamedicaatencionList) {
                ssfFichamedicaatencionListSsfFichamedicaatencion.setIdAtencionmedica(null);
                ssfFichamedicaatencionListSsfFichamedicaatencion = em.merge(ssfFichamedicaatencionListSsfFichamedicaatencion);
            }
            List<SsfAdjunto> ssfAdjuntoList = ssfAtencionmedica.getSsfAdjuntoList();
            for (SsfAdjunto ssfAdjuntoListSsfAdjunto : ssfAdjuntoList) {
                ssfAdjuntoListSsfAdjunto.setIdAtencionmedica(null);
                ssfAdjuntoListSsfAdjunto = em.merge(ssfAdjuntoListSsfAdjunto);
            }
            em.remove(ssfAtencionmedica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfAtencionmedica> findSsfAtencionmedicaEntities() {
        return findSsfAtencionmedicaEntities(true, -1, -1);
    }

    public List<SsfAtencionmedica> findSsfAtencionmedicaEntities(int maxResults, int firstResult) {
        return findSsfAtencionmedicaEntities(false, maxResults, firstResult);
    }

    private List<SsfAtencionmedica> findSsfAtencionmedicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfAtencionmedica.class));
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

    public SsfAtencionmedica findSsfAtencionmedica(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfAtencionmedica.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfAtencionmedicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfAtencionmedica> rt = cq.from(SsfAtencionmedica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
