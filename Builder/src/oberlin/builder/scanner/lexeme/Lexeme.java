package oberlin.builder.scanner.lexeme;

import java.util.regex.*;

public interface Lexeme {
	/**
	 * @return a java.util.regex.Pattern containing the regular expression representing members of this lexeme 
	 */
	public Pattern getPattern();
	
	/**
	 * Determines whether the provided token is a representation of this lexeme
	 * @param token questioned token
	 * @return true if it properly represents this lexeme, false otherwise
	 */
	public default boolean matches(String token) {
		return getPattern().matcher(token).find();
	}
	
	/**
	 * Determines whether the provided token is a representation of the provided lexeme
	 * @param lexeme the lexeme under scrutiny
	 * @param token the token in question
	 * @return true if token represents lexeme, false otherwise
	 */
	public static boolean matches(Lexeme lexeme, String token) {
		Matcher matcher = lexeme.getPattern().matcher(token);
		return matcher.find();
	}
	
	/**
	 * @return the number of characters of the match
	 */
	public int getCharacterCount();
}
