package oberlin.builder.parser;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
	
	List<List<Class<? extends Term<?>>>> orderOfOperations = new LinkedList<>();
	
	/*
	 * Technically, parse returns an Abstract Parse Tree, or the equivalent of Swiss Notation for the method calls.
	 */
	public Object parse(List<String> tokens) {
		/*
		 * TODO: Next up, establish items for program that describe the individual object, not
		 * just the type of object—likely if(type.check(token)) { program.add(new type.instanceClass(token)); }
		 */
		List<Term<?>> program = new ArrayList<>();
		
		for(String token : tokens) {
			for(List<Class<? extends Term<?>>> opGroup : this.getOrderOfOperations()) {
				boolean found = false;
				Logger.getLogger("Builder").log(Level.INFO, "opGroup: " + opGroup);
				
				//TODO: Issue: Class<? extends Term> always defaults to Term, instead of ?.
				for(Class<? extends Term<?>> op : opGroup) {
					try {
						program.add(op.getConstructor(String.class).newInstance(token));
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
	
	protected List<List<Class<? extends Term<?>>>> getOrderOfOperations() {
		return orderOfOperations;
	}

	protected void setOrderOfOperations(List<List<Class<? extends Term<?>>>> orderOfOperations) {
		this.orderOfOperations = orderOfOperations;
	}
	
	protected void addOperationList(int order, List<Class<? extends Term<?>>> operationList) {
		this.getOrderOfOperations().add(order, operationList);
	}

}
