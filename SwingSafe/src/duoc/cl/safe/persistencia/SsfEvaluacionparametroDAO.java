/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfEvaluacionparametro;
import duoc.cl.safe.jpa.SsfEvaluacionparametroJpaController;
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
public class SsfEvaluacionparametroDAO {
    private static Logger log = Logger.getLogger(SsfEvaluacionparametroDAO.class.getName());
    
    public boolean add(SsfEvaluacionparametro evaluacionparametro){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionparametroJpaController service = new SsfEvaluacionparametroJpaController(emf);
            em.getTransaction().begin();
            service.create(evaluacionparametro);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionparametroDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfEvaluacionparametro evaluacionparametro){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionparametroJpaController service = new SsfEvaluacionparametroJpaController(emf);
            em.getTransaction().begin();
            service.edit(evaluacionparametro);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionparametroDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionparametroJpaController service = new SsfEvaluacionparametroJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionparametroDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfEvaluacionparametro find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionparametroJpaController service = new SsfEvaluacionparametroJpaController(emf);
            em.getTransaction().begin();
            SsfEvaluacionparametro evaluacionparametro = service.findSsfEvaluacionparametro(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return evaluacionparametro;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionparametroDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfEvaluacionparametro> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEvaluacionparametroJpaController service = new SsfEvaluacionparametroJpaController(emf);
            em.getTransaction().begin();
            List<SsfEvaluacionparametro> lista = service.findSsfEvaluacionparametroEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfEvaluacionparametroDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
