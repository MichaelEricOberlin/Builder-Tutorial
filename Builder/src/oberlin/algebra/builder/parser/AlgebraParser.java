package oberlin.algebra.builder.parser;

import oberlin.builder.parser.Parser;

import java.util.*;
import java.util.logging.Logger;

import oberlin.builder.parser.*;
import oberlin.algebra.builder.parser.*;

/**
 * Parser specifically meant for the simplification of a complete algebraic expression
 * 
 * @author © Michael Eric Oberlin Oct 15, 2014
 *
 */
public class AlgebraParser extends Parser {
	public AlgebraParser() {
		List<Class<? extends Term>> operationList = new ArrayList<>();
		operationList.add(NumericTerm.class);
		
		this.addOperationList(0, operationList);
	}

}
