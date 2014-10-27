package oberlin.builder.analysis;

import oberlin.builder.*;
import oberlin.builder.analysis.syntax.*;
import oberlin.builder.analysis.context.*;

/**
 * Handles syntactual analysis and contextual analysis of the code, in a structured fashion that may be
 * one-pass, multi-pass, or any variation.
 * 
 * @author © Michael Eric Oberlin Oct 27, 2014
 *
 */
public interface BulkAnalyzer {
	/**
	 * Runs an analysis on a provided AbstractSyntaxTree to ensure that it is well-formed. Returns true
	 * on completion, or throws a BuilderException on the detection of any malignancy in program format.
	 * By default, is multi-pass.
	 * 
	 * @param ast
	 * @throws BuilderException exception thrown by either the enclosed syntax analyzer, or the context analyzer,
	 * on detection of an exception in the source program.
	 */
	public default boolean analyze(AbstractSyntaxTree ast) throws BuilderException {
		syntaxAnalysis(ast);
		contextAnalysis(ast);
		
		return true;
	}
	
	public Object syntaxAnalysis(AbstractSyntaxTree ast) throws BuilderException;
	public Object contextAnalysis(AbstractSyntaxTree ast) throws BuilderException;
}
