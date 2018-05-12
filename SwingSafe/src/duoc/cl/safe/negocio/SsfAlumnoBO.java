/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.negocio;

import duoc.cl.safe.entity.SsfAlumno;
import duoc.cl.safe.persistencia.SsfAlumnoDAO;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class SsfAlumnoBO {
    private SsfAlumnoDAO alumnoDAO;

    public SsfAlumnoBO() {
        this.alumnoDAO = new SsfAlumnoDAO();
    }
    
    public boolean add(SsfAlumno alumno){
        return this.alumnoDAO.add(alumno);
    }
    
    public boolean update(SsfAlumno alumno){
        return this.alumnoDAO.update(alumno);
    }
    
    public boolean remove(int id){
        return this.alumnoDAO.remove(id);
    }
    
    public SsfAlumno find(int id){
        return this.alumnoDAO.find(id);
    }
    
    public List<SsfAlumno> getAll(){
        return this.alumnoDAO.getAll();
    }
}
