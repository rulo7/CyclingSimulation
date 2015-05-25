/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import herramientas.Contenedor;

/**
 *
 * Interfaz que implementa los metodos necesarios para un comando
 * 
 * @author Raul Cobos y Sergio Rodriguez
 */
public interface Command {
    
    public void ejecutar();
    public void configureContext(Contenedor container);
    public Command parser(String comando);
    public String help();
    
}
