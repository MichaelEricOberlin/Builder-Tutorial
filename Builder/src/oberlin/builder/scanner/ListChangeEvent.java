package oberlin.builder.scanner;

import java.util.EventObject;

/**
 * Simple, package-exclusive event to alert observers to the change of list data.
 * 
 * @author © Michael Eric Oberlin Oct 14, 2014
 *
 */
class ListChangeEvent extends EventObject {

	/**
	 * For the unlikely case of serialization. This probably shouldn't be done
	 * anyway, though.
	 */
	private static final long serialVersionUID = 5092011285807267231L;

	public ListChangeEvent(Object source) {
		super(source);
	}

}
