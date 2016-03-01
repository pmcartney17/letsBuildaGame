import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject {

	private HandleRendering handler;
	private float alpha = 1;
	private Color color;
	private int Width, Height;
	private float life;
	
	//life = 0.01 ~ 0.1
	
	
	public Trail(int x, int y, ID id,Color color,int Width,int Height, float life, HandleRendering handler) {
		super(x, y, id, handler);
		this.handler = handler;
		this.color = color;
		this.Width = Width;
		this.Height = Height;
		this.life = life;
	}


	public Rectangle getBounds() {
		return null;
	}

	
	public void tick() {
		if(alpha > life){
			alpha -= (life - 0.001f);
		}else{
			handler.removeObject(this);
		}
	}

	private AlphaComposite makeTransparent(float alpha){
		int type = AlphaComposite.SRC_OVER;
		return(AlphaComposite.getInstance(type,alpha));
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		g.setColor(color);
		g.fillRect(x, y, Width, Height);
		g2d.setComposite(makeTransparent(1));
	}

	
	
}
