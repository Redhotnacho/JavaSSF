/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfFichamedica;
import duoc.cl.safe.persistencia.SsfFichamedicaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfFichamedicaBO {
    private SsfFichamedicaDAO fichamedicaDAO;

    public SsfFichamedicaBO() {
        this.fichamedicaDAO = new SsfFichamedicaDAO();
    }
    
    public boolean add(SsfFichamedica fichamedica){
        return this.fichamedicaDAO.add(fichamedica);
    }
    
    public boolean update(SsfFichamedica fichamedica){
        return this.fichamedicaDAO.update(fichamedica);
    }
    
    public boolean remove(int id){
        return this.fichamedicaDAO.remove(id);
    }
    
    public SsfFichamedica find(int id){
        return this.fichamedicaDAO.find(id);
    }
    
    public List<SsfFichamedica> getAll(){
        return this.fichamedicaDAO.getAll();
    }
    
    public SsfFichamedica findSP(int id){
        return this.fichamedicaDAO.findSP(id);
    }
    
    public List<SsfFichamedica> getAllSP(){
        return this.fichamedicaDAO.getAllSP();
    }
    
    public boolean addSP(SsfFichamedica fichamedica){
        return this.fichamedicaDAO.addSP(fichamedica);
    }
    
    public boolean updateSP(SsfFichamedica fichamedica){
        return this.fichamedicaDAO.updateSP(fichamedica);
    }
    
    public boolean removeSP(int id){
        return this.fichamedicaDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.fichamedicaDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.fichamedicaDAO.activarSP(id);
    }
    
}
