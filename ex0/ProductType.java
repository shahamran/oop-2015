/**
 * A product class. Consists of a name, a price sold to a store and a
 * price sold to a customer.
 * @author Ran Shaham
 */
class ProductType {
    //Constants
    final String OPENER = "[";
    final String CLOSER = "]";
    final String SEPERATOR = ",";

    String name;
    int customerPrice;
    int storePrice;

    /**
     * Constructs a new ProductType.
     * @param productName          the name of the product
     * @param productStorePrice    the price the store buys it
     * @param productCustomerPrice the price a customer buys is
     */
    ProductType(String productName, int productStorePrice,
                                    int productCustomerPrice){
        name = productName;
        customerPrice = productCustomerPrice;
        storePrice = productStorePrice;
    }

    /**
     * @return the String representation of this ProductType object.
     */
    String stringRepresentation(){
        return OPENER + name + SEPERATOR + storePrice + 
               SEPERATOR + customerPrice + CLOSER;
    }

    /**
     * @return The profit per unit of this product type.
     */
    int profitPerUnit(){
        return customerPrice - storePrice;
    }

}
