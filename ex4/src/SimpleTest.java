

import java.util.Scanner;
import oop.ex4.data_structures.*;

public class SimpleTest {
	private static Scanner scanner = new Scanner(System.in);
	private static final String OPTIONS = "options", DEFAULT = "1", COPY = "2", DATA = "3", END = "end", SIZE = "size";
	private static final String ADD = "add", REMOVE = "remove", CONTAINS = "contains", ITERATE = "iterate";
	private static final String BST = "bst", AVL = "avl";
	
	public static void main(String[] args) {
		BstTree myTree;
		System.out.println("Welcome. Type " + OPTIONS + " to print options.");
		myTree = constructTree();
		String userInput = "";
		while (!userInput.toLowerCase().equals(END)) {
			userInput = manageInput(myTree);
		}
		
	}
	
	private static String manageInput(BstTree myTree) {
		String userInput,msg;
		msg = "What do you want to do?";
		String[] valid = {ADD,REMOVE,CONTAINS,ITERATE,SIZE,END};
		userInput = getInput(msg,valid);
		switch (userInput) {
		case ADD:
			addToTree(myTree);
			break;
		case REMOVE:
			removeFromTree(myTree);
			break;
		case CONTAINS:
			checkContains(myTree);
			break;
		case ITERATE:
			iterateOnTree(myTree);
			break;
		case SIZE:
			System.out.println(myTree.size());
			break;
		case END:
			return END;
		}
		return "";
	}
	
	private static void addToTree(BstTree myTree) {
		int userInput;
		System.out.print("Enter a number to add: ");
		userInput = scanner.nextInt();
		System.out.println(myTree.add(userInput));
	}
	
	private static void removeFromTree(BstTree myTree) {
		int userInput;
		System.out.print("Enter a number to remove: ");
		userInput = scanner.nextInt();
		System.out.println(myTree.delete(userInput));
	}
	
	private static void checkContains(BstTree myTree) {
		int userInput;
		System.out.print("Enter a number to check: ");
		userInput = scanner.nextInt();
		System.out.println(myTree.contains(userInput));
	}
	
	private static void iterateOnTree(BstTree myTree) {
		for (int val : myTree) {
			System.out.println(val);
		}
	}
	
	private static BstTree constructTree() {
		String constructor,treeType,msg;
		String[] valid;
		BstTree myTree = null;
		
		msg = "Which type of constructor?";
		valid = new String[3]; valid[0] = DEFAULT; valid[1] = COPY; valid[2] = DATA;
		constructor = getInput(msg,valid);
		msg = "Which type of tree?";
		valid = new String[2]; valid[0] = BST; valid[1] = AVL;
		treeType = getInput(msg, valid);
		if (treeType.equals(BST)) {
			switch (constructor) {
			case DEFAULT:
				myTree = new BstTree();
				break;
			case COPY:
				myTree = new BstTree(buildNewAvl());
				break;
			case DATA:
				myTree = new BstTree(getData());
				break;
			}
		} else if (treeType.equals(AVL)) {
			switch (constructor) {
			case DEFAULT:
				myTree = new AvlTree();
				break;
			case COPY:
				myTree = new AvlTree(buildNewAvl());
				break;
			case DATA:
				myTree = new AvlTree(getData());
				break;
			}
		}
		return myTree;
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
			try {
				userInt = Integer.parseInt(userInput);
				count++;
				int[] oldOut = output;
				output = new int[count];
				for (int i = 0; i < count-1; i++) {
					output[i] = oldOut[i];
				}
				output[count-1] = userInt;
			} catch (Exception e) {
				System.out.println("Invalid input.");
			}
		}
		return null;
	}

	private static AvlTree buildNewAvl() {
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
