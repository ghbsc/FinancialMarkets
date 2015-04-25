package OrderMatchingEngine;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueProcessor implements Runnable {
	private BlockingQueue<Order> messageQueue;	
	private BusinessDomain businessDomain;
	
	public QueueProcessor(BusinessDomain domain) {
		//latch = cdLatch;
		businessDomain = domain;
		
		messageQueue = new LinkedBlockingQueue<Order>();	
	}
	
	@Override
	public void run() {
		ProcessQueue();
	}

	public void EnQueue(Order newOrder) {
		try {
			messageQueue.put(newOrder);
			//latch.countDown();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ProcessQueue() {
		while(true) {		
			try {
				//latch.await();
				Order order = messageQueue.take();
				
				businessDomain.getOrderBook().Process(order);		
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}
