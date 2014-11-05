package oberlin.builder.visitor;

import java.util.Map;

public interface Element {
	public void accept(Visitor visitor);
}
