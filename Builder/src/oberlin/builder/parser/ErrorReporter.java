package oberlin.builder.parser;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import oberlin.builder.BuilderException;

/**
 * Keeps running tally of all errors (and warnings) encountered by a build.
 * 
 * @author © Michael Eric Oberlin Dec 15, 2014
 *
 */
public class ErrorReporter {
	
	private List<BuilderException> errors;
	
	public ErrorReporter() {
		
	}
	
	public void error(BuilderException error) {
		errors.add(error);
	}
	
	public void reportErrors() {
		try(BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out))) {
			for(BuilderException error : errors) {
				out.write(error.getMessage());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}
}
