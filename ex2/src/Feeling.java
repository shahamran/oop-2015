import java.util.Random;

/**
 * Reprsents a feeling that can change randomly.
 * @author ransha
 *
 */
public class Feeling {
	private static int MAX_FEELING = 200, ACTUALLY_FEELS = 160;
	private int feelingAmount, feelingCounter;
	private boolean doesFeel;
	private Random randomGenerator;
	
	/**
	 * Constructor for a Feeling object.
	 */
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
	 * @return True if this feeling is currently being felt, false otherwise.
	 */
	public boolean isFeeling() {
		return doesFeel;
	}
	
}
