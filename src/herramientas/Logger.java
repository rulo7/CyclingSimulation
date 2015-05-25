package herramientas;

import constantes.Constantes;
import ficheros.FicheroSalida;
import java.io.IOException;
import java.util.ArrayList;
import tiempo.Reloj;

/**
 *
 * Registra todos los sucesos que se producen en la aplicacion
 *
 * @author Raul Coboc y Sergio Rodriguez
 */
public class Logger {

    private static Logger log = null;
    private ArrayList<String> mensajes;
    Reloj reloj;

    /**
     * privado para el patron Singleton
     */
    private Logger() {
        reloj = Reloj.getInstanciaDelReloj();
        mensajes = new ArrayList<>();
    }

    /**
     * Devuelvela instancia del propio log y lo instancia si no se ha hecho ya
     * antes se asegura de que solo sea una vez instanciado
     *
     * @return la instancia del unico objeto log instanciado
     */
    public static Logger getInstanciaLogger() {
        if (log == null) {
            log = new Logger();
        }
        return log;
    }

    public void addMensaje(String msj) {
        mensajes.add("[" + reloj.getTiempoAplicacion() + "] - " + msj);
    }

    /**
     * Devuelve todos los mensajes en un solo String
     *
     * @return mensajes
     */
    public String getMensajes() {

        String allMsj = "";

        for (String s : mensajes) {
            allMsj = allMsj + s + "\n";
        }

        return allMsj;
    }

    /**
     * Devuelve de numero de mensajes almacenados en el logF
     *
     * @return numero de mensajes loggeados
     */
    public int numMsj() {
        return mensajes.size();
    }
    
    /**
     * Guarda los mensajes en un archivo de texto
     */
    public void guardarMensajes(){
        FicheroSalida.escribir_archivo(Constantes.ARCHIVO_LOGGER, getMensajes());
    }
}
