package filescript.parsing;

import filescript.*;
import filescript.sections.*;
import filescript.sections.filters.*;
import filescript.sections.orders.*;

import java.io.*;

/**
 * This class reads the command file and extract the correct FILTER and ORDER objects from it.
 * @author ransha
 *
 */
public class FileParser {
	private String[] fileContent = {};
	private int currentLine = 0;
	private boolean moreSectionsToParse = true;
	
	private static final char NEW_LINE = '\n', IGNORE = '\r'; // This char caused me some trouble...
	private static final int MIN_ARGS_PER_SECTION = 2;
	private static final String FILTER = "FILTER",ORDER = "ORDER", SEPERATOR = "#";
	
	/**
	 * Creates a new FileParser object - reads the given file and extracts its lines.
	 * @param inFile The command file to parse.
	 * @throws IOException
	 */
	public FileParser(File inFile) throws IOException {
		fileContent = readFile(inFile);
	}
	
	/**
	 * @return An array of sections as read from the commands file.
	 * @throws FileScriptException
	 */
	public Section[] getSections() throws FileScriptException {
		// Estimate the maximum number of sections in this file.
		int maxNumOfSections = (int) Math.ceil(fileContent.length / MIN_ARGS_PER_SECTION);
		Section[] sections = new Section[maxNumOfSections];
		int sectionsCount = 0; // The actual number of sections
		
		while (moreSectionsToParse) {
			sections[sectionsCount] = parseSection();
			sectionsCount++;
			moreSectionsToParse = currentLine < fileContent.length;
		}
		// Make a new array with the correct size.
		Section[] oldSections = sections;
		sections = new Section[sectionsCount];
		for (int i = 0; i < sectionsCount; i++) {
			sections[i] = oldSections[i];
		}
		return sections;
	}
	
	/**
	 * Reads the file's content and creates and array in which each element is a different line.
	 * @param myFile The file to read
	 * @return An array in which each elements represents a line in the file.
	 * @throws IOException
	 */
	private String[] readFile(File myFile) throws IOException {
		String EMPTY = "";
		int result;
		String line = EMPTY;
		String[] content = fileContent;
		try (FileReader fileReader = new FileReader(myFile)) {
			while ((result = fileReader.read()) != -1) {
				if ((char) result != NEW_LINE) {
					if ((char) result != IGNORE)
						line += (char) result;
				} else {
					content = addLineToContent(content, line);
					line = EMPTY;
				}
			}
			if (!line.equals(EMPTY))
				content = addLineToContent(content, line);
			return content;
		} catch (IOException e) {
			throw e;
		}
	}
	
	/**
	 * @param original An array of strings
	 * @param word A String to add to the array
	 * @return A bigger array that contains the old one and the new string.
	 */
	private String[] addLineToContent(String[] original, String word) {
		String[] newArray = new String[original.length +1];
		for (int i = 0; i < original.length; i++) {
			newArray[i] = original[i];
		}
		newArray[original.length] = word;
		return newArray;
	}
	
	/**
	 * Reads the file's lines. Expects a certain order (FILTER - filterArgs - ORDER - orderArgs -....)
	 * @return A section from the commands file.
	 * @throws FileScriptException
	 */
	private Section parseSection() throws FileScriptException {
		Section section = new Section();
		boolean parsingEnded = false;
		
		while (!parsingEnded) {
			try {
				if (section.getFilter() == null)
					section.setFilter(parseFilter());
				if (section.getOrder() == null)
					section.setOrder(parseOrder());
				parsingEnded = true;
				
			// If there was a problem (type i) with the FILTER sub-section, treat as *all* filter.
			} catch (FilterException badFilter) { 
				section.setFilter(new AllFilter());
				badFilter.setLineNumber(currentLine); // Remember the line number (in the file).
				section.addWarning(badFilter); // Add to warning array.
				continue;
			// If there was a problem (type i) with the ORDER sub-section, treat as *abs* order.
			} catch (OrderException badOrder) {
				section.setOrder(new AbsOrder());
				badOrder.setLineNumber(currentLine);
				section.addWarning(badOrder);
				continue;
			} catch (Exception otherException) {
				throw otherException;
			}
		}
		return section;
	}
	
	/**
	 * @return The Filter object as specified by the commands file's FILTER sub-section.
	 * @throws FileScriptException
	 */
	private Filter parseFilter() throws FileScriptException {
		// Checks if the FILTER line exists.
		if (!fileContent[currentLine].equals(FILTER)) {
			throw new BadCommandFileException("No FILTER sub-section. Line " + currentLine);
		}
		currentLine++; // Move to the next line
		Filter filter = null;
		
		try { // Attempts to create the filter object
			String[] filterArgs = fileContent[currentLine].split(SEPERATOR);
			filter = FilterFactory.createFilter(filterArgs);
			currentLine++;
			return filter;
		} catch (BadFilterNameException badName) {
			/* If NO filter arguments were given - we reached the ORDER sub-section - so we don't need
			   to move to the next line */
			if (!badName.getBadName().equals(ORDER)) {
				currentLine++;
			}
			throw badName;
		} catch (BadFilterArgumentsException badVal) {
			currentLine++;
			throw badVal;
		} catch (ArrayIndexOutOfBoundsException badIndex) { // This means End-of-File is reached
			throw new BadCommandFileException("No ORDER sub-section. Line " + currentLine);
		} catch (Exception otherException) {
			throw otherException;
		}
	}
	
	/**
	 * @return The Order object as specified by the ORDER sub-section.
	 * @throws FileScriptException
	 */
	private Order parseOrder() throws FileScriptException {
		// Checks if EoF is reached or the ORDER line does not exist.
		if (currentLine >= fileContent.length || !fileContent[currentLine].equals(ORDER)) {
			throw new BadCommandFileException("No ORDER sub-section. Line " + currentLine);
		}
		currentLine++; // Move to the next line
		Order order = null;
		
		try { // Attempts to create an Order object
			String[] orderArgs = fileContent[currentLine].split(SEPERATOR);
			order = OrderFactory.createOrder(orderArgs);
			currentLine++;
		} catch (BadOrderNameException badOrder) {
			// If no order arguments were given, treat as AbsOrder
			if (badOrder.getBadName().equals(FILTER)) {
				order = new AbsOrder();
			} else { // If bad arguments were given, move to the next line and throw an exception.
				currentLine++;
				throw badOrder;
			}
		} catch (ArrayIndexOutOfBoundsException badIndex) {
			// EoF is reached without order arguments given - treat as AbsOrder
			order = new AbsOrder();
			moreSectionsToParse = false; // Stop parsing.
		}
		return order;
	}
}
