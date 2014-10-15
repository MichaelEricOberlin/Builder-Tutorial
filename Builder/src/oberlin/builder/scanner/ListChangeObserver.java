package oberlin.builder.scanner;

public abstract class ListChangeObserver {
	public void eventDispatched(ListChangeEvent e) {
		hook();
	}
	
	//method called every time a ListChangeEvent is intercepted.
	public abstract void hook();
}
