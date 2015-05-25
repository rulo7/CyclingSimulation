package factores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

import tiempo.Reloj;
import constantes.Constantes;
import cycling.Ciclista;
import ficheros.FicheroEntrada;
import globalInterfaces.ObjetosQueSeEjecutan;
import herramientas.Logger;

/**
 * Clase que simula la abstraccion del viento en la aplicacion
 *
 * velocidadDelViento - m/s - negativo si es en contra y viciversa
 * decimaDeSegundoEnLaQueSeProduceElviento - decimas de segundo
 *
 * @author Raul Cobos y Sergio Rodriguez
 *
 */
public class Eolo implements ObjetosQueSeEjecutan {

    // ________Atributos_____________ //
    private ArrayList<Ciclista> listaCiclistas;
    private TreeMap<Integer, Double> mapaEolo;
    private Double velocidadVientoActual;

    // Logger loggin;
    public Eolo(ArrayList<Ciclista> _listaCiclistas) {
        listaCiclistas = _listaCiclistas;
        mapaEolo = new TreeMap<>();

        leerVientosDelArchivo();

        if (!mapaEolo.containsKey(0)) {
            mapaEolo.put(0, 0.0);
        }

        velocidadVientoActual = mapaEolo.get(0);

        // InstanciarLoggin
    }

    /**
     * Introduce los valores de los vientos alojados en el archivo ubicado en la
     * ruta que especifica la clase Constates al mapa
     */
    private void leerVientosDelArchivo() {

        String archivo = FicheroEntrada.leer_archivo(Constantes.ARCHIVO_EOLO);
        StringTokenizer str = new StringTokenizer(archivo);

        while (str.hasMoreTokens()) {

            int decimasDeSegundo = Integer.parseInt(str.nextToken());

            double velocidadViento = Double.parseDouble(str.nextToken());

            mapaEolo.put(decimasDeSegundo, velocidadViento);
        }
    }

    /**
     * Anade al mapa un nuevo viento En caso de que la clave ya exista se
     * sobreescribe
     *
     * @param decimaDeSegundo
     * @param velocidad del Viento
     */
    public void addEolo(int decimaDeSegundo, double velocidadViento) {

        if (decimaDeSegundo >= 0) {
            mapaEolo.put(decimaDeSegundo, velocidadViento);

            Logger.getInstanciaLogger().addMensaje("Se ha creado un viento con:\n"
                    + "Tiempo en el que se Produce: " + decimaDeSegundo + "\n"
                    + "Velocidad del viento: " + velocidadViento);
        }
    }

    public TreeMap<Integer, Double> getMapa() {
        return mapaEolo;
    }

    @Override
    public void ejecutar() {

        double aceleracionEolo = 0;
        double velocidadSiguiente = velocidadVientoActual;
        int tiempoActual = Reloj.getInstanciaDelReloj().getPulsos();

        //Se crea un iterador para recorrer el mapa
        Iterator<Entry<Integer, Double>> it = mapaEolo.entrySet().iterator();
        Entry<Integer, Double> elem;

        //Se recorre el mapa hasta encontrar el fin de los valores o la clave mas cercana al tiempo del reloj
        if (it.hasNext()) {

            do {

                elem = it.next();

                if (elem.getKey() <= tiempoActual) {

                    //la velocidadSiguiente es negativa teniendo en cuenta que cuando la velocidad
                    //del viento salga por debajo de cero sera en contra y viceversa
                    velocidadSiguiente = elem.getValue();
                    aceleracionEolo = Math.pow(velocidadSiguiente / 0.837, 3 / 2) / 100;

                }

            } while (it.hasNext() && elem.getKey() <= tiempoActual);


            // Se actualiza la nueva velocidad del viento
            if (velocidadSiguiente != velocidadVientoActual) {
                velocidadVientoActual = velocidadSiguiente;

                Logger.getInstanciaLogger().addMensaje("Cambio de viento: " + velocidadVientoActual + " m/s");
            }

        }

        // Actua la aceleracion del viento en todos los ciclistas
        for (int i = 0; i < listaCiclistas.size(); i++) {
            if (listaCiclistas.get(i).getEstaVivo()) {
                listaCiclistas.get(i).setVelocidadVientoActual(velocidadVientoActual);
                listaCiclistas.get(i).modificarVelocidad(aceleracionEolo * 0.1);
                listaCiclistas.get(i).getBici().setAceleracionEolo(aceleracionEolo);
            }
        }

    }
}
