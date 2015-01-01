package oberlin.builder.identifier;

import java.util.Optional;

import oberlin.builder.IdentificationNotFoundException;
import oberlin.builder.parser.ast.AST;

public class IdentificationTable {
	//PRIVATE UTILITY FIELDS
	private int level;	//level of scope, zero being global.
	private Optional<Identification> latest;	//last identifier read.
	
	public IdentificationTable() {
		level = 0;
		latest = Optional.empty();
	}
	
	/**
	 * Opens a new level of scope. This is a status which Identifications
	 * may be associated with; every reference must be of equal or lesser
	 * scope to the program pointer to be retrieved.
	 */
	public void openScope() {
		level++;
	}
	
	/**
	 * Closes the topmost level, discards all entries belonging to that
	 * level.
	 */
	public void closeScope() {
		Optional<Identification> entry, local;
		
		//idea for assert: level >= 0
		if(this.latest.isPresent()) {
			entry = this.latest;
			while(entry.get().getLevel() == this.level) {
				local = entry;
				entry = local.get().getPrevious();
			}
			this.level--;
			this.latest = entry; 
		}	//else, there is no latest entry, and the table is empty anyway
	}
	
	public void enter(String spelling, AST node) {
		Optional<Identification> entry = this.latest;
		boolean present = false,
				searching = true;
//				duplicated;
		
		while(searching) {
			if(entry.isPresent()) {
				if(entry.get().getLevel() <= this.level) {
					searching = false;
				} else if(entry.get().getSpelling().equals(spelling)) {
					//found it, but no guarantee that it's in the right scope!
					present = true;
					searching = false;
				} else entry = entry.get().getPrevious();
			}
		}
		
		//duplicated entry...
//		duplicated = present;
		
		entry = Optional.of(new Identification(spelling, node, this.level, this.latest));
		this.latest = entry;
	}
	
	public AST retrieve(String spelling) throws IdentificationNotFoundException {
		Optional<Identification> entry = Optional.empty();
		AST node = null;
		boolean present = false,
				searching = true;
		
		entry = this.latest;
		while(searching) {
			if(!entry.isPresent()) {
				//if entry is null, then we are at the beginning.
				searching = false;
			} else if(entry.get().getSpelling().equals(spelling)) {
				present = true;
				searching = false;
				node = entry.get().getNode();
			} else entry = entry.get().getPrevious();
		}
		
		if(node == null) throw new IdentificationNotFoundException("Identifier \"" + spelling + "\" has not been declared");
		return node;
	}
}
