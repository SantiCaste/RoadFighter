package entity;

import config.Config;

public class ModoVehiculoJugadorMejorado extends ModoVehiculoJugador {

	
	//Estos metodos hacen que el power up solo lo haga acelerar mas rapido
//	@Override
//	public int velocidadAvance() {
//		return 100;
//	}
	
	@Override
	public double velocidadAvance() {
		return 100 * Config.modificadorResolucion;
	}
	
	@Override
	public double velocidadFreno() {
		return -50; //lo ponemos igual q el otro o -100 para q frene mas de golpe?
	}
	
	//Este metodo lo hace invulnerable
	@Override
	public double desplazamientoChoque() {
		return 0;
		
		
	}
	
	@Override
	public ModoVehiculoJugador cambiarModo() {
		return new ModoVehiculoJugadorNormal();
	}
	
	
}
