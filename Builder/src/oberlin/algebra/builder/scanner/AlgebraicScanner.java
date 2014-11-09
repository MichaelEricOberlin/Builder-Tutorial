package oberlin.algebra.builder.scanner;

import java.util.List;

import oberlin.algebra.builder.AlgebraicSpelling;
import oberlin.builder.scanner.Scanner;

public class AlgebraicScanner implements Scanner<AlgebraicSpelling> {
	
	@Override
	public Class<AlgebraicSpelling> getSpelling() {
		return AlgebraicSpelling.class;
	}

}
