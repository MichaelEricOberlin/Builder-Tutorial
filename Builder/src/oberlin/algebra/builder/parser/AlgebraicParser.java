package oberlin.algebra.builder.parser;

import java.util.List;

import oberlin.builder.BuilderException;
import oberlin.builder.parser.Parser;
import oberlin.builder.parser.ast.AST;

public class AlgebraicParser extends Parser<AlgebraicPhraseStructure> {

	@Override
	public Class<AlgebraicPhraseStructure> getPhraseStructure() {
		return AlgebraicPhraseStructure.class;
	}


}
