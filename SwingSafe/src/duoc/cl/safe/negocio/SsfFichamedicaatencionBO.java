/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfFichamedicaatencion;
import duoc.cl.safe.persistencia.SsfFichamedicaatencionDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfFichamedicaatencionBO {

    private SsfFichamedicaatencionDAO fichamedicaatencionDAO;

    public SsfFichamedicaatencionBO() {
        this.fichamedicaatencionDAO = new SsfFichamedicaatencionDAO();
    }

    public boolean add(SsfFichamedicaatencion fichamedicaatencion) {
        return this.fichamedicaatencionDAO.add(fichamedicaatencion);
    }

    public boolean update(SsfFichamedicaatencion fichamedicaatencion) {
        return this.fichamedicaatencionDAO.update(fichamedicaatencion);
    }

    public boolean remove(int id) {
        return this.fichamedicaatencionDAO.remove(id);
    }

    public SsfFichamedicaatencion find(int id) {
        return this.fichamedicaatencionDAO.find(id);
    }

    public List<SsfFichamedicaatencion> getAll() {
        return this.fichamedicaatencionDAO.getAll();
    }

    public SsfFichamedicaatencion findSP(int id) {
        return this.fichamedicaatencionDAO.findSP(id);
    }

    public List<SsfFichamedicaatencion> getAllSP() {
        return this.fichamedicaatencionDAO.getAllSP();
    }

    public boolean addSP(SsfFichamedicaatencion fichamedicaatencion) {
        return this.fichamedicaatencionDAO.addSP(fichamedicaatencion);
    }

    public boolean updateSP(SsfFichamedicaatencion fichamedicaatencion) {
        return this.fichamedicaatencionDAO.updateSP(fichamedicaatencion);
    }

    public boolean removeSP(int id) {
        return this.fichamedicaatencionDAO.removeSP(id);
    }

    public boolean desactivarSP(int id) {
        return this.fichamedicaatencionDAO.desactivarSP(id);
    }

    public boolean activarSP(int id) {
        return this.fichamedicaatencionDAO.activarSP(id);
    }

}
