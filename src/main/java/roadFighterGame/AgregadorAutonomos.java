package roadFighterGame;

import java.util.Random;

import config.Config;
import entity.Pista;
import entity.VehiculoAutonomo;
import interfaces.Updatable;
import javafx.scene.paint.Color;

public class AgregadorAutonomos implements Updatable{

//	private long tiempo;
	
	private double tiempoGeneracion;
	
//	private long tiempoEntreAutos = 2 * Config.NANOS_IN_SECOND;
	private double tiempoEntreAutos = 1.5;
	private Pista p;
	
	private double posicionPrevia, posicionActual;
	
	public AgregadorAutonomos(Pista p) {
		this.p = p;
		this.posicionActual = this.posicionPrevia = 0;
//		this.tiempo = System.nanoTime();
	}
	
//	public void update(double deltaTime) {
//		tiempo += deltaTime;
//		
//		if(tiempo > tiempoEntreAutos) {
//			tiempo = System.nanoTime();
//			
//			Random x = new Random();
//			Random y = new Random();
//			
//			agregarAuto(x.nextInt(60), y.nextInt(60)); //ver el rango que deberia tener.
//		}
//	}
	
	public void update(double deltaT) { //no estoy usando deltaT.

			double currentNano = System.nanoTime();
			
			this.posicionActual = p.getJugador().getY();
			

			if (currentNano / Config.NANOS_IN_SECOND - tiempoGeneracion > 0 && posicionActual > posicionPrevia + 100 * Config.modificadorResolucion) {
				
				posicionPrevia = posicionActual; //con esto evito el amontonamiento de vehiculos autonomos.
				
				tiempoGeneracion = currentNano / Config.NANOS_IN_SECOND + tiempoEntreAutos;
				
				
				Random x = new Random();
				System.out.println("Se agrega un auto nuevo");
				agregarAuto(x.nextInt(-Config.distAlCentro, Config.distAlCentro + 1), Config.baseHeight); //ver el rango que deberia tener.
			} //ver bien los parametros que se mandan.
		}

	private void agregarAuto(int x, int y) {
		
//		tiempoEntreAutos += (System.nanoTime() + 2 * Config.NANOS_IN_SECOND);
		
		VehiculoAutonomo v = new VehiculoAutonomo(x, y, Color.RED);
		p.add(v); //pista podria ser singleton tambien?
		GameObjectBuilder.getInstance().add(v);
	}
	
}
