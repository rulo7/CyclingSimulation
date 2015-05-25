package cycling;

import constantes.Constantes;
import globalInterfaces.ObjetosQueSeEjecutan;
import herramientas.Logger;

/**
 * Clase que representa la abstraccion de un ciclista
 *
 * @author Raul Cobos y Sergio Rodriguez
 *
 */
public class Ciclista implements ObjetosQueSeEjecutan {

    // ________Atributos_____________ //
    private Bicicleta bici;
    private double masa; // Kg
    // segundos que tarda en dar una
    // pedalada
    private double tiempoEnDarLaPedalada;//segundos
    private int cadencia;// pedaladas / minuto
    private boolean estaVivo;
    private boolean esGanador;
    // Cantidad de velocidad que reduce la
    // bicicleta cuando frena
    private double reduccionFrenada;
    private int contadorFreno;
    // Contador para saber cuando tiene que dar la siguiente
    // pedalada(pulsos = decimas de segundo)
    private int contadorSiguientePedalada;
    private int id_ciclista; // Identificador del cilista
    private double fuerza_ciclista;
    private double velocidad;
    private double totalRecorrido;
    private double pendienteActual;
    private double velocidadVientoActual;

    /**
     * Asigna valores por defecto al ciclista Instancia una nueva bicicleta para
     * este ciclista
     *
     * @param _id_ciclista
     */
    public Ciclista(int _id_ciclista) {

        bici = new Bicicleta(_id_ciclista);
        masa = Constantes.MASA_CICLISTA;
        tiempoEnDarLaPedalada = Constantes.TIEMPO_POR_PEDALADA;
        cadencia = Constantes.CADENCIA_INICIAL;
        estaVivo = true;
        esGanador = false;
        reduccionFrenada = 0;
        contadorFreno = 0;
        contadorSiguientePedalada = (int) (((double) (60.0 / cadencia)) * 0.1);
        id_ciclista = _id_ciclista;
        fuerza_ciclista = Constantes.FUERZA_CICLISTA;
        velocidad = 0;


    }

