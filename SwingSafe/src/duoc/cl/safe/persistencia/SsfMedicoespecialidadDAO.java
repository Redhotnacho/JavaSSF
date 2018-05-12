/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

import duoc.cl.safe.entity.SsfMedicoespecialidad;
import duoc.cl.safe.jpa.SsfMedicoespecialidadJpaController;
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
public class SsfMedicoespecialidadDAO {
    private static Logger log = Logger.getLogger(SsfMedicoespecialidadDAO.class.getName());
    
    public boolean add(SsfMedicoespecialidad medicoespecialidad){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfMedicoespecialidadJpaController service = new SsfMedicoespecialidadJpaController(emf);
            em.getTransaction().begin();
            service.create(medicoespecialidad);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfMedicoespecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al agregar", ex);
            return false;
        }
    }
    
    public boolean update(SsfMedicoespecialidad medicoespecialidad){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfMedicoespecialidadJpaController service = new SsfMedicoespecialidadJpaController(emf);
            em.getTransaction().begin();
            service.edit(medicoespecialidad);
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfMedicoespecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al modificar", ex);
            return false;
        }
    }
    
    public boolean remove(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfMedicoespecialidadJpaController service = new SsfMedicoespecialidadJpaController(emf);
            em.getTransaction().begin();
            service.destroy(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(SsfMedicoespecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al borrar", ex);
            return false;
        }
    }
    
    public SsfMedicoespecialidad find(int id){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfMedicoespecialidadJpaController service = new SsfMedicoespecialidadJpaController(emf);
            em.getTransaction().begin();
            SsfMedicoespecialidad medicoespecialidad = service.findSsfMedicoespecialidad(BigDecimal.valueOf(new Long(id)));
            em.getTransaction().commit();
            return medicoespecialidad;
        } catch (Exception ex) {
            Logger.getLogger(SsfMedicoespecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar", ex);
            return null;
        }
    }
    
    public List<SsfMedicoespecialidad> getAll(){
        try {
            EntityManagerFactory emf= Persistence.createEntityManagerFactory("SwingSafePU");
            EntityManager em = emf.createEntityManager();
            SsfMedicoespecialidadJpaController service = new SsfMedicoespecialidadJpaController(emf);
            em.getTransaction().begin();
            List<SsfMedicoespecialidad> lista = service.findSsfMedicoespecialidadEntities();
            em.getTransaction().commit();
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(SsfMedicoespecialidadDAO.class.getName()).log(Level.SEVERE, null, ex);
            log.log(Level.SEVERE, "Error al buscar elementos", ex);
            return null;
        }
    }
}
