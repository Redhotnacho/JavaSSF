/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfPerfil;
import duoc.cl.safe.persistencia.SsfPerfilDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfPerfilBO {
    private SsfPerfilDAO perfilDAO;

    public SsfPerfilBO() {
        this.perfilDAO = new SsfPerfilDAO();
    }
    
    public boolean add(SsfPerfil perfil){
        return this.perfilDAO.add(perfil);
    }
    
    public boolean update(SsfPerfil perfil){
        return this.perfilDAO.update(perfil);
    }
    
    public boolean remove(int id){
        return this.perfilDAO.remove(id);
    }
    
    public SsfPerfil find(int id){
        return this.perfilDAO.find(id);
    }
    
    public List<SsfPerfil> getAll(){
        return this.perfilDAO.getAll();
    }
    
    public SsfPerfil findSP(int id){
        return this.perfilDAO.findSP(id);
    }
    
    public List<SsfPerfil> getAllSP(){
        return this.perfilDAO.getAllSP();
    }
    
    public boolean addSP(SsfPerfil perfil){
        return this.perfilDAO.addSP(perfil);
    }
    
    public boolean updateSP(SsfPerfil perfil){
        return this.perfilDAO.updateSP(perfil);
    }
    
    public boolean removeSP(int id){
        return this.perfilDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.perfilDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.perfilDAO.activarSP(id);
    }
}
