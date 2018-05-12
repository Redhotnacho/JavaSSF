/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfCapacitaciondia;
import duoc.cl.safe.persistencia.SsfCapacitaciondiaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfCapacitaciondiaBO {
    private SsfCapacitaciondiaDAO capacitaciondiaDAO;

    public SsfCapacitaciondiaBO() {
        this.capacitaciondiaDAO = new SsfCapacitaciondiaDAO();
    }
    
    public boolean add(SsfCapacitaciondia capacitaciondia){
        return this.capacitaciondiaDAO.add(capacitaciondia);
    }
    
    public boolean update(SsfCapacitaciondia capacitaciondia){
        return this.capacitaciondiaDAO.update(capacitaciondia);
    }
    
    public boolean remove(int id){
        return this.capacitaciondiaDAO.remove(id);
    }
    
    public SsfCapacitaciondia find(int id){
        return this.capacitaciondiaDAO.find(id);
    }
    
    public List<SsfCapacitaciondia> getAll(){
        return this.capacitaciondiaDAO.getAll();
    }
}
