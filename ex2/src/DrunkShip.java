import java.util.Random;
import oop.ex2.*;

public class DrunkShip extends SpaceShip {
	private Random randomGenerator = new Random();
	private SpaceShipPhysics closestShip;
	private int shieldCounter = 0, shieldDuration = 0;
	
	@Override
	protected void doTeleport(SpaceWars game) {
		int shouldTeleport = randomGenerator.nextInt(5);
		if (shouldTeleport == 4) {
			teleport();
		}
	}

	@Override
	protected void doMove(SpaceWars game) {
		closestShip = game.getClosestShipTo(this).getPhysics();
		
		int turn = randomGenerator.nextInt(3) - 1;
		int doAccel = randomGenerator.nextInt(2);
		boolean accel;
		if (doAccel == 1) {
			accel = true;
		} else {
			accel = false;
		}
		myPhysics.move(accel, turn);
	}

	@Override
	protected void doShields(SpaceWars game) {
		if (!shieldsAreOn) {
			int randDuration = randomGenerator.nextInt(100);
			if (randDuration > 80) {
				shieldOn();
				shieldDuration = randDuration;
			}
		} else {
			shieldCounter ++;
			if (shieldCounter >= shieldDuration) {
				shieldsAreOn = false;
				shieldCounter = 0;
				shieldDuration = 0;
			}
		}
	}

	@Override
	protected void doFire(SpaceWars game) {
		// TODO Auto-generated method stub
		
	}

}
