package comandos;

import herramientas.Contenedor;
import factores.Eolo;
import java.util.StringTokenizer;

/**
 *
 * Cambia o introduce un viento en un momento determinado
 *
 * @author Raul Cobos y Sergio rodriguez
 */
public class ComandoEolo implements Command {

    private int decimasDeSegundoEnElQueSeProduceElViento; //dec. de seg
    private double veloCidadDelViento; //m/s
    Eolo eolo;

    @Override
    public void ejecutar() {
        eolo.addEolo(decimasDeSegundoEnElQueSeProduceElViento, veloCidadDelViento);
    }

    @Override
    public void configureContext(Contenedor container) {
        eolo = container.getEolo();
    }

    @Override
    public Command parser(String comando) {
        Command comandoADevolver = null;

        if (comando != null) {
            StringTokenizer str = new StringTokenizer(comando);

            if (str.hasMoreTokens()) {
                if (str.nextToken().equalsIgnoreCase("addEolo")) {

                    if (str.hasMoreTokens()) {

                        decimasDeSegundoEnElQueSeProduceElViento = Integer.valueOf(str.nextToken());

                        if (decimasDeSegundoEnElQueSeProduceElViento >= 0) {

                            if (str.hasMoreTokens()) {

                                veloCidadDelViento = Double.valueOf(str.nextToken());
                                comandoADevolver = this;

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
        return "Sintaxis del comando:\naddEolo [momentoEnElQueSeProduce(1/10 seg)] [velocidadDelViento(< 0 para viento en contra)m/s]";
    }
}
