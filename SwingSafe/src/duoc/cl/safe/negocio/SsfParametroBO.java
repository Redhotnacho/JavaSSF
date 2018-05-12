/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfParametro;
import duoc.cl.safe.persistencia.SsfParametroDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfParametroBO {
    private SsfParametroDAO parametroDAO;

    public SsfParametroBO() {
        this.parametroDAO = new SsfParametroDAO();
    }
    
    public boolean add(SsfParametro parametro){
        return this.parametroDAO.add(parametro);
    }
    
    public boolean update(SsfParametro parametro){
        return this.parametroDAO.update(parametro);
    }
    
    public boolean remove(int id){
        return this.parametroDAO.remove(id);
    }
    
    public SsfParametro find(int id){
        return this.parametroDAO.find(id);
    }
    
    public List<SsfParametro> getAll(){
        return this.parametroDAO.getAll();
    }
}
