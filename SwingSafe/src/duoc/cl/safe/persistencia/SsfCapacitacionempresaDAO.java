/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfCapacitacionempresa;
import duoc.cl.safe.jpa.SsfCapacitacionempresaJpaController;
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
public class SsfCapacitacionempresaDAO {

    private static Logger log = Logger.getLogger(SsfCapacitacionempresaDAO.class.getName());

    public boolean add(SsfCapacitacionempresa capacitacionempresa) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            service.create(capacitacionempresa);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al agregar", ex);
            return false;
        }
    }

    public boolean update(SsfCapacitacionempresa capacitacionempresa) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            service.edit(capacitacionempresa);
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
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al borrar", ex);
            return false;
        }
    }

    public SsfCapacitacionempresa find(int id) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            SsfCapacitacionempresa capacitacionempresa = service.findSsfCapacitacionempresa(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return capacitacionempresa;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfCapacitacionempresa> getAll() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            List<SsfCapacitacionempresa> lista = service.findSsfCapacitacionempresaEntities();
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

    public SsfCapacitacionempresa findSP(int id) {
        SsfCapacitacionempresa objCapacitacionempresa = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacionempresa.sp_find", SsfCapacitacionempresa.class);
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
            List<SsfCapacitacionempresa> capacitacionempresas = (List<SsfCapacitacionempresa>) storedProcedure.getOutputParameterValue("o_data");

            if (!capacitacionempresas.isEmpty()) {
                objCapacitacionempresa = capacitacionempresas.get(0);
            }

            return objCapacitacionempresa;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al buscar", ex);
            return null;
        }
    }

    public List<SsfCapacitacionempresa> getAllSP() {
        List<SsfCapacitacionempresa> capacitacionempresas = null;
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacionempresa.sp_getAll", SsfCapacitacionempresa.class);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_data", void.class, ParameterMode.REF_CURSOR);
            storedProcedure.execute();
            String o_glosa = (String) storedProcedure.getOutputParameterValue("o_glosa");
            System.out.println("o_glosa : " + o_glosa);
            capacitacionempresas = (List<SsfCapacitacionempresa>) storedProcedure.getOutputParameterValue("o_data");
            capacitacionempresas.forEach((r) -> {
                em.refresh(r);
            });
            return capacitacionempresas;
        } catch (Exception ex) {
            log.log(Level.ERROR, "Error al buscar elementos", ex);
            return null;
        }
    }

    public boolean addSP(SsfCapacitacionempresa capacitacionempresa) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacionempresa.sp_add", SsfCapacitacionempresa.class);
            storedProcedure.registerStoredProcedureParameter("p_capacitacion", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_empresa", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_estadocapa", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_usuario", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_cantalumnos", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_id", BigDecimal.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_capacitacion", capacitacionempresa.getIdCapacitacion().getId());
            storedProcedure.setParameter("p_empresa", capacitacionempresa.getIdEmpresa().getId());
            storedProcedure.setParameter("p_estadocapa", capacitacionempresa.getIdEstadocapacitacion().getId());
            storedProcedure.setParameter("p_usuario", capacitacionempresa.getIdUsuario().getId());
            storedProcedure.setParameter("p_cantalumnos", capacitacionempresa.getCantidadAlumnos());
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

    public boolean updateSP(SsfCapacitacionempresa capacitacionempresa) {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacionempresa.sp_update", SsfCapacitacionempresa.class);
            storedProcedure.registerStoredProcedureParameter("p_id", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_capacitacion", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_empresa", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_estadocapa", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_usuario", BigDecimal.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("p_cantalumnos", Long.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("o_glosa", String.class, ParameterMode.OUT);
            storedProcedure.registerStoredProcedureParameter("o_estado", Short.class, ParameterMode.OUT);
            storedProcedure.setParameter("p_id", capacitacionempresa.getId());
            storedProcedure.setParameter("p_capacitacion", capacitacionempresa.getIdCapacitacion().getId());
            storedProcedure.setParameter("p_empresa", capacitacionempresa.getIdEmpresa().getId());
            storedProcedure.setParameter("p_estadocapa", capacitacionempresa.getIdEstadocapacitacion().getId());
            storedProcedure.setParameter("p_usuario", capacitacionempresa.getIdUsuario().getId());
            storedProcedure.setParameter("p_cantalumnos", capacitacionempresa.getCantidadAlumnos());
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

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacionempresa.sp_delete", SsfCapacitacionempresa.class);
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

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacionempresa.sp_desactivar", SsfCapacitacionempresa.class);
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

            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("pkg_ssfCapacitacionempresa.sp_activar", SsfCapacitacionempresa.class);
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
