package oberlin.builder.codegenerator;

/**
 * General runtime data object.
 *   
 * @author © Michael Eric Oberlin Nov 3, 2014
 *
 */
public abstract class RuntimeEntity {
	private int size;
	
	public RuntimeEntity() {
		this.setSize(0);
	}

	protected int getSize() {
		return size;
	}

	protected void setSize(int size) {
		this.size = size;
	}
	
}
