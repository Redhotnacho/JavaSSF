/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfAtencionmedica;
import duoc.cl.safe.persistencia.SsfAtencionmedicaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfAtencionmedicaBO {

    private SsfAtencionmedicaDAO atencionmedicaDAO;

    public SsfAtencionmedicaBO() {
        this.atencionmedicaDAO = new SsfAtencionmedicaDAO();
    }

    public boolean add(SsfAtencionmedica atencionmedica) {
        return this.atencionmedicaDAO.add(atencionmedica);
    }

    public boolean update(SsfAtencionmedica atencionmedica) {
        return this.atencionmedicaDAO.update(atencionmedica);
    }

    public boolean remove(int id) {
        return this.atencionmedicaDAO.remove(id);
    }

    public SsfAtencionmedica find(int id) {
        return this.atencionmedicaDAO.find(id);
    }

    public List<SsfAtencionmedica> getAll() {
        return this.atencionmedicaDAO.getAll();
    }

    public SsfAtencionmedica findSP(int id) {
        return this.atencionmedicaDAO.findSP(id);
    }

    public List<SsfAtencionmedica> getAllSP() {
        return this.atencionmedicaDAO.getAllSP();
    }

    public boolean addSP(SsfAtencionmedica atencionmedica) {
        return this.atencionmedicaDAO.addSP(atencionmedica);
    }

    public boolean updateSP(SsfAtencionmedica atencionmedica) {
        return this.atencionmedicaDAO.updateSP(atencionmedica);
    }

    public boolean removeSP(int id) {
        return this.atencionmedicaDAO.removeSP(id);
    }

    public boolean desactivarSP(int id) {
        return this.atencionmedicaDAO.desactivarSP(id);
    }

    public boolean activarSP(int id) {
        return this.atencionmedicaDAO.activarSP(id);
    }

}
