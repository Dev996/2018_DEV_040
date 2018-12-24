package com.dev.ttt.board;

/**
 * @Classname Board Interface
 *
 * @author Developer D996
 * @version 1.0
 */

public interface Board {

	boolean isValidInput(String positionInput);

	String checkWinner(String[][] cells);

	void draw(String[][] cells, int moveCount);

}
