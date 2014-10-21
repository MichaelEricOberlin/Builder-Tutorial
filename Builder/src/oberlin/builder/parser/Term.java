package oberlin.builder.parser;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

public interface Term<E> {
	//GETTERS/SETTERS	
	public E getData();

	//INTRINSIC METHODS
	
	/**
	 * Check to see if the provided token matches the strictures of this term type. Called on construction,
	 * not generally necessary to access from outside the class.
	 * 
	 * @param token the potential match
	 * @return true if the token matches the term; false otherwise.
	 */
	public boolean check(String token);
	
}
