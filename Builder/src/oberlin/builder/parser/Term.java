package oberlin.builder.parser;

import java.util.regex.*;

public abstract class Term {
	//FIELDS
	private Pattern regex;
	private Object data;
	
	//CONSTRUCTORS
	protected Term() {
		//Initialize regex here
	}
	
	public Term(String token) throws UnparsableException {
		this();
		getData(token);
	}
	
	//GETTERS/SETTERS	
	protected Pattern getRegex() {
		return regex;
	}

	protected void setRegex(Pattern regex) {
		this.regex = regex;
	}
	
	protected Object getData() {
		return data;
	}

	protected void setData(Object data) {
		this.data = data;
	}

	//INTRINSIC METHODS
	/**
	 * Determines whether the provided token is parsable as this type 
	 * @param sz code token in question
	 * @return true if it can be parsed, false otherwise
	 */
	private boolean matches(String sz) {
		return regex.matcher(sz).find();
	}
	
	/**
	 * Retrieves encoded data from the provided token and encapsulates it in this class.
	 * @param sz
	 * @return true if successful
	 * 
	 * @throws UnparsableException the provided token cannot be parsed as this data type
	 */
	protected abstract boolean getData(String sz) throws UnparsableException;
	
}
