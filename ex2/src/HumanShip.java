import java.awt.Image;
import oop.ex2.*;

public class HumanShip extends SpaceShip {
	
	@Override
	public Image getImage() {
		if (shieldsAreOn) {
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		} else {
			return GameGUI.SPACESHIP_IMAGE;
		}
	}

	@Override
	protected void doTeleport(SpaceWars game) {
		if (game.getGUI().isTeleportPressed()) {
			teleport();
		}
	}

	@Override
	protected void doMove(SpaceWars game) {
		boolean accel = game.getGUI().isUpPressed();
		int turn = 0;
		if (game.getGUI().isLeftPressed()) {
			turn += LEFT;
		} 
		if (game.getGUI().isRightPressed()) {
			turn += RIGHT;
		}
		
		myPhysics.move(accel, turn);
	}

	@Override
	protected void doShields(SpaceWars game) {
		if (game.getGUI().isShieldsPressed()) {
			if (shieldsAreOn) {
				shieldOff();
			} else {
				shieldOn();
			}
		}
	}

	@Override
	protected void doFire(SpaceWars game) {
		if (game.getGUI().isShotPressed()) {
			fire(game);
		}
	}


}
