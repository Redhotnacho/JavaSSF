/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfPerfilvista;
import duoc.cl.safe.jpa.SsfPerfilvistaJpaController;
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
public class SsfPerfilvistaDAO {
    private static Logger log = Logger.getLogger(SsfPerfilvistaDAO.class.getName());
    
    public boolean add(SsfPerfilvista perfilvista){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfPerfilvistaJpaController service = new SsfPerfilvistaJpaController(emf);
            em.getTransaction().begin();
            service.create(perfilvista);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfPerfilvistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfPerfilvista perfilvista){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfPerfilvistaJpaController service = new SsfPerfilvistaJpaController(emf);
            em.getTransaction().begin();
            service.edit(perfilvista);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfPerfilvistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfPerfilvistaJpaController service = new SsfPerfilvistaJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfPerfilvistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfPerfilvista find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfPerfilvistaJpaController service = new SsfPerfilvistaJpaController(emf);
            em.getTransaction().begin();
            SsfPerfilvista perfilvista = service.findSsfPerfilvista(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return perfilvista;
        } catch (Exception ex) {
            Logger.getLogger(SsfPerfilvistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfPerfilvista> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfPerfilvistaJpaController service = new SsfPerfilvistaJpaController(emf);
            em.getTransaction().begin();
            List<SsfPerfilvista> lista = service.findSsfPerfilvistaEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfPerfilvistaDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
