package entity;

import config.Config;
import interfaces.Collidator;
import interfaces.Updatable;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public class VehiculoAutonomo extends Vehiculo implements Updatable, Collidator/* Collidable */ {

	private boolean activo;
	private double velocidadRelativa;

	public VehiculoAutonomo(double x, double y, Color color) {
		super(x, y, color); // ver si los set deberian estarn en elementoDePista.

		this.setY(Config.baseHeight - y * Config.modificadorResolucion);
		this.setX(Config.baseCentro + x * Config.modificadorResolucion); // ver bien esto.
		this.velocidad = 175 * Config.modificadorResolucion;
		this.velocidadRelativa = velocidad;

		render.setY(Config.baseHeight - y * Config.modificadorResolucion);
//		collider.setX(Config.baseCentro + x * Config.modificadorResolucion);
//		collider.setY(Config.baseHeight - y * Config.modificadorResolucion); // revisar donde poner el collider.
//		this.setX(Config.baseCentro);

//		collider.setY(Config.baseHeight - (y - 56) * Config.modificadorResolucion); //en 26 queda joya.
//		this.setX(Config.baseCentro + x * Config.modificadorResolucion);

//		this.collider = new Rectangle(this.x, this.y, this.width, this.height); // ver si lo puede hacer el padre.

		this.activo = true;

		vidas = 1;
	}

//	@Override //sobreescribo xq tiene comportamientos personalizados al ser un bot, sino lo trato como Vehiculo
//	public void avanzar() {
//		this.velocidad = 100; //velocidad constante al avanzar
//		this.y += 1; //avance posicion tmbn
//	}

	public void avanzar(double deltaT) {

//		render.setTranslateY(y - velocidadRelativa * deltaT);

//	this.y += 1; //avance posicion tmbn

		this.setY(y - velocidadRelativa * deltaT); // no se si está bien.
		
		if(this.vivo) //29/05/22, para que la imagen del auto no avance si no esta vivo.
			this.velocidad = 300; // capaz que no va.
	}

	@Override
	public void setY(double y) {
		this.y = y; // estaria reduciendo la posicion en y?. me importa?
		render.setY(y);

		collider.setY(y - height);
	}

	@Override
	public void chocar() {
		this.y -= 1;
	}

	@Override
	public void frenar(double deltaT) {// se agrega para mantener la consistencia de la clase padre
		// TODO Auto-generated method stub
	}

	@Override
	public void cambiarModo() {// se agrega para mantener la consistencia de la clase padre
		// TODO Auto-generated method stub
	}

	public Node getRender() {
		return render;
	}

	public boolean isActivo() {
		return activo;
	}

	public void update(double deltaT) {

		if (this.activo)
			this.avanzar(deltaT);

		if (vivo != true) {
			tiempoMuerto += deltaT;

			if (tiempoMuerto > 1.6) { // aca el tiempo muerto es menor porque solo quiero la animacion de la
										// explocion, no la del parpadeo.
				this.activo = false;
				
				System.out.println(this.getClass() + " dejo de estar activo, se borra");
			}
		}
	}

//	public void update(double deltaT) {
//		this.setY(y + deltaT);
//	}

//	private boolean isOffScreen() {
//		return ()
//	}

	public void activar() { // no se lo llama nunca.
		this.activo = true;
	}

	public void setVelocidadRelativa(double v) {
		this.velocidadRelativa = v;
	}

	public Shape getCollider() {
		return collider;
	}

	public void incrementarAudio() {
		double volActual = choqueAudio.getVolume();
		
		volActual += 0.1;
		
		if(volActual > 1)
			volActual = 1;
				
		choqueAudio.setVolume(volActual);
		explosionAudio.setVolume(volActual);
	}

	public void reducirAudio() {
		double volActual = choqueAudio.getVolume();
		volActual -= 0.1;
		
		if(volActual < 0.01)
			volActual = 0;
				
		choqueAudio.setVolume(volActual);
		explosionAudio.setVolume(volActual);
	}

////	@Override
//	public void collide(Collidable collidable) {
//		super.collide(collidable);
//		
//		if(this.vivo == false) {
//			GameObjectBuilder.getInstance().remove(this);
//		}
//		
//	}

	// lo comente el 28/05/22
//	public void collide(Collidable collidable) {
//		//se puede hacer un super().
//		if(collidable.getClass() == VehiculoAutonomo.class || collidable.getClass() == VehiculoJugador.class) {
//			this.velocidad = 0;
//			
//			Vehiculo v = (Vehiculo)collidable;
//			
//			if (v.getX() > this.getX()) {
//				this.setX(x - 10);
////			this.recibirChoque();
//			} else {
//				this.setX(x + 10);
//			}
//			
////			this.setX(x + 10); //27/05/22
////			this.setY(y - 10);
//		}
//		
////		if(collidable.getClass() == VehiculoJugador.class) {
////			VehiculoJugador j = (VehiculoJugador)collidable;
////			
////			if(j.getX() > this.getX()) {
////				this.setX(x - 10);
//////				this.recibirChoque();
////			}
////			else {
////				this.setX(x + 10);
////			}
////		}
//		
//		if(collidable.getClass() == Borde.class) {
//			this.setX(Config.baseCentro);
//		}
//	}

//	if(colidable.getClass() == VehiculoAutonomo.class) {
//		//choca el auto contra otro auto.
////		this.chocar();
//		
////		this.velocidad -= 100; //ver que hace en caso de colision, agregar animacion y eso.
//		this.velocidad = 0;
//	}

//	private void setPosX(double posX) {
//		this.posX = posX;
//		render.setTranslateX(posX - width / 2);
//		collider.setX(posX - width / 2);
//	}
//
//	@Override
//	public void update(double deltaTime) {
//		setPosX(posX + -Config.baseSpeed * deltaTime);
//
//		if (isOffScreen()) {
//			GameObjectBuilder.getInstance().remove(this);
//		}
//	}

//	@Override
//	public void update(double deltaT) {
//		setY(y - velocidad * deltaT);
//		
//	}

}
