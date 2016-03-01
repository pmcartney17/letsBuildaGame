import java.awt.Color;
import java.awt.Graphics;

public class HUD {

	private int score = 0;
	private int level = 1;
	public static int Health = 1000, Healthmax = 1000;
	private int Barwidth = Game.WIDTH/2;
	private int greenValue;
	
	public void tick(){
		Health = Game.clamp(Health, 0, Healthmax);
		greenValue = (int) Math.round(Health/10*2.55);
		
		score++;
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fillRect(10, 10, Barwidth, 20);
		g.setColor(new Color(255-greenValue,greenValue,0));
		g.fillRect(10, 10, Health/10*Barwidth/100, 20);
		g.setColor(Color.white);
		g.drawRect(10, 10, Barwidth, 20);
		
		g.drawString("Score: " + score, Barwidth+(Barwidth/6), 25);
		g.drawString("Level: " + level, Barwidth+(Barwidth/2), 25);
	}
	
	public int getscore(){
		return score;
	}
	
	public int getlevel(){
		return level;
	}
	
	public void setscore(int score){
		this.score = score;
	}
	
	public void setlevel(int level){
		this.level = level;
	}
}

