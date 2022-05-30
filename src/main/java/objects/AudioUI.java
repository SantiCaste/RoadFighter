package objects;

import config.Config;
import entity.ElementoDePista;
import interfaces.Renderable;
import interfaces.Updatable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AudioUI extends ElementoDePista implements Renderable, Updatable{

	private double valor;
	
	private Text audioText;
	private VBox render;
	
	public AudioUI() { //este deberia relacionarse con RoadFighterGame.
		super(0, 0);
		this.valor = 1;
		audioText = new Text("Audio: " + valor * 100 + "%");
		render = new VBox(audioText);
		
		render.setTranslateX(Config.baseWidth - 130);
		render.setTranslateY(50);
		
		Font font = Font.loadFont("ARIAL", 42);
		audioText.setTextAlignment(TextAlignment.CENTER);
		audioText.setFont(font);
		audioText.setFill(Color.FUCHSIA);
		
		render.setViewOrder(0);
	}
	
	@Override
	public void update(double deltaTime) {
		audioText.setText("Audio: " + (int)(valor * 100) + "%");
	}

	@Override
	public Node getRender() {
		return render;
	}
	
	public void asignar(double valor) {
		this.valor = valor;
	}

}

//public class Puntaje extends ElementoDePista implements Renderable, Updatable { // ver como manejar esta clase.
//	private int puntaje;
//
//	private Text puntajeText;
//	private VBox render;
//
//	public Puntaje() {
//		super(0, 0);
//		this.puntaje = 0;
//		puntajeText = new Text("Puntaje: " + puntaje);
//		render = new VBox(puntajeText);
//
//		render.setTranslateX(Config.baseWidth - 130);
//		render.setTranslateY(100);
//
//		Font font = Font.loadFont("ARIAL", 100);
//		puntajeText.setTextAlignment(TextAlignment.CENTER);
//		puntajeText.setFont(font);
//		puntajeText.setFill(Color.WHITE);
//
//		render.setViewOrder(0);
//
//	}
//
//	@Override
//	public void update(double deltaTime) {
//		puntajeText.setText("Puntaje: " + puntaje);
//
//	}
//
//	@Override
//	public Node getRender() {
//		return render;
//	}
//
//	public void asignar(int valor) {
//		this.puntaje = valor;
//	}
//}