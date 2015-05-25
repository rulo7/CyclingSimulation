package controller;

import cycling.Ciclista;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import view.PanelDeControl;

/**
 * Cambia la cadencia de la bici cuando asi lo indica el panel de control
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class CadenciaListener implements ChangeListener {

    private ArrayList<Ciclista> ciclistas;
    PanelDeControl pc;

    public CadenciaListener(ArrayList<Ciclista> _ciclistas, PanelDeControl _pc) {
        ciclistas = _ciclistas;
        pc = _pc;
    }

    @Override
    public void stateChanged(ChangeEvent e) {

        Ciclista ciclista = ciclistas.get(pc.getID());
        
        if (ciclista.setCadencia(pc.getCadencia())) {
            //loggin 
        }else{
            pc.setCadencia(ciclista.getCadencia());
        }
        
        pc.updateLabels(pc.getID());
    }
}
