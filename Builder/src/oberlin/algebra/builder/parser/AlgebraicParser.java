package oberlin.algebra.builder.parser;

import java.util.List;

import oberlin.builder.parser.ErrorReporter;
import oberlin.builder.parser.Parser;
import oberlin.builder.parser.Parser2;
import oberlin.builder.parser.ast.AST;

public class AlgebraicParser extends Parser2<AlgebraicPhraseStructure> {
	
	public AlgebraicParser(List<AST> astList) {
		super(new AlgebraicPhraseStructure(), astList, null);
	}

	public AlgebraicParser(AlgebraicPhraseStructure visitor, List<AST> astList, ErrorReporter reporter) {
		super(visitor, astList, reporter);
	}

//	@Override
//	public Class<AlgebraicPhraseStructure> getPhraseStructure() {
//		return AlgebraicPhraseStructure.class;
//	}

}
