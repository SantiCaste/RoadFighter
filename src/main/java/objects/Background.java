package objects;

import java.util.Random;

import config.Config;
import entity.ElementoDePista;
import interfaces.Renderable;
import interfaces.Updatable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Background extends ElementoDePista implements Updatable, Renderable{
	private HBox render;
	
	private final int bordeIzqWidth = (int)(72 * Config.modificadorResolucion); //del limite izquierdo a la pista. 30.64 sin el mapita de la izquierda.
	private final int carreteraWidth = (int)(82 * Config.modificadorResolucion); //toda la parte de la pista en la que se mueve el auto.
	private final int bordeDerWidth = (int)(102 * Config.modificadorResolucion); //de la pista al limite derecho de la imagen.
	
	private final int fotoWidth = 256; //no los usamos.
	private final int fotoHeight = 240;
	
	private final int carreteraHeight = (int)(48 * 2 * Config.modificadorResolucion); //antes era 47 y sin el * 2.
	
	private double y;
	
	
	//toma 3:
//	public Background() { //esto podria ir en Pista en vez de en Background, o que Background sea solo el pastito.
//	super(0, 0);
//	Image fondo = new Image("file:src/main/resources/img/Pista.jpg", fotoWidth, fotoHeight, false,
//	false);
//	
////	ImagePattern fondo = new ImagePattern(backgroundImage, pistaWidth, pistaHeight, pistaWidth, pistaHeight, false);
//	
////	ImagePattern imagePattern = new ImagePattern(fondo, Config.baseWidth, Config.baseHeight, 
////			Config.baseWidth, Config.baseHeight, false); //hacer esto
//	
//	ImagePattern imagePattern = new ImagePattern(fondo, fotoWidth, fotoHeight * 2, fotoWidth, fotoHeight, false);
//	
////	Rectangle bordeIzq = new Rectangle(bordeIzqWidth, Config.baseHeight);
//	Rectangle pista = new Rectangle(carreteraWidth + bordeIzqWidth + bordeDerWidth, Config.baseHeight * 2); // el * 2 no estaba.
////	Rectangle bordeDer = new Rectangle(bordeDerWidth, Config.baseHeight);
//	
//	pista.setFill(imagePattern);
//	
////	render = new HBox(bordeIzq, pista, bordeDer);
//	
//	render = new HBox(pista);
//	
//	render.setViewOrder(10);
//	
////	y = Config.baseHeight;
//}
	
	
	//el que funcionaba
	public Background() {
		super(0, 0);
		
		String[] fondos = new String[2];
		
		fondos[0] = "file:src/main/resources/img/PistaGloriaToma3.png";
		fondos[1] = "file:src/main/resources/img/PistaInfierno.png";
		
		Random rand = new Random();
		
		
//		Image fondo = new Image("file:src/main/resources/img/PistaGloriaToma3.png", Config.baseWidth, carreteraHeight, false,
//		false);
		
		Image fondo = new Image(fondos[rand.nextInt(0, 2)], Config.baseWidth, carreteraHeight, false,
		false);
		
//		ImagePattern fondo = new ImagePattern(backgroundImage, pistaWidth, pistaHeight, pistaWidth, pistaHeight, false);
		
//		ImagePattern imagePattern = new ImagePattern(fondo, Config.baseWidth, Config.baseHeight, 
//				Config.baseWidth, Config.baseHeight, false); //hacer esto
		
		ImagePattern imagePattern = new ImagePattern(fondo, Config.baseWidth, carreteraHeight, Config.baseWidth, carreteraHeight, false);
		
//		Rectangle bordeIzq = new Rectangle(bordeIzqWidth, Config.baseHeight);
		Rectangle pista = new Rectangle(carreteraWidth + bordeIzqWidth + bordeDerWidth, Config.baseHeight + carreteraHeight); // el * 2 no estaba.
//		Rectangle bordeDer = new Rectangle(bordeDerWidth, Config.baseHeight);
		
		pista.setFill(imagePattern);
		
//		render = new HBox(bordeIzq, pista, bordeDer);
		
		render = new HBox(pista);
		render.relocate(0, - carreteraHeight);
		
		render.setViewOrder(10);
		
//		y = Config.baseHeight;
	}
	
	//el que quiero probar, no funciona:
//	public Background() {
//		super(0, 0);
//		
//		Image fondo = new Image("file:src/main/resources/img/Pista.jpg", carreteraWidth, Config.baseHeight, false,
//		false);
//		
////		ImagePattern fondo = new ImagePattern(backgroundImage, pistaWidth, pistaHeight, pistaWidth, pistaHeight, false);
//		
////		ImagePattern imagePattern = new ImagePattern(fondo, Config.baseWidth, Config.baseHeight, 
////				Config.baseWidth, Config.baseHeight, false); //hacer esto
//		
//		ImagePattern imagePattern = new ImagePattern(fondo, carreteraWidth, Config.baseHeight, carreteraWidth, Config.baseHeight, false);
//		
//		Rectangle bordeIzq = new Rectangle(Config.baseWidth - bordeDerWidth - carreteraWidth, Config.baseHeight * 2);
//		Rectangle pista = new Rectangle(Config.baseWidth - bordeDerWidth, Config.baseHeight * 2); // el * 2 no estaba.
//		Rectangle bordeDer = new Rectangle(Config.baseWidth, Config.baseHeight * 2);
//		
//		pista.setFill(imagePattern);
//		
//		render = new HBox(bordeIzq, pista, bordeDer);
//		
////		render = new HBox(pista);
//		
//		render.setViewOrder(10);
//		
////		y = Config.baseHeight;
//	}
	
//	public Background() {
//		Image backgroundImage = new Image("file:src/main/resources/img/background.png", cityWidth, cityHeight, false, false);
//
//		ImagePattern image_pattern = new ImagePattern(backgroundImage, cityWidth, cityHeight, cityWidth, cityHeight,
//				false);
//
//		Rectangle sky = new Rectangle(Config.baseWidth + cityWidth, Config.baseHeight - cityHeight - grassHeight);
//		Rectangle city = new Rectangle(Config.baseWidth + cityWidth, cityHeight);
//		Rectangle grass = new Rectangle(Config.baseWidth + cityWidth, grassHeight);
//
//		sky.setFill(Color.rgb(84, 192, 201));
//		city.setFill(image_pattern);
//		grass.setFill(Color.rgb(100, 224, 117));
//
//		render = new VBox(sky, city, grass);
//		// TODO zIndex list
//		render.setViewOrder(10);
//	}


	public Node getRender() {
		return render;
	}


	public void update(double variacionY) { //el deltaTime tiene el deltaT * velocidadJugador.
//		y += -0.5 * deltaTime * 0.01;
		
		
		y += variacionY;
		
		render.setTranslateY(y % (carreteraHeight));
	}
	
}