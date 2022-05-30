package entity;

import config.Config;
//import enums.Color;
import enums.Tecla;
import interfaces.Collidator;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import objects.AudioUI;
import objects.Puntaje;
import roadFighterGame.GameObjectBuilder;

public class VehiculoJugador extends Vehiculo implements Collidator {

	private final double VELOCIDAD_MAX = 250 * Config.modificadorResolucion; // que cambie con el tamaño de la pantalla.
	private final double VELOCIDAD_HORIZONTAL = 30 * Config.modificadorResolucion;

	private int puntajeValor;

	private Puntaje puntaje;
	private AudioUI audio;
	
	private ModoVehiculoJugador modo = new ModoVehiculoJugadorNormal();

//	private ModoVehiculoJugador modo = new ModoVehiculoJugadorMejorado();

	// todos estos booleanos feos se pueden cambiar pero por ahora sirven.
	private boolean directionRight = false;
	private boolean directionLeft = false;
	private boolean directionUp = false;
	private boolean directionDown = false; // no seria abajo, sino frenar.

	private boolean modoMejorado = false; // para ver el tema de las colisiones.

	public VehiculoJugador(Color c) {
		super(0, 10, c);

		puntaje = new Puntaje();
		audio = new AudioUI();

		// 25/05/22, establezco la posicion en el inicio de la pista.

		this.setY(Config.baseHeight);
//		render.setY(Config.baseHeight - 10 * Config.modificadorResolucion);

		render.setY(Config.baseHeight - height);

//		collider.setY(Config.baseHeight - 10 * Config.modificadorResolucion);

//		collider.setY(Config.baseHeight - (26) * Config.modificadorResolucion); //en 26 queda joya.

		collider.setY(Config.baseHeight - 2 * height);
		this.setX(Config.baseCentro);

//		puntaje = 0;
		vidas = 3;

		GameObjectBuilder.getInstance().add(puntaje, audio); // aca queda feo
	}

	// avanzar viejo
//	@Override
//	public void avanzar() {
//		// primero asigno la nueva velocidad y luego avanzo
//
//		// primero asigno la nueva velocidad y luego avanzo
//		this.velocidad += this.modo.velocidadAvance();
//
//		if (this.velocidad > this.VELOCIDAD_MAX) {
//			this.velocidad = this.VELOCIDAD_MAX;
//		}
//
//		// entre 0 y 100 avanza 1, hasta 200 avanza 2, dsps avanza 3
//		
////		this.y += this.velocidad <= 100 ? 1 : this.velocidad <= 200 ? 2 : this.velocidad <= 300 ? 3 : 4;
//		
//		//cosas que agregue 25/05/22:
//		
//		int nuevaY = this.y + this.velocidad <= 100 ? 1 : this.velocidad <= 200 ? 2 : this.velocidad <= 300 ? 3 : 4;
////		this.y += this.velocidad <= 100 ? 1 : this.velocidad <= 200 ? 2 : this.velocidad <= 300 ? 3 : 4;
//		
//		this.setY(nuevaY);
//		
//		if (this.y > Pista.getLongitud())
//			this.y = Pista.getLongitud(); // lo agregue para que no se pase de la pista.
//	}

	// avanzar nuevo (25/05/22)
	@Override
	public void avanzar(double deltaT) {

		// primero asigno la nueva velocidad y luego avanzo
		this.velocidad += this.modo.velocidadAvance();

		if (this.velocidad > this.VELOCIDAD_MAX) {
			this.velocidad = this.VELOCIDAD_MAX;
		}

		double nuevoY = y + velocidad * deltaT;

		this.setY(nuevoY);

//		if (this.y < Pista.getLongitud())

		// para imagen estatica:

//		if(this.y - height < 0)
//			this.setY(0 + height); // lo agregue para que no se pase de la pista.

		// para que vuelva al inicio:

//		if (this.y < 0)
//			this.setY(Pista.getLongitud()); // la logica de que no se pueda salir de la pantalla deberia ser de la
		// pista.

	}

