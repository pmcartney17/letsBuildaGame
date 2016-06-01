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

		RawModel model = OBJLoader.loadObjModel("grassob", loader);
				TexturedModel staticModel = new TexturedModel(model, new ModelTexture(
				loader.loadTexture("grass (1)")));
				model.getTexture().setHasTransparency(true)	;
		
	   
		Entity entity = new Entity(staticModel, new Vector3f(0, 0, 5), 0, 160, 0, 50);;
		Light light = new Light(new Vector3f(250, 250, 125), new Vector3f(1, 1, 1));
		
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(1,0,loader,new ModelTexture(loader.loadTexture("grass")));
			
		Camera camera = new Camera();
		
		List<Entity> grass = new ArrayList<Entity>();
		Random random = new Random();


		
		for (int i = 0; i < 25; i++)		{
			float x = random.nextFloat() * 100 - 50;
			
			float z = random.nextFloat() * -300;
			grass.add(new Entity(staticModel, new Vector3f (x,0,z), random.nextFloat() *180f,
						random.nextFloat() *10f, 1f, 0.2f));
			
			
		}
	
		
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0, 0.2f, 0);
			camera.move();
				renderer.processTerrain(terrain);
			   	renderer.processTerrain(terrain2);
			 
			
			   	for (Entity cube : grass){
					renderer.processEntity(cube);
				}
			   
			   	renderer.processEntity(entity);
				renderer.render(light,  camera);
				DisplayManager.updateDisplay();	
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
}