    // ____________________ Gets & Sets ____________________ //
    public int getDuracionDeFrenada() {
        return contadorFreno * 10;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public int getIdCiclista() {
        return id_ciclista;
    }

    public int getCadencia() {
        return cadencia;
    }

    public double getMasa() {
        return masa;
    }

    public double getTiempoEnDarLaPedalada() {
        return tiempoEnDarLaPedalada;
    }

    public boolean getEstaVivo() {
        return estaVivo;
    }

    public boolean getEsGanador() {
        return esGanador;
    }

    public double getFuerzaCiclista() {
        return fuerza_ciclista;
    }

    public Bicicleta getBici() {
        return bici;
    }

    public double getReduccionFrenada() {
        return reduccionFrenada;
    }

    public double getVelocidadVientoActual() {
        return velocidadVientoActual;
    }

    public double getPendienteActual() {
        return pendienteActual;
    }

    public void setPendienteActual(double _pendiente) {
        pendienteActual = _pendiente;
        Logger.getInstanciaLogger().addMensaje("La pendiente para el ciclista[" + id_ciclista + "] ha cambiado:\n"
                + "Punto metrico actual: " + bici.getDistanciaRecorrida()
                + "\nPendiente actual: " + pendienteActual);
    }

    public void setVelocidadVientoActual(double _velocidadViento) {
        velocidadVientoActual = _velocidadViento;
    }

    public void setIdCiclista(int _id_ciclista) {
        id_ciclista = _id_ciclista;
    }

    public void setEstaVivo(boolean _estaVivo) {
        estaVivo = _estaVivo;
    }

    public void setEsGanador(boolean _esGanador) {
        esGanador = _esGanador;
    }

    /**
     * Define el final del recorrido del ciclista
     *
     * @param _finalRecorrido
     */
    public void setFinalRecorrido(double _finalRecorrido) {
        totalRecorrido = _finalRecorrido;
    }

    /**
     *
     * Cambia la cadencia siempre que se cumplan los requisitos necesarios
     *
     * @param _cadencia
     * @return true si se ha modificado con exito la cadencia
     */
    public boolean setCadencia(int _cadencia) {
        boolean ok = false;

        if (_cadencia >= 0 && _cadencia <= 120 && (60.0 / _cadencia) >= tiempoEnDarLaPedalada && estaVivo) {
            cadencia = _cadencia;

            Logger.getInstanciaLogger().addMensaje("Se ha cambiado la cadencia del ciclista[" + id_ciclista + "]:\n"
                    + "Cadencia: " + cadencia);


            if (cadencia == 0) {
                contadorSiguientePedalada = Integer.MAX_VALUE;
            } else if ((60.0 / cadencia) * 10 < contadorSiguientePedalada) {
                contadorSiguientePedalada = (int) ((60.0 / cadencia) * 10);
            }
            ok = true;
        } else {
            Logger.getInstanciaLogger().addMensaje("Error al intentar cambiar la cadencia del ciclista[" + id_ciclista + "]");
        }

        return ok;

    }

    /**
     *
     * Cambia tiempo que tarda en dar una pedalada el ciclista siempre que se
     * cumplan los requisitos necesarios
     *
     * @param _tiempoEnDarLaPedalada
     * @return true si se ha modificado con exito el tiempo en dar una pedalada
     */
    public boolean setTiempoEnDarLaPedalada(double _tiempoEnDarLaPedalada) {
        boolean exito = false;
        if ((60.0 / cadencia) >= _tiempoEnDarLaPedalada && _tiempoEnDarLaPedalada > 0.2 && estaVivo) {
            tiempoEnDarLaPedalada = _tiempoEnDarLaPedalada;
            exito = true;

            Logger.getInstanciaLogger().addMensaje("Se ha cambiado el tiempo en dar una pedalada del ciclista[" + id_ciclista + "]:\n"
                    + "Periodo: " + cadencia);
        } else {
            Logger.getInstanciaLogger().addMensaje("Error al intentar cambiar el periodo del ciclista[" + id_ciclista + "]");
        }

        return exito;
    }

    /**
     * Modifica la velocidad y aumenta la distancia recorrida
     *
     * @param velocidadIncrementada
     */
    public void modificarVelocidad(double velocidadIncrementada) {

        if (velocidad + velocidadIncrementada > 0) {

            velocidad += velocidadIncrementada;

            bici.setIncremetarEspacioRecorrido(velocidad * 0.1);

        } else {
            velocidad = 0;
        }

    }

    /**
     * Le resta fuerza al ciclista, asegurandose de que esta nunca es negativa
     *
     * @param decrementoFuerza
     */
    public void restarFuerza(double decrementoFuerza) {
        if (fuerza_ciclista - decrementoFuerza > 0) {
            fuerza_ciclista -= decrementoFuerza;
        } else {
            fuerza_ciclista = 0;
        }
    }

    // ____________________ Calculos ____________________ //
    /**
     * Calcula la fuerza gastada por cada pedalada que da el ciclista
     *
     * @param aceleracion
     * @return fuerza gastada por el ciclista con la pedalada
     */
    public double getFuerzaGastadaPorPedalada(double aceleracion) {
        double fuerzaGastada = (masa + bici.getMasa()) * aceleracion;

        return fuerzaGastada;
    }

    /**
     * Calcula la aceleracion instantanea que produce la pedalada del ciclista
     *
     * @return aceleracion instantanea que genera la pedalada
     */
    public double getAceleracionInstantaneaQueProduceLaPedalada() {
        return bici.getEspacioPorPedalada()
                / Math.pow(tiempoEnDarLaPedalada, 2);
    }

    /**
     * Metodo que simula la accion de frenar Para frenar se indican el tiempo
     * que va a estar frenando el ciclista y la cantidad porcentual que se vera
     * reducida la velocidad a la que se mueve
     *
     * @param tiempoDeFrenada (en segundos)
     * @param porcentajeDeReduccionDeLafrenada
     * @return booleano queinformade si se ha realizado correctamente el metodo
     */
    public void frenar(int tiempoDeFrenada, double porcentajeDeReduccionDeLafrenada) {


        if (porcentajeDeReduccionDeLafrenada > 0
                && porcentajeDeReduccionDeLafrenada <= 100
                && tiempoDeFrenada >= 0 && tiempoDeFrenada <= 6 && estaVivo) {

            contadorFreno = tiempoDeFrenada * 10; //se pasa a decimas de segundo
            reduccionFrenada = (porcentajeDeReduccionDeLafrenada / 100) * velocidad;

            velocidad = velocidad - reduccionFrenada;

            Logger.getInstanciaLogger().addMensaje("Ha frenado el ciclista[" + id_ciclista + "]:\n"
                    + "Duracion de frenada: " + tiempoDeFrenada
                    + "\nIntensidad de frenada: " + porcentajeDeReduccionDeLafrenada);
        } else {
            Logger.getInstanciaLogger().addMensaje("Error de intento de frenada del ciclista[" + id_ciclista + "]");
        }
    }

    // ____________________ Sobreescritura de los metodos de las interfaces que
    // implementan ____________________ //
    @Override
    public void ejecutar() {

        // En caso de que aun no haya muerto
        if (estaVivo) {
            // En caso de frenado
            if (contadorFreno > 0) {
                // Decrementa la velocidad
                modificarVelocidad(-reduccionFrenada);

                // Decrementa el contadorDeFrenada
                contadorFreno--;

            } // En caso de que no se frene
            // Se calcula si merece la pena pedalear
            else if (bici.getAceleracionPendiolo() <= 0 && bici.getAceleracionPendiolo() + bici.getAceleracionEolo() < getAceleracionInstantaneaQueProduceLaPedalada()) {

                contadorSiguientePedalada--;

                // Si toca dar la siguiente pedalada            
                if (contadorSiguientePedalada <= 0) {
                    // Decrementa la fuerza del ciclista

                    double aceleracion = bici.aceleracionPedalada(tiempoEnDarLaPedalada);
                    
                    //Con influencia del rozamiento
                    double aceleracionFriccion = Constantes.COEFICIENTE_ROZAMIENTO * aceleracion;
                    
                    
                    modificarVelocidad(((aceleracion - aceleracionFriccion)/ 2.0) * tiempoEnDarLaPedalada);
                    restarFuerza(getFuerzaGastadaPorPedalada(aceleracion));

                    if (fuerza_ciclista <= 0) {
                        setEstaVivo(false);
                        Logger.getInstanciaLogger().addMensaje("El ciclista " + getIdCiclista() + " ha muerto por cansancio");
                    }

                    // Reinicia el contador de la siguiente pedalada
                    if (cadencia > 0) {
                        contadorSiguientePedalada = (int) (60 * 10 / cadencia);
                    }
                }

            }
            // comprueba si es campeon
            if (bici.getDistanciaRecorrida() > totalRecorrido) {
                esGanador = true;
                Logger.getInstanciaLogger().addMensaje("El ciclista " + getIdCiclista() + " ha ganado");
            }

        }

    }
}
