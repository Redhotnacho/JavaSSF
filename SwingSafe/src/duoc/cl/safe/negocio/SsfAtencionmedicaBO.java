/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfAtencionmedica;
import duoc.cl.safe.persistencia.SsfAtencionmedicaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfAtencionmedicaBO {
    private SsfAtencionmedicaDAO atencionmedicaDAO;

    public SsfAtencionmedicaBO() {
        this.atencionmedicaDAO = new SsfAtencionmedicaDAO();
    }
    
    public boolean add(SsfAtencionmedica atencionmedica){
        return this.atencionmedicaDAO.add(atencionmedica);
    }
    
    public boolean update(SsfAtencionmedica atencionmedica){
        return this.atencionmedicaDAO.update(atencionmedica);
    }
    
    public boolean remove(int id){
        return this.atencionmedicaDAO.remove(id);
    }
    
    public SsfAtencionmedica find(int id){
        return this.atencionmedicaDAO.find(id);
    }
    
    public List<SsfAtencionmedica> getAll(){
        return this.atencionmedicaDAO.getAll();
    }
}
