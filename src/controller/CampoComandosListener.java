/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import comandos.Parser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.PanelDeControl;

/**
 *
 * Implementa la funcionalidad ocurrida cuando el usuario introduce un comando
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class CampoComandosListener implements ActionListener {

    private PanelDeControl pc;
    private Parser parser;

    public CampoComandosListener(Parser _parser, PanelDeControl _pc) {
        pc = _pc;
        parser = _parser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        pc.borrarComando();
        System.out.println(comando);
        parser.addStringCommand(comando);  
    }
}
