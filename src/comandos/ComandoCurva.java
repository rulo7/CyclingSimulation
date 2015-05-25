package comandos;

import herramientas.Contenedor;
import factores.Curva;
import java.util.StringTokenizer;

/**
 * Crea una curva en el punto y la velocidad maxima indicados
 *
 * @author Raul cobos y Sergio Rodrigueza
 */
public class ComandoCurva implements Command {

    private double puntoMetrico; //dec. de seg
    private double velocidadMaximaDePaso; //m/s
    Curva curvas;

    @Override
    public void ejecutar() {
        curvas.addCurva(puntoMetrico, velocidadMaximaDePaso);
    }

    @Override
    public void configureContext(Contenedor container) {
        curvas = container.getCurvas();
    }

    @Override
    public Command parser(String comando) {
        Command comandoADevolver = null;

        if (comando != null) {
            StringTokenizer str = new StringTokenizer(comando);

            if (str.hasMoreTokens()) {

                if (str.nextToken().equalsIgnoreCase("addCurva")) {

                    if (str.hasMoreTokens()) {

                        puntoMetrico = Integer.valueOf(str.nextToken());

                        if (puntoMetrico >= 0) {

                            if (str.hasMoreTokens()) {

                                velocidadMaximaDePaso = Double.valueOf(str.nextToken());

                                if (velocidadMaximaDePaso >= 0) {
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
        return "Sintaxis del comando:\naddCurva [puntoMetricoEnElQueSeSitua(m)] [velocidadMaximaDePaso(m/s)]";
    }
}
