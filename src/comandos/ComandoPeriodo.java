package comandos;

import constantes.Constantes;
import herramientas.Contenedor;
import cycling.Ciclista;
import java.util.StringTokenizer;

/**
 *
 * Le aigna el valor indicado a la cadencia siempre y cuando cumpla los
 * requistos
 *
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class ComandoPeriodo implements Command {

    private double tiempoEnDarLaPedalada; // entre 0 y 2.0
    private int indiceCiclistaQueSeModifica; //Indice del ciclista de la lista que sufre el comando
    private Ciclista ciclista; //Ciclista que sufre el comando

    public ComandoPeriodo() {
        tiempoEnDarLaPedalada = 0;
        indiceCiclistaQueSeModifica = -1;
        ciclista = null;
    }

    @Override
    public void ejecutar() {
        ciclista.setTiempoEnDarLaPedalada(tiempoEnDarLaPedalada);
    }

    @Override
    public Command parser(String comando) {
        Command comandoADevolver = null;

        if (comando != null) {
            StringTokenizer str = new StringTokenizer(comando);

            if (str.hasMoreTokens()) {

                if (str.nextToken().equalsIgnoreCase("changePeriodo")) {

                    if (str.hasMoreTokens()) {
                        tiempoEnDarLaPedalada = Double.valueOf(str.nextToken());

                        if (tiempoEnDarLaPedalada > 0.0 && tiempoEnDarLaPedalada <= 2.0) {

                            if (str.hasMoreTokens()) {
                                indiceCiclistaQueSeModifica = Integer.valueOf(str.nextToken());

                                if (indiceCiclistaQueSeModifica >= 0 && indiceCiclistaQueSeModifica < Constantes.NUMERO_CICLISTAS) {
                                    comandoADevolver = this;
                                }

                            }
                        }
                    }
                }
            }
        }
        return comandoADevolver;
    }

    @Override
    public String help() {
        return "Sintaxis del comando:\nchangePeriodo [periodo(0-2.0)s] [idCiclista]";
    }

    @Override
    public void configureContext(Contenedor container) {
        ciclista = container.getListaCiclistas().get(indiceCiclistaQueSeModifica);
    }
}
