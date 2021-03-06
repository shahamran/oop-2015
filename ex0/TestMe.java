import java.util.Scanner;

class TestMe {
    static String PRODUCT = "product";
    static String CUSTOMER = "customer";
    static String STORE = "store";
    static ProductType aProduct;
    static Store myStore = new Store();
    private static Scanner scanner = new Scanner( System.in );

    public static void main(String[] args){
        String input = "";
        while ( !input.equals("end") ){
            System.out.println("Which class do you want to run?");
            input = scanner.nextLine();
            if ( input.equals(PRODUCT) ){
                testProduct();
            } else if ( input.equals(CUSTOMER) ){
                testCustomer();
            } else if ( input.equals(STORE) ){
                testStore();
            }
        }
    }

    static void testProduct(){
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter store price:");
        int storePrice = Integer.valueOf( scanner.nextLine() );
        System.out.println("Enter customer price:");
        int customerPrice = Integer.valueOf( scanner.nextLine() );

        ProductType myProduct = new ProductType(name, storePrice, customerPrice);
        aProduct = myProduct;
        myStore.addProductType(myProduct);
        System.out.println( "Representation: " + myProduct.stringRepresentation() );
        System.out.println( "ProfitPerUnit: " + myProduct.profitPerUnit() );
    }

    static void testCustomer(){
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter address:");
        String address = scanner.nextLine();
        System.out.println("Enter balance:");
        int balance = Integer.valueOf( scanner.nextLine() );

        Customer myCustomer = new Customer(name, address, balance);
        System.out.println( "Representation: " + myCustomer.stringRepresentation() );
        System.out.println( "How many " + aProduct.name + " do you want to buy?" );
        int quantity = Integer.valueOf( scanner.nextLine() );
        System.out.println( "can afford: " + myCustomer.canAfford(quantity,aProduct) );
        int maxQuantity = myCustomer.maximumAffordableQuantity(aProduct);
        System.out.println( "Maximum affordable: " + maxQuantity);
        myCustomer.makePurchase(maxQuantity / 2, aProduct);
        System.out.println( myCustomer.getPurchaseLog() );
    }
    
    static void testStore(){

    }
}
