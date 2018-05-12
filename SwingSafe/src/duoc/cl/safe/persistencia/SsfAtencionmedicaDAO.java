/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfAtencionmedica;
import duoc.cl.safe.jpa.SsfAtencionmedicaJpaController;
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
public class SsfAtencionmedicaDAO {
    private static Logger log = Logger.getLogger(SsfAtencionmedicaDAO.class.getName());
    
    public boolean add(SsfAtencionmedica atencionmedica){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            service.create(atencionmedica);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfAtencionmedica atencionmedica){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            service.edit(atencionmedica);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfAtencionmedica find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            SsfAtencionmedica atencionmedica = service.findSsfAtencionmedica(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return atencionmedica;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfAtencionmedica> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAtencionmedicaJpaController service = new SsfAtencionmedicaJpaController(emf);
            em.getTransaction().begin();
            List<SsfAtencionmedica> lista = service.findSsfAtencionmedicaEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfAtencionmedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
