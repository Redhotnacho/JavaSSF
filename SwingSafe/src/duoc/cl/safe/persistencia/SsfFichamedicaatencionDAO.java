/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfFichamedicaatencion;
import duoc.cl.safe.jpa.SsfFichamedicaatencionJpaController;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
}
