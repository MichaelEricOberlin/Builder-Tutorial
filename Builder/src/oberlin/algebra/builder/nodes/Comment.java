package oberlin.algebra.builder.nodes;

import oberlin.builder.Terminal;
import oberlin.builder.parser.SourcePosition;

public abstract class Comment extends Terminal {

	public Comment(SourcePosition position, String spelling) {
		super(position, spelling);
	}

}
