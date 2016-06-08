package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import renderEngine.EntityRenderer;
import shaders.StaticShader;
import terrains.Terrain;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
	
		DisplayManager.createDisplay();
		Loader loader = new Loader();

		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
	
		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassob", loader),new ModelTexture(loader.loadTexture("grass")));
		grass.getTexture().setHasTransparency(true);
	   
		Entity entity = new Entity(staticModel, new Vector3f(0, 0, 5), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(250, 250, 125), new Vector3f(1, 1, 1));
		
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
		
			
		Camera camera = new Camera();
		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		
		for (int i = 0; i < 10; i++)	{
			entities.add(new Entity(staticModel, new Vector3f(random.nextFloat() * 800 -400,0,random.nextFloat() *-600),0,0,0,3));
			//entities.add(new Entity(grass, new Vector3f(random.nextFloat() * 800 -400,0,random.nextFloat() *-600),0,0,0,0.6f));
			
		}
	
		MasterRenderer renderer = new MasterRenderer();
		float y = 0;
		while(!Display.isCloseRequested()){
			for(Entity d:entities)
			{
				y =(float) ( y + 0.03);
				float x = (float) Math.sin(y);
				float z = (float) Math.cos(y+ 0.06);
				d.increasePosition(0,x,0);
				renderer.processEntity(d);
			}
			
			light.increasePosition(0,0,0);
			camera.move();
			renderer.processTerrain(terrain);
			
			renderer.processEntity(entity);
			renderer.render(light,  camera);
			DisplayManager.updateDisplay();	
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
}
