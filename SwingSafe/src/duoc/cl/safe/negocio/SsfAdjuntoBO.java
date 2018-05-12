/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfAdjunto;
import duoc.cl.safe.persistencia.SsfAdjuntoDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfAdjuntoBO {
    private SsfAdjuntoDAO adjuntoDAO;

    public SsfAdjuntoBO() {
        this.adjuntoDAO = new SsfAdjuntoDAO();
    }
    
    public boolean add(SsfAdjunto adjunto){
        return this.adjuntoDAO.add(adjunto);
    }
    
    public boolean update(SsfAdjunto adjunto){
        return this.adjuntoDAO.update(adjunto);
    }
    
    public boolean remove(int id){
        return this.adjuntoDAO.remove(id);
    }
    
    public SsfAdjunto find(int id){
        return this.adjuntoDAO.find(id);
    }
    
    public List<SsfAdjunto> getAll(){
        return this.adjuntoDAO.getAll();
    }
}
