package oberlin.algebra.builder.nodes;

import oberlin.builder.Terminal;
import oberlin.builder.parser.SourcePosition;

public class Whitespace extends Terminal {

	public Whitespace(SourcePosition position, String spelling) {
		super(position, spelling);
	}

}
