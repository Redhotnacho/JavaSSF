/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfEmpresa;
import duoc.cl.safe.jpa.SsfEmpresaJpaController;
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
public class SsfEmpresaDAO {

    private static Logger log = Logger.getLogger(SsfEmpresaDAO.class.getName());

    public boolean add(SsfEmpresa empresa) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEmpresaJpaController service = new SsfEmpresaJpaController(emf);
            em.getTransaction().begin();
            service.create(empresa);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al agregar", ex);
            return false;
        }
    }

    public boolean update(SsfEmpresa empresa) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEmpresaJpaController service = new SsfEmpresaJpaController(emf);
            em.getTransaction().begin();
            service.edit(empresa);
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
            SsfEmpresaJpaController service = new SsfEmpresaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al borrar", ex);
            return false;
        }
    }

    public SsfEmpresa find(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEmpresaJpaController service = new SsfEmpresaJpaController(emf);
            em.getTransaction().begin();
            SsfEmpresa empresa = service.findSsfEmpresa(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return empresa;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfEmpresa> getAll() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEmpresaJpaController service = new SsfEmpresaJpaController(emf);
            em.getTransaction().begin();
            List<SsfEmpresa> lista = service.findSsfEmpresaEntities();
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

    public SsfEmpresa findSP(int id) {
        SsfEmpresa objEmpresa = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfEmpresa.sp_find", SsfEmpresa.class);
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
            List<SsfEmpresa> empresas = (List<SsfEmpresa>) storedProcedure.getOutputParameterValue("o_data");

            if (!empresas.isEmpty()) {
                objEmpresa = empresas.get(0);
            }

            return objEmpresa;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            log.log(Level.ERROR, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfEmpresa> getAllSP() {
        List<SsfEmpresa> empresas = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfEmpresa.sp_getAll", SsfEmpresa.class);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            empresas = (List<SsfEmpresa>) storedProcedure.getOutputParameterValue("o_data");
            empresas.forEach((r) -> {
                em.refresh(r);
            });
            return empresas;
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            log.log(Level.ERROR, "Error al buscar elementos", ex);
            return null;
        }
    }

    public boolean addSP(SsfEmpresa empresa) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfEmpresa.sp_add", SsfEmpresa.class);
            storedProcedure.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_telefono", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_id", BigDecimal.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_nombre", empresa.getNombre());
            storedProcedure.setParameter("p_telefono", empresa.getTelefono());
            storedProcedure.setParameter("p_direccion", empresa.getDireccion());
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
            log.log(Level.ERROR, "Error al agregar", ex);
            return false;
        }
    }

    public boolean updateSP(SsfEmpresa empresa) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfEmpresa.sp_update", SsfEmpresa.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_nombre", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_telefono", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_direccion", String.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", empresa.getId());
            storedProcedure.setParameter("p_nombre", empresa.getNombre());
            storedProcedure.setParameter("p_telefono", empresa.getTelefono());
            storedProcedure.setParameter("p_direccion", empresa.getDireccion());
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
            log.log(Level.ERROR, "Error al modificar", ex);
            return false;
        }
    }

    public boolean removeSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfEmpresa.sp_delete", SsfEmpresa.class);
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
            log.log(Level.ERROR, "Error al borrar", ex);
            return false;
        }
    }

    public boolean desactivarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfEmpresa.sp_desactivar", SsfEmpresa.class);
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
            log.log(Level.ERROR, "Error al desactivar", ex);
            return false;
        }
    }

    public boolean activarSP(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfEmpresa.sp_activar", SsfEmpresa.class);
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
            log.log(Level.ERROR, "Error al activar", ex);
            return false;
        }
    }

}
