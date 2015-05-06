import java.util.*;

public class SimpleSetPerformanceAnalyzer {
	private static SimpleSet[] dastList;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		initEmptyDastList();
		String[] data1 = Ex3Utils.file2array("/cs/stud/ransha/safe/oop/ex3/src/data1.txt");
		String[] data2 = Ex3Utils.file2array("/cs/stud/ransha/safe/oop/ex3/src/data2.txt");
		long thisTime;
//		System.out.println("DATA 1:");
//		for (SimpleSet dast : dastList) {
//			thisTime = measureAddTime(dast, data1);
//			System.out.println(dast + ": " + thisTime);
//		}
		initEmptyDastList();
		System.out.println("DATA 2:");
		for (SimpleSet dast : dastList) {
			thisTime = measureAddTime(dast, data2);
			System.out.println(dast + ": " + thisTime);
		}

	}

	public static long measureAddTime(SimpleSet dast, String[] data) {
		long timeBefore = System.nanoTime();
		for (String value : data) {
			dast.add(value);
		}
		long difference = System.nanoTime() - timeBefore;
		return difference / (1000000);
	}
	

	
	public static void initEmptyDastList() {
		dastList = new SimpleSet[5];
		dastList[0] = new OpenHashSet();
		dastList[1] = new ChainedHashSet();
		dastList[2] = new CollectionFacadeSet(new TreeSet<String>());
		dastList[3] = new CollectionFacadeSet(new LinkedList<String>());
		dastList[4] = new CollectionFacadeSet(new HashSet<String>());
	}
	
}
