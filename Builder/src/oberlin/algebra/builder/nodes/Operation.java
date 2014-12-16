package oberlin.algebra.builder.nodes;

import java.util.ArrayList;
import java.util.List;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.pattern.ASTPattern;

public class Operation extends NonTerminal {

	private static final List<Class<? extends AST>> typeList = new ArrayList<>();
	{
		typeList.add(Identifier.class);
		typeList.add(Operator.class);
		typeList.add(Identifier.class);
	}
	
	public Operation(SourcePosition position, AST... astList) throws MismatchException {
		super(position, astList);
	}

	public Operation(SourcePosition position, List<AST> astList) throws MismatchException {
		super(position, astList);
	}

	@Override
	public List<Class<? extends AST>> getExpectedASTTypes() {
		return typeList;
	}

	@Override
	protected ASTPattern getASTPattern() {
		throw new UnsupportedOperationException("getASTPattern is not yet implemented for Operation");
	}

}
