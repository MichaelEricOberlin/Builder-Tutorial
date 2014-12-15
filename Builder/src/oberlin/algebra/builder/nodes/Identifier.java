package oberlin.algebra.builder.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.Terminal;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.pattern.ASTPattern;
import oberlin.builder.visitor.Visitor;

public class Identifier extends NonTerminal {

	public Identifier(SourcePosition position, List<AST> astList) {
		super(position, astList);
	}
	
	public Identifier(SourcePosition position, AST...astList) throws MismatchException {
		super(position, astList);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Class<? extends AST>> getExpectedASTTypes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ASTPattern getASTPattern() {
		/**
		 * TODO: DEBUG: There is a problem here, as this will not work on its own. "NUMERIC|NOMINAL" is,
		 * for group zero matching, the equivalent of NUMERI(C|N)OMINAL; which is obviously not what I want.
		 * 
		 * To correct for this, we may need to jump into ASTExpression, and calculate the exact location of
		 * each of the parentheticals regarding the independent ASTs. 
		 */
//		final ASTPattern pattern = new AlgebraicPattern(Pattern.compile("(NUMERIC)|(NOMINAL)"));
		final ASTPattern pattern = new AlgebraicPattern(Pattern.compile("NUMERIC"));
		
//		System.out.println("ASTPattern Test: ");
//		List<AST> code = new ArrayList<>();
//		code.add(new Numeric("1"));
//		System.out.println(pattern.getPattern().pattern());
//		System.out.println("String: " + pattern.makeString(code));
//		System.out.println(pattern.match(code));
		
		return pattern;
	}

}
