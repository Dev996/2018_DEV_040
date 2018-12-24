package com.dev.ttt.console;

import static java.util.Arrays.asList;

import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.dev.ttt.constants.TTTConstants;

/**
 * @Classname ConsoleScreen
 *
 * @author Developer D996
 * @version 1.0
 */
public class ConsoleScreen implements Screen {

	private static Logger log = Logger.getLogger(ConsoleScreen.class.getName());

	/**
	 * This draw method will iterate every single row of the cell which is the game
	 * board
	 * 
	 * @param cells (Double array) - Game board
	 * 
	 * @return Nothing.
	 */
	@Override
	public void draw(String[][] cells) {
		asList(cells).forEach(this::drawRow);
	}

	/**
	 * This method will print the board exactly by iterating the positions of a row.
	 * 
	 * @param row (array) - Single row of the game board
	 * 
	 * @return Nothing.
	 */
	public void drawRow(String[] row) {
		log.info(asList(row).stream().map(this::findMark).collect(Collectors.joining("  ")) + TTTConstants.SPACE);
	}

	/**
	 * This method will find the position value and return a string as per the value
	 * 
	 * @param cell (String) - value of the position in the game board double array
	 * 
	 * @return value to be printed in the console.
	 */
	public String findMark(String cell) {
		if (null != cell && !cell.isEmpty()) {
			return cell == TTTConstants.PLAYER_1_VALUE ? TTTConstants.PLAYER_1_SIGN : TTTConstants.PLAYER_2_SIGN;
		} else {
			return TTTConstants.DOT;
		}
	}
}
