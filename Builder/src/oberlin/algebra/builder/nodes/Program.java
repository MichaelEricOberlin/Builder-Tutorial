package oberlin.algebra.builder.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.pattern.ASTPattern;
import oberlin.builder.visitor.Visitor;

public class Program extends NonTerminal {

	//PRIVATE FIELDS
	
	public Program(SourcePosition position, List<AST> astList) throws MismatchException {
		super(position, astList);
	}
	
	public Program(SourcePosition position, AST...astList) throws MismatchException {
		super(position, astList);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Class<? extends AST>> getExpectedASTTypes() {
		List<Class<? extends AST>> types = new ArrayList<>();
		types.add(Equality.class);
		return types;
	}

	@Override
	protected ASTPattern getASTPattern() {
		final ASTPattern pattern = new AlgebraicPattern(Pattern.compile("EQUALITY")); 
		return pattern;
	}

}
