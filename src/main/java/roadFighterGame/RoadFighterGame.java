package roadFighterGame;

import java.util.List;

import config.Config;
import entity.Pista;
import entity.VehiculoJugador;
//import enums.Color;
import enums.Tecla;
import interfaces.Collidable;
import interfaces.Collidator;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import objects.Background;

public class RoadFighterGame extends Application /* implements Updatable */ {

	private Stage stage; // seria como la ventana. por ahora no me hace falta tenerlo como atributo.
	private GameSceneHandler gameSceneHandler; // esto capaz ya no hace falta.
	private Scene currentScene;

	protected final long NANOS_IN_SECOND = 1000000000;
	protected final double NANOS_IN_SECOND_D = 1000000000.0;

	private VehiculoJugador jugador;
	private Pista pista;
	private Background background;

	private long previousNanoFrame;

	AnimationTimer gameTimer;
	private EventHandler<KeyEvent> keyEventHandler;

	private GameObjectBuilder gameObjectBuilder;

	AgregadorAutonomos a;
	AgregadorObstaculos o;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) { // hay que crear una escena.
//puedo intercambiar entre escenas, por ej, para pasar del menu al juego.

		Group root = new Group();
		currentScene = new Scene(root, Config.baseWidth, Config.baseHeight);

//		Image fondo = new Image("file:src/main/resources/img/Pista.jpg", Config.baseWidth, Config.baseHeight, false,
//				false);
		// le mando la imagen y el tamaño que quiero que tenga.

		// el stage va a tener el tamaño que tenga la escena.

		// los nodos solo aceptan nodos, e imagen no es un nodo.
//		ImageView imageView = new ImageView(fondo);
//		root.getChildren().add(imageView);

		background = new Background();

//		root.getChildren().add(background.getRender());

		jugador = new VehiculoJugador(Color.RED); // un jugador por partida. el color deberia poder ser determinable

		pista = new Pista();
		
//		Obstaculo obs = new Obstaculo(10, 0);
		
//		pista.add(obs);
		
//		GameObjectBuilder.getInstance().add(obs);

//		root.getChildren().add(jugador.getRender());

		addUpdateEachFrame();

		stage.setScene(currentScene);
		stage.setTitle("RoadFighters | Grupo 4"); // el titulo de la ventana.

		this.stage = stage;

		agregarEventHandlers();

//		VehiculoAutonomo v = new VehiculoAutonomo(5, 40, Color.BLUE); // desarrollar una forma que permita generar autos
		// con un criterio, un cierto tiempo, posicion,
		// lo que sea.

		// Cambios de 26/05/22
//		pista.add(jugador);

		pista.addJugador(jugador);
//		pista.add(v);

//		root.getChildren().add(v.getRender()); // esto podria hacerse en otro lado, pero no se como, PREGUNTAR. sino,
		// para cada autonomo nuevo tenemos que hacer esto dentro de este
		// metodo.
//		VehiculoAutonomo v2 = new VehiculoAutonomo(40, 20, Color.VERDE);

//		root.getChildren().add(v2.getRender());

//		pista.add(v2); // esto tambien hacerlo dentro de esa nueva clase. podria ser un ObjectBuilder
		// como en flappyBird.

		gameObjectBuilder = GameObjectBuilder.getInstance(); // no deberia ir
		gameObjectBuilder.setRoot(root);
//		gameObjectBuilder.add(jugador, v, background);
		gameObjectBuilder.add(jugador, background);

//		root.getChildren().add(jugador.getCollider());
//		root.getChildren().add(v.getCollider());

		a = new AgregadorAutonomos(pista);
		o = new AgregadorObstaculos(pista);
		
		stage.show();
	}

//	public void update(double delta) {
//		frames++;
//
//		List<Updatable> updatables = GameObjectBuilder.getInstance().getUpdatables();
//		for (Updatable updatable : updatables) {
//			updatable.update(delta);
//		}
//	}

	private void agregarEventHandlers() {

//		currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//
//			public void handle(KeyEvent e) {
//				switch (e.getCode()) {
//				case UP:
//				case W:
//					jugador.keyPressed(Tecla.ARRIBA);
//					break;
//
//				case DOWN:
//				case S:
//					jugador.keyPressed(Tecla.ABAJO);
//					break;
//
//				case LEFT:
//				case A:
//					jugador.keyPressed(Tecla.IZQUIERDA);
//					break;
//
//				case RIGHT:
//				case D:
//					jugador.keyPressed(Tecla.DERECHA);
//					break;
//				}
//			}
//		});

		currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
//4 booleanos es feo y no hace falta.
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				case UP:
				case W:
					jugador.keyPressed(Tecla.ARRIBA);

					break;

				case DOWN:
				case S:
					jugador.keyPressed(Tecla.ABAJO);
					break;

				case LEFT:
				case A:
					jugador.keyPressed(Tecla.IZQUIERDA);
					break;

				case RIGHT:
				case D:
					jugador.keyPressed(Tecla.DERECHA);
					break;

				case ESCAPE:
					System.exit(0); // capaz que no
					break;
					
				case ADD:
					pista.incrementarAudio(); //ver como corregirlo.
					break;
				
				case SUBTRACT:
					pista.reducirAudio();
					break;
				}
			}
		});

		currentScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {

			public void handle(KeyEvent e) { // ver como hacer para que el switch haga el update.
				switch (e.getCode()) {
				case UP:
				case W:
					jugador.keyReleased(Tecla.ARRIBA);
//					jugador.avanzar(NANOS_IN_SECOND);
					break;

				case DOWN:
				case S:
					jugador.keyReleased(Tecla.ABAJO);
					break;

				case LEFT:
				case A:
					jugador.keyReleased(Tecla.IZQUIERDA);
					break;

				case RIGHT:
				case D:
					jugador.keyReleased(Tecla.DERECHA);
					break;
				}
			}
		});
	}

	// esto no lo pude hacer:
