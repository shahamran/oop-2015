import oop.ex2.*;

/**
 * A class that reprsents a ship that tries to 'bash' other ships (chasing them and turns on shields when
 * close enough).
 * @author ransha
 *
 */
public class BasherShip extends SpaceShip implements HasShields {
	private SpaceShipPhysics closestShip;
	
	@Override
	/**
	 * Finds the closest ship and turns towards it.
	 */
	protected void doMove(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		int turn = directionToShip(closestShip, true);
		// Always accelerates
		myPhysics.move(ACCELERATE, turn);
	}

	@Override
	/**
	 * If close enough to enemy ship, (tries to) turn on shields.
	 */
	public void doShields(SpaceWars game) {
		double distance = myPhysics.distanceFrom(closestShip);
		if (distance < CLOSE_DISTANCE) {
			shieldOn();
		} else {
			shieldOff();
		}
	}
}
