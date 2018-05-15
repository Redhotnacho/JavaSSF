/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.jpa.SsfUsuarioJpaController;
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
public class SsfUsuarioDAO {
    private static Logger log = Logger.getLogger(SsfUsuarioDAO.class.getName());
    
    public boolean add(SsfUsuario usuario){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfUsuarioJpaController service = new SsfUsuarioJpaController(emf);
            em.getTransaction().begin();
            service.create(usuario);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfUsuario usuario){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfUsuarioJpaController service = new SsfUsuarioJpaController(emf);
            em.getTransaction().begin();
            service.edit(usuario);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfUsuarioJpaController service = new SsfUsuarioJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfUsuario find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfUsuarioJpaController service = new SsfUsuarioJpaController(emf);
            em.getTransaction().begin();
            SsfUsuario usuario = service.findSsfUsuario(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return usuario;
        } catch (Exception ex) {
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfUsuario> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfUsuarioJpaController service = new SsfUsuarioJpaController(emf);
            em.getTransaction().begin();
            List<SsfUsuario> lista = service.findSsfUsuarioEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
    
    public SsfUsuario findSP(int id) {
        SsfUsuario objUsuario = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfUsuario.sp_find", SsfUsuario.class);
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
            List<SsfUsuario> usuarios = (List<SsfUsuario>) storedProcedure.getOutputParameterValue("o_data");
            objUsuario = usuarios.get(0);

            return objUsuario;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfUsuario> getAllSP() {
        List<SsfUsuario> usuarios = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfUsuario.sp_getAll", SsfUsuario.class);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            usuarios = (List<SsfUsuario>) storedProcedure.getOutputParameterValue("o_data");

            return usuarios;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }

    public boolean addSP(SsfUsuario usuario) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfUsuario.sp_add", SsfUsuario.class);
            storedProcedure.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_persona", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_perfil", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_empresa", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_id", BigDecimal.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_username", usuario.getUsername());
            storedProcedure.setParameter("p_contrasena", usuario.getContrasena());
            storedProcedure.setParameter("p_persona", usuario.getIdPersona().getId());
            storedProcedure.setParameter("p_perfil", usuario.getIdPerfil().getId());
            storedProcedure.setParameter("p_empresa", usuario.getIdEmpresa().getId());
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
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }

    public boolean updateSP(SsfUsuario usuario) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfUsuario.sp_update", SsfUsuario.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_persona", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_perfil", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_empresa", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", usuario.getId());
            storedProcedure.setParameter("p_username", usuario.getUsername());
            storedProcedure.setParameter("p_contrasena", usuario.getContrasena());
            storedProcedure.setParameter("p_persona", usuario.getIdPersona().getId());
            storedProcedure.setParameter("p_perfil", usuario.getIdPerfil().getId());
            storedProcedure.setParameter("p_empresa", usuario.getIdEmpresa().getId());
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
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }

    public boolean removeSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfUsuario.sp_delete", SsfUsuario.class);
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
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }

    public boolean desactivarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfUsuario.sp_desactivar", SsfUsuario.class);
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
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al desactivar", ex);
            return false;
        }
    }

    public boolean activarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfUsuario.sp_activar", SsfUsuario.class);
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
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al activar", ex);
            return false;
        }
    }
    
    
    public SsfUsuario validaUsuarioSP(String username, String contrasena) {
        SsfUsuario objUsuario = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfUsuario.sp_validaUsuario", SsfUsuario.class);
            storedProcedure.registerStoredProcedureParameter("p_username", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_contrasena", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.setParameter("p_username", username);
            storedProcedure.setParameter("p_contrasena", contrasena);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            Short o_estado = (Short) storedProcedure.getOutputParameterValue("o_estado");
            System.out.println("o_glosa : " + o_glosa);
            System.out.println("o_estado : " + o_estado);
            List<SsfUsuario> usuarios = (List<SsfUsuario>) storedProcedure.getOutputParameterValue("o_data");
            objUsuario = usuarios.get(0);

            if (o_glosa.contains("xito")) {
                return objUsuario;
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            Logger.getLogger(SsfUsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al validar usuario", ex);
            return null;
        }
    }
    
}
