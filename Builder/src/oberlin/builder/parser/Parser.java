package oberlin.builder.parser;

import java.util.*;
import java.util.function.*;
import java.util.logging.*;

import oberlin.builder.*;
import oberlin.builder.parser.ast.*;

public interface Parser<E extends Enum<E> & PhraseStructure> extends Function<List<AST>, AST> {

	/**
	 * Note that the given list must not be immutable; in its reduction to a single AST (for
	 * a properly formatted program), Parser modifies and reduces the contents of code until
	 * only a single value remains.
	 */
	public default AST apply(List<AST> code) {
		Logger logger = Logger.getLogger("Parser");
		
		while(code.size() != 1) {	//While we still have more than one tree to merge...
			boolean match = false;
			
			AST node = null;
			for(PhraseStructure structure : getPhraseStructure().getEnumConstants()) {
				try {
					node = structure.match(code);
					
					match = true;
					break;	//Ensures that an exception is not thrown at an inappropriate time.
				} catch (MismatchException e) {
					// Wrong one, so try the next one or break and throw an exception
					continue;
				}
			}
			
			if(!match) {
				//TODO: Report error
				
				break;
			}
			
			for(int replaceIndex = 0; replaceIndex < node.getElementCount(); replaceIndex++) {
				code.remove(0);	//Remove the first terminal
			}
			code.add(0, node);
			
		}
		
		return code.get(0);
	}

	/**
	 * @return List of all grammatical rules for the compiled language. That is,
	 *         every non-terminal construct that syntactically qualifies.
	 */
	/*
	 * Initially, I was thinking about expressing each A := B... expression as a
	 * Rule. However, this may lead to redundancy. Scanner already scans for
	 * terminal expressions using a class called TerminalSpelling. Is it incorrect for
	 * Parser to use a complementary grammar class?
	 * 
	 * I think so. I think it's a serious problem.
	 * 
	 * <strike>Proposal (mull it over)</strike>: Each AST, on the call of
	 * toString(), returns its literal contents. However, its Pattern can be
	 * manufactured from each of it's tokens' types' getPattern(), as
	 * getPattern() is inherited from TerminalSpelling. Thus, getPattern() can run
	 * recursively through multiple source objects, and self-generate.
	 * 
	 * New Proposal: Use a visitor pattern. Visit the List<Terminal> sequence,
	 * and attempt to match the context from the left to the right, continuing
	 * with each token until its semantics can't be expanded any deeper.
	 * 
	 * Example: y;=;x; +; 1; y → terminal → identifier → fieldName = → equator x
	 * → terminal → identifier → fieldName + → terminal → operator 1 → terminal
	 * → identifier → literalNumber Now, we have reached the end of the
	 * statement, but we have not yet compiled it into a single AST. Reset to
	 * the beginning. start with y → fieldName What starts with fieldName? What
	 * follows fieldName in these circumstances? check next token for match
	 * assignment := fieldName equator expression iff assignment, then next
	 * token should be equator next token is an equator, so all other
	 * possibilities are removed from the stack next token must be an expression
	 * currently, it is a fieldName can fieldName be an expression? fieldName
	 * instanceof identifier Yes, expression := identifier (operator
	 * identifier)* Given that fieldName is identifier, be greedy. Does an
	 * operator follow it? Yes, "+" is an operator. Does an identifier follow
	 * it? Yes, "1" is an identifier. fieldName completes as an expression y
	 * completes as an assignment
	 * 
	 * This can probably be done with the streaming API.
	 * 
	 * So what is a rule? We have a basic regular expression describing it. I
	 * would like to avoid, in this instance, writing a compiler/interpreter for
	 * the sake of my compiler/interpreter. If I did, I might use something
	 * like: result → AST Type costruction → Clause[] Clause → AST Type[],
	 * enum(ZERO_OR_MORE, ONE_OR_MORE, ...) Or maybe, just use an unused but
	 * recognized unicode character before the name of a token type, like "🃟"
	 * ("white joker"). Add an escape sequence (like "\🃟") for good form. So
	 * Identifier would be marked as "🃟Identifier". Expression rule clause
	 * becomes "🃟expression\s*(🃟operator\s*🃟identifier)" (escaped for Java of
	 * course) on encounter of regex matching "([^\\\\]?(🃟(\\w+)))", group 3 is
	 * type name.
	 * 
	 * Get type name, look up its class in an enumeration. -Try constructing and
	 * catch a MismatchException -Avoids "instanceof", more in OOP canon. -on
	 * Mismatch, progress to next candidate
	 * 
	 * I still don't like this. There's a major redundancy; AST already knows
	 * what its constituents are. How about this: Enumerate through all ASTs.
	 * For each AST, attempt to construct with List<AST>. If Mismatch thrown,
	 * continue. If not, replace its constituents with the new tree.
	 */

	public Class<E> getPhraseStructure();
}
