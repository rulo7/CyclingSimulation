package ficheros;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * Clase con los metodos necesarios para poder almacenar los datos de una
 * variable de tipo string en un archivo de texto mediante una variable
 * BuffereadWriter para escribir los datos en el archivo de entrada
 *
 * @author Raul Cobos y Sergio Rodriguez
 *
 */
public class FicheroSalida {

    /**
     *
     * Se le pasa un parametro con una variable de tipo String con el nombre del
     * archivo que se desea abrir y asociar al atributo writer Lanza errores en
     * caso de que no se encuentre el archivo con dicho nombre
     *
     * @param nombre_archivo
     */
    private static PrintWriter abrirFichero(String nombre_archivo) // Abre el archivo
    {
        PrintWriter writter;

        //Si existe nombre para el archivo que se quiere abrir que no sea vacio
        if (nombre_archivo != null) {
            try {
                writter = new PrintWriter(new BufferedWriter(new FileWriter(nombre_archivo))); // Instancia la variable reader y le pasa como parametro el nombre del archivo del que se quiere leer y lo abre
            } catch (IOException ex) {
                writter = null;
            }

        } else {
            writter = null;
        }

        return writter;
    }

    /**
     *
     * Almacena en el archivo de texto lo datos almacenados en la variable
     * String Lanza un error si no se ha podido escribir en el archivo
     * correctamente
     *
     * @param nombre del archivo
     * @param texto a escribir en el archivo
     *
     * @return lectura
     */
    public static void escribir_archivo(String nombre_archivo, String txt) {

        PrintWriter writter;

        if (nombre_archivo != null) {
            writter = abrirFichero(nombre_archivo);

            writter.write(txt);

        } else {
            writter = null;
            System.err.println("Error de hardware durante la escritura. "
                    + "Intentelo de nuevo o introduzca otra vez el nombre del archivo");
        }
        
        cerrarFichero(writter);
    }

    /**
     * Cierra el archivo asociado al atributo reader de esta clase Lanza errores
     * en caso de que se produzca algun fallo a la hora de cerrar el archivo
     */
    private static void cerrarFichero(PrintWriter writter) // Cierra el archivo
    {
        writter.close();

    }
}