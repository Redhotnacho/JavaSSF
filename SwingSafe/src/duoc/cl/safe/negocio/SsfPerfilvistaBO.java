/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfPerfilvista;
import duoc.cl.safe.persistencia.SsfPerfilvistaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfPerfilvistaBO {
    private SsfPerfilvistaDAO perfilvistaDAO;

    public SsfPerfilvistaBO() {
        this.perfilvistaDAO = new SsfPerfilvistaDAO();
    }
    
    public boolean add(SsfPerfilvista perfilvista){
        return this.perfilvistaDAO.add(perfilvista);
    }
    
    public boolean update(SsfPerfilvista perfilvista){
        return this.perfilvistaDAO.update(perfilvista);
    }
    
    public boolean remove(int id){
        return this.perfilvistaDAO.remove(id);
    }
    
    public SsfPerfilvista find(int id){
        return this.perfilvistaDAO.find(id);
    }
    
    public List<SsfPerfilvista> getAll(){
        return this.perfilvistaDAO.getAll();
    }
    
    public SsfPerfilvista findSP(int id){
        return this.perfilvistaDAO.findSP(id);
    }
    
    public List<SsfPerfilvista> getAllSP(){
        return this.perfilvistaDAO.getAllSP();
    }
    
    public boolean addSP(SsfPerfilvista perfilvista){
        return this.perfilvistaDAO.addSP(perfilvista);
    }
    
    public boolean updateSP(SsfPerfilvista perfilvista){
        return this.perfilvistaDAO.updateSP(perfilvista);
    }
    
    public boolean removeSP(int id){
        return this.perfilvistaDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.perfilvistaDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.perfilvistaDAO.activarSP(id);
    }
    
}
