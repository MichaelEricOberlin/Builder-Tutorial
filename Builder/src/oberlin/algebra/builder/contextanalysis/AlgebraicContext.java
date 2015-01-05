package oberlin.algebra.builder.contextanalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import oberlin.algebra.builder.nodes.Equality;
import oberlin.builder.contextanalysis.Context;
import oberlin.builder.contextanalysis.ContextAnalyzer;
import oberlin.builder.contextanalysis.ContextProperty;
import oberlin.builder.parser.Parser;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

public class AlgebraicContext extends Context {

	private Map<Class<? extends AST>, BiFunction<ContextAnalyzer<?>, SourcePosition, ? extends Map<ContextProperty, ?>>>
		map = new HashMap<>();
	{
		map.put(Equality.class, new BiFunction<ContextAnalyzer<?>, SourcePosition, Map<ContextProperty, ?>>(){

			@Override
			public Map<ContextProperty, ?> apply(ContextAnalyzer<?> t, SourcePosition u) {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
	}
	
	public AlgebraicContext() {
	}

	@Override
	public Map<Class<? extends AST>, BiFunction<ContextAnalyzer<?>, SourcePosition, ? extends Map<ContextProperty, ?>>> getHandlerMap() {
		return map;
	}

}
