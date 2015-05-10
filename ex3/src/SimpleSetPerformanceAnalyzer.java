import java.util.*;

public class SimpleSetPerformanceAnalyzer {
	private static SimpleSet[] dastList;
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		dastList = new SimpleSet[5];
		String[] data1 = Ex3Utils.file2array("/cs/stud/ransha/safe/oop/ex3/src/data1.txt");
		String[] data2 = Ex3Utils.file2array("/cs/stud/ransha/safe/oop/ex3/src/data2.txt");
		testData(data1,"Data 1");
		testContains("hi");
		testContains("-13170890158");
		testData(data2,"Data 2");
		testContains("hi");
		testContains("23");
	}

	public static void testData(String[] data,String name) {
		initEmptyDastList();
		System.out.println(name + ":");
		for (SimpleSet dast : dastList) {
			if (dast == null)
				continue;
			System.out.print(dast + ": ");
			long thisTime = measureAddTime(dast, data);
			System.out.println(thisTime + " ms");
		}
	}
	
	
	public static long measureAddTime(SimpleSet dast, String[] data) {
		long timeBefore = System.nanoTime();
		for (int i = 0; i < data.length; i++) {
			dast.add(data[i]);
		}
		long difference = System.nanoTime() - timeBefore;
		return difference / (1000000);
	}
	
	public static void testContains(String val) {
		System.out.println("Tests 'contains(" + val + ")':");
		for (SimpleSet dast : dastList) {
			if (dast == null)
				continue;
			long timeBefore = System.nanoTime();
			dast.contains(val);
			long difference = System.nanoTime() - timeBefore;
			System.out.println(dast + ": " + difference + " ns");
		}
			
	}
	
	public static void initEmptyDastList() {
		dastList[0] = new ChainedHashSet();
		dastList[1] = new OpenHashSet();
		dastList[2] = new CollectionFacadeSet(new TreeSet<String>());
		dastList[3] = new CollectionFacadeSet(new LinkedList<String>());
		dastList[4] = new CollectionFacadeSet(new HashSet<String>());
	}
	
}
