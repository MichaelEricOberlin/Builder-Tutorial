package oberlin.builder;

/**
 * <p>Note that "abstract" in the title does not, in this instance, mean abstract as a class. An AST
 * is a tree structure produced from program code, which allows for syntactical and contextual
 * error checking and translation. Standard term.</p>
 * 
 * <p><em>Note: I am strongly tempted to make it abstract just for consistency.</em></p>
 * 
 * @author © Michael Eric Oberlin Oct 27, 2014
 *
 */
public class AbstractSyntaxTree {
	/**
	 * Respective token indices for the beginning and end of the application of the AST.
	 * 
	 * In other words, if the code file is 1,482 tokens long, but this AST only applies to a section
	 * of code spanning from token 438 to 642, then positionStart = 438; and positionEnd = 642.
	 */
	private final int positionStart, positionEnd;
	
	public AbstractSyntaxTree(int start, int end) {
		//initialize bounds for AST
		this.positionStart = start;
		this.positionEnd = end;
	}

}
