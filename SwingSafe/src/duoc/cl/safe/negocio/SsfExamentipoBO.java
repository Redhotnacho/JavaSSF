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
}
