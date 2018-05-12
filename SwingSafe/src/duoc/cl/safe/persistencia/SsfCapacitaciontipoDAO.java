/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfCapacitaciontipo;
import duoc.cl.safe.jpa.SsfCapacitaciontipoJpaController;
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
public class SsfCapacitaciontipoDAO {
    private static Logger log = Logger.getLogger(SsfCapacitaciontipoDAO.class.getName());
    
    public boolean add(SsfCapacitaciontipo capacitaciontipo){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciontipoJpaController service = new SsfCapacitaciontipoJpaController(emf);
            em.getTransaction().begin();
            service.create(capacitaciontipo);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfCapacitaciontipo capacitaciontipo){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciontipoJpaController service = new SsfCapacitaciontipoJpaController(emf);
            em.getTransaction().begin();
            service.edit(capacitaciontipo);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciontipoJpaController service = new SsfCapacitaciontipoJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfCapacitaciontipo find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciontipoJpaController service = new SsfCapacitaciontipoJpaController(emf);
            em.getTransaction().begin();
            SsfCapacitaciontipo capacitaciontipo = service.findSsfCapacitaciontipo(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return capacitaciontipo;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfCapacitaciontipo> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitaciontipoJpaController service = new SsfCapacitaciontipoJpaController(emf);
            em.getTransaction().begin();
            List<SsfCapacitaciontipo> lista = service.findSsfCapacitaciontipoEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
