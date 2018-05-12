/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfMedicoespecialidad;
import duoc.cl.safe.persistencia.SsfMedicoespecialidadDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfMedicoespecialidadBO {
    private SsfMedicoespecialidadDAO medicoespecialidadDAO;

    public SsfMedicoespecialidadBO() {
        this.medicoespecialidadDAO = new SsfMedicoespecialidadDAO();
    }
    
    public boolean add(SsfMedicoespecialidad medicoespecialidad){
        return this.medicoespecialidadDAO.add(medicoespecialidad);
    }
    
    public boolean update(SsfMedicoespecialidad medicoespecialidad){
        return this.medicoespecialidadDAO.update(medicoespecialidad);
    }
    
    public boolean remove(int id){
        return this.medicoespecialidadDAO.remove(id);
    }
    
    public SsfMedicoespecialidad find(int id){
        return this.medicoespecialidadDAO.find(id);
    }
    
    public List<SsfMedicoespecialidad> getAll(){
        return this.medicoespecialidadDAO.getAll();
    }
}
