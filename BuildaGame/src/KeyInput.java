import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private int speed = 4;
	private HandleRendering handler;
	
	public KeyInput(HandleRendering handler){
		this.handler = handler;
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			ID objectid = tempObject.getid();
			if (objectid == ID.Player){
				//player keyboard input
				if(key == KeyEvent.VK_W){tempObject.setvely(-speed);}
				if(key == KeyEvent.VK_S){tempObject.setvely(speed);}
				if(key == KeyEvent.VK_A){tempObject.setvelx(-speed);}
				if(key == KeyEvent.VK_D){tempObject.setvelx(speed);}
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE){System.exit(0);}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			ID objectid = tempObject.getid();
			if (objectid == ID.Player){
				//player keyboard input
				if(key == KeyEvent.VK_W){tempObject.setvely(0);}
				if(key == KeyEvent.VK_S){tempObject.setvely(0);}
				if(key == KeyEvent.VK_A){tempObject.setvelx(0);}
				if(key == KeyEvent.VK_D){tempObject.setvelx(0);}
			}
		}
	}
	
}
