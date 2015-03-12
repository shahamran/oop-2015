/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of rounds. 
 * It also keeps track of the number of victories of each player.
 */
public class Competition {
	
	/* You need to implement this class */
	
	
	/*
	 * Returns the integer representing the type of the player; returns -1 on bad
	 * input.
	 */
	private static int parsePlayerType(String[] args,index){
		try{
			return Integer.parseInt(args[index]);
		} catch (Exception E){
			return -1;
		}
	}
	
	/*
	 * Returns the integer representing the type of player 2; returns -1 on bad
	 * input.
	 */
	private static int parseNumberOfGames(String[] args){
		try{
			return Integer.parseInt(args[2]);
		} catch (Exception E){
			return -1;
		}
	}

	/**
	 * The method runs a Nim competition between two players according to the three user-specified arguments. 
	 * (1) The type of the first player, which is a positive integer between 1 and 4: 1 for a Random computer
	 *     player, 2 for a Heuristic computer player, 3 for a Smart computer player and 4 for a human player.
	 * (2) The type of the second player, which is a positive integer between 1 and 4.
	 * (3) The number of rounds to be played in the competition.
	 * @param args an array of string representations of the three input arguments, as detailed above.
	 */
	public static void main(String[] args) {
		
		int p1Type = parsePlayerType(args,0);
		int p2Type = parsePlayerType(args,1);
		int numGames = parseNumberOfGames(args);
		
		/* You need to implement this method /*
		
	}	
	
}
