package oberlin.algebra.builder.nodes;

import oberlin.builder.Terminal;
import oberlin.builder.parser.SourcePosition;

public class Operator extends Terminal {

	public Operator(String spelling, SourcePosition position) {
		super(position, spelling);
	}

}
