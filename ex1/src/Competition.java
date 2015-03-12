import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of rounds. 
 * It also keeps track of the number of victories of each player.
 */
public class Competition extends java.lang.Object {
	private static final int NUM_OF_PLAYERS = 2;
	private Player[] player;
	private int[] score;
	private boolean gameOver = false;
	
	
	/**
	 * Receives two Player objects, representing the two competing opponents,
	 * and a flag determining whether messages should be displayed.
	 * @param player1 The Player objects representing the first player.
	 * @param player2 The Player objects representing the second player.
	 * @param displayMessage a flag indicating whether gameplay messages
	 *        should be printed to the console.
	 */
	public Competition(Player player1, Player player2, boolean displayMessage){
		player = new Player[NUM_OF_PLAYERS];
		score = new int[NUM_OF_PLAYERS];
		player[0] = player1;
		player[1] = player2;
		score[0] = 0;
		score[1] = 0;
	}
	
	/**
	 * f playerPosition = 1, the results of the first player is returned.
	 * If playerPosition = 2, the result of the second player is returned. 
	 * If playerPosition equals neiter, -1 is returned.
	 * @param playerPosition
	 * @return the number of victories of a player.
	 */
	public int getPlayerScore(int playerPosition){
		if (playerPosition >= 1 && playerPosition <= NUM_OF_PLAYERS){
			return score[playerPosition - 1];
		}
		return -1;
	}
	
	/**
	 * Run the game for the given number of rounds.
	 * @param numberOfRounds number of rounds to play.
	 */
	public void playMultipleRounds(int numberOfRounds){
		for (int round = 0; round < numberOfRounds; round++){
			while (!gameOver){
				if 
			}
		}
	}
	
	/**
	 * Returns the integer representing the type of the player; returns -1 on bad
	 * input.
	 */
	private static int parsePlayerType(String[] args,int index){
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
		Board board = new Board();
		Scanner scanner = new Scanner(System.in);
		
		
	}	
	
}
