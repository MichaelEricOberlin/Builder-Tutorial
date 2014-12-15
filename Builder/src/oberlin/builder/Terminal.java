package oberlin.builder;

import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.Position;
import oberlin.builder.visitor.Visitor;

/**
 * Basis of all complete abstract syntax trees. Terminals are basically isolated-tokens known only by their spellings.
 * 
 * @author © Michael Eric Oberlin Nov 5, 2014
 *
 */
public class Terminal implements AST {
	private final String spelling;
	private final SourcePosition position;
	
	public Terminal(String spelling, SourcePosition position) {
		this.spelling = spelling;
		this.position = position;
	}
	
	public final String getSpelling() {
		return this.spelling;
	}

//	@Override
//	public void accept(Visitor visitor) {
//		visitor.visit(this);
//	}

	@Override
	public String toString() {
		return getSpelling();
	}

	@Override
	public final int getElementCount() {
		return 1;
	}

	@Override
	public SourcePosition getPosition() {
		return position;
	}
}
