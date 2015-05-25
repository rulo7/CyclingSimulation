package factores;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

import constantes.Constantes;
import cycling.Ciclista;
import ficheros.FicheroEntrada;
import globalInterfaces.ObjetosQueSeEjecutan;
import herramientas.Logger;

/**
 * Clase que simula la abstraccion de una curva en la aplicacion Si algun
 * ciclista no pasa a la velocidad indicada en la curva este muere
 *
 * velocidadMaximaDePaso m/s puntoMetrico m
 *
 * @author Raul Cobos y Sergio Rodriguez
 *
 */
public class Curva implements ObjetosQueSeEjecutan {

    // ________Atributos_____________ //
    private ArrayList<Ciclista> listaCiclistas;
    private TreeMap<Double, Double> mapaCurvas;
    private boolean actualiza;
    private int numCurvas;

    public Curva(ArrayList<Ciclista> _listaCiclistas) {
        listaCiclistas = _listaCiclistas;
        mapaCurvas = new TreeMap<>();
        leerCurvasDelArchivo();
        actualiza = false;
    }

    /**
     * Introduce los valores de las pendientes alojados en el archivo ubicado en
     * la ruta que especifica la clase Constates al mapa
     */
    private void leerCurvasDelArchivo() {

        String archivo = FicheroEntrada.leer_archivo(Constantes.ARCHIVO_CURVAS);
        StringTokenizer str = new StringTokenizer(archivo);

        while (str.hasMoreTokens()) {

            double puntoKilometrico = Double.parseDouble(str.nextToken());

            double velocidadMaximaDePaso = Double.parseDouble(str.nextToken());

            if (puntoKilometrico >= 0 && velocidadMaximaDePaso >= 0) {
                mapaCurvas.put(puntoKilometrico, velocidadMaximaDePaso);
                numCurvas++;
            }
        }
    }

    public int getNumCurvas() {
        return numCurvas;
    }

    /**
     * Anade una nueva pendiente al mapa, en el caso de que ya exista una
     * pendiente en ese punto, se reemplazara
     *
     * @param puntoMetrico
     * @param pendientePorcentual
     */
    public void addCurva(double puntoMetrico, double velocidadMaximaDePaso) {
        if (puntoMetrico >= 0 && velocidadMaximaDePaso >= 0) {

            mapaCurvas.put(puntoMetrico, velocidadMaximaDePaso);

            actualiza = true;
            numCurvas++;

            Logger.getInstanciaLogger().addMensaje("Se ha creado una curva con:\n"
                    + "Punto donde se situa: " + puntoMetrico + "\n"
                    + "Velocidad maxima de paso: " + velocidadMaximaDePaso);
        }
    }

    public boolean getActualizado() {

        boolean devolver = false;

        if (actualiza) {
            devolver = true;
            actualiza = false;
        }

        return devolver;
    }

    public TreeMap<Double, Double> getMapa() {
        return mapaCurvas;
    }

    @Override
    public void ejecutar() {

        boolean comprobar;

        int numeroCurva;

        Ciclista ciclista;

        for (int i = 0; i < listaCiclistas.size(); i++) {

            numeroCurva = 0;
            ciclista = listaCiclistas.get(i);

            comprobar = false;

            Iterator<Entry<Double, Double>> it = mapaCurvas.entrySet()
                    .iterator();

            Entry<Double, Double> elem;

            while (!comprobar && it.hasNext()) {

                numeroCurva++;

                elem = it.next();

                double distanciaRecorrida = ciclista.getBici().getDistanciaRecorrida();

                // Rango de duracion de la curva (5 al entrar y 5 al salir = 5m)
                if (elem.getKey() - 5 <= distanciaRecorrida
                        && elem.getKey() + 5 >= distanciaRecorrida) {

                    if (ciclista.getVelocidad() > elem.getValue()) {


                        if (ciclista.getEstaVivo()) {

                            Logger.getInstanciaLogger().addMensaje("Ciclista[" + ciclista.getIdCiclista()
                                    + "] muerto en la curva situada a los " + elem.getKey() + " m");

                        }


                        ciclista.setEstaVivo(false);

                        comprobar = true;
                    }
                }

            }
        }

    }
}
