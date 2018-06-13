/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.jpa;

import duoc.cl.safe.entity.SsfPerfil;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import duoc.cl.safe.entity.SsfPerfilvista;
import java.util.ArrayList;
import java.util.List;
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
public class SsfPerfilJpaController implements Serializable {

    public SsfPerfilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SsfPerfil ssfPerfil) throws PreexistingEntityException, Exception {
        if (ssfPerfil.getSsfPerfilvistaList() == null) {
            ssfPerfil.setSsfPerfilvistaList(new ArrayList<SsfPerfilvista>());
        }
        if (ssfPerfil.getSsfUsuarioList() == null) {
            ssfPerfil.setSsfUsuarioList(new ArrayList<SsfUsuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<SsfPerfilvista> attachedSsfPerfilvistaList = new ArrayList<SsfPerfilvista>();
            for (SsfPerfilvista ssfPerfilvistaListSsfPerfilvistaToAttach : ssfPerfil.getSsfPerfilvistaList()) {
                ssfPerfilvistaListSsfPerfilvistaToAttach = em.getReference(ssfPerfilvistaListSsfPerfilvistaToAttach.getClass(), ssfPerfilvistaListSsfPerfilvistaToAttach.getId());
                attachedSsfPerfilvistaList.add(ssfPerfilvistaListSsfPerfilvistaToAttach);
            }
            ssfPerfil.setSsfPerfilvistaList(attachedSsfPerfilvistaList);
            List<SsfUsuario> attachedSsfUsuarioList = new ArrayList<SsfUsuario>();
            for (SsfUsuario ssfUsuarioListSsfUsuarioToAttach : ssfPerfil.getSsfUsuarioList()) {
                ssfUsuarioListSsfUsuarioToAttach = em.getReference(ssfUsuarioListSsfUsuarioToAttach.getClass(), ssfUsuarioListSsfUsuarioToAttach.getId());
                attachedSsfUsuarioList.add(ssfUsuarioListSsfUsuarioToAttach);
            }
            ssfPerfil.setSsfUsuarioList(attachedSsfUsuarioList);
            em.persist(ssfPerfil);
            for (SsfPerfilvista ssfPerfilvistaListSsfPerfilvista : ssfPerfil.getSsfPerfilvistaList()) {
                SsfPerfil oldIdPerfilOfSsfPerfilvistaListSsfPerfilvista = ssfPerfilvistaListSsfPerfilvista.getIdPerfil();
                ssfPerfilvistaListSsfPerfilvista.setIdPerfil(ssfPerfil);
                ssfPerfilvistaListSsfPerfilvista = em.merge(ssfPerfilvistaListSsfPerfilvista);
                if (oldIdPerfilOfSsfPerfilvistaListSsfPerfilvista != null) {
                    oldIdPerfilOfSsfPerfilvistaListSsfPerfilvista.getSsfPerfilvistaList().remove(ssfPerfilvistaListSsfPerfilvista);
                    oldIdPerfilOfSsfPerfilvistaListSsfPerfilvista = em.merge(oldIdPerfilOfSsfPerfilvistaListSsfPerfilvista);
                }
            }
            for (SsfUsuario ssfUsuarioListSsfUsuario : ssfPerfil.getSsfUsuarioList()) {
                SsfPerfil oldIdPerfilOfSsfUsuarioListSsfUsuario = ssfUsuarioListSsfUsuario.getIdPerfil();
                ssfUsuarioListSsfUsuario.setIdPerfil(ssfPerfil);
                ssfUsuarioListSsfUsuario = em.merge(ssfUsuarioListSsfUsuario);
                if (oldIdPerfilOfSsfUsuarioListSsfUsuario != null) {
                    oldIdPerfilOfSsfUsuarioListSsfUsuario.getSsfUsuarioList().remove(ssfUsuarioListSsfUsuario);
                    oldIdPerfilOfSsfUsuarioListSsfUsuario = em.merge(oldIdPerfilOfSsfUsuarioListSsfUsuario);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSsfPerfil(ssfPerfil.getId()) != null) {
                throw new PreexistingEntityException("SsfPerfil " + ssfPerfil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SsfPerfil ssfPerfil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SsfPerfil persistentSsfPerfil = em.find(SsfPerfil.class, ssfPerfil.getId());
            List<SsfPerfilvista> ssfPerfilvistaListOld = persistentSsfPerfil.getSsfPerfilvistaList();
            List<SsfPerfilvista> ssfPerfilvistaListNew = ssfPerfil.getSsfPerfilvistaList();
            List<SsfUsuario> ssfUsuarioListOld = persistentSsfPerfil.getSsfUsuarioList();
            List<SsfUsuario> ssfUsuarioListNew = ssfPerfil.getSsfUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (SsfUsuario ssfUsuarioListOldSsfUsuario : ssfUsuarioListOld) {
                if (!ssfUsuarioListNew.contains(ssfUsuarioListOldSsfUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SsfUsuario " + ssfUsuarioListOldSsfUsuario + " since its idPerfil field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SsfPerfilvista> attachedSsfPerfilvistaListNew = new ArrayList<SsfPerfilvista>();
            for (SsfPerfilvista ssfPerfilvistaListNewSsfPerfilvistaToAttach : ssfPerfilvistaListNew) {
                ssfPerfilvistaListNewSsfPerfilvistaToAttach = em.getReference(ssfPerfilvistaListNewSsfPerfilvistaToAttach.getClass(), ssfPerfilvistaListNewSsfPerfilvistaToAttach.getId());
                attachedSsfPerfilvistaListNew.add(ssfPerfilvistaListNewSsfPerfilvistaToAttach);
            }
            ssfPerfilvistaListNew = attachedSsfPerfilvistaListNew;
            ssfPerfil.setSsfPerfilvistaList(ssfPerfilvistaListNew);
            List<SsfUsuario> attachedSsfUsuarioListNew = new ArrayList<SsfUsuario>();
            for (SsfUsuario ssfUsuarioListNewSsfUsuarioToAttach : ssfUsuarioListNew) {
                ssfUsuarioListNewSsfUsuarioToAttach = em.getReference(ssfUsuarioListNewSsfUsuarioToAttach.getClass(), ssfUsuarioListNewSsfUsuarioToAttach.getId());
                attachedSsfUsuarioListNew.add(ssfUsuarioListNewSsfUsuarioToAttach);
            }
            ssfUsuarioListNew = attachedSsfUsuarioListNew;
            ssfPerfil.setSsfUsuarioList(ssfUsuarioListNew);
            ssfPerfil = em.merge(ssfPerfil);
            for (SsfPerfilvista ssfPerfilvistaListOldSsfPerfilvista : ssfPerfilvistaListOld) {
                if (!ssfPerfilvistaListNew.contains(ssfPerfilvistaListOldSsfPerfilvista)) {
                    ssfPerfilvistaListOldSsfPerfilvista.setIdPerfil(null);
                    ssfPerfilvistaListOldSsfPerfilvista = em.merge(ssfPerfilvistaListOldSsfPerfilvista);
                }
            }
            for (SsfPerfilvista ssfPerfilvistaListNewSsfPerfilvista : ssfPerfilvistaListNew) {
                if (!ssfPerfilvistaListOld.contains(ssfPerfilvistaListNewSsfPerfilvista)) {
                    SsfPerfil oldIdPerfilOfSsfPerfilvistaListNewSsfPerfilvista = ssfPerfilvistaListNewSsfPerfilvista.getIdPerfil();
                    ssfPerfilvistaListNewSsfPerfilvista.setIdPerfil(ssfPerfil);
                    ssfPerfilvistaListNewSsfPerfilvista = em.merge(ssfPerfilvistaListNewSsfPerfilvista);
                    if (oldIdPerfilOfSsfPerfilvistaListNewSsfPerfilvista != null && !oldIdPerfilOfSsfPerfilvistaListNewSsfPerfilvista.equals(ssfPerfil)) {
                        oldIdPerfilOfSsfPerfilvistaListNewSsfPerfilvista.getSsfPerfilvistaList().remove(ssfPerfilvistaListNewSsfPerfilvista);
                        oldIdPerfilOfSsfPerfilvistaListNewSsfPerfilvista = em.merge(oldIdPerfilOfSsfPerfilvistaListNewSsfPerfilvista);
                    }
                }
            }
            for (SsfUsuario ssfUsuarioListNewSsfUsuario : ssfUsuarioListNew) {
                if (!ssfUsuarioListOld.contains(ssfUsuarioListNewSsfUsuario)) {
                    SsfPerfil oldIdPerfilOfSsfUsuarioListNewSsfUsuario = ssfUsuarioListNewSsfUsuario.getIdPerfil();
                    ssfUsuarioListNewSsfUsuario.setIdPerfil(ssfPerfil);
                    ssfUsuarioListNewSsfUsuario = em.merge(ssfUsuarioListNewSsfUsuario);
                    if (oldIdPerfilOfSsfUsuarioListNewSsfUsuario != null && !oldIdPerfilOfSsfUsuarioListNewSsfUsuario.equals(ssfPerfil)) {
                        oldIdPerfilOfSsfUsuarioListNewSsfUsuario.getSsfUsuarioList().remove(ssfUsuarioListNewSsfUsuario);
                        oldIdPerfilOfSsfUsuarioListNewSsfUsuario = em.merge(oldIdPerfilOfSsfUsuarioListNewSsfUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                BigDecimal id = ssfPerfil.getId();
                if (findSsfPerfil(id) == null) {
                    throw new NonexistentEntityException("The ssfPerfil with id " + id + " no longer exists.");
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
            SsfPerfil ssfPerfil;
            try {
                ssfPerfil = em.getReference(SsfPerfil.class, id);
                ssfPerfil.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ssfPerfil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SsfUsuario> ssfUsuarioListOrphanCheck = ssfPerfil.getSsfUsuarioList();
            for (SsfUsuario ssfUsuarioListOrphanCheckSsfUsuario : ssfUsuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SsfPerfil (" + ssfPerfil + ") cannot be destroyed since the SsfUsuario " + ssfUsuarioListOrphanCheckSsfUsuario + " in its ssfUsuarioList field has a non-nullable idPerfil field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SsfPerfilvista> ssfPerfilvistaList = ssfPerfil.getSsfPerfilvistaList();
            for (SsfPerfilvista ssfPerfilvistaListSsfPerfilvista : ssfPerfilvistaList) {
                ssfPerfilvistaListSsfPerfilvista.setIdPerfil(null);
                ssfPerfilvistaListSsfPerfilvista = em.merge(ssfPerfilvistaListSsfPerfilvista);
            }
            em.remove(ssfPerfil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SsfPerfil> findSsfPerfilEntities() {
        return findSsfPerfilEntities(true, -1, -1);
    }

    public List<SsfPerfil> findSsfPerfilEntities(int maxResults, int firstResult) {
        return findSsfPerfilEntities(false, maxResults, firstResult);
    }

    private List<SsfPerfil> findSsfPerfilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SsfPerfil.class));
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

    public SsfPerfil findSsfPerfil(BigDecimal id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SsfPerfil.class, id);
        } finally {
            em.close();
        }
    }

    public int getSsfPerfilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SsfPerfil> rt = cq.from(SsfPerfil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
