package lab3;

import java.sql.Array;
import java.util.EmptyStackException;
import java.util.Random;

public class Inventory 
{	
	private int wasteBun = 0, wastePatty = 0, wasteLettuce = 0, wasteTomato = 0, wasteOnion = 0, wasteCheese = 0;
	private int countItemOne = 0, countItemTwo = 0, countItemThree=0, countItemFour=0, countItemFive=0, countItemSix=0;
	private int lostCustomerDay=0, lostCustomerTotal = 0;
	private int startDate = 301; //March 1st
	private int currentDate = startDate;
	Random rand = new Random();
	
	enum FoodItem {BUN, PATTY, LETTUCE, TOMATO, ONION, CHEESE}
	//initialize menu lists
	LinkedList<FoodItem> Burger = new LinkedList<FoodItem>(); //item one
	LinkedList<FoodItem> chsBrgr = new LinkedList<FoodItem>(); //item two
	LinkedList<FoodItem> vegBrgr = new LinkedList<FoodItem>(); //item three
	LinkedList<FoodItem> BrgrNoOnion = new LinkedList<FoodItem>(); //item four
	LinkedList<FoodItem> chsBrgrNoOnion = new LinkedList<FoodItem>(); //item five
	LinkedList<FoodItem> BrgrNoTom = new LinkedList<FoodItem>(); //item six
	
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
	private ArrayStack<Integer>[] initializeStock(ArrayStack<Integer>[] stock)
	{
		for(int i = 0; i < stock.length; i++)
		{
			stock[i] = new ArrayStack<Integer>(1000);
		}
		return stock;
	}
	
	public void initialize(ArrayStack<Integer>[] stock)
	{
		initializeMenu();
		initializeStock(stock);
	}
	
	public void businessDay(ArrayStack<Integer>[] stock)
	{
		lostCustomerDay = 0;
		System.out.println("Beginning of day " + currentDate);
		if((currentDate - startDate)%3 == 0 || currentDate == startDate)
		{
			receiveShipment(rand.nextInt(301)+700, stock);
			System.out.println("Shipment complete");
		}
		CircularLinkedQueue<Integer> customers;
		for(int i = 0; i < 10; i++)
		{
			customers = customerArrival(rand.nextInt(100)+1, 50);
			processCustomers(customers, stock);
		}
		
		System.out.println("End of day");
		lostCustomerTotal += lostCustomerDay;
		sortStock(stock);
		removeExpiredStock(stock);
		currentDate++;
		/**System.out.println("Customers Lost: " + lostCustomerDay);
		System.out.println("Total Sold: "+ (countItemOne+countItemTwo+countItemThree+countItemFour+countItemFive+countItemSix));
		System.out.println("Burgers sold: " + countItemOne);
		System.out.println("Cheeseburgers sold: " + countItemTwo);
		System.out.println("Vegan Options sold: "+countItemThree);
		System.out.println("Burgers w/o Onion sold: " + countItemFour);
		System.out.println("Cheesburgers w/o Onion sold: " + countItemFive);
		System.out.println("Burgers w/o Tomato sold: " + countItemSix);
		System.out.println("Wasted Food Totals: Cheese: " + wasteCheese + " Buns: " + wasteBun + " Patties: " + wastePatty);
		System.out.println("Lettuce: " + wasteLettuce + " Tomato: " + wasteTomato + " Onion: " + wasteOnion);
		System.out.println("Total Customers Lost: " + lostCustomerTotal);
		*/
	}
	
	public CircularLinkedQueue<Integer> customerArrival(int numberOfCustomers, int limit)
	{
		//System.out.println(numberOfCustomers + " customers arrived");
		CircularLinkedQueue<Integer> customerQueue = new CircularLinkedQueue<Integer>();
		if(numberOfCustomers > limit)
		{
			//System.out.println((numberOfCustomers - limit) + " customers turned away");
			lostCustomerDay += (numberOfCustomers - limit);
			for(int i = limit; i > 0; i--)
				customerQueue.enqueue(rand.nextInt(6) + 1);
		}
		else
			for(int i = numberOfCustomers; i > 0; i--)
				customerQueue.enqueue(rand.nextInt(6) + 1);
		
		return customerQueue;
	}
	
