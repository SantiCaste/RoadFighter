package entity;

public abstract class ModoVehiculoJugador {
//	public abstract int velocidadAvance(); //le cambio los nombres para que sean mas especificos.
	
	public abstract double velocidadAvance();
	public abstract double velocidadFreno();
	public abstract double desplazamientoChoque();
//	public abstract void moverseAIzquierda(Vehiculo vehiculo);
//	public abstract void moverseADerecha(Vehiculo vehiculo); X AHORA NO HACEN FALTA
	public abstract ModoVehiculoJugador cambiarModo();
	
	
//	private int cantVidas;
//	
//	public void daniar () {
//		this.cantVidas --;
//		if (this.cantVidas <= 0)
//			this.morir();
//	}
}
