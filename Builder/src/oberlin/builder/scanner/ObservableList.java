package oberlin.builder.scanner;

import java.util.*;

class ObservableList<E> implements List<E> {
	
	private List<E> innerList;
	private Set<ListChangeObserver> observers = new HashSet<>();
	
	public ObservableList(List<E> innerList) {
		this.innerList = innerList;
	}
	
	protected Set<ListChangeObserver> getObservers() {
		return observers;
	}

	protected void setObservers(Set<ListChangeObserver> observers) {
		this.observers = observers;
	}
	
	protected void addObserver(ListChangeObserver observer) {
		this.getObservers().add(observer);
	}

	//OVERRIDES
	@Override
	public int size() {
		return innerList.size();
	}

	@Override
	public boolean isEmpty() {
		return innerList.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return innerList.contains(o);
	}

	@Override
	public Iterator<E> iterator() {
		return innerList.iterator();
	}

	@Override
	public Object[] toArray() {
		return innerList.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return innerList.toArray(a);
	}

	@Override
	public boolean add(E e) {
		boolean ret = innerList.add(e);
		notifyObservers();
		return ret;
	}

	@Override
	public boolean remove(Object o) {
		boolean ret = innerList.remove(o);
		notifyObservers();
		return ret;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return innerList.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean ret = innerList.addAll(c);
		notifyObservers();
		return ret;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		boolean ret = innerList.addAll(index, c);
		notifyObservers();
		return ret;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean ret = innerList.removeAll(c);
		notifyObservers();
		return ret;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		boolean ret = innerList.removeAll(c);
		notifyObservers();
		return ret;
	}

	@Override
	public void clear() {
		innerList.clear();
		notifyObservers();
	}

	@Override
	public E get(int index) {
		return innerList.get(index);
	}

	@Override
	public E set(int index, E element) {
		E ret = innerList.set(index, element);
		notifyObservers();
		return ret;
	}

	@Override
	public void add(int index, E element) {
		innerList.add(index, element);
		notifyObservers();
	}

	@Override
	public E remove(int index) {
		E ret = innerList.remove(index);
		notifyObservers();
		return ret;
	}

	@Override
	public int indexOf(Object o) {
		return innerList.indexOf(o);
	}

	@Override
	public int lastIndexOf(Object o) {
		return innerList.lastIndexOf(o);
	}

	@Override
	public ListIterator<E> listIterator() {
		return innerList.listIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return innerList.listIterator(index);
	}
	
	/**
	 * Not especially recommended! Only if the sublist is GUARANTEED to call the methods
	 * in this class should it be used. If it does not, then it is possible to desync the
	 * listener and the contents.
	 */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		return innerList.subList(fromIndex, toIndex);
	}
	
	
	//INTRINSIC METHODS
	protected void notifyObservers() {
		ListChangeEvent event = new ListChangeEvent(this);
		
		for(ListChangeObserver observer : observers) {
			observer.eventDispatched(event);
		}
	}
}
