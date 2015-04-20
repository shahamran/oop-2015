import oop.ex2.*;

/**
 * A class that represents a ship that runs away from combat, as specified in the exercise
 * description.
 * @author ransha
 *
 */
public class RunnerShip extends SpaceShip implements Teleportable {
	private SpaceShipPhysics closestShip;
	
	@Override
	/**
	 * If threatened, attempts to teleport.
	 */
	public void doTeleport(SpaceWars game) {
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
		int turn = directionToShip(closestShip, RUN_AWAY);
		myPhysics.move(ACCELERATE, turn);
	}
}
