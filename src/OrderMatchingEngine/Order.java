package OrderMatchingEngine;

import java.util.Date;

public abstract class Order {
	protected double price;
	private Date orderTimeStamp;
	protected String buySell;
	protected int quantity;
	protected String instrument;
	protected String orderType;	
	
	public double getPrice() {
		return price;
	}
	
	public Date getTimeStamp() {
		return orderTimeStamp;
	}
	
	public String getBuySell() {
		return buySell;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int newValue) {
		if(newValue < 0)
			quantity = 0;
		else
			quantity = newValue;
	}
	
	public String getInstrument() {
		return instrument;
	}
	
	public String getOrderType() {
		return orderType;
	}	
}
