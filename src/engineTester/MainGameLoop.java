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
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
	
		DisplayManager.createDisplay();
		Loader loader = new Loader();

		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(
				loader.loadTexture("white")));
		
		//Entity entity = new Entity(staticModel, new Vector3f(0, 0, -25), 0, 160, 0, 1);;
		Light light = new Light(new Vector3f(250, 250, 125), new Vector3f(1, 1, 1));
		
		Camera camera = new Camera();
		
		List<Entity> allCubes = new ArrayList<Entity>();
		Random random = new Random();
		
		for (int i = 0; i < 25; i++)		{
			float x = random.nextFloat() * 100 - 50;
			float y = random.nextFloat() * 100 - 50;
			float z = random.nextFloat() * -300;
			allCubes.add(new Entity(staticModel, new Vector3f (x,y,z), random.nextFloat() *180f,
						random.nextFloat() *180f, 0f, 1f));
			
			
		}
		
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()){
			camera.move();
			
				for (Entity cube : allCubes){
					renderer.processEntity(cube);
				}
				renderer.render(light,  camera);
				DisplayManager.updateDisplay();	
		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
}
