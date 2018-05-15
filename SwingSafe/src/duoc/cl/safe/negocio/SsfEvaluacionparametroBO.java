/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfEvaluacionparametro;
import duoc.cl.safe.persistencia.SsfEvaluacionparametroDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfEvaluacionparametroBO {
    private SsfEvaluacionparametroDAO evaluacionparametroDAO;

    public SsfEvaluacionparametroBO() {
        this.evaluacionparametroDAO = new SsfEvaluacionparametroDAO();
    }
    
    public boolean add(SsfEvaluacionparametro evaluacionparametro){
        return this.evaluacionparametroDAO.add(evaluacionparametro);
    }
    
    public boolean update(SsfEvaluacionparametro evaluacionparametro){
        return this.evaluacionparametroDAO.update(evaluacionparametro);
    }
    
    public boolean remove(int id){
        return this.evaluacionparametroDAO.remove(id);
    }
    
    public SsfEvaluacionparametro find(int id){
        return this.evaluacionparametroDAO.find(id);
    }
    
    public List<SsfEvaluacionparametro> getAll(){
        return this.evaluacionparametroDAO.getAll();
    }
    
    public SsfEvaluacionparametro findSP(int id){
        return this.evaluacionparametroDAO.findSP(id);
    }
    
    public List<SsfEvaluacionparametro> getAllSP(){
        return this.evaluacionparametroDAO.getAllSP();
    }
    
    public boolean addSP(SsfEvaluacionparametro evaluacionparametro){
        return this.evaluacionparametroDAO.addSP(evaluacionparametro);
    }
    
    public boolean updateSP(SsfEvaluacionparametro evaluacionparametro){
        return this.evaluacionparametroDAO.updateSP(evaluacionparametro);
    }
    
    public boolean removeSP(int id){
        return this.evaluacionparametroDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.evaluacionparametroDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.evaluacionparametroDAO.activarSP(id);
    }
    
}
