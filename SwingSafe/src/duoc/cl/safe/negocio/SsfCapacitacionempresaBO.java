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
    
    public SsfCapacitacionempresa findSP(int id){
        return this.capacitacionempresaDAO.findSP(id);
    }
    
    public List<SsfCapacitacionempresa> getAllSP(){
        return this.capacitacionempresaDAO.getAllSP();
    }
    
    public boolean addSP(SsfCapacitacionempresa capacitacionempresa){
        return this.capacitacionempresaDAO.addSP(capacitacionempresa);
    }
    
    public boolean updateSP(SsfCapacitacionempresa capacitacionempresa){
        return this.capacitacionempresaDAO.updateSP(capacitacionempresa);
    }
    
    public boolean removeSP(int id){
        return this.capacitacionempresaDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.capacitacionempresaDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.capacitacionempresaDAO.activarSP(id);
    }
    
    
}
