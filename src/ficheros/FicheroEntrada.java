package ficheros;

import java.io.*;

/**
 * 
 * Clase con los metodos necesarios para poder almacenar los datos 
 * de un archivo en un variable de tipo string mediante una variable 
 * BuffereadReader para leer los datos del archivo de entrada
 * 
 * @author Raul Cobos y Sergio Rodriguez
 *
 */

public class FicheroEntrada{
	
	
/**
 * 
 * Se le pasa un parametro con una variable de tipo String con el nombre del archivo que se desea abrir y asociar al atributo reader
 * Lanza errores en caso de que no se encuentre el archivo con dicho nombre
 * 
 * @param nombre_archivo
 */
private static BufferedReader abrirFichero(String nombre_archivo) // Abre el archivo
{
	
	BufferedReader reader; // Atributo de la clase FicheroEntrada cuya finalidad es la de leer de un archivo de texto
	
	//Si existe nombre para el archivo que se quiere abrir que no sea vacio
	 if (nombre_archivo != null){
		 
		 try{

			 reader = new BufferedReader(new FileReader(nombre_archivo)); // Instancia la variable reader y le pasa como parametro el nombre del archivo del que se quiere leer y lo abre
		 } 
		 catch (FileNotFoundException e){

			 System.err.println (e + "\n Archivo no encontrado. Intentelo de nuevo o introduzcalo otra vez el nombre del archivo");
			 reader = null;
		 }
	 }else{
		 reader = null;
	 }
	 
	 return reader;
}

/**
 * 
 * Almacena en una variable de tipo String lo datos almacenados linea a linea del archivo
 * Lanza un error si no se ha podido leer el archivo correctamente
 * 
 * @return lectura
 */
public static String leer_archivo(String nombre_archivo) // Almacena los datos del archivo en una variable String
{
	
	BufferedReader reader = abrirFichero(nombre_archivo);
	
	
	String lectura = new String(); // Variable con la funcion de almacenar los datos contenidos en el fichero
	
	 try {
		 
		while (reader.ready()) 
			 lectura = lectura + reader.readLine() + "\n"; // Almacena linea a linea los datos del fichero en la variable lectura hasta que se acabe el fichero
		
	} catch (IOException e) {
		System.err.println (e + "\n Error de hardware durante la lectura. " + "Intentelo de nuevo o introduzca otra vez el nombre del archivo") ;
	}
	
	 cerrarFichero(reader);
	 
	return lectura;
}

/**
 * Cierra el archivo asociado al atributo reader de esta clase 
 * Lanza errores en caso de que se produzca algun fallo a la hora de cerrar el archivo
 */

private static void cerrarFichero(BufferedReader reader) // Cierra el archivo
{
	
	try {
		reader.close();
	} catch (IOException e) {
		System.err.println("error al cerrar el archivo");
	}
	
}

}