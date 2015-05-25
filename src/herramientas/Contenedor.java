package herramientas;


import constantes.Constantes;
import cycling.Ciclista;
import factores.Curva;
import factores.Eolo;
import factores.Pendiolo;
import java.util.ArrayList;

/**
 * Contiene todos los objetos d elas clases que se pueden necesitar Su unico
 * proposito es el de facilitar los elementos a las clases que los necesiten
 * intentando asi tener a mayor cohesion porible
 *
 * @author Raul Cobos y Sergio Rodriguez
 */
public class Contenedor {

    private ArrayList<Ciclista> listaCiclistas;
    private Eolo eolo;
    private Pendiolo pendiolo;
    private Curva curvas;
    
    
    public Contenedor (){
        listaCiclistas = new ArrayList<>();

        for (int i = 0; i < Constantes.NUMERO_CICLISTAS; i++) {
            listaCiclistas.add(new Ciclista(i));
        }
        
        eolo = new Eolo(listaCiclistas);
        pendiolo = new Pendiolo(listaCiclistas);
        curvas = new Curva(listaCiclistas);
        
    }


    //___________ Gets de la clase __________//
    
    public Eolo getEolo(){
        return eolo;
    }
    
    public Pendiolo getPendiolo(){
        return pendiolo;
    }

    public Curva getCurvas(){
        return curvas;
    }
    
    public ArrayList<Ciclista> getListaCiclistas(){
        return listaCiclistas;
    }
    
    
}
