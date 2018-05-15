/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfCapacitacion;
import duoc.cl.safe.jpa.SsfCapacitacionJpaController;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;

/**
 *
 * @author Nacho
 */
public class SsfCapacitacionDAO {
    private static Logger log = Logger.getLogger(SsfCapacitacionDAO.class.getName());
    
    public boolean add(SsfCapacitacion capacitacion){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionJpaController service = new SsfCapacitacionJpaController(emf);
            em.getTransaction().begin();
            service.create(capacitacion);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfCapacitacion capacitacion){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionJpaController service = new SsfCapacitacionJpaController(emf);
            em.getTransaction().begin();
            service.edit(capacitacion);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionJpaController service = new SsfCapacitacionJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfCapacitacion find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionJpaController service = new SsfCapacitacionJpaController(emf);
            em.getTransaction().begin();
            SsfCapacitacion capacitacion = service.findSsfCapacitacion(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return capacitacion;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfCapacitacion> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionJpaController service = new SsfCapacitacionJpaController(emf);
            em.getTransaction().begin();
            List<SsfCapacitacion> lista = service.findSsfCapacitacionEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
    
    public SsfCapacitacion findSP(int id) {
        SsfCapacitacion objCapacitacion = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacion.sp_find", SsfCapacitacion.class);
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
            List<SsfCapacitacion> capacitaciones = (List<SsfCapacitacion>) storedProcedure.getOutputParameterValue("o_data");
            objCapacitacion = capacitaciones.get(0);

            return objCapacitacion;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfCapacitacion> getAllSP() {
        List<SsfCapacitacion> capacitaciones = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacion.sp_getAll", SsfCapacitacion.class);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            capacitaciones = (List<SsfCapacitacion>) storedProcedure.getOutputParameterValue("o_data");

            return capacitaciones;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }

    public boolean addSP(SsfCapacitacion capacitacion) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacion.sp_add", SsfCapacitacion.class);
            storedProcedure.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_horas", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_capatipo", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fechainicio", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fechatermino", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_id", BigDecimal.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_horas", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_capatipo", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fechainicio", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fechatermino", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", capacitacion.getId());
            storedProcedure.setParameter("p_nombre", capacitacion.getNombre());
            storedProcedure.setParameter("p_horas", capacitacion.getHoras());
            storedProcedure.setParameter("p_capatipo", capacitacion.getIdCapacitaciontipo().getId());
            storedProcedure.setParameter("p_fechainicio", capacitacion.getFechaInicio());
            storedProcedure.setParameter("p_fechatermino", capacitacion.getFechaTermino());
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
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }

    public boolean updateSP(SsfCapacitacion capacitacion) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacion.sp_update", SsfCapacitacion.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_horas", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_capatipo", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fechainicio", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fechatermino", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", capacitacion.getId());
            storedProcedure.setParameter("p_nombre", capacitacion.getNombre());
            storedProcedure.setParameter("p_horas", capacitacion.getHoras());
            storedProcedure.setParameter("p_capatipo", capacitacion.getIdCapacitaciontipo().getId());
            storedProcedure.setParameter("p_fechainicio", capacitacion.getFechaInicio());
            storedProcedure.setParameter("p_fechatermino", capacitacion.getFechaTermino());
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
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }

    public boolean removeSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacion.sp_delete", SsfCapacitacion.class);
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
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }

    public boolean desactivarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacion.sp_desactivar", SsfCapacitacion.class);
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
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al desactivar", ex);
            return false;
        }
    }

    public boolean activarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacion.sp_activar", SsfCapacitacion.class);
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
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfCapacitacionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al activar", ex);
            return false;
        }
    }
    
    
}
