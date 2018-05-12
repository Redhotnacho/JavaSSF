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
import duoc.cl.safe.entity.SsfMenu;
import duoc.cl.safe.entity.SsfPerfilvista;
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
public class SsfVistaJpaController implements Serializable {

    public SsfVistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfVista ssfVista) throws PreexistingEntityException, Exception {
        if (ssfVista.getSsfPerfilvistaList() == null) {
            ssfVista.setSsfPerfilvistaList(new ArrayList<SsfPerfilvista>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfMenu idMenu = ssfVista.getIdMenu();
            if (idMenu != null) {
                idMenu = em.getReference(idMenu.getClass(), idMenu.getId());
                ssfVista.setIdMenu(idMenu);
            }
            List<SsfPerfilvista> attachedSsfPerfilvistaList = new ArrayList<SsfPerfilvista>();
            for (SsfPerfilvista ssfPerfilvistaListSsfPerfilvistaToAttach : ssfVista.getSsfPerfilvistaList()) {
                ssfPerfilvistaListSsfPerfilvistaToAttach = em.getReference(ssfPerfilvistaListSsfPerfilvistaToAttach.getClass(), ssfPerfilvistaListSsfPerfilvistaToAttach.getId());
                attachedSsfPerfilvistaList.add(ssfPerfilvistaListSsfPerfilvistaToAttach);
            }
            ssfVista.setSsfPerfilvistaList(attachedSsfPerfilvistaList);
            em.persist(ssfVista);
            if (idMenu != null) {
                idMenu.getSsfVistaList().add(ssfVista);
                idMenu = em.merge(idMenu);
            }
            for (SsfPerfilvista ssfPerfilvistaListSsfPerfilvista : ssfVista.getSsfPerfilvistaList()) {
                SsfVista oldIdVistaOfSsfPerfilvistaListSsfPerfilvista = ssfPerfilvistaListSsfPerfilvista.getIdVista();
                ssfPerfilvistaListSsfPerfilvista.setIdVista(ssfVista);
                ssfPerfilvistaListSsfPerfilvista = em.merge(ssfPerfilvistaListSsfPerfilvista);
                if (oldIdVistaOfSsfPerfilvistaListSsfPerfilvista != null) {
                    oldIdVistaOfSsfPerfilvistaListSsfPerfilvista.getSsfPerfilvistaList().remove(ssfPerfilvistaListSsfPerfilvista);
                    oldIdVistaOfSsfPerfilvistaListSsfPerfilvista = em.merge(oldIdVistaOfSsfPerfilvistaListSsfPerfilvista);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfVista(ssfVista.getId()) != null) {
                throw new PreexistingEntityException("SsfVista " + ssfVista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfVista ssfVista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfVista persistentSsfVista = em.find(SsfVista.class, ssfVista.getId());
            SsfMenu idMenuOld = persistentSsfVista.getIdMenu();
            SsfMenu idMenuNew = ssfVista.getIdMenu();
            List<SsfPerfilvista> ssfPerfilvistaListOld = persistentSsfVista.getSsfPerfilvistaList();
            List<SsfPerfilvista> ssfPerfilvistaListNew = ssfVista.getSsfPerfilvistaList();
            if (idMenuNew != null) {
                idMenuNew = em.getReference(idMenuNew.getClass(), idMenuNew.getId());
                ssfVista.setIdMenu(idMenuNew);
            }
            List<SsfPerfilvista> attachedSsfPerfilvistaListNew = new ArrayList<SsfPerfilvista>();
            for (SsfPerfilvista ssfPerfilvistaListNewSsfPerfilvistaToAttach : ssfPerfilvistaListNew) {
                ssfPerfilvistaListNewSsfPerfilvistaToAttach = em.getReference(ssfPerfilvistaListNewSsfPerfilvistaToAttach.getClass(), ssfPerfilvistaListNewSsfPerfilvistaToAttach.getId());
                attachedSsfPerfilvistaListNew.add(ssfPerfilvistaListNewSsfPerfilvistaToAttach);
            }
            ssfPerfilvistaListNew = attachedSsfPerfilvistaListNew;
            ssfVista.setSsfPerfilvistaList(ssfPerfilvistaListNew);
            ssfVista = em.merge(ssfVista);
            if (idMenuOld != null && !idMenuOld.equals(idMenuNew)) {
                idMenuOld.getSsfVistaList().remove(ssfVista);
                idMenuOld = em.merge(idMenuOld);
            }
            if (idMenuNew != null && !idMenuNew.equals(idMenuOld)) {
                idMenuNew.getSsfVistaList().add(ssfVista);
                idMenuNew = em.merge(idMenuNew);
            }
            for (SsfPerfilvista ssfPerfilvistaListOldSsfPerfilvista : ssfPerfilvistaListOld) {
                if (!ssfPerfilvistaListNew.contains(ssfPerfilvistaListOldSsfPerfilvista)) {
                    ssfPerfilvistaListOldSsfPerfilvista.setIdVista(null);
                    ssfPerfilvistaListOldSsfPerfilvista = em.merge(ssfPerfilvistaListOldSsfPerfilvista);
                }
            }
            for (SsfPerfilvista ssfPerfilvistaListNewSsfPerfilvista : ssfPerfilvistaListNew) {
                if (!ssfPerfilvistaListOld.contains(ssfPerfilvistaListNewSsfPerfilvista)) {
                    SsfVista oldIdVistaOfSsfPerfilvistaListNewSsfPerfilvista = ssfPerfilvistaListNewSsfPerfilvista.getIdVista();
                    ssfPerfilvistaListNewSsfPerfilvista.setIdVista(ssfVista);
                    ssfPerfilvistaListNewSsfPerfilvista = em.merge(ssfPerfilvistaListNewSsfPerfilvista);
                    if (oldIdVistaOfSsfPerfilvistaListNewSsfPerfilvista != null && !oldIdVistaOfSsfPerfilvistaListNewSsfPerfilvista.equals(ssfVista)) {
                        oldIdVistaOfSsfPerfilvistaListNewSsfPerfilvista.getSsfPerfilvistaList().remove(ssfPerfilvistaListNewSsfPerfilvista);
                        oldIdVistaOfSsfPerfilvistaListNewSsfPerfilvista = em.merge(oldIdVistaOfSsfPerfilvistaListNewSsfPerfilvista);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfVista.getId();
                if (findSsfVista(id) == null) {
                    throw new NonexistentEntityException("The ssfVista with id " + id + " no longer exists.");
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
            SsfVista ssfVista;
            try {
                ssfVista = em.getReference(SsfVista.class, id);
                ssfVista.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfVista with id " + id + " no longer exists.", enfe);
            }
            SsfMenu idMenu = ssfVista.getIdMenu();
            if (idMenu != null) {
                idMenu.getSsfVistaList().remove(ssfVista);
                idMenu = em.merge(idMenu);
            }
            List<SsfPerfilvista> ssfPerfilvistaList = ssfVista.getSsfPerfilvistaList();
            for (SsfPerfilvista ssfPerfilvistaListSsfPerfilvista : ssfPerfilvistaList) {
                ssfPerfilvistaListSsfPerfilvista.setIdVista(null);
                ssfPerfilvistaListSsfPerfilvista = em.merge(ssfPerfilvistaListSsfPerfilvista);
            }
            em.remove(ssfVista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfVista> findSsfVistaEntities() {
        return findSsfVistaEntities(true, -1, -1);
    }

    public List<SsfVista> findSsfVistaEntities(int maxResults, int firstResult) {
        return findSsfVistaEntities(false, maxResults, firstResult);
    }

    private List<SsfVista> findSsfVistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfVista.class));
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

    public SsfVista findSsfVista(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfVista.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfVistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfVista> rt = cq.from(SsfVista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
