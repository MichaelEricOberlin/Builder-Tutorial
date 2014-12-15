package oberlin.algebra.builder.nodes;

import oberlin.builder.Terminal;
import oberlin.builder.parser.SourcePosition;

public class Numeric extends Terminal {

	public Numeric(String spelling, SourcePosition position) {
		super(position, spelling);
	}

}
