package filescript.sections;

import filescript.*;
import filescript.sections.filters.*;
import filescript.sections.orders.*;

import java.io.*;

public class FileParser {
	private String[] fileContent;
	private int result, currentLine = 0;
	private boolean moreSectionsToParse = true;
	
	private static final char NEW_LINE = '\n', IGNORE = '\r';
	private static final int MIN_ARGS_PER_SECTION = 2;
	private static final String FILTER = "FILTER",ORDER = "ORDER", SEPERATOR = "#";
	
	public FileParser(File inFile) throws IOException {
		fileContent = readFile(inFile);
	}
	
	public Section[] parseFile() throws FileScriptException {
		int maxNumOfSections = (int) Math.ceil(fileContent.length / MIN_ARGS_PER_SECTION);
		Section[] sections = new Section[maxNumOfSections];
		int sectionsCount = 0;
		while (moreSectionsToParse) {
			sections[sectionsCount] = parseSection();
			sectionsCount++;
			moreSectionsToParse = currentLine < fileContent.length;
		}
		Section[] oldSections = sections;
		sections = new Section[sectionsCount];
		for (int i = 0; i < sectionsCount; i++) {
			sections[i] = oldSections[i];
		}
		return sections;
	} // Method Ends Here
	
	private String[] readFile(File myFile) throws IOException {
		String word = "";
		String[] content = null;
		int lineNum = 0;
		try (FileReader fileReader = new FileReader(myFile)) {
			while ((result = fileReader.read()) != -1) {
				if ((char) result != NEW_LINE) {
					if ((char) result != IGNORE)
						word += (char) result;
				} else {
					lineNum++;
					String[] oldContent = content;
					content = new String[lineNum];
					for (int i = 0; i < lineNum - 1; i++) {
						content[i] = oldContent[i];
					}
					content[lineNum - 1] = word;
					word = "";
				}
			}
			content = addWordToArray(content, word);
			return content;
		} catch (IOException e) {
			throw e;
		}
	}
	
	private String[] addWordToArray(String[] original, String word) {
		String[] newArray = new String[original.length +1];
		for (int i = 0; i < original.length; i++) {
			newArray[i] = original[i];
		}
		newArray[original.length] = word;
		return newArray;
	}
	
	public Section parseSection() throws FileScriptException {
		Section section = new Section();
		boolean parsingEnded = false;
		while (!parsingEnded) {
			try {
				if (section.getFilter() == null)
					section.setFilter(parseFilter());
				if (section.getOrder() == null)
					section.setOrder(parseOrder());
				parsingEnded = true;
			} catch (BadFilterArgumentsException badFilter) {
				section.setFilter(new AllFilter());
				badFilter.setLineNumber(currentLine);
				section.addWarning(badFilter);
				continue;
			} catch (BadOrderNameException badOrder) {
				section.setOrder(new AbsOrder());
				badOrder.setLineNumber(currentLine);
				section.addWarning(badOrder);
				continue;
			}
		}
		return section;
		
	}
	
	private Filter parseFilter() throws FileScriptException {
		if (!fileContent[currentLine].equals(FILTER)) {
			throw new BadCommandFileException("No FILTER sub-section");
		}
		currentLine++;
		Filter filter = null;
		
		try {
			String[] filterArgs = fileContent[currentLine].split(SEPERATOR);
			filter = FilterFactory.createFilter(filterArgs);
			currentLine++;
			return filter;
		} catch (BadFilterNameException badName) {
			if (!badName.getBadName().equals(ORDER)) {
				currentLine++;
			}
			throw badName;
		} catch (BadFilterArgumentsException badVal) {
			currentLine++;
			throw badVal;
		} catch (ArrayIndexOutOfBoundsException badIndex) {
			throw new BadCommandFileException("No ORDER sub-section");
		} catch (Exception otherException) {
			throw otherException;
		}
		
	}
	
	private Order parseOrder() throws FileScriptException {
		if (currentLine >= fileContent.length || !fileContent[currentLine].equals(ORDER)) {
			throw new BadCommandFileException("No ORDER sub-section");
		}
		currentLine++;
		Order order = null;
		
		try {
			String[] orderArgs = fileContent[currentLine].split(SEPERATOR);
			order = OrderFactory.createOrder(orderArgs);
			currentLine++;
		} catch (BadOrderNameException e) {
			if (e.getBadName().equals(FILTER)) {
				order = new AbsOrder();
			} else {
				currentLine++;
				throw e;
			}
		} catch (ArrayIndexOutOfBoundsException badIndex) {
			order = new AbsOrder();
			moreSectionsToParse = false;
		}
		return order;
	}
}
