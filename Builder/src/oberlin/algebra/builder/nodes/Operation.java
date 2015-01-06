package oberlin.algebra.builder.nodes;

import java.util.List;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public class Operation extends NonTerminal {
	
	private final Identifier leftIdentifier;
	private Operator operator;
	private Operation rightOperation;
	
	public Operation(SourcePosition position, AST... astList) throws MismatchException {
		super(position, astList);
		
		leftIdentifier = (Identifier)astList[0];
		if(astList.length > 1) {
			operator = (Operator)astList[1];
			rightOperation = (Operation)astList[2];
		} else {
			operator = null;
			rightOperation = null;
		}
/*		
		System.out.println(leftIdentifier.getClass().getSimpleName() + " " + 
				(operator != null ? operator.getClass().getSimpleName() : "null") + " " +
				(rightOperation != null ? rightOperation.getClass().getSimpleName() : null));*/
	}

	public Operation(SourcePosition position, List<AST> astList) throws MismatchException {
		super(position, astList);
		
		leftIdentifier = (Identifier)astList.get(0);
		if(astList.size() > 1) {
			operator = (Operator)astList.get(1);
			rightOperation = (Operation)astList.get(2);
		} else {
			operator = null;
			rightOperation = null;
		}
		
/*		System.out.println(leftIdentifier.getClass().getSimpleName() + " " + 
				(operator != null ? operator.getClass().getSimpleName() : "null") + " " +
				(rightOperation != null ? rightOperation.getClass().getSimpleName() : null));*/
	}

}
