package oberlin.builder.scanner;

import java.util.List;
import java.util.regex.*;

public abstract class AbstractPatternScanner<E> implements PatternScanner<E, List<String>> {
	protected Pattern pattern;
	
	public AbstractPatternScanner(Pattern pattern) {
		this.setPattern(pattern);
	}
	
	public AbstractPatternScanner() {
		final Pattern nullPattern = Pattern.compile("");
		this.setPattern(nullPattern);
	}
	
	public Pattern getPattern() {
		return pattern;
	}
	
	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}

}
