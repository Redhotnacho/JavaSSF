/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfEstadocapaempresa;
import duoc.cl.safe.persistencia.SsfEstadocapaempresaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfEstadocapaempresaBO {
    private SsfEstadocapaempresaDAO estadocapaempresaDAO;

    public SsfEstadocapaempresaBO() {
        this.estadocapaempresaDAO = new SsfEstadocapaempresaDAO();
    }
    
    public boolean add(SsfEstadocapaempresa estadocapaempresa){
        return this.estadocapaempresaDAO.add(estadocapaempresa);
    }
    
    public boolean update(SsfEstadocapaempresa estadocapaempresa){
        return this.estadocapaempresaDAO.update(estadocapaempresa);
    }
    
    public boolean remove(int id){
        return this.estadocapaempresaDAO.remove(id);
    }
    
    public SsfEstadocapaempresa find(int id){
        return this.estadocapaempresaDAO.find(id);
    }
    
    public List<SsfEstadocapaempresa> getAll(){
        return this.estadocapaempresaDAO.getAll();
    }
    
    public SsfEstadocapaempresa findSP(int id){
        return this.estadocapaempresaDAO.findSP(id);
    }
    
    public List<SsfEstadocapaempresa> getAllSP(){
        return this.estadocapaempresaDAO.getAllSP();
    }
    
    public boolean addSP(SsfEstadocapaempresa estadocapaempresa){
        return this.estadocapaempresaDAO.addSP(estadocapaempresa);
    }
    
    public boolean updateSP(SsfEstadocapaempresa estadocapaempresa){
        return this.estadocapaempresaDAO.updateSP(estadocapaempresa);
    }
    
    public boolean removeSP(int id){
        return this.estadocapaempresaDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.estadocapaempresaDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.estadocapaempresaDAO.activarSP(id);
    }
    
}
