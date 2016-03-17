package engineTester;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
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
		
		
		
		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		ModelTexture texture = new ModelTexture(loader.loadTexture("white"));
		TexturedModel staticModel = new TexturedModel(model,texture);
		
		Entity entity = new Entity(staticModel, new Vector3f(0,-5,-20),0,0,0,1); // xyz scale
		Light light = new Light(new Vector3f(0,0,-30),new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		
		double testSine = 10.0;
	
		while(!Display.isCloseRequested()){
			entity.increaseRotation(0, 0.1f, 0);
			entity.increasePosition(0, 0, 0);
			camera.move();
			renderer.prepare();
			shader.start();
			
			
			testSine+=0.00021;
			double lightpos = Math.sin(testSine*100)*10-20;
			light.setPosition(new Vector3f(0,0,(int)lightpos));
			System.out.println(lightpos);
			
			
			shader.loadLight(light);
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
