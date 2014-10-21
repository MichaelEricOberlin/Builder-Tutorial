package oberlin.algebra.builder.parser;

import oberlin.builder.parser.*;

import java.util.regex.*;

/**
 * AlgebraParser specific alphabetic type, meant for such items as "x" and "y"
 * 
 * @author © Michael Eric Oberlin Oct 15, 2014
 *
 */
public class AlphabeticTerm extends AbstractTerm<String> {
	private String data;
	//accept anything that starts with a letter followed by zero or more alphanumerics
	private final Pattern checkPattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9]*$"); 
	
	public AlphabeticTerm(String token) throws MismatchException {
		super(token);
	}

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public boolean check(String token) {
		Matcher matcher = checkPattern.matcher(token);
		return matcher.find();
	}

	@Override
	protected String derive(String token) throws MismatchException {
		Matcher matcher = checkPattern.matcher(token);
		matcher.find();
		return matcher.group();
	}

}
