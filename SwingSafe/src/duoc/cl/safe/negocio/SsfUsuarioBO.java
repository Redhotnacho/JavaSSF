/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.persistencia.SsfUsuarioDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfUsuarioBO {
    private SsfUsuarioDAO usuarioDAO;

    public SsfUsuarioBO() {
        this.usuarioDAO = new SsfUsuarioDAO();
    }
    
    public boolean add(SsfUsuario usuario){
        return this.usuarioDAO.add(usuario);
    }
    
    public boolean update(SsfUsuario usuario){
        return this.usuarioDAO.update(usuario);
    }
    
    public boolean remove(int id){
        return this.usuarioDAO.remove(id);
    }
    
    public SsfUsuario find(int id){
        return this.usuarioDAO.find(id);
    }
    
    public List<SsfUsuario> getAll(){
        return this.usuarioDAO.getAll();
    }
}
