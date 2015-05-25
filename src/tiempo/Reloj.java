package tiempo;

import globalInterfaces.ObjetosQueSeEjecutan;

/**
 * Clase encargada de simular la abstraccion de un reloj Utiliza el patron
 * singleton con el fin de permitir una unica instancia de esta clase
 *
 * @author Raul Cobos y Sergio Rodriguez
 *
 */
public class Reloj implements ObjetosQueSeEjecutan {

    // ________Atributos_____________ //
    private static Reloj reloj = null;
    private Contador horas;
    private Contador minutos;
    private Contador segundos;
    private Contador decimasDeSegundo;
    private int pulsos;

    /**
     * Constructor de tipo privado que solo permite instanciar esta clase en
     * ella misma Instancia cada uno de sus atributos como contadores con
     * dependencias entre si
     *
     */
    private Reloj() {
        horas = new Contador(1000, null);
        minutos = new Contador(59, horas);
        segundos = new Contador(59, minutos);
        decimasDeSegundo = new Contador(9, segundos);
        pulsos = 0;
    }

    /**
     * Clase que entrega la unica instancia posible de esta clase Reloj
     *
     * @return la unica instancia posible de esta clase
     */
    public static Reloj getInstanciaDelReloj() {

        // De esta manera nos aseguramos que el reloj solo ser instanciado una
        // unica vez
        if (reloj == null) {
            reloj = new Reloj();
        }

        return reloj;
    }

    // ____________________ Gets & Sets ____________________ //
    public int getHoras() {
        return horas.getCuenta();
    }

    public int getMinutos() {
        return minutos.getCuenta();
    }

    public int getSegundos() {
        return segundos.getCuenta();
    }

    public int getDecimasDeSegundo() {
        return decimasDeSegundo.getCuenta();
    }

    public int getPulsos() {
        return pulsos;
    }
    
    /**
     * Devuelve un string para poder mostrar el tiempo en la aplicacion
     *
     * @return el tiempo de la aplicacion en formato reloj digital
     */
    public String getTiempoAplicacion() {
        String tiempo = "";

        tiempo += Reloj.getInstanciaDelReloj().getHoras() + ":";

        if (Reloj.getInstanciaDelReloj().getMinutos() < 10) {
            tiempo += "0";
        }

        tiempo += Reloj.getInstanciaDelReloj().getMinutos() + ":";

        if (Reloj.getInstanciaDelReloj().getSegundos() < 10) {
            tiempo += "0";
        }

        tiempo += Reloj.getInstanciaDelReloj().getSegundos() + ":0";
        tiempo += Reloj.getInstanciaDelReloj().getDecimasDeSegundo();

        return tiempo;
    }

    // ____________________ Sobreescritura de los metodos de las interfaces que implementan ____________________ //
    @Override
    public void ejecutar() {
        decimasDeSegundo.incrementar();
        pulsos++;
    }
}
