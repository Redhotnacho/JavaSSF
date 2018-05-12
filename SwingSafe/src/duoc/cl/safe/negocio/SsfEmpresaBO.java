/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfEmpresa;
import duoc.cl.safe.persistencia.SsfEmpresaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfEmpresaBO {
    private SsfEmpresaDAO empresaDAO;

    public SsfEmpresaBO() {
        this.empresaDAO = new SsfEmpresaDAO();
    }
    
    public boolean add(SsfEmpresa empresa){
        return this.empresaDAO.add(empresa);
    }
    
    public boolean update(SsfEmpresa empresa){
        return this.empresaDAO.update(empresa);
    }
    
    public boolean remove(int id){
        return this.empresaDAO.remove(id);
    }
    
    public SsfEmpresa find(int id){
        return this.empresaDAO.find(id);
    }
    
    public List<SsfEmpresa> getAll(){
        return this.empresaDAO.getAll();
    }
}
