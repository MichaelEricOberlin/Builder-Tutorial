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
	
	public Equality(SourcePosition position, AST...astList) {
		super(position, astList);
	}
	
	public Equality(SourcePosition position, List<AST> astList) {
		super(position, astList);
	}
	
	public List<Class<? extends AST>> getExpectedASTTypes() {
		List<Class<? extends AST>> expected = new ArrayList<>();
		expected.add(Identifier.class);
		expected.add(Equator.class);
		expected.add(Identifier.class);
		return expected;
	}

}
