/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfEvaluacionestado;
import duoc.cl.safe.jpa.SsfEvaluacionestadoJpaController;
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
public class SsfEvaluacionestadoDAO {
    private static Logger log = Logger.getLogger(SsfEvaluacionestadoDAO.class.getName());
    
    public boolean add(SsfEvaluacionestado evaluacionestado){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionestadoJpaController service = new SsfEvaluacionestadoJpaController(emf);
            em.getTransaction().begin();
            service.create(evaluacionestado);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionestadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfEvaluacionestado evaluacionestado){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionestadoJpaController service = new SsfEvaluacionestadoJpaController(emf);
            em.getTransaction().begin();
            service.edit(evaluacionestado);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionestadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionestadoJpaController service = new SsfEvaluacionestadoJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionestadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfEvaluacionestado find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionestadoJpaController service = new SsfEvaluacionestadoJpaController(emf);
            em.getTransaction().begin();
            SsfEvaluacionestado evaluacionestado = service.findSsfEvaluacionestado(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return evaluacionestado;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionestadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfEvaluacionestado> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionestadoJpaController service = new SsfEvaluacionestadoJpaController(emf);
            em.getTransaction().begin();
            List<SsfEvaluacionestado> lista = service.findSsfEvaluacionestadoEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionestadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
