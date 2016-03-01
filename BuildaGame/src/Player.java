import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject {

	public Player(int x, int y, ID id, HandleRendering handler){
		super(x,y,id, handler);
	}
	
	public void tick(){
		x+=velx;
		y+=vely;
		y = Game.clamp(y, 0, Game.frame.getContentPane().getHeight()-32);
		x = Game.clamp(x, 0, Game.frame.getContentPane().getWidth()-32);
		collision();
		if (HUD.Health <= 0){
			handler.removeObject(this);
		}
	}
	
	private void collision(){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempobject = handler.object.get(i);
			if(tempobject.getid() == ID.basicEnemy){
				if(getBounds().intersects(tempobject.getBounds())){
					//collide
					HUD.Health -= 10;
				}
			}
		}	
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}

	
	public Rectangle getBounds() {
		
		return new Rectangle(x,y,32,32);
	}
	
}
