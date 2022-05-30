package roadFighterGame;

import config.Config;
import entity.VehiculoJugador;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;



//ESTA CLASE POR AHORA NO LA PUDE IMPLEMENTAR, se deberia usar para distinguir la fase de juego de la de menu,
//pero me genera problemas a la hora de compartir nodos Group, debido a que solo retorna Scenes.

public class GameSceneHandler {
	
	protected final long NANOS_IN_SECOND = 1000000000;
	protected final double NANOS_IN_SECOND_D = 1000000000.0;

	private VehiculoJugador jugador;
	private RoadFighterGame r;
	
	private long previousNanoFrame;
	
	private Scene scene;

	AnimationTimer gameTimer;

//	private EventHandler<keyEvent> keyEventHandler;

	public GameSceneHandler(RoadFighterGame r) {
		this.r = r;

//		definirEventHandlers();
	}

//	public void definirEventHandlers() {
//		keyEventHandler = new EventHandler<KeyEvent>() {
//
//			@Override
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

	public Scene getScene() {
		return scene;
	}

	public void load() {
		Group root = new Group();

		scene = new Scene(root);

		Image fondo = new Image("file:src/main/resources/img/Pista.jpg", Config.baseWidth, Config.baseHeight, false,
				false);
			// le mando la imagen y el tamaño que quiero que tenga.
		
		ImageView imageView = new ImageView(fondo);
		root.getChildren().add(imageView);
		
		jugador = new VehiculoJugador(Color.RED);
		
		root.getChildren().add(jugador.getRender());
		
		addTimeEventsAnimationTimer(); //me desarrolla el handle y el update.
		
	}

	private void addTimeEventsAnimationTimer() {
		
		previousNanoFrame = System.nanoTime();
		gameTimer = new AnimationTimer() {

			@Override
			public void handle(long currentNano) { //en el video lo explica. (33:00)
				//actualizo ticks:
				update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D); //actualizo con la diferencia pasada de nanos a segundos.
				
				previousNanoFrame = currentNano;
				
			}
		};
		
		gameTimer.start();
	}
	
	protected void update(double deltaT) {
		jugador.update(deltaT);
	}
}

//COSAS QUE COPIE DE LO DEL FLAPPY BIRD:

//	protected void addTimeEventsAnimationTimer() {
//		gameTimer = new AnimationTimer() {
//			@Override
//			public void handle(long currentNano) {
//				// Update tick
//				update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D);
//				previousNanoFrame = currentNano;
//	
//				// Update second
//				if (currentNano - previousNanoSecond > NANOS_IN_SECOND) {
//					oneSecondUpdate((currentNano - previousNanoSecond) / NANOS_IN_SECOND_D);
//					previousNanoSecond = currentNano;
//				}
//	
//			}
//		};
//	
//		previousNanoSecond = System.nanoTime();
//		previousNanoFrame = System.nanoTime();
//		gameTimer.start();
//	}
		
	//esto tenia el metodo start de RoadFighterGame:
//		Group root = new Group();
//		Scene currentScene = new Scene(root);
//
//		Image fondo = new Image("file:src/main/resources/img/Pista.jpg", Config.baseWidth, Config.baseHeight, false,
//				false);
//		// le mando la imagen
//		// y el tamaño que
//		// quiero que tenga.
//
//		// el stage va a tener el tamaño que tenga la escena. (720 x 720)
//
//		// los nodos solo aceptan nodos, e imagen no es un nodo
//		ImageView imageView = new ImageView(fondo);
//		root.getChildren().add(imageView);
//
//		Vehiculo vehiculo = new VehiculoJugador(enums.Color.ROJO);
//
//		root.getChildren().add(vehiculo.getRender());
//
//		stage.setScene(currentScene);
//		stage.setTitle("RoadFighters | Grupo 4"); // el titulo de la ventana.
//
//		this.stage = stage;
//
//		stage.show();
//		
//		vehiculo.update(2);
//	}

//	public void load(boolean fullStart) {
//		Group rootGroup = new Group();
//		scene.setRoot(rootGroup);
//		
//		score = new Score();
//		player = new FlappyBird(Config.playerCenter, Config.baseHeight / 2, score);
//		background = new Background();
//		ground = new Ground();
//		pipeBuilder = new PipeBuilder();
//		fpsInfo = new FpsInfo(fps);
//		radio = new Radio(Config.playerCenter, Config.baseHeight / 2, player);
//
//		// Add to builder
//		GameObjectBuilder gameOB = GameObjectBuilder.getInstance();
//		gameOB.setRootNode(rootGroup);
//		gameOB.add(background, radio, player, ground, score, fpsInfo, pipeBuilder);
//
//
//		if (fullStart) {
//			addTimeEventsAnimationTimer();
//			addInputEvents();
//		}
//	}