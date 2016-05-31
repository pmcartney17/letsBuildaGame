package models;

import textures.ModelTexture;

public class RawModel {

	private int vaoID;
	private int vertexCount;
	
	public RawModel(int vaoID, int vertexCount){
		this.vaoID = vaoID;
		this.vertexCount = vertexCount;
	}
	
	public int getVaoID() {
		return vaoID;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}
	
	public void setVertexCount(int vertexCount) {
		this.vertexCount = vertexCount;
	}

	
	
	
	
}
