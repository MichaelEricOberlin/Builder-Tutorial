package oberlin.builder.encoder;

import oberlin.builder.AbstractSyntaxTree;
import oberlin.builder.BuilderException;

/**
 * Creates final output for a translator, from a properly formatted AbstractSyntaxTree. Final stage
 * of a compiler.
 * 
 * @author © Michael Eric Oberlin Oct 27, 2014
 *
 * @param <E> Material which source program is being compiled into.
 */
public interface CodeGenerator<E> {
	/**
	 * Constructs object program from properly formatted abstract syntax tree. Presumably, analysis has already
	 * been done on the source program to ensure its proper formatting.
	 * 
	 * @param ast Tree representing source
	 * @return complete object program
	 * @throws BuilderException Thrown only if something occurs which is indicative of an improperly formatted 
	 * source program; this can generally be avoided by running through a SyntacticAnalyzer and
	 * ContextualAnalyzer first (usually with a BulkAnalyzer).
	 */
	public E encode(AbstractSyntaxTree ast) throws BuilderException;
}
