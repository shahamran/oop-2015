import java.awt.Image;
import oop.ex2.*;

/**
 * The API spaceships need to implement for the SpaceWars game. 
 * It is your decision whether SpaceShip.java will be an interface, an abstract class,
 *  a base class for the other spaceships or any other option you will choose.
 *  
 * @author oop
 */
public abstract class SpaceShip {
	// Constants
	protected static final int START_ENERGY = 200, START_HEALTH = 20,
					     	   COLLISION_DAMAGE = 1, SHOT_DAMAGE = 1,
					     	   BASHING_BONUS = 20, HIT_ENERGY_PENALTY = 10;
	protected static int TELEPORT_COST = 150, SHOT_COST = 20, SHIELD_COST = 3, FIRE_REFRACTORY = 8;
	protected static final int LEFT = 1, RIGHT = -1;
	// Ship's data members
	protected int maxEnergy,currentEnergy,health;
	protected SpaceShipPhysics myPhysics;
	protected boolean shieldsAreOn;
	private int fireWait; // Counts the fire waiting time (rounds).
	
	/** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
    	maxEnergy = START_ENERGY;
    	currentEnergy = START_ENERGY;
    	health = START_HEALTH;
    	shieldsAreOn = false;
    	fireWait = 0;
    	myPhysics = new SpaceShipPhysics();
    }
    
	/**
	 * Constructor for the SpaceShip object. sets a new ship with initial parameters (uses reset() method).
	 */
	public SpaceShip(){
		reset();
	}
	
	/**
	 * Decides whether to teleport or not.
	 * @param game
	 */
	protected abstract void doTeleport(SpaceWars game);
	
	/**
	 * Decides how to move this round.
	 * Should call myPhysics.move(); method exactly ONCE.
	 * @param game
	 */
	protected abstract void doMove(SpaceWars game);
	
	/**
	 * Decides whether to turn on shields or not.
	 * @param game
	 */
	protected abstract void doShields(SpaceWars game);
	
	/**
	 * Decides whether to fire or not.
	 * @param game
	 */
	protected abstract void doFire(SpaceWars game);
	
    /**
     * Does the actions of this ship for this round. 
     * This is called once per round by the SpaceWars game driver.
     * 
     * @param game the game object to which this ship belongs.
     */
    public void doAction(SpaceWars game) {
    	doTeleport(game);
    	doMove(game);
    	doShields(game);
    	
    	// Update fire delay
    	if (fireWait > 0) {
    		fireWait --;
    	}
    	doFire(game);
    	
    	// Regenerate
    	if (currentEnergy < maxEnergy) {
    		currentEnergy ++;
    	}
    }
    
    /**
     * Attempts to reduce maximum energy level of this ship.
     * @param amount The amount of max energy to reduce. Must be positive.
     */
    private void reduceMaxEnergy(int amount) {
    	if (amount < 0) {
    		return;
    	} else if (amount > maxEnergy){
    		amount = maxEnergy;
    	}
    	maxEnergy -= amount;
    	if (currentEnergy > maxEnergy) {
    		currentEnergy = maxEnergy;
    	}
    }
    
    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip() {
    	if (!shieldsAreOn) {
    		health -= COLLISION_DAMAGE;
    		reduceMaxEnergy(HIT_ENERGY_PENALTY);
    	} else {
    		currentEnergy += BASHING_BONUS;
    		maxEnergy += BASHING_BONUS;
    	}
    }

    /**
     * Checks if this ship is dead.
     * 
     * @return true if the ship is dead. false otherwise.
     */
    public boolean isDead() {
        return health <= 0;
    }

    /**
     * Gets the physics object that controls this ship.
     * 
     * @return the physics object that controls the ship.
     */
    public SpaceShipPhysics getPhysics() {
        return myPhysics;
    }

    /**
     * This method is called by the SpaceWars game object when ever this ship
     * gets hit by a shot.
     */
    public void gotHit() {
    	if (!shieldsAreOn) {
    		health -= SHOT_DAMAGE;
    		reduceMaxEnergy(HIT_ENERGY_PENALTY);
    	}
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * NOTE : Default is ENEMY SHIP. If an ally ship is created, override this method.
     * 
     * @return the image of this ship.
     */
    public Image getImage() {
    	if (shieldsAreOn) {
			return GameGUI.ENEMY_SPACESHIP_IMAGE_SHIELD;
		} else {
			return GameGUI.ENEMY_SPACESHIP_IMAGE;
		}
    }

    /**
     * Attempts to fire a shot.
     * 
     * @param game the game object.
     */
    public void fire(SpaceWars game) {
       if (currentEnergy >= SHOT_COST && fireWait == 0) {
    	   game.addShot(myPhysics);
    	   currentEnergy -= SHOT_COST;
    	   fireWait = FIRE_REFRACTORY;
       }
    }

    /**
     * Turns off shields.
     */
    protected void shieldOff() {
    	shieldsAreOn = false;
    }
    
    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
        if (currentEnergy >= SHIELD_COST) {
        	shieldsAreOn = true;
        	currentEnergy -= SHIELD_COST;
        } else {
        	shieldsAreOn = false;
        }
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
       if (currentEnergy >= TELEPORT_COST) {
    	   myPhysics = new SpaceShipPhysics();
    	   currentEnergy -= TELEPORT_COST;
       }
    }
    
}
