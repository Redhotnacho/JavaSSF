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

    public boolean add(SsfEvaluaciontipo evaluaciontipo) {
        return this.evaluaciontipoDAO.add(evaluaciontipo);
    }

    public boolean update(SsfEvaluaciontipo evaluaciontipo) {
        return this.evaluaciontipoDAO.update(evaluaciontipo);
    }

    public boolean remove(int id) {
        return this.evaluaciontipoDAO.remove(id);
    }

    public SsfEvaluaciontipo find(int id) {
        return this.evaluaciontipoDAO.find(id);
    }

    public List<SsfEvaluaciontipo> getAll() {
        return this.evaluaciontipoDAO.getAll();
    }

    public SsfEvaluaciontipo findSP(int id) {
        return this.evaluaciontipoDAO.findSP(id);
    }

    public List<SsfEvaluaciontipo> getAllSP() {
        return this.evaluaciontipoDAO.getAllSP();
    }

    public boolean addSP(SsfEvaluaciontipo evaluaciontipo) {
        return this.evaluaciontipoDAO.addSP(evaluaciontipo);
    }

    public boolean updateSP(SsfEvaluaciontipo evaluaciontipo) {
        return this.evaluaciontipoDAO.updateSP(evaluaciontipo);
    }

    public boolean removeSP(int id) {
        return this.evaluaciontipoDAO.removeSP(id);
    }

    public boolean desactivarSP(int id) {
        return this.evaluaciontipoDAO.desactivarSP(id);
    }

    public boolean activarSP(int id) {
        return this.evaluaciontipoDAO.activarSP(id);
    }

}
