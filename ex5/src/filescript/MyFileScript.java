package filescript;

import java.io.File;
import java.util.Arrays;

import filescript.sections.*;
import filescript.sections.filters.*;
import filescript.sections.orders.*;

public class MyFileScript {
	private static final int SOURCE_DIR_ARG = 0, COMMAND_FILE_ARG = 1, EXPECTED_ARGS = 2;
	private static final String ERROR_MSG = "ERROR";
	
	public static void main(String[] args) {
		if (args.length != EXPECTED_ARGS) {
			System.err.println(ERROR_MSG);
			return;
		}
		File commandFile,dir;
		File[] filesList = null;
		Section[] sections = null;
		
		dir = new File(args[SOURCE_DIR_ARG]);
		filesList = dir.listFiles();
		
		commandFile = new File(args[COMMAND_FILE_ARG]);
		
		try {
			FileParser parser = new FileParser(commandFile);
			sections = parser.parseFile();
		} catch (TypeTwoException e) {
			System.err.println(e.getMessage());
			//System.err.println(ERROR_MSG);
			return;
		} catch (Exception otherException) {
			otherException.printStackTrace();
		}
		for (Section section : sections) {
			handleSection(filesList,section);
		}
	}
	
	private static void handleSection(File[] filesList, Section section) {
		Filter filter = section.getFilter();
		Order order = section.getOrder();
		File[] relevantFiles = getRelevantFiles(filesList, filter);
		Arrays.sort(relevantFiles,order);
		for (File f : relevantFiles) {
			System.out.println(f.getName());
		}
	}
	
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
		File[] finalList = new File[filesNum];
		for (int i = 0; i< filesNum; i++) {
			finalList[i] = relevantFiles[i];
		}
		return finalList;
	}

}
