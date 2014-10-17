package oberlin.builder.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public abstract class Parser {
	
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
	
	List<List<Class<Term>>> orderOfOperations = new LinkedList<>();
	
	public Object parse(List<String> tokens) {
		/*
		 * TODO: Next up, establish items for program that describe the individual object, not
		 * just the type of object—likely if(type.check(token)) { program.add(new type.instanceClass(token)); }
		 */
		List<Term> program = new ArrayList<>();
		
		for(String token : tokens) {
			for(List<Class<Term>> opGroup : orderOfOperations) {
				boolean found = false;
				
				for(Class<Term> op : opGroup) {
					try {
						program.add(op.getConstructor(String.class).newInstance(token));
					} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | NoSuchMethodException
							| SecurityException e) {
						e.printStackTrace();
						continue;
					} catch(InvocationTargetException  e) {
						//this would be wrapping an UnparsableException, so move on tot he next candidate
						continue;
					}
				}
				
				if(found) break;
			}
		}
		
		return program;
	}

}
