package oberlin.builder.visitor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import oberlin.builder.parser.Parser;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public class NullaryVisitor implements Visitor {
	
	private Map<Class<? extends AST>, BiFunction<Parser<?>, SourcePosition, ? extends AST>> map =
			new HashMap<>();
	
	public NullaryVisitor() {
	}

	@Override
	public Map<Class<? extends AST>, BiFunction<Parser<?>, SourcePosition, ? extends AST>> getHandlerMap() {
		return map;
	}

}
