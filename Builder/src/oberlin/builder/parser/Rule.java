package oberlin.builder.parser;

import java.util.*;

public class Rule {
	//FIELDS
	
	//the sequence of types that qualify for this rule
	private List<Term> order = new ArrayList<>();
	
	//CONSTRUCTORS
	public Rule() {
		
	}
	
	public Rule(List<Term> order) {
		setOrder(order);
	}

	//GETTERS/SETTERS
	
	protected List<Term> getOrder() {
		return order;
	}

	protected void setOrder(List<Term> order) {
		this.order = order;
	}
	
	//INTRINSIC METHODS
	
	/**
	 * Checks a Term against the type at a specific position in the order
	 * @param index the index of the Term to check against
	 * @param subject the Term to attempt to match
	 * @return true if they match, false if they don't.
	 */
	public boolean check(int index, Term subject) {
		Term reference = order.get(index);
		
		return subject.getClass().isInstance(reference);
	}
	
	/*
	 * NOTE: Possibility: keep an internal private volatile reference to an index, and read from that index by default,
	 * until a call to reset.
	 */
}
