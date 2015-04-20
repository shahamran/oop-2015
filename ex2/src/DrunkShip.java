import java.util.Random;
import oop.ex2.*;

/**
 * A class that represents a drunk pilot. The pilot, who had a bit too much in passover evening,
 * let his(/her) emotions get the best of him(/her). Now, their actions are based solely on their
 * emotional state.
 * @author ransha
 *
 */
public class DrunkShip extends SpaceShip implements Teleportable, HasShields, HasGuns {
	// Random Generator
	private Random randomGenerator;
	// Some Constants
	private static final int MAX_SHIELD = 50;
	private static final double CLOSE_ENOUGH_ANGLE = 1.2;
	// Data members
	private SpaceShipPhysics closestShip;
	private int shieldAmount = 0, shieldCounter = 0;
	private Feeling dizzy, angry, paranoid;
	private Feeling[] feelingsList;
	
	public DrunkShip() {
		dizzy = new Feeling();
		angry = new Feeling();
		paranoid = new Feeling();
		Feeling[] myFeelingsList = {dizzy, angry, paranoid}; 
		feelingsList = myFeelingsList;
		randomGenerator = new Random();
	}
		
	/**
	 * Changes the pilot's feelings if needed.
	 */
	private void updateFeelings() {
		for (Feeling myFeeling: feelingsList) {
			myFeeling.updateFeeling();
		}
	}
	
	@Override
	/**
	 * State-of-the-art algorithm to decide where to go (feeling-based, of course).
	 */
	protected void doMove(SpaceWars game) {
		updateFeelings();
		
		closestShip = game.getClosestShipTo(this).getPhysics();				
		/* Turn towards enemy ships fiercefully!
		 * ...or... run for your life if feeling paranoid... */
		int turn = directionToShip(closestShip, !paranoid.isFeeling());
		// Avoid accelerating if dizzy, unless of course you're angry... in this case.. DIE ENEMY DIE
		boolean accel = !dizzy.isFeeling() || angry.isFeeling(); 
		myPhysics.move(accel, turn);
	}
	
	@Override
	/**
	 * Attempts to teleport, only 1 out of 4 times and only if the pilot's feeling paranoid.
	 */
	public void doTeleport(SpaceWars game) {
		if (!paranoid.isFeeling()) {
			return;
		}
		int shouldTeleport = randomGenerator.nextInt(4);
		if (shouldTeleport == 0) {
			teleport();
		}
	}

	@Override
	/**
	 * Attempts to turn on the shield if the pilot is feeling paranoid, but not dizzy (so they could
	 * press the button) 
	 */
	public void doShields(SpaceWars game) {
		if (shieldCounter < shieldAmount) {
			shieldOn();
			shieldCounter ++;
			return;
		} else {
			shieldOff();
			shieldAmount = 0;
			shieldCounter = 0;
		}
		if (paranoid.isFeeling() && !dizzy.isFeeling()) {
			shieldAmount = randomGenerator.nextInt(MAX_SHIELD) + MAX_SHIELD;
		}
	}

	@Override
	/**
	 * Attempts to fire at the closest ship if the pilot is feeling angry but 
	 * only if they're *kind of* facing it.
	 */
	public void doFire(SpaceWars game) {
		double angle = myPhysics.angleTo(closestShip);
		if (angry.isFeeling() && Math.abs(angle) < CLOSE_ENOUGH_ANGLE) {
			fire(game);	
		}
		
	}

}
