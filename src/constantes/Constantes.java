package constantes;

/**
 * 
 * Clase que contiene todas las constantes necesarias utilizasdas
 * La utilidad de esta clase radica en la facilidad que implica a la hora
 * de cambiar valores que en otro caso tendria que modificarse en muchos sitios 
 * distintos mientras que con la clase, solo habria que cambiar aqui el valor
 * ademas son valores mucho mas faciles de localizar
 * 
 * @author Raul Cobos y Sergio Rodriguez
 */
public class Constantes {

	// ________________ Parametros relacionados con la bicicleta ________________ //

	// masa por defecto de la bicicleta.
	public static final double MASA_BICICLETA = 7.5;
	// platos por defecto de la bicicleta.
	public static final int NUMERO_PLATOS = 3;
	// pinones por defecto de la bicicleta.
	public static final int NUMERO_PINONES = 5;
	// radio de la rueda por defecto de la bicicleta.
	public static final double RADIO_RUEDA = 0.3302;/*metros*/
	// pinon inicial de la bici
	public static final int PINON_INICIAL = 1;
	// plato inicial de la bici
	public static final int PLATO_INICIAL = 1;
        // coeficiente de rozamiento estandar
        public static final double COEFICIENTE_ROZAMIENTO = 0.7;

	// ________________ Parametros relacionados con el ciclista ________________ //

	// ciclistas que participan en la carrera.
	public static final int NUMERO_CICLISTAS = 4;
	// masa por defecto del ciclista.
	public static final double MASA_CICLISTA = 75;
	// fuerza por defecto del ciclista.
	public static final double FUERZA_CICLISTA = 100000;
	// cadencia inicial del ciclista. pedaladas realizadas en un minuto
	public static final int CADENCIA_INICIAL = 60;
	// tiempo en segundos por defecto que tarda el ciclista en dar una pedalada.
	public static final double TIEMPO_POR_PEDALADA = 0.4;

	// ________________ Archivos de lectura ________________ //

	// ruta donde se encuentra el fichero de comandos.
	public static final String ARCHIVO_COMANDOS = "comandos.txt";
	// ruta donde se encuentra el fichero de pendientes iniciales.
	public static final String ARCHIVO_PENDIOLO = "pendiolo.txt";
	// ruta donde se encuentra el fichero de curvas iniciales.
	public static final String ARCHIVO_CURVAS = "curvas.txt";
	// ruta donde se encuentra el fichero de vientos iniciales.
	public static final String ARCHIVO_EOLO = "eolo.txt";
        // ruta donde se encuentra el fichero donde se almacena el loggin de la aplicacion
        public static final String ARCHIVO_LOGGER = "log.txt";

	// ________________ Otras ________________ //

	// constante gravitatoria.
	public static final double G = 9.8;
	// PI
	public static final double PI = 3.141592654;
	// segundos que tarda en ejecutarse un ciclo de reloj
	public static final double TIEMPO_POR_PULSO = 0.1;

}
