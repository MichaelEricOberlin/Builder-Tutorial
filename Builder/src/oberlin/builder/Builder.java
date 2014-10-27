package oberlin.builder;

import java.util.List;
import java.util.logging.*;

import oberlin.builder.scanner.*;
import oberlin.builder.scanner.lexeme.Lexeme;
import oberlin.builder.analysis.BulkAnalyzer;
import oberlin.builder.encoder.CodeGenerator;
import oberlin.builder.parser.*;

public abstract class Builder {
	private Logger logger = Logger.getLogger("Builder");
	private Scanner scanner;
	private Parser parser;
	private BulkAnalyzer bulkAnalyzer;
	private CodeGenerator<?> codeGenerator;
	
	public Object build(String code) throws BuilderException {
		List<Lexeme> lexemes = getScanner().scan(code);
		
		AbstractSyntaxTree ast = getParser().parse(lexemes);
		
		try {
			//Analyze code, check for malformed-program exceptions.
			bulkAnalyzer.analyze(ast);
			
			/*
			 * This isn't really the best way to handle an exception, I don't think.
			 * 
			 * For starters, there may be occasions during which it is in the client's
			 * best interest to find as many bugs as possible; particularly with larger
			 * programs. In this instance, it might be better to have the analyzer return
			 * a list of BuilderExceptions, and go through them one by one; or continue
			 * if the list is empty.
			 */
		} catch(BuilderException ex) {
			logger.log(Level.WARNING, ex.getMessage(), ex);
			System.exit(0);
		}
		
		Object objectProgram = codeGenerator.encode(ast);
		
//		return objectProgram;
		
		//TODO: At this point, objectProgram should be our completed and runnable binary. The following code is more for
		//embedded testing than anything else, and should be removed before release.
		System.out.println(ast.getClass() + ": " + ast);
		
		StringBuilder szBuilder = new StringBuilder();
		for(Lexeme lexeme : lexemes) {
			szBuilder.append(lexeme).append("; ");
		}
		return szBuilder.toString();
		
	}

	public Scanner getScanner() {
		return scanner;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	protected Parser getParser() {
		return parser;
	}

	protected void setParser(Parser parser) {
		this.parser = parser;
	}
	
}
