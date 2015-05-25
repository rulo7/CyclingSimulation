package controller;

import cycling.Ciclista;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.PanelDeControl;

/**
 * Cambia el tiempo en dar una pedalada de la bici cuando asi lo indica el panel de control
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class PeriodoListener implements ChangeListener {

    private ArrayList<Ciclista> ciclistas;
    PanelDeControl pc;

    public PeriodoListener(ArrayList<Ciclista> _ciclistas, PanelDeControl _pc) {
        ciclistas = _ciclistas;
        pc = _pc;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
        Ciclista ciclista = ciclistas.get(pc.getID());
        
        if (ciclista.setTiempoEnDarLaPedalada(pc.getPeriodo())) {
            //loggin 
        }else{
            pc.setPeriodo(ciclista.getTiempoEnDarLaPedalada());
        }
        
        pc.updateLabels(pc.getID());
    }
}