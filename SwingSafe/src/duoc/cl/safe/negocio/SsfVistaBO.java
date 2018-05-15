/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfVista;
import duoc.cl.safe.persistencia.SsfVistaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfVistaBO {
    private SsfVistaDAO vistaDAO;

    public SsfVistaBO() {
        this.vistaDAO = new SsfVistaDAO();
    }
    
    public boolean add(SsfVista vista){
        return this.vistaDAO.add(vista);
    }
    
    public boolean update(SsfVista vista){
        return this.vistaDAO.update(vista);
    }
    
    public boolean remove(int id){
        return this.vistaDAO.remove(id);
    }
    
    public SsfVista find(int id){
        return this.vistaDAO.find(id);
    }
    
    public List<SsfVista> getAll(){
        return this.vistaDAO.getAll();
    }
    
    public SsfVista findSP(int id){
        return this.vistaDAO.findSP(id);
    }
    
    public List<SsfVista> getAllSP(){
        return this.vistaDAO.getAllSP();
    }
    
    public boolean addSP(SsfVista vista){
        return this.vistaDAO.addSP(vista);
    }
    
    public boolean updateSP(SsfVista vista){
        return this.vistaDAO.updateSP(vista);
    }
    
    public boolean removeSP(int id){
        return this.vistaDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.vistaDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.vistaDAO.activarSP(id);
    }
    
}
