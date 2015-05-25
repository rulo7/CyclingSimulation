/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import herramientas.Contenedor;
import factores.Pendiolo;
import java.util.StringTokenizer;

/**
 *
 * Crea una nueva pendiente en algun lugar del mapa
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class ComandoPendiolo implements Command {

    private double puntoMetrico; // m
    private double pendientePorcentual; //(0-100) %
    Pendiolo pendiolo;

    @Override
    public void ejecutar() {
        pendiolo.addPendiolo(puntoMetrico, pendientePorcentual);
    }

    @Override
    public void configureContext(Contenedor container) {
        pendiolo = container.getPendiolo();
    }

    @Override
    public Command parser(String comando) {
        Command comandoADevolver = null;

        if (comando != null) {
            StringTokenizer str = new StringTokenizer(comando);

            if (str.hasMoreTokens()) {
                if (str.nextToken().equalsIgnoreCase("addPendiolo")) {

                    if (str.hasMoreTokens()) {

                        puntoMetrico = Double.valueOf(str.nextToken());

                        if (puntoMetrico >= 0) {

                            if (str.hasMoreTokens()) {

                                pendientePorcentual = Double.valueOf(str.nextToken());

                                if (pendientePorcentual > -100 && pendientePorcentual < 100) {
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
        return "Sintaxis del comando:\naddPendiolo [puntoMetricoDondeComienza(m)] [pendientePorcentual(0-100)%]";
    }
}
