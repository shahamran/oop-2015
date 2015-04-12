import oop.ex2.*;

/**
 * 
 * @author ransha
 *
 */
public class AggressiveShip extends SpaceShip {
	// Holds the closest ship's physics.
	private SpaceShipPhysics closestShip;
	private final double CLOSE_ANGLE = 0.2;
	
	
	@Override
	protected void doMove(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		double angle = myPhysics.angleTo(closestShip);
		int turn = 0;
		if (angle > 0) {
			turn = 1;
		} else if (angle < 0) {
			turn = -1;
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
