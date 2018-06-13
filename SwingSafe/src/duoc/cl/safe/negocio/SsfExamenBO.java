/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfExamen;
import duoc.cl.safe.persistencia.SsfExamenDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfExamenBO {

    private SsfExamenDAO examenDAO;

    public SsfExamenBO() {
        this.examenDAO = new SsfExamenDAO();
    }

    public boolean add(SsfExamen examen) {
        return this.examenDAO.add(examen);
    }

    public boolean update(SsfExamen examen) {
        return this.examenDAO.update(examen);
    }

    public boolean remove(int id) {
        return this.examenDAO.remove(id);
    }

    public SsfExamen find(int id) {
        return this.examenDAO.find(id);
    }

    public List<SsfExamen> getAll() {
        return this.examenDAO.getAll();
    }

    public SsfExamen findSP(int id) {
        return this.examenDAO.findSP(id);
    }

    public List<SsfExamen> getAllSP() {
        return this.examenDAO.getAllSP();
    }

    public boolean addSP(SsfExamen examen) {
        return this.examenDAO.addSP(examen);
    }

    public boolean updateSP(SsfExamen examen) {
        return this.examenDAO.updateSP(examen);
    }

    public boolean removeSP(int id) {
        return this.examenDAO.removeSP(id);
    }

    public boolean desactivarSP(int id) {
        return this.examenDAO.desactivarSP(id);
    }

    public boolean activarSP(int id) {
        return this.examenDAO.activarSP(id);
    }

}
