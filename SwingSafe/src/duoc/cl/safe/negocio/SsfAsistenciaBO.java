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
}
