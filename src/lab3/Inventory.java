package lab3;

import java.util.EmptyStackException;

public class Inventory 
{	
	int date = 301; //March 1st
	enum FoodItem {BUN, PATTY, LETTUCE, TOMATO, ONION, CHEESE}
	//initialize menu lists
	LinkedList<FoodItem> Burger = new LinkedList<FoodItem>();
	LinkedList<FoodItem> chsBrgr = new LinkedList<FoodItem>();
	LinkedList<FoodItem> vegBrgr = new LinkedList<FoodItem>();
	LinkedList<FoodItem> BrgrNoOnion = new LinkedList<FoodItem>();
	LinkedList<FoodItem> chsBrgrNoOnion = new LinkedList<FoodItem>();
	LinkedList<FoodItem> BrgrNoTom = new LinkedList<FoodItem>();
	
	private void initializeMenu()
	{
		Burger.add(FoodItem.BUN);
		Burger.add(FoodItem.PATTY);
		Burger.add(FoodItem.LETTUCE);
		Burger.add(FoodItem.TOMATO);
		Burger.add(FoodItem.ONION);
		
		chsBrgr.add(FoodItem.BUN);
		chsBrgr.add(FoodItem.PATTY);
		chsBrgr.add(FoodItem.CHEESE);
		chsBrgr.add(FoodItem.LETTUCE);
		chsBrgr.add(FoodItem.ONION);
		chsBrgr.add(FoodItem.TOMATO);
		
		vegBrgr.add(FoodItem.LETTUCE);
		vegBrgr.add(FoodItem.LETTUCE);
		vegBrgr.add(FoodItem.TOMATO);
		vegBrgr.add(FoodItem.ONION);
		
		BrgrNoOnion.add(FoodItem.BUN);
		BrgrNoOnion.add(FoodItem.PATTY);
		BrgrNoOnion.add(FoodItem.LETTUCE);
		BrgrNoOnion.add(FoodItem.TOMATO);
		
		chsBrgrNoOnion.add(FoodItem.BUN);
		chsBrgrNoOnion.add(FoodItem.PATTY);
		chsBrgrNoOnion.add(FoodItem.CHEESE);
		chsBrgrNoOnion.add(FoodItem.LETTUCE);
		chsBrgrNoOnion.add(FoodItem.TOMATO);
		
		BrgrNoTom.add(FoodItem.BUN);
		BrgrNoTom.add(FoodItem.PATTY);
		BrgrNoTom.add(FoodItem.ONION);
		BrgrNoTom.add(FoodItem.LETTUCE);
	}
	
	//initialize ingredient stacks
	private void initializeStock(ArrayStack<Integer>[] stock)
	{
		for(int i = 0; i < stock.length; i++)
		{
			stock[i] = new ArrayStack<Integer>(5000);
		}
	}
	
	public void initialize(ArrayStack<Integer>[] stock)
	{
		initializeMenu();
		initializeStock(stock);
	}
	
	public boolean placeOrder(int orderNumber, ArrayStack<Integer>[] stock)
	{
		assert (orderNumber <= 6 || orderNumber >= 1);
		boolean result = false;
		switch (orderNumber) {
		case 1:
			result = assembleOrder(Burger, stock);
			break;
		case 2:
			result = assembleOrder(chsBrgr, stock);
			break;
		case 3:
			result = assembleOrder(vegBrgr, stock);
			break;
		case 4:
			result = assembleOrder(BrgrNoOnion, stock);
			break;
		case 5:
			result = assembleOrder(chsBrgrNoOnion, stock);
			break;
		case 6:
			result = assembleOrder(BrgrNoTom, stock); 
			break;
		default:
			break;
		}
		return result;
	}

	private boolean assembleOrder(LinkedList<FoodItem> order, ArrayStack<Integer>[] stock) 
	{
		for(int i = 1; i <= order.getCount(); i++)
		{
			try {
				stock[order.look(i).ordinal()].pop();
			} catch (EmptyStackException e) {
				return false;
			}
		}
		return true;
	}
	
	// [stack order]INGREDIENT(expiration time) is 
	// [0]BUN(5) [1]PATTY(4) [2]LETTUCE(3) [3]TOMATO(3) [4]ONION(5) [5]CHEESE(2)
	public void receiveShipment(int shipmentSize, ArrayStack<Integer>[] stock)
	{
		for(int i = 0; i < stock.length; i++)
		{
			int expireDate = date;
			switch (i) 
			{
			case 0:
			case 4:
				expireDate+=5;
				break;
			case 1:
				expireDate+=4;
				break;
			case 2:
			case 3:
				expireDate+=3;
				break;
			case 5:
				expireDate+=2;
				break;
			default:
				break;
			}
			stock[i].push(expireDate, shipmentSize);
		}
	}
	
	public void sortStock(ArrayStack<Integer>[] stock)
	{
		RadixSort sorter = new RadixSort();
		for(int i = 0; i < stock.length; i++)
		{
			sorter.iterativeRadixSort(stock[i].toArray(), 0, stock[i].getLength() - 1);
		}
	}
	
	
}
