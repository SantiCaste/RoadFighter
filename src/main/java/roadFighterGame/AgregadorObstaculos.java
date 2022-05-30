package roadFighterGame;

import java.util.Random;

import config.Config;
import entity.Pista;
import interfaces.Updatable;
import objects.Obstaculo;

public class AgregadorObstaculos implements Updatable {

	private long tiempoEntreObstaculos = 10;
	private long tiempoGeneracion;

	private double posicionPrevia, posicionActual;

	private Pista p; // no se si esta bien asi

	public AgregadorObstaculos(Pista p) {
		this.p = p;
		this.posicionActual = this.posicionPrevia = 0;
		this.tiempoGeneracion = 0;
	}

	@Override
	public void update(double deltaT) {

		long currentNano = System.nanoTime();

		this.posicionActual = p.getJugador().getY();

		if ((currentNano / Config.NANOS_IN_SECOND) - tiempoGeneracion > 0 //no puedo preguntar por ese tiempo ya que se va de rango.
				&& posicionActual > posicionPrevia + 500 * Config.modificadorResolucion) {

			posicionPrevia = posicionActual; // con esto evito el amontonamiento de vehiculos autonomos.

			tiempoGeneracion = currentNano / Config.NANOS_IN_SECOND + tiempoEntreObstaculos;
			System.out.println("El nuevo tiempoGeneracion es de: " + tiempoGeneracion);
			Random x = new Random();
			System.out.println("Se agrega un obstaculo nuevo");
			agregarObstaculo(x.nextInt(-Config.distAlCentro, Config.distAlCentro + 1), Config.baseHeight); // ver el
																											// rango que
																											// deberia
																											// tener.
		} // ver bien los parametros que se mandan.

	}

	private void agregarObstaculo(int x, int y) {
		Obstaculo o = new Obstaculo(x, y);

		p.add(o);
		GameObjectBuilder.getInstance().add(o);
	}

}
