import java.util.Random;

public class Spawn {
	
	private HandleRendering handler;
	private HUD hud;
	private int scorekeep = 0;
	private Random rand = new Random();
	
	public Spawn(HandleRendering handler, HUD hud){
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick(){
		scorekeep++;
		if(scorekeep >= 50 && handler.object.size() <= 1500){
			scorekeep=0;
			hud.setlevel(hud.getlevel()+1);
			new BasicEnemy(rand.nextInt(Game.frame.getContentPane().getWidth()-17),rand.nextInt(Game.frame.getContentPane().getHeight()-17),ID.basicEnemy,handler);
		}
	}

}
