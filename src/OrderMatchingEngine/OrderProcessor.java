package OrderMatchingEngine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OrderProcessor {
	private BusinessDomain businessDomain;
	
	//private CountDownLatch latch;
	private QueueProcessor queueProcessor;
	
	public OrderProcessor(BusinessDomain domain,String wspName) {
		businessDomain = domain;
		
		//latch = new CountDownLatch(100);
		queueProcessor = new QueueProcessor(businessDomain);
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(queueProcessor);
		executor.shutdown();
	}

	public void EnQueue(Order newOrder) {
		queueProcessor.EnQueue(newOrder);
	}

}
