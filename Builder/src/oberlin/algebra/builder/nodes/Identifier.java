package oberlin.algebra.builder.nodes;

import java.util.List;

import oberlin.builder.MismatchException;
import oberlin.builder.NonTerminal;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public class Identifier extends NonTerminal {

	private boolean parenthetical = false;
	private boolean isNominal = false;
	
	private AST content;
	
	public Identifier(SourcePosition position, List<AST> astList) {
		super(position, astList);
		
		System.out.println(astList.size());
		initFields(astList.toArray(new AST[0]));
	}
	
	public Identifier(SourcePosition position, AST...astList) throws MismatchException {
		super(position, astList);
		
		initFields(astList);
	}
	
	private void initFields(AST...astList) {
		if(astList[0] instanceof LParen) {
			parenthetical = true;
			
			content = astList[1];
		} else {
			content = astList[0];
			
			if(content instanceof Nominal)
				isNominal = true;
		}
		
		System.out.println("isNominal: " + isNominal());
	}

	public boolean isParenthetical() {
		return parenthetical;
	}

	public boolean isNominal() {
		return isNominal;
	}

	public AST getContent() {
		return content;
	}

}
