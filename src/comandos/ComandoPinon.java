package comandos;

import constantes.Constantes;
import herramientas.Contenedor;
import cycling.Ciclista;
import java.util.StringTokenizer;

/**
 *
 * Le aigna el valor indicado al pinon de la bici siempre y cuando cumpla los
 * requistos
 *
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class ComandoPinon implements Command {

    private int pinon; // entre 1 y el numero establecido en las constantes
    private int indiceCiclistaQueSeModifica; //Indice del ciclista de la lista que sufre el comando
    private Ciclista ciclista; //Ciclista que sufre el comando

    public ComandoPinon() {
        pinon = 0;
        indiceCiclistaQueSeModifica = -1;
        ciclista = null;
    }

    @Override
    public void ejecutar() {
        ciclista.getBici().setPinon(pinon);
    }

    @Override
    public Command parser(String comando) {
        Command comandoADevolver = null;

        if (comando != null) {
            StringTokenizer str = new StringTokenizer(comando);

            if (str.hasMoreTokens()) {

                if (str.nextToken().equalsIgnoreCase("changePinon")) {

                    if (str.hasMoreTokens()) {
                        pinon = Integer.valueOf(str.nextToken());

                        if (pinon >= 1 && pinon <= Constantes.NUMERO_PINONES) {

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
        return "Sintaxis del comando:\nchangePinon [pinon(0-" + Constantes.NUMERO_PINONES + "] [idCiclista]";
    }

    @Override
    public void configureContext(Contenedor container) {
        ciclista = container.getListaCiclistas().get(indiceCiclistaQueSeModifica);
    }
}