//		definirEventHandlers();

//			@Override
//			public void handle(KeyEvent e) {
//				switch(e.getCode()) {
//				
//				}
//			}
//		});

	// HABIA QUERIDO USAR GameSceneHandler PERO NO LO SÉ HACER, guiandome del
	// flappyBird tampoco pude.
//		this.stage = stage;
//		
//		gameSceneHandler.load();
//		Scene scene = gameSceneHandler.getScene();
//		
//		stage.setTitle("RoadFighters | Grupo 4"); // el titulo de la ventana.
//		stage.setScene(scene);
//		
//		stage.show();

	private void addUpdateEachFrame() {

		previousNanoFrame = System.nanoTime();
		AnimationTimer gameTimer = new AnimationTimer() {

			@Override
			public void handle(long currentNano) { // en el video lo explica. (33:00)
				// actualizo ticks:
				update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D); // actualizo con la diferencia pasada de
																				// nanos a segundos.

				previousNanoFrame = currentNano;

			}
		};

		gameTimer.start();
	}

//	private void definirEventHandlers() {
//		keyEventHandler = new EventHandler<KeyEvent>() {
//
////		@Override
//			public void handle(KeyEvent e) {
//				switch (e.getCode()) {
//
//				case W:
//				case UP:
//					jugador.update(1);
//				}
//			}
//		};
//
//	}

	// hacer un manager de pista que se encarge de actualizar todo en la pista, que
	// tenga como atributo al jugador para poder hacer que la pista se desplace
	// segun la velocidad del jugador.

	public void update(double deltaT) { // el update debe recorrer todos los objetos y actualizarlos.
		pista.update(deltaT);
		background.update(deltaT * jugador.getVelocidad()); // se desplaza la pista segun la velocidad del jugador.

		checkColliders();

		a.update(deltaT);
		o.update(deltaT);

		// se puede recorrer una lista de updatables y hacerle update a cada uno de los
		// elementos de esta lista. (y depues checkColliders ya que usa otra logica).
	}

//	public void update(double deltaT) {
//
//		List<Updatable> updatables = GameObjectBuilder.getInstance().getUpdatables();
//		for (Updatable updatable : updatables) {
//			updatable.update(deltaT);
//		}
//	}

	private void checkColliders() { // no se hacer esto.
//		List<Collidator> collidators = gameObjectBuilder.getCollidators();
		List<Collidator> collidators = GameObjectBuilder.getInstance().getCollidators();

//		List<Collidable> collidables = gameObjectBuilder.getCollidables();
		List<Collidable> collidables = GameObjectBuilder.getInstance().getCollidables();

//		for (Collidator collidator : collidators) {
//			for (Collidable collidable : collidables) {
//
//				Shape interseccion = Shape.intersect(collidator.getCollider(), collidable.getCollider());
//
//				if (interseccion.getBoundsInLocal().getWidth() != -1) {
//					collidator.collide(collidable);
//				}
//
//				// hay que comparar posicion de collidator.getCollider() con posicion de
//				// collidable.getCollider()
//				
////				if(collidator.getCollider().getBoundsInLocal().get)
//				
////				if(collidator.getClass() == VehiculoJugador.class) {
////					VehiculoJugador j = (VehiculoJugador)collidator;
//					
////					j.getX()
////				}
//
//			}
//		}

		for (int i = 0; i < collidators.size(); i++) {
			Collidator collidator = collidators.get(i);
			for (int j = i + 1; j < collidators.size(); j++) {
				Collidator otherCollidator = collidators.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), otherCollidator.getCollider());
				if (intersect.getBoundsInLocal().getWidth() != -1) {

//					System.out.println(collidator.getClass() + " choca a " + otherCollidator.getClass());
					collidator.collide(otherCollidator);

//					System.out.println(otherCollidator.getClass() + " choca a " + collidator.getClass());
					otherCollidator.collide(collidator);
				}
			}

			// agregado 27/05/22
			for (int j = 0; j < collidables.size(); j++) {
				Collidable collidable = collidables.get(j);
				Shape intersect = Shape.intersect(collidator.getCollider(), collidable.getCollider());

				if (intersect.getBoundsInLocal().getWidth() != -1) {
					collidator.collide(collidable);
				}
			}
		}
	}

}