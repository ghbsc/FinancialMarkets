package OrderMatchingEngine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class LeafContainer extends Container<Order> {	
	List<Order> orderDataStore = Collections.synchronizedList(new ArrayList<Order>());
	
	public LeafContainer(OrderBook oBook, String name, Container<Order> parent) {
		super(oBook, name, parent);

	}

	@Override
	public void ProcessOrder(Order newOrder) {
		Container<Order> buyBook = parentContainer.getChildContainers().getValue("B");
		Container<Order> sellBook = parentContainer.getChildContainers().getValue("S");
		
		OrderEventArgs orderArg = new OrderEventArgs(this, newOrder, buyBook, sellBook);
		
		orderBook.addListeners(new EquityMatchingLogic());
		
		//Invoke the OrderBeforeInsert event which will also notify 
		//the matching business component which will then perform 
		//its internal matching 
		//the order becomes active in this stage	
		orderBook.OnOrderBeforeInsert(orderArg);
		
		//Check the quantity of the newly created order
		//because if the order has been successfully matched by matching
		//business component then quantity will be 0	
		if(newOrder.getQuantity() > 0) {
			orderDataStore.add(newOrder);
			orderDataStore.sort(orderBook.getOrderPriority());
			
			//orderBook.OnOrderInsert(orderArg);
		}
	}
	
	
	@Override
	public Iterator<Order> iterator() {
		Iterator<Order> it = new Iterator<Order>() {
			
			private int rowPosition = 0;
			
            @Override
            public boolean hasNext() {
            	return rowPosition < orderDataStore.size() && orderDataStore.get(rowPosition) != null;
            }

            @Override
            public Order next() {    		    
            	//The code in MoveNext method validates an order by checking 
    			//its quantity. If the quantity is equal to zero then it is deleted from ArrayList 
    			//and row pointer is positioned to next element in the Arraylist. 
    			//This check is continuously repeated inside a loop till it encounters an 
    			//Order whose quantity is greater than zero.             	
        		Order currentOrder = orderDataStore.get(rowPosition++);
        		if(currentOrder.getQuantity() == 0) {
        			orderDataStore.remove(rowPosition);
        			hasNext();
        		}
        		else
        			return currentOrder;

            		
            	rowPosition = 0;
                return null;
            }

            @Override
            public void remove() {
                // TODO Auto-generated method stub
            }
        };
        return it;
	}
	 
}
