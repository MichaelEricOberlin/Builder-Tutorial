package oberlin.algebra.builder.nodes;

import java.util.List;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public class Identifier extends NonTerminal {

	public Identifier(SourcePosition position, List<AST> astList) {
		super(position, astList);
	}
	
	public Identifier(SourcePosition position, AST...astList) throws MismatchException {
		super(position, astList);
	}

}
