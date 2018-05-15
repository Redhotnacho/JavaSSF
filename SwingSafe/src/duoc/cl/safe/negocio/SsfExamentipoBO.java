/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfExamentipo;
import duoc.cl.safe.persistencia.SsfExamentipoDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfExamentipoBO {
    private SsfExamentipoDAO examentipoDAO;

    public SsfExamentipoBO() {
        this.examentipoDAO = new SsfExamentipoDAO();
    }
    
    public boolean add(SsfExamentipo examentipo){
        return this.examentipoDAO.add(examentipo);
    }
    
    public boolean update(SsfExamentipo examentipo){
        return this.examentipoDAO.update(examentipo);
    }
    
    public boolean remove(int id){
        return this.examentipoDAO.remove(id);
    }
    
    public SsfExamentipo find(int id){
        return this.examentipoDAO.find(id);
    }
    
    public List<SsfExamentipo> getAll(){
        return this.examentipoDAO.getAll();
    }
    
    public SsfExamentipo findSP(int id){
        return this.examentipoDAO.findSP(id);
    }
    
    public List<SsfExamentipo> getAllSP(){
        return this.examentipoDAO.getAllSP();
    }
    
    public boolean addSP(SsfExamentipo examentipo){
        return this.examentipoDAO.addSP(examentipo);
    }
    
    public boolean updateSP(SsfExamentipo examentipo){
        return this.examentipoDAO.updateSP(examentipo);
    }
    
    public boolean removeSP(int id){
        return this.examentipoDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.examentipoDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.examentipoDAO.activarSP(id);
    }
    
}
