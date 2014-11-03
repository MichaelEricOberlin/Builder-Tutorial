package oberlin.algebra.builder.scanner;

import java.util.List;

import oberlin.algebra.builder.AlgebraicGrammar;
import oberlin.builder.scanner.Scanner;

public class AlgebraicScanner implements Scanner<AlgebraicGrammar> {
	
	@Override
	public Class<AlgebraicGrammar> getGrammar() {
		return AlgebraicGrammar.class;
	}

}
