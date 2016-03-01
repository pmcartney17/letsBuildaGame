import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x,y;
	protected ID id;
	protected int velx, vely;
	protected HandleRendering handler;
	
	public GameObject(int x, int y, ID id,HandleRendering handler){
		this.x = x;
		this.y = y;
		this.id = id;
		this.handler = handler;
		handler.addobject(this);
	}
	
	
	public abstract Rectangle getBounds();
	public abstract void tick();
	public abstract void render(Graphics g);
	
	public void setx(int x) {
		this.x = x;
	}
	public void sety(int y){
		this.y = y;
	}
	public int getx(){
		return x;
	}
	public int gety(){
		return y;
	}
	public void setid(ID id){
		this.id = id;
	}
	public ID getid(){
		return id;
	}
	public void setvelx(int velX){
		this.velx = velX;
	}
	public void setvely(int velY){
		this.vely = velY;
	}
	public int getvelx(){
		return velx;
	}
	public int getvely(){
		return vely;
	}
	
	
}
