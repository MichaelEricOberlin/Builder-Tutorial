package oberlin.algebra.builder.nodes;

import java.util.ArrayList;
import java.util.List;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

public class Program extends NonTerminal {

	//PRIVATE FIELDS
	
	public Program(List<AST> astList) throws MismatchException {
		super(astList);
	}
	
	public Program(AST...astList) throws MismatchException {
		super(astList);
	}

	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Class<? extends AST>> getExpectedASTTypes() {
		List<Class<? extends AST>> types = new ArrayList<>();
		types.add(Equality.class);
		System.out.println("types: " + types);
		return types;
	}

}
