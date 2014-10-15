package oberlin.algebra.builder;

import java.util.List;

import oberlin.algebra.builder.scanner.AlgebraScanner;
import oberlin.builder.Builder;

public class AlgebraBuilder extends Builder {
	
	public AlgebraBuilder() {
		this.setScanner(new AlgebraScanner());
	}
	
	@Override
	public Object build(String code) {
		List<String> tokens = getScanner().tokenize(code);
		
		StringBuilder builder = new StringBuilder();
		for(String token : tokens) {
			builder.append(token).append("; ");
		}
		return builder.toString();
	}

}
