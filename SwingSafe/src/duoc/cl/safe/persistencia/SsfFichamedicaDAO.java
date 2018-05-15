/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfFichamedica;
import duoc.cl.safe.jpa.SsfFichamedicaJpaController;
import java.math.BigDecimal;
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
public class SsfFichamedicaDAO {
    private static Logger log = Logger.getLogger(SsfFichamedicaDAO.class.getName());
    
    public boolean add(SsfFichamedica fichamedica){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            service.create(fichamedica);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfFichamedica fichamedica){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            service.edit(fichamedica);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfFichamedica find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            SsfFichamedica fichamedica = service.findSsfFichamedica(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return fichamedica;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfFichamedica> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            List<SsfFichamedica> lista = service.findSsfFichamedicaEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
    
    public SsfFichamedica findSP(int id) {
        SsfFichamedica objFichamedica = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedica.sp_find", SsfFichamedica.class);
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
            List<SsfFichamedica> fichamedicas = (List<SsfFichamedica>) storedProcedure.getOutputParameterValue("o_data");
            objFichamedica = fichamedicas.get(0);

            return objFichamedica;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfFichamedica> getAllSP() {
        List<SsfFichamedica> fichamedicas = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedica.sp_getAll", SsfFichamedica.class);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            fichamedicas = (List<SsfFichamedica>) storedProcedure.getOutputParameterValue("o_data");

            return fichamedicas;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }

    public boolean addSP(SsfFichamedica fichamedica) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedica.sp_add", SsfFichamedica.class);
            storedProcedure.registerStoredProcedureParameter("p_examen", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_usuario", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_id", BigDecimal.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_examen", fichamedica.getIdExamen().getId());
            storedProcedure.setParameter("p_usuario", fichamedica.getIdUsuario().getId());
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
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }

    public boolean updateSP(SsfFichamedica fichamedica) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedica.sp_update", SsfFichamedica.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_examen", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_usuario", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", fichamedica.getId());
            storedProcedure.setParameter("p_examen", fichamedica.getIdExamen().getId());
            storedProcedure.setParameter("p_usuario", fichamedica.getIdUsuario().getId());
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
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }

    public boolean removeSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedica.sp_delete", SsfFichamedica.class);
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
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }

    public boolean desactivarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedica.sp_desactivar", SsfFichamedica.class);
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
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al desactivar", ex);
            return false;
        }
    }

    public boolean activarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfFichamedica.sp_activar", SsfFichamedica.class);
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
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al activar", ex);
            return false;
        }
    }
    
    
    
}
