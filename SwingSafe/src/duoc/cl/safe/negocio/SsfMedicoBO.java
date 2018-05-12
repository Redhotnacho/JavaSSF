/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfMedico;
import duoc.cl.safe.persistencia.SsfMedicoDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfMedicoBO {
    private SsfMedicoDAO medicoDAO;

    public SsfMedicoBO() {
        this.medicoDAO = new SsfMedicoDAO();
    }
    
    public boolean add(SsfMedico medico){
        return this.medicoDAO.add(medico);
    }
    
    public boolean update(SsfMedico medico){
        return this.medicoDAO.update(medico);
    }
    
    public boolean remove(int id){
        return this.medicoDAO.remove(id);
    }
    
    public SsfMedico find(int id){
        return this.medicoDAO.find(id);
    }
    
    public List<SsfMedico> getAll(){
        return this.medicoDAO.getAll();
    }
}
