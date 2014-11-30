package oberlin.algebra.builder.nodes;

import java.util.*;
import java.util.regex.Pattern;

import oberlin.builder.parser.ast.AST;
import oberlin.builder.parser.ast.pattern.ASTPattern;

public class AlgebraicPattern extends ASTPattern {
	
	final Map<String, Class<? extends AST>> nameMap = new HashMap<>();
	{
		nameMap.put("EXPRESSION", Expression.class);
		nameMap.put("EQUALITY", Equality.class);
		nameMap.put("PROGRAM", Program.class);
		nameMap.put("EQUATOR", Equator.class);
		nameMap.put("NUMERIC", Numeric.class);
		nameMap.put("NOMINAL", Nominal.class);
		nameMap.put("LPAREN", LParen.class);
		nameMap.put("RPAREN", RParen.class);
		nameMap.put("OPERATOR", Operator.class);
	}
	
	public AlgebraicPattern(Pattern pattern) {
		super(pattern);
		
		nameMap.keySet().stream().forEach(name -> System.out.print(name + " "));
		//set type name map
		setTypeNameMap(nameMap);
	}

}
