package oberlin.builder.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oberlin.builder.parser.ast.AST;

public class NullaryParser implements Parser<NullaryPhraseStructure> {

	@Override
	public Class<NullaryPhraseStructure> getPhraseStructure() {
		return NullaryPhraseStructure.class;
	}


}
