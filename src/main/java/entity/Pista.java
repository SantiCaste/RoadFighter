package entity;

import java.util.ArrayList;

import config.Config;
import interfaces.Updatable;
import objects.AudioUI;
import objects.Borde;
import objects.Obstaculo;
import roadFighterGame.GameObjectBuilder;

public class Pista implements Updatable {
	private static final Integer longitudDePista = Config.baseHeight * 10; // lo puse static porque nadie lo va a
																			// cambiar, y
																			// sino no podia acceder.
	private ArrayList<VehiculoAutonomo> autos; // antes era de <Vehiculo>.

	private ArrayList<Obstaculo> obstaculos;

	private VehiculoJugador jugador; // para saber su velocidad.

	private AudioUI audio;

	private Borde bordeIzq, bordeDer;

	private final int maxOffScreen = 5 * Config.baseHeight;

	public Pista() {
		this.autos = new ArrayList<VehiculoAutonomo>();
		this.obstaculos = new ArrayList<Obstaculo>();
		this.bordeIzq = new Borde(Config.bordeIzquierdo);
		this.bordeDer = new Borde(Config.bordeDerecho);

//		audio = new AudioUI(); DEBERIA IR ACA PERO NO SE IMPLEMENTARLO.

		GameObjectBuilder.getInstance().add(bordeIzq, bordeDer);
	}

	public void add(VehiculoAutonomo v) {
		autos.add(v);
	}

	public void add(Obstaculo o) {
		obstaculos.add(o);
	}

	public void addJugador(VehiculoJugador j) {
		jugador = j;
	}

	public VehiculoJugador getJugador() {
		return jugador;
	}

	public boolean autoLlegoAFinal(VehiculoJugador auto) {

		if (auto.getY() >= longitudDePista)
			return true;

		return false;
	}

	public static int getLongitud() {
		return longitudDePista;
	}

	public ArrayList<VehiculoJugador> getPodio(ArrayList<VehiculoJugador> autos) {

		autos.sort(new ComparadorPuntajes());

		return autos;
	}

	public void update(double deltaTime) { // los autonomos deberia actualizarlos segun la velocidad del jugador.

		jugador.update(deltaTime);

//		if(jugador.getY() >= longitudDePista) {
//			System.out.println("Ganastes!!!!");
//		}

		ArrayList<VehiculoAutonomo> removibles = new ArrayList<VehiculoAutonomo>();

		for (VehiculoAutonomo vehiculo : autos) {
			vehiculo.setVelocidadRelativa(vehiculo.getVelocidad() - jugador.getVelocidad());

			vehiculo.update(deltaTime);

//			if ((jugador.getY() - vehiculo.getY()) > maxOffScreen) { // esto deberia borrarlo de la pista si se queda
//																		// demasiado atras.
//				GameObjectBuilder.getInstance().remove(vehiculo); //desarrollar bien
//			}

			if (vehiculo.isActivo() == false) {
				GameObjectBuilder.getInstance().remove(vehiculo);
				removibles.add(vehiculo);
			}
		}

		for (Obstaculo obstaculo : obstaculos) {
			obstaculo.update(jugador.getVelocidad() * deltaTime);
		}

		autos.removeAll(removibles); // saco todos los que borre de la pista.

		bordeIzq.update(deltaTime * jugador.getVelocidad());
		bordeDer.update(deltaTime * jugador.getVelocidad());

	}

	public void incrementarAudio() { // HACER BIEN DESPUES, LOS AUTOS NUEVOS NO EMPIEZAN CON EL VOLUMEN BAJO O ALTO.
		for (VehiculoAutonomo auto : autos) {
			auto.incrementarAudio();
		}

		jugador.incrementarAudio();
	}

	public void reducirAudio() {
		for (VehiculoAutonomo auto : autos) {
			auto.reducirAudio();
		}

		jugador.reducirAudio();
	}
}
