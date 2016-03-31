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
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("white")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		
		Entity entity = new Entity(staticModel, new Vector3f(0,-5,-20),0,0,0,1); // xyz scale
		Light light = new Light(new Vector3f(200,200,100),new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		List<Entity> allCubes = new ArrayList<Entity>();
		Random random = new Random();
		
		MasterRenderer renderer = new MasterRenderer();
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0, 0.1f, 0);
			entity.increasePosition(0, 0, 0);
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
