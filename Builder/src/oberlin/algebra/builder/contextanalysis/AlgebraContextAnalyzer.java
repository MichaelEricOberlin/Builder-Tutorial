package oberlin.algebra.builder.contextanalysis;

import oberlin.builder.contextanalysis.ContextAnalyzer;
import oberlin.builder.reporter.ErrorReporter;

public class AlgebraContextAnalyzer extends ContextAnalyzer<AlgebraicContext> {
	
	AlgebraicContext context = new AlgebraicContext();
	
	public AlgebraContextAnalyzer(ErrorReporter reporter) {
		super(reporter);
	}

	@Override
	public AlgebraicContext getVisitor() {
		return context;
	}

}
