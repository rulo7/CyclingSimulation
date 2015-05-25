/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comandos;

import herramientas.Contenedor;
import java.util.ArrayList;
import java.util.StringTokenizer;
import view.HelpPanel;

/**
 *
 * Muestra ayuda al usuario
 *
 * @author Raul Cobos y sergio Rodriguez
 */
public class ComandoHelp implements Command {

    HelpPanel marco;
    ArrayList<Command> listaComandos;
    
    public ComandoHelp(ArrayList<Command> _listaComandos){
        listaComandos = _listaComandos;
        marco = null;        
    }

    @Override
    public void ejecutar() {
               
        String txt = "Los comandos existentes son los siguientes:\n\n";
        
        for(Command c: listaComandos){
            txt = txt + "- " + c.help() + "\n";
        }
        
        marco.settexto(txt);
        
        marco.setVisible(true);
        
    }

    @Override
    public void configureContext(Contenedor container) {
        marco = new HelpPanel();
    }

    @Override
    public Command parser(String comando) {
        Command comandoADevolver = null;

        if (comando != null) {
            StringTokenizer str = new StringTokenizer(comando);
            if (str.hasMoreTokens()) {
                if (str.nextToken().equalsIgnoreCase("help")) {
                    comandoADevolver = this;
                }

            }
        }

        return comandoADevolver;
    }

    @Override
    public String help() {
        return "Sintaxis del comando:\nhelp";
    }
}
