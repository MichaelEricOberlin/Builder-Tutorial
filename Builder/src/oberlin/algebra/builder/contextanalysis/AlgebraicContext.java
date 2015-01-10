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

	private Map<Class<? extends AST>, BiFunction<AST, SourcePosition, ? extends Map<ContextProperty, ?>>>
		map = new HashMap<>();
	{
		map.put(Equality.class, new BiFunction<AST, SourcePosition, Map<ContextProperty, ?>>(){

			@Override
			public Map<ContextProperty, ?> apply(AST ast, SourcePosition position) {
				/*
				 * Rule: Ensure that what is on the left hand side and what is on the right
				 * hand side follow the rule of the Equator; or, report an error.
				 */
				
				/*
				 * First, get a handle on the token
				 */
				
				
				// TODO Auto-generated method stub
				return null;
			}
			
		});
	}
	
	public AlgebraicContext() {
	}

	@Override
	public Map<Class<? extends AST>, BiFunction<AST, SourcePosition, ? extends Map<ContextProperty, ?>>> getHandlerMap() {
		return map;
	}

}
