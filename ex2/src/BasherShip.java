import oop.ex2.*;

/**
 * A class that reprsents a ship that tries to 'bash' other ships (chasing them and turns on shields when
 * close enough).
 * @author ransha
 *
 */
public class BasherShip extends SpaceShip {
	private SpaceShipPhysics closestShip;
	// Constants
	private final static double CLOSE_DISTANCE = 0.2;
	private final boolean ACCELERATES = true;
	
	@Override
	/**
	 * Finds the closest ship and turns towards it.
	 */
	protected void doMove(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		double angle = myPhysics.angleTo(closestShip);
		int turn = 0;
		if (angle > 0) {
			turn = LEFT;
		} else if (angle < 0) {
			turn = RIGHT;
		}
		// Always accelerates
		myPhysics.move(ACCELERATES, turn);
	}

	@Override
	/**
	 * 
	 */
	protected void doShields(SpaceWars game) {
		double distance = myPhysics.distanceFrom(closestShip);
		if (distance < CLOSE_DISTANCE) {
			shieldOn();
		} else {
			shieldOff();
		}
	}

	@Override
	protected void doTeleport(SpaceWars game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void doFire(SpaceWars game) {
		// TODO Auto-generated method stub
		
	}
}
