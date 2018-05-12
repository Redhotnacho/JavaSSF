/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfCapacitacionempresa;
import duoc.cl.safe.persistencia.SsfCapacitacionempresaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfCapacitacionempresaBO {
    private SsfCapacitacionempresaDAO capacitacionempresaDAO;

    public SsfCapacitacionempresaBO() {
        this.capacitacionempresaDAO = new SsfCapacitacionempresaDAO();
    }
    
    public boolean add(SsfCapacitacionempresa capacitacionempresa){
        return this.capacitacionempresaDAO.add(capacitacionempresa);
    }
    
    public boolean update(SsfCapacitacionempresa capacitacionempresa){
        return this.capacitacionempresaDAO.update(capacitacionempresa);
    }
    
    public boolean remove(int id){
        return this.capacitacionempresaDAO.remove(id);
    }
    
    public SsfCapacitacionempresa find(int id){
        return this.capacitacionempresaDAO.find(id);
    }
    
    public List<SsfCapacitacionempresa> getAll(){
        return this.capacitacionempresaDAO.getAll();
    }
}
