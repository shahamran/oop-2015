import oop.ex2.*;

public class BasherShip extends SpaceShip {
	private SpaceShipPhysics closestShip;
	private final double CLOSE_DISTANCE = 0.2;
	
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
	protected void doShields(SpaceWars game) {
		double distance = myPhysics.distanceFrom(closestShip);
		if (distance < CLOSE_DISTANCE) {
			shieldOn();
		} else {
			shieldsAreOn = false;
		}
	}

	@Override
	protected void doTeleport(SpaceWars game) {
		/* Doesn't teleport */
	}
	
	@Override
	protected void doFire(SpaceWars game) {
		/* Doesn't fire */
	}

}
