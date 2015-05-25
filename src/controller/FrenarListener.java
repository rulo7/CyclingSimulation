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
 * Implementa la funcionalidad ocurrida cuando el usuario acciona el boton
 * frenae
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class FrenarListener implements ActionListener {

    private PanelDeControl pc;
    private ArrayList<Ciclista> ciclistas;

    public FrenarListener(ArrayList<Ciclista> _ciclistas, PanelDeControl _pc) {
        ciclistas = _ciclistas;
        pc = _pc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Ciclista c = ciclistas.get(pc.getID());

        
        c.frenar(pc.getDuracionFrenada(), pc.getIntensidadFrenada());



        //loggin

        pc.updateLabels(pc.getID());
    }
}
