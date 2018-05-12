/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfCapacitaciondia;
import duoc.cl.safe.jpa.SsfCapacitaciondiaJpaController;
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
public class SsfCapacitaciondiaDAO {
    private static Logger log = Logger.getLogger(SsfCapacitaciondiaDAO.class.getName());
    
    public boolean add(SsfCapacitaciondia capacitaciondia){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciondiaJpaController service = new SsfCapacitaciondiaJpaController(emf);
            em.getTransaction().begin();
            service.create(capacitaciondia);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciondiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfCapacitaciondia capacitaciondia){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciondiaJpaController service = new SsfCapacitaciondiaJpaController(emf);
            em.getTransaction().begin();
            service.edit(capacitaciondia);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciondiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciondiaJpaController service = new SsfCapacitaciondiaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciondiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfCapacitaciondia find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciondiaJpaController service = new SsfCapacitaciondiaJpaController(emf);
            em.getTransaction().begin();
            SsfCapacitaciondia capacitaciondia = service.findSsfCapacitaciondia(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return capacitaciondia;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciondiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfCapacitaciondia> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciondiaJpaController service = new SsfCapacitaciondiaJpaController(emf);
            em.getTransaction().begin();
            List<SsfCapacitaciondia> lista = service.findSsfCapacitaciondiaEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciondiaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
