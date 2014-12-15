package oberlin.algebra.builder.nodes;

import oberlin.builder.parser.SourcePosition;

public class LineComment extends Comment {

	public LineComment(String spelling, SourcePosition position) {
		super(position, spelling);
	}

}
