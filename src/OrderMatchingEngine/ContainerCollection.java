package OrderMatchingEngine;

import java.util.HashMap;

public class ContainerCollection {
	
	//Container collection represents individual container
	//For example all regular orders for MSFT will be arranged 
	//in a separate container, similarly all buy orders falling under
	//regular order category will form a separate container but with 
	//reference to its parent container which is "regular order container"
	private HashMap<String, Container<Order>> containerCollection = new HashMap<String, Container<Order>>();
	
	public boolean Exists(String containerName) {
		return containerCollection.containsKey(containerName);
	}	
		
	public Container<Order> getValue(String name) {
		return containerCollection.get(name);
	}
	
	public void setValue(String name, Container<Order> container) {
		containerCollection.put(name, container);
	}
}
