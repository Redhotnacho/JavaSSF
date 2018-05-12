/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfAdjunto;
import duoc.cl.safe.jpa.SsfAdjuntoJpaController;
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
public class SsfAdjuntoDAO {
    private static Logger log = Logger.getLogger(SsfAdjuntoDAO.class.getName());
    
    public boolean add(SsfAdjunto adjunto){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAdjuntoJpaController service = new SsfAdjuntoJpaController(emf);
            em.getTransaction().begin();
            service.create(adjunto);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAdjuntoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfAdjunto adjunto){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAdjuntoJpaController service = new SsfAdjuntoJpaController(emf);
            em.getTransaction().begin();
            service.edit(adjunto);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAdjuntoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAdjuntoJpaController service = new SsfAdjuntoJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAdjuntoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfAdjunto find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAdjuntoJpaController service = new SsfAdjuntoJpaController(emf);
            em.getTransaction().begin();
            SsfAdjunto adjunto = service.findSsfAdjunto(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return adjunto;
        } catch (Exception ex) {
            Logger.getLogger(SsfAdjuntoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfAdjunto> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAdjuntoJpaController service = new SsfAdjuntoJpaController(emf);
            em.getTransaction().begin();
            List<SsfAdjunto> lista = service.findSsfAdjuntoEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfAdjuntoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
