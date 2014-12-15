package oberlin.algebra.builder.nodes;

import oberlin.builder.Terminal;
import oberlin.builder.parser.SourcePosition;

public class Nominal extends Terminal {

	public Nominal(String spelling, SourcePosition position) {
		super(position, spelling);
	}

}
