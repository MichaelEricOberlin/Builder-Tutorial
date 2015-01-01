package oberlin.builder.identifier;

import java.util.Optional;

import oberlin.builder.parser.ast.AST;

public class Identification {
	final String spelling;
	final AST node;
	Optional<Identification> previous = Optional.empty();
	int level;
	
	public Identification(String spelling, AST node, int level, Optional<Identification> previous) {
		this.spelling = spelling;
		this.node = node;
		this.level = level;
		this.previous = previous;
	}

	public Optional<Identification> getPrevious() {
		return previous;
	}

	public int getLevel() {
		return level;
	}

	public String getSpelling() {
		return spelling;
	}
	
	public AST getNode() {
		return node;
	}

}
