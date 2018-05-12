/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfCapacitacion;
import duoc.cl.safe.persistencia.SsfCapacitacionDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfCapacitacionBO {
    private SsfCapacitacionDAO capacitacionDAO;

    public SsfCapacitacionBO() {
        this.capacitacionDAO = new SsfCapacitacionDAO();
    }
    
    public boolean add(SsfCapacitacion capacitacion){
        return this.capacitacionDAO.add(capacitacion);
    }
    
    public boolean update(SsfCapacitacion capacitacion){
        return this.capacitacionDAO.update(capacitacion);
    }
    
    public boolean remove(int id){
        return this.capacitacionDAO.remove(id);
    }
    
    public SsfCapacitacion find(int id){
        return this.capacitacionDAO.find(id);
    }
    
    public List<SsfCapacitacion> getAll(){
        return this.capacitacionDAO.getAll();
    }
}
