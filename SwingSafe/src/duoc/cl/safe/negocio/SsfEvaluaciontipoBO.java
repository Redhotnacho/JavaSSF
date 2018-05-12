/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfEvaluaciontipo;
import duoc.cl.safe.persistencia.SsfEvaluaciontipoDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfEvaluaciontipoBO {
    private SsfEvaluaciontipoDAO evaluaciontipoDAO;

    public SsfEvaluaciontipoBO() {
        this.evaluaciontipoDAO = new SsfEvaluaciontipoDAO();
    }
    
    public boolean add(SsfEvaluaciontipo evaluaciontipo){
        return this.evaluaciontipoDAO.add(evaluaciontipo);
    }
    
    public boolean update(SsfEvaluaciontipo evaluaciontipo){
        return this.evaluaciontipoDAO.update(evaluaciontipo);
    }
    
    public boolean remove(int id){
        return this.evaluaciontipoDAO.remove(id);
    }
    
    public SsfEvaluaciontipo find(int id){
        return this.evaluaciontipoDAO.find(id);
    }
    
    public List<SsfEvaluaciontipo> getAll(){
        return this.evaluaciontipoDAO.getAll();
    }
}
