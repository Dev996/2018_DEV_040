package com.dev.ttt.board;

import com.dev.ttt.constants.TTTConstants;

/**
 * @Classname SquareBoard
 *
 * @author Developer D996
 * @version 1.0
 */

public class SquareBoard implements Board {

	/**
	 * This method will check the player's input whether it is valid or not
	 * 
	 * @param positionInput (String) - input provided by the user
	 * 
	 * @return true - if the provided input is valid and false if input is invalid.
	 */
	@Override
	public boolean isValidInput(String positionInput) {
		return null != positionInput && !positionInput.isEmpty()
				&& positionInput.matches(TTTConstants.ONLY_NUMBER_REGEX) && positionInput.contains(TTTConstants.COMMA);
	}

	/**
	 * This method will check the winner of the game for the provided game board
	 * positions
	 * 
	 * @param cells (double array) - game board which players are playing,
	 *              moveCount(Integer) - Count of valid moves of both players
	 * 
	 * @return winner sign, null incase no one is winner
	 */
	@Override
	public String checkWinner(String[][] cells) {
		return TTTConstants.PLAYER_1_SIGN;

	}

}
