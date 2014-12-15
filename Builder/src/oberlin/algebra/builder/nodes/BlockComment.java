package oberlin.algebra.builder.nodes;

import oberlin.builder.parser.SourcePosition;

public class BlockComment extends Comment {

	public BlockComment(String spelling, SourcePosition position) {
		super(position, spelling);
	}

}
