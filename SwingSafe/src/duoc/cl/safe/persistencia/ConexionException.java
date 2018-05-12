/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duoc.cl.safe.persistencia;

/**
 *
 * @author Nacho
 * 
 * Clase que hereda de Exception
 */
public class ConexionException extends Exception {

    public ConexionException(String message) {
        super(message);
    }
    
}
