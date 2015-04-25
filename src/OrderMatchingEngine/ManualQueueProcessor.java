package OrderMatchingEngine;

public class ManualQueueProcessor implements Runnable {

	private LiteBlockingQueue<Order> messageQueue;	
	private ManualBusinessDomain businessDomain;	
	
	public ManualQueueProcessor(ManualBusinessDomain domain) {
		businessDomain = domain;
		
		messageQueue = new LiteBlockingQueue<Order>(100);	
	}
	
	@Override
	public void run() {
		ProcessQueue();
	}

	public void EnQueue(Order newOrder) {
		try {
			messageQueue.put(newOrder);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void ProcessQueue() {
		while(true) {		
			try {
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
