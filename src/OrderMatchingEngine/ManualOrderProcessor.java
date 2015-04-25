package OrderMatchingEngine;

public class ManualOrderProcessor {
	private ManualBusinessDomain businessDomain;
	
	private ManualQueueProcessor queueProcessor;	
	
	public ManualOrderProcessor(ManualBusinessDomain domain,String wspName) {		
		businessDomain = domain;
		
		queueProcessor = new ManualQueueProcessor(businessDomain);
		
		Thread processor = new Thread(queueProcessor);
		processor.start();
	}
	
	public void EnQueue(Order newOrder) {
		queueProcessor.EnQueue(newOrder);;
	}	
}
