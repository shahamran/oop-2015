

import java.util.Scanner;

import oop.ex4.data_structures.AvlTree;
import oop.ex4.data_structures.BstTree;

public class NewTest {
	private static Scanner scanner = new Scanner(System.in);
	private static final String OPTIONS = "options", DEFAULT = "default", COPY = "copy", DATA = "data", 
								END = "end", SIZE = "size", ADD = "add", REMOVE = "remove", CONTAINS = "contains",
								ITERATE = "iterate", BST = "bst", AVL = "avl", SEP = " ", INVALID = "!",VALID = "V", PRINT = "print";
	private static final int MODULU_FACTOR = 5000, TREE = 0, ACTION = 1, CONTENT = 2;
	private static BstTree myTree;
	private static BstTree bstTree;
	private static AvlTree avlTree;
	
	public static void main(String[] args) {
		System.out.println("Welcome. Type " + OPTIONS + " to print options.");
		constructTree();
		manageInput();
	}
	
	private static void manageInput() {
		String[] userInput;
		System.out.println("What do you want to do?");
		boolean shouldStop = false;
		while (!shouldStop) {
			scanner = new Scanner(System.in);
			userInput = scanner.nextLine().toLowerCase().split(SEP);
			String result = checkFirst(userInput);
			if (result == END) {
				shouldStop = true;
				continue;
			} else if (result == INVALID) {
				continue;
			}
			doAction(userInput);
		}
		
	}
	
	private static String checkFirst(String[] str) {
		if (str == null || str.length == 0) {
			return INVALID;
		}
		switch (str[TREE]) {
		case AVL:
			myTree = avlTree;
			break;
		case BST:
			myTree = bstTree;
			break;
		case END:
			return END;
		case PRINT:
			System.out.println("FORMAT: <TREE> <ACTION> <CONTENT>");
			return INVALID;
			default:
				return INVALID;
		}
		
		return VALID;
	}
	
	private static void doAction(String[] str) {
		try {
			switch (str[ACTION]) {
			case ADD:
				addToTree(str[CONTENT]);
				break;
			case REMOVE:
				removeFromTree(str[CONTENT]);
				break;
			case SIZE:
				System.out.println(myTree.size());
				break;
			case ITERATE:
				iterateOnTree();
				break;
			case CONTAINS:
				checkContains(str[CONTENT]);
				break;
				default:
					invalidInput();
			}	
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			invalidInput();
		}
		
	}
	
	private static void invalidInput() {
		System.out.println("Invalid Input.");
		System.out.println("FORMAT: <TREE> <ACTION> (<CONTENT>)");
	}
	
	private static int getVal(String str) {
		int userInt;
		try {
			userInt = Integer.parseInt(str);
		} catch (Exception e) {
			userInt = str.hashCode() % MODULU_FACTOR;
		}
		return userInt;
	}
	
	private static void addToTree(String input) {
		System.out.println(myTree.add(getVal(input)));
	}
	
	private static void removeFromTree(String input) {
		System.out.println(myTree.delete(getVal(input)));
	}
	
	private static void checkContains(String input) {
		System.out.println(myTree.contains(getVal(input)));
	}
	
	private static void iterateOnTree() {
		for (int val : myTree) {
			System.out.println(val);
		}
	}
	
	private static void constructTree() {
		String constructor,msg;
		String[] valid;
		
		msg = "Which type of constructor?";
		valid = new String[3]; valid[0] = DEFAULT; valid[1] = COPY; valid[2] = DATA;
		constructor = getInput(msg,valid);
		switch (constructor) {
		case DEFAULT:
			bstTree = new BstTree();
			avlTree = new AvlTree();
			break;
		case DATA:
			int[] data = getData();
			bstTree = new BstTree(data);
			avlTree = new AvlTree(data);
			break;
			
			default:
				bstTree = new BstTree();
				avlTree = new AvlTree();
				break;	
		}
	}
	
	private static int[] getData() {
		System.out.println("Enter numbers line by line.");
		System.out.println("When finished, type 'end'");
		int[] output = null;
		int count = 0, userInt;
		String userInput = "";
		while (!userInput.toLowerCase().equals(END)) {
			userInput = scanner.nextLine();
			if (userInput.toLowerCase().equals(END)) {
				if (output == null) {
					output = new int[1];
					output[0] = 0;
				}
				scanner = new Scanner(System.in);
				return output;
			}
			
			userInt = getVal(userInput);
			count++;
			int[] oldOut = output;
			output = new int[count];
			for (int i = 0; i < count-1; i++) {
				output[i] = oldOut[i];
			}
			output[count-1] = userInt;
		}
		return null;
	}


	private static String getInput(String msg, String[] validInputs) {
		scanner = new Scanner(System.in);
		System.out.println(msg);
		String input;
		boolean isValid = false;
		while(!isValid) {
			input = scanner.nextLine();
			if (input.equals("options")) {
				printOptions(validInputs);
				continue;
			}
			for (String str : validInputs) {
				if (str != null) {
					if (str.equals(input))
						return input;
				}
			}
			System.out.println("Invalid input.");
		}
		return null;
	}

	private static void printOptions(String[] options) {
		String sep = " ";
		String output = "";
		for (String opt : options) {
			output += opt + sep;
		}
		System.out.println(output);
	}

}
