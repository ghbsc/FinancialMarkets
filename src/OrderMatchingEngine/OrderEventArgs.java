package OrderMatchingEngine;

import java.util.EventObject;

public class OrderEventArgs extends EventObject  {
	private Order order;
	private Container<Order> buyBook;
	private Container<Order> sellBook;	
	
	public OrderEventArgs(Object source, Order newOrder, Container<Order> bBook, Container<Order> sBook) {
		super(source);
		order = newOrder;
		buyBook = bBook;
		sellBook = sBook;
	}

	public Order getOrder() {
		return order;
	}

	public Container<Order> getSellBook() {
		return sellBook;
	}
	
	public Container<Order> getBuyBook() {
		return buyBook;
	}	
}
