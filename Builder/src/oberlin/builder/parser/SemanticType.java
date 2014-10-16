package oberlin.builder.parser;

import java.util.regex.*;

public abstract class SemanticType {
	Pattern regex;
	
	//GETTERS/SETTERS	
	protected Pattern getRegex() {
		return regex;
	}

	protected void setRegex(Pattern regex) {
		this.regex = regex;
	}
	
	//INTRINSIC METHODS
	/**
	 * Determines whether or not the provided string is an example of this type
	 * 
	 * @param sz string to be tested
	 * @return true if a match, false otherwise
	 */
	public boolean check(String sz) {
		Matcher matcher = regex.matcher(sz);
		
		if(matcher.find()) {
			return true;
		}
		
		return false;
	}
}
