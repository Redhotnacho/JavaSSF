/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.jpa;

import duoc.cl.safe.entity.SsfMenu;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import duoc.cl.safe.entity.SsfVista;
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
public class SsfMenuJpaController implements Serializable {

    public SsfMenuJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfMenu ssfMenu) throws PreexistingEntityException, Exception {
        if (ssfMenu.getSsfVistaList() == null) {
            ssfMenu.setSsfVistaList(new ArrayList<SsfVista>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfVista> attachedSsfVistaList = new ArrayList<SsfVista>();
            for (SsfVista ssfVistaListSsfVistaToAttach : ssfMenu.getSsfVistaList()) {
                ssfVistaListSsfVistaToAttach = em.getReference(ssfVistaListSsfVistaToAttach.getClass(), ssfVistaListSsfVistaToAttach.getId());
                attachedSsfVistaList.add(ssfVistaListSsfVistaToAttach);
            }
            ssfMenu.setSsfVistaList(attachedSsfVistaList);
            em.persist(ssfMenu);
            for (SsfVista ssfVistaListSsfVista : ssfMenu.getSsfVistaList()) {
                SsfMenu oldIdMenuOfSsfVistaListSsfVista = ssfVistaListSsfVista.getIdMenu();
                ssfVistaListSsfVista.setIdMenu(ssfMenu);
                ssfVistaListSsfVista = em.merge(ssfVistaListSsfVista);
                if (oldIdMenuOfSsfVistaListSsfVista != null) {
                    oldIdMenuOfSsfVistaListSsfVista.getSsfVistaList().remove(ssfVistaListSsfVista);
                    oldIdMenuOfSsfVistaListSsfVista = em.merge(oldIdMenuOfSsfVistaListSsfVista);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfMenu(ssfMenu.getId()) != null) {
                throw new PreexistingEntityException("SsfMenu " + ssfMenu + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfMenu ssfMenu) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfMenu persistentSsfMenu = em.find(SsfMenu.class, ssfMenu.getId());
            List<SsfVista> ssfVistaListOld = persistentSsfMenu.getSsfVistaList();
            List<SsfVista> ssfVistaListNew = ssfMenu.getSsfVistaList();
            List<SsfVista> attachedSsfVistaListNew = new ArrayList<SsfVista>();
            for (SsfVista ssfVistaListNewSsfVistaToAttach : ssfVistaListNew) {
                ssfVistaListNewSsfVistaToAttach = em.getReference(ssfVistaListNewSsfVistaToAttach.getClass(), ssfVistaListNewSsfVistaToAttach.getId());
                attachedSsfVistaListNew.add(ssfVistaListNewSsfVistaToAttach);
            }
            ssfVistaListNew = attachedSsfVistaListNew;
            ssfMenu.setSsfVistaList(ssfVistaListNew);
            ssfMenu = em.merge(ssfMenu);
            for (SsfVista ssfVistaListOldSsfVista : ssfVistaListOld) {
                if (!ssfVistaListNew.contains(ssfVistaListOldSsfVista)) {
                    ssfVistaListOldSsfVista.setIdMenu(null);
                    ssfVistaListOldSsfVista = em.merge(ssfVistaListOldSsfVista);
                }
            }
            for (SsfVista ssfVistaListNewSsfVista : ssfVistaListNew) {
                if (!ssfVistaListOld.contains(ssfVistaListNewSsfVista)) {
                    SsfMenu oldIdMenuOfSsfVistaListNewSsfVista = ssfVistaListNewSsfVista.getIdMenu();
                    ssfVistaListNewSsfVista.setIdMenu(ssfMenu);
                    ssfVistaListNewSsfVista = em.merge(ssfVistaListNewSsfVista);
                    if (oldIdMenuOfSsfVistaListNewSsfVista != null && !oldIdMenuOfSsfVistaListNewSsfVista.equals(ssfMenu)) {
                        oldIdMenuOfSsfVistaListNewSsfVista.getSsfVistaList().remove(ssfVistaListNewSsfVista);
                        oldIdMenuOfSsfVistaListNewSsfVista = em.merge(oldIdMenuOfSsfVistaListNewSsfVista);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfMenu.getId();
                if (findSsfMenu(id) == null) {
                    throw new NonexistentEntityException("The ssfMenu with id " + id + " no longer exists.");
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
            SsfMenu ssfMenu;
            try {
                ssfMenu = em.getReference(SsfMenu.class, id);
                ssfMenu.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfMenu with id " + id + " no longer exists.", enfe);
            }
            List<SsfVista> ssfVistaList = ssfMenu.getSsfVistaList();
            for (SsfVista ssfVistaListSsfVista : ssfVistaList) {
                ssfVistaListSsfVista.setIdMenu(null);
                ssfVistaListSsfVista = em.merge(ssfVistaListSsfVista);
            }
            em.remove(ssfMenu);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfMenu> findSsfMenuEntities() {
        return findSsfMenuEntities(true, -1, -1);
    }

    public List<SsfMenu> findSsfMenuEntities(int maxResults, int firstResult) {
        return findSsfMenuEntities(false, maxResults, firstResult);
    }

    private List<SsfMenu> findSsfMenuEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfMenu.class));
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

    public SsfMenu findSsfMenu(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfMenu.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfMenuCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfMenu> rt = cq.from(SsfMenu.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
