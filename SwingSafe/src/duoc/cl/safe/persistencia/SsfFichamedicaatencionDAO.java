/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfFichamedicaatencion;
import duoc.cl.safe.jpa.SsfFichamedicaatencionJpaController;
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
public class SsfFichamedicaatencionDAO {
    private static Logger log = Logger.getLogger(SsfFichamedicaatencionDAO.class.getName());
    
    public boolean add(SsfFichamedicaatencion fichamedicaatencion){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaatencionJpaController service = new SsfFichamedicaatencionJpaController(emf);
            em.getTransaction().begin();
            service.create(fichamedicaatencion);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfFichamedicaatencion fichamedicaatencion){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaatencionJpaController service = new SsfFichamedicaatencionJpaController(emf);
            em.getTransaction().begin();
            service.edit(fichamedicaatencion);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaatencionJpaController service = new SsfFichamedicaatencionJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfFichamedicaatencion find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaatencionJpaController service = new SsfFichamedicaatencionJpaController(emf);
            em.getTransaction().begin();
            SsfFichamedicaatencion fichamedicaatencion = service.findSsfFichamedicaatencion(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return fichamedicaatencion;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfFichamedicaatencion> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaatencionJpaController service = new SsfFichamedicaatencionJpaController(emf);
            em.getTransaction().begin();
            List<SsfFichamedicaatencion> lista = service.findSsfFichamedicaatencionEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
    
    public SsfFichamedicaatencion findSP(int id) {
        SsfFichamedicaatencion objFichamedicaatencion = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedicaatencion.sp_find", SsfFichamedicaatencion.class);
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
            List<SsfFichamedicaatencion> fichamedicatenciones = (List<SsfFichamedicaatencion>) storedProcedure.getOutputParameterValue("o_data");
            objFichamedicaatencion = fichamedicatenciones.get(0);

            return objFichamedicaatencion;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfFichamedicaatencion> getAllSP() {
        List<SsfFichamedicaatencion> fichamedicatenciones = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedicaatencion.sp_getAll", SsfFichamedicaatencion.class);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            fichamedicatenciones = (List<SsfFichamedicaatencion>) storedProcedure.getOutputParameterValue("o_data");

            return fichamedicatenciones;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }

    public boolean addSP(SsfFichamedicaatencion fichamedicatencion) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedicaatencion.sp_add", SsfFichamedicaatencion.class);
            storedProcedure.registerStoredProcedureParameter("p_atencionmedica", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fichamedica", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fecha_atencion", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_id", BigDecimal.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_atencionmedica", fichamedicatencion.getIdAtencionmedica().getId());
            storedProcedure.setParameter("p_fichamedica", fichamedicatencion.getIdFichamedica().getId());
            storedProcedure.setParameter("p_fecha_atencion", fichamedicatencion.getFechaAtencion());
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
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }

    public boolean updateSP(SsfFichamedicaatencion fichamedicatencion) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedicaatencion.sp_update", SsfFichamedicaatencion.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_atencionmedica", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fichamedica", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_fecha_atencion", Date.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", fichamedicatencion.getId());
            storedProcedure.setParameter("p_atencionmedica", fichamedicatencion.getIdAtencionmedica().getId());
            storedProcedure.setParameter("p_fichamedica", fichamedicatencion.getIdFichamedica().getId());
            storedProcedure.setParameter("p_fecha_atencion", fichamedicatencion.getFechaAtencion());
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
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }

    public boolean removeSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedicaatencion.sp_delete", SsfFichamedicaatencion.class);
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
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }

    public boolean desactivarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedicaatencion.sp_desactivar", SsfFichamedicaatencion.class);
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
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al desactivar", ex);
            return false;
        }
    }

    public boolean activarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedicaatencion.sp_activar", SsfFichamedicaatencion.class);
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
            Logger.getLogger(SsfFichamedicaatencionDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al activar", ex);
            return false;
        }
    }
    
}
