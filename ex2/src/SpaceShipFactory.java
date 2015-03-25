import oop.ex2.*;

public class SpaceShipFactory {
	public final static String HUMAN = "h", DRUNK = "d", RUNNER = "r", 
			                   BASHER = "b",AGGRESSIVE = "a", SPECIAL = "s";
	
    public static SpaceShip[] createSpaceShips(String[] args) {
    	int numOfShips = args.length;
    	if (numOfShips == 0) {
    		return null;
    	}
    	
    	SpaceShip[] myShips = new SpaceShip[numOfShips];
    	int shipIndex = 0;
    	
        for (String shipType : args) {
        	int thisType;
        	switch (shipType) {
        	case HUMAN:
        		thisType = SpaceWars.HUMAN_CONTROLLED_SHIP;
        	case BASHER:
        		thisType = SpaceWars.BASHER_SHIP;
        	case AGGRESSIVE:
        		thisType = SpaceWars.AGGRESSIVE_SHIP;
        	case DRUNK:
        		thisType = SpaceWars.FLOATING_SHIP;
        	case SPECIAL:
        		thisType = SpaceWars.SPECIAL_SHIP;
        	case RUNNER:
        		thisType = SpaceWars.RUNNER_SHIP;
        	default:
        		thisType = -1;
        	}
        	if (!(thisType == -1)) {
        		myShips[shipIndex] = new SpaceShip(thisType);
        		shipIndex ++;
        	}
        }
        return myShips;
    }
}
