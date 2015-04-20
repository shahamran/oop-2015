import java.awt.Image;
import oop.ex2.*;

/**
 * A type of SpaceShip that gets input from the GUI and acts accordingly.
 * @author ransha
 *  
 */
public class HumanShip extends SpaceShip implements Teleportable, HasShields, HasGuns {
	
	@Override
	/**
	 * Returns an ally ship image. Overrides the default enemy ship image method.
	 */
	public Image getImage() {
		if (shields.getAction()) {
			return GameGUI.SPACESHIP_IMAGE_SHIELD;
		} else {
			return GameGUI.SPACESHIP_IMAGE;
		}
	}

	@Override
	/**
	 * Tries to teleport if user pressed the teleport button.
	 */
	public void doTeleport(SpaceWars game) {
		if (game.getGUI().isTeleportPressed()) {
			teleport();
		}
	}

	@Override
	/**
	 * Decides how to move according to user input.
	 */
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
	/**
	 * Attempts to turn on shields if the user is holding the shields button.
	 */
	public void doShields(SpaceWars game) {
		if (game.getGUI().isShieldsPressed()) {
			shieldOn();
		} else {
			shieldOff();
		}
	}

	@Override
	/**
	 * Attempts to fire a shot if the user is pressing the fire button.
	 */
	public void doFire(SpaceWars game) {
		if (game.getGUI().isShotPressed()) {
			fire(game);
		}
	}


}
