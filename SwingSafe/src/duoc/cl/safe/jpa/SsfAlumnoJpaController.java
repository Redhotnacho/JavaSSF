/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.jpa;

import duoc.cl.safe.entity.SsfAlumno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import duoc.cl.safe.entity.SsfEmpresa;
import duoc.cl.safe.entity.SsfPersona;
import duoc.cl.safe.entity.SsfAlumnocapaempresa;
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
public class SsfAlumnoJpaController implements Serializable {

    public SsfAlumnoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfAlumno ssfAlumno) throws PreexistingEntityException, Exception {
        if (ssfAlumno.getSsfAlumnocapaempresaList() == null) {
            ssfAlumno.setSsfAlumnocapaempresaList(new ArrayList<SsfAlumnocapaempresa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEmpresa idEmpresa = ssfAlumno.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getId());
                ssfAlumno.setIdEmpresa(idEmpresa);
            }
            SsfPersona idPersona = ssfAlumno.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getId());
                ssfAlumno.setIdPersona(idPersona);
            }
            List<SsfAlumnocapaempresa> attachedSsfAlumnocapaempresaList = new ArrayList<SsfAlumnocapaempresa>();
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach : ssfAlumno.getSsfAlumnocapaempresaList()) {
                ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach = em.getReference(ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach.getClass(), ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach.getId());
                attachedSsfAlumnocapaempresaList.add(ssfAlumnocapaempresaListSsfAlumnocapaempresaToAttach);
            }
            ssfAlumno.setSsfAlumnocapaempresaList(attachedSsfAlumnocapaempresaList);
            em.persist(ssfAlumno);
            if (idEmpresa != null) {
                idEmpresa.getSsfAlumnoList().add(ssfAlumno);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idPersona != null) {
                SsfAlumno oldSsfAlumnoOfIdPersona = idPersona.getSsfAlumno();
                if (oldSsfAlumnoOfIdPersona != null) {
                    oldSsfAlumnoOfIdPersona.setIdPersona(null);
                    oldSsfAlumnoOfIdPersona = em.merge(oldSsfAlumnoOfIdPersona);
                }
                idPersona.setSsfAlumno(ssfAlumno);
                idPersona = em.merge(idPersona);
            }
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListSsfAlumnocapaempresa : ssfAlumno.getSsfAlumnocapaempresaList()) {
                SsfAlumno oldIdAlumnoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa = ssfAlumnocapaempresaListSsfAlumnocapaempresa.getIdAlumno();
                ssfAlumnocapaempresaListSsfAlumnocapaempresa.setIdAlumno(ssfAlumno);
                ssfAlumnocapaempresaListSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListSsfAlumnocapaempresa);
                if (oldIdAlumnoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa != null) {
                    oldIdAlumnoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresaListSsfAlumnocapaempresa);
                    oldIdAlumnoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa = em.merge(oldIdAlumnoOfSsfAlumnocapaempresaListSsfAlumnocapaempresa);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfAlumno(ssfAlumno.getId()) != null) {
                throw new PreexistingEntityException("SsfAlumno " + ssfAlumno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfAlumno ssfAlumno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfAlumno persistentSsfAlumno = em.find(SsfAlumno.class, ssfAlumno.getId());
            SsfEmpresa idEmpresaOld = persistentSsfAlumno.getIdEmpresa();
            SsfEmpresa idEmpresaNew = ssfAlumno.getIdEmpresa();
            SsfPersona idPersonaOld = persistentSsfAlumno.getIdPersona();
            SsfPersona idPersonaNew = ssfAlumno.getIdPersona();
            List<SsfAlumnocapaempresa> ssfAlumnocapaempresaListOld = persistentSsfAlumno.getSsfAlumnocapaempresaList();
            List<SsfAlumnocapaempresa> ssfAlumnocapaempresaListNew = ssfAlumno.getSsfAlumnocapaempresaList();
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getId());
                ssfAlumno.setIdEmpresa(idEmpresaNew);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getId());
                ssfAlumno.setIdPersona(idPersonaNew);
            }
            List<SsfAlumnocapaempresa> attachedSsfAlumnocapaempresaListNew = new ArrayList<SsfAlumnocapaempresa>();
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach : ssfAlumnocapaempresaListNew) {
                ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach = em.getReference(ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach.getClass(), ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach.getId());
                attachedSsfAlumnocapaempresaListNew.add(ssfAlumnocapaempresaListNewSsfAlumnocapaempresaToAttach);
            }
            ssfAlumnocapaempresaListNew = attachedSsfAlumnocapaempresaListNew;
            ssfAlumno.setSsfAlumnocapaempresaList(ssfAlumnocapaempresaListNew);
            ssfAlumno = em.merge(ssfAlumno);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getSsfAlumnoList().remove(ssfAlumno);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getSsfAlumnoList().add(ssfAlumno);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.setSsfAlumno(null);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                SsfAlumno oldSsfAlumnoOfIdPersona = idPersonaNew.getSsfAlumno();
                if (oldSsfAlumnoOfIdPersona != null) {
                    oldSsfAlumnoOfIdPersona.setIdPersona(null);
                    oldSsfAlumnoOfIdPersona = em.merge(oldSsfAlumnoOfIdPersona);
                }
                idPersonaNew.setSsfAlumno(ssfAlumno);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListOldSsfAlumnocapaempresa : ssfAlumnocapaempresaListOld) {
                if (!ssfAlumnocapaempresaListNew.contains(ssfAlumnocapaempresaListOldSsfAlumnocapaempresa)) {
                    ssfAlumnocapaempresaListOldSsfAlumnocapaempresa.setIdAlumno(null);
                    ssfAlumnocapaempresaListOldSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListOldSsfAlumnocapaempresa);
                }
            }
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListNewSsfAlumnocapaempresa : ssfAlumnocapaempresaListNew) {
                if (!ssfAlumnocapaempresaListOld.contains(ssfAlumnocapaempresaListNewSsfAlumnocapaempresa)) {
                    SsfAlumno oldIdAlumnoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa = ssfAlumnocapaempresaListNewSsfAlumnocapaempresa.getIdAlumno();
                    ssfAlumnocapaempresaListNewSsfAlumnocapaempresa.setIdAlumno(ssfAlumno);
                    ssfAlumnocapaempresaListNewSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListNewSsfAlumnocapaempresa);
                    if (oldIdAlumnoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa != null && !oldIdAlumnoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa.equals(ssfAlumno)) {
                        oldIdAlumnoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa.getSsfAlumnocapaempresaList().remove(ssfAlumnocapaempresaListNewSsfAlumnocapaempresa);
                        oldIdAlumnoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa = em.merge(oldIdAlumnoOfSsfAlumnocapaempresaListNewSsfAlumnocapaempresa);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfAlumno.getId();
                if (findSsfAlumno(id) == null) {
                    throw new NonexistentEntityException("The ssfAlumno with id " + id + " no longer exists.");
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
            SsfAlumno ssfAlumno;
            try {
                ssfAlumno = em.getReference(SsfAlumno.class, id);
                ssfAlumno.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfAlumno with id " + id + " no longer exists.", enfe);
            }
            SsfEmpresa idEmpresa = ssfAlumno.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getSsfAlumnoList().remove(ssfAlumno);
                idEmpresa = em.merge(idEmpresa);
            }
            SsfPersona idPersona = ssfAlumno.getIdPersona();
            if (idPersona != null) {
                idPersona.setSsfAlumno(null);
                idPersona = em.merge(idPersona);
            }
            List<SsfAlumnocapaempresa> ssfAlumnocapaempresaList = ssfAlumno.getSsfAlumnocapaempresaList();
            for (SsfAlumnocapaempresa ssfAlumnocapaempresaListSsfAlumnocapaempresa : ssfAlumnocapaempresaList) {
                ssfAlumnocapaempresaListSsfAlumnocapaempresa.setIdAlumno(null);
                ssfAlumnocapaempresaListSsfAlumnocapaempresa = em.merge(ssfAlumnocapaempresaListSsfAlumnocapaempresa);
            }
            em.remove(ssfAlumno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfAlumno> findSsfAlumnoEntities() {
        return findSsfAlumnoEntities(true, -1, -1);
    }

    public List<SsfAlumno> findSsfAlumnoEntities(int maxResults, int firstResult) {
        return findSsfAlumnoEntities(false, maxResults, firstResult);
    }

    private List<SsfAlumno> findSsfAlumnoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfAlumno.class));
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

    public SsfAlumno findSsfAlumno(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfAlumno.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfAlumnoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfAlumno> rt = cq.from(SsfAlumno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
