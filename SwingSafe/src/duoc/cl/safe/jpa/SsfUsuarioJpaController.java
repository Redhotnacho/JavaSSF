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
import duoc.cl.safe.entity.SsfEmpresa;
import duoc.cl.safe.entity.SsfPerfil;
import duoc.cl.safe.entity.SsfPersona;
import duoc.cl.safe.entity.SsfCapacitacionempresa;
import java.util.ArrayList;
import java.util.List;
import duoc.cl.safe.entity.SsfAtencionmedica;
import duoc.cl.safe.entity.SsfFichamedica;
import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.jpa.exceptions.IllegalOrphanException;
import duoc.cl.safe.jpa.exceptions.NonexistentEntityException;
import duoc.cl.safe.jpa.exceptions.PreexistingEntityException;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Nacho
 */
public class SsfUsuarioJpaController implements Serializable {

    public SsfUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfUsuario ssfUsuario) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (ssfUsuario.getSsfCapacitacionempresaList() == null) {
            ssfUsuario.setSsfCapacitacionempresaList(new ArrayList<SsfCapacitacionempresa>());
        }
        if (ssfUsuario.getSsfAtencionmedicaList() == null) {
            ssfUsuario.setSsfAtencionmedicaList(new ArrayList<SsfAtencionmedica>());
        }
        if (ssfUsuario.getSsfFichamedicaList() == null) {
            ssfUsuario.setSsfFichamedicaList(new ArrayList<SsfFichamedica>());
        }
        List<String> illegalOrphanMessages = null;
        SsfPersona idPersonaOrphanCheck = ssfUsuario.getIdPersona();
        if (idPersonaOrphanCheck != null) {
            SsfUsuario oldSsfUsuarioOfIdPersona = idPersonaOrphanCheck.getSsfUsuario();
            if (oldSsfUsuarioOfIdPersona != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The SsfPersona " + idPersonaOrphanCheck + " already has an item of type SsfUsuario whose idPersona column cannot be null. Please make another selection for the idPersona field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfEmpresa idEmpresa = ssfUsuario.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getId());
                ssfUsuario.setIdEmpresa(idEmpresa);
            }
            SsfPerfil idPerfil = ssfUsuario.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getId());
                ssfUsuario.setIdPerfil(idPerfil);
            }
            SsfPersona idPersona = ssfUsuario.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getId());
                ssfUsuario.setIdPersona(idPersona);
            }
            List<SsfCapacitacionempresa> attachedSsfCapacitacionempresaList = new ArrayList<SsfCapacitacionempresa>();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresaToAttach : ssfUsuario.getSsfCapacitacionempresaList()) {
                ssfCapacitacionempresaListSsfCapacitacionempresaToAttach = em.getReference(ssfCapacitacionempresaListSsfCapacitacionempresaToAttach.getClass(), ssfCapacitacionempresaListSsfCapacitacionempresaToAttach.getId());
                attachedSsfCapacitacionempresaList.add(ssfCapacitacionempresaListSsfCapacitacionempresaToAttach);
            }
            ssfUsuario.setSsfCapacitacionempresaList(attachedSsfCapacitacionempresaList);
            List<SsfAtencionmedica> attachedSsfAtencionmedicaList = new ArrayList<SsfAtencionmedica>();
            for (SsfAtencionmedica ssfAtencionmedicaListSsfAtencionmedicaToAttach : ssfUsuario.getSsfAtencionmedicaList()) {
                ssfAtencionmedicaListSsfAtencionmedicaToAttach = em.getReference(ssfAtencionmedicaListSsfAtencionmedicaToAttach.getClass(), ssfAtencionmedicaListSsfAtencionmedicaToAttach.getId());
                attachedSsfAtencionmedicaList.add(ssfAtencionmedicaListSsfAtencionmedicaToAttach);
            }
            ssfUsuario.setSsfAtencionmedicaList(attachedSsfAtencionmedicaList);
            List<SsfFichamedica> attachedSsfFichamedicaList = new ArrayList<SsfFichamedica>();
            for (SsfFichamedica ssfFichamedicaListSsfFichamedicaToAttach : ssfUsuario.getSsfFichamedicaList()) {
                ssfFichamedicaListSsfFichamedicaToAttach = em.getReference(ssfFichamedicaListSsfFichamedicaToAttach.getClass(), ssfFichamedicaListSsfFichamedicaToAttach.getId());
                attachedSsfFichamedicaList.add(ssfFichamedicaListSsfFichamedicaToAttach);
            }
            ssfUsuario.setSsfFichamedicaList(attachedSsfFichamedicaList);
            em.persist(ssfUsuario);
            if (idEmpresa != null) {
                idEmpresa.getSsfUsuarioList().add(ssfUsuario);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idPerfil != null) {
                idPerfil.getSsfUsuarioList().add(ssfUsuario);
                idPerfil = em.merge(idPerfil);
            }
            if (idPersona != null) {
                idPersona.setSsfUsuario(ssfUsuario);
                idPersona = em.merge(idPersona);
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresa : ssfUsuario.getSsfCapacitacionempresaList()) {
                SsfUsuario oldIdUsuarioOfSsfCapacitacionempresaListSsfCapacitacionempresa = ssfCapacitacionempresaListSsfCapacitacionempresa.getIdUsuario();
                ssfCapacitacionempresaListSsfCapacitacionempresa.setIdUsuario(ssfUsuario);
                ssfCapacitacionempresaListSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListSsfCapacitacionempresa);
                if (oldIdUsuarioOfSsfCapacitacionempresaListSsfCapacitacionempresa != null) {
                    oldIdUsuarioOfSsfCapacitacionempresaListSsfCapacitacionempresa.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresaListSsfCapacitacionempresa);
                    oldIdUsuarioOfSsfCapacitacionempresaListSsfCapacitacionempresa = em.merge(oldIdUsuarioOfSsfCapacitacionempresaListSsfCapacitacionempresa);
                }
            }
            for (SsfAtencionmedica ssfAtencionmedicaListSsfAtencionmedica : ssfUsuario.getSsfAtencionmedicaList()) {
                SsfUsuario oldIdUsuarioOfSsfAtencionmedicaListSsfAtencionmedica = ssfAtencionmedicaListSsfAtencionmedica.getIdUsuario();
                ssfAtencionmedicaListSsfAtencionmedica.setIdUsuario(ssfUsuario);
                ssfAtencionmedicaListSsfAtencionmedica = em.merge(ssfAtencionmedicaListSsfAtencionmedica);
                if (oldIdUsuarioOfSsfAtencionmedicaListSsfAtencionmedica != null) {
                    oldIdUsuarioOfSsfAtencionmedicaListSsfAtencionmedica.getSsfAtencionmedicaList().remove(ssfAtencionmedicaListSsfAtencionmedica);
                    oldIdUsuarioOfSsfAtencionmedicaListSsfAtencionmedica = em.merge(oldIdUsuarioOfSsfAtencionmedicaListSsfAtencionmedica);
                }
            }
            for (SsfFichamedica ssfFichamedicaListSsfFichamedica : ssfUsuario.getSsfFichamedicaList()) {
                SsfUsuario oldIdUsuarioOfSsfFichamedicaListSsfFichamedica = ssfFichamedicaListSsfFichamedica.getIdUsuario();
                ssfFichamedicaListSsfFichamedica.setIdUsuario(ssfUsuario);
                ssfFichamedicaListSsfFichamedica = em.merge(ssfFichamedicaListSsfFichamedica);
                if (oldIdUsuarioOfSsfFichamedicaListSsfFichamedica != null) {
                    oldIdUsuarioOfSsfFichamedicaListSsfFichamedica.getSsfFichamedicaList().remove(ssfFichamedicaListSsfFichamedica);
                    oldIdUsuarioOfSsfFichamedicaListSsfFichamedica = em.merge(oldIdUsuarioOfSsfFichamedicaListSsfFichamedica);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfUsuario(ssfUsuario.getId()) != null) {
                throw new PreexistingEntityException("SsfUsuario " + ssfUsuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfUsuario ssfUsuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfUsuario persistentSsfUsuario = em.find(SsfUsuario.class, ssfUsuario.getId());
            SsfEmpresa idEmpresaOld = persistentSsfUsuario.getIdEmpresa();
            SsfEmpresa idEmpresaNew = ssfUsuario.getIdEmpresa();
            SsfPerfil idPerfilOld = persistentSsfUsuario.getIdPerfil();
            SsfPerfil idPerfilNew = ssfUsuario.getIdPerfil();
            SsfPersona idPersonaOld = persistentSsfUsuario.getIdPersona();
            SsfPersona idPersonaNew = ssfUsuario.getIdPersona();
            List<SsfCapacitacionempresa> ssfCapacitacionempresaListOld = persistentSsfUsuario.getSsfCapacitacionempresaList();
            List<SsfCapacitacionempresa> ssfCapacitacionempresaListNew = ssfUsuario.getSsfCapacitacionempresaList();
            List<SsfAtencionmedica> ssfAtencionmedicaListOld = persistentSsfUsuario.getSsfAtencionmedicaList();
            List<SsfAtencionmedica> ssfAtencionmedicaListNew = ssfUsuario.getSsfAtencionmedicaList();
            List<SsfFichamedica> ssfFichamedicaListOld = persistentSsfUsuario.getSsfFichamedicaList();
            List<SsfFichamedica> ssfFichamedicaListNew = ssfUsuario.getSsfFichamedicaList();
            List<String> illegalOrphanMessages = null;
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                SsfUsuario oldSsfUsuarioOfIdPersona = idPersonaNew.getSsfUsuario();
                if (oldSsfUsuarioOfIdPersona != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The SsfPersona " + idPersonaNew + " already has an item of type SsfUsuario whose idPersona column cannot be null. Please make another selection for the idPersona field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getId());
                ssfUsuario.setIdEmpresa(idEmpresaNew);
            }
            if (idPerfilNew != null) {
                idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getId());
                ssfUsuario.setIdPerfil(idPerfilNew);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getId());
                ssfUsuario.setIdPersona(idPersonaNew);
            }
            List<SsfCapacitacionempresa> attachedSsfCapacitacionempresaListNew = new ArrayList<SsfCapacitacionempresa>();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach : ssfCapacitacionempresaListNew) {
                ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach = em.getReference(ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach.getClass(), ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach.getId());
                attachedSsfCapacitacionempresaListNew.add(ssfCapacitacionempresaListNewSsfCapacitacionempresaToAttach);
            }
            ssfCapacitacionempresaListNew = attachedSsfCapacitacionempresaListNew;
            ssfUsuario.setSsfCapacitacionempresaList(ssfCapacitacionempresaListNew);
            List<SsfAtencionmedica> attachedSsfAtencionmedicaListNew = new ArrayList<SsfAtencionmedica>();
            for (SsfAtencionmedica ssfAtencionmedicaListNewSsfAtencionmedicaToAttach : ssfAtencionmedicaListNew) {
                ssfAtencionmedicaListNewSsfAtencionmedicaToAttach = em.getReference(ssfAtencionmedicaListNewSsfAtencionmedicaToAttach.getClass(), ssfAtencionmedicaListNewSsfAtencionmedicaToAttach.getId());
                attachedSsfAtencionmedicaListNew.add(ssfAtencionmedicaListNewSsfAtencionmedicaToAttach);
            }
            ssfAtencionmedicaListNew = attachedSsfAtencionmedicaListNew;
            ssfUsuario.setSsfAtencionmedicaList(ssfAtencionmedicaListNew);
            List<SsfFichamedica> attachedSsfFichamedicaListNew = new ArrayList<SsfFichamedica>();
            for (SsfFichamedica ssfFichamedicaListNewSsfFichamedicaToAttach : ssfFichamedicaListNew) {
                ssfFichamedicaListNewSsfFichamedicaToAttach = em.getReference(ssfFichamedicaListNewSsfFichamedicaToAttach.getClass(), ssfFichamedicaListNewSsfFichamedicaToAttach.getId());
                attachedSsfFichamedicaListNew.add(ssfFichamedicaListNewSsfFichamedicaToAttach);
            }
            ssfFichamedicaListNew = attachedSsfFichamedicaListNew;
            ssfUsuario.setSsfFichamedicaList(ssfFichamedicaListNew);
            ssfUsuario = em.merge(ssfUsuario);
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getSsfUsuarioList().remove(ssfUsuario);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getSsfUsuarioList().add(ssfUsuario);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
                idPerfilOld.getSsfUsuarioList().remove(ssfUsuario);
                idPerfilOld = em.merge(idPerfilOld);
            }
            if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
                idPerfilNew.getSsfUsuarioList().add(ssfUsuario);
                idPerfilNew = em.merge(idPerfilNew);
            }
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.setSsfUsuario(null);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.setSsfUsuario(ssfUsuario);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListOldSsfCapacitacionempresa : ssfCapacitacionempresaListOld) {
                if (!ssfCapacitacionempresaListNew.contains(ssfCapacitacionempresaListOldSsfCapacitacionempresa)) {
                    ssfCapacitacionempresaListOldSsfCapacitacionempresa.setIdUsuario(null);
                    ssfCapacitacionempresaListOldSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListOldSsfCapacitacionempresa);
                }
            }
            for (SsfCapacitacionempresa ssfCapacitacionempresaListNewSsfCapacitacionempresa : ssfCapacitacionempresaListNew) {
                if (!ssfCapacitacionempresaListOld.contains(ssfCapacitacionempresaListNewSsfCapacitacionempresa)) {
                    SsfUsuario oldIdUsuarioOfSsfCapacitacionempresaListNewSsfCapacitacionempresa = ssfCapacitacionempresaListNewSsfCapacitacionempresa.getIdUsuario();
                    ssfCapacitacionempresaListNewSsfCapacitacionempresa.setIdUsuario(ssfUsuario);
                    ssfCapacitacionempresaListNewSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListNewSsfCapacitacionempresa);
                    if (oldIdUsuarioOfSsfCapacitacionempresaListNewSsfCapacitacionempresa != null && !oldIdUsuarioOfSsfCapacitacionempresaListNewSsfCapacitacionempresa.equals(ssfUsuario)) {
                        oldIdUsuarioOfSsfCapacitacionempresaListNewSsfCapacitacionempresa.getSsfCapacitacionempresaList().remove(ssfCapacitacionempresaListNewSsfCapacitacionempresa);
                        oldIdUsuarioOfSsfCapacitacionempresaListNewSsfCapacitacionempresa = em.merge(oldIdUsuarioOfSsfCapacitacionempresaListNewSsfCapacitacionempresa);
                    }
                }
            }
            for (SsfAtencionmedica ssfAtencionmedicaListOldSsfAtencionmedica : ssfAtencionmedicaListOld) {
                if (!ssfAtencionmedicaListNew.contains(ssfAtencionmedicaListOldSsfAtencionmedica)) {
                    ssfAtencionmedicaListOldSsfAtencionmedica.setIdUsuario(null);
                    ssfAtencionmedicaListOldSsfAtencionmedica = em.merge(ssfAtencionmedicaListOldSsfAtencionmedica);
                }
            }
            for (SsfAtencionmedica ssfAtencionmedicaListNewSsfAtencionmedica : ssfAtencionmedicaListNew) {
                if (!ssfAtencionmedicaListOld.contains(ssfAtencionmedicaListNewSsfAtencionmedica)) {
                    SsfUsuario oldIdUsuarioOfSsfAtencionmedicaListNewSsfAtencionmedica = ssfAtencionmedicaListNewSsfAtencionmedica.getIdUsuario();
                    ssfAtencionmedicaListNewSsfAtencionmedica.setIdUsuario(ssfUsuario);
                    ssfAtencionmedicaListNewSsfAtencionmedica = em.merge(ssfAtencionmedicaListNewSsfAtencionmedica);
                    if (oldIdUsuarioOfSsfAtencionmedicaListNewSsfAtencionmedica != null && !oldIdUsuarioOfSsfAtencionmedicaListNewSsfAtencionmedica.equals(ssfUsuario)) {
                        oldIdUsuarioOfSsfAtencionmedicaListNewSsfAtencionmedica.getSsfAtencionmedicaList().remove(ssfAtencionmedicaListNewSsfAtencionmedica);
                        oldIdUsuarioOfSsfAtencionmedicaListNewSsfAtencionmedica = em.merge(oldIdUsuarioOfSsfAtencionmedicaListNewSsfAtencionmedica);
                    }
                }
            }
            for (SsfFichamedica ssfFichamedicaListOldSsfFichamedica : ssfFichamedicaListOld) {
                if (!ssfFichamedicaListNew.contains(ssfFichamedicaListOldSsfFichamedica)) {
                    ssfFichamedicaListOldSsfFichamedica.setIdUsuario(null);
                    ssfFichamedicaListOldSsfFichamedica = em.merge(ssfFichamedicaListOldSsfFichamedica);
                }
            }
            for (SsfFichamedica ssfFichamedicaListNewSsfFichamedica : ssfFichamedicaListNew) {
                if (!ssfFichamedicaListOld.contains(ssfFichamedicaListNewSsfFichamedica)) {
                    SsfUsuario oldIdUsuarioOfSsfFichamedicaListNewSsfFichamedica = ssfFichamedicaListNewSsfFichamedica.getIdUsuario();
                    ssfFichamedicaListNewSsfFichamedica.setIdUsuario(ssfUsuario);
                    ssfFichamedicaListNewSsfFichamedica = em.merge(ssfFichamedicaListNewSsfFichamedica);
                    if (oldIdUsuarioOfSsfFichamedicaListNewSsfFichamedica != null && !oldIdUsuarioOfSsfFichamedicaListNewSsfFichamedica.equals(ssfUsuario)) {
                        oldIdUsuarioOfSsfFichamedicaListNewSsfFichamedica.getSsfFichamedicaList().remove(ssfFichamedicaListNewSsfFichamedica);
                        oldIdUsuarioOfSsfFichamedicaListNewSsfFichamedica = em.merge(oldIdUsuarioOfSsfFichamedicaListNewSsfFichamedica);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfUsuario.getId();
                if (findSsfUsuario(id) == null) {
                    throw new NonexistentEntityException("The ssfUsuario with id " + id + " no longer exists.");
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
            SsfUsuario ssfUsuario;
            try {
                ssfUsuario = em.getReference(SsfUsuario.class, id);
                ssfUsuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfUsuario with id " + id + " no longer exists.", enfe);
            }
            SsfEmpresa idEmpresa = ssfUsuario.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getSsfUsuarioList().remove(ssfUsuario);
                idEmpresa = em.merge(idEmpresa);
            }
            SsfPerfil idPerfil = ssfUsuario.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getSsfUsuarioList().remove(ssfUsuario);
                idPerfil = em.merge(idPerfil);
            }
            SsfPersona idPersona = ssfUsuario.getIdPersona();
            if (idPersona != null) {
                idPersona.setSsfUsuario(null);
                idPersona = em.merge(idPersona);
            }
            List<SsfCapacitacionempresa> ssfCapacitacionempresaList = ssfUsuario.getSsfCapacitacionempresaList();
            for (SsfCapacitacionempresa ssfCapacitacionempresaListSsfCapacitacionempresa : ssfCapacitacionempresaList) {
                ssfCapacitacionempresaListSsfCapacitacionempresa.setIdUsuario(null);
                ssfCapacitacionempresaListSsfCapacitacionempresa = em.merge(ssfCapacitacionempresaListSsfCapacitacionempresa);
            }
            List<SsfAtencionmedica> ssfAtencionmedicaList = ssfUsuario.getSsfAtencionmedicaList();
            for (SsfAtencionmedica ssfAtencionmedicaListSsfAtencionmedica : ssfAtencionmedicaList) {
                ssfAtencionmedicaListSsfAtencionmedica.setIdUsuario(null);
                ssfAtencionmedicaListSsfAtencionmedica = em.merge(ssfAtencionmedicaListSsfAtencionmedica);
            }
            List<SsfFichamedica> ssfFichamedicaList = ssfUsuario.getSsfFichamedicaList();
            for (SsfFichamedica ssfFichamedicaListSsfFichamedica : ssfFichamedicaList) {
                ssfFichamedicaListSsfFichamedica.setIdUsuario(null);
                ssfFichamedicaListSsfFichamedica = em.merge(ssfFichamedicaListSsfFichamedica);
            }
            em.remove(ssfUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfUsuario> findSsfUsuarioEntities() {
        return findSsfUsuarioEntities(true, -1, -1);
    }

    public List<SsfUsuario> findSsfUsuarioEntities(int maxResults, int firstResult) {
        return findSsfUsuarioEntities(false, maxResults, firstResult);
    }

    private List<SsfUsuario> findSsfUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfUsuario.class));
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

    public SsfUsuario findSsfUsuario(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfUsuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfUsuario> rt = cq.from(SsfUsuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
