package oberlin.builder.scanner;

import java.util.*;
import java.util.function.*;
import java.util.logging.*;

import oberlin.builder.*;

/**
 * New design for scanners. Requires an enumeration of type Grammar to iterate over, and a code string.
 * Returns a list of tokens.
 * 
 * @author © Michael Eric Oberlin Nov 2, 2014
 *
 */
public interface Scanner<E extends Enum<E> & Grammar> extends Function<String, List<String>> {
	@Override
	public default List<String> apply(String code) {
		Logger logger = Logger.getLogger("Scanner");
		
		//Start with a list of tokens, with the singular pre-token of the bulk of code
		List<String> tokens = new LinkedList<>();
		tokens.add(code);
		
		//For each item found in the code, parse it, and replace the contents of tokens with the parsed contents
		for(int index = 0; index < tokens.size(); index++) {
			//maintain for check
			String origToken = tokens.get(index);
			
			List<String> newTokens = new ArrayList<>();
			for(Grammar grammar : getGrammar().getEnumConstants()) {
				
				try {
					newTokens.addAll(grammar.matchToken(tokens.get(index)));
					
					replaceItemWithCollection(tokens, index, newTokens);
					break;
				} catch (MismatchException e) {
					//didn't match, so continue to the next item
					continue;
				}
			}
			
			//Final defensive check: if one token was received, and one token provided, and
			//the old is not the same as the new, then the only thing that happened was the
			//removal of irrelevant data. Thus, index should be decremented so that this new
			//item may be scanned again.
			if(newTokens.size() == 1 && !newTokens.get(0).equals(origToken)) index--;
		}
		
		//This algorithm will always terminate the token list with an empty token, the one item
		//which cannot have semantic value. So, remove it here.
		tokens.remove(tokens.size() - 1);
		
		return tokens;
	}
	
	/**
	 * Internally used method which replaces an entry in a provided list with a collection.
	 * 
	 * @param list list to be altered
	 * @param entry numeric index of replaced entry
	 * @param replacement item to insert in place of current data
	 */
	static <E> void replaceItemWithCollection(List<E> list, int entry, Collection<E> replacement) {
		list.remove(entry);
		list.addAll(entry, replacement);
	}
	
	/**
	 * 
	 * @return Grammar allocated to token recognition.
	 */
	public Class<E> getGrammar();
}
