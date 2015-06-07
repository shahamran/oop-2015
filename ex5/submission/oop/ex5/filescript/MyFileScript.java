package filescript;

import java.io.File;
import java.util.Arrays;

import filescript.parsing.*;
import filescript.sections.*;
import filescript.sections.filters.*;
import filescript.sections.orders.*;

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
		}
		
		Section[] sections = null;
		File 	commandFile = new File(args[COMMAND_FILE_ARG]),
				dir = new File(args[SOURCE_DIR_ARG]);
		if (! (dir.exists() && dir.isDirectory() && commandFile.exists() && commandFile.isFile()) ) {
			handleTypeTwoException(new BadScriptArgumentsException());
		}
		
		File[] filesList = dir.listFiles();
		
		try {
			FileParser parser = new FileParser(commandFile);
			sections = parser.getSections();
		} catch (TypeTwoException t2) {
			handleTypeTwoException(t2);
		} catch (Exception otherException) {
			otherException.printStackTrace();
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
	private static void handleSection(File[] filesList, Section section) {
		// Print warnings.
		for (TypeOneException warning : section.getWarnings()) {
			handleTypeOneException(warning);
		}
		// Extracts Filter and Order from the section
		Filter filter = section.getFilter(); Order order = section.getOrder();
		File[] relevantFiles = getRelevantFiles(filesList, filter);
		// Sorts the files using the *order* comparator, (O(nlogn) sorting according to the API).
		Arrays.sort(relevantFiles, order);
		for (File f : relevantFiles) {
			System.out.println(f.getName());
		}
	}
	
	/**
	 * Returns a list of only the files that pass the given filter.
	 * @param filesList The list of all files.
	 * @param filter The filter object.
	 * @return A list of files that pass the filter.
	 */
	private static File[] getRelevantFiles(File[] filesList, Filter filter) {
		File[] relevantFiles = new File[filesList.length];
		File currFile;
		int filesNum = 0;
		for (int i = 0; i < filesList.length; i++) {
			currFile = filesList[i];
			if (currFile == null)
				continue;
			if (currFile.exists() && currFile.isFile()) {
				if (filter.isPass(currFile)) {
					relevantFiles[filesNum] = currFile;
					filesNum++;
				}
			}
		}
		// Copy the list of relevant files to a list in the right size.
		File[] finalList = new File[filesNum];
		for (int i = 0; i< filesNum; i++) {
			finalList[i] = relevantFiles[i];
		}
		return finalList;
	}
	
	/**
	 * This methods handles Type II Exceptions.
	 * @param e The exception object.
	 */
	private static void handleTypeTwoException(TypeTwoException e) {
		System.err.println(ERROR_MSG);
		System.exit(1);
	}
	
	/**
	 * Handles Type I Exceptions (prints a warning).
	 * @param e The exception object.
	 */
	private static void handleTypeOneException(TypeOneException e) {
		System.err.println(WARNING_MSG + e.getLineNumber());
	}
}
