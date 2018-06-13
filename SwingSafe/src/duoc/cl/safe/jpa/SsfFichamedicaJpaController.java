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
import duoc.cl.safe.entity.SsfExamen;
import duoc.cl.safe.entity.SsfFichamedica;
import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.entity.SsfFichamedicaatencion;
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
public class SsfFichamedicaJpaController implements Serializable {

    public SsfFichamedicaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfFichamedica ssfFichamedica) throws PreexistingEntityException, Exception {
        if (ssfFichamedica.getSsfFichamedicaatencionList() == null) {
            ssfFichamedica.setSsfFichamedicaatencionList(new ArrayList<SsfFichamedicaatencion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfExamen idExamen = ssfFichamedica.getIdExamen();
            if (idExamen != null) {
                idExamen = em.getReference(idExamen.getClass(), idExamen.getId());
                ssfFichamedica.setIdExamen(idExamen);
            }
            SsfUsuario idUsuario = ssfFichamedica.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getId());
                ssfFichamedica.setIdUsuario(idUsuario);
            }
            List<SsfFichamedicaatencion> attachedSsfFichamedicaatencionList = new ArrayList<SsfFichamedicaatencion>();
            for (SsfFichamedicaatencion ssfFichamedicaatencionListSsfFichamedicaatencionToAttach : ssfFichamedica.getSsfFichamedicaatencionList()) {
                ssfFichamedicaatencionListSsfFichamedicaatencionToAttach = em.getReference(ssfFichamedicaatencionListSsfFichamedicaatencionToAttach.getClass(), ssfFichamedicaatencionListSsfFichamedicaatencionToAttach.getId());
                attachedSsfFichamedicaatencionList.add(ssfFichamedicaatencionListSsfFichamedicaatencionToAttach);
            }
            ssfFichamedica.setSsfFichamedicaatencionList(attachedSsfFichamedicaatencionList);
            em.persist(ssfFichamedica);
            if (idExamen != null) {
                idExamen.getSsfFichamedicaList().add(ssfFichamedica);
                idExamen = em.merge(idExamen);
            }
            if (idUsuario != null) {
                idUsuario.getSsfFichamedicaList().add(ssfFichamedica);
                idUsuario = em.merge(idUsuario);
            }
            for (SsfFichamedicaatencion ssfFichamedicaatencionListSsfFichamedicaatencion : ssfFichamedica.getSsfFichamedicaatencionList()) {
                SsfFichamedica oldIdFichamedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion = ssfFichamedicaatencionListSsfFichamedicaatencion.getIdFichamedica();
                ssfFichamedicaatencionListSsfFichamedicaatencion.setIdFichamedica(ssfFichamedica);
                ssfFichamedicaatencionListSsfFichamedicaatencion = em.merge(ssfFichamedicaatencionListSsfFichamedicaatencion);
                if (oldIdFichamedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion != null) {
                    oldIdFichamedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion.getSsfFichamedicaatencionList().remove(ssfFichamedicaatencionListSsfFichamedicaatencion);
                    oldIdFichamedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion = em.merge(oldIdFichamedicaOfSsfFichamedicaatencionListSsfFichamedicaatencion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfFichamedica(ssfFichamedica.getId()) != null) {
                throw new PreexistingEntityException("SsfFichamedica " + ssfFichamedica + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfFichamedica ssfFichamedica) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfFichamedica persistentSsfFichamedica = em.find(SsfFichamedica.class, ssfFichamedica.getId());
            SsfExamen idExamenOld = persistentSsfFichamedica.getIdExamen();
            SsfExamen idExamenNew = ssfFichamedica.getIdExamen();
            SsfUsuario idUsuarioOld = persistentSsfFichamedica.getIdUsuario();
            SsfUsuario idUsuarioNew = ssfFichamedica.getIdUsuario();
            List<SsfFichamedicaatencion> ssfFichamedicaatencionListOld = persistentSsfFichamedica.getSsfFichamedicaatencionList();
            List<SsfFichamedicaatencion> ssfFichamedicaatencionListNew = ssfFichamedica.getSsfFichamedicaatencionList();
            if (idExamenNew != null) {
                idExamenNew = em.getReference(idExamenNew.getClass(), idExamenNew.getId());
                ssfFichamedica.setIdExamen(idExamenNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getId());
                ssfFichamedica.setIdUsuario(idUsuarioNew);
            }
            List<SsfFichamedicaatencion> attachedSsfFichamedicaatencionListNew = new ArrayList<SsfFichamedicaatencion>();
            for (SsfFichamedicaatencion ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach : ssfFichamedicaatencionListNew) {
                ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach = em.getReference(ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach.getClass(), ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach.getId());
                attachedSsfFichamedicaatencionListNew.add(ssfFichamedicaatencionListNewSsfFichamedicaatencionToAttach);
            }
            ssfFichamedicaatencionListNew = attachedSsfFichamedicaatencionListNew;
            ssfFichamedica.setSsfFichamedicaatencionList(ssfFichamedicaatencionListNew);
            ssfFichamedica = em.merge(ssfFichamedica);
            if (idExamenOld != null && !idExamenOld.equals(idExamenNew)) {
                idExamenOld.getSsfFichamedicaList().remove(ssfFichamedica);
                idExamenOld = em.merge(idExamenOld);
            }
            if (idExamenNew != null && !idExamenNew.equals(idExamenOld)) {
                idExamenNew.getSsfFichamedicaList().add(ssfFichamedica);
                idExamenNew = em.merge(idExamenNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getSsfFichamedicaList().remove(ssfFichamedica);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getSsfFichamedicaList().add(ssfFichamedica);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (SsfFichamedicaatencion ssfFichamedicaatencionListOldSsfFichamedicaatencion : ssfFichamedicaatencionListOld) {
                if (!ssfFichamedicaatencionListNew.contains(ssfFichamedicaatencionListOldSsfFichamedicaatencion)) {
                    ssfFichamedicaatencionListOldSsfFichamedicaatencion.setIdFichamedica(null);
                    ssfFichamedicaatencionListOldSsfFichamedicaatencion = em.merge(ssfFichamedicaatencionListOldSsfFichamedicaatencion);
                }
            }
            for (SsfFichamedicaatencion ssfFichamedicaatencionListNewSsfFichamedicaatencion : ssfFichamedicaatencionListNew) {
                if (!ssfFichamedicaatencionListOld.contains(ssfFichamedicaatencionListNewSsfFichamedicaatencion)) {
                    SsfFichamedica oldIdFichamedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion = ssfFichamedicaatencionListNewSsfFichamedicaatencion.getIdFichamedica();
                    ssfFichamedicaatencionListNewSsfFichamedicaatencion.setIdFichamedica(ssfFichamedica);
                    ssfFichamedicaatencionListNewSsfFichamedicaatencion = em.merge(ssfFichamedicaatencionListNewSsfFichamedicaatencion);
                    if (oldIdFichamedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion != null && !oldIdFichamedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion.equals(ssfFichamedica)) {
                        oldIdFichamedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion.getSsfFichamedicaatencionList().remove(ssfFichamedicaatencionListNewSsfFichamedicaatencion);
                        oldIdFichamedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion = em.merge(oldIdFichamedicaOfSsfFichamedicaatencionListNewSsfFichamedicaatencion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfFichamedica.getId();
                if (findSsfFichamedica(id) == null) {
                    throw new NonexistentEntityException("The ssfFichamedica with id " + id + " no longer exists.");
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
            SsfFichamedica ssfFichamedica;
            try {
                ssfFichamedica = em.getReference(SsfFichamedica.class, id);
                ssfFichamedica.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfFichamedica with id " + id + " no longer exists.", enfe);
            }
            SsfExamen idExamen = ssfFichamedica.getIdExamen();
            if (idExamen != null) {
                idExamen.getSsfFichamedicaList().remove(ssfFichamedica);
                idExamen = em.merge(idExamen);
            }
            SsfUsuario idUsuario = ssfFichamedica.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getSsfFichamedicaList().remove(ssfFichamedica);
                idUsuario = em.merge(idUsuario);
            }
            List<SsfFichamedicaatencion> ssfFichamedicaatencionList = ssfFichamedica.getSsfFichamedicaatencionList();
            for (SsfFichamedicaatencion ssfFichamedicaatencionListSsfFichamedicaatencion : ssfFichamedicaatencionList) {
                ssfFichamedicaatencionListSsfFichamedicaatencion.setIdFichamedica(null);
                ssfFichamedicaatencionListSsfFichamedicaatencion = em.merge(ssfFichamedicaatencionListSsfFichamedicaatencion);
            }
            em.remove(ssfFichamedica);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfFichamedica> findSsfFichamedicaEntities() {
        return findSsfFichamedicaEntities(true, -1, -1);
    }

    public List<SsfFichamedica> findSsfFichamedicaEntities(int maxResults, int firstResult) {
        return findSsfFichamedicaEntities(false, maxResults, firstResult);
    }

    private List<SsfFichamedica> findSsfFichamedicaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfFichamedica.class));
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

    public SsfFichamedica findSsfFichamedica(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfFichamedica.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfFichamedicaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfFichamedica> rt = cq.from(SsfFichamedica.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
