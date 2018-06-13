/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfAlumnocapaempresa;
import duoc.cl.safe.persistencia.SsfAlumnocapaempresaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfAlumnocapaempresaBO {

    private SsfAlumnocapaempresaDAO alumnocapaempresaDAO;

    public SsfAlumnocapaempresaBO() {
        this.alumnocapaempresaDAO = new SsfAlumnocapaempresaDAO();
    }

    public boolean add(SsfAlumnocapaempresa alumnocapaempresa) {
        return this.alumnocapaempresaDAO.add(alumnocapaempresa);
    }

    public boolean update(SsfAlumnocapaempresa alumnocapaempresa) {
        return this.alumnocapaempresaDAO.update(alumnocapaempresa);
    }

    public boolean remove(int id) {
        return this.alumnocapaempresaDAO.remove(id);
    }

    public SsfAlumnocapaempresa find(int id) {
        return this.alumnocapaempresaDAO.find(id);
    }

    public List<SsfAlumnocapaempresa> getAll() {
        return this.alumnocapaempresaDAO.getAll();
    }

    public SsfAlumnocapaempresa findSP(int id) {
        return this.alumnocapaempresaDAO.findSP(id);
    }

    public List<SsfAlumnocapaempresa> getAllSP() {
        return this.alumnocapaempresaDAO.getAllSP();
    }

    public boolean addSP(SsfAlumnocapaempresa alumnocapaempresa) {
        return this.alumnocapaempresaDAO.addSP(alumnocapaempresa);
    }

    public boolean updateSP(SsfAlumnocapaempresa alumnocapaempresa) {
        return this.alumnocapaempresaDAO.updateSP(alumnocapaempresa);
    }

    public boolean removeSP(int id) {
        return this.alumnocapaempresaDAO.removeSP(id);
    }

    public boolean desactivarSP(int id) {
        return this.alumnocapaempresaDAO.desactivarSP(id);
    }

    public boolean activarSP(int id) {
        return this.alumnocapaempresaDAO.activarSP(id);
    }

}
