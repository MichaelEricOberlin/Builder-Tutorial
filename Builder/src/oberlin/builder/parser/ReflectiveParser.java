package oberlin.builder.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import oberlin.builder.AbstractSyntaxTree;
import oberlin.builder.scanner.lexeme.Lexeme;

public abstract class ReflectiveParser implements Parser {
	
	/*
	 * Keep a collection of SemanticTypes, but remember that there must be an order-of-operation
	 * for them.
	 * 
	 * Two examples for why:
	 * 
	 * a + sin(x + 7) could be interpreted either as
	 * Symbol + Symbol * (Symbol + Number)
	 * or
	 * Symbol + Function(Symbol + Number)
	 * 
	 * It cannot be both.
	 * 
	 * List structure, perhaps?
	 * 
	 * List<List<Term>> ?
	 * 
	 * It's a start.
	 */
	
	/*
	 * Technically, parse returns an Abstract Parse Tree, or the equivalent of Swiss Notation for the method calls.
	 */
	@Override
	public AbstractSyntaxTree parse(List<Lexeme> lexemes) {
		/*
		 * TODO: Next up, establish items for program that describe the individual object, not
		 * just the type of object—likely if(type.check(token)) { program.add(new type.instanceClass(token)); }
		 */
		AbstractSyntaxTree program = buildSymbolList(lexemes);
		
		
		
		return program;
	}
	
	@Override
	public List<Term<?>> buildSymbolList(List<Lexeme> lexemes) {
		List<Term<?>> program = new ArrayList<>();
		
		for(Lexeme lex : lexemes) {
			/*
			 * NOTE: This can (and should) be done differently. You're thinking along the lines of the somewhat fixed
			 * algebraic order-of-ops. Terms are terminal and nonterminal; they provide information on the next
			 * available term type.
			 * 
			 * Go over not a list of lists, but a tree beginning with "Beginning of sentence", and stemming to the
			 * first entry of each term sequence; then step further out with each term until you reach the end
			 * of the sentence (greedy, of course). After that, return to "Beginning of sentence" and progress
			 * forward again, until you get to the end of the code tokens.
			 */
			for(List<Class<? extends Term<?>>> opGroup : this.getOrderOfOperations()) {
				boolean found = false;
				Logger.getLogger("Builder").log(Level.INFO, "opGroup: " + opGroup);
				
				//TODO: Issue: Class<? extends Term> always defaults to Term, instead of ?.
				for(Class<? extends Term<?>> op : opGroup) {
					try {
						program.add(op.getConstructor(String.class).newInstance(lex.toString()));
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException
							| SecurityException e) {
						e.printStackTrace();
						continue;
					} catch(InvocationTargetException  e) {
						//this would be wrapping a MismatchException, so move on to the next candidate
						continue;
					}
				}
				
				if(found) break;
			}
		}
		
		return program;
	}

	//GETTERS/SETTERS

}
