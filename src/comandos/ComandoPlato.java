package comandos;

import constantes.Constantes;
import herramientas.Contenedor;
import cycling.Ciclista;
import java.util.StringTokenizer;

/**
 *
 * Le aigna el valor indicado al plato de la bici siempre y cuando cumpla los
 * requistos
 *
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class ComandoPlato implements Command {

    private int plato; // entre 1 y el numero establecido en las constantes
    private int indiceCiclistaQueSeModifica; //Indice del ciclista de la lista que sufre el comando
    private Ciclista ciclista; //Ciclista que sufre el comando

    public ComandoPlato() {
        plato = 0;
        indiceCiclistaQueSeModifica = -1;
        ciclista = null;
    }

    @Override
    public void ejecutar() {
        ciclista.getBici().setPlato(plato);
    }

    @Override
    public Command parser(String comando) {
        Command comandoADevolver = null;

        if (comando != null) {
            StringTokenizer str = new StringTokenizer(comando);

            if (str.hasMoreTokens()) {

                if (str.nextToken().equalsIgnoreCase("changePlato")) {

                    if (str.hasMoreTokens()) {
                        plato = Integer.valueOf(str.nextToken());

                        if (plato >= 1 && plato <= Constantes.NUMERO_PLATOS) {

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
        return "Sintaxis del comando:\nchangePlato [plato(0-" + Constantes.NUMERO_PLATOS + "] [idCiclista]";
    }

    @Override
    public void configureContext(Contenedor container) {
        ciclista = container.getListaCiclistas().get(indiceCiclistaQueSeModifica);
    }
}
