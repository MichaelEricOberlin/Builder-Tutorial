package oberlin.algebra.builder.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import oberlin.builder.*;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.pattern.ASTPattern;
import oberlin.builder.visitor.Visitor;

public class Equality extends NonTerminal {
	
	private final Operation leftOperation;
	private final Equator equator;
	private final Operation rightOperation;
	
	public Equality(SourcePosition position, AST...astList) {
		super(position, astList);
		
		leftOperation = (Operation)astList[0];
		equator = (Equator)astList[1];
		rightOperation = (Operation)astList[2];
	}
	
	public Equality(SourcePosition position, List<AST> astList) {
		super(position, astList);
		
		leftOperation = (Operation)astList.get(0);
		equator = (Equator)astList.get(1);
		rightOperation = (Operation)astList.get(2);
	}
	
	public List<Class<? extends AST>> getExpectedASTTypes() {
		List<Class<? extends AST>> expected = new ArrayList<>();
		expected.add(Identifier.class);
		expected.add(Equator.class);
		expected.add(Identifier.class);
		return expected;
	}

}
