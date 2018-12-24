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
                   && positionInput.matches(TTTConstants.ONLY_NUMBER_REGEX) && positionInput.contains(TTTConstants.COMMA)
                   && 2 == positionInput.split(TTTConstants.COMMA).length
                   && !positionInput.split(TTTConstants.COMMA)[0].isEmpty()
                   && null != positionInput.split(TTTConstants.COMMA)[1]
                   && 0 <= Integer.parseInt(positionInput.split(TTTConstants.COMMA)[0])
                   && 3 > Integer.parseInt(positionInput.split(TTTConstants.COMMA)[0])
                   && 0 <= Integer.parseInt(positionInput.split(TTTConstants.COMMA)[1])
                   && 3 > Integer.parseInt(positionInput.split(TTTConstants.COMMA)[1]);
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
		int i;
		int j;
		int row = 0;
		int[] column = new int[3];
		int rightDiag = 0;
		int leftDiag = 0;
		int size = cells.length;

		for (i = 0; i < 3; i++) {
			for (j = 0; j < 3; j++) {
				if (cells[j][i] != null) {
					row = row + Integer.parseInt(cells[i][j]);
					column[i] = column[i] + Integer.parseInt(cells[j][i]);
				}
				if (i == j && cells[i][j] != null) {
					rightDiag = rightDiag + Integer.parseInt(cells[i][j]);
				}
			}
			if (row == 3) {
				return TTTConstants.PLAYER_1_SIGN;
			} else if (row == -3) {
				return TTTConstants.PLAYER_2_SIGN;
			}
			row = 0;
			if (cells[i][size - i - 1] != null) {
				leftDiag = leftDiag + Integer.parseInt(cells[i][size - i - 1]);
			}
		}
		return checkColumnAndDiagonal(column, rightDiag, leftDiag);
	}

	/**
	 * This checkColumnAndDiagonol method looks for a winner based on the inputs
	 * provided
	 * 
	 * @return winner sign if there is one or null
	 */
	public String checkColumnAndDiagonal(int[] column, int rightDiag, int leftDiag) {
		if (null != column) {
			for (int columnvalue : column) {
				if (columnvalue == 3) {
					return TTTConstants.PLAYER_1_SIGN;
				} else if (columnvalue == -3) {
					return TTTConstants.PLAYER_2_SIGN;
				}
			}
		}

		if (rightDiag == 3 || leftDiag == 3) {
			return TTTConstants.PLAYER_1_SIGN;
		} else if (rightDiag == -3 || leftDiag == -3) {
			return TTTConstants.PLAYER_2_SIGN;
		}
		return null;
	}

}
