package OrderMatchingEngine;

public class EquityOrder extends Order {
	
	public EquityOrder(String instrument, String orderType, String buySell, double price, int quantity)
	{
		this.instrument = instrument;
		this.orderType = orderType;
		this.buySell = buySell;
		this.price = price;
		this.quantity = quantity;
	}
}
