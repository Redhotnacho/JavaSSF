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
}
