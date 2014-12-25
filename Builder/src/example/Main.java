package example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import oberlin.algebra.builder.AlgebraicBuilder;
import oberlin.builder.Builder;

public class Main {
	
	static Builder builder = new AlgebraicBuilder();
	
	public static void main(String[] args) throws IOException {

		System.out.println("example.Main");
		
		try(BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));) {
			
			out.write("Type \"!exit\" to exit the program\n");
			String input = "";
			Object built = null;
			while(!input.trim().toLowerCase().equals("!exit")) {
				if(input.length() != 0) {
					built = builder.build(input);
					
					out.write("Builds to: " + built);
					out.newLine();
					out.flush();
				}
				
				out.write("Enter text: ");
				out.flush();
				input = in.readLine();
			}
		}
	}

}
