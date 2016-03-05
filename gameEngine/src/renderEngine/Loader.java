package renderEngine;


import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class Loader {
 private ArrayList<Integer> vaos = new ArrayList<Integer>();
 private ArrayList<Integer> vbos = new ArrayList<Integer>();
 

	public RawModel loadToVAO(float[] postions, int[] indices){
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttribueList(0,postions);
		unbindVAO();
		return new RawModel(vaoID,postions.length / 3, 0);
	}
	
	private int createVAO(){
		
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	public void cleanUp(){
	for(int vao:vaos){
		GL30.glDeleteVertexArrays(vao);
		
	}	
		for(int vbo:vbos){
			
		GL15.glDeleteBuffers(vbo);	
		}
	}
	private void storeDataInAttribueList(int attributeNumber, float[]data){
		
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attributeNumber,3,GL11.GL_FLOAT,false,0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
	}
	
	private void unbindVAO(){
		
	GL30.glBindVertexArray(0);	
		
	
	}
	private void bindIndicesBuffer(int[] indices){
	int vboID = GL15.glGenBuffers();
	vbos.add(vboID);
	GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
	IntBuffer buffer = storeDataInIntBuffer(indices);
	GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	private IntBuffer storeDataInIntBuffer(int[] data){
	IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
	buffer.put(data);
	buffer.flip();
	return buffer;
	}
	private FloatBuffer storeDataInFloatBuffer(float[]data){
		
	FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
	buffer.put(data);
	buffer.flip();
	return buffer;
	}
	
	
	
	
}
