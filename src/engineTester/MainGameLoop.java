package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

public class MainGameLoop {

	public static void main(String[] args) {
	
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		StaticShader shader = new StaticShader();
		Renderer renderer = new Renderer(shader);
		
		
		
		RawModel model = OBJLoader.loadObjModel("House", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("HouseTex"));
		TexturedModel staticModel = new TexturedModel(model,texture);
		Entity entity = new Entity(staticModel, new Vector3f(0,-5,-20),0,0,0,1);
		Camera camera = new Camera();
	
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0, 0.1f, 0);
			entity.increasePosition(0, 0, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			shader.loadviewMatrix(camera);
			renderer.render(entity,shader);
			shader.stop();
			DisplayManager.updateDisplay();	
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
}
