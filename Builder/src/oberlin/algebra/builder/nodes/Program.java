package oberlin.algebra.builder.nodes;

import java.util.List;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public class Program extends NonTerminal {

	//PRIVATE FIELDS
	
	public Program(SourcePosition position, List<AST> astList) throws MismatchException {
		super(position, astList);
	}
	
	public Program(SourcePosition position, AST...astList) throws MismatchException {
		super(position, astList);
	}

}
