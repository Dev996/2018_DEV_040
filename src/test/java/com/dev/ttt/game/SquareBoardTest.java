package com.dev.ttt.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.dev.ttt.board.SquareBoard;
import com.dev.ttt.constants.TTTConstants;

public class SquareBoardTest {

	@Test
	public void checkIsValidInputMethodReturnsFalseForNullValue() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput(null);
		assertEquals(false, result);
	}

	@Test
	public void checkIsValidInputMethodReturnsFalseForEmptyValue() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput("");
		assertEquals(false, result);
	}

	@Test
	public void checkIsValidInputMethodReturnsFalseForNonCommaInput() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput("12");
		assertEquals(false, result);
	}

	@Test
	public void shouldPrintWinnerSignInCheckWinnerMethodForValidInput() {
		String[][] cells = { { "1", "-1", "-1" }, { "1", null, "-1" }, { "1", "1", "-1" } };
		SquareBoard square = new SquareBoard();
		String actual = square.checkWinner(cells);
		assertEquals(TTTConstants.PLAYER_1_SIGN, actual);
	}

}
