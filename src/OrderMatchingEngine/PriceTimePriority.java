package OrderMatchingEngine;

import java.util.Comparator;
import java.util.Date;

import javax.print.attribute.standard.Compression;

public class PriceTimePriority implements Comparator<Order> {

	public int CompareOrder(Order order0, Order order1, int sortingOrder) {
		int priceComp = Double.compare(order0.getPrice(), order1.getPrice());
		
		//If both price are equal then we also need to sort according to 
		//order timestamp		
		if ( priceComp == 0 ) {
			int timeComp = order0.getTimeStamp().compareTo(order1.getTimeStamp());
			return timeComp;
		}
		
		//since the sorting order for buy and sell order book is different
		//we need to ensure that orders are arranged accordingly
		//buy order book - highest buy price occupies top position
		//sell order book - lowest sell price occupies top position
		//therefore sortingOrder helps to achieve this ranking
		//a value of -1 sorts order in descending order of price and ascending 
		//order of time 
		//similarly value of 1 sorts order in ascending order of price
		//and ascending order of time
		return priceComp * sortingOrder;		
	}
	
	public int compare(Order order0, Order order1) {
		if(order0.getBuySell() == "B")
			return CompareOrder(order0, order1, -1);
		else
			return CompareOrder(order0, order1, 1);
	}

}
