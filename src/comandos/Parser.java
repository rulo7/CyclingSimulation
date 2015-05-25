package comandos;

import factores.Curva;
import globalInterfaces.ObjetosQueSeEjecutan;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Reconoce y traduce los comandos que se leen a comandos de la clase Command
 *
 * @author Raul Cobos y Sergio rodriguez
 */
public class Parser implements ObjetosQueSeEjecutan {

    private ArrayList<Command> listaComandos;
    private Queue<String> comandosQueQuierenPasearse;
    private Comandero comandero;

    /**
     * Constructor que inicializa la cola y la lista y asigna un valor al
     * comandero
     *
     * @param _comandero
     */
    public Parser(Comandero _comandero) {
        comandero = _comandero;
        listaComandos = new ArrayList<>();
        comandosQueQuierenPasearse = new LinkedList<>();

        addAllCommands();

    }

    /**
     * Anade todos los comandos existentes a la lista de comandos para poder
     * identificarlos
     */
    private void addAllCommands() {
        
        //Pendiente
        listaComandos.add(new ComandoPendiolo());
        //Eolo
        listaComandos.add(new ComandoEolo());
        //Curva
        listaComandos.add(new ComandoCurva());
        //Cadencia
        listaComandos.add(new ComandoCadencia());
        //Periodo
        listaComandos.add(new ComandoPeriodo());
        //Platos
        listaComandos.add(new ComandoPlato());
        //Pinones
        listaComandos.add(new ComandoPinon());
        //Frenar
        listaComandos.add(new ComandoFrenar());
        //Help
        listaComandos.add(new ComandoHelp(this.listaComandos));
    }

    /**
     * Añade a la cola de comandos que se quieren parsear un comando mas
     *
     * @param c texto que quiere transformarse en comando
     */
    public void addStringCommand(String c) {
        comandosQueQuierenPasearse.add(c);
    }

    /**
     * Parsea y comprueba la existencia de verdaderos comandos en la lista de
     * comandos que se desean parsear. Si pasan el parseo, se comprueba que
     * comando es y una vez comprobado se envia a la lista de comandos que se
     * quieren ejecutar del comandero
     */
    @Override
    public void ejecutar() {

        Command comando = null;
        String comandoTxt = comandosQueQuierenPasearse.poll();

        if (comandoTxt != null) {
            for (Command c : listaComandos) {
                //Solo se parsea hasta que se reconoce un comando
                if (comando == null) {
                    // si existe se transmite el String como comando de la clase
                    comando = c.parser(comandoTxt);
                }
            }// Comprueba si alguno de los comandos existentes coinciden con el de la cola

            // Comprueba si se ha reconocido algun comando del txt y en ese caso
            // lo anade a la cola de comandos para ejecutarse del comandero
            if (comando != null) {
                comandero.addCommand(comando);
            }

        }


    }

    public Comandero getComandero() {
        return comandero;
    }
}
