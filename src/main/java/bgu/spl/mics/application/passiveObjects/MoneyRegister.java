package bgu.spl.mics.application.passiveObjects;


import java.io.File;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Passive object representing the store finance management. 
 * It should hold a list of receipts issued by the store.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private fields and methods to this class as you see fit.
 */
public class MoneyRegister {
	private static volatile MoneyRegister instance = null;
	private static Object mutex = new Object();
	private static ConcurrentLinkedQueue<OrderReceipt> receiptList = new ConcurrentLinkedQueue<>();

	/**
     * Retrieves the single instance of this class.
     */
	public static MoneyRegister getInstance() {
		MoneyRegister result = instance;
		if (result == null){
			synchronized (mutex){
				result = instance;
				if (result == null){
					instance = result = new MoneyRegister();
				}
			}
		}
		return result;
	}
	
	/**
     * Saves an order receipt in the money register.
     * <p>   
     * @param r		The receipt to save in the money register.
     */
	public void file (OrderReceipt r) {
		receiptList.add(r);
	}
	
	/**
     * Retrieves the current total earnings of the store.  
     */
	public int getTotalEarnings() {
		synchronized (this) {
			int sum = 0;
			for (OrderReceipt receipt : receiptList) {
				sum += receipt.getPrice();
			}
			return sum;
		}
	}
	
	/**
     * Charges the credit card of the customer a certain amount of money.
     * <p>
     * @param amount 	amount to charge
     */
	public void chargeCreditCard(Customer c, int amount) {
		for(OrderReceipt orderReceipt : receiptList){
			if (orderReceipt.getCustomerId() == c.getId())
				c.chargeCredit(amount);
		}
	}
	
	/**
     * Prints to a file named @filename a serialized object List<OrderReceipt> which holds all the order receipts 
     * currently in the MoneyRegister
     * This method is called by the main method in order to generate the output.. 
     */
	public void printOrderReceipts(String filename) {
		File file = new File(filename);
		synchronized (file) {
			//TODO: Implement this
		}
	}
}
