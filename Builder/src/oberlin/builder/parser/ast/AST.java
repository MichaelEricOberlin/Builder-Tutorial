package oberlin.builder.parser.ast;

import oberlin.builder.codegenerator.RuntimeEntity;
import oberlin.builder.visitor.*;

/**
 * Abstract Syntax Tree, capable of representing any sequence of statements or the entire program.
 * 
 * @author © Michael Eric Oberlin Nov 3, 2014
 *
 */
public interface AST extends Element {
	/**
	 * @return number of sub-elements contained in this tree node.
	 */
	public int getElementCount();
}
