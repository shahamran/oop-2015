import java.util.Scanner;

/**
 * The Competition class represents a Nim competition between two players, consisting of a given number of rounds. 
 * It also keeps track of the number of victories of each player.
 */
public class Competition extends java.lang.Object {
	private static String DOTS = ":";
	private int player1Wins, totalRounds, numOfSticks = 1;
	private boolean displayMessage;
	private Player player1, player2, currentPlayer;
	private Board board;
	private static final int YOUR_TURN = 1, INVALID_MOVE = 2,
			                      MOVE_MADE = 3, WIN_MSG = 4;
	
	public Competition(Player player1, Player player2, boolean displayMessage){
		this.player1 = player1; this.player2 = player2;
		this.displayMessage = displayMessage;
	}
	
	public int getPlayerScore(int playerPosition){
		switch (playerPosition) {
			case 1:
				return player1Wins;
			case 2:
				return totalRounds - player1Wins;
			default:
				return -1;
		}
	}
	
	public void playMultipleRounds(int numberOfRounds){
		currentPlayer = player1;
		if (displayMessage)
			System.out.println("Welcome to the sticks game!");
		for (int round = 1; round <= numberOfRounds; round++){
			board = new Board();
			playGame();
		}
		System.out.println("The results are " + getPlayerScore(1) + DOTS +
				                                getPlayerScore(2));
	}
	
	private void printMessage(int msgNum){
		if (!displayMessage)
			return;
		switch (msgNum){
		case YOUR_TURN:
			System.out.println("Player " + currentPlayer.getPlayerId() +
                                                    ", it is now your turn!");
			break;
		case INVALID_MOVE:
			if (currentPlayer.getPlayerType() == Player.HUMAN)
				System.out.println("Invalid move.  Enter another:");
			break;
			
		case WIN_MSG:
			System.out.println("Player " + currentPlayer.getPlayerId() + " won!");
			break;
		}
	}
	
	private void playGame(){
		numOfSticks = board.getNumberOfUnmarkedSticks();
		while (numOfSticks > 0){
			printMessage(YOUR_TURN);
			int success = 0;
			Move myMove = null;
			while (success <= 0) {
				myMove = currentPlayer.produceMove(board);
				success = board.markStickSequence(myMove);
				if (success <= 0)
					printMessage(INVALID_MOVE);
			}
			if (displayMessage)
				System.out.println("Player " + currentPlayer.getPlayerId() +
						                         " made the move: " + myMove);
			if (currentPlayer == player1) {
				currentPlayer = player2;
			} else {
				currentPlayer = player1;
			}
			numOfSticks = board.getNumberOfUnmarkedSticks();
		}
		printMessage(WIN_MSG);
		if (currentPlayer == player1) {
			player1Wins ++;
		}
		totalRounds ++;
	}
	
	/*
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
		Scanner scanner = new Scanner(System.in);
		boolean printMessage = false;
		
		Player player1 = new Player(p1Type,1,scanner);
		Player player2 = new Player(p2Type,2,scanner);
		if (p1Type == Player.HUMAN || p2Type == Player.HUMAN){
			printMessage = true;
		}
		Competition myCompetition = new Competition(player1,player2,printMessage);
		System.out.println("Starting a Nim competition of " + numGames + 
				           " rounds between a " + player1.getTypeName() +
				           " player and a " + player2.getTypeName() + " player.");
		myCompetition.playMultipleRounds(numGames);
		
		
		
		
	}	
	
}
