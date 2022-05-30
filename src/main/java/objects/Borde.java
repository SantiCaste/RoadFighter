package objects;

import config.Config;
import entity.ElementoDePista;
import interfaces.Collidable;
import interfaces.Updatable;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Borde extends ElementoDePista implements Collidable, Updatable{
	
	private Rectangle collider;

	public Borde(double x) {
		super(x, 0);
		
		this.collider = new Rectangle(x, Config.baseHeight, 2 * Config.modificadorResolucion, Config.baseHeight); //corregir la dimension de x
	}

	
	public Shape getCollider() {
		return collider;
	}
	
	public void setY(double y) {
		collider.setY(y);
	}

	public void update(double deltaT) {
		collider.setY(y + deltaT);
	}
}
