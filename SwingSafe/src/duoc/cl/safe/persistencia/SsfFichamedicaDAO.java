/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfFichamedica;
import duoc.cl.safe.jpa.SsfFichamedicaJpaController;
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
public class SsfFichamedicaDAO {
    private static Logger log = Logger.getLogger(SsfFichamedicaDAO.class.getName());
    
    public boolean add(SsfFichamedica fichamedica){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            service.create(fichamedica);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfFichamedica fichamedica){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            service.edit(fichamedica);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfFichamedica find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            SsfFichamedica fichamedica = service.findSsfFichamedica(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return fichamedica;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfFichamedica> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfFichamedicaJpaController service = new SsfFichamedicaJpaController(emf);
            em.getTransaction().begin();
            List<SsfFichamedica> lista = service.findSsfFichamedicaEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfFichamedicaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
