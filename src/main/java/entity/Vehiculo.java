package entity;

import java.util.Random;

import config.Config;
import interfaces.Collidable;
import interfaces.Collidator;
import interfaces.Renderable;
import interfaces.Updatable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import objects.Borde;
import objects.Obstaculo;
import utils.AudioResources;

public abstract class Vehiculo extends ElementoDePista implements Renderable, Updatable, Collidator {// agregue
																										// collidator
																										// 28/05/22

	protected double velocidad = 0 * Config.modificadorResolucion;
	protected Color color;
	protected Integer vidas;
	protected boolean vivo = true;

	protected double tiempoMuerto = 0;

	protected int width = (int) (14 * Config.modificadorResolucion); // el ancho de la imagen * la modificacion por
																		// resolucion.
	protected int height = (int) (16 * Config.modificadorResolucion); // la altura de la imagen * la modificacion por
																		// resolucion.

//	private double x, y; // 25/5/22, capaz que no va. no va porque es de elementoDePista.

	protected Rectangle collider;

	private Image imageBase;

	protected AudioClip explosionAudio;
	protected AudioClip choqueAudio;

	protected boolean explotado = false; // lo agrego para el tema de la explosion

	protected ImageView render;

	protected ExplosionSpriteAnimation explosionAnimation;

	public Vehiculo(double x, double y, Color c) { // podemos hacer que todos los objetos sean ElementoDePista pero sin
													// llamar al constructor, solo para categorizarlos como
													// ElementoDePista y poder recorrerlos si se quiere detectar
													// colisiones o algo, ya que no todo ElementoDePista puede tener un
													// collider o un render, pero sigue siendo un elemento de la pista.
		super(x, y);
		color = c;

		initImage();
		initAudio();

//		imageBase = new Image("file:src/main/resources/img/AutoTransparente.png", width, height, false, false);
		render = new ImageView(imageBase);

		// cambio el ancla, en la mitad del eje X y en el fondo del auto.
		render.relocate(-width / 2, -height); // lo pongo al inicio de la pista, y cambio el ancla.

		render.setViewOrder(0);

//		collider = new Rectangle(width, height); // las dimensiones del collider estan un poco raras. es por la
		// dimension de la imagen del auto, deberia ser algo asi como 8x16.

		collider = new Rectangle(width - 6 * Config.modificadorResolucion, height);

		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);

		explosionAnimation = initExplosionAnimation();
	}

	protected ExplosionSpriteAnimation initExplosionAnimation() {
		Image Explosion1 = new Image("file:src/main/resources/img/Explosion1T.png", 17 * Config.modificadorResolucion,
				17 * Config.modificadorResolucion, false, false);
		Image Explosion2 = new Image("file:src/main/resources/img/Explosion2T.png", 17 * Config.modificadorResolucion,
				17 * Config.modificadorResolucion, false, false);
		Image Explosion3 = new Image("file:src/main/resources/img/Explosion3T.png", 17 * Config.modificadorResolucion,
				17 * Config.modificadorResolucion, false, false);
		//
//			Image imageB = new Image("file:src/main/resources/img/AutoTransparente.png", width, height, false, false);
		//
		Image imageB = imageBase;

		ExplosionSpriteAnimation explosionSpriteAnimation = new ExplosionSpriteAnimation(
				new Image[] { Explosion1, Explosion2, Explosion3, imageB, null }, render, Duration.millis(4500));
		explosionSpriteAnimation.setCustomFrames(new int[] { 0, 1, 2, 0, 1, 2, 4, 4, 4, 4, 3, 4, 3, 4, 3, 4, 3 });
		explosionSpriteAnimation.setCycleCount(1);
//		explosionSpriteAnimation.play();
//		explosionSpriteAnimation.stop(); //capaz no va, NO HACE FALTA QUE VAYA, LE PONGO PLAY CUANDO LA LLAMO.

		return explosionSpriteAnimation;
	}

