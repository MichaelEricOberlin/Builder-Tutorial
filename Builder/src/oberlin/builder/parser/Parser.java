package oberlin.builder.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import oberlin.builder.AbstractSyntaxTree;
import oberlin.builder.scanner.lexeme.Lexeme;

public interface Parser {
	public AbstractSyntaxTree parse(List<String> tokens);
	
	public default List<Symbol> buildSymbolList(List<String> tokens) {
		final Logger logger = Logger.getLogger("Parser " + this.getClass());
		
		List<Symbol> symbols = new ArrayList<>();
		
		for(String token : tokens) {
			Symbol symbol;
			
			for(Class<? extends Symbol> cls : this.getSymbolClasses()) {
				try {
					symbol = cls.getConstructor(String.class).newInstance(token);
				} catch(InvocationTargetException ex) {
					if(ex.getCause() instanceof MismatchException)
						continue;
					
					//else, if something else mucked up…
					logger.log(Level.WARNING, ex.getCause().getMessage(), ex.getCause());
					
					//and trudge ahead!
					continue;
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| NoSuchMethodException | SecurityException ex) {
					logger.log(Level.WARNING, ex.getMessage(), ex);
					//and trudge ahead!
					continue;
				}
				
				//If we've gotten this far, it must have been an acceptable match.
				//So, add it to the list.
				symbols.add(symbol);
				break;
			}
		}
		
		return symbols;
	}
	
	public List<Class<? extends Symbol>> getSymbolClasses();
}
