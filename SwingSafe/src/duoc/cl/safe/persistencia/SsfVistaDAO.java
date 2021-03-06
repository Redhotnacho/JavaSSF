/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfVista;
import duoc.cl.safe.jpa.SsfVistaJpaController;
import duoc.cl.safe.jpa.exceptions.NonexistentEntityException;
import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Nacho
 */
public class SsfVistaDAO {

    private static Logger log = Logger.getLogger(SsfVistaDAO.class.getName());

    public boolean add(SsfVista vista) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfVistaJpaController service = new SsfVistaJpaController(emf);
            em.getTransaction().begin();
            service.create(vista);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al agregar", ex);
            return false;
        }
    }

    public boolean update(SsfVista vista) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfVistaJpaController service = new SsfVistaJpaController(emf);
            em.getTransaction().begin();
            service.edit(vista);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al modificar", ex);
            return false;
        }
    }

    public boolean remove(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfVistaJpaController service = new SsfVistaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (NonexistentEntityException ex) {
            log.log(Level.ERROR, "Error al borrar", ex);
            return false;
        }
    }

    public SsfVista find(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfVistaJpaController service = new SsfVistaJpaController(emf);
            em.getTransaction().begin();
            SsfVista vista = service.findSsfVista(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return vista;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfVista> getAll() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfVistaJpaController service = new SsfVistaJpaController(emf);
            em.getTransaction().begin();
            List<SsfVista> lista = service.findSsfVistaEntities();
            lista.forEach((r) -> {
                em.refresh(r);
            });
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al buscar elementos", ex);
            return null;
        }
    }

    public SsfVista findSP(int id) {
        SsfVista objVista = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfVista.sp_find", SsfVista.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", id);
            storedProcedure.execute();
            Short o_estado = (Short) storedProcedure.getOutputParameterValue("o_estado");
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            System.out.println("o_estado : " + o_estado);
            List<SsfVista> vistas = (List<SsfVista>) storedProcedure.getOutputParameterValue("o_data");

            if (!vistas.isEmpty()) {
                objVista = vistas.get(0);
            }

            return objVista;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfVista> getAllSP() {
        List<SsfVista> vistas = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfVista.sp_getAll", SsfVista.class);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            vistas = (List<SsfVista>) storedProcedure.getOutputParameterValue("o_data");
            vistas.forEach((r) -> {
                em.refresh(r);
            });
            return vistas;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al buscar elementos", ex);
            return null;
        }
    }

    public boolean addSP(SsfVista vista) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfVista.sp_add", SsfVista.class);
            storedProcedure.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_url", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_menu", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_id", BigDecimal.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_nombre", vista.getNombre());
            storedProcedure.setParameter("p_url", vista.getUrl());
            storedProcedure.setParameter("p_menu", vista.getIdMenu().getId());
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            Short o_estado = (Short) storedProcedure.getOutputParameterValue("o_estado");
            BigDecimal o_id = (BigDecimal) storedProcedure.getOutputParameterValue("o_id");
            System.out.println("o_glosa : " + o_glosa);
            System.out.println("o_estado : " + o_estado);
            System.out.println("o_id : " + o_id);
            if (o_glosa.contains("xito")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al agregar", ex);
            return false;
        }
    }

    public boolean updateSP(SsfVista vista) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfVista.sp_update", SsfVista.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_url", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_menu", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", vista.getId());
            storedProcedure.setParameter("p_nombre", vista.getNombre());
            storedProcedure.setParameter("p_url", vista.getUrl());
            storedProcedure.setParameter("p_menu", vista.getIdMenu().getId());
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            Short o_estado = (Short) storedProcedure.getOutputParameterValue("o_estado");
            System.out.println("o_glosa : " + o_glosa);
            System.out.println("o_estado : " + o_estado);
            if (o_glosa.contains("xito")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al modificar", ex);
            return false;
        }
    }

    public boolean removeSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfVista.sp_delete", SsfVista.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", id);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            if (o_glosa.contains("xito")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al borrar", ex);
            return false;
        }
    }

    public boolean desactivarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfVista.sp_desactivar", SsfVista.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", id);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            Short o_estado = (Short) storedProcedure.getOutputParameterValue("o_estado");
            System.out.println("o_glosa : " + o_glosa);
            System.out.println("o_estado : " + o_estado);
            if (o_glosa.contains("xito")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al desactivar", ex);
            return false;
        }
    }

    public boolean activarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfVista.sp_activar", SsfVista.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", id);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            Short o_estado = (Short) storedProcedure.getOutputParameterValue("o_estado");
            System.out.println("o_glosa : " + o_glosa);
            System.out.println("o_estado : " + o_estado);
            if (o_glosa.contains("xito")) {
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al activar", ex);
            return false;
        }
    }

}
