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
public class ComandoCadencia implements Command {

    private int cadencia; // entre 0 y 120
    private int indiceCiclistaQueSeModifica; //Indice del ciclista de la lista que sufre el comando
    private Ciclista ciclista; //Ciclista que sufre el comando

    public ComandoCadencia() {
        cadencia = 0;
        indiceCiclistaQueSeModifica = -1;
        ciclista = null;
    }

    @Override
    public void ejecutar() {
        ciclista.setCadencia(cadencia);
    }

    @Override
    public Command parser(String comando) {
        Command comandoADevolver = null;

        if (comando != null) {
            StringTokenizer str = new StringTokenizer(comando);

            if (str.hasMoreTokens()) {

                if (str.nextToken().equalsIgnoreCase("changeCadencia")) {

                    if (str.hasMoreTokens()) {
                        cadencia = Integer.valueOf(str.nextToken());

                        if (cadencia >= 0 && cadencia <= 120) {

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
        return "Sintaxis del comando:\nchangeCadencia [cadencia(0-120)pedaladas/min] [idCiclista]";
    }

    @Override
    public void configureContext(Contenedor container) {
        ciclista = container.getListaCiclistas().get(indiceCiclistaQueSeModifica);
    }
}
