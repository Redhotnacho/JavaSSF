/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfAsistencia;
import duoc.cl.safe.persistencia.SsfAsistenciaDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfAsistenciaBO {
    private SsfAsistenciaDAO asistenciaDAO;

    public SsfAsistenciaBO() {
        this.asistenciaDAO = new SsfAsistenciaDAO();
    }
    
    public boolean add(SsfAsistencia asistencia){
        return this.asistenciaDAO.add(asistencia);
    }
    
    public boolean update(SsfAsistencia asistencia){
        return this.asistenciaDAO.update(asistencia);
    }
    
    public boolean remove(int id){
        return this.asistenciaDAO.remove(id);
    }
    
    public SsfAsistencia find(int id){
        return this.asistenciaDAO.find(id);
    }
    
    public List<SsfAsistencia> getAll(){
        return this.asistenciaDAO.getAll();
    }
    
    public SsfAsistencia findSP(int id){
        return this.asistenciaDAO.findSP(id);
    }
    
    public List<SsfAsistencia> getAllSP(){
        return this.asistenciaDAO.getAllSP();
    }
    
    public boolean addSP(SsfAsistencia asistencia){
        return this.asistenciaDAO.addSP(asistencia);
    }
    
    public boolean updateSP(SsfAsistencia asistencia){
        return this.asistenciaDAO.updateSP(asistencia);
    }
    
    public boolean removeSP(int id){
        return this.asistenciaDAO.removeSP(id);
    }
    
    public boolean desactivarSP(int id){
        return this.asistenciaDAO.desactivarSP(id);
    }
    
    public boolean activarSP(int id){
        return this.asistenciaDAO.activarSP(id);
    }
    
}
