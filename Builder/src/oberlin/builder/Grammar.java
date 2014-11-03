package oberlin.builder;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.*;

public interface Grammar {
	
	public default List<String> matchToken(String sz) throws MismatchException {
		List<String> returnable = new LinkedList<>();
		
		Pattern pattern = getPattern();
		Matcher matcher = pattern.matcher(sz);
		if(matcher.find()) {
			returnable.addAll(manageToken(matcher));
			returnable.add(sz.substring(matcher.end()));
			return returnable;
		} else throw new MismatchException("String \"" + sz + "\" does not match grammar pattern + \"" + pattern + "\"");
	}
	
	public Pattern getPattern();
	
	/**
	 * Determines, often through enumeration self-inspection, what should be done with a passed token.
	 * 
	 * @param token the original token removed from the complete String by matchToken
	 * @return appropriate value given the circumstances and implementation of Grammar.
	 */
	public List<String> manageToken(Matcher matcher);
}
