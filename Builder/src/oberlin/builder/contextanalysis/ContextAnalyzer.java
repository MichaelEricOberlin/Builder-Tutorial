package oberlin.builder.contextanalysis;

import oberlin.builder.parser.ast.AST;
import oberlin.builder.reporter.ErrorReporter;
import oberlin.builder.visitor.Visitor;

public abstract class ContextAnalyzer<V extends Visitor> {
	ErrorReporter reporter;
	
	public ContextAnalyzer(ErrorReporter reporter) {
		if(reporter == null) reporter = new ErrorReporter();
			//Null entry means create own error reporter
		else this.reporter = reporter;
	}
	
	public void check(AST root) {
		
	}
	
	public abstract V getVisitor();
}
