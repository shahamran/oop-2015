import java.util.Scanner;

/**
 * @author ransha
 *
 */
public class OpenHashTest {
	private static SimpleHashSet mySet;
	private static Scanner scanner = new Scanner(System.in);
	private static String[] validValues = {"Add","Remove","Contains","Size","Capacity","Load","Exit", "Print"};
	
	public static String getInput(String msg,String[] validValues) {
		
		String userInput = null;
		boolean validInput = false;
		while (!validInput) {
			System.out.println(msg);
			userInput = scanner.nextLine();
			for (String value : validValues) {
				if (value.toLowerCase().equals(userInput)) {
					return userInput;
				}
			}
			System.out.println("Invalid Input!");
		}
		return userInput;
	}
	
	
	public static void main(String[] args) {
		String userInput = "",msg,classType,constructorType;
		String[] values = {"chained","open"};
		msg = "Which class would you like to test? chained / open";
		classType = getInput(msg, values).toLowerCase();
		msg = "What type of constructor? 1 = default; 2 = up/down load factors";
		values[0] = "1"; values[1] = "2";
		constructorType = getInput(msg, values);
		float upperLoad = 0.75f, lowerLoad = 0.25f;
		if (constructorType.equals("2")) {
			System.out.println("Enter load factors: Upper 'enter' Lower.");
			upperLoad = scanner.nextFloat();
			lowerLoad = scanner.nextFloat();
		}
		if (classType.equals("chained")) {
			mySet = new ChainedHashSet(upperLoad,lowerLoad);
		} else {
			mySet = new OpenHashSet(upperLoad,lowerLoad);
		}
		
		scanner = new Scanner(System.in);
		boolean shouldStop = false;
		System.out.println("Type exit to exit.");
		while (!shouldStop) {
			userInput = getInput("What to do? add/remove/contains/print/load/size/capacity",validValues);
			switch (userInput.toLowerCase()) {
			case "add":
				System.out.print("Add: ");
				userInput = scanner.nextLine();
				System.out.println("Result: " + mySet.add(userInput));
				break;
			case "remove":
				System.out.print("Remove: ");
				userInput = scanner.nextLine();
				System.out.println("Result: " + mySet.delete(userInput));
				break;
			case "contains":
				System.out.print("Contains: ");
				userInput = scanner.nextLine();
				System.out.println("Result: " + mySet.contains(userInput));
				break;
			case "load":
				System.out.println(mySet.size() / mySet.capacity()); 
				break;
			case "size":
				System.out.println(mySet.size());
				break;
			case "capacity":
				System.out.println(mySet.capacity());
				break;
			case "print":
				System.out.println("SIZE: " + mySet.size());
				System.out.println("CAPACITY: " + mySet.capacity());
				System.out.println("LOAD: " + (float) mySet.size() / mySet.capacity());
				break;
			case "exit":
				shouldStop = true;
			}
			
		}
		
		
		
	}
}
