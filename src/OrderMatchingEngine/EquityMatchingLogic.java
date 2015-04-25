package OrderMatchingEngine;

public class EquityMatchingLogic implements OrderBeforeListener {

	@Override
	public void OrderBook_OrderBeforeInsert(OrderEventArgs e) {
		if(e.getOrder().getBuySell() == "B")
			MatchBuyLogic(e);
		else
			MatchSellLogic(e);
	}

	private void MatchBuyLogic(OrderEventArgs e) {
		//since the order to be matched is a buy order
		//therefore start iterating orders in sell order book
		//Use custom iterator implementation here
		Container<Order> sellBook = e.getSellBook();
		for(Order currentOrder : sellBook) {
			if(currentOrder.getPrice() <= e.getOrder().getPrice() && e.getOrder().getQuantity() > 0) {
				System.out.println("Match found..Generate Trade..");
				
				int quantity = currentOrder.getQuantity();
				
				currentOrder.setQuantity(currentOrder.getQuantity() - e.getOrder().getQuantity());
				e.getOrder().setQuantity(e.getOrder().getQuantity() - quantity);
			}
			else
				continue; //break in other place
		}
	}

	private void MatchSellLogic(OrderEventArgs e) {
		//since the order to be matched is a sell order
		//therefore start iterating orders in buy order book
		//Use custom iterator implementation here
		Container<Order> buyBook = e.getBuyBook();		
		for(Order currentOrder : buyBook) {
			if(currentOrder.getPrice() >= e.getOrder().getPrice() && e.getOrder().getQuantity() > 0) {
				System.out.println("Match found..Generate Trade..");
				
				int quantity = currentOrder.getQuantity();
				
				currentOrder.setQuantity(currentOrder.getQuantity() - e.getOrder().getQuantity());
				e.getOrder().setQuantity(e.getOrder().getQuantity() - quantity);
			}
			else
				continue; //break in other place
		}
	}
	
}
