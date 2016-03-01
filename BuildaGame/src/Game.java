import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	public static JFrame frame;
	public static final int WIDTH = 1200, HEIGHT = (WIDTH/16)*10;
	private static final String TITLE = "Noob java game";
	private static final long serialVersionUID = -6112428091888191314L;
	
	private Thread thread;
	private boolean running = false;
	private Random rand = new Random();

	private HUD hud;
	private HandleRendering handler;
	private Spawn spawn;
	
	public Game(){
		handler = new HandleRendering();
		hud = new HUD();
		spawn = new Spawn(handler, hud);
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH,HEIGHT,TITLE,this,frame);
		
		start();
		
		new BasicEnemy(rand.nextInt(Game.frame.getContentPane().getWidth()-17),rand.nextInt(Game.frame.getContentPane().getHeight()-17),ID.basicEnemy,handler);
		new Player(WIDTH/2-16,HEIGHT/2-16,ID.Player,handler);
		
		
	}
	

	public synchronized void start(){
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	
	public synchronized void Stop(){
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public void run(){
		long lastTime = System.nanoTime();
		double amountofticks = 60;
		double ns = 1000000000/amountofticks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while (delta >=1){
				tick();
				delta--;
			}
			
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				frame.setTitle(TITLE +" FPS: " + frames);
				frames = 0;
			}
		}
		Stop();
	}
	
	
	private void tick(){
		handler.tick();
		hud.tick();
		spawn.tick();
		System.out.println(handler.object.size());
	}
	
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		hud.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max){
		if(var >= max){ return var = max; }
		else if ( var <= min ){return var = min;}
		else {return var;}
	}
	
	
	public static void main(String args[]){
		frame = new JFrame(TITLE);
		new Game();
	}

}
