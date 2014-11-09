package oberlin.builder.scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oberlin.builder.TerminalSpelling;
import oberlin.builder.Terminal;
import oberlin.builder.parser.ast.AST;

public enum NullaryGrammar implements TerminalSpelling {
	ANY;

	@Override
	public Pattern getPattern() {
		final Pattern pattern = Pattern.compile("^.*$");
		return pattern;
	}

	@Override
	public List<AST> manageToken(Matcher matcher) {
		List<AST> returnable = new ArrayList<>();
		returnable.add(new Terminal(matcher.group()));
		return returnable;
	}

}
