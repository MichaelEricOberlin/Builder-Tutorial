package oberlin.algebra.builder.nodes;

import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

public class Equality implements AST {
	
	final Expression expression1, expression2;
	final Equator equator;
	
	public Equality(Expression expression1, Equator equator, Expression expression2) {
		this.expression1 = expression1;
		this.equator = equator;
		this.expression2 = expression2;
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getElementCount() {
		//expression1, equalsOperator, expression2
		return 3;
	}

}
