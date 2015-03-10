
class Store {
	
    final String OPENER = "[";
    final String CLOSER = "]";
    final String SEP = ",";
	final int MAX_NUM_OF_PRODUCT_TYPES = 5;
	int balance;			
	ProductType[] productTypeArray;
    int currentEmptySpot = 0;

	Store() {
		balance = 0;
		productTypeArray = new ProductType[MAX_NUM_OF_PRODUCT_TYPES];
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            productTypeArray[i] = null;
        }
	}

    String stringRepresentation(){
        String sentence;
        sentence = "Store has a balance of " + balance + ", and the following products:";
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            if ( productTypeArray[i] == null )
                continue;
            sentence += "\n" + productTypeArray[i].stringRepresentation();
        }
        return sentence;
    }

    int findEmptySpot(){
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            if ( productTypeArray[i] == null ){
                return i;
            }
        }
        return MAX_NUM_OF_PRODUCT_TYPES;
    }

    boolean addProductType(ProductType productType){
        boolean success = false;
        if ( (currentEmptySpot < MAX_NUM_OF_PRODUCT_TYPES) &&
             !(sellsProductsOfType(productType.name)) ){
            productTypeArray[currentEmptySpot] = productType;
            success = true;
        }
        currentEmptySpot = findEmptySpot();
        return success;
    }

    boolean removeProductTypeFromStore(String productTypeName){
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            if (productTypeArray[i] == null)
                continue;
            if ( productTypeArray[i].name.equals(productTypeName) ){
                productTypeArray[i] = null;
                currentEmptySpot = i;
                return true;
            }
        }
        return false;
    }

    boolean sellsProductsOfType(String productTypeName){
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            if (productTypeArray[i] == null)
                continue;
            if (productTypeArray[i].name.equals(productTypeName) ){
                return true;
            }
        }
        return false;
    }

    ProductType getProduct(String productTypeName){
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            if (productTypeArray[i] == null)
                continue;
            if ( productTypeName.equals(productTypeArray[i].name) ){
                return productTypeArray[i];
            }
        }
        return null;
    }

    int makePurchase(Customer customer, String productTypeName, int quantity){
        if ( !sellsProductsOfType(productTypeName) ){
            return 0;
        }
        ProductType theProduct = getProduct(productTypeName);
        int actual = Math.min( quantity, customer.maximumAffordableQuantity(theProduct) );
        if ( actual > 0 ){
            customer.makePurchase(actual, theProduct);
            balance += actual * theProduct.profitPerUnit();
        } 
        return actual;
    }
}
