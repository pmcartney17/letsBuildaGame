import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{

	private int speed = 4;
	
	public BasicEnemy(int x,int y, ID id, HandleRendering handler) {
		super(x,y,id, handler);
		velx = -speed; vely = -speed;
	}
	
	public void tick() {
		x += velx;
		y += vely;
		if(y <= 0 || y >= Game.frame.getContentPane().getHeight()-16){vely *= -1;}
		if(x <= 0 || x >= Game.frame.getContentPane().getWidth()-16){velx *= -1;}
		new Trail(x,y,ID.Trail,Color.RED,16,16,0.02f,handler);
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,16,16);
	}

}
