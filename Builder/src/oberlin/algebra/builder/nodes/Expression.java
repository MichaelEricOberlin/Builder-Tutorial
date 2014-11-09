package oberlin.algebra.builder.nodes;

import java.util.List;

import oberlin.builder.NonTerminal;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

public class Expression extends NonTerminal {
	
	public Expression(List<AST> astList) {
		super(astList);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getElementCount() {
		// TODO Auto-generated method stub
		return 0;
	}

}
