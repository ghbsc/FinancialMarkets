package OrderMatchingEngine;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.management.ListenerNotFoundException;

public class OrderBook {
	//This variable holds the root node of the order tree 
	//that in turn allows navigating the entire tree.
	private ContainerCollection bookRoot;
	
	private Comparator<Order> orderPriority;
	
	List<OrderBeforeListener> listeners = new ArrayList<OrderBeforeListener>();
	
	public OrderBook() {
		bookRoot = new ContainerCollection();
	}
	
	public void addListeners(OrderBeforeListener toAdd) {
		listeners.add(toAdd);
	}
		
	public Comparator<Order> getOrderPriority() {
		return orderPriority;
	}
	
	public void SetOrderPriority(Comparator<Order> newValue) {
		orderPriority = newValue;
	}
	
	//Internal method to trigger active order notification 
	//to external business component
	public void OnOrderBeforeInsert(OrderEventArgs e)
	{		
	   for (OrderBeforeListener ol : listeners) 
		   ol.OrderBook_OrderBeforeInsert(e);
	   
       
       //Clean up listeners afterwards to prevent duplicates
       listeners.clear();
	}
	
	//Internal method to trigger passive order notification 
	//to external business component
//	public void OnOrderInsert(OrderEventArgs e)
//	{		
//       for (OrderListener ol : listeners) 
//    	   ol.OrderBook_OrderInsert(e);
//	}	
	
	public void Process(Order order) {
		Container<Order> container = ProcessContainers(bookRoot, order.getInstrument(), order, null);
		container = ProcessContainers(container.getChildContainers(), order.getOrderType(), order, container);
		
		//Logic deviates a bit, if it is a buy or sell node
		//then leafcontainer is created which actually holds the order	
		if(!container.getChildContainers().Exists(order.getBuySell())) {
			LeafContainer buyContainer = new LeafContainer(this, "B", container);
			LeafContainer sellContainer = new LeafContainer(this, "S", container);
			
			container.getChildContainers().setValue("B", buyContainer);
			container.getChildContainers().setValue("S", sellContainer);			
		}
		
		//Based on the buy/sell attribute of the order 
		//access the underlying leaf container
		LeafContainer leafContainer = (LeafContainer)container.getChildContainers().getValue(order.getBuySell());
		leafContainer.ProcessOrder(order);
	}

	
	private Container<Order> ProcessContainers(ContainerCollection containerCollection, String name, Order order, Container<Order> parent) {
		if(!containerCollection.Exists(name))
			containerCollection.setValue(name, new Container<Order>(this, name, parent));
		
		Container<Order> currentContainer = containerCollection.getValue(name);
		currentContainer.ProcessOrder(order);
		return currentContainer;
	}	
	
}
