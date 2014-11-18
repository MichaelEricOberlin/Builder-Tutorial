package oberlin.algebra.builder.nodes;

import java.util.ArrayList;
import java.util.List;

import oberlin.builder.*;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

public class Equality extends NonTerminal {
	
//	final Expression expression1, expression2;
//	final Equator equator;
	
	
	public Equality(AST...astList) {
		super(astList);
	}
	
	public Equality(List<AST> astList) {
		super(astList);
	}
	
	public List<Class<? extends AST>> getExpectedASTTypes() {
		List<Class<? extends AST>> expected = new ArrayList<>();
		expected.add(Expression.class);
		expected.add(Equator.class);
		expected.add(Expression.class);
		return expected;
	}
	
	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

}