	public void processCustomers(CircularLinkedQueue<Integer> customerQueue, ArrayStack<Integer>[] stock)
	{
		while(!customerQueue.isEmpty())
		{
			if(placeOrder(customerQueue.getFront(), stock))
			{
				int item = customerQueue.dequeue();
				switch (item) {
				case 1:
					countItemOne++;
					break;
				case 2:
					countItemTwo++;
					break;
				case 3:
					countItemThree++;
					break;
				case 4:
					countItemFour++;
					break;
				case 5:
					countItemFive++;
					break;
				case 6:
					countItemSix++;
					break;
				default:
					break;
				}
			}
			else{
				customerQueue.dequeue();
				lostCustomerDay++;
			}
		}
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
		System.out.println("Receiving a shipment of size " + shipmentSize);
		for(int i = 0; i < stock.length; i++)
		{
			int expireDate = currentDate;
			switch (i) 
			{
			case 0:
				expireDate+=5;
				System.out.println("Buns Expire On: " + expireDate);
				break;
			case 4:
				expireDate+=5;
				System.out.println("Onions Expire On: " + expireDate);
				break;
			case 1:
				expireDate+=4;
				System.out.println("Patties expire on: " + expireDate);
				break;
			case 2:
				expireDate+=3;
				System.out.println("Lettuce expires on: " + expireDate);
				break;
			case 3:
				expireDate+=3;
				System.out.println("Tomatos expire on: " + expireDate);
				break;
			case 5:
				expireDate+=2;
				System.out.println("Cheese expires on: " + expireDate);
				break;
			default:
				break;
			}
			//try{
			for(int j = 1; j <= shipmentSize; j++)
				stock[i].push(expireDate);
			/**}catch(IllegalStateException|ArrayIndexOutOfBoundsException a){
				sortStock(stock);
				removeExpiredStock(stock);
			}*/
		}
	}
	
	public void sortStock(ArrayStack<Integer>[] stock)
	{
		QuickSort sorter = new QuickSort();
		
		for(int i = 0; i < stock.length; i++)
		{
			if(!stock[i].isEmpty())
			{
				Integer[] tempArray = new Integer[stock[i].getLength()];
				for(int x = 0; x < tempArray.length; x++)
					tempArray[x] = stock[i].pop();
				sorter.printArray(tempArray);
				sorter.recursiveQuickSort(tempArray, 0, tempArray.length - 1);
			//	System.out.println(tempArray);
				for(int j = 0; j < tempArray.length; j++)
					stock[i].push(tempArray[j]);
			}
		}
	}
	
	private void incrementWaste(int i) {
		switch (i) {
		case 0:
			wasteBun++;
			break;
		case 1:
			wastePatty++;
			break;
		case 2:
			wasteLettuce++;
			break;
		case 3:
			wasteTomato++;
			break;
		case 4:
			wasteOnion++;
			break;
		case 5:
			wasteCheese++;
		default:
			break;
		}
	}

	private void removeExpiredStock(ArrayStack<Integer>[] stock)
	{
		for(int i = 0; i < stock.length; i++)
		{
			try{
				while((stock[i].peek().intValue() < currentDate) && !stock[i].isEmpty())
				{
					System.out.println("Removing Expired Product");
					stock[i].pop();
					incrementWaste(i);
				}
			}catch(EmptyStackException e){
				System.out.println("Empty Stack");
			}
		}
	}
	
	public static void main(String[] args) 
	{
		Inventory inv = new Inventory();
		@SuppressWarnings("unchecked")
		ArrayStack<Integer>[] stock = new ArrayStack[6];
		inv.initialize(stock);
		//while(inv.currentDate <= 330){
			inv.businessDay(stock);
			System.out.println();
		//}
	}
	
}
