/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfCentromedico;
import duoc.cl.safe.jpa.SsfCentromedicoJpaController;
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
public class SsfCentromedicoDAO {
    private static Logger log = Logger.getLogger(SsfCentromedicoDAO.class.getName());
    
    public boolean add(SsfCentromedico centromedico){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCentromedicoJpaController service = new SsfCentromedicoJpaController(emf);
            em.getTransaction().begin();
            service.create(centromedico);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCentromedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfCentromedico centromedico){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCentromedicoJpaController service = new SsfCentromedicoJpaController(emf);
            em.getTransaction().begin();
            service.edit(centromedico);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCentromedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCentromedicoJpaController service = new SsfCentromedicoJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfCentromedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfCentromedico find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCentromedicoJpaController service = new SsfCentromedicoJpaController(emf);
            em.getTransaction().begin();
            SsfCentromedico centromedico = service.findSsfCentromedico(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return centromedico;
        } catch (Exception ex) {
            Logger.getLogger(SsfCentromedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfCentromedico> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfCentromedicoJpaController service = new SsfCentromedicoJpaController(emf);
            em.getTransaction().begin();
            List<SsfCentromedico> lista = service.findSsfCentromedicoEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfCentromedicoDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}