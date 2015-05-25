package cycling;

import constantes.Constantes;
import herramientas.Logger;

/**
 * Clase que representa la abstraccion de un objeto biciceta
 *
 * @author Raul Cobos y Sergio Rodriguez // *
 */
public class Bicicleta {

    // ________Atributos_____________ //
    private double distanciaRecorrida; // m
    private double masa; // Kg
    private int[] pinones;
    private int[] platos;
    private int pinonActual;
    private int platoActual;
    private double radioRueda; // metros
    private double aceleracionEolo; // m / s^2
    private double aceleracionPendiolo; // m / s^2
    private int id_bicicleta;

    /**
     * Asigna valores por defecto a la bicicleta Recibe un numero de
     * identificacion para el objeto
     *
     * @param _id_bicicleta
     */
    public Bicicleta(int _id_bicicleta) {

        distanciaRecorrida = 0;
        masa = Constantes.MASA_BICICLETA;
        pinones = new int[Constantes.NUMERO_PINONES];
        platos = new int[Constantes.NUMERO_PLATOS];
        asignarValorDientesPinones();
        asignarValorDientesPlatos();
        pinonActual = Constantes.PINON_INICIAL - 1;
        platoActual = Constantes.PLATO_INICIAL - 1;
        radioRueda = Constantes.RADIO_RUEDA;
        aceleracionEolo = 0;
        aceleracionPendiolo = 0;
        id_bicicleta = _id_bicicleta;

    }

    // ____________________ Calculos del movimiento de la bicicleta ________________ //
    /**
     * Calcula la relacion de transmision entre los platos y los pi�ones de la
     * bicicleta
     *
     * @return relacion de transmision: dientes plato actual / dientes po�on
     * actual
     *
     */
    private double getRelacionDeTransmision() {
        return platos[platoActual] / pinones[pinonActual];
    }

    /**
     * Calcula el recorrido que realiza en una vuelta
     *
     * @return recorrido lineal de una rueda al dar una vuelta
     */
    private double getRecorridoLinealRueda() {
        return radioRueda * Constantes.PI;
    }

    /**
     * Calcula el espacio que se recorre cada vez que el ciclista efectua una
     * pedalada completa
     *
     * @return espacio recorrido con cada pedalada
     */
    public double getEspacioPorPedalada() {
        return getRelacionDeTransmision() * getRecorridoLinealRueda();
    }

    // ____________________ Metodos que simulan el movimiento de la bicicleta ________________ //
    /**
     * Simula la accion de dar una pedalada, modificando la velocidad de la
     * bicicleta y produciendo una aceleracion
     *
     * @param tiempo que tarda el ciclista en dar una pedalada
     * @return la aceleracion que produce cada pedalada completa
     */
    public double aceleracionPedalada(double _tiempoEnDarUnaPedalada) {
        
        double aceleracion = getEspacioPorPedalada() / Math.pow(_tiempoEnDarUnaPedalada, 2);
        
        return aceleracion;
    }

    // ____________________ Gets & Sets ____________________ //
    /**
     * Devuelve la distancia recorrida en KM
     *
     * @return distancia recorrida por el ciclista
     */
    public double getDistanciaRecorrida() {
        return distanciaRecorrida;
    }

    public double getMasa() {
        return masa;
    }

    public int getId_Bicicleta() {
        return id_bicicleta;
    }

    public double getAceleracionPendiolo() {
        return aceleracionPendiolo;
    }

    public double getAceleracionEolo() {
        return aceleracionEolo;
    }

    public int getPinon() {
        return pinonActual;
    }

    public int getPlato() {
        return platoActual;
    }

    public boolean setPinon(int pinon) {
        boolean exito = true;
        if (pinon > 0 && pinon <= Constantes.NUMERO_PINONES) {
            pinonActual = pinon - 1;
            
            Logger.getInstanciaLogger().addMensaje("Se ha cambiado el pinon de la bicicleta del ciclista[" + id_bicicleta + "]:\n"
                    + "Pinon: " + pinon
                    + "\n");

            
        } else {
            exito = false;
            
            Logger.getInstanciaLogger().addMensaje("Error al intentar cambiar de pinon en el ciclista[" + id_bicicleta + "]");
        }


        return exito;
    }

    public boolean setPlato(int plato) {

        boolean exito = true;
        if (plato > 0 && plato <= Constantes.NUMERO_PLATOS) {
            platoActual = plato - 1;
            
            Logger.getInstanciaLogger().addMensaje("Se ha cambiado el plato de la bicicleta del ciclista[" + id_bicicleta + "]:\n"
                    + "Plato: " + plato);
            
        } else {
            exito = false;
            Logger.getInstanciaLogger().addMensaje("Error al intentar cambiar de plato en el ciclista[" + id_bicicleta + "]");
        }

        return exito;
    }

    public void setAceleracionEolo(double _aceleracionEolo) {
        aceleracionEolo = _aceleracionEolo;
    }

    public void setAceleracionPendiolo(double _aceleracionPendiolo) {
        aceleracionPendiolo = _aceleracionPendiolo;
    }
    
    /**
     * Incrementa el espacio recorrido en la cifra indicada
     * @param incremento 
     */
    public void setIncremetarEspacioRecorrido(double incremento){
        distanciaRecorrida += incremento;
    }

    /**
     * Metodo encargado de asignar un numero de dientes a cada plato de la
     * bicicleta.
     */
    private void asignarValorDientesPlatos() {
        for (int i = 0; i < Constantes.NUMERO_PLATOS; i++) {
            platos[i] = 11 * (i + 2);
        }
        platos[Constantes.NUMERO_PLATOS - 1] += 2;
    }

    /**
     * M�todo encargado de asignar un numero de dientes a cada pinon de la
     * bicicleta.
     */
    private void asignarValorDientesPinones() {
        for (int i = 0; i < Constantes.NUMERO_PINONES; i++) {
            pinones[i] = 14 + (i * 3);
        }
    }
}
