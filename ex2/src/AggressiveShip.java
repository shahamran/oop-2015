import oop.ex2.*;

/**
 * A class that represents a SpaceShip that tries to destroy other spaceships by shooting at them. 
 * 
 * @author ransha
 */
public class AggressiveShip extends SpaceShip implements HasGuns {
	// Holds the closest ship's physics.
	private SpaceShipPhysics closestShip;
	
	
	@Override
	/**
	 * Moves towards enemy ships.
	 */
	protected void doMove(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		int turn = directionToShip(closestShip, FOLLOW_SHIP);
		myPhysics.move(ACCELERATE, turn);
	}

	@Override
	/**
	 * Fires at a ship if the angle to it is about right.
	 */
	public void doFire(SpaceWars game) {
		double angle = myPhysics.angleTo(closestShip);
		if (Math.abs(angle) < CLOSE_ANGLE) {
			fire(game);
		}
	}
}
