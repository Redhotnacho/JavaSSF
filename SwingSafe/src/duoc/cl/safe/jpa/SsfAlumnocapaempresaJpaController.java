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
import duoc.cl.safe.entity.SsfAlumno;
import duoc.cl.safe.entity.SsfAlumnocapaempresa;
import duoc.cl.safe.entity.SsfCapacitacionempresa;
import duoc.cl.safe.entity.SsfCertificado;
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
public class SsfAlumnocapaempresaJpaController implements Serializable {

    public SsfAlumnocapaempresaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfAlumnocapaempresa ssfAlumnocapaempresa) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfAlumno idAlumno = ssfAlumnocapaempresa.getIdAlumno();
            if (idAlumno != null) {
                idAlumno = em.getReference(idAlumno.getClass(), idAlumno.getId());
                ssfAlumnocapaempresa.setIdAlumno(idAlumno);
            }
            SsfCapacitacionempresa idCapaempresa = ssfAlumnocapaempresa.getIdCapaempresa();
            if (idCapaempresa != null) {
                idCapaempresa = em.getReference(idCapaempresa.getClass(), idCapaempresa.getId());
                ssfAlumnocapaempresa.setIdCapaempresa(idCapaempresa);
            }
            SsfCertificado idCertificado = ssfAlumnocapaempresa.getIdCertificado();
            if (idCertificado != null) {
                idCertificado = em.getReference(idCertificado.getClass(), idCertificado.getId());
                ssfAlumnocapaempresa.setIdCertificado(idCertificado);
            }
            em.persist(ssfAlumnocapaempresa);
            if (idAlumno != null) {
                idAlumno.getSsfAlumnocapaempresaList().add(ssfAlumnocapaempresa);
                idAlumno = em.merge(idAlumno);
            }
            if (idCapaempresa != null) {
                idCapaempresa.getSsfAlumnocapaempresaList().add(ssfAlumnocapaempresa);
                idCapaempresa = em.merge(idCapaempresa);
            }
            if (idCertificado != null) {
                idCertificado.getSsfAlumnocapaempresaList().add(ssfAlumnocapaempresa);
                idCertificado = em.merge(idCertificado);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfAlumnocapaempresa(ssfAlumnocapaempresa.getId()) != null) {
                throw new PreexistingEntityException("SsfAlumnocapaempresa " + ssfAlumnocapaempresa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfAlumnocapaempresa ssfAlumnocapaempresa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfAlumnocapaempresa persistentSsfAlumnocapaempresa = em.find(SsfAlumnocapaempresa.class, ssfAlumnocapaempresa.getId());
            SsfAlumno idAlumnoOld = persistentSsfAlumnocapaempresa.getIdAlumno();
            SsfAlumno idAlumnoNew = ssfAlumnocapaempresa.getIdAlumno();
            SsfCapacitacionempresa idCapaempresaOld = persistentSsfAlumnocapaempresa.getIdCapaempresa();
            SsfCapacitacionempresa idCapaempresaNew = ssfAlumnocapaempresa.getIdCapaempresa();
            SsfCertificado idCertificadoOld = persistentSsfAlumnocapaempresa.getIdCertificado();
            SsfCertificado idCertificadoNew = ssfAlumnocapaempresa.getIdCertificado();
            if (idAlumnoNew != null) {
                idAlumnoNew = em.getReference(idAlumnoNew.getClass(), idAlumnoNew.getId());
                ssfAlumnocapaempresa.setIdAlumno(idAlumnoNew);
            }
            if (idCapaempresaNew != null) {
                idCapaempresaNew = em.getReference(idCapaempresaNew.getClass(), idCapaempresaNew.getId());
                ssfAlumnocapaempresa.setIdCapaempresa(idCapaempresaNew);
            }
            if (idCertificadoNew != null) {
                idCertificadoNew = em.getReference(idCertificadoNew.getClass(), idCertificadoNew.getId());
                ssfAlumnocapaempresa.setIdCertificado(idCertificadoNew);
            }
            ssfAlumnocapaempresa = em.merge(ssfAlumnocapaempresa);
            if (idAlumnoOld != null && !idAlumnoOld.equals(idAlumnoNew)) {
                idAlumnoOld.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresa);
                idAlumnoOld = em.merge(idAlumnoOld);
            }
            if (idAlumnoNew != null && !idAlumnoNew.equals(idAlumnoOld)) {
                idAlumnoNew.getSsfAlumnocapaempresaList().add(ssfAlumnocapaempresa);
                idAlumnoNew = em.merge(idAlumnoNew);
            }
            if (idCapaempresaOld != null && !idCapaempresaOld.equals(idCapaempresaNew)) {
                idCapaempresaOld.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresa);
                idCapaempresaOld = em.merge(idCapaempresaOld);
            }
            if (idCapaempresaNew != null && !idCapaempresaNew.equals(idCapaempresaOld)) {
                idCapaempresaNew.getSsfAlumnocapaempresaList().add(ssfAlumnocapaempresa);
                idCapaempresaNew = em.merge(idCapaempresaNew);
            }
            if (idCertificadoOld != null && !idCertificadoOld.equals(idCertificadoNew)) {
                idCertificadoOld.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresa);
                idCertificadoOld = em.merge(idCertificadoOld);
            }
            if (idCertificadoNew != null && !idCertificadoNew.equals(idCertificadoOld)) {
                idCertificadoNew.getSsfAlumnocapaempresaList().add(ssfAlumnocapaempresa);
                idCertificadoNew = em.merge(idCertificadoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfAlumnocapaempresa.getId();
                if (findSsfAlumnocapaempresa(id) == null) {
                    throw new NonexistentEntityException("The ssfAlumnocapaempresa with id " + id + " no longer exists.");
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
            SsfAlumnocapaempresa ssfAlumnocapaempresa;
            try {
                ssfAlumnocapaempresa = em.getReference(SsfAlumnocapaempresa.class, id);
                ssfAlumnocapaempresa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfAlumnocapaempresa with id " + id + " no longer exists.", enfe);
            }
            SsfAlumno idAlumno = ssfAlumnocapaempresa.getIdAlumno();
            if (idAlumno != null) {
                idAlumno.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresa);
                idAlumno = em.merge(idAlumno);
            }
            SsfCapacitacionempresa idCapaempresa = ssfAlumnocapaempresa.getIdCapaempresa();
            if (idCapaempresa != null) {
                idCapaempresa.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresa);
                idCapaempresa = em.merge(idCapaempresa);
            }
            SsfCertificado idCertificado = ssfAlumnocapaempresa.getIdCertificado();
            if (idCertificado != null) {
                idCertificado.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresa);
                idCertificado = em.merge(idCertificado);
            }
            em.remove(ssfAlumnocapaempresa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfAlumnocapaempresa> findSsfAlumnocapaempresaEntities() {
        return findSsfAlumnocapaempresaEntities(true, -1, -1);
    }

    public List<SsfAlumnocapaempresa> findSsfAlumnocapaempresaEntities(int maxResults, int firstResult) {
        return findSsfAlumnocapaempresaEntities(false, maxResults, firstResult);
    }

    private List<SsfAlumnocapaempresa> findSsfAlumnocapaempresaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfAlumnocapaempresa.class));
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

    public SsfAlumnocapaempresa findSsfAlumnocapaempresa(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfAlumnocapaempresa.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfAlumnocapaempresaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfAlumnocapaempresa> rt = cq.from(SsfAlumnocapaempresa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
