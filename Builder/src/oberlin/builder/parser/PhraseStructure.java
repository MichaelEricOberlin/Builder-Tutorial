package oberlin.builder.parser;

import oberlin.builder.*;
import oberlin.builder.parser.ast.AST;
import oberlin.builder.visitor.Visitor;

import java.util.*;

public interface PhraseStructure extends Visitor<Parser<?>, AST> {
}
