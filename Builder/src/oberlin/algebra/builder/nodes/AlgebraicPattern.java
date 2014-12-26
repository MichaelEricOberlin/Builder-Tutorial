package oberlin.algebra.builder.nodes;

import java.util.*;
import java.util.regex.Pattern;

import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.pattern.ASTPattern;

public class AlgebraicPattern extends ASTPattern {

	public AlgebraicPattern(Pattern pattern) {
		super(getNameMap(), pattern);
	}

	private static Map<String, Class<? extends AST>> getNameMap() {
		final Map<String, Class<? extends AST>> nameMap = new HashMap<>();
		nameMap.put("EQUALITY", Equality.class);
		nameMap.put("PROGRAM", Program.class);
		nameMap.put("EQUATOR", Equator.class);
		nameMap.put("NUMERIC", Numeric.class);
		nameMap.put("NOMINAL", Nominal.class);
		nameMap.put("LPAREN", LParen.class);
		nameMap.put("RPAREN", RParen.class);
		nameMap.put("OPERATOR", Operator.class);

		return nameMap;
	}

}
