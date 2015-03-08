
class Store {
	
    final String OPENER = "[";
    final String CLOSER = "]";
    final String SEP = ",";
	final int MAX_NUM_OF_PRODUCT_TYPES = 5;
	int balance;			
	ProductType[] productTypeArray;
    int currentNumOfTypes = 0;

	Store() {
		balance = 0;
		productTypeArray = new ProductType[MAX_NUM_OF_PRODUCT_TYPES];
	}

    String stringRepresentation(){
        String sentence;
        sentence = "Store has a balance of " + balance + ", and the following products:";
        for (int i = 0; i < currentNumOfTypes; i++){
            sentence += "\n" + productTypeArray[i].stringRepresentation();
        }
    }

    boolean addProductType(ProductType productType){
        int length = productTypeArray.length();
        boolean success = false;
        if (length < MAX_NUM_OF_PRODUCT_TYPES){
            productTypeArray[length] = productType;
            success = true;
        }
        return success;
    }

    boolean sellsProductsOfType(String productTypeName){
        for (int i = 0; i < productTypeArray.length(); i++){
            if (productTypeArray[i].name == productTypeName){
                return true;
            }
        }
        return false;
    }

}
