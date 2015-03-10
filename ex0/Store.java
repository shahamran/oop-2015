/**
 * A store. Contains a certain amount of products, earns money by
 * selling those products with a higher price than bought from supplier. 
 * @author Ran Shaham
 * @see Customer.java
 * @see ProductType.java
 */
class Store {
	// Constants
    final String NEW_LINE = "\n";
	final int MAX_NUM_OF_PRODUCT_TYPES = 5;

	int balance;			
	ProductType[] productTypeArray;
    // this variable keeps track of the index of an empty spot in the array.
    int currentEmptySpot;

    /**
     * Constructs a new Store object with default values.
     */
	Store() {
		balance = 0;
		productTypeArray = new ProductType[MAX_NUM_OF_PRODUCT_TYPES];
        currentEmptySpot = 0;
        // Initialize the array to be empty (full of 'null's)
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            productTypeArray[i] = null;
        }
	}

    /**
     * Returns string representation of this store that includes its' balance
     * and representation of every product it sells.
     * @return the String representation of this Store object.
     */
    String stringRepresentation(){
        String sentence;
        sentence = "Store has a balance of " + balance + 
                   ", and the following products:";
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            if ( productTypeArray[i] == null )
                continue;
            sentence += NEW_LINE + productTypeArray[i].stringRepresentation();
        }
        return sentence;
    }
    
    /**
     * Checks whether a product type can be added to the store and if so,
     * returns the index in the array of products in which it can be added.
     * Otherwise, returns the max number of allowed products in this store.
     * @return an index of an empty spot in productTypeArray
     */
    int findEmptySpot(){
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            if ( productTypeArray[i] == null ){
                return i;
            }
        }
        return MAX_NUM_OF_PRODUCT_TYPES;
    }

    /**
     * Attempts to add a product to the store. Will succeed if the store is
     * not full and this product isn't yet sold by this store.
     * @param productType The type of product to add to this store
     * @return true if the product was added, false otherwise
     */
    boolean addProductType(ProductType productType){
        boolean success = false;
        // the currentEmptySpot indicates if the store is 'full' or not
        if ( (currentEmptySpot < MAX_NUM_OF_PRODUCT_TYPES) &&
                   !(sellsProductsOfType(productType.name)) ){
            productTypeArray[currentEmptySpot] = productType;
            success = true;
            // If a type was added, the empty spot has changed.
            currentEmptySpot = findEmptySpot();
        }
        return success;
    }

    /**
     * Attempts to remove a product type from this store. Will succeed if
     * this store (productTypeArray) contains a product with the given name.
     * @param productTypeName  the name of the product to be removed
     * @return  true if the product was removed, false otherwise
     */
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
        // reached if the store doesn't sell a product with the given name
        return false;
    }

    /**
     * Checks whether this store sells a product with the given name.
     * @param productTypeName The name of the product to be checked
     * @return true if the store sells this product, false otherwise
     */
    boolean sellsProductsOfType(String productTypeName){
        for (int i = 0; i < MAX_NUM_OF_PRODUCT_TYPES; i++){
            if (productTypeArray[i] == null)
                continue;
            if (productTypeArray[i].name.equals(productTypeName) ){
                return true;
            }
        }
        // reached if the store doesn't sell a product with the given name
        return false;
    }

    /**
     * Returns the ProductType in this store with the given name.
     * If it doesn't exist, returns null.
     * @param productTypeName name of the product to be returned
     * @return the ProductType with the given name in this store
     */
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

    /**
     * Attempts to 'sell' a product to a customer. If the customer can afford
     * the item, the purchase is made and the store balance is changed.
     * @param customer the customer to whom the product is sold.
     * @param productTypeName the name of product to be sold to the customer
     * @param quantity the number of the given product to be sold
     * @return the actual quantity of the product that was sold
     */
    int makePurchase(Customer customer, String productTypeName, int quantity){
        if ( !sellsProductsOfType(productTypeName) )
            return 0;

        ProductType theProduct = getProduct(productTypeName);
        /* the actual quantity to be sold is the minimum of the number of
           products the customer CAN buy, and the number he WANTS to buy. */
        int actual = Math.min(quantity, 
                              customer.maximumAffordableQuantity(theProduct));
        // if the customer can't afford even 1, does nothing.
        if ( actual > 0 ){
            customer.makePurchase(actual, theProduct);
            balance += actual * theProduct.profitPerUnit();
        } 
        return actual;
    }

}
