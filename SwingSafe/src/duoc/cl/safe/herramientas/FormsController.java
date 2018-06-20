/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.herramientas;

import duoc.cl.safe.presentacion.Inicio;
import duoc.cl.safe.presentacion.capacitaciones.MantenedorCapEmpresa;
import duoc.cl.safe.presentacion.capacitaciones.MantenedorCapacitacion;
import duoc.cl.safe.presentacion.capacitaciones.MantenedorAsistencia;
import duoc.cl.safe.presentacion.capacitaciones.MantenedorCapacitacionTipo;
import duoc.cl.safe.presentacion.capacitaciones.MantenedorEstadoCapacitacion;
import duoc.cl.safe.presentacion.evaluaciones.FormularioEvaluacion;
import duoc.cl.safe.presentacion.evaluaciones.MantenedorEvaluacion;
import duoc.cl.safe.presentacion.evaluaciones.MantenedorEvaluacionEstado;
import duoc.cl.safe.presentacion.evaluaciones.MantenedorEvaluacionParametro;
import duoc.cl.safe.presentacion.evaluaciones.MantenedorEvaluacionTipo;
import duoc.cl.safe.presentacion.evaluaciones.MantenedorParametro;
import duoc.cl.safe.presentacion.perfiles.MantenedorMenu;
import duoc.cl.safe.presentacion.perfiles.MantenedorPerfil;
import duoc.cl.safe.presentacion.perfiles.MantenedorPerfilVistas;
import duoc.cl.safe.presentacion.perfiles.MantenedorVistas;
import duoc.cl.safe.presentacion.usuarios.Login;
import duoc.cl.safe.presentacion.usuarios.MantenedorEmpresa;
import duoc.cl.safe.presentacion.usuarios.MantenedorPersona;
import duoc.cl.safe.presentacion.usuarios.MantenedorUsuario;

/**
 *
 * @author yerko
 */
public class FormsController {

    private int idVista;
    private Menu menu;

    public FormsController(int idVista, Menu menu) {
        this.menu = menu;
        this.idVista = idVista;
    }

    public int getIdVista() {
        return idVista;
    }

    public void setIdVista(int idVista) {
        this.idVista = idVista;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void abrirJframe() {
        if (idVista == -1) {
            Inicio inicio = new Inicio();
            inicio.setFormsController(this);
            inicio.setVisible(true);
        }
        if (idVista == 2 || idVista == 61) {
            MantenedorUsuario mpf = new MantenedorUsuario();
            mpf.setFormsController(this);
            mpf.setVisible(true);
        }
        if (idVista == 3) {
            MantenedorEvaluacionTipo mantenedorEvTipo = new MantenedorEvaluacionTipo();
            mantenedorEvTipo.setFormsController(this);
            mantenedorEvTipo.setVisible(true);
        }
        if (idVista == 70) {
            MantenedorEvaluacionParametro mantenedorEvParametro = new MantenedorEvaluacionParametro();
            mantenedorEvParametro.setFormsController(this);
            mantenedorEvParametro.setVisible(true);
        }
        if (idVista == 5 || idVista == 63) {
            MantenedorEmpresa mpp = new MantenedorEmpresa();
            mpp.setFormsController(this);
            mpp.setVisible(true);
        }
        if (idVista == 21 || idVista == 65) {
            MantenedorPerfil mpp = new MantenedorPerfil();
            mpp.setFormsController(this);
            mpp.setVisible(true);
        }
        if (idVista == 41 || idVista == 1) {
            MantenedorEvaluacion mantenedorEv = new MantenedorEvaluacion();
            mantenedorEv.setFormsController(this);
            mantenedorEv.setVisible(true);
        }
        if (idVista == 42 || idVista == 64) {
            MantenedorMenu mantendorMenu = new MantenedorMenu();
            mantendorMenu.setFormsController(this);
            mantendorMenu.setVisible(true);
        }
        if (idVista == 43 || idVista == 67) {
            MantenedorVistas mantendoVistas = new MantenedorVistas();
            mantendoVistas.setFormsController(this);
            mantendoVistas.setVisible(true);
        }
        if (idVista == 44 || idVista == 66) {
            MantenedorPerfilVistas mantendoPerfilesVistas = new MantenedorPerfilVistas();
            mantendoPerfilesVistas.setFormsController(this);
            mantendoPerfilesVistas.setVisible(true);
        }
        if (idVista == 62) {
            MantenedorPersona mantenedorPersona = new MantenedorPersona();
            mantenedorPersona.setFormsController(this);
            mantenedorPersona.setVisible(true);
        }
        if (idVista == 68) {
            FormularioEvaluacion formularioEv = new FormularioEvaluacion();
            formularioEv.setFormsController(this);
            formularioEv.setVisible(true);
        }
        if (idVista == 69) {
            MantenedorEvaluacionEstado mantenedorEstadoEv = new MantenedorEvaluacionEstado();
            mantenedorEstadoEv.setFormsController(this);
            mantenedorEstadoEv.setVisible(true);
        }
        if (idVista == 4) {
            MantenedorParametro mantenedorParametro = new MantenedorParametro();
            mantenedorParametro.setFormsController(this);
            mantenedorParametro.setVisible(true);
        }
        if (idVista == 101) {
            MantenedorCapEmpresa mantenedorCapEmpresa = new MantenedorCapEmpresa();
            mantenedorCapEmpresa.setFormsController(this);
            mantenedorCapEmpresa.setVisible(true);
        }
        if (idVista == 102) {
            MantenedorCapacitacion mantenedorCapacitacion = new MantenedorCapacitacion();
            mantenedorCapacitacion.setFormsController(this);
            mantenedorCapacitacion.setVisible(true);
        }
        if (idVista == 103) {
            MantenedorAsistencia mantenedorCapacitacionDia = new MantenedorAsistencia();
            mantenedorCapacitacionDia.setFormsController(this);
            mantenedorCapacitacionDia.setVisible(true);
        }
        if (idVista == 104) {
            MantenedorCapacitacionTipo mantenedorCapacitacionTipo = new MantenedorCapacitacionTipo();
            mantenedorCapacitacionTipo.setFormsController(this);
            mantenedorCapacitacionTipo.setVisible(true);
        }
        if (idVista == 105) {
            MantenedorEstadoCapacitacion mantenedorEstadoCapacitacion = new MantenedorEstadoCapacitacion();
            mantenedorEstadoCapacitacion.setFormsController(this);
            mantenedorEstadoCapacitacion.setVisible(true);
        }
        if (idVista == 45 || idVista == 81) {
            Login login = new Login();
            login.setVisible(true);
        }
    }
}
