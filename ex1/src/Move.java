/**
 * The move class represents a move in the Nim game by a player. A move
 * consists of the row on which it is applied, the left bound of the sequence
 * of sticks to mark, and the right bound of that sequence.
 * @author Ran Shaham
 */
public class Move extends java.lang.Object {
	// String constants:
	String DOTS = ":", DASH = "-";
	int row, left, right;
	
	/**
	 * Constructs a Move object with the given parameters.
	 * @param inRow The row on which the move is performed.
	 * @param inLeft The left bound of the sequence to mark.
	 * @param inRight The right bound of the sequence to mark.
	 */
	public Move(int inRow, int inLeft, int inRight){
		row = inRow; left = inLeft; right = inRight;
	}

	/**
	 * @override in class java.lang.Object
	 * @return a string representation of the move. For example, if the row
	 *         is 2, the left bound of the sequence is 3 and the right bound
	 *         is 5, this function will return the string "2:3-5"
	 */
	public java.lang.String toString() {
		return row + DOTS + left + DASH + right;
	}
	
	/**
	 * @return The row on which the move is performed
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * @return The left bound of the stick sequence to mark
	 */
	public int getLeftBound() {
		return left;
	}
	
	/**
	 * @return The right bound of the stick sequence to mark
	 */
	public int getRightBound() {
		return right;
	}
}
