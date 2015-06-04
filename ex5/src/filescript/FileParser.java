package filescript;

import filescript.*;
import filescript.filters.*;
import java.io.*;

public class FileParser {
	private File myFile;
	private int result, lineNum = 0;
	private boolean moreSectionsToParse = true;
	
	private static final char NEW_LINE = '\n';
	private static final int MAX_ARGS = 4, FILTER_NUM = 0, FILTER_ARGS = 1, ORDER_NUM = 2, ORDER_ARGS = 3;
	private static final String FILTER = "FILTER",ORDER = "ORDER", SEPERATOR = "#";
	
	
	public FileParser(File inFile) {
		myFile = inFile;
	}
	
	public Section[] parseFile(File commandsFile) throws FileScriptException {
		Section[] sections = null;
		int sectionsCount = 0;
		String orderArgs, currLine;
		String[] filterArgs;
		Filter filter = null;
		Object order = null;
		while (moreSectionsToParse) {
			currLine = readLineFromFile();
			switch (lineNum % MAX_ARGS) {
			case FILTER_NUM:
				if (!currLine.equals(FILTER)) {
					throw new FileScriptException();
				}
				break;
			case FILTER_ARGS:
				filterArgs = currLine.split(SEPERATOR);
				filter = FiltersFactory.createFilter(filterArgs);
				break;
			case ORDER_NUM:
				if (!currLine.equals(ORDER)) {
					throw new FileScriptException();
				}
				break;
			case ORDER_ARGS:
				orderArgs = currLine;
				order = new Object();
				sectionsCount++;
				Section[] oldSections = sections;
				sections = new Section[sectionsCount];
				for (int i = 0; i < oldSections.length; i++) {
					sections[i] = oldSections[i];
				}
				sections[sectionsCount] = new Section(filter,order);
				break;
			} // Switch Ends Here
		} // While Ends Here
		return sections;
	} // Method Ends Here
	
	private String readLineFromFile() {
		String word = "";
		try (FileReader fileReader = new FileReader(myFile)) {
			while ((result = fileReader.read()) != -1) {
				if ((char) result != NEW_LINE) {
					word += (char) result;
				} else {
					lineNum++;
					return word;
				}
			}
			moreSectionsToParse = false;
			return word;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
