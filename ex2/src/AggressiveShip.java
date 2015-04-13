import oop.ex2.*;

/**
 * A class that reprsents a SpaceShip that tries to destroy other spaceships by shooting at them. 
 * @author ransha
 */
public class AggressiveShip extends SpaceShip {
	// Holds the closest ship's physics.
	private SpaceShipPhysics closestShip;
	// Constant
	private final static double CLOSE_ANGLE = 0.2;
	
	
	@Override
	protected void doMove(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		double angle = myPhysics.angleTo(closestShip);
		int turn = 0;
		if (angle > 0) {
			turn = LEFT;
		} else if (angle < 0) {
			turn = RIGHT;
		}
		myPhysics.move(true, turn);
	}

	@Override
	protected void doFire(SpaceWars game) {
		double angle = myPhysics.angleTo(closestShip);
		if (Math.abs(angle) < CLOSE_ANGLE) {
			fire(game);
		}
	}
	
	@Override
	protected void doTeleport(SpaceWars game) {
		/* Doesn't try to teleport */
	}
	
	@Override
	protected void doShields(SpaceWars game) {
		/* Doesn't turn on shields */
	}

}
