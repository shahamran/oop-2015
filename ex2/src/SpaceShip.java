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
	protected static final int START_ENERGY = 200, START_HEALTH = 20;
	protected static final int LEFT = 1, RIGHT = -1;
	protected static final double CLOSE_ANGLE = 0.2, CLOSE_DISTANCE = 0.2;
	protected static final boolean ACCELERATE = true, DONT_ACCEL = false,
								   FOLLOW_SHIP = true, RUN_AWAY = false;
	// Actions values (constants)
	private final static int FIRE_COST = 20, SHIELD_COST = 3, TELEPORT_COST = 150,
							 HIT_DAMAGE = 1, HIT_ENERGY_PENALTY = 10, BASHING_BONUS = 20,
							 FIRE_REFRACTORY = 8;
	// Ship's data members
	protected int maxEnergy,currentEnergy,health;
	protected Action fire,shields,teleport,bashing,collision;
	protected SpaceShipPhysics myPhysics;
	
	   
	/**
	 * Constructor for the SpaceShip object. sets a new ship with initial parameters (uses reset() method).
	 */
	public SpaceShip(){
		reset();
	}
		
	/** 
     * This method is called whenever a ship has died. It resets the ship's 
     * attributes, and starts it at a new random position.
     */
    public void reset(){
    	maxEnergy = START_ENERGY;
    	currentEnergy = START_ENERGY;
    	health = START_HEALTH;
    	myPhysics = new SpaceShipPhysics();
    	initActions();
    }
    
    /**
     * Initializes the Action objects of this ship.
     */
    private void initActions() {
    	fire = new Action(FIRE_COST, HIT_DAMAGE, HIT_ENERGY_PENALTY, FIRE_REFRACTORY);
    	shields = new Action(SHIELD_COST, 0, 0);
    	teleport = new Action(TELEPORT_COST, 0, 0);
    	collision = new Action(0, HIT_DAMAGE, HIT_ENERGY_PENALTY);
    	bashing = new Action(0, 0, 0, 0, BASHING_BONUS);
    }	

	/**
	 * Decides how to move this round. Every SpaceShip should implement this method.
	 * Should call myPhysics.move(); method EXACTLY ONCE.
	 * 
	 * @param game the SpaceWars object of the game currently running
	 */
	protected abstract void doMove(SpaceWars game);
	
	/**
	 * Decides whether to teleport or not.
	 * 
	 * @param game the SpaceWars object of the game currently running
	 */
	protected void doTeleport(SpaceWars game) {
		/* Does nothing, by default */
	}
	
	/**
	 * Decides whether to turn on shields or not.
	 * 
	 * @param game the SpaceWars object of the game currently running
	 */
	protected void doShields(SpaceWars game) {
		/* Does nothing, by default */
	}
	
	/**
	 * Decides whether to fire or not.
	 * 
	 * @param game the SpaceWars object of the game currently running
	 */
	protected void doFire(SpaceWars game) {
		/* Does nothing, by default */
	}
	
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
    	fire.updateRefractory(); // Updates the fire inactivation period.
    	doFire(game);
    	
    	// Regenerate energy once per round.
    	if (currentEnergy < maxEnergy) {
    		currentEnergy ++;
    	}
    }
    
    /**
     * Attempts to reduce maximum energy level of this ship.
     * 
     * @param amount The amount of max energy to reduce. Must be positive.
     */
    private void reduceMaxEnergy(int amount) {
    	if (amount <= 0) {
    		return;
    	} else if (amount > maxEnergy){
    		amount = maxEnergy;
    	}
    	maxEnergy -= amount;
    	// If current energy is greather than the new Maximum energy value, it changes to the smaller value.
    	currentEnergy = Math.min(currentEnergy, maxEnergy);
    }
    
    /**
     * This method is called every time a collision with this ship occurs 
     */
    public void collidedWithAnotherShip() {
    	// If shields are on, the current action is bashing, otherwise, it's collision.
    	Action myAction = shields.getAction() ? bashing : collision;
    	// This part works because of the attributes of each action (see Action class, and InitActions())
    	health -= myAction.getDamage();
    	reduceMaxEnergy(myAction.getPenalty());
    	currentEnergy += myAction.getBonus();
    	maxEnergy += myAction.getBonus();
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
    	if (!shields.getAction()) {
    		health -= fire.getDamage();
    		reduceMaxEnergy(fire.getPenalty());
    	}
    	
    }

    /**
     * Gets the image of this ship. This method should return the image of the
     * ship with or without the shield. This will be displayed on the GUI at
     * the end of the round.
     * NOTE : Default is ENEMY SHIP. If an ally ship is created, OVERRIDE this method.
     * 
     * @return the image of this ship.
     */
    public Image getImage() {
    	if (shields.getAction()) {
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
    	if (fire.canDoAction(currentEnergy)) {
    		game.addShot(myPhysics);
    		currentEnergy -= fire.getCost();
    		fire.setAction(true);
    	}
    }

    /**
     * Turns off shields.
     */
    protected void shieldOff() {
    	shields.setAction(false);
    }
    
    /**
     * Attempts to turn on the shield.
     */
    public void shieldOn() {
    	// Turns on shields only if energy state allows it.
    	shields.setAction(shields.canDoAction(currentEnergy));
    	if (shields.getAction()) {
    		// Reduces energy only if shield activation succeeded
    		currentEnergy -= shields.getCost();
    	}
    }

    /**
     * Attempts to teleport.
     */
    public void teleport() {
    	if (teleport.canDoAction(currentEnergy)) {
    		myPhysics = new SpaceShipPhysics();
    		currentEnergy -= teleport.getCost();
    	}
    }
    
    /**
     * Returns the direction you should turn to follow a chosen ship (if doFollow is true) or to run away
     * from it (if doFollow is false).
     * 
     * @param ship the SpaceShipPhysics object of the chosen ship
     * @param doFollow Choose true if you want to follow cloeset ship or false if you want to
     * run away from it.
     * 
     * @return The direction you should go.
     */
    protected int directionToShip(SpaceShipPhysics ship, boolean doFollow) {
    	double angle = myPhysics.angleTo(ship);
    	int turn = angle >= 0 ? LEFT : RIGHT;
    	if (doFollow) {
    		if (angle == 0) {
    			turn = 0;
    		}
    	} else {
    		turn = (-1) * turn;
    	}
    	return turn;
    }
    
}
