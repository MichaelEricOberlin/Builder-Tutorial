package oberlin.builder.contextanalysis;

import java.util.Map;
import java.util.function.BiFunction;

import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

/*
 * TODO: "Boolean" is probably a bad move here. This is meant to return contextual properties,
 * which can rarely be adequately described with a simple true/false binary value. It 
 * might be better to return a mapping.
 */
public abstract class Context implements Visitor<ContextAnalyzer<?>, Boolean> {

	public Context() {
	}

}
