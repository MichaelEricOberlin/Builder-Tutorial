package oberlin.algebra.builder;

import java.util.List;

import oberlin.algebra.builder.contextanalysis.AlgebraContextAnalyzer;
import oberlin.algebra.builder.parser.AlgebraicParser;
import oberlin.algebra.builder.scanner.AlgebraicScanner;
import oberlin.algebra.builder.scanner.AlgebraicScanner;
import oberlin.builder.*;
import oberlin.builder.parser.Parser;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.reporter.ErrorReporter;

public class AlgebraicBuilder extends Builder {

	public AlgebraicBuilder() {
		setReporter(new ErrorReporter());
		setScanner(new AlgebraicScanner());
		setContextAnalyzer(new AlgebraContextAnalyzer(getReporter()));
	}
	
	@Override
	public Parser<?> createParser(List<AST> tokens) {
			return new AlgebraicParser(tokens);
	}

}
