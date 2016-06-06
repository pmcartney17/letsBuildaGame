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
		
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(
				loader.loadTexture("white")));
		
		TexturedModel grass = new TexturedModel(model, new ModelTexture(
				loader.loadTexture("grass")));
				grass.getTexture().setHasTransparency(true);
		TexturedModel fern = new TexturedModel(OBJLoader.loadObjModel("grassob", loader),
				new ModelTexture(loader.loadTexture("grass")));
		fern.getTexture().setHasTransparency(true);
	   
		Entity entity = new Entity(staticModel, new Vector3f(0, 0, 5), 0, 160, 0, 50);;
		Light light = new Light(new Vector3f(250, 250, 125), new Vector3f(1, 1, 1));
		
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("grass")));
			
		Camera camera = new Camera();
		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		for (int i = 0; i < 25; i++)	{
			entities.add(new Entity(staticModel, new Vector3f(random.nextFloat() * 800 - 400, 0,
					random.nextFloat() * -600), 0, 0, 0, 3));
			//entities.add(new Entity(dragon, new Vector3f(random.nextFloat() * 800 - 400, 0,
			//		random.nextFloat() * -600), 0, 0, 0, 1));
			entities.add(new Entity(fern, new Vector3f(random.nextFloat() * 800 - 400, 0,
					random.nextFloat() * -600), 0, 0, 0, 0.6f));			
			/*float x = random.nextFloat() * 100 - 50;
			
			float z = random.nextFloat() * -300;
			grass.add(new Entity(staticModel, new Vector3f (x,0,z), random.nextFloat() *180f,
						random.nextFloat() *10f, 1f, 0.2f));*/
			
			
		}
	
		
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0, 1, 0);
			camera.move();
			
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
			 
			
			   	/*(Entity cube : grass){
					renderer.processEntity(cube);
				}*/
			   
			   	renderer.processEntity(entity);
				renderer.render(light,  camera);
				DisplayManager.updateDisplay();	
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
}