	public void frenar(double deltaT) {
		// primero cambio la velocidad y luego avanzo

		this.velocidad += this.modo.velocidadFreno();
		if (this.velocidad < 0) {
			// si velocidad es menor a cero significa que se freno estando quieto
			this.velocidad = 0;
		} else {
			// solo se avanza si se frena estando en movimineto
			// entre 0 y 100 avanza 1, hasta 200 avanza 2, dsps avanza 3
//			this.y += this.velocidad <= 100 ? 1 : this.velocidad <= 200 ? 2 : this.velocidad <= 300 ? 3 : 4;

			double nuevoY = y + velocidad * deltaT; // el - va porque el y = 0 es arriba de todo

			this.setY(nuevoY);

//			if (this.y > Pista.getLongitud())
//				this.y = Pista.getLongitud();

//			if (this.y < 0)
//				this.setY(Pista.getLongitud());

		}
	}

	public void ralentizarse(double deltaT) { // que se vaya deteniendo si no se esta apretando el acelerador.
		this.velocidad += (this.modo.velocidadFreno() / 5); // se detiene 5 veces mas lento que si se frenara.
		if (this.velocidad < 0)
			this.velocidad = 0;

		double nuevoY = y + velocidad * deltaT;

		this.setY(nuevoY);

//		if (this.y < 0)
//			this.setY(Pista.getLongitud());
	}

	public void cambiarModo() {
		this.modo = this.modo.cambiarModo();
	}

	@Override
	// me tiene que dejar el auto con velocidad 0 e ir reduciendola mientras da
	// vueltas hacia uno de los bordes (puede chocar o no).
	public void chocar() {
//		if (this.y - this.modo.desplazamientoChoque() < 0) { //ahora seria Config.baseHeight.
//			this.y = 0;
////			this.setY(0);
//		} else {
////			this.y -= this.modo.desplazamientoChoque();
//			this.setY(y - this.modo.desplazamientoChoque());
//		}

		// nuevo:
		// deberia perder el control y desplazarse para los costados tambien.
		this.setY(y + this.modo.desplazamientoChoque());
	}

	public ModoVehiculoJugador getModo() {
		return modo;
	}

	public void activarPowerUp() {
		if (!this.modo.getClass().getSimpleName().equals("ModoVehiculoJugadorMejorado")) {// verificar modo
			this.cambiarModo();
			this.modoMejorado = true;
		}
	}

	public void tomarPowerUp(PowerUp power) {
		if (this.y >= power.y) {
			activarPowerUp();
		}
	}
	/*
	 * public void managerPowerUp() { this.activarPowerUp();// poner modoMejorado
	 * for (int i = 0; i < 10; i++) {// despues de 10 seg desactivo power up // en
	 * la proxima iteracion de debe cambiar por tiempo } desactivarPowerUp(); }
	 */

	/*
	 * private void desactivarPowerUp() { if
	 * (this.modo.getClass().getName().equals("ModoVehiculoJugadorMejorado")) {
	 * this.cambiarModo();//vuelve a modo normal }}
	 */

//	public void morir() {	// despues hay q delegar la llamda
//		this.vivo = false;
//	}

//	public void calcularPuntaje() {
//		this.puntaje = (int) ((int) getY() * Math.PI); //se multiplica por Pi para agregarle diversion al calculo nada mas
//	}

//	public void calcularPuntaje() { // provisorio
////		this.puntaje = y + vidas * 10 + cantidadDePowerUps * 50
//
//		this.puntaje = (int) y + vidas * 10;
//	}

//	public int getPuntaje() {
//		return puntaje;
//	}

	public Node getRender() {
		return render;
	}

	// este update funciona pero con una velocidad constante fea que puse a modo de
	// prueba.
//	public void update(double deltaTime) {
//		// TODO Auto-generated method stub
//		int directionVertical = directionUp ? 1 : (directionDown ? -1 : 0);
//		setY(y - directionVertical * 50 * deltaTime); // lo que multiplica a deltaTime deberia ser la velocidad que
//														// tiene el auto.
//		
//		int directionHorizontal = directionRight ? 1 : (directionLeft ? -1 : 0);
//		
//		setX(x + directionHorizontal * 25 * deltaTime);
//	}

