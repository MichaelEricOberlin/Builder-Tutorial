package oberlin.algebra.builder.parser;

import oberlin.builder.parser.*;

import java.util.regex.*;

/**
 * AlgebraParser specific type meant for operators such as +, -, *, and /.
 * 
 * @author © Michael Eric Oberlin Oct 15, 2014
 *
 */
public class OperatorTerm extends AbstractTerm<OperatorTerm.Operator> {
	//ENUMERATIONS
	enum Operator {
		ADD,
		SUBTRACT,	//Technically a form of ADD, but here for simplicity
		SCALAR_PRODUCT,
		DIVIDE;	//Technically a form of SCALAR_PRODUCT, but here for simplicity
	}
	
	public OperatorTerm(String token) throws MismatchException {
		super(token);
	}
	
	//FIELDS
	private Operator data;
	
	//PRIVATE FIELDS
	private final Pattern checkPattern = Pattern.compile("^[\\+\\-\\*\\/\\\\]$");
	
	//GETTERS/SETTERS
	@Override
	public Operator getData() {
		return data;
	}

	@Override
	protected void setData(Operator data) {
		this.data = data;
	}

	@Override
	public boolean check(String token) {
		return checkPattern.matcher(token).find();
	}

	@Override
	protected Operator derive(String token) throws MismatchException {
		Matcher matcher = checkPattern.matcher(token);
		matcher.find();
		String szOp = matcher.group();
		switch(szOp) {
		case "+":
			return Operator.ADD;
		case "-":
			return Operator.SUBTRACT;
		case "*":
			return Operator.SCALAR_PRODUCT;
		case "/":
		case "\\":
			return Operator.DIVIDE;
		default:
			throw new MismatchException("Operator " + szOp + " is not recognized");
		}
	}

}
