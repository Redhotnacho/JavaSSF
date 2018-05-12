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
}
