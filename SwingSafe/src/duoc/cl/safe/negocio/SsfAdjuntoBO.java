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
    
    public SsfAdjunto findSP(int id){
        return this.adjuntoDAO.findSP(id);
    }
    
    public List<SsfAdjunto> getAllSP(){
        return this.adjuntoDAO.getAllSP();
    }
    
    public boolean addSP(SsfAdjunto adjunto){
        return this.adjuntoDAO.addSP(adjunto);
    }
    
    public boolean updateSP(SsfAdjunto adjunto){
        return this.adjuntoDAO.updateSP(adjunto);
    }
    
    public boolean removeSP(int id){
        return this.adjuntoDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.adjuntoDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.adjuntoDAO.activarSP(id);
    }
    
}
