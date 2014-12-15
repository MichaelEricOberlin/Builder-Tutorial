package oberlin.builder.parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

public class NullaryAST implements AST {
	
	private SourcePosition position;
	
	public NullaryAST(SourcePosition position) {
		this.position = position;
	}
	
	private Logger logger = Logger.getLogger("NullaryAST");
	
	/**
	 * @return always 2, so that any sequence of ASTs can be reduced to a single NullaryAST. This
	 * prevents infinite loops.
	 */
	@Override
	public int getElementCount() {
		return 2;
	}

	@Override
	public SourcePosition getPosition() {
		return this.position;
	}

}
