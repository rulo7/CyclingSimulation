/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import cycling.Ciclista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import view.PanelDeControl;

/**
 *
 * Actualiza los valores del panel de control si se cambia el ciclista
 * 
 * @author Raul Cobos y Sergio Rodriguez
 */
public class IDListener implements ActionListener{

    private PanelDeControl pc;
    private ArrayList<Ciclista> ciclistas;

    public IDListener(ArrayList<Ciclista> _ciclistas, PanelDeControl _pc) {
        ciclistas = _ciclistas;
        pc = _pc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {        
        
        Ciclista c = ciclistas.get(pc.getID());
        
        pc.setPlato(c.getBici().getPlato() + 1);
        pc.setPinon(c.getBici().getPinon() + 1);
        pc.setPeriodo(c.getTiempoEnDarLaPedalada());
        pc.setCadencia(c.getCadencia());
        
        pc.updateLabels(pc.getID());
    }
}
