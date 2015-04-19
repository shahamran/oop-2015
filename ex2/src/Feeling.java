import java.util.Random;

public class Feeling {
	private static final int MAX_FEELING = 100, ACTUALLY_FEELS = 150;
	private int feelingAmount, feelingCounter;
	private boolean doesFeel;
	private Random randomGenerator;
	
	public Feeling() {
		randomGenerator = new Random();
		feelingAmount = 0; feelingCounter = 0;
		doesFeel = false;
	}
	
	/**
	 * Randomly change the feeling.
	 */
	public void updateFeeling() {
		if (feelingAmount == 0) {
			feelingCounter = 0;
			feelingAmount = randomGenerator.nextInt(MAX_FEELING) + MAX_FEELING;
		} else {
			if (feelingCounter > feelingAmount) {
				feelingCounter = 0; feelingAmount = 0;
			} else {
				feelingCounter ++;
			}
		}
		doesFeel = feelingAmount >= ACTUALLY_FEELS;
	}
	
	/**
	 * 
	 * @return True if this feeling is currently being felt, false otherwise.
	 */
	public boolean isFeeling() {
		return doesFeel;
	}
	
	
}
