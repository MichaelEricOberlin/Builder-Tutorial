package oberlin.algebra.builder.nodes;

import java.util.ArrayList;
import java.util.List;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

public class Expression extends NonTerminal {
	
	public Expression(List<AST> astList) throws MismatchException {
		super(astList);
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
		
		/*
		 * TODO: Fill in expected
		 */
		
		return expected;
	}

}
