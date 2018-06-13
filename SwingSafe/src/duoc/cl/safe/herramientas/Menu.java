/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.herramientas;

import duoc.cl.safe.entity.SsfMenu;
import duoc.cl.safe.entity.SsfPerfilvista;
import duoc.cl.safe.entity.SsfUsuario;
import duoc.cl.safe.entity.SsfVista;
import duoc.cl.safe.negocio.SsfMenuBO;
import duoc.cl.safe.negocio.SsfPerfilvistaBO;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author yerko
 */
public class Menu {

    private JMenuBar menuBar;
    private JMenu menu, submenu;
    private JMenuItem menuItem;
    private JRadioButtonMenuItem rbMenuItem;
    private JCheckBoxMenuItem cbMenuItem;
    private javax.swing.JFrame jFrame;
    private FormsController formsController;
    private SsfUsuario usuarioSesion;
    List<SsfMenu> menuList;

    public Menu() {
    }

    public Menu(SsfUsuario usuarioSesion) {
        this.usuarioSesion = usuarioSesion;
    }

    public void menu() {
        SsfMenuBO menubo = new SsfMenuBO();
        if (menuList == null) {
            menuList = menubo.getAllSP();
        }

        SsfPerfilvistaBO perfilVista = new SsfPerfilvistaBO();
        List<SsfPerfilvista> perfilVistaList = perfilVista.getVistasXPerfilSP(Integer.parseInt(usuarioSesion.getIdPerfil().getId().toString()));
        HashMap<BigDecimal, Boolean> perfilVistaHash = new HashMap<>();

        perfilVistaList.forEach((perfilVistaObject) -> {
            if (perfilVistaObject.getEstado() == 1) {
                //System.out.println(perfilVistaObject.getIdVista().getEstado() + " - " +perfilVistaObject.getIdVista().getNombre());
                BigDecimal idVista = perfilVistaObject.getIdVista().getId();
                perfilVistaHash.put(idVista, true);
            }
        });
        System.out.println("**************************");
        menuBar = new JMenuBar();

        for (SsfMenu ssfmenu : menuList) {
            menu = new JMenu(ssfmenu.getNombre());
            int contador = 0;
//            System.out.println(ssfmenu.getNombre()+"********");
            for (SsfVista ssfvista : ssfmenu.getSsfVistaList()) {
                Boolean value = perfilVistaHash.get(ssfvista.getId());
                if (value != null) {
//                    System.out.println(ssfvista.getId());
                    if (ssfvista.getEstado() == 1) {
                        System.out.println(ssfvista.getEstado());
                        contador++;
                        menuItem = new JMenuItem(new AbstractAction(ssfvista.getNombre()) {
                            public void actionPerformed(ActionEvent e) {
                                int idVista = Integer.parseInt(ssfvista.getId().toString());
                                FormsController form = new FormsController(idVista, Menu.this);
                                form.abrirJframe();
                                jFrame.dispose();
                            }
                        });
                        menu.add(menuItem);
                    }
                }
            }
            if (contador > 0) {
                menuBar.add(menu);
            }
        }
        if (usuarioSesion.getId().intValue() != 102) {
            menu = new JMenu("Usuario");
            menuItem = new JMenuItem(new AbstractAction("Cerrar Sesión") {
                public void actionPerformed(ActionEvent e) {
                    FormsController form = new FormsController(45, Menu.this);
                    form.abrirJframe();
                    jFrame.dispose();
                }
            });
            menu.add(menuItem);
            menuBar.add(menu);
        }

        //Build the first menu.
        //menu.setMnemonic(KeyEvent.VK_A);
//        menu.getAccessibleContext().setAccessibleDescription(
//                "The only menu in this program that has menu items");
        //a group of JMenuItems
//        menuItem = new JMenuItem(new AbstractAction("Menu Item Clickeable"){
//            public void actionPerformed(ActionEvent e){
//                System.out.println("Clicked Action Menu item");
//            }
//        });
//        menuItem.setAccelerator(KeyStroke.getKeyStroke( //acceso rapido
//                KeyEvent.VK_1, ActionEvent.ALT_MASK));
//        menuItem.getAccessibleContext().setAccessibleDescription(
//                "This doesn't really do anything");
//        menu.add(menuItem);
//        menu.addSeparator();
//        menuItem = new JMenuItem("Both text and icon"/*,new ImageIcon("images/middle.gif")*/);
//        menuItem.setMnemonic(KeyEvent.VK_B);
//        menu.add(menuItem);
//
//        menuItem = new JMenuItem("Prueba"/*new ImageIcon("images/middle.gif")*/);
//        menuItem.setMnemonic(KeyEvent.VK_D);
//        menu.add(menuItem);
//        
//        
//        //a submenu
//        menu.addSeparator();
//        submenu = new JMenu("A submenu");
//        submenu.setMnemonic(KeyEvent.VK_S);
//
//        menuItem = new JMenuItem("An item in the submenu");
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_2, ActionEvent.ALT_MASK));
//        submenu.add(menuItem);
//
//        menuItem = new JMenuItem("Another item");
//        submenu.add(menuItem);
//        menu.add(submenu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }

    public void setMenuBar(JMenuBar menuBar) {
        this.menuBar = menuBar;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setjFrame(JFrame jFrame) {
        this.jFrame = jFrame;
    }

}
