
class ProductType {

    String name;
    int customerPrice;
    int storePrice;
    final String OPENER = "[";
    final String CLOSER = "]";
    final String SEP = ",";

    ProductType(String productName,
                int productStorePrice,
                int productCustomerPrice){
        name = productName;
        customerPrice = productCustomerPrice;
        storePrice = productStorePrice;
    }

    String stringRepresentation(){
        return OPENER + name + SEP + storePrice + \
               SEP + customerPrice + CLOSER;
    }
    
    int profitPerUnit(){
        return customerPrice - storePrice;
    }

}
