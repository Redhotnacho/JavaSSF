/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfPersona;
import duoc.cl.safe.persistencia.SsfPersonaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfPersonaBO {

    private SsfPersonaDAO personaDAO;

    public SsfPersonaBO() {
        this.personaDAO = new SsfPersonaDAO();
    }

    public boolean add(SsfPersona persona) {
        return this.personaDAO.add(persona);
    }

    public boolean update(SsfPersona persona) {
        return this.personaDAO.update(persona);
    }

    public boolean remove(int id) {
        return this.personaDAO.remove(id);
    }

    public SsfPersona find(int id) {
        return this.personaDAO.find(id);
    }

    public List<SsfPersona> getAll() {
        return this.personaDAO.getAll();
    }

    public SsfPersona findSP(int id) {
        return this.personaDAO.findSP(id);
    }

    public List<SsfPersona> getAllSP() {
        return this.personaDAO.getAllSP();
    }

    public boolean addSP(SsfPersona persona) {
        return this.personaDAO.addSP(persona);
    }

    public boolean updateSP(SsfPersona persona) {
        return this.personaDAO.updateSP(persona);
    }

    public boolean removeSP(int id) {
        return this.personaDAO.removeSP(id);
    }

    public boolean desactivarSP(int id) {
        return this.personaDAO.desactivarSP(id);
    }

    public boolean activarSP(int id) {
        return this.personaDAO.activarSP(id);
    }
}
