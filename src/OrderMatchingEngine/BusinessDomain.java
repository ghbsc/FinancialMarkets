package OrderMatchingEngine;

import java.util.concurrent.ConcurrentHashMap;

public class BusinessDomain {
	private ConcurrentHashMap<String, OrderProcessor> orderProcessors = 
														new ConcurrentHashMap<String, OrderProcessor>();
	
	private String[] oprocNames;	
	
	//creation of order book
	private OrderBook orderBook = new OrderBook();	
	
	
	public BusinessDomain(String domainName, String[] workNames) {
		oprocNames= workNames;		
	}

	public OrderBook getOrderBook() {
		return orderBook;
	}
	
	public void Start() {
		for(String oprocName : oprocNames) 
			orderProcessors.putIfAbsent(oprocName, new OrderProcessor(this, oprocName));			
	}
	
	//A façade method to the outside world, 
	//through which orders are submitted and queued up in 
	//appropriate order processor. 
	public void SubmitOrder(String procName, Order order) {
		OrderProcessor orderProcessor = orderProcessors.get(procName);
		orderProcessor.EnQueue(order);
	}
}
