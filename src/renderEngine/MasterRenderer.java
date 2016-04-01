package renderEngine;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;



import models.TexturedModel;
import entities.Entity;
import entities.Camera;
import entities.Light;
import shaders.StaticShader;

public class MasterRenderer {
	
	private StaticShader shader= new StaticShader();
	private Renderer renderer = new Renderer(shader);
	
	private Map<TexturedModel, List<Entity>> entities = new HashMap<TexturedModel, List<Entity>>();

	public void render(Light sun, Camera camera){
		renderer.prepare();
		shader.start();
		shader.loadLight(sun);
		shader.loadviewMatrix(camera);
		renderer.render(entities);
		shader.stop();
		entities.clear();
		
	}
	
	public void processEntity(Entity entity){
		TexturedModel entityModel = entity.getModel();
		List<Entity> batch = entities.get(entityModel);
		if(batch!=null){
			batch.add(entity);
		}else{
			List<Entity> newBatch = new ArrayList<Entity>();
			newBatch.add(entity);
			entities.put(entityModel, newBatch);
		}
	}
	
	public void cleanUp(){
		shader.cleanUp();
	}
	
}
