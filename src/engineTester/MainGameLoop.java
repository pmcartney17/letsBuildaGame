package engineTester;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class MainGameLoop {

	public static void main(String[] args) {
	
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
	
		float[] vertices = { -0.5f, 0.5f, 0f, 
							-0.5f, -0.5f, 0f, 
							0.5f, -0.5f, 0f, 
							//0.5f, -0.5f, 0f, 
							//0.5f, 0.5f, 0f, 
							//-0.5f, 0.5f, 0f
							};
		//int[] indices = {0,1,3,3,1,2};
		
		RawModel model = loader.loadToVAO(vertices);//, indices);
	
		while(!Display.isCloseRequested()){
			//game logic
			//render
			renderer.prepare();
			//shader.start();
			renderer.render(model);	
			//renderer.prepare();
			//shader.stop();
			DisplayManager.updateDisplay();	
		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
		
	}
}
