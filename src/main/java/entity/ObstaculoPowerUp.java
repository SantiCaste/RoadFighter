package entity;

public class ObstaculoPowerUp extends ElementoDePista{

	public ObstaculoPowerUp(double x, double y) {
		super(x, y);
	}
	
	public void activarPowerUp(VehiculoJugador auto) {
		 auto.cambiarModo();
	}
}
