/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfMenu;
import duoc.cl.safe.persistencia.SsfMenuDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfMenuBO {

    private SsfMenuDAO menuDAO;

    public SsfMenuBO() {
        this.menuDAO = new SsfMenuDAO();
    }

    public boolean add(SsfMenu menu) {
        return this.menuDAO.add(menu);
    }

    public boolean update(SsfMenu menu) {
        return this.menuDAO.update(menu);
    }

    public boolean remove(int id) {
        return this.menuDAO.remove(id);
    }

    public SsfMenu find(int id) {
        return this.menuDAO.find(id);
    }

    public List<SsfMenu> getAll() {
        return this.menuDAO.getAll();
    }

    public SsfMenu findSP(int id) {
        return this.menuDAO.findSP(id);
    }

    public List<SsfMenu> getAllSP() {
        return this.menuDAO.getAllSP();
    }

    public boolean addSP(SsfMenu menu) {
        return this.menuDAO.addSP(menu);
    }

    public boolean updateSP(SsfMenu menu) {
        return this.menuDAO.updateSP(menu);
    }

    public boolean removeSP(int id) {
        return this.menuDAO.removeSP(id);
    }

    public boolean desactivarSP(int id) {
        return this.menuDAO.desactivarSP(id);
    }

    public boolean activarSP(int id) {
        return this.menuDAO.activarSP(id);
    }

}
