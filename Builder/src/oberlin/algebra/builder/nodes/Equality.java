package oberlin.algebra.builder.nodes;

import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

public class Equality implements AST {

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
