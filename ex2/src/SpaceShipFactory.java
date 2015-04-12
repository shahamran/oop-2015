
public class SpaceShipFactory {
	// String constants for the ship types
	private static final String HUMAN  = "h", RUNNER = "r", AGGRESSIVE  = "a",
								BASHER = "b", DRUNK = "d", SPECIAL = "s";
	
	/**
	 * Returns an array of SpaceShip objects according to given arguments.
	 * @param args Array of strings specifying which ships to create.
	 * @return Array of SpaceShip objects according to the given argumetns.
	 */
    public static SpaceShip[] createSpaceShips(String[] args) {
    	// numOfShips counts the number of ships that were actually created.
    	int numOfShips = 0, maxNumberOfShips = args.length;
    	// If no arguments were give, return null
      	if (maxNumberOfShips == 0) {
      		return null;
      	}
      	// Primary ships array. May include empty values.
    	SpaceShip[] shipsArray = new SpaceShip[maxNumberOfShips];
    	
    	for (String shipType : args) {
    		switch (shipType) {
    		case HUMAN:
    			shipsArray[numOfShips] = new HumanShip();
    			break;
    		case RUNNER:
    			shipsArray[numOfShips] = new RunnerShip();
    			break;
    		case AGGRESSIVE:
    			shipsArray[numOfShips] = new AggressiveShip();
    			break;
    		case BASHER:
    			shipsArray[numOfShips] = new BasherShip();
    			break;
    		case DRUNK:
    			shipsArray[numOfShips] = new DrunkShip();
    			break;
    		case SPECIAL:
    			shipsArray[numOfShips] = new SpecialShip();
    			break;
    		default:
    			// If no legal argument was given, don't count this ship.
    			numOfShips --;
    			break;
    		}
    		numOfShips ++;
    	}
    	
    	// If no ships were created, return null.
    	if (numOfShips <= 0) {
    		return null;
    	}
    	
    	// Returns an array that includes only space ships (and no empty values)
    	SpaceShip[] finalShipsArray = new SpaceShip[numOfShips];
    	for (int i = 0; i < numOfShips; i++) {
    		finalShipsArray[i] = shipsArray[i];
    	}
        return finalShipsArray;
    }
}
