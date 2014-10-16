package oberlin.builder.parser;

import java.util.*;

public class Rule {
	//FIELDS
	
	//the sequence of types that qualify for this rule
	private List<SemanticType> order = new ArrayList<>();
	
	//CONSTRUCTORS
	public Rule() {
		
	}
	
	public Rule(List<SemanticType> order) {
		setOrder(order);
	}

	//GETTERS/SETTERS
	
	protected List<SemanticType> getOrder() {
		return order;
	}

	protected void setOrder(List<SemanticType> order) {
		this.order = order;
	}
	
	//INTRINSIC METHODS
	
	/**
	 * Checks a SemanticType against the type at a specific position in the order
	 * @param index the index of the SemanticType to check against
	 * @param subject the SemanticType to attempt to match
	 * @return true if they match, false if they don't.
	 */
	public boolean check(int index, SemanticType subject) {
		SemanticType reference = order.get(index);
		
		return subject.getClass().isInstance(reference);
	}
	
	/*
	 * NOTE: Possibility: keep an internal private volatile reference to an index, and read from that index by default,
	 * until a call to reset.
	 */
}
