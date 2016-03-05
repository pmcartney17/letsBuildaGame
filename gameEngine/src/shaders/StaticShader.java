package shaders;

public class StaticShader extends ShaderProgram {

	
	private static final String VERTEX_FILE = "src/shaders/vertexShader.txt";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.txt";
	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	
	}

	@Override
	protected void bindAttributes() {
		// TODO Auto-generated method stub
		super.bindAttribute(0, "positions");
	}

	
	
	
	
}
