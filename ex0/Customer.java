/**
 * A customer class. Consists of a name, an address a balance and a log.
 * Can buy items (ProductTypes).
 * @author Ran Shaham
 * @see ProductType.java
 */
class Customer {
    //Constant Strings
    final String OPENER = "[";
    final String CLOSER = "]";
    final String SEPERATOR = ",";
    final String SPACE = " ";
    final String NEW_LINE = "\n";

	String name;
	String address;
	int balance;
	String log;

    /**
     * Constructs a new Customer with the given parameters.
     * @param customerName     the name of the customer
     * @param customerAddress  the address of the customer's home
     * @param customerBalance  the amount of money the customer has
     */
	Customer(String customerName, String customerAddress, int customerBalance){
		name = customerName;
        address = customerAddress;
        balance = customerBalance;
        log = "Shopping log for customer: " + name;
	}

    /**
     * @return the String representation of this Customer object.
     */
    String stringRepresentation(){
        return OPENER + name + SEPERATOR + address
                    + SEPERATOR + balance + CLOSER;
    }

    /**
     * Checks whether this customer can affort to buy the given quantity of
     * units of products of the given ProductType.
     * @param  quantity The number of units to check
     * @param  productType The type of product to check
     * @return true if this customer can afford this quantity of product
     *         false otherwise
     */
    boolean canAfford(int quantity, ProductType productType){
        int price = quantity * productType.customerPrice;
        return balance >= price;
    }

    /**
     * Checks what is the maximum amount of products from a certain
     * type can be bought by this customer.
     * @param  productType The type of product to check
     * @return The number of productType items that could be bought
     */
    int maximumAffordableQuantity(ProductType productType){
        // Making sure the returned value is an integer
        int amount = balance / productType.customerPrice;
        return amount;
    }

    /**
     * @return A String that consists of successful purchases made by this
     *         customer.
     */
    String getPurchaseLog(){
        return log;
    }

    /**
     * Checks if this customer can buy a given amount of a given product.
     * Updates the customer's balance if a purchase can be made, otherwise
     * does nothing.
     * @param  quantity    The number of units to buy
     * @param  productType The type of product to buy
     */
    void makePurchase(int quantity, ProductType productType){
        if (quantity > 0 && canAfford(quantity, productType)) {
            // The price is the number of items bought * the price of one item
            balance -= (quantity * productType.customerPrice);
            addToLog(quantity, productType);
        }
    }

    /**
     * Adds the correct data to the customer's log - number of items bought
     * and what kind of item was it.
     * @param  quantity    The number of units bought
     * @param  productType The type of product that was bought
     */
    void addToLog(int quantity, ProductType productType){
        log += NEW_LINE + quantity + SPACE + productType.name;
    }
	
}
