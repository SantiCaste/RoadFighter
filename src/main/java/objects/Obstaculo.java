package objects;

import config.Config;
import entity.ElementoDePista;
import interfaces.Collidable;
import interfaces.Renderable;
import interfaces.Updatable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Obstaculo extends ElementoDePista implements Updatable, Renderable, Collidable{
	private final int width = (int)(18 * Config.modificadorResolucion);
	private final int height = (int)(18 * Config.modificadorResolucion);
	private final int imageHeight = 42;
	private final int offScreenTolerance = 50; //no lo usamos.
	
	private ImageView render;
	private Rectangle collider;
	
	
	public Obstaculo(double x, double y) {
		super(x, y);
		this.y = 0;
		Image obstaculo;
		
		obstaculo = new Image("file:src/main/resources/img/obstaculo.png", width, imageHeight, false, false);
		
		render = new ImageView(obstaculo);
		
		render.setViewOrder(0);

		collider = new Rectangle(width, height);
		collider.relocate(Config.baseCentro + x, 0);
		render.relocate(Config.baseCentro + x, 0);
		collider.setFill(null);
		collider.setStroke(Color.FUCHSIA);
	}

	@Override
	public Node getRender() {
		return render;
	}

	@Override
	public void update(double variacion) {
		setY(this.y + variacion);
		
	}
	
	@Override
	public void setY(double y) {
		this.y = y;
		render.setY(y);
		collider.setY(y);
	}


	@Override
	public Shape getCollider() {
		return collider;
	}

}
