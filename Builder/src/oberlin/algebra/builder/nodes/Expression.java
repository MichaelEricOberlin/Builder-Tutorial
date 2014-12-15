package oberlin.algebra.builder.nodes;

import java.util.*;
import java.util.regex.Pattern;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.pattern.ASTPattern;
import oberlin.builder.visitor.Visitor;

public class Expression extends NonTerminal {
	
	public Expression(SourcePosition position, List<AST> astList) throws MismatchException {
		super(position, astList);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	/*
	 * TODO: Set up clauses for ASTs, not direct references. There are many occasions during which
	 * a {1, 1} reference will be wholly inadequate.
	 * 
	 * (non-Javadoc)
	 * @see oberlin.builder.NonTerminal#getExpectedASTTypes()
	 */
	@Override
	public List<Class<? extends AST>> getExpectedASTTypes() {
		final List<Class<? extends AST>> expected = new ArrayList<>();
		expected.add(Identifier.class);
		expected.add(Operator.class);
		expected.add(Identifier.class);
		
		return expected;
	}

	@Override
	protected ASTPattern getASTPattern() {
		final AlgebraicPattern pattern = new AlgebraicPattern(Pattern.compile("IDENTIFIER OPERATOR IDENTIFIER"));
		return pattern;
	}

}
