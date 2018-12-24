package com.dev.ttt.game;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import org.junit.Test;
import org.mockito.Mockito;

import com.dev.ttt.board.SquareBoard;
import com.dev.ttt.console.ConsoleScreen;
import com.dev.ttt.constants.TTTConstants;

public class SquareBoardTest {

	private static final SquareBoard squareBoard = Mockito.mock(SquareBoard.class);

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
	public void checkIsValidInputMethodReturnsFalseForEmptyInput() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput(",1");
		assertEquals(false, result);
	}

	@Test
	public void checkIsValidInputMethodReturnsFalseForEmptySecondPosition() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput("1,");
		assertEquals(false, result);
	}

	@Test
	public void checkIsValidInputMethodReturnsFalseForNonNumberInput() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput("a,1");
		assertEquals(false, result);
	}

	@Test
	public void checkIsValidInputMethodReturnsFalseForInvalidInput() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput("4,0");
		assertEquals(false, result);
	}

	@Test
	public void checkIsValidInputMethodReturnsFalseForInvalidFirstPosition() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput("-1,4");
		assertEquals(false, result);
	}

	@Test
	public void checkIsValidInputMethodReturnsFalseForNegativeInput() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput("-2,-1");
		assertEquals(false, result);
	}

	@Test
	public void checkIsValidInputMethodReturnsTrueForValidInput() {
		SquareBoard square = new SquareBoard();
		boolean result = square.isValidInput("2,2");
		assertEquals(true, result);
	}

	@Test
	public void shouldPrintWinnerIsNullCheckWinnerMethodForValidInput() {
		String[][] cells = new String[3][3];
		SquareBoard square = new SquareBoard();
		Mockito.when(squareBoard.checkColumnAndDiagonal(null, 2, 2)).thenReturn(null);
		String actual = square.checkWinner(cells);
		assertEquals(null, actual);
	}

	@Test
	public void shouldPrintWinnerSignInCheckWinnerMethodForValidInput() {
		String[][] cells = { { "1", "-1", "-1" }, { "1", null, "-1" }, { "1", "1", "-1" } };
		SquareBoard square = new SquareBoard();
		String actual = square.checkWinner(cells);
		assertEquals(TTTConstants.PLAYER_1_SIGN, actual);
	}

	@Test
	public void shouldPrintNullInForDrawGameInCheckWinnerMethod() {
		String[][] cells = { { "1", "-1", "1" }, { "-1", "-1", "1" }, { "-1", "1", "-1" } };
		SquareBoard square = new SquareBoard();
		String actual = square.checkWinner(cells);
		assertEquals(null, actual);
	}

	@Test
	public void checkWinnerIsNullForNullColumnValue() {
		SquareBoard square = new SquareBoard();
		String result = square.checkColumnAndDiagonal(null, 0, 0);
		assertEquals(null, result);
	}

	@Test
	public void checkWinnerIsNullForImproperDiagonalValue() {
		SquareBoard square = new SquareBoard();
		int[] dummyColumn = { -1, 1, 1 };
		assertEquals(null, square.checkColumnAndDiagonal(null, 4, 0));
		assertEquals(null, square.checkColumnAndDiagonal(dummyColumn, 4, 0));
		assertEquals(null, square.checkColumnAndDiagonal(dummyColumn, 0, 4));
		assertEquals(null, square.checkColumnAndDiagonal(dummyColumn, 2, 0));
		assertEquals(null, square.checkColumnAndDiagonal(dummyColumn, 0, 2));
	}

	@Test
	public void checkWinnerForProperValues() {
		SquareBoard square = new SquareBoard();
		int[] columnForPlayer1 = { 3, 1, 1 };
		int[] columnForPlayer2 = { -3, 1, 1 };
		assertEquals(TTTConstants.PLAYER_1_SIGN, square.checkColumnAndDiagonal(columnForPlayer1, 3, 0));
		assertEquals(TTTConstants.PLAYER_1_SIGN, square.checkColumnAndDiagonal(null, 3, 0));
		assertEquals(TTTConstants.PLAYER_1_SIGN, square.checkColumnAndDiagonal(null, 0, 3));
		assertEquals(TTTConstants.PLAYER_2_SIGN, square.checkColumnAndDiagonal(columnForPlayer2, 3, 0));
		assertEquals(TTTConstants.PLAYER_2_SIGN, square.checkColumnAndDiagonal(null, 0, -3));
		assertEquals(TTTConstants.PLAYER_2_SIGN, square.checkColumnAndDiagonal(null, -3, 0));
	}

	@Test
	public void shouldNotCallDrawMethodForHigherMoveCount() {
		String[][] cells = new String[3][3];
		ConsoleScreen screen = new ConsoleScreen();
		ConsoleScreen spy = Mockito.spy(screen);
		Mockito.doNothing().when(spy).draw(cells);
		squareBoard.draw(cells, 10);
		verifyNoMoreInteractions(spy);
	}

}
