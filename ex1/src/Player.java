import java.util.Random;
import java.util.Scanner;

/**
 * The Player class represents a player in the Nim game, producing Moves as a response to a Board state. Each player 
 * is initialized with a type, either human or one of several computer strategies, which defines the move he 
 * produces when given a board in some state. The heuristic strategy of the player is already implemented. You are 
 * required to implement the rest of the player types according to the exercise description.
 * @author OOP course staff
 */
public class Player {

	//Constants that represent the different players.
	/** The constant integer representing the Random player type. */
	public static final int RANDOM = 1;
	/** The constant integer representing the Heuristic player type. */
	public static final int HEURISTIC = 2;
	/** The constant integer representing the Smart player type. */
	public static final int SMART = 3;
	/** The constant integer representing the Human player type. */
	public static final int HUMAN = 4;
	
	//Used by produceHeuristicMove() for binary representation of board rows.
	private static final int BINARY_LENGTH = 3;
	
	private final int playerType;
	private final int playerId;
	private Scanner scanner;
	
	/**
	 * Initializes a new player of the given type and the given id, and an initialized scanner.
	 * @param type The type of the player to create.
	 * @param id The id of the player (either 1 or 2).
	 * @param inputScanner The Scanner object through which to get user input
	 * for the Human player type. 
	 */
	public Player(int type, int id, Scanner inputScanner){		
		// Check for legal player type (we will see better ways to do this in the future).
		if (type != RANDOM && type != HEURISTIC 
				&& type != SMART && type != HUMAN){
			System.out.println("Received an unknown player type as a parameter"
					+ " in Player constructor. Terminating.");
			System.exit(-1);
		}		
		playerType = type;	
		playerId = id;
		scanner = inputScanner;
	}
	
	/**
	 * @return an integer matching the player type.
	 */	
	public int getPlayerType(){
		return playerType;
	}
	
	/**
	 * @return the players id number.
	 */	
	public int getPlayerId(){
		return playerId;
	}
	
	
	/**
	 * @return a String matching the player type.
	 */
	public String getTypeName(){
		switch(playerType){
			
			case RANDOM:
				return "Random";			    
	
			case SMART: 
				return "Smart";	
				
			case HEURISTIC:
				return "Heuristic";
				
			case HUMAN:			
				return "Human";
		}
		//Because we checked for legal player types in the
		//constructor, this line shouldn't be reachable.
		return "UnkownPlayerType";
	}
	
	/**
	 * This method encapsulates all the reasoning of the player about the game. The player is given the 
	 * board object, and is required to return his next move on the board. The choice of the move depends
	 * on the type of the player: a human player chooses his move manually; the random player should 
	 * return some random move; the Smart player can represent any reasonable strategy; the Heuristic 
	 * player uses a strong heuristic to choose a move. 
	 * @param board - a Board object representing the current state of the game.
	 * @return a Move object representing the move that the current player will play according to his strategy.
	 */
	public Move produceMove(Board board){
		
		switch(playerType){
		
			case RANDOM:
				return produceRandomMove(board);				
				    
			case SMART: 
				return produceSmartMove(board);
				
			case HEURISTIC:
				return produceHeuristicMove(board);
				
			case HUMAN:
				return produceHumanMove(board);

			//Because we checked for legal player types in the
			//constructor, this line shouldn't be reachable.
			default: 
				return null;			
		}
	}
	
