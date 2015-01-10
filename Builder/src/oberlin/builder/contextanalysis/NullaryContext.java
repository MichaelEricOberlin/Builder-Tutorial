package oberlin.builder.contextanalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public class NullaryContext extends Context {

	private Map<Class<? extends AST>, BiFunction<AST, SourcePosition, ? extends Map<ContextProperty, ?>>> map = new HashMap<>();
	
	@Override
	public Map<Class<? extends AST>, BiFunction<AST, SourcePosition, ? extends Map<ContextProperty, ?>>> getHandlerMap() {
		return map;
	}

}
