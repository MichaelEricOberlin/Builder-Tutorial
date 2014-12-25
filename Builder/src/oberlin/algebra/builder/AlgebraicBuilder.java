package oberlin.algebra.builder;

import java.util.List;

import oberlin.algebra.builder.parser.AlgebraicParser;
import oberlin.algebra.builder.scanner.AlgebraicScanner;
import oberlin.algebra.builder.scanner.AlgebraicScanner;
import oberlin.builder.*;
import oberlin.builder.parser.Parser;
import oberlin.builder.parser.ast.AST;

public class AlgebraicBuilder extends Builder {

	public AlgebraicBuilder() {
		 setScanner(new AlgebraicScanner());
	}
	
	@Override
	public Parser<?> createParser(List<AST> tokens) {
			return new AlgebraicParser(tokens);
	}

}
