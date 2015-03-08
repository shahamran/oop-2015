
class Customer {
	
	String name;
	String address;
	int balance;
	String log;
    String OPENER = "[";
    String CLOSER = "]";
    String SEP = ",";
    String SPACE = " ";

	Customer(String customerName, String customerAddress, int customerBalance){
		name = customerName;
        address = customerAddress;
        balance = customerBalance;
        log = "Shopping log for customer:" + name;
	}

	int maximumAffordableQuantity(ProductType productType){
		return balance / productType.customerPrice;
	}

    String stringRepresentation(){
        return OPENER + name + SEP + address + SEP + balance + CLOSER;
    }

    boolean canAfford(int quantity, ProductType productType){
        int price = quantity * productType.customerPrice;
        return balance >= price;
    }

    int maximumAffordableQuantity(ProductType productType){
        int quantity = balance / productType.customerPrice;
        if (quantity < 0){
            quantity = 0;
        }
        return quantity;
    }

    String getPurchaseLog(){
        return log;
    }

    void makePurchase(int quantity, ProductType productType){
        if (quantity > 0 && canAfford(quantity, productType)) {
            int price = quantity * productType.customerPrice;
            balance -= price;
            addToLog(quantity, productType);
        }
    }

    void addToLog(int quantity, ProductType productType){
        String ending = "";
        if (quantity > 1){
            ending = "s";
        }
        log += "\n" + quantity + SPACE + productType.name + ending;
    }
	
}
