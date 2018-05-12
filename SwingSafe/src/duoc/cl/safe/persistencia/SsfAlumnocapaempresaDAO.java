/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfAlumnocapaempresa;
import duoc.cl.safe.jpa.SsfAlumnocapaempresaJpaController;
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
public class SsfAlumnocapaempresaDAO {
    private static Logger log = Logger.getLogger(SsfAlumnocapaempresaDAO.class.getName());
    
    public boolean add(SsfAlumnocapaempresa alumnocapaempresa){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAlumnocapaempresaJpaController service = new SsfAlumnocapaempresaJpaController(emf);
            em.getTransaction().begin();
            service.create(alumnocapaempresa);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAlumnocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfAlumnocapaempresa alumnocapaempresa){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAlumnocapaempresaJpaController service = new SsfAlumnocapaempresaJpaController(emf);
            em.getTransaction().begin();
            service.edit(alumnocapaempresa);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAlumnocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAlumnocapaempresaJpaController service = new SsfAlumnocapaempresaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfAlumnocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfAlumnocapaempresa find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAlumnocapaempresaJpaController service = new SsfAlumnocapaempresaJpaController(emf);
            em.getTransaction().begin();
            SsfAlumnocapaempresa alumnocapaempresa = service.findSsfAlumnocapaempresa(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return alumnocapaempresa;
        } catch (Exception ex) {
            Logger.getLogger(SsfAlumnocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfAlumnocapaempresa> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfAlumnocapaempresaJpaController service = new SsfAlumnocapaempresaJpaController(emf);
            em.getTransaction().begin();
            List<SsfAlumnocapaempresa> lista = service.findSsfAlumnocapaempresaEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfAlumnocapaempresaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
