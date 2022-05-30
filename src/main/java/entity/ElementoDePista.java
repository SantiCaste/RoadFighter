package entity;

import javafx.scene.image.ImageView;

public class ElementoDePista {
	protected double y; // pos
	protected double x;
	protected ImageView render; //25/05/22. CADA ELEMENTO DE PISTA DEBE TENER SU PROPIO RENDER.
	
	public ElementoDePista(double x, double y) { // elementos de pista de crean antes de jugar
		this.x = x;
		this.y = y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getX() {
		return x;
	}
	
	//con este funciona bien:
//	public ElementoDePista(double y) { // elementos de pista de crean antes de jugar
//		this.y = y;
//	}
//
//	public void setY(double y) {
//		this.y = y;
//	}
//
//	public double getY() {
//		return y;
//	}
//	
//	public void setX(double x) {
//		this.x = x;
//	}
}
