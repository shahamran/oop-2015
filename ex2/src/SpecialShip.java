
public class SpecialShip extends SpaceShip {
	private static double CLOSE_ANGLE = 0.2, CLOSE_DISTANCE = 0.5;
	
	@Override
	protected void doTeleport(SpaceWars game) {
		// Doesn't teleport
		
	}

	@Override
	protected void doMove(SpaceWars game) {
		SpaceShip closeShip = game.getClosestShipTo(this);
		double angle = closeShip.getPhysics().angleTo(myPhysics);
		double distance = myPhysics.distanceFrom(closeShip.getPhysics());
		if (Math.abs(angle) <= CLOSE_ANGLE) {
			closeShip.teleport();
		} else if (distance <= CLOSE_DISTANCE) {
			closeShip.shieldOff();
		}
		myPhysics.move(true, 1);
	}

	@Override
	protected void doShields(SpaceWars game) {
		//shieldOn();
		
	}

	@Override
	protected void doFire(SpaceWars game) {
		
	}
	
	public void fire(SpaceWars game) {
		if (fire.canDoAction(currentEnergy)) {
			myPhysics.move(false, 1);
			myPhysics.move(false, 1);
			game.addShot(myPhysics);
			myPhysics.move(false, -1);
			myPhysics.move(false, -1);
			game.addShot(myPhysics);
			myPhysics.move(false, -1);
			myPhysics.move(false, -1);
			game.addShot(myPhysics);
			myPhysics.move(false, 1);
			myPhysics.move(false, 1);
			currentEnergy -= fire.getCost();
		}
	}
	



	

}
