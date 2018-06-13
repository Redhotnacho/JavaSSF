/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.jpa;

import duoc.cl.safe.entity.SsfExamen;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import duoc.cl.safe.entity.SsfExamentipo;
import duoc.cl.safe.entity.SsfFichamedica;
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
public class SsfExamenJpaController implements Serializable {

    public SsfExamenJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfExamen ssfExamen) throws PreexistingEntityException, Exception {
        if (ssfExamen.getSsfFichamedicaList() == null) {
            ssfExamen.setSsfFichamedicaList(new ArrayList<SsfFichamedica>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfExamentipo idExamentipo = ssfExamen.getIdExamentipo();
            if (idExamentipo != null) {
                idExamentipo = em.getReference(idExamentipo.getClass(), idExamentipo.getId());
                ssfExamen.setIdExamentipo(idExamentipo);
            }
            List<SsfFichamedica> attachedSsfFichamedicaList = new ArrayList<SsfFichamedica>();
            for (SsfFichamedica ssfFichamedicaListSsfFichamedicaToAttach : ssfExamen.getSsfFichamedicaList()) {
                ssfFichamedicaListSsfFichamedicaToAttach = em.getReference(ssfFichamedicaListSsfFichamedicaToAttach.getClass(), ssfFichamedicaListSsfFichamedicaToAttach.getId());
                attachedSsfFichamedicaList.add(ssfFichamedicaListSsfFichamedicaToAttach);
            }
            ssfExamen.setSsfFichamedicaList(attachedSsfFichamedicaList);
            em.persist(ssfExamen);
            if (idExamentipo != null) {
                idExamentipo.getSsfExamenList().add(ssfExamen);
                idExamentipo = em.merge(idExamentipo);
            }
            for (SsfFichamedica ssfFichamedicaListSsfFichamedica : ssfExamen.getSsfFichamedicaList()) {
                SsfExamen oldIdExamenOfSsfFichamedicaListSsfFichamedica = ssfFichamedicaListSsfFichamedica.getIdExamen();
                ssfFichamedicaListSsfFichamedica.setIdExamen(ssfExamen);
                ssfFichamedicaListSsfFichamedica = em.merge(ssfFichamedicaListSsfFichamedica);
                if (oldIdExamenOfSsfFichamedicaListSsfFichamedica != null) {
                    oldIdExamenOfSsfFichamedicaListSsfFichamedica.getSsfFichamedicaList().remove(ssfFichamedicaListSsfFichamedica);
                    oldIdExamenOfSsfFichamedicaListSsfFichamedica = em.merge(oldIdExamenOfSsfFichamedicaListSsfFichamedica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfExamen(ssfExamen.getId()) != null) {
                throw new PreexistingEntityException("SsfExamen " + ssfExamen + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfExamen ssfExamen) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfExamen persistentSsfExamen = em.find(SsfExamen.class, ssfExamen.getId());
            SsfExamentipo idExamentipoOld = persistentSsfExamen.getIdExamentipo();
            SsfExamentipo idExamentipoNew = ssfExamen.getIdExamentipo();
            List<SsfFichamedica> ssfFichamedicaListOld = persistentSsfExamen.getSsfFichamedicaList();
            List<SsfFichamedica> ssfFichamedicaListNew = ssfExamen.getSsfFichamedicaList();
            if (idExamentipoNew != null) {
                idExamentipoNew = em.getReference(idExamentipoNew.getClass(), idExamentipoNew.getId());
                ssfExamen.setIdExamentipo(idExamentipoNew);
            }
            List<SsfFichamedica> attachedSsfFichamedicaListNew = new ArrayList<SsfFichamedica>();
            for (SsfFichamedica ssfFichamedicaListNewSsfFichamedicaToAttach : ssfFichamedicaListNew) {
                ssfFichamedicaListNewSsfFichamedicaToAttach = em.getReference(ssfFichamedicaListNewSsfFichamedicaToAttach.getClass(), ssfFichamedicaListNewSsfFichamedicaToAttach.getId());
                attachedSsfFichamedicaListNew.add(ssfFichamedicaListNewSsfFichamedicaToAttach);
            }
            ssfFichamedicaListNew = attachedSsfFichamedicaListNew;
            ssfExamen.setSsfFichamedicaList(ssfFichamedicaListNew);
            ssfExamen = em.merge(ssfExamen);
            if (idExamentipoOld != null && !idExamentipoOld.equals(idExamentipoNew)) {
                idExamentipoOld.getSsfExamenList().remove(ssfExamen);
                idExamentipoOld = em.merge(idExamentipoOld);
            }
            if (idExamentipoNew != null && !idExamentipoNew.equals(idExamentipoOld)) {
                idExamentipoNew.getSsfExamenList().add(ssfExamen);
                idExamentipoNew = em.merge(idExamentipoNew);
            }
            for (SsfFichamedica ssfFichamedicaListOldSsfFichamedica : ssfFichamedicaListOld) {
                if (!ssfFichamedicaListNew.contains(ssfFichamedicaListOldSsfFichamedica)) {
                    ssfFichamedicaListOldSsfFichamedica.setIdExamen(null);
                    ssfFichamedicaListOldSsfFichamedica = em.merge(ssfFichamedicaListOldSsfFichamedica);
                }
            }
            for (SsfFichamedica ssfFichamedicaListNewSsfFichamedica : ssfFichamedicaListNew) {
                if (!ssfFichamedicaListOld.contains(ssfFichamedicaListNewSsfFichamedica)) {
                    SsfExamen oldIdExamenOfSsfFichamedicaListNewSsfFichamedica = ssfFichamedicaListNewSsfFichamedica.getIdExamen();
                    ssfFichamedicaListNewSsfFichamedica.setIdExamen(ssfExamen);
                    ssfFichamedicaListNewSsfFichamedica = em.merge(ssfFichamedicaListNewSsfFichamedica);
                    if (oldIdExamenOfSsfFichamedicaListNewSsfFichamedica != null && !oldIdExamenOfSsfFichamedicaListNewSsfFichamedica.equals(ssfExamen)) {
                        oldIdExamenOfSsfFichamedicaListNewSsfFichamedica.getSsfFichamedicaList().remove(ssfFichamedicaListNewSsfFichamedica);
                        oldIdExamenOfSsfFichamedicaListNewSsfFichamedica = em.merge(oldIdExamenOfSsfFichamedicaListNewSsfFichamedica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfExamen.getId();
                if (findSsfExamen(id) == null) {
                    throw new NonexistentEntityException("The ssfExamen with id " + id + " no longer exists.");
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
            SsfExamen ssfExamen;
            try {
                ssfExamen = em.getReference(SsfExamen.class, id);
                ssfExamen.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfExamen with id " + id + " no longer exists.", enfe);
            }
            SsfExamentipo idExamentipo = ssfExamen.getIdExamentipo();
            if (idExamentipo != null) {
                idExamentipo.getSsfExamenList().remove(ssfExamen);
                idExamentipo = em.merge(idExamentipo);
            }
            List<SsfFichamedica> ssfFichamedicaList = ssfExamen.getSsfFichamedicaList();
            for (SsfFichamedica ssfFichamedicaListSsfFichamedica : ssfFichamedicaList) {
                ssfFichamedicaListSsfFichamedica.setIdExamen(null);
                ssfFichamedicaListSsfFichamedica = em.merge(ssfFichamedicaListSsfFichamedica);
            }
            em.remove(ssfExamen);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfExamen> findSsfExamenEntities() {
        return findSsfExamenEntities(true, -1, -1);
    }

    public List<SsfExamen> findSsfExamenEntities(int maxResults, int firstResult) {
        return findSsfExamenEntities(false, maxResults, firstResult);
    }

    private List<SsfExamen> findSsfExamenEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfExamen.class));
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

    public SsfExamen findSsfExamen(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfExamen.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfExamenCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfExamen> rt = cq.from(SsfExamen.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
