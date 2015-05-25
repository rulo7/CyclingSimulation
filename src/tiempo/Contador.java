package tiempo;


/**
 * 
 * Clase que simula la abstraccion de un contador
 * Posee dos valores claves que son el maximo valor y el siguiente contador,
 * el sigiente contador empieza a contar cuando el incremento del contador llega a su maximo, donde se reinicia y vuelve a empezar
 * 
 * @author Raul Cobos y Sergio Rodriguez
 *
 */
public class Contador {

	// ________Atributos_____________ //
	
	private Contador siguienteContador;
	private int cuenta;
	private int maxValor;
	
	/**
	 * 
	 * Constructor de la clase que da valores a los atributos de esta
	 * Cuando el contador instanciado llegue al maximo, contara el siguiente contador
	 * 
	 * @param maximo
	 * @param next
	 */
	public Contador(int maximo, Contador next){
		siguienteContador = next;
		maxValor = maximo;
		cuenta = 0;
	}
	
	/**
	 * Metodo encargado de aumentar la cuenta del contador
	 * Si la cuenta del contador llega a su maximo, se pone de nuevo a cero
	 * y se incrementa el valor del siguienteContador
	 */
	public void incrementar(){
		if(cuenta == maxValor){
			cuenta = 0;
			this.siguienteContador.incrementar();
		}
		else{
			cuenta ++;
		}
	}
	
	// ____________________ Gets & Sets ____________________ //
	
	public int getCuenta(){
		return cuenta;
	}
}
