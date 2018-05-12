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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Nacho
 */
public class SsfCapacitacionempresaDAO {
    private static Logger log = Logger.getLogger(SsfCapacitacionempresaDAO.class.getName());
    
    public boolean add(SsfCapacitacionempresa capacitacionempresa){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            service.create(capacitacionempresa);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfCapacitacionempresa capacitacionempresa){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            service.edit(capacitacionempresa);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfCapacitacionempresa find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            SsfCapacitacionempresa capacitacionempresa = service.findSsfCapacitacionempresa(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return capacitacionempresa;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfCapacitacionempresa> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCapacitacionempresaJpaController service = new SsfCapacitacionempresaJpaController(emf);
            em.getTransaction().begin();
            List<SsfCapacitacionempresa> lista = service.findSsfCapacitacionempresaEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfCapacitacionempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