//	public void setX(double x) {
//		this.x = x;
//	}

	private void initImage() { // ver que los autos de autonomo no puedan ser del mismo color que el jugador.

		String[] colores = new String[6];

		colores[0] = "file:src/main/resources/img/AutoRojoT.png";
		colores[1] = "file:src/main/resources/img/AutoAzulT.png";
		colores[2] = "file:src/main/resources/img/AutoAmarilloT.png";
		colores[3] = "file:src/main/resources/img/AutoNaranjaT.png";
		colores[4] = "file:src/main/resources/img/AutoVerdeT.png";
		colores[5] = "file:src/main/resources/img/AutoGloria.png";

		Random rand = new Random();

//		imageBase = new Image("file:src/main/resources/img/AutoPruebaT.png", 95 / 3, 190 / 3, false, false);
		
		//ver despues que auto queda mas chulo
		
		imageBase = new Image(colores[rand.nextInt(0, 6)], width, height, false, false); 

//		Color[] original = { Color.rgb(247, 182, 67), Color.rgb(215, 229, 204), Color.rgb(208, 48, 21),
//				Color.rgb(249, 58, 28), Color.rgb(249, 115, 39) };
//		Color[] blue = { Color.rgb(228, 96, 23), Color.rgb(215, 229, 204), Color.rgb(65, 163, 209),
//				Color.rgb(74, 193, 248), Color.rgb(84, 208, 255) };
//		Color[] yellow = { Color.rgb(252, 56, 0), Color.rgb(252, 216, 132), Color.rgb(224, 128, 44),
//				Color.rgb(249, 183, 51), Color.rgb(250, 215, 140) };
//
//		Color[][] posibleColos = { original, blue, yellow };
//
//		int randomIndex = (int) Math.floor(Math.random() * 3);
//		Color[] colorRandom = posibleColos[randomIndex];
//		
//		imageBase = Colorear.reColor(imageBase, original, colorRandom);
		
	}

	private void initAudio() {
		choqueAudio = AudioResources.getChoqueAudio();
		explosionAudio = AudioResources.getExplosionAudio();
	}

//	dieAudio = AudioResources.getDieAudio();
//	hitAudio = AudioResources.getHitAudio();
//	wingAudio = AudioResources.getWingAudio();

	@Override
	public void setY(double y) { // overridee el VehiculoAutonomo.
		this.y = y;
//		render.setY(y);
//		this.collider.setY(y);
	}

	@Override
	public void setX(double x) {
		this.x = x;
		render.setX(x);
		this.collider.setX(x - 4 * Config.modificadorResolucion/* + 6 * Config.modificadorResolucion */);
		// porque es mas chiquito que el objeto el collider.
		// el 4 es porque la imagen del vehiculo es mas grande que el collider que
		// deberia tener, hay un desfasaje de 4 pixeles.
	}

	public double getVelocidad() {
		return velocidad;
	}

	public Color getColor() {
		return color;
	}

	public Integer getVidas() {
		return vidas;
	}

	public boolean isVivo() {
		return vivo;
	}

	// avanzar viejo:
