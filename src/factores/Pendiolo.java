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
 * Clase que simula la abstraccion de las pendientes en la aplicacion
 *
 * puntoMetrico donde comienza la pendiente m pendientePorcentual (0-100)%
 * negativo si es cuesta abajo y viceversa
 *
 * @author Raul Cobos y Sergio Rodriguez
 *
 */
public class Pendiolo implements ObjetosQueSeEjecutan {

    // ________Atributos_____________ //
    private ArrayList<Ciclista> listaCiclistas;
    private TreeMap<Double, Double> mapaPendiolo;
    private boolean actualiza;

    public Pendiolo(ArrayList<Ciclista> _listaCiclistas) {

        listaCiclistas = _listaCiclistas;
        mapaPendiolo = new TreeMap<>();
        actualiza = false;

        leerPendientesDelArchivo();

        if (!mapaPendiolo.containsKey(0.0)) {
            mapaPendiolo.put(0.0, 0.0);
        }

        double pendienteActual = mapaPendiolo.get(0.0);

        for (Ciclista c : listaCiclistas) {
            c.setFinalRecorrido(mapaPendiolo.lastKey());
            c.setPendienteActual(pendienteActual);
        }

    }

    public double getFinalRecorrido() {
        return mapaPendiolo.lastKey();
    }

    public boolean getActualizado() {

        boolean devolver = false;

        if (actualiza) {
            devolver = true;
            actualiza = false;
        }

        return devolver;
    }

    /**
     * Introduce los valores de las pendientes alojados en el archivo ubicado en
     * la ruta que especifica la clase Constates al mapa
     */
    private void leerPendientesDelArchivo() {

        String archivo = FicheroEntrada
                .leer_archivo(Constantes.ARCHIVO_PENDIOLO);
        StringTokenizer str = new StringTokenizer(archivo);

        while (str.hasMoreTokens()) {

            double puntoMetrico = Double.parseDouble(str.nextToken());

            double pendientePorcentual = Double.parseDouble(str.nextToken());

            if (puntoMetrico >= 0 && pendientePorcentual > -100
                    && pendientePorcentual < 100) {
                mapaPendiolo.put(puntoMetrico, pendientePorcentual);
            }
        }
    }

    /**
     * Anade una nueva pendiente al mapa, en el caso de que ya exista una
     * pendiente en ese punto, se reemplazara
     *
     * @param puntoMetrico
     * @param pendientePorcentual
     */
    public void addPendiolo(double puntoMetrico, double pendientePorcentual) {
        if (puntoMetrico >= 0 && pendientePorcentual > -100
                && pendientePorcentual < 100) {
            mapaPendiolo.put(puntoMetrico, pendientePorcentual);

            for (Ciclista c : listaCiclistas) {
                c.setFinalRecorrido(mapaPendiolo.lastKey());
            }

            Logger.getInstanciaLogger().addMensaje("Se ha creado una pendiente con:\n"
                    + "Punto en el que empieza: " + puntoMetrico + "\n"
                    + "Pendiente porcentual: " + pendientePorcentual);

            actualiza = true;
        }

    }

    public TreeMap<Double, Double> getMapa() {
        return mapaPendiolo;
    }

    @Override
    public void ejecutar() {



        double aceleracionPendiolo;

        for (int i = 0; i < listaCiclistas.size(); i++) {

            if (listaCiclistas.get(i).getEstaVivo()) {

                double pendienteActualParaElCiclista = listaCiclistas.get(i).getPendienteActual();
                double pendienteSiguiente = pendienteActualParaElCiclista;

                aceleracionPendiolo = 0;
                Iterator<Entry<Double, Double>> it = mapaPendiolo.entrySet().iterator();

                while (it.hasNext()) {
                    Entry<Double, Double> elem = it.next();

                    if (elem.getKey() <= listaCiclistas.get(i).getBici().getDistanciaRecorrida()) {
                        aceleracionPendiolo = -Constantes.G * Math.sin((elem.getValue() * Constantes.PI) / 200);
                        pendienteSiguiente = elem.getValue();
                    }

                    // Si la pendiente fuera cuestabajo o negativa
                    // esta seria positiva al transmitirsela al ciclista
                    // lo cual haria que actuase a su favor

                }


                listaCiclistas.get(i).modificarVelocidad(aceleracionPendiolo * 0.1);

                if (pendienteSiguiente != pendienteActualParaElCiclista) {
                    listaCiclistas.get(i).setPendienteActual(pendienteSiguiente);
                }

                listaCiclistas.get(i).getBici().setAceleracionPendiolo(aceleracionPendiolo);

            }
        }
    }
}
