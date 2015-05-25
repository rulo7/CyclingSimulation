package comandos;

import constantes.Constantes;
import ficheros.FicheroEntrada;
import globalInterfaces.ObjetosQueSeEjecutan;
import java.util.StringTokenizer;

/**
 *
 * Comprende y ejecuta comandos alojados en un archivo de texto
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class ComandosQueSeEjecutanArchivoTexto implements ObjetosQueSeEjecutan {

    private Parser parser;
    private String listaComandosTxt;

    public ComandosQueSeEjecutanArchivoTexto(Parser p) {

        parser = p;
        listaComandosTxt = null;
    }

    @Override
    public void ejecutar() {

        listaComandosTxt = FicheroEntrada.leer_archivo(Constantes.ARCHIVO_COMANDOS);
        StringTokenizer str = new StringTokenizer(listaComandosTxt, ";");

        String comando;


        while (str.hasMoreTokens()) {
            comando = str.nextToken();

            parser.addStringCommand(comando);
        }
    }
}
