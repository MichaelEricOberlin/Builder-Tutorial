package oberlin.builder.visitor;

import java.util.Map;

public interface Element {
	//Because seriously, why shouldn't this be a default method? 90% of the time, this is
	//all it will do.
	public default void accept(Visitor visitor) {
		visitor.visit(this);
	}
}
