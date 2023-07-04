import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TicTacToeTest {

    TicTacToe testGame = new TicTacToe();

    @org.junit.jupiter.api.Test
    void executeMoveTest() throws Exception {

        char [][] board = {
                {'X', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        Throwable thrown = assertThrows(Exception.class, () -> testGame.executeMove(board,10,11,'X'));
        assertEquals("Out of range", thrown.getMessage());

        Throwable thrown1 = assertThrows(Exception.class, () -> testGame.executeMove(board,0,0,'X'));
        assertEquals("Coordinate already occupied", thrown1.getMessage());
    }

    @org.junit.jupiter.api.Test
    void isAnyMovePossibleTest() {

        char [][] emptyBoard = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        assertTrue((testGame.isAnyMovePossible(emptyBoard)));

        char [][] fullBoard = {
                {'X', 'X', 'O'},
                {'X', 'O', 'O'},
                {'X', 'O', 'O'}
        };


        assertFalse((testGame.isAnyMovePossible(fullBoard)));
    }


    @org.junit.jupiter.api.Test
    void CheckHorizontalWinTest() {

        char [][] boardO = {
                {' ', ' ', 'O'},
                {' ', ' ', ' '},
                {'O', 'O', 'O'}
        };

        assertEquals('O',testGame.checkHorizontalWin(boardO));

        char [][] boardX = {
                {'X', ' ', ' '},
                {' ', ' ', ' '},
                {'X', 'X', 'X'}
        };

        assertEquals('X',testGame.checkHorizontalWin(boardX));

        char [][] boardNoWinner = {
                {'X', ' ', ' '},
                {' ', 'O', 'X'},
                {'X', ' ', 'O'}
        };

        assertEquals('$', testGame.checkHorizontalWin(boardNoWinner));

    }

    @org.junit.jupiter.api.Test
    void checkVerticalWinTest() {

       char[][] boardX = {
               {'X', ' ', ' '},
               {'X', ' ', ' '},
               {'X', 'X', 'X'},
        };

        assertEquals('O',testGame.checkVerticalWin(boardX));

        char[][] boardO = {
                {'O', ' ', ' '},
                {'O', ' ', ' '},
                {'O', 'X', 'X'},
        };

        assertEquals('O',testGame.checkVerticalWin(boardO));

        char[][] boardNoWinner = {
                {'X', ' ', ' '},
                {' ', ' ', ' '},
                {'X', 'X', 'X'},
        };

        assertNull(testGame.checkVerticalWin(boardNoWinner));

    }

    @org.junit.jupiter.api.Test
    void getWinnerTest() {

        char[][] boardX = {
                {'X', ' ', ' '},
                {'X', ' ', ' '},
                {'X', 'X', 'X'},
        };

        assertEquals('X',testGame.getWinner(boardX));

        char[][] boardO = {
                {'O', ' ', ' '},
                {'O', ' ', ' '},
                {'O', 'X', 'X'},
        };

        assertEquals('O',testGame.getWinner(boardO));

        char[][] boardNoWinner = {
                {'X', ' ', ' '},
                {' ', ' ', ' '},
                {'X', 'X', 'X'},
        };

        assertEquals('$',testGame.getWinner(boardNoWinner));

    }

}
