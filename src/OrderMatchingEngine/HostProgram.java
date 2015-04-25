package OrderMatchingEngine;

public class HostProgram {

	public static void main(String[] args) {
		BusinessDomain equityDomain = new BusinessDomain("Equity Domain", new String[]{"MSFT","IBM","GE"});
		//ManualBusinessDomain equityDomain = new ManualBusinessDomain("Equity Domain", new String[]{"MSFT","IBM","GE"});
		
		equityDomain.getOrderBook().SetOrderPriority(new PriceTimePriority());
		equityDomain.Start();
		
		//Submit buy order		
		equityDomain.SubmitOrder("MSFT", new EquityOrder("MSFT", "Regular", "B", 20, 3));
		
		//Submit sell order
		//this will also generate a trade because 
		//there is a matching counter buy order
		equityDomain.SubmitOrder("MSFT",new EquityOrder("MSFT", "Regular", "S", 20,2));	
	}

}
