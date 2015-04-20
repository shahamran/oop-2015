/**
 * This SpaceShip simulates 'medusa' - No other ship can look at it (will be teleported if it tries)
 * and other ships are weak when next to it (turns close ships' shields off).
 * Also, it can move twice as fast as a regular space ship.
 * 
 * @author ransha
 *
 */
public class SpecialShip extends SpaceShip implements HasGuns, HasShields {
	private SpaceShip closeShip;

	@Override
	/**
	 * Moves twice as fast as a regular space ship.
	 */
	protected void doMove(SpaceWars game) {
		closeShip = game.getClosestShipTo(this);
		
		int turn = directionToShip(closeShip.getPhysics(), FOLLOW_SHIP);
		myPhysics.move(ACCELERATE, turn);
		myPhysics.move(ACCELERATE, turn);
	}

	
	@Override
	/** 
	 * Tries to teleport ships that are looking at this ship.
	 * When a ship is close by, medusa turns off its shields.
	 * Medusa fires at ships in range.
	 */
	public void doFire(SpaceWars game) {
		closeShip = game.getClosestShipTo(this);
		
		double angleToMe = closeShip.getPhysics().angleTo(myPhysics);
		double distance = myPhysics.distanceFrom(closeShip.getPhysics());
		if (Math.abs(angleToMe) <= CLOSE_ANGLE) {
			closeShip.teleport();
		} else if (distance <= CLOSE_DISTANCE) {
			closeShip.shieldOff();
		}
		// Update the closest ship variable (Could have been teleported)
		closeShip = game.getClosestShipTo(this);
		double angle = myPhysics.angleTo(closeShip.getPhysics());
		
		if (Math.abs(angle) <= CLOSE_ANGLE) {
			this.fire(game);
		}
	}
	
	/**
	 * Turn on shields when close to other ships.
	 */
	public void doShields(SpaceWars game) {
		closeShip = game.getClosestShipTo(this);
		
		double distance = myPhysics.distanceFrom(closeShip.getPhysics());
		if (distance <= CLOSE_DISTANCE) {
			shieldOn();
		} else {
			shieldOff();
		}
	}

}
