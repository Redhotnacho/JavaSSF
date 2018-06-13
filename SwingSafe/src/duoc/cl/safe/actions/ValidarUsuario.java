/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.actions;

import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.negocio.SsfUsuarioBO;
import duoc.cl.safe.herramientas.Cargando;
import duoc.cl.safe.herramientas.FormsController;
import duoc.cl.safe.herramientas.Menu;
import duoc.cl.safe.presentacion.usuarios.Login;

/**
 *
 * @author yerko
 */
public class ValidarUsuario implements Runnable {

    private String username;
    private String password;
    private Login login;
    private Cargando cargando;

    @Override
    public void run() {
        SsfUsuarioBO ubo;
        ubo = new SsfUsuarioBO();
        cargando.jLabel2.setText("Validando Usuario...");
        SsfUsuario usuarioSesion = ubo.validaUsuarioSP(username, password);
        if (usuarioSesion == null) {
            login.incorecto();
        } else {
            cargando.jLabel2.setText("Buscando Permisos...");
            Menu menu = new Menu(usuarioSesion);
            menu.menu();
            FormsController formsController = new FormsController(2, menu);
            formsController.abrirJframe();
            login.dispose();
            cargando.dispose();
        }
    }

    public ValidarUsuario(Login login, Cargando cargando, String username, String password) {
        this.login = login;
        this.cargando = cargando;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
