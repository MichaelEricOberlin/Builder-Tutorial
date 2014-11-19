package oberlin.algebra.builder.parser;

import oberlin.builder.parser.Parser;

public class AlgebraicParser extends Parser<AlgebraicPhraseStructure> {

	@Override
	public Class<AlgebraicPhraseStructure> getPhraseStructure() {
		return AlgebraicPhraseStructure.class;
	}

}