	/*
	 * Returns true if a row has unmarked sticks, false otherwise.
	 * Helps other methods (RandomMove, SmartMove).
	 */
	private boolean isRowHasUnmarked(Board board, int aRow) {
		int rowLength = board.getRowLength(aRow);
		for (int i = 1; i <= rowLength; i++){
			if ( board.isStickUnmarked(aRow, i) ) {
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Produces a random move.
	 */
	private Move produceRandomMove(Board board){
		Random myRandom = new Random();
		int maxRow = board.getNumberOfRows(), randRow = 0;
		boolean hasUnmarked = false;
		// Chooses a random row with sticks in it
		while (!hasUnmarked){
			randRow = myRandom.nextInt(maxRow) + 1;
			hasUnmarked = isRowHasUnmarked(board,randRow);
		}
		
		// Produce random left and right boundaries
		int rowLength = board.getRowLength(randRow);
		int randLeft = myRandom.nextInt(rowLength) + 1;
		int randSequence = myRandom.nextInt(rowLength - randLeft + 1) + 1;
		return new Move(randRow, randLeft, randLeft + randSequence - 1);
	}
	
	// Methods to help the SmartMove method.
	
	/*
	 * Returns the total number of separate sequences of sticks in a given board.
	 */
	public int getNumberOfSequences(Board board) {
		int sequenceNum = 0;
		boolean counting;
		for (int row = 1; row <= board.getNumberOfRows(); row ++) {
			counting = false;
			for (int stick = 1; stick <= board.getRowLength(row); stick ++) {
				// Counts unmarked sticks.
				if (board.isStickUnmarked(row, stick)) {
					if (!counting) {
						sequenceNum ++;
						counting = true;
					}
				} else {
					counting = false;
				}
			}
		}
		return sequenceNum;
	}
	
	/*
	 * Returns a new Board object identical to the given one.
	 */
	private Board copyBoard(Board board) {
		Board newBoard = new Board();
		Move myMove;
		for (int row = 1; row <= board.getNumberOfRows(); row ++) {
			for (int stick = 1; stick <= board.getRowLength(row); stick ++) {
				// Mark in the new board every marked stick in the old board.
				if (!board.isStickUnmarked(row, stick)) {
					myMove = new Move(row, stick, stick);
					newBoard.markStickSequence(myMove);
				}
			}
		}
		return newBoard;
	}
	
	/*
	 * Returns true if the current setting of the board is a sure lost,
	 * false otherwise.
	 * Helps determine whether a 'test' move is a winning one.
	 */
	private boolean isLosingState(Board board) {
		int numOfSticks = board.getNumberOfUnmarkedSticks();
		int numOfSequences = getNumberOfSequences(board);
		return (numOfSticks == numOfSequences && numOfSticks % 2 == 1);
	}
	
	/*
	 * Returns the total number of possible moves on the board.
	 */
	private int possibleMoves(Board board) {
		int numOfMoves = 0;
		int maxRow = board.getNumberOfRows();
		boolean counting;
		int sequenceSize, rowLength;
		
		for (int row = 1; row <= maxRow; row ++) {
			if (!isRowHasUnmarked(board, row)){
				continue; // Skip empty rows.
			}
			rowLength = board.getRowLength(row);
			counting = false;
			sequenceSize = 0;
			for (int stick = 1; stick <= rowLength; stick ++) {
				if (board.isStickUnmarked(row, stick)) {
					if (!counting) {
						counting = true;
					}
					sequenceSize ++;
				} else {
					if (counting) {
						numOfMoves += sequenceSize*(sequenceSize + 1) / 2;
					}
					sequenceSize = 0;
					counting = false;
				}
			}
			if (counting) {
				numOfMoves += sequenceSize*(sequenceSize + 1) / 2;
			}
		}
		return numOfMoves;
	}
	
	/*
	 * Produce some intelligent strategy to produce a move
	 */
	private Move produceSmartMove(Board board) {
		// Higher number will get better results, but worse performance.
		int MAX_MOVES_ALLOWED = 15;
		// a new board for test moves.
		Board testBoard;
		// total number of sticks and sequences on board
		int numOfSticks = board.getNumberOfUnmarkedSticks();
		int numOfSequences = getNumberOfSequences(board);
		int maxRow = board.getNumberOfRows(), rowLength;
		Move myMove;
		
		if (numOfSticks == numOfSequences){
			// In this case, it doesn't matter what move you play.
			return produceRandomMove(board);
		} else {
			/* Checks every possible move (within the limits) to see 
			   if there's a winning move. If one was found, return it. */
			if (possibleMoves(board) < MAX_MOVES_ALLOWED) {
				for (int row = 1; row <= maxRow; row ++) {
					if (!isRowHasUnmarked(board, row)) {
						continue;
					}
					rowLength = board.getRowLength(row);
					for (int left = 1; left <= rowLength; left ++) {
						if (!board.isStickUnmarked(row, left)) {
							continue;
						}
						for (int seq = 0; seq <= rowLength - left; seq ++) {
							myMove = new Move(row, left, left + seq);
							testBoard = copyBoard(board);
							testBoard.markStickSequence(myMove);
							if (isLosingState(testBoard)) {
								return myMove;
							}
						}
					}
				}
			}
		}
		// If no move was a winning one, produce a random move.
		return produceRandomMove(board);
	}
	
	/*
	 * Interact with the user to produce his move.
	 */
	private Move produceHumanMove(Board board){
		int myRow,myLeft,myRight;
		Move myMove = null;
		int input;
		boolean shouldStop = false;
		while(!shouldStop) {
			System.out.print("Press 1 to display the board.  ");
			System.out.println("Press 2 to make a move:");
			// Get input from user
			input = scanner.nextInt();
			switch (input){
				case 1: // Print board
					System.out.println(board);
					break;
				case 2: // Ask for a move
					System.out.println("Enter the row number:");
					myRow = scanner.nextInt();
					System.out.println("Enter the index of the " +
							 		             "leftmost stick:");
					myLeft = scanner.nextInt();
					System.out.println("Enter the index of the " +
							                    "rightmost stick:");
					myRight = scanner.nextInt();
					myMove = new Move(myRow,myLeft,myRight);
					shouldStop = true;
					break;
				default:
					System.out.println("Unknown input.");
			}
		}
		return myMove;
	}
	
	/*
	 * Uses a winning heuristic for the Nim game to produce a move.
	 */
	private Move produceHeuristicMove(Board board){

		if(board == null){
			return null;
		}
	
		int numRows = board.getNumberOfRows();
		int[][] bins = new int[numRows][BINARY_LENGTH];
		int[] binarySum = new int[BINARY_LENGTH];
		int bitIndex,higherThenOne=0,totalOnes=0,lastRow=0,lastLeft=0,lastSize=0,lastOneRow=0,lastOneLeft=0;
		
		for(bitIndex = 0;bitIndex<BINARY_LENGTH;bitIndex++){
			binarySum[bitIndex] = 0;
		}
		
		for(int k=0;k<numRows;k++){
			
			int curRowLength = board.getRowLength(k+1);
			int i = 0;
			int numOnes = 0;
			
			for(bitIndex = 0;bitIndex<BINARY_LENGTH;bitIndex++){
				bins[k][bitIndex] = 0;
			}
			
			do {
				if(i<curRowLength && board.isStickUnmarked(k+1,i+1) ){
					numOnes++;
				} else {
					
					if(numOnes>0){
						
						String curNum = Integer.toBinaryString(numOnes);
						while(curNum.length()<BINARY_LENGTH){
							curNum = "0" + curNum;
						}
						for(bitIndex = 0;bitIndex<BINARY_LENGTH;bitIndex++){
							bins[k][bitIndex] += curNum.charAt(bitIndex)-'0'; //Convert from char to int
						}
						
						if(numOnes>1){
							higherThenOne++;
							lastRow = k +1;
							lastLeft = i - numOnes + 1;
							lastSize = numOnes;
						} else {
							totalOnes++;
						}
						lastOneRow = k+1;
						lastOneLeft = i;
						
						numOnes = 0;
					}
				}
				i++;
			}while(i<=curRowLength);
			
			for(bitIndex = 0;bitIndex<BINARY_LENGTH;bitIndex++){
				binarySum[bitIndex] = (binarySum[bitIndex]+bins[k][bitIndex])%2;
			}
		}
		
		
		//We only have single sticks
		if(higherThenOne==0){
			return new Move(lastOneRow,lastOneLeft,lastOneLeft);
		}
		
		//We are at a finishing state				
		if(higherThenOne<=1){
			
			if(totalOnes == 0){
				return new Move(lastRow,lastLeft,lastLeft+(lastSize-1) - 1);
			} else {
				return new Move(lastRow,lastLeft,lastLeft+(lastSize-1)-(1-totalOnes%2));
			}
			
		}
		
		for(bitIndex = 0;bitIndex<BINARY_LENGTH-1;bitIndex++){
			
			if(binarySum[bitIndex]>0){
				
				int finalSum = 0,eraseRow = 0,eraseSize = 0,numRemove = 0;
				for(int k=0;k<numRows;k++){
					
					if(bins[k][bitIndex]>0){
						eraseRow = k+1;
						eraseSize = (int)Math.pow(2,BINARY_LENGTH-bitIndex-1);
						
						for(int b2 = bitIndex+1;b2<BINARY_LENGTH;b2++){
							
							if(binarySum[b2]>0){
								
								if(bins[k][b2]==0){
									finalSum = finalSum + (int)Math.pow(2,BINARY_LENGTH-b2-1);
								} else {
									finalSum = finalSum - (int)Math.pow(2,BINARY_LENGTH-b2-1);
								}
								
							}
							
						}
						break;
					}
				}
				
				numRemove = eraseSize - finalSum;
				
				//Now we find that part and remove from it the required piece
				int numOnes=0,i=0;
				//while(numOnes<eraseSize){
				while(numOnes<numRemove && i<board.getRowLength(eraseRow)){

					if(board.isStickUnmarked(eraseRow,i+1)){
						numOnes++;
					} else {
						numOnes=0;
					}
					i++;
					
				}
				
				//This is the case that we cannot perform a smart move because there are marked
				//Sticks in the middle
				if(numOnes == numRemove){
					return new Move(eraseRow,i-numOnes+1,i-numOnes+numRemove);
				} else {
					return new Move(lastRow,lastLeft,lastLeft);
				}
				
			}
		}
		
		//If we reached here, and the board is not symmetric, then we only need to erase a single stick
		if(binarySum[BINARY_LENGTH-1]>0){
			return new Move(lastOneRow,lastOneLeft,lastOneLeft);
		}
		
		//If we reached here, it means that the board is already symmetric,
		//and then we simply mark one stick from the last sequence we saw:
		return new Move(lastRow,lastLeft,lastLeft);		
	}
	
	
}
