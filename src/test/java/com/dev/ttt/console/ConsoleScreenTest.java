/**
 * 
 */
package com.dev.ttt.console;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.dev.ttt.board.SquareBoard;
import com.dev.ttt.constants.TTTConstants;

/**
 * @Classname ConsoleScreenTest
 *
 * @author Developer D996
 * @version 1.0
 */
public class ConsoleScreenTest {
	private static final SquareBoard squareBoard = Mockito.mock(SquareBoard.class);

	@Before
	public void setUp() throws Exception {
		Mockito.when(squareBoard.isValidInput(Mockito.anyString())).thenReturn(true);
	}

	@Test
	public void shouldFindPositionReturnDotIfInputIsNullAndEmpty() {
		ConsoleScreen consoleScreen = new ConsoleScreen();
		assertEquals(TTTConstants.DOT, consoleScreen.findMark(null));
		assertEquals(TTTConstants.DOT, consoleScreen.findMark(""));
	}

	@Test
	public void shouldFindPositionReturnPlayer1SignIfInputIs1() {
		ConsoleScreen consoleScreen = new ConsoleScreen();
		String actual = consoleScreen.findMark(TTTConstants.PLAYER_1_VALUE);
		assertEquals(TTTConstants.PLAYER_1_SIGN, actual);
	}

	@Test
	public void shouldPrintErrorMessageInUpdateBoardMethodForInvalidInput() {
		String[][] cells = new String[3][3];
		ConsoleScreen consoleScreen = new ConsoleScreen();
		int actual = consoleScreen.updateBoard(cells, "X", 2, "12");
		assertEquals(2, actual);
	}

	@Test
	public void shouldPrintErrorMessageInUpdateBoardMethodForExistingPositionInput() {
		String[][] newCells = { { "1", "-1", "-1" }, { "1", null, "-1" }, { "1", "1", "2" } };
		ConsoleScreen consoleScreen = new ConsoleScreen();
		int actual = consoleScreen.updateBoard(newCells, "X", 2, "0,0");
		assertEquals(2, actual);
	}

	@Test
	public void shouldUpdateBoardMethodForValidInput() {
		String[][] newCells = { { "1", "-1", "-1" }, { "1", null, "-1" }, { "1", "1", null } };
		ConsoleScreen consoleScreen = new ConsoleScreen();
		consoleScreen.updateBoard(newCells, "X", 2, "2,2");
		assertEquals("1", newCells[2][2]);
	}

}
