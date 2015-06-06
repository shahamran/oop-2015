package filescript.sections;
import filescript.sections.filters.*;
import filescript.sections.orders.*;
import filescript.*;

public class Section {
	private Filter myFilter;
	private Order myOrder;
	private TypeOneException[] warnings = {};
	
	public Section() {
		warnings = new TypeOneException[0];
	}
	
	public Section(Filter newFilter, Order newOrder) {
		this();
		myFilter = newFilter;
		myOrder = newOrder;
	}

	public Order getOrder() {
		return myOrder;
	}
	
	public void setOrder(Order newOrder) {
		myOrder = newOrder;
	}

	public Filter getFilter() {
		return myFilter;
	}
	
	public void setFilter(Filter newFilter) {
		myFilter = newFilter;
	}
	
	public void addWarning(TypeOneException e) {
		if (e == null) {
			return;
		}
		int oldLength = warnings.length;
		TypeOneException[] oldWarnings = warnings;
		warnings = new TypeOneException[oldLength + 1];
		for (int i = 0; i < oldLength; i++) {
			warnings[i] = oldWarnings[i];
		}
		warnings[oldLength] = e;
	}
	
	public TypeOneException[] getWarnings() {
		return warnings;
	}
}
