package oberlin.builder.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import oberlin.builder.parser.ast.AST;

public class NullaryParser extends Parser<NullaryPhraseStructure> {

	public NullaryParser(List<AST> astList) {
		super(new NullaryPhraseStructure(), astList, null);
	}
	
	public NullaryParser(List<AST> astList, ErrorReporter reporter) {
		super(new NullaryPhraseStructure(), astList, reporter);
	}


}