	// con los metodos que hicimos nosotros:
	public void update(double deltaTime) {

		puntajeValor += deltaTime * this.velocidad; // 29/05/22
		puntaje.asignar(puntajeValor);
		puntaje.update(deltaTime);
		audio.update(deltaTime);

		if (vivo == true) {

//			puntaje.update(velocidad * deltaTime); // ver como manejar el tema del puntaje.

//			puntaje.update(deltaTime);

			if (directionUp)
				this.avanzar(deltaTime);
			else if (directionDown)
				this.frenar(deltaTime);
			else
				this.ralentizarse(deltaTime); // si no se esta frenando pero tampoco se avanza que se vaya deniendo.

			int directionHorizontal = directionRight ? 1 : (directionLeft ? -1 : 0);

			// para que no se mueva solo horizontalmente si no se esta avanzando, parece
			// raro sino.
			if (velocidad > 0)
				setX(x + directionHorizontal * VELOCIDAD_HORIZONTAL * deltaTime); // esta velocidad puede no ser fija.
		} else { // si no esta vivo voy acumulando tiempo muerto.
			tiempoMuerto += deltaTime;
//			System.out.println("Tiempo muerto es de : " + tiempoMuerto + " Milisegundos");

			if (tiempoMuerto > 2.1) { // lo reaparece
//				vivo = true;

				if (this.x < Config.baseCentro) // ver bien a quien corresponde esta responsabilidad.
					setX(Config.baseCentro - 20 * Config.modificadorResolucion);
				else // si explota del lado izquierdo que aparezca de ese lado, sino del otro.
					setX(Config.baseCentro + 20 * Config.modificadorResolucion);
				explotado = false;

				if (tiempoMuerto > 4.2) { // despues de titilar 3 veces se puede volver a mover.
					vivo = true;
					tiempoMuerto = 0;
				}

			}
		}
	}

//	@Override no se si va aca, queda medio raro.
//	public void collide(Collidable collidable) {
//		super.collide(collidable);
//		
//		if(this.vivo == false){
//			puntaje.bajar(50);
//		}
//	}

	public void keyPressed(Tecla t) {

		switch (t) {
		case ARRIBA:
			this.directionUp = true;
			break;

		case ABAJO:
			this.directionDown = true;
			break;

		case IZQUIERDA:
			this.directionLeft = true;
			break;

		case DERECHA:
			this.directionRight = true;
			break;
		}
	}

	public void keyReleased(Tecla t) { // esto esta feo, se puede cambiar. no hacen falta 4 booleanos
		switch (t) {
		case ARRIBA:
			this.directionUp = false;
			break;

		case ABAJO:
			this.directionDown = false;
			break;

		case IZQUIERDA:
			this.directionLeft = false;
			break;

		case DERECHA:
			this.directionRight = false;
			break;
		}
	}

	public Shape getCollider() {
		return collider;
	}

	public void bajarPuntaje(int cant) {
		this.puntajeValor -= cant;

		if (this.puntajeValor < 0) {
			this.puntajeValor = 0;
		}
	}

	public void reducirAudio() {
		double volActual = choqueAudio.getVolume();
		volActual -= 0.1;
		
		if(volActual < 0.01)
			volActual = 0;
		
		this.audio.asignar(volActual);
		
		choqueAudio.setVolume(volActual);
		explosionAudio.setVolume(volActual);
	}

	public void incrementarAudio() {
		
		double volActual = choqueAudio.getVolume();
		
		volActual += 0.1;
		
		if(volActual > 1)
			volActual = 1;
		
		this.audio.asignar(volActual);
		
		choqueAudio.setVolume(volActual);
		explosionAudio.setVolume(volActual);
	}

	// comentado el 28/05/22
//	public void collide(Collidable collidable) { //se puede hacer super() y despues ver lo del modo, o directamente pisarlo.
//		if (collidable.getClass() == VehiculoAutonomo.class) {
//			// choca el auto contra otro auto.
////			this.chocar();
//
////			this.velocidad -= 100; //ver que hace en caso de colision, agregar animacion y eso.
////			this.velocidad = 0;
//			
//			if(this.modo.getClass() == ModoVehiculoJugadorNormal.class) {
//				this.velocidad = 0;
//			}else {
////				this.velocidad = velocidad; //si esta en el otro modo no hace nada.
//			}
//			
////			double proxVelocidad = this.modo.desplazamientoChoque();
//			
//			
//		}
//		
//		if(collidable.getClass() == Borde.class){
//			this.setX(Config.baseCentro);
//		}
//
////		if(colidable.getClase() == ExtremosPista.class) {
////			//choca contra el borde de la cancha.
////			this.morir();
////		}
//	}
}
