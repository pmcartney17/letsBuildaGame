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
import toolbox.Maths;

public class MainGameLoop {

	public static void main(String[] args) {
	
		DisplayManager.createDisplay();
		Loader loader = new Loader();

		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		
		TexturedModel staticModel = new TexturedModel(model, new ModelTexture(loader.loadTexture("white")));
	
		TexturedModel grass = new TexturedModel(OBJLoader.loadObjModel("grassob", loader),new ModelTexture(loader.loadTexture("grass")));
		grass.getTexture().setHasTransparency(true);
	   
		Entity entity = new Entity(staticModel, new Vector3f(0, 0, 0), 0, 0, 0, 1);
		Light light = new Light(new Vector3f(250, 250, 125), new Vector3f(1, 1, 1));
		
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
		
			
		Camera camera = new Camera();
		
		List<Entity> entities = new ArrayList<Entity>();
		Random random = new Random();
		
		for (int i = 0; i < 1; i++)	{
			entities.add(new Entity(staticModel, new Vector3f(0,0,0),0,0,0,1));
			 
		}

		Vector3f vPos = new Vector3f((random.nextFloat()-.5f)*800,0,(random.nextFloat()-.5f)*800);
		System.out.println(vPos);
		
	
		MasterRenderer renderer = new MasterRenderer();
		
		while(!Display.isCloseRequested()){
			for(Entity d:entities)
			{	
				d.setPosition(vPos);
				renderer.processEntity(d);
			}
			
			

			if(Maths.sign(vPos.x- entity.getPosition().x) == 0 && Maths.sign(vPos.z- entity.getPosition().z) == 0){
				vPos = new Vector3f((random.nextFloat()-.5f)*800,0,(random.nextFloat()-.5f)*800);
				
				System.out.println("changed " + vPos);
			}
			entity.increasePosition(Maths.sign(vPos.x- entity.getPosition().x)/2, 0, 0);
			entity.increasePosition(0, 0, Maths.sign(vPos.z- entity.getPosition().z)/2);
			System.out.println(entity.getPosition());
			
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
