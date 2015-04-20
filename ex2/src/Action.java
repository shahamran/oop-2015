/**
 * This class reprsents an action in the SpaceWars game. Every action has energy cost, damage, energy
 * penalty, refractory time (inactivation period) and energy bonus values.
 * 
 * @author ransha
 *
 */
public class Action {
	private boolean doAction;
	private int actionDamage, actionPenalty, actionRefractory, actionCost, actionBonus;
	private int actionCounter;
	
	/**
	 * Constructor for a simple action.
	 * 
	 * @param cost The amount of energy needed to perform this action.
	 * @param damage The amount of health damage this action does.
	 * @param penalty The energy penalty this action causes.
	 */
	public Action(int cost, int damage, int penalty) {
		actionCounter = 0;
		actionCost = cost;
		actionDamage = damage;
		actionPenalty = penalty;
		actionRefractory = 0;
		actionBonus = 0;
		doAction = false;
	}
	
	/**
	 * Constructor for a more complex action.
	 * 
	 * @param cost The amount of energy needed to perform this action.
	 * @param damage The amount of health damage this action does.
	 * @param penalty The energy penalty this action causes.
	 * @param refractory The refractory time after each use of this acion.
	 */
	public Action(int cost, int damage, int penalty, int refractory) {
		this(cost,damage,penalty);
		actionCounter = refractory;
		actionRefractory = refractory;
	}
	
	/**
	 * Constructor for the most complex actions.
	 * 
	 * @param cost The amount of energy needed to perform this action.
	 * @param damage The amount of health damage this action does.
	 * @param penalty The energy penalty this action causes.
	 * @param refractory The refractory time after each use of this acion.
	 * @param bonus The energy bonus gained from this action.
	 */
	public Action(int cost, int damage, int penalty,int refractory, int bonus) {
		this(cost, damage, penalty, refractory);
		actionBonus = bonus;
	}
	
	/**
	 * Sets this action to be on or off
	 * 
	 * @param yourSet true if action should be activated, false otherwise.
	 */
	public void setAction(boolean yourSet) {
		doAction = yourSet;
	}
	
	/**
	 * @return True if the action is currently activated, false otherwise.
	 */
	public boolean getAction() {
		return doAction;
	}
	
	/**
	 * @return The energy needed to activate this action.
	 */
	public int getCost() {
		return actionCost;
	}
	
	/**
	 * @return The damage this action deals.
	 */
	public int getDamage() {
		return actionDamage;
	}
	
	/**
	 * @return The energy penalty this action causes.
	 */
	public int getPenalty() {
		return actionPenalty;
	}
	
	/**
	 * @return The energy bonus gained by this action.
	 */
	public int getBonus() {
		return actionBonus;
	}
	
	/**
	 * Call this once per round to update the inactivation time for recently activated action.
	 */
	public void updateRefractory() {
		if (!doAction) {
			return; // If the action wasn't performed, do nothing.
		}
		if (actionCounter > 0) {
			actionCounter --;
		} else {
			actionCounter = actionRefractory;
			doAction = false;
		}
	}
	
	/**
	 * Indicates whether your current energy state and inactivation time 
	 * allows you to perform this action.
	 * 
	 * @param yourEnergy The ship's current energy state.
	 * @return True if this action can be performed, false otherwise.
	 */
	public boolean canDoAction(int yourEnergy) {
		return yourEnergy >= actionCost && actionCounter == actionRefractory;
	}
}
