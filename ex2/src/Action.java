
public class Action {
	private boolean doAction;
	private final int actionInitial;
	private int actionDamage, actionPenalty, actionRefractory, actionCost, actionBonus;
	private int actionCounter;
	
	public Action(int cost, int initial, int damage){
		actionCost = cost;
		actionInitial = initial;
		actionPenalty = 0;
		actionRefractory = 0;
		doAction = false;
	}
	
	public Action(int cost, int initial, int damage, int penalty, int refractory, int bonus) {
		actionCounter = 0;
		actionCost = cost;
		actionInitial = initial;
		actionDamage = damage;
		actionPenalty = penalty;
		actionRefractory = refractory;
		actionBonus = bonus;
		doAction = false;
	}
	
	public void setAction(boolean yourSet) {
		doAction = yourSet;
	}
	
	public boolean getAction() {
		return doAction;
	}
	
	public int getInitial() {
		return actionInitial;
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
	
	public void updateRefractory() {
		if (actionCounter < actionRefractory) {
			actionCounter ++;
		} else {
			actionCounter = 0; actionRefractory = 0;
		}
	}
	
	public boolean canDoAction() {
		return actionCounter >= actionRefractory;
	}
}
