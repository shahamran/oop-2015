package oop.ex4.data_structures;

import java.util.Scanner;

public class SimpleTest {
	private static Scanner scanner = new Scanner(System.in);
	private static final String OPTIONS = "options", DEFAULT = "1", COPY = "2", DATA = "3";
	
	public static void main(String[] args) {
		AvlTree avlTree;
		String userInput,msg;
		System.out.println("Welcome. Type " + OPTIONS + " to print options.");
		msg = "Which type of constructor?";
		String[] valid = {DEFAULT, COPY, DATA};
		userInput = getInput(msg,valid);
		switch (userInput) {
		case DEFAULT:
			avlTree = new AvlTree();
			break;
		case COPY:
			avlTree = new AvlTree(buildNewAvl());
			break;
		case DATA:
			avlTree = new AvlTree(getData());
		}
		
	}
	
	private static int[] getData() {
		int[] output = {0};
		String END = "end";
		int count = 0, userInt;
		String userInput = "";
		while (!userInput.toLowerCase().equals(END)) {
			userInput = scanner.nextLine();
			if (userInput.toLowerCase().equals(END)) {
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
			} catch Exception {
				
			}
		}
		return null;
	}

	private static AvlTree buildNewAvl() {
		return null;
	}

	private static String getInput(String msg, String[] validInputs) {
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
