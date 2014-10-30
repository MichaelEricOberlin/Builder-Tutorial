package oberlin.algebra.builder;

import java.util.List;

import oberlin.algebra.builder.scanner.AlgebraScanner;
import oberlin.builder.Builder;

/*
 * NOTE: Broken until AlgebraParser is produced.
 */
public class AlgebraBuilder extends Builder {
	
	public AlgebraBuilder() {
		this.setScanner(new AlgebraScanner());
//		this.setParser(new AlgebraParser());
	}
	
}
