/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfEvaluacionestado;
import duoc.cl.safe.persistencia.SsfEvaluacionestadoDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfEvaluacionestadoBO {
    private SsfEvaluacionestadoDAO evaluacionestadoDAO;

    public SsfEvaluacionestadoBO() {
        this.evaluacionestadoDAO = new SsfEvaluacionestadoDAO();
    }
    
    public boolean add(SsfEvaluacionestado evaluacionestado){
        return this.evaluacionestadoDAO.add(evaluacionestado);
    }
    
    public boolean update(SsfEvaluacionestado evaluacionestado){
        return this.evaluacionestadoDAO.update(evaluacionestado);
    }
    
    public boolean remove(int id){
        return this.evaluacionestadoDAO.remove(id);
    }
    
    public SsfEvaluacionestado find(int id){
        return this.evaluacionestadoDAO.find(id);
    }
    
    public List<SsfEvaluacionestado> getAll(){
        return this.evaluacionestadoDAO.getAll();
    }
}
