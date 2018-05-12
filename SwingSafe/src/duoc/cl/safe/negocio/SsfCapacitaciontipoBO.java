/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfCapacitaciontipo;
import duoc.cl.safe.persistencia.SsfCapacitaciontipoDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfCapacitaciontipoBO {
    private SsfCapacitaciontipoDAO capacitaciontipoDAO;

    public SsfCapacitaciontipoBO() {
        this.capacitaciontipoDAO = new SsfCapacitaciontipoDAO();
    }
    
    public boolean add(SsfCapacitaciontipo capacitaciontipo){
        return this.capacitaciontipoDAO.add(capacitaciontipo);
    }
    
    public boolean update(SsfCapacitaciontipo capacitaciontipo){
        return this.capacitaciontipoDAO.update(capacitaciontipo);
    }
    
    public boolean remove(int id){
        return this.capacitaciontipoDAO.remove(id);
    }
    
    public SsfCapacitaciontipo find(int id){
        return this.capacitaciontipoDAO.find(id);
    }
    
    public List<SsfCapacitaciontipo> getAll(){
        return this.capacitaciontipoDAO.getAll();
    }
}
