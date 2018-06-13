/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfEvaluacion;
import duoc.cl.safe.persistencia.SsfEvaluacionDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfEvaluacionBO {

    private SsfEvaluacionDAO evaluacionDAO;

    public SsfEvaluacionBO() {
        this.evaluacionDAO = new SsfEvaluacionDAO();
    }

    public boolean add(SsfEvaluacion evaluacion) {
        return this.evaluacionDAO.add(evaluacion);
    }

    public boolean update(SsfEvaluacion evaluacion) {
        return this.evaluacionDAO.update(evaluacion);
    }

    public boolean remove(int id) {
        return this.evaluacionDAO.remove(id);
    }

    public SsfEvaluacion find(int id) {
        return this.evaluacionDAO.find(id);
    }

    public List<SsfEvaluacion> getAll() {
        return this.evaluacionDAO.getAll();
    }

    public SsfEvaluacion findSP(int id) {
        return this.evaluacionDAO.findSP(id);
    }

    public List<SsfEvaluacion> getAllSP() {
        return this.evaluacionDAO.getAllSP();
    }

    public boolean addSP(SsfEvaluacion evaluacion) {
        return this.evaluacionDAO.addSP(evaluacion);
    }

    public boolean updateSP(SsfEvaluacion evaluacion) {
        return this.evaluacionDAO.updateSP(evaluacion);
    }

    public boolean removeSP(int id) {
        return this.evaluacionDAO.removeSP(id);
    }

    public boolean desactivarSP(int id) {
        return this.evaluacionDAO.desactivarSP(id);
    }

    public boolean activarSP(int id) {
        return this.evaluacionDAO.activarSP(id);
    }

}
