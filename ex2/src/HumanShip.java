import java.awt.Image;
import oop.ex2.*;

public class HumanShip extends SpaceShip {
	
	@Override
	public Image getImage() {
		if (shields.getAction()) {
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		} else {
			return GameGUI.SPACESHIP_IMAGE;
		}
	}

	@Override
	public void doTeleport(SpaceWars game) {
		if (game.getGUI().isTeleportPressed()) {
			teleport();
		}
	}

	@Override
	public void doMove(SpaceWars game) {
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
	public void doShields(SpaceWars game) {
		if (game.getGUI().isShieldsPressed()) {
			shieldOn();
		} else {
			shieldOff();
		}
	}

	@Override
	public void doFire(SpaceWars game) {
		if (game.getGUI().isShotPressed()) {
			fire(game);
		}
	}


}
