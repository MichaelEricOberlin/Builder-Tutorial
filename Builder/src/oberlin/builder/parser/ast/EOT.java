package oberlin.builder.parser.ast;

import oberlin.builder.parser.SourcePosition;
import oberlin.builder.visitor.Visitor;

/**
 * AST designating the end of a node list.
 * @author © Michael Eric Oberlin Dec 15, 2014
 *
 */
public final class EOT implements AST {

	public EOT() {
	}

	@Override
	public int getElementCount() {
		return 0;
	}

	@Override
	public SourcePosition getPosition() {
		return null;
	}


}
