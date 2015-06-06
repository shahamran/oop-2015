package filescript.sections;
import filescript.sections.filters.*;
import filescript.sections.orders.*;

public class Section {
	private Filter myFilter;
	private Order myOrder;
	
	public Section(Filter newFilter, Order newOrder) {
		setFilter(newFilter);
		setOrder(newOrder);
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
}
