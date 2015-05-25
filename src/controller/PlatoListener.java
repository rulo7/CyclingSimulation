package controller;

import cycling.Bicicleta;
import cycling.Ciclista;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.PanelDeControl;

/**
 * Cambia los platos de la bici cuando asi lo indica el panel de control
 *
 * @author raul
 */
public class PlatoListener implements ChangeListener {

    private ArrayList<Ciclista> ciclistas;
    PanelDeControl pc;

    public PlatoListener(ArrayList<Ciclista> _ciclistas, PanelDeControl _pc) {
        ciclistas = _ciclistas;
        pc = _pc;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        
        Bicicleta bici = ciclistas.get(pc.getID()).getBici();
        
        if (bici.setPlato(pc.getPlato())) {
            //loggin 
        } else {
            pc.setPlato(bici.getPlato());
        }
        
        pc.updateLabels(pc.getID());
    }
}
