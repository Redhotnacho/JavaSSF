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
import duoc.cl.safe.entity.SsfCentromedico;
import duoc.cl.safe.entity.SsfMedicoespecialidad;
import duoc.cl.safe.entity.SsfAtencionmedica;
import duoc.cl.safe.entity.SsfMedico;
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
public class SsfMedicoJpaController implements Serializable {

    public SsfMedicoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfMedico ssfMedico) throws PreexistingEntityException, Exception {
        if (ssfMedico.getSsfAtencionmedicaList() == null) {
            ssfMedico.setSsfAtencionmedicaList(new ArrayList<SsfAtencionmedica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfCentromedico idCentromedico = ssfMedico.getIdCentromedico();
            if (idCentromedico != null) {
                idCentromedico = em.getReference(idCentromedico.getClass(), idCentromedico.getId());
                ssfMedico.setIdCentromedico(idCentromedico);
            }
            SsfMedicoespecialidad idEspecialidad = ssfMedico.getIdEspecialidad();
            if (idEspecialidad != null) {
                idEspecialidad = em.getReference(idEspecialidad.getClass(), idEspecialidad.getId());
                ssfMedico.setIdEspecialidad(idEspecialidad);
            }
            List<SsfAtencionmedica> attachedSsfAtencionmedicaList = new ArrayList<SsfAtencionmedica>();
            for (SsfAtencionmedica ssfAtencionmedicaListSsfAtencionmedicaToAttach : ssfMedico.getSsfAtencionmedicaList()) {
                ssfAtencionmedicaListSsfAtencionmedicaToAttach = em.getReference(ssfAtencionmedicaListSsfAtencionmedicaToAttach.getClass(), ssfAtencionmedicaListSsfAtencionmedicaToAttach.getId());
                attachedSsfAtencionmedicaList.add(ssfAtencionmedicaListSsfAtencionmedicaToAttach);
            }
            ssfMedico.setSsfAtencionmedicaList(attachedSsfAtencionmedicaList);
            em.persist(ssfMedico);
            if (idCentromedico != null) {
                idCentromedico.getSsfMedicoList().add(ssfMedico);
                idCentromedico = em.merge(idCentromedico);
            }
            if (idEspecialidad != null) {
                idEspecialidad.getSsfMedicoList().add(ssfMedico);
                idEspecialidad = em.merge(idEspecialidad);
            }
            for (SsfAtencionmedica ssfAtencionmedicaListSsfAtencionmedica : ssfMedico.getSsfAtencionmedicaList()) {
                SsfMedico oldIdMedicoOfSsfAtencionmedicaListSsfAtencionmedica = ssfAtencionmedicaListSsfAtencionmedica.getIdMedico();
                ssfAtencionmedicaListSsfAtencionmedica.setIdMedico(ssfMedico);
                ssfAtencionmedicaListSsfAtencionmedica = em.merge(ssfAtencionmedicaListSsfAtencionmedica);
                if (oldIdMedicoOfSsfAtencionmedicaListSsfAtencionmedica != null) {
                    oldIdMedicoOfSsfAtencionmedicaListSsfAtencionmedica.getSsfAtencionmedicaList().remove(ssfAtencionmedicaListSsfAtencionmedica);
                    oldIdMedicoOfSsfAtencionmedicaListSsfAtencionmedica = em.merge(oldIdMedicoOfSsfAtencionmedicaListSsfAtencionmedica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfMedico(ssfMedico.getId()) != null) {
                throw new PreexistingEntityException("SsfMedico " + ssfMedico + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfMedico ssfMedico) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfMedico persistentSsfMedico = em.find(SsfMedico.class, ssfMedico.getId());
            SsfCentromedico idCentromedicoOld = persistentSsfMedico.getIdCentromedico();
            SsfCentromedico idCentromedicoNew = ssfMedico.getIdCentromedico();
            SsfMedicoespecialidad idEspecialidadOld = persistentSsfMedico.getIdEspecialidad();
            SsfMedicoespecialidad idEspecialidadNew = ssfMedico.getIdEspecialidad();
            List<SsfAtencionmedica> ssfAtencionmedicaListOld = persistentSsfMedico.getSsfAtencionmedicaList();
            List<SsfAtencionmedica> ssfAtencionmedicaListNew = ssfMedico.getSsfAtencionmedicaList();
            if (idCentromedicoNew != null) {
                idCentromedicoNew = em.getReference(idCentromedicoNew.getClass(), idCentromedicoNew.getId());
                ssfMedico.setIdCentromedico(idCentromedicoNew);
            }
            if (idEspecialidadNew != null) {
                idEspecialidadNew = em.getReference(idEspecialidadNew.getClass(), idEspecialidadNew.getId());
                ssfMedico.setIdEspecialidad(idEspecialidadNew);
            }
            List<SsfAtencionmedica> attachedSsfAtencionmedicaListNew = new ArrayList<SsfAtencionmedica>();
            for (SsfAtencionmedica ssfAtencionmedicaListNewSsfAtencionmedicaToAttach : ssfAtencionmedicaListNew) {
                ssfAtencionmedicaListNewSsfAtencionmedicaToAttach = em.getReference(ssfAtencionmedicaListNewSsfAtencionmedicaToAttach.getClass(), ssfAtencionmedicaListNewSsfAtencionmedicaToAttach.getId());
                attachedSsfAtencionmedicaListNew.add(ssfAtencionmedicaListNewSsfAtencionmedicaToAttach);
            }
            ssfAtencionmedicaListNew = attachedSsfAtencionmedicaListNew;
            ssfMedico.setSsfAtencionmedicaList(ssfAtencionmedicaListNew);
            ssfMedico = em.merge(ssfMedico);
            if (idCentromedicoOld != null && !idCentromedicoOld.equals(idCentromedicoNew)) {
                idCentromedicoOld.getSsfMedicoList().remove(ssfMedico);
                idCentromedicoOld = em.merge(idCentromedicoOld);
            }
            if (idCentromedicoNew != null && !idCentromedicoNew.equals(idCentromedicoOld)) {
                idCentromedicoNew.getSsfMedicoList().add(ssfMedico);
                idCentromedicoNew = em.merge(idCentromedicoNew);
            }
            if (idEspecialidadOld != null && !idEspecialidadOld.equals(idEspecialidadNew)) {
                idEspecialidadOld.getSsfMedicoList().remove(ssfMedico);
                idEspecialidadOld = em.merge(idEspecialidadOld);
            }
            if (idEspecialidadNew != null && !idEspecialidadNew.equals(idEspecialidadOld)) {
                idEspecialidadNew.getSsfMedicoList().add(ssfMedico);
                idEspecialidadNew = em.merge(idEspecialidadNew);
            }
            for (SsfAtencionmedica ssfAtencionmedicaListOldSsfAtencionmedica : ssfAtencionmedicaListOld) {
                if (!ssfAtencionmedicaListNew.contains(ssfAtencionmedicaListOldSsfAtencionmedica)) {
                    ssfAtencionmedicaListOldSsfAtencionmedica.setIdMedico(null);
                    ssfAtencionmedicaListOldSsfAtencionmedica = em.merge(ssfAtencionmedicaListOldSsfAtencionmedica);
                }
            }
            for (SsfAtencionmedica ssfAtencionmedicaListNewSsfAtencionmedica : ssfAtencionmedicaListNew) {
                if (!ssfAtencionmedicaListOld.contains(ssfAtencionmedicaListNewSsfAtencionmedica)) {
                    SsfMedico oldIdMedicoOfSsfAtencionmedicaListNewSsfAtencionmedica = ssfAtencionmedicaListNewSsfAtencionmedica.getIdMedico();
                    ssfAtencionmedicaListNewSsfAtencionmedica.setIdMedico(ssfMedico);
                    ssfAtencionmedicaListNewSsfAtencionmedica = em.merge(ssfAtencionmedicaListNewSsfAtencionmedica);
                    if (oldIdMedicoOfSsfAtencionmedicaListNewSsfAtencionmedica != null && !oldIdMedicoOfSsfAtencionmedicaListNewSsfAtencionmedica.equals(ssfMedico)) {
                        oldIdMedicoOfSsfAtencionmedicaListNewSsfAtencionmedica.getSsfAtencionmedicaList().remove(ssfAtencionmedicaListNewSsfAtencionmedica);
                        oldIdMedicoOfSsfAtencionmedicaListNewSsfAtencionmedica = em.merge(oldIdMedicoOfSsfAtencionmedicaListNewSsfAtencionmedica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfMedico.getId();
                if (findSsfMedico(id) == null) {
                    throw new NonexistentEntityException("The ssfMedico with id " + id + " no longer exists.");
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
            SsfMedico ssfMedico;
            try {
                ssfMedico = em.getReference(SsfMedico.class, id);
                ssfMedico.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfMedico with id " + id + " no longer exists.", enfe);
            }
            SsfCentromedico idCentromedico = ssfMedico.getIdCentromedico();
            if (idCentromedico != null) {
                idCentromedico.getSsfMedicoList().remove(ssfMedico);
                idCentromedico = em.merge(idCentromedico);
            }
            SsfMedicoespecialidad idEspecialidad = ssfMedico.getIdEspecialidad();
            if (idEspecialidad != null) {
                idEspecialidad.getSsfMedicoList().remove(ssfMedico);
                idEspecialidad = em.merge(idEspecialidad);
            }
            List<SsfAtencionmedica> ssfAtencionmedicaList = ssfMedico.getSsfAtencionmedicaList();
            for (SsfAtencionmedica ssfAtencionmedicaListSsfAtencionmedica : ssfAtencionmedicaList) {
                ssfAtencionmedicaListSsfAtencionmedica.setIdMedico(null);
                ssfAtencionmedicaListSsfAtencionmedica = em.merge(ssfAtencionmedicaListSsfAtencionmedica);
            }
            em.remove(ssfMedico);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfMedico> findSsfMedicoEntities() {
        return findSsfMedicoEntities(true, -1, -1);
    }

    public List<SsfMedico> findSsfMedicoEntities(int maxResults, int firstResult) {
        return findSsfMedicoEntities(false, maxResults, firstResult);
    }

    private List<SsfMedico> findSsfMedicoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfMedico.class));
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

    public SsfMedico findSsfMedico(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfMedico.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfMedicoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfMedico> rt = cq.from(SsfMedico.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
