package oberlin.builder.visitor;

import java.util.Map;
import java.util.function.BiFunction;

import oberlin.builder.parser.Parser;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public interface Visitor<E, F> {
	public default F visit(Class<? extends AST> element, E e, SourcePosition position) {
		F f = getHandlerMap().get(element).apply(e, position);
		return f;
	}

	public Map<Class<? extends AST>, BiFunction<E, SourcePosition, ? extends F>> getHandlerMap();

	public default void addVisitHandler(Class<? extends AST> elementClass, BiFunction<E, SourcePosition, F> handler) {
		getHandlerMap().put(elementClass, handler);
	}

	public default BiFunction<E, SourcePosition, ? extends F> getVisitHandler(Class<? extends F> elementClass) {
		return getHandlerMap().get(elementClass);
	}
}
