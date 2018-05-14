/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.presentacion;

import duoc.cl.safe.entity.SsfPerfil;
import duoc.cl.safe.entity.SsfPersona;
import duoc.cl.safe.negocio.SsfPerfilBO;
import duoc.cl.safe.negocio.SsfPersonaBO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nacho
 */
public class Prueba {
    public static void main(String[] args) throws ParseException {
        //SsfPersonaBO pbo= new SsfPersonaBO();
        
        // Comprobación addSP
        /*
        SsfPersona p = new SsfPersona();
        p.setNombre("Prueba2");
        SimpleDateFormat dateformat2 = new SimpleDateFormat("dd/MM/yyyy");
	String strdate2 = "15/05/1996";
        p.setFechaNac(dateformat2.parse(strdate2));
        if (pbo.addSP(p)) {
            System.out.println("Éxito insert");
        }else{
            System.out.println("Fail!");
        }
        */
        //Comprobación removeSP
        /*
        if (pbo.removeSP(89)) {
            System.out.println("Éxito: borrado");
        }else{
            System.out.println("Fail!");
        }
        */
        // Comprobación updateSP
        /*
        p = pbo.findSP(121);
        System.out.println("correo sin update: "+p.getCorreo());
        p.setCorreo("updated6969@gmail.com");
        if (pbo.updateSP(p)) {
            p = pbo.findSP(121);
            System.out.println("Éxito update: "+p.getCorreo());
        }else{
            System.out.println("Fail!");
        }

        // Comprobación findSP
        
        p = pbo.findSP(108);
        System.out.println("Nombre persona find: "+p.getNombre());
        if (p.getSsfAlumno()!=null) {
            System.out.println("Alumno find: "+p.getSsfAlumno().getIdPersona().getNombre());
        }
        if (p.getSsfUsuario()!=null) {
            System.out.println("Usuario find: "+p.getSsfUsuario().getUsername());
        }

        // Comprobación getAllSP
        
        List<SsfPersona> personas = pbo.getAllSP();
        for (SsfPersona psp : personas) {
            System.out.println("Nombre persona getAll: "+psp.getNombre());
        }
        
        // Comprobación desactivarSP
        if (pbo.desactivarSP(108)) {
            p = pbo.findSP(108);
            System.out.println("Éxito desactivar estado: "+p.getEstado());
        }else{
            System.out.println("Fail!");
        }
        
        // Comprobación activarSP
        if (pbo.activarSP(108)) {
            p = pbo.findSP(108);
            System.out.println("Éxito activar estado: "+p.getEstado());
        }else{
            System.out.println("Fail!");
        }
        */
        
        // ------------- Prueba conexión perfil -----------------------
        /*
        SsfPerfilBO pbo = new SsfPerfilBO();
        for (SsfPerfil p : pbo.getAll()){
            System.out.println("Perfil getAll(): "+p.getPerfil());
        }
        */
        
    }
}
