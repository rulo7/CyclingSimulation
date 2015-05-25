package comandos;

import herramientas.Contenedor;
import globalInterfaces.ObjetosQueSeEjecutan;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * Clase encargada de administrar, comprobar y ejecutar los comandos
 * que quieren ejecutarse y modificar los diferentes elementos que
 * lo conforman
 * 
 * @author Raul Cobos y Sergio Rodriguez
 */
public class Comandero implements ObjetosQueSeEjecutan{

    private Queue<Command> comandosQueSeEjecutan;
    private Contenedor contenedor;
    
    /**
     * Instancia y asigna valores a los atributos
     * @param _e
     * @param _p
     * @param _listaCiclistas 
     */
    public Comandero(Contenedor _contenedor){
        
        contenedor = _contenedor;
        
        comandosQueSeEjecutan = new LinkedList<>();
        
    }
    
    /**
     * Anade un comando a la cola de comandos
     * @param c 
     */
    public void addCommand(Command c){
        comandosQueSeEjecutan.add(c);
    }
        
    /**
     * Configura y ejecuta los distintos comandos de la cola de comandos
     */
    @Override
    public void ejecutar() {
        Command command = comandosQueSeEjecutan.poll();
        if(command != null){
            command.configureContext(contenedor);
            command.ejecutar();
        }
    }

    
    
}
