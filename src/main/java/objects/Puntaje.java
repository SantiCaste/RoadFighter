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

//agregar tambien lo de la configuracion de sonido.
public class Puntaje extends ElementoDePista implements Renderable, Updatable { // ver como manejar esta clase.
	private int puntaje;

	private Text puntajeText;
	private VBox render;

	public Puntaje() {
		super(0, 0);
		this.puntaje = 0;
		puntajeText = new Text("Puntaje: " + puntaje);
		render = new VBox(puntajeText);

		render.setTranslateX(Config.baseWidth - 130);
		render.setTranslateY(100);

//		Font font = Font.loadFont(ClassLoader.getSystemResource("font/flappy-bird-numbers.ttf").toString(), 20);

		Font font = Font.loadFont("ARIAL", 100);
		puntajeText.setTextAlignment(TextAlignment.CENTER);
		puntajeText.setFont(font);
		puntajeText.setFill(Color.WHITE);

		render.setViewOrder(0);

	}

	public void bajar(double cant) {
		this.puntaje -= cant;
		System.out.println("Se baja el puntaje en " + cant);
	}

//	@Override
//	public void update(double variacion) { //ver bien que le mando para actualizar.
//		this.puntaje += variacion;
//		
//		puntajeText.setText("Puntaje: " + puntaje);
//		
////		System.out.println(puntaje);
//	}

	@Override
	public void update(double deltaTime) {
		puntajeText.setText("Puntaje: " + puntaje);

	}

	@Override
	public Node getRender() {
		return render;
	}

	public void asignar(int valor) {
		this.puntaje = valor;
	}
}
