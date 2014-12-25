package oberlin.builder.visitor;

import java.util.function.BiFunction;

import oberlin.builder.parser.Parser;
import oberlin.builder.parser.SourcePosition;
import oberlin.builder.parser.ast.AST;

public abstract class VisitHandler<E extends AST> implements BiFunction<Parser<?>, SourcePosition, E> {
}
