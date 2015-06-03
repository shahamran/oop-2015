package filescript.filters;

public class FilterValue<T> {
	T myVal;
	
	public FilterValue(T value) {
		myVal = value;
	}
	
	public T getVal() {
		return myVal;
	}
}
