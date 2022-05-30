package entity;

import config.Config;

public class ModoVehiculoJugadorNormal extends ModoVehiculoJugador {
	
	//viejo
//	@Override
//	public int velocidadAvance() {
//		return 50; // aumento velocidad de a 50.
//	}
	
	//nuevo
	@Override
	public double velocidadAvance() {
		return 5 * Config.modificadorResolucion; // con 2 acelera muy lento, se puede modificar igual.
	}

	@Override
	public double velocidadFreno() {
		return -15 * Config.modificadorResolucion;
	}
	
	@Override 
	public double desplazamientoChoque() {
//		return 10 * Config.modificadorResolucion; //lo hago retroceder un lugar
		
		return 0;
	}

//	@Override
//	public void moverseAIzquierda(Vehiculo vehiculo) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void moverseADerecha(Vehiculo vehiculo) {
//		// TODO Auto-generated method stub	
//	}
	
	@Override
	public ModoVehiculoJugador cambiarModo() {
		return new ModoVehiculoJugadorMejorado();
	}
}
