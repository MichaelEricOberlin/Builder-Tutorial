package oberlin.builder;

/**
 * Representation of the semantic behind any lexical item in the program. The notion of "tree" comes from
 * internal references to other types of ASTs; as an example, "x + 1" might represent an "expression", but
 * within it, it will have a reference to not only a required operator but an AST for the variable "x" and
 * for the terminal integer literal "1".
 */
public abstract class AbstractSyntaxTree {
	/**
	 * Respective token indices for the beginning and end of the application of the AST.
	 * 
	 * In other words, if the code file is 1,482 tokens long, but this AST only applies to a section
	 * of code spanning from token 438 to 642, then positionStart = 438; and positionEnd = 642.
	 */
	private final Position position;
	
	public AbstractSyntaxTree(Position position) {
		//initialize bounds for AST
		this.position = position;
	}

}
