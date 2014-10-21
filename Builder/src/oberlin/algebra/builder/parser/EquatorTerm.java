package oberlin.algebra.builder.parser;

import oberlin.algebra.builder.parser.OperatorTerm.Operator;
import oberlin.builder.parser.*;

import java.util.regex.*;

/**
 * AlgebraParser-specific Term representing "=", ">", "<", and so forth
 * 
 * @author © Michael Eric Oberlin Oct 15, 2014
 *
 */
public class EquatorTerm extends AbstractTerm<EquatorTerm.Equator> {
	enum Equator {
		EQUALS,
		NOT_EQUALS,
		GREATER_THAN,
		LESS_THAN,
		GREATER_OR_EQUALS,
		LESS_OR_EQUALS;
	}
	
	private Equator data;
	
	private Pattern checkPattern = Pattern.compile("^!?[=<>]$");
	
	@Override
	public Equator getData() {
		return data;
	}

	@Override
	protected void setData(Equator data) {
		this.data = data;
	}

	@Override
	public boolean check(String token) {
		return checkPattern.matcher(token).find();
	}

	@Override
	protected Equator derive(String token) throws MismatchException {
		Matcher matcher = checkPattern.matcher(token);
		matcher.find();
		String szEq = matcher.group();
		switch(szEq) {
		case "=":
		case "==":
			return Equator.EQUALS;
		case "!<":
		case ">=":
			return Equator.GREATER_OR_EQUALS;
		case "!>":
		case "<=":
			return Equator.LESS_OR_EQUALS;
		case "!<=":
		case ">":
			return Equator.GREATER_THAN;
		case "!>=":
		case "<":
			return Equator.LESS_THAN;
		case "!=":
			return Equator.NOT_EQUALS;
		default:
			throw new MismatchException("Equator " + szEq + " is not recognized");
		}
	}
	
	
}
