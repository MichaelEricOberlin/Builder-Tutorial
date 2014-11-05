package oberlin.builder.visitor;

import java.util.Map;

public interface Visitor {
	public default void visit(Element element) {
		getHandlerMap().get(element.getClass()).handle(element);
	}
	
	public Map<Class<? extends Element>, VisitHandler> getHandlerMap();
	
	public default void addVisitHandler(Class<? extends Element> elementClass, VisitHandler handler) {
		getHandlerMap().put(elementClass, handler);
	}
	
	public default VisitHandler getVisitHandler(Class<? extends Element> elementClass) {
		return getHandlerMap().get(elementClass);
	}
}
