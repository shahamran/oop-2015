package oop.ex5.filescript;

import java.io.File;
import java.util.*;

import oop.ex5.filescript.parsing.*;
import oop.ex5.filescript.sections.*;
import oop.ex5.filescript.sections.filters.*;
import oop.ex5.filescript.sections.orders.*;


/**
 * This class acts as the manager of this project. It composes most of the other classes that I wrote
 * in this exercise and uses them as instructed.
 * @author ransha
 *
 */
public class MyFileScript {
	// Some constants
	private static final int SOURCE_DIR_ARG = 0, COMMAND_FILE_ARG = 1, EXPECTED_ARGS = 2;
	private static final String ERROR_MSG = "ERROR", WARNING_MSG = "Warning in line ";
	
	public static void main(String[] args) {
		if (args.length != EXPECTED_ARGS) {
			// Anything other than 2 arguments given results in an ERROR
			handleTypeTwoException(new BadScriptArgumentsException());
			return;
		}
		
		List<Section> sections = null;
		File 	commandFile = new File(args[COMMAND_FILE_ARG]),
				        dir = new File(args[SOURCE_DIR_ARG]);
		// Check if the arguments are valid (a directory and a file)
		if (! (dir.exists() && dir.isDirectory() && commandFile.exists() && commandFile.isFile()) ) {
			handleTypeTwoException(new BadScriptArgumentsException());
			return;
		}
		
		ArrayList<File> filesList = new ArrayList<>();
		Collections.addAll(filesList, dir.listFiles());
		
		try {
			FileParser parser = new FileParser(commandFile);
			sections = parser.getSections();
		} catch (TypeTwoException t2) {
			handleTypeTwoException(t2);
			return;
		} catch (Exception otherException) {
			return;
		}
		for (Section section : sections) {
			handleSection(filesList,section);
		}
	}
	
	/**
	 * 
	 * @param filesList The files in the directory
	 * @param section The section that contains the filter, order and warnings.
	 */
	private static void handleSection(List<File> filesList, Section section) {
		// Print warnings.
		for (TypeOneException warning : section.getWarnings()) {
			handleTypeOneException(warning);
		}
		// Extracts Filter and Order from the section
		Filter filter = section.getFilter(); Order order = section.getOrder();
		filesList = getRelevantFiles(filesList, filter);
		// Sorts the files using the *order* comparator, (O(nlogn) sorting according to the API).
		Collections.sort(filesList, order);
		for (File f : filesList) {
			System.out.println(f.getName());
		}
	}
	
	/**
	 * Returns a list of only the files that pass the given filter.
	 * @param filesList The list of all files.
	 * @param filter The filter object.
	 * @return A list of files that pass the filter.
	 */
	private static List<File> getRelevantFiles(List<File> filesList, Filter filter) {
		List<File> newList = new ArrayList<>();
		for (File f : filesList) {
			if (f.exists() && f.isFile() && filter.isPass(f) ) {
				newList.add(f);
			}
		}
		return newList;
	}
	
	/**
	 * This methods handles Type II Exceptions.
	 * @param e The exception object.
	 */
	private static void handleTypeTwoException(TypeTwoException e) {
		System.err.println(ERROR_MSG);
	}
	
	/**
	 * Handles Type I Exceptions (prints a warning).
	 * @param e The exception object.
	 */
	private static void handleTypeOneException(TypeOneException e) {
		System.err.println(WARNING_MSG + e.getLineNumber());
	}
}
