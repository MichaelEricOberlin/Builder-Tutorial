package oberlin.builder.visitor;

import java.util.Map;
import java.util.function.BiFunction;

import oberlin.builder.parser.Parser2;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public interface Visitor {
	public default AST visit(Class<? extends AST> element, Parser2<?> parser, SourcePosition position) {
//		System.out.println("Map: " + getHandlerMap());
//		System.out.println("Element: " + element);
//		System.out.println("Parser: " + parser);
//		System.out.println("Position: " + position);
//		System.out.println("This class: " + this.getClass());
//		System.out.println("Fetch: " + getHandlerMap().get(element));
		AST ast = getHandlerMap().get(element).apply(parser, position);
		System.out.println("Result for '" + element + "': " + ast);
		System.out.println("Result type: " + ast.getClass());
		return ast;
	}

	// public Map<Class<? extends Element>, VisitHandler> getHandlerMap();
	public Map<Class<? extends AST>, BiFunction<Parser2<?>, SourcePosition, ? extends AST>> getHandlerMap();

	//Hesitant to suppress warning on unchecked; not sure why it feels that it's unchecked yet.
	public default void addVisitHanlder(Class<? extends AST> elementClass, VisitHandler<?> handler) {
		this.addVisitHandler(elementClass, (BiFunction<Parser2<?>, SourcePosition, AST>)handler);
	}
	
	public default void addVisitHandler(Class<? extends AST> elementClass, BiFunction<Parser2<?>, SourcePosition, AST> handler) {
		getHandlerMap().put(elementClass, handler);
	}

	public default BiFunction<Parser2<?>, SourcePosition, ? extends AST> getVisitHandler(Class<? extends AST> elementClass) {
		return getHandlerMap().get(elementClass);
	}
}
