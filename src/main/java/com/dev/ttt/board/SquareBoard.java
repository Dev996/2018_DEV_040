package com.dev.ttt.board;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.dev.ttt.console.ConsoleScreen;
import com.dev.ttt.console.Screen;
import com.dev.ttt.constants.TTTConstants;

/**
 * @Classname SquareBoard
 *
 * @author Developer D996
 * @version 1.0
 */

public class SquareBoard implements Board {

	private static Logger log = Logger.getLogger(SquareBoard.class.getName());

	private String wonBy = null;

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
	 * This draw method will help players to play the game. Also prints the board
	 * with the position chosen by both players and find the winner appropriately.
	 * 
	 * @param cells (Double array) - Game board, moveCount (Integer) - Valid move
	 *              count of both players
	 * 
	 * @return Nothing.
	 */
	@Override
	public void draw(String[][] cells, int moveCount) {
		Screen screen = new ConsoleScreen();
		while (moveCount < 9) {
			screen.draw(cells);
			String userSign = null;
			if (moveCount % 2 == 0) {
				userSign = TTTConstants.PLAYER_1_SIGN;
			} else {
				userSign = TTTConstants.PLAYER_2_SIGN;
			}
			String positionInput = getInput(TTTConstants.GET_INPUT_STRING + userSign);
			moveCount = screen.updateBoard(cells, userSign, moveCount, positionInput);
			if (moveCount > 2) {
				wonBy = checkWinner(cells);
				if (wonBy != null) {
					log.info(TTTConstants.WINNER + moveCount);
					moveCount = 9;
				}
			}
		}
		if (moveCount == 9) {
			screen.draw(cells);
			if (wonBy == null) {
				log.info(TTTConstants.DRAW);
			}
			if (!resetGame(getInput(TTTConstants.RESET_GAME))) {
				resetGame(getInput(TTTConstants.RESET_GAME));
			}
		}
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
				if (cells[i][j] != null) {
					row = row + Integer.parseInt(cells[i][j]);
				}
				if (cells[j][i] != null) {
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

	/**
	 * This method will get the input provided by the user. Its a generic method
	 * which can be used to retrieve input from user for a specific message
	 * 
	 * @param message (String) - Message displayed to the user
	 * 
	 * @return input value provided by the user.
	 */
	@SuppressWarnings("resource")
	private String getInput(String message) {
		log.info(message);
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		if (null == input) {
			getInput(message);
		}
		return input;
	}

	/**
	 * This resetGame method will reset the game
	 * 
	 * @return true if game is reset or false
	 */
	public boolean resetGame(String userInput) {
		if (null != userInput && TTTConstants.YES.equalsIgnoreCase(userInput)) {
			String[][] resetCell = new String[3][3];
			log.info(TTTConstants.GAME_INTRO);
			draw(resetCell, 0);
			wonBy = null;
			return true;
		} else {
			return false;
		}
	}
}
