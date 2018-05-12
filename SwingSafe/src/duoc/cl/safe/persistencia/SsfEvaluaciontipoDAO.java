/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfEvaluaciontipo;
import duoc.cl.safe.jpa.SsfEvaluaciontipoJpaController;
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
public class SsfEvaluaciontipoDAO {
    private static Logger log = Logger.getLogger(SsfEvaluaciontipoDAO.class.getName());
    
    public boolean add(SsfEvaluaciontipo evaluaciontipo){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluaciontipoJpaController service = new SsfEvaluaciontipoJpaController(emf);
            em.getTransaction().begin();
            service.create(evaluaciontipo);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfEvaluaciontipo evaluaciontipo){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluaciontipoJpaController service = new SsfEvaluaciontipoJpaController(emf);
            em.getTransaction().begin();
            service.edit(evaluaciontipo);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluaciontipoJpaController service = new SsfEvaluaciontipoJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfEvaluaciontipo find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluaciontipoJpaController service = new SsfEvaluaciontipoJpaController(emf);
            em.getTransaction().begin();
            SsfEvaluaciontipo evaluaciontipo = service.findSsfEvaluaciontipo(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return evaluaciontipo;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfEvaluaciontipo> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluaciontipoJpaController service = new SsfEvaluaciontipoJpaController(emf);
            em.getTransaction().begin();
            List<SsfEvaluaciontipo> lista = service.findSsfEvaluaciontipoEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluaciontipoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
