import java.util.Scanner;


public class ChainedTest {
	private static Scanner scanner = new Scanner(System.in);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String userInput = "";
		ChainedHashSet mySet;
		int userInt;
		System.out.println("Which Type of constructor?");
	
		userInt = scanner.nextInt();
		switch (userInt) {
		case 1:
			mySet = new ChainedHashSet();
			break;
		case 2:
			System.out.println("Which load factors? (UP,DOWN)");
			int upFactor,lowFactor;
			upFactor = scanner.nextInt();
			lowFactor = scanner.nextInt();
			mySet = new ChainedHashSet(upFactor,lowFactor);
			break;
		default:
			mySet = new ChainedHashSet();
		}
		while (!userInput.equals("exit")) {
			System.out.println("What to do? add, remove, contains");
			userInput = scanner.nextLine();
			switch (userInput) {
			case "add":
				userInput = scanner.nextLine();
				System.out.println(mySet.add(userInput));
				break;
			case "remove":
				userInput = scanner.nextLine();
				System.out.println(mySet.delete(userInput));
				break;
			case "contains":
				userInput = scanner.nextLine();
				System.out.println(mySet.contains(userInput));
				break;
			case "load":
				System.out.println(mySet.loadFactor);
				break;
			case "size":
				System.out.println(mySet.size());
				break;
			case "capacity":
				System.out.println(mySet.capacity());
				break;
			case "print":
				System.out.println("SIZE: " + mySet.mySize);
				System.out.println("CAPACITY: " + mySet.capacity());
				System.out.println("LOAD: " + mySet.loadFactor);
			}
		}
	}
}


