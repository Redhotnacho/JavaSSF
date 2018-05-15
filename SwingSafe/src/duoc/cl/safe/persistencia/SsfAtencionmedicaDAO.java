/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfAtencionmedica;
import duoc.cl.safe.jpa.SsfAtencionmedicaJpaController;
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
public class SsfAtencionmedicaDAO {
    private static Logger log = Logger.getLogger(SsfAtencionmedicaDAO.class.getName());
    
    public boolean add(SsfAtencionmedica atencionmedica){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            service.create(atencionmedica);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfAtencionmedica atencionmedica){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            service.edit(atencionmedica);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfAtencionmedica find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            SsfAtencionmedica atencionmedica = service.findSsfAtencionmedica(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return atencionmedica;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfAtencionmedica> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            List<SsfAtencionmedica> lista = service.findSsfAtencionmedicaEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
    
    public SsfAtencionmedica findSP(int id) {
        SsfAtencionmedica objAtencionmedica = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfAtencionmedica.sp_find", SsfAtencionmedica.class);
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
            List<SsfAtencionmedica> atencionmedicas = (List<SsfAtencionmedica>) storedProcedure.getOutputParameterValue("o_data");
            objAtencionmedica = atencionmedicas.get(0);

            return objAtencionmedica;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfAtencionmedica> getAllSP() {
        List<SsfAtencionmedica> atencionmedicas = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfAtencionmedica.sp_getAll", SsfAtencionmedica.class);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            atencionmedicas = (List<SsfAtencionmedica>) storedProcedure.getOutputParameterValue("o_data");

            return atencionmedicas;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }

    public boolean addSP(SsfAtencionmedica atencionmedica) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfAtencionmedica.sp_add", SsfAtencionmedica.class);
            storedProcedure.registerStoredProcedureParameter("p_medico", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_usuario", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_diagnostico", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_id", BigDecimal.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_medico", atencionmedica.getIdMedico().getId());
            storedProcedure.setParameter("p_usuario", atencionmedica.getIdUsuario().getId());
            storedProcedure.setParameter("p_diagnostico", atencionmedica.getDiagnostico());
            storedProcedure.setParameter("p_descripcion", atencionmedica.getDescripcion());
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
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }

    public boolean updateSP(SsfAtencionmedica atencionmedica) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfAtencionmedica.sp_update", SsfAtencionmedica.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_medico", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_usuario", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_diagnostico", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_descripcion", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", atencionmedica.getId());
            storedProcedure.setParameter("p_medico", atencionmedica.getIdMedico().getId());
            storedProcedure.setParameter("p_usuario", atencionmedica.getIdUsuario().getId());
            storedProcedure.setParameter("p_diagnostico", atencionmedica.getDiagnostico());
            storedProcedure.setParameter("p_descripcion", atencionmedica.getDescripcion());
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
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }

    public boolean removeSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfAtencionmedica.sp_delete", SsfAtencionmedica.class);
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
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }

    public boolean desactivarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfAtencionmedica.sp_desactivar", SsfAtencionmedica.class);
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
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al desactivar", ex);
            return false;
        }
    }

    public boolean activarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfAtencionmedica.sp_activar", SsfAtencionmedica.class);
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
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al activar", ex);
            return false;
        }
    }
    
}
