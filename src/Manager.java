
import herramientas.Contenedor;
import comandos.*;
import constantes.Constantes;
import tiempo.Reloj;
import globalInterfaces.ObjetosQueSeEjecutan;
import herramientas.Logger;
import java.util.ArrayList;
import view.PanelCarrera;
import view.PanelDeControl;

/**
 * Se encarga de la ejecucion al completo de la aplicacion
 *
 * @author raul
 */
public class Manager {

    private ArrayList<ObjetosQueSeEjecutan> objetosQueSequierenEjecutar;
    private Contenedor container;
    private Comandero comandero;
    private Parser parser;
    private ComandosQueSeEjecutanArchivoTexto comandoArchivoTexto;
    private PanelDeControl panelControl;
    private PanelCarrera panelCarrera;

    /**
     * Se encarga de inicializar todos los elementos que conformaran la
     * ejecucion de la aplicacion
     *
     * @throws InterruptedException
     */
    public void inicializar() throws InterruptedException {

        //Instancias
        objetosQueSequierenEjecutar = new ArrayList<>();
        container = new Contenedor();

        //Ciclistas
        objetosQueSequierenEjecutar.addAll(container.getListaCiclistas());
        
        //Tiempo
        objetosQueSequierenEjecutar.add(Reloj.getInstanciaDelReloj());
        
        //Factores

        objetosQueSequierenEjecutar.add(container.getEolo());

        objetosQueSequierenEjecutar.add(container.getPendiolo());

        objetosQueSequierenEjecutar.add(container.getCurvas()); 
        
        
        
        
        //Comandos        
        comandero = new Comandero(container);
        objetosQueSequierenEjecutar.add(comandero);
        parser = new Parser(comandero);
        objetosQueSequierenEjecutar.add(parser);

        comandoArchivoTexto = new ComandosQueSeEjecutanArchivoTexto(parser);
        //objetosQueSequierenEjecutar.add(comandoArchivoTexto);       
        
        //Vista
        panelCarrera = new PanelCarrera(container);
        panelControl = new PanelDeControl(container, parser, panelCarrera);
        panelControl.setVisible(true);
        objetosQueSequierenEjecutar.add(panelControl);

    }

    /**
     * Se encarga de la ejecucion del bucle principal y la aplicacion
     *
     * @throws InterruptedException
     */
    public void ejecutar() throws InterruptedException {

        boolean fin = false;

        Logger.getInstanciaLogger().addMensaje("La carrera comienza con " + Constantes.NUMERO_CICLISTAS + " ciclistas");

        comandoArchivoTexto.ejecutar();

        while (!fin) {

            //Se ejecutan todos los objetos
            for (ObjetosQueSeEjecutan o : objetosQueSequierenEjecutar) {
                o.ejecutar();
            }
            //Se comprueba si existe ganador o si todos estan muertos
            boolean todosMuertos = true;
            for (int j = 0; j < Constantes.NUMERO_CICLISTAS; j++) {
                if (container.getListaCiclistas().get(j).getEsGanador()) {
                    fin = true;
                    panelControl.mostrarPanelGanador(j);
                    Logger.getInstanciaLogger().addMensaje("El ciclista " + j + " ha llegado a la meta y es el ganador de la carrera");
                } else if (container.getListaCiclistas().get(j).getEstaVivo()) {
                    todosMuertos = false;                    
                }
            }
            if (todosMuertos) {
                fin = true;
                Logger.getInstanciaLogger().addMensaje("Todos los ciclistas han muerto");
            }
            // Duerme la ejecucion del hilo por tantos milisegundos como indique el argumento
            Thread.sleep((int) (Constantes.TIEMPO_POR_PULSO * 1000));

        }
    }

    public void finalizar() {

        
        Logger.getInstanciaLogger().guardarMensajes();
        System.out.println("FIN");
    }

    public static void main(String[] args) throws InterruptedException {

        Manager m = new Manager();

        m.inicializar();
        m.ejecutar();
        m.finalizar();
    }
}
