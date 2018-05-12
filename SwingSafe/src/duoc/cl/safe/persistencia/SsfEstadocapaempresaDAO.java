/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfEstadocapaempresa;
import duoc.cl.safe.jpa.SsfEstadocapaempresaJpaController;
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
public class SsfEstadocapaempresaDAO {
    private static Logger log = Logger.getLogger(SsfEstadocapaempresaDAO.class.getName());
    
    public boolean add(SsfEstadocapaempresa estadocapaempresa){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEstadocapaempresaJpaController service = new SsfEstadocapaempresaJpaController(emf);
            em.getTransaction().begin();
            service.create(estadocapaempresa);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEstadocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfEstadocapaempresa estadocapaempresa){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEstadocapaempresaJpaController service = new SsfEstadocapaempresaJpaController(emf);
            em.getTransaction().begin();
            service.edit(estadocapaempresa);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEstadocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEstadocapaempresaJpaController service = new SsfEstadocapaempresaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfEstadocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfEstadocapaempresa find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEstadocapaempresaJpaController service = new SsfEstadocapaempresaJpaController(emf);
            em.getTransaction().begin();
            SsfEstadocapaempresa estadocapaempresa = service.findSsfEstadocapaempresa(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return estadocapaempresa;
        } catch (Exception ex) {
            Logger.getLogger(SsfEstadocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfEstadocapaempresa> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfEstadocapaempresaJpaController service = new SsfEstadocapaempresaJpaController(emf);
            em.getTransaction().begin();
            List<SsfEstadocapaempresa> lista = service.findSsfEstadocapaempresaEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfEstadocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
