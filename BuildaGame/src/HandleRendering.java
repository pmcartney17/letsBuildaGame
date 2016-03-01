import java.awt.Graphics;
import java.util.LinkedList;

public class HandleRendering {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick(){
		for (int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g){
		for (int i = 0; i < object.size(); i++){
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addobject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
	
}
