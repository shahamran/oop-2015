
public class Action {
	private boolean doAction;
	private int actionDamage, actionPenalty, actionRefractory, actionCost, actionBonus;
	private int actionCounter;
	
	/**
	 * 
	 * @param cost
	 * @param initial
	 * @param damage
	 * @param penalty
	 * @param refractory
	 * @param bonus
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
	
	public Action(int cost, int damage, int penalty, int refractory) {
		this(cost,damage,penalty);
		actionCounter = refractory;
		actionRefractory = refractory;
	}
	
	public Action(int cost, int damage, int penalty,int refractory, int bonus) {
		this(cost, damage, penalty, refractory);
		actionBonus = bonus;
	}
	
	
	public void setAction(boolean yourSet) {
		doAction = yourSet;
	}
	
	public boolean getAction() {
		return doAction;
	}
	
	public int getCost() {
		return actionCost;
	}
	
	public int getDamage() {
		return actionDamage;
	}
	
	public int getPenalty() {
		return actionPenalty;
	}
	
	public int getRefractory() {
		return actionRefractory;
	}
	
	public int getBonus() {
		return actionBonus;
	}
	
	public void updateRefractory() {
		if (!doAction) {
			return;
		}
		if (actionCounter > 0) {
			actionCounter --;
		} else {
			actionCounter = actionRefractory;
			doAction = false;
		}
	}
	
	public boolean canDoAction(int yourEnergy) {
		return yourEnergy >= actionCost && actionCounter == actionRefractory;
	}
}
