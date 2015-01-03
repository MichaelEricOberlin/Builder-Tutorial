package oberlin.builder.scanner;

import java.util.*;

import oberlin.builder.parser.ast.AST;

public class NullaryScanner implements Scanner<NullarySpelling> {

	@Override
	public List<AST> apply(AST code) {
		List<AST> list = new ArrayList<AST>();
		return list;
	}

	@Override
	public Class<NullarySpelling> getSpelling() {
		return NullarySpelling.class;
	}

}
