package oberlin.builder.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oberlin.builder.Grammar;

public enum NullaryGrammar implements Grammar {
	ANY;

	@Override
	public Pattern getPattern() {
		final Pattern pattern = Pattern.compile("^.*$");
		return pattern;
	}

	@Override
	public List<String> manageToken(Matcher matcher) {
		List<String> returnable = new ArrayList<>();
		returnable.add(matcher.group());
		return returnable;
	}

}
