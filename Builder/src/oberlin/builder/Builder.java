package oberlin.builder;

import oberlin.builder.scanner.*;

public abstract class Builder {
	private Scanner scanner;
	
	public abstract Object build(String code);

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
}
