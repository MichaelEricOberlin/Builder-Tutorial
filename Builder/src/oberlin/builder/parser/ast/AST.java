package oberlin.builder.parser.ast;

import java.util.List;

import oberlin.builder.codegenerator.RuntimeEntity;
import oberlin.builder.parser.Parser2;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.visitor.*;

/**
 * Abstract Syntax Tree, capable of representing any sequence of statements or the entire program.
 * 
 * @author © Michael Eric Oberlin Nov 3, 2014
 *
 */
public interface AST {
	/**
	 * @return number of sub-elements contained in this tree node.
	 */
	public int getElementCount();
	
	public SourcePosition getPosition();
	
	public default void accept(Visitor visitor, Parser2<?> parser, SourcePosition position) {
		visitor.visit(getClass(), parser, position);
	}

	public void printContainedNodes();
	
	public List<AST> getContainedNodes();
}
