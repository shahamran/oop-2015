import java.util.Random;
import oop.ex2.*;

/**
 * A class that represents a drunk pilot. The pilot, who had a bit too much in passover evening,
 * let his(/her) emotions get the best of him(/her). Now, their actions are based solely on their
 * emotional state.
 * @author ransha
 *
 */
public class DrunkShip extends SpaceShip {
	// Random Generator
	private static Random randomGenerator = new Random();
	// Some Constants
	private static final int MAX_FEELINGS = 200, ACTUALLY_FEELS = 140;
	private static final double CLOSE_ENOUGH_ANGLE = 1;
	// Data members
	private SpaceShipPhysics closestShip;
	private boolean isAngry = false, isDizzy = false, isParanoid = false;
	
	/* the feelingAmount is the intensity of a feeling, if greater that ACTUALLY_FEELS, the boolean
	   of this feeling will be set to true */
	private int dizzyAmount = 0, angryAmount = 0, paranoidAmount = 0;
	// the currentFeeling is simply a counter to maintain a feeling during several rounds.
	private int currentDizzy = 0, currentAngry = 0, currentParanoid = 0;
	
	/**
	 * Randomally change the "pilot's" specific feeling
	 * @param feelingAmount Current amount of this feeling.
	 * @param currentFeeling Current state of that feeling.
	 * @param feeling Is this feeling feeled right now.
	 * @return An array of 3 integers : the new feelingAmount, currentFeeling and 1 if feeling should be
	 * true, 0 if it should be false.
	 */
	private int[] changeFeeling(int feelingAmount, int currentFeeling) {
		boolean feeling = false;
		
		if (feelingAmount == 0) {
			currentFeeling = 0;
			// Randomally generates a feeling intensity
			feelingAmount = randomGenerator.nextInt(MAX_FEELINGS);
		} else {
			if (currentFeeling < feelingAmount) {
				currentFeeling ++;
			} else {
				currentFeeling = 0;
				feelingAmount = 0;
			}
		}
		
		if (feelingAmount >= ACTUALLY_FEELS) {
			feeling = true;
		}
		
		int[] output = new int[3];
		output[0] = feelingAmount;
		output[1] = currentFeeling;
		output[2] = feeling ? 1 : 0;
		return output;
	}
	
	/**
	 * Changes the pilot's feelings if needed.
	 */
	private void updateFeelings() {
		// Check if dizzy
		int[] Dizzy = changeFeeling(dizzyAmount, currentDizzy);
		dizzyAmount = Dizzy[0]; currentDizzy = Dizzy[1]; isDizzy = Dizzy[2] == 1 ? true : false;
		// Check if angry
		int[] Angry = changeFeeling(angryAmount, currentAngry);
		angryAmount = Angry[0]; currentAngry = Angry[1]; isAngry = Angry[2] == 1 ? true : false;
		// Check if paranoid
		int[] Paranoid = changeFeeling(paranoidAmount, currentParanoid);
		paranoidAmount = Paranoid[0]; currentParanoid = Paranoid[1];
		isParanoid = Paranoid[2] == 1 ? true : false;
	}
	
	@Override
	/**
	 * State-of-the-art algorithm to decide where to go (feeling-based, of course).
	 */
	protected void doMove(SpaceWars game) {
		updateFeelings();
		
		closestShip = game.getClosestShipTo(this).getPhysics();
						
		double angle = myPhysics.angleTo(closestShip);
		int turn = 0;
		turn = (angle >= 0) ? -1 : 1; // turn towards enemy ships
		turn = isParanoid ? (-1) * turn : turn; // -- or -- run away if paranoid
		
		boolean accel = isDizzy ? false : true; // don't accelerate if dizzy (to avoid throwing up)
		
		myPhysics.move(accel, turn);
	}
	
	@Override
	/**
	 * Attempts to teleport, only 1 out of 5 times and only if the pilot's feeling paranoid.
	 */
	protected void doTeleport(SpaceWars game) {
		if (!isParanoid) {
			return;
		}
		int shouldTeleport = randomGenerator.nextInt(5);
		if (shouldTeleport == 4) {
			teleport();
		}
	}


	@Override
	/**
	 * Attempts to turn on the shield if the pilot is feeling paranoid, but not dizzy (so they could
	 * press the button) 
	 */
	protected void doShields(SpaceWars game) {
		if (isParanoid && !isDizzy) {
			shieldOn();
		} else {
			shieldsAreOn = false;
		}
	}

	@Override
	/**
	 * Attempts to fire at the closest ship if the pilot is feeling angry but 
	 * only if they're *kind of* facing it.
	 */
	protected void doFire(SpaceWars game) {
		double angle = myPhysics.angleTo(closestShip);
		if (isAngry) {
			if (Math.abs(angle) < CLOSE_ENOUGH_ANGLE) {
				fire(game);
			}
		}
		
	}

}
