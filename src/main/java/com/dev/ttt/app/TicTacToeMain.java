package com.dev.ttt.app;

import org.apache.log4j.Logger;

import com.dev.ttt.board.Board;
import com.dev.ttt.board.SquareBoard;
import com.dev.ttt.constants.TTTConstants;

/**
 * The TicTacToe program implements an application that simply allows two users
 * to play a 3 x 3 tic-tac-toe game in the console.
 *
 * @author Developer D996
 * @version 1.0
 */

public class TicTacToeMain {

	private static final Logger log = Logger.getLogger(TicTacToeMain.class.getName());

	/**
	 * This is the main method which initialize the tic-tac-toe game.
	 * 
	 * @param args Unused.
	 * 
	 * @return Nothing.
	 */
	public static final void main(String[] args) {
		String[][] cells = new String[3][3];
		Board board = new SquareBoard();
		log.info(TTTConstants.GAME_INTRO);
		board.draw(cells, 0);
	}
}
