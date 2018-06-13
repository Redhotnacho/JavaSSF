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
import duoc.cl.safe.entity.SsfMedicoespecialidad;
import duoc.cl.safe.jpa.exceptions.IllegalOrphanException;
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
public class SsfMedicoespecialidadJpaController implements Serializable {

    public SsfMedicoespecialidadJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfMedicoespecialidad ssfMedicoespecialidad) throws PreexistingEntityException, Exception {
        if (ssfMedicoespecialidad.getSsfMedicoList() == null) {
            ssfMedicoespecialidad.setSsfMedicoList(new ArrayList<SsfMedico>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfMedico> attachedSsfMedicoList = new ArrayList<SsfMedico>();
            for (SsfMedico ssfMedicoListSsfMedicoToAttach : ssfMedicoespecialidad.getSsfMedicoList()) {
                ssfMedicoListSsfMedicoToAttach = em.getReference(ssfMedicoListSsfMedicoToAttach.getClass(), ssfMedicoListSsfMedicoToAttach.getId());
                attachedSsfMedicoList.add(ssfMedicoListSsfMedicoToAttach);
            }
            ssfMedicoespecialidad.setSsfMedicoList(attachedSsfMedicoList);
            em.persist(ssfMedicoespecialidad);
            for (SsfMedico ssfMedicoListSsfMedico : ssfMedicoespecialidad.getSsfMedicoList()) {
                SsfMedicoespecialidad oldIdEspecialidadOfSsfMedicoListSsfMedico = ssfMedicoListSsfMedico.getIdEspecialidad();
                ssfMedicoListSsfMedico.setIdEspecialidad(ssfMedicoespecialidad);
                ssfMedicoListSsfMedico = em.merge(ssfMedicoListSsfMedico);
                if (oldIdEspecialidadOfSsfMedicoListSsfMedico != null) {
                    oldIdEspecialidadOfSsfMedicoListSsfMedico.getSsfMedicoList().remove(ssfMedicoListSsfMedico);
                    oldIdEspecialidadOfSsfMedicoListSsfMedico = em.merge(oldIdEspecialidadOfSsfMedicoListSsfMedico);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfMedicoespecialidad(ssfMedicoespecialidad.getId()) != null) {
                throw new PreexistingEntityException("SsfMedicoespecialidad " + ssfMedicoespecialidad + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfMedicoespecialidad ssfMedicoespecialidad) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfMedicoespecialidad persistentSsfMedicoespecialidad = em.find(SsfMedicoespecialidad.class, ssfMedicoespecialidad.getId());
            List<SsfMedico> ssfMedicoListOld = persistentSsfMedicoespecialidad.getSsfMedicoList();
            List<SsfMedico> ssfMedicoListNew = ssfMedicoespecialidad.getSsfMedicoList();
            List<String> illegalOrphanMessages = null;
            for (SsfMedico ssfMedicoListOldSsfMedico : ssfMedicoListOld) {
                if (!ssfMedicoListNew.contains(ssfMedicoListOldSsfMedico)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SsfMedico " + ssfMedicoListOldSsfMedico + " since its idEspecialidad field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SsfMedico> attachedSsfMedicoListNew = new ArrayList<SsfMedico>();
            for (SsfMedico ssfMedicoListNewSsfMedicoToAttach : ssfMedicoListNew) {
                ssfMedicoListNewSsfMedicoToAttach = em.getReference(ssfMedicoListNewSsfMedicoToAttach.getClass(), ssfMedicoListNewSsfMedicoToAttach.getId());
                attachedSsfMedicoListNew.add(ssfMedicoListNewSsfMedicoToAttach);
            }
            ssfMedicoListNew = attachedSsfMedicoListNew;
            ssfMedicoespecialidad.setSsfMedicoList(ssfMedicoListNew);
            ssfMedicoespecialidad = em.merge(ssfMedicoespecialidad);
            for (SsfMedico ssfMedicoListNewSsfMedico : ssfMedicoListNew) {
                if (!ssfMedicoListOld.contains(ssfMedicoListNewSsfMedico)) {
                    SsfMedicoespecialidad oldIdEspecialidadOfSsfMedicoListNewSsfMedico = ssfMedicoListNewSsfMedico.getIdEspecialidad();
                    ssfMedicoListNewSsfMedico.setIdEspecialidad(ssfMedicoespecialidad);
                    ssfMedicoListNewSsfMedico = em.merge(ssfMedicoListNewSsfMedico);
                    if (oldIdEspecialidadOfSsfMedicoListNewSsfMedico != null && !oldIdEspecialidadOfSsfMedicoListNewSsfMedico.equals(ssfMedicoespecialidad)) {
                        oldIdEspecialidadOfSsfMedicoListNewSsfMedico.getSsfMedicoList().remove(ssfMedicoListNewSsfMedico);
                        oldIdEspecialidadOfSsfMedicoListNewSsfMedico = em.merge(oldIdEspecialidadOfSsfMedicoListNewSsfMedico);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfMedicoespecialidad.getId();
                if (findSsfMedicoespecialidad(id) == null) {
                    throw new NonexistentEntityException("The ssfMedicoespecialidad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(BigDecimal id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfMedicoespecialidad ssfMedicoespecialidad;
            try {
                ssfMedicoespecialidad = em.getReference(SsfMedicoespecialidad.class, id);
                ssfMedicoespecialidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfMedicoespecialidad with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SsfMedico> ssfMedicoListOrphanCheck = ssfMedicoespecialidad.getSsfMedicoList();
            for (SsfMedico ssfMedicoListOrphanCheckSsfMedico : ssfMedicoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SsfMedicoespecialidad (" + ssfMedicoespecialidad + ") cannot be destroyed since the SsfMedico " + ssfMedicoListOrphanCheckSsfMedico + " in its ssfMedicoList field has a non-nullable idEspecialidad field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(ssfMedicoespecialidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfMedicoespecialidad> findSsfMedicoespecialidadEntities() {
        return findSsfMedicoespecialidadEntities(true, -1, -1);
    }

    public List<SsfMedicoespecialidad> findSsfMedicoespecialidadEntities(int maxResults, int firstResult) {
        return findSsfMedicoespecialidadEntities(false, maxResults, firstResult);
    }

    private List<SsfMedicoespecialidad> findSsfMedicoespecialidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfMedicoespecialidad.class));
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

    public SsfMedicoespecialidad findSsfMedicoespecialidad(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfMedicoespecialidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfMedicoespecialidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfMedicoespecialidad> rt = cq.from(SsfMedicoespecialidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
