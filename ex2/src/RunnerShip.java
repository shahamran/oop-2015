import oop.ex2.*;

/**
 * A class that represents a ship that runs away from combat, as specified in the exercise
 * description.
 * @author ransha
 *
 */
public class RunnerShip extends SpaceShip {
	private SpaceShipPhysics closestShip;
	// Constants
	private final static double CLOSE_DISTANCE = 0.2, CLOSE_ANGLE = 0.2;
	
	@Override
	/**
	 * If threatened, attempts to teleport.
	 */
	protected void doTeleport(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		double angleToMe = closestShip.angleTo(myPhysics);
		double distanceFromMe = myPhysics.distanceFrom(closestShip);
		if (Math.abs(angleToMe) < CLOSE_ANGLE && distanceFromMe < CLOSE_DISTANCE) {
			teleport();
		}
	}

	@Override
	/**
	 * Faces AWAY from combat
	 */
	protected void doMove(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		double angle = myPhysics.angleTo(closestShip);
		int turn = 0;
		if (angle >= 0) {
			turn = RIGHT;
		} else {
			turn = LEFT;
		}
		myPhysics.move(true, turn);
	}

	@Override
	protected void doShields(SpaceWars game) {
		/* Doesn't turn on shields */
	}

	@Override
	protected void doFire(SpaceWars game) {
		/* Doesn't fire */
	}


}
