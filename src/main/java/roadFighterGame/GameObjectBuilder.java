package roadFighterGame;

import java.util.ArrayList;
import java.util.List;

import entity.ElementoDePista;
import interfaces.Collidable;
import interfaces.Collidator;
import interfaces.Renderable;
import interfaces.Updatable;
import javafx.scene.Group;

//esta clase se va a encargar de revisar choques, y todo eso. es igual a la de flappyBird.
public class GameObjectBuilder {

	private static GameObjectBuilder instance = null;

	private Group root = null;

	private List<ElementoDePista> allObjects = new ArrayList<ElementoDePista>();
	private List<Collidable> collidables = new ArrayList<Collidable>();
	private List<Collidator> collidators = new ArrayList<Collidator>();
	private List<Renderable> renderables = new ArrayList<Renderable>();
	private List<Updatable> updatables = new ArrayList<Updatable>();

	// sin los group me tira error en la interseccion de formas para detectar
	// colisiones.
	private Group objectsGroup = new Group();
	private Group collidersGroup = new Group();

	private GameObjectBuilder() {

	}

	public static GameObjectBuilder getInstance() {
		if (instance == null) {
			instance = new GameObjectBuilder();
		}

		return instance;
	}

	public void setRoot(Group root) {
		this.root = root;
		root.getChildren().add(objectsGroup);
//		root.getChildren().add(collidersGroup); // muestra los colliders
	}

	public void add(ElementoDePista... elementos) { // se rompe cuando se usa este metodo

		for (ElementoDePista elemento : elementos) {
			// si es un collidable:
			allObjects.add(elemento);

			if (Updatable.class.isAssignableFrom(elemento.getClass())) {
				updatables.add((Updatable) elemento);
			}

			if (Renderable.class.isAssignableFrom(elemento.getClass())) {
				renderables.add((Renderable) elemento);

				Renderable elementoRenderable = (Renderable) elemento;

				objectsGroup.getChildren().add(elementoRenderable.getRender());
			}

			if (Collidator.class.isAssignableFrom(elemento.getClass())) {
				collidators.add((Collidator) elemento);

				Collidator elementoCollidator = (Collidator) elemento;

				collidersGroup.getChildren().add(elementoCollidator.getCollider());
			}
			// no se por que va el else if, en el flappyBird estaba
			else if (Collidable.class.isAssignableFrom(elemento.getClass())) {
				collidables.add((Collidable) elemento);

				collidersGroup.getChildren().add(((Collidable) elemento).getCollider());
			}
		}
	}

	public List<Updatable> getUpdatables() {
		return new ArrayList<Updatable>(updatables);
	}

	public List<Collidable> getCollidables() {
		return new ArrayList<Collidable>(collidables);
	}

	public List<Collidator> getCollidators() {
		return new ArrayList<Collidator>(collidators);
	}

	public void remove(ElementoDePista... elementos) {
		// checkRootNode();

		for (ElementoDePista elemento : elementos) {
			allObjects.remove(elemento);
			System.out.println("Se extrae el vehiculo " + elemento.getClass());
			if (Updatable.class.isAssignableFrom(elemento.getClass())) {
				updatables.remove((Updatable) elemento);
			}

			if (Renderable.class.isAssignableFrom(elemento.getClass())) {
				Renderable renderableGameObject = (Renderable) elemento;
				renderables.remove(renderableGameObject);

				objectsGroup.getChildren().remove(renderableGameObject.getRender());
			}

			if (Collidator.class.isAssignableFrom(elemento.getClass())) {
				Collidator collidatorGameObject = (Collidator) elemento;
				collidators.remove(collidatorGameObject);

				collidersGroup.getChildren().remove(collidatorGameObject.getCollider());
			} else if (Collidable.class.isAssignableFrom(elemento.getClass())) {
				Collidable collideableGameObject = (Collidable) elemento;
				collidables.remove(collideableGameObject);

				collidersGroup.getChildren().remove(collideableGameObject.getCollider());
			}

//			elemento.destroy(); // ver que haria en nuestro caso.
		}
	}
}
