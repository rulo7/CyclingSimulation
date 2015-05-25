/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import constantes.Constantes;
import herramientas.Contenedor;
import cycling.Ciclista;
import java.util.StringTokenizer;

/**
 * Abstrae la accion y ejecuta el metodo en el ciclista de frenar
 *
 * @author Sergio Rodriguez y Raul Cobos
 */
public class ComandoFrenar implements Command {

    private int duracionFrenada; //Segundos entre 1 y 6
    private double intensidadDeFrenada; //En porcentaje aunque en el execute se transforma /100
    private int indiceCiclistaQueSeModifica; //Indice del ciclista de la lista que sufre el comando
    private Ciclista ciclista; //Ciclista que sufre el comando

    public ComandoFrenar() {
        duracionFrenada = 0;
        intensidadDeFrenada = 0;
        indiceCiclistaQueSeModifica = -1;
        ciclista = null;
    }

    @Override
    public void ejecutar() {
        ciclista.frenar(duracionFrenada, intensidadDeFrenada);
    }

    @Override
    public Command parser(String comando) {
        Command comandoADevolver = null;

        if (comando != null) {
            StringTokenizer str = new StringTokenizer(comando);

            if (str.hasMoreTokens()) {

                if (str.nextToken().equalsIgnoreCase("frenar")) {

                    if (str.hasMoreTokens()) {
                        duracionFrenada = Integer.valueOf(str.nextToken());

                        if (duracionFrenada > 0 && duracionFrenada <= 6) {

                            if (str.hasMoreTokens()) {

                                intensidadDeFrenada = Double.valueOf(str.nextToken());

                                if (intensidadDeFrenada > 0 && intensidadDeFrenada <= 100) {

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
            }

        }

        return comandoADevolver;
    }

    @Override
    public String help() {
        return "Sintaxis del comando:\nfrenar [duracionFrenada(1-6)s] [intensidadFrenada(1-100)%] [idCiclista]";
    }

    @Override
    public void configureContext(Contenedor container) {
        ciclista = container.getListaCiclistas().get(indiceCiclistaQueSeModifica);
    }
}
