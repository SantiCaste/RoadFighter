package config;

public class Config {
//modificadorResolucion en 2.5
	public final static double modificadorResolucion = 2.5; // tener cuidado con esto porque afecta a la velocidad del
															// auto, ya que se mueve en pixeles.

	// se pueden evitar los casteos si fueran Integer.
	public final static int baseHeight = (int) (240 * modificadorResolucion); // 256 es el alto de la imagen original.
	public final static int baseWidth = (int) (256 * modificadorResolucion); // 240 es el ancho de la imagen original.
	public final static int baseCentro = (int) (112 * modificadorResolucion); // el centro de la carretera.

	public final static int bordeIzquierdo = (int)(74 * modificadorResolucion);
	public final static int bordeDerecho = (int)(152 * modificadorResolucion);
	
	public final static int distAlCentro = (int)(13 * Config.modificadorResolucion);
	
	public final static int NANOS_IN_SECOND = 1000000000;

//	public final static int groundHeight = 80;

//	public final static double gravity = 1300;
//	public final static double jumpForce = 500;
//	public static double baseSpeed = 250;

//	public final static double emptySpace = 0.25;

//	public final static double pipesPerSecond = 1.3;
//	public final static int playerCenter = baseWidth / 3;

//	public static int maxScore = 0;
}
