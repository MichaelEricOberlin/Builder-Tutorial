package oberlin.algebra.builder.parser;

import oberlin.builder.parser.Term;
import oberlin.builder.parser.UnparsableException;

import java.util.*;
import java.util.regex.*;

/**
 * AlgebraParser specific type expressing a numeric value
 * 
 * @author © Michael Eric Oberlin Oct 15, 2014
 *
 */
class NumericTerm extends Term {

	protected NumericTerm() {
		this.setRegex(Pattern.compile("^\\d+$"));
	}
	
	@Override
	protected boolean getData(String sz) throws UnparsableException {
		Matcher matcher = this.getRegex().matcher(sz);
		
		if(matcher.find()) {
			this.setData(Integer.parseInt(sz));
		} else throw new UnparsableException();
		return false;
	}

}
