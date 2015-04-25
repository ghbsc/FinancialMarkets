package OrderMatchingEngine;

public interface OrderBeforeListener {
	public void OrderBook_OrderBeforeInsert(OrderEventArgs e);
}
