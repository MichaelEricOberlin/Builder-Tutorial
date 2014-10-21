package oberlin.algebra.builder.parser;

import oberlin.builder.parser.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;

/**
 * AlgebraParser specific type expressing a numeric value
 * 
 * @author © Michael Eric Oberlin Oct 15, 2014
 *
 */
public class NumericTerm extends AbstractTerm<Double> {
	
	private Double data;
	private Pattern checkPattern = Pattern.compile("^\\d+\\.?\\d*$");
	
	@Override
	public Double getData() {
		return data;
	}

	@Override
	public boolean check(String token) {
		return checkPattern.matcher(token).find();
	}

	@Override
	protected Double derive(String token) throws MismatchException {
		Matcher matcher = checkPattern.matcher(token);
		matcher.find();
		return Double.valueOf(matcher.group());
	}

	@Override
	protected void setData(Double data) {
		this.data = data;
	}

}
