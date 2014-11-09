package oberlin.builder.parser;

import java.util.logging.Level;
import java.util.logging.Logger;

import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

public class NullaryAST implements AST {
	
	Logger logger = Logger.getLogger("NullaryAST");
	
	@Override
	public void accept(Visitor visitor) {
		logger.log(Level.INFO, "NullaryAST received visitor " + visitor);
	}

	/**
	 * @return always 2, so that any sequence of ASTs can be reduced to a single NullaryAST. This
	 * prevents infinite loops.
	 */
	@Override
	public int getElementCount() {
		return 2;
	}

}