//	public abstract void avanzar();

	// avanzar nuevo (25/05/22):
	public abstract void avanzar(double deltaT);

	public abstract void chocar();

	public abstract void frenar(double deltaT);

	public abstract void cambiarModo();

	public void managerObstaculo(ObstaculoPowerUp obstaculo) {
		// hacemos el >= porque ahora esta en linea recta es decir q en algun momento lo
		// pasa por arriba
		if (this.y >= obstaculo.y)
			accionObstaculo();
	}

	public void accionObstaculo() {// reduce velocidad
		// modificar velocidad
		// de lo contrario reducir velocidad
		if (this.velocidad < 20)
			this.velocidad = 0;
		else
			this.velocidad -= 20;
	}

	// se comporta igual tanto para jugador como para bot
	public void explotar() {
		this.vidas -= 1;
		if (this.vidas < 1) {
			this.vivo = false;
		}
	}

	public void collide(Collidable collidable) { // revisar

		if (vivo == true) { // si el coche con el que choca esta vivo, hace algo. Sino no.
			
			if(collidable.getClass() == Obstaculo.class) {
				this.velocidad = 0; //ver que mas hace si es un obstaculo.
			}

			if (collidable.getClass() == VehiculoAutonomo.class || collidable.getClass() == VehiculoJugador.class) {
				Vehiculo v = (Vehiculo) collidable;

//			System.out.println(this.getClass() + " chocó a " + collidable.getClass());

				if (v.isVivo()) { // 29/05/22

					this.velocidad = 0;

					choqueAudio.play(); // ver donde va esto.

					if (v.getX() > this.getX()) {
						this.setX(x - 10 * Config.modificadorResolucion); // aca iria toda la animacion del choque.
//			this.recibirChoque();
//				v.setX(x + 10 * Config.modificadorResolucion); //no se por que con este no anda.
					} else {
						this.setX(x + 10 * Config.modificadorResolucion); // hay algunos autos que no chocan con los
																			// bordes
																			// si
//				v.setX(x - 10 * Config.modificadorResolucion); // la colision los desplaza mucho.
					}

				}
			}

//		if (collidable.getClass() == Borde.class) { // esto puede ir despues del super() en cada vehiculo, ya que los
//													// autonomos no reaparecen pero los jugadores si.
//			
//			this.setX(Config.baseCentro);
//		}

			if (collidable.getClass() == Borde.class && explotado == false) {
				//
				// PARAR MOVIMIENTO
				this.velocidad = 0;

				explosionAudio.play();

				explotado = true; // no se si este booleano va.

				vivo = false;
				// ver el tema de bajar el puntaje si explota contra el borde. no deberia ser
				// responsabilidad de vehiculo.
				System.out.println(this.getClass() + " Se puso vivo en false");

				if (this.getClass() == VehiculoJugador.class) {
					VehiculoJugador v = (VehiculoJugador) this;

					v.bajarPuntaje(1000);
				}

				explosionAnimation.play();

			}
//ver si todo esto se puede pasar a un metodo aparte, y que lo haga una sola vez.

//			Image Explosion1 = new Image("file:src/main/resources/img/Explosion1T.png",
//					17 * Config.modificadorResolucion, 17 * Config.modificadorResolucion, false, false);
//			Image Explosion2 = new Image("file:src/main/resources/img/Explosion2T.png",
//					17 * Config.modificadorResolucion, 17 * Config.modificadorResolucion, false, false);
//			Image Explosion3 = new Image("file:src/main/resources/img/Explosion3T.png",
//					17 * Config.modificadorResolucion, 17 * Config.modificadorResolucion, false, false);
//			//
////				Image imageB = new Image("file:src/main/resources/img/AutoTransparente.png", width, height, false, false);
//			//
//			Image imageB = imageBase;
//
//			ExplosionSpriteAnimation explosionSpriteAnimation = new ExplosionSpriteAnimation(
//					new Image[] { Explosion1, Explosion2, Explosion3, imageB, null }, render, Duration.millis(4500));
//			explosionSpriteAnimation.setCustomFrames(new int[] { 0, 1, 2, 0, 1, 2, 4, 4, 4, 4, 3, 4, 3, 4, 3, 4, 3 });
//			explosionSpriteAnimation.setCycleCount(1);
//			explosionSpriteAnimation.play();
		}
	}

//	if (collidable.getClass() == Borde.class) {
//
//		// PARAR MOVIMIENTO
//		
//		vivo = false;
//		
//		System.out.println("Se puso vivo en false");
//
//		Image Explosion1 = new Image("file:src/main/resources/img/Explosion1.png",
//				17 * Config.modificadorResolucion, 17 * Config.modificadorResolucion, false, false);
//		Image Explosion2 = new Image("file:src/main/resources/img/Explosion2.png",
//				17 * Config.modificadorResolucion, 17 * Config.modificadorResolucion, false, false);
//		Image Explosion3 = new Image("file:src/main/resources/img/Explosion3.png",
//				17 * Config.modificadorResolucion, 17 * Config.modificadorResolucion, false, false);
//
//		Image imageB = new Image("file:src/main/resources/img/AutoTransparente.png", width, height, false, false);
//
//		ExplosionSpriteAnimation explosionSpriteAnimation = new ExplosionSpriteAnimation(
//				new Image[] { Explosion1, Explosion2, Explosion3, imageB }, render, Duration.millis(500));
//		explosionSpriteAnimation.setCustomFrames(new int[] { 0, 1, 2, 0, 1, 2, 3 });
//		explosionSpriteAnimation.setCycleCount(1);
//		System.out.println("Se pone play");
//		explosionSpriteAnimation.play();
//
//		// RETARDO
//		
//		double tiempoActMilis = 0;
//					
//		System.out.println("La duracion de la animacion en milis: " + 
//		explosionSpriteAnimation.getTotalDuration().toMillis());
//		
////		while(explosionSpriteAnimation.getTotalDuration().toSeconds() * Config.NANOS_IN_SECOND > tiempoAct) {
////			tiempoAct++;
////		}
//		
//		while(explosionSpriteAnimation.getTotalDuration().toMillis() > tiempoActMilis) {
//			tiempoActMilis++;
//			vivo = false;
//		}
//		
//		System.out.println("Termino la explosion");
//
//		this.setX(Config.baseCentro);
//
//		// VOLVER A MOVER
//
//		vivo = true;
//		
//		System.out.println("Se puso vivo en true");
//	}
}
