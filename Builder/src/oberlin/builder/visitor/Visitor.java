package oberlin.builder.visitor;

import java.util.Map;
import java.util.function.BiFunction;

import oberlin.builder.parser.Parser2;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public interface Visitor {
	public default AST visit(Class<? extends AST> element, Parser2<?> parser, SourcePosition position) {
		AST ast = getHandlerMap().get(element).apply(parser, position);
		return ast;
	}

	public Map<Class<? extends AST>, BiFunction<Parser2<?>, SourcePosition, ? extends AST>> getHandlerMap();

	public default void addVisitHandler(Class<? extends AST> elementClass, BiFunction<Parser2<?>, SourcePosition, AST> handler) {
		getHandlerMap().put(elementClass, handler);
	}

	public default BiFunction<Parser2<?>, SourcePosition, ? extends AST> getVisitHandler(Class<? extends AST> elementClass) {
		return getHandlerMap().get(elementClass);
	}
}
