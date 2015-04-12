import oop.ex2.*;

/**
 * 
 * @author ransha
 *
 */
public class RunnerShip extends SpaceShip {
	private SpaceShipPhysics closestShip;
	private final double CLOSE_DISTANCE = 0.2;
	private final double CLOSE_ANGLE = 0.2;
	
	@Override
	protected void doTeleport(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		double angleToMe = closestShip.angleTo(myPhysics);
		double distanceFromMe = myPhysics.distanceFrom(closestShip);
		if (Math.abs(angleToMe) < CLOSE_ANGLE && distanceFromMe < CLOSE_DISTANCE) {
			teleport();
		}
	}

	@Override
	protected void doMove(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		double angle = myPhysics.angleTo(closestShip);
		int turn = 0;
		if (angle >= 0) {
			turn = -1;
		} else {
			turn = 1;
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
