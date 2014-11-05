package oberlin.builder.parser.ast;

import oberlin.builder.codegenerator.RuntimeEntity;
import oberlin.builder.visitor.*;

/**
 * Abstract Syntax Tree, capable of representing any sequence of statements or the entire program.
 * 
 * @author © Michael Eric Oberlin Nov 3, 2014
 *
 */
public abstract class AST implements Element {
	/**
	 * Indices of beginning and ending tokens in program.
	 */
	private RuntimeEntity entity;
	
	public AST() {
		entity = null;
	}
}
