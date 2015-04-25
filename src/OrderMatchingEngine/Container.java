package OrderMatchingEngine;

import java.util.Iterator;

//@SuppressWarnings("rawtypes")
public class Container<Order> implements Iterable<Order>  {

	protected OrderBook orderBook;
	protected String containerName;
	protected Container<Order> parentContainer;
	
	//Reference to Leaf items where the actual order are stored
	protected ContainerCollection leafItems = new ContainerCollection();	
	
	public Container(OrderBook oBook, String name, Container<Order> parent) {
		orderBook = oBook;
		containerName = name;
		parentContainer = parent;
	}
	
	public ContainerCollection getChildContainers() {
		return leafItems;
	}
	
	public Iterator<Order> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	public void ProcessOrder(Order newOrder)
	{
	}	
}
