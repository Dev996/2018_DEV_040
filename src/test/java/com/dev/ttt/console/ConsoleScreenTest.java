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

}
