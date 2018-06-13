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
import duoc.cl.safe.entity.SsfPerfil;
import duoc.cl.safe.entity.SsfPerfilvista;
import duoc.cl.safe.entity.SsfVista;
import duoc.cl.safe.jpa.exceptions.NonexistentEntityException;
import duoc.cl.safe.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nacho
 */
public class SsfPerfilvistaJpaController implements Serializable {

    public SsfPerfilvistaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfPerfilvista ssfPerfilvista) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfPerfil idPerfil = ssfPerfilvista.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getId());
                ssfPerfilvista.setIdPerfil(idPerfil);
            }
            SsfVista idVista = ssfPerfilvista.getIdVista();
            if (idVista != null) {
                idVista = em.getReference(idVista.getClass(), idVista.getId());
                ssfPerfilvista.setIdVista(idVista);
            }
            em.persist(ssfPerfilvista);
            if (idPerfil != null) {
                idPerfil.getSsfPerfilvistaList().add(ssfPerfilvista);
                idPerfil = em.merge(idPerfil);
            }
            if (idVista != null) {
                idVista.getSsfPerfilvistaList().add(ssfPerfilvista);
                idVista = em.merge(idVista);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfPerfilvista(ssfPerfilvista.getId()) != null) {
                throw new PreexistingEntityException("SsfPerfilvista " + ssfPerfilvista + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfPerfilvista ssfPerfilvista) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfPerfilvista persistentSsfPerfilvista = em.find(SsfPerfilvista.class, ssfPerfilvista.getId());
            SsfPerfil idPerfilOld = persistentSsfPerfilvista.getIdPerfil();
            SsfPerfil idPerfilNew = ssfPerfilvista.getIdPerfil();
            SsfVista idVistaOld = persistentSsfPerfilvista.getIdVista();
            SsfVista idVistaNew = ssfPerfilvista.getIdVista();
            if (idPerfilNew != null) {
                idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getId());
                ssfPerfilvista.setIdPerfil(idPerfilNew);
            }
            if (idVistaNew != null) {
                idVistaNew = em.getReference(idVistaNew.getClass(), idVistaNew.getId());
                ssfPerfilvista.setIdVista(idVistaNew);
            }
            ssfPerfilvista = em.merge(ssfPerfilvista);
            if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
                idPerfilOld.getSsfPerfilvistaList().remove(ssfPerfilvista);
                idPerfilOld = em.merge(idPerfilOld);
            }
            if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
                idPerfilNew.getSsfPerfilvistaList().add(ssfPerfilvista);
                idPerfilNew = em.merge(idPerfilNew);
            }
            if (idVistaOld != null && !idVistaOld.equals(idVistaNew)) {
                idVistaOld.getSsfPerfilvistaList().remove(ssfPerfilvista);
                idVistaOld = em.merge(idVistaOld);
            }
            if (idVistaNew != null && !idVistaNew.equals(idVistaOld)) {
                idVistaNew.getSsfPerfilvistaList().add(ssfPerfilvista);
                idVistaNew = em.merge(idVistaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfPerfilvista.getId();
                if (findSsfPerfilvista(id) == null) {
                    throw new NonexistentEntityException("The ssfPerfilvista with id " + id + " no longer exists.");
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
            SsfPerfilvista ssfPerfilvista;
            try {
                ssfPerfilvista = em.getReference(SsfPerfilvista.class, id);
                ssfPerfilvista.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfPerfilvista with id " + id + " no longer exists.", enfe);
            }
            SsfPerfil idPerfil = ssfPerfilvista.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getSsfPerfilvistaList().remove(ssfPerfilvista);
                idPerfil = em.merge(idPerfil);
            }
            SsfVista idVista = ssfPerfilvista.getIdVista();
            if (idVista != null) {
                idVista.getSsfPerfilvistaList().remove(ssfPerfilvista);
                idVista = em.merge(idVista);
            }
            em.remove(ssfPerfilvista);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfPerfilvista> findSsfPerfilvistaEntities() {
        return findSsfPerfilvistaEntities(true, -1, -1);
    }

    public List<SsfPerfilvista> findSsfPerfilvistaEntities(int maxResults, int firstResult) {
        return findSsfPerfilvistaEntities(false, maxResults, firstResult);
    }

    private List<SsfPerfilvista> findSsfPerfilvistaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfPerfilvista.class));
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

    public SsfPerfilvista findSsfPerfilvista(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfPerfilvista.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfPerfilvistaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfPerfilvista> rt = cq.from(SsfPerfilvista.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
