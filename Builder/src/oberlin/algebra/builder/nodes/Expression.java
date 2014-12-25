package oberlin.algebra.builder.nodes;

import java.util.*;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public class Expression extends NonTerminal {
	
	public Expression(SourcePosition position, List<AST> astList) throws MismatchException {
		super(position, astList);
	}

}
