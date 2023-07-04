import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public abstract class TicTacToe {

    public String displayBoard(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j == board[i].length - 1) System.out.print(board[i][j]);
                else System.out.print(board[i][j] + " | ");
            }

            System.out.println();
        }
        return null;
    }

    public Boolean isAnyMovePossible(char [][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == ' ') {
                    return true;
                }
            }
        }

        return false;
    }

    public Number [] getComputerCoordinte(char [][] board) {

        Number [] moveCoordinates = new Number[2];

        Random random = new Random();

        while (true){

            int x = random.nextInt(3);
            int y = random.nextInt(3);

            if (board[x][y] == ' ') {
                moveCoordinates[0] = x;
                moveCoordinates[1] = y;
                return moveCoordinates;
            }
        }
    }

    public char checkHorizontalWin(char [][] board) {

        char [] XWin = {'X', 'X', 'X'};
        char [] OWin = {'O', 'O', 'O'};


        for (int i = 0; i < board.length; i++) {

            if (Arrays.equals(board[i],XWin)) {
                return 'X';
            }
            if (Arrays.equals(board[i],OWin)) {
                return 'O';
            }
        }

        return '$';
    }

    public char checkVerticalWin(char[][] board) {

        if (board[0][0] == 'X' && board[1][0]== 'X' && board[2][0]== 'X') {
            return 'X';
        }

        if (board[0][1]== 'X' && board[1][1]== 'X' && board[2][1]== 'X') {
            return 'X';
        }

        if (board[0][2]== 'X' && board[1][2]== 'X' && board[2][2]== 'X') {
            return 'X';
        }

        if (board[0][0]== 'O' && board[1][0]== 'O' && board[2][0]== 'O') {
            return 'O';
        }

        if (board[0][1]== 'O' && board[1][1]== 'O' && board[2][1]== 'O') {
            return 'O';
        }

        if (board[0][2]== 'O' && board[1][2]== 'O' && board[2][2]== 'O') {
            return 'O';
        }

        return '$';

    }

    public char checkDiagonalWin(char[][] board) {

        if (board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X') {
            return 'X';
        }

        if (board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X') {

            return 'X';
        }

        if (board[0][0]== 'O' && board[1][1]== 'O' && board[2][2]== 'O') {
            return 'O';
        }

        if (board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O') {
            return 'O';
        }

        return '$';
    }

    public char getWinner(char[][] board) {

            if ( checkHorizontalWin(board) == 'X' || checkHorizontalWin(board) == 'O' ) {
                return checkHorizontalWin(board);

            } else if ( checkDiagonalWin(board) == 'X' || checkDiagonalWin(board) == 'O' ) {
                return checkDiagonalWin(board);

            } else if ( checkVerticalWin(board) == 'X' || checkVerticalWin(board) == 'O' ) {
                return checkVerticalWin(board);
            }

            return '$';
    }

    public Number[] getPlayerCoordinate(char [][] board) throws InterruptedException {

        Scanner playerMove = new Scanner(System.in);
        Number[] playerMoveCoordinates = new Number[2];

        int coordinateX;
        int coordinateY;

        while (true) {

            try {

                System.out.print("Enter coordinate X ...");
                coordinateX = playerMove.nextInt();
                System.out.print("Enter coordinate Y ...");
                coordinateY = playerMove.nextInt();

            } catch (InputMismatchException e) {
                System.out.println("input must be a number");
                playerMove.nextLine();
                continue;
            }

            if (coordinateX >= 0 && coordinateX < 3 && coordinateY >= 0 && coordinateY < 3) {

                if (board[coordinateX][coordinateY] == ' ') {
                    playerMoveCoordinates[0] = coordinateX;
                    playerMoveCoordinates[1] = coordinateY;
                    return playerMoveCoordinates;
                }

                System.out.println("Already taken!");
            }

            System.out.println("Wrong coordinate.Please enter a coordinate between 0 and 2 :) ");

        }
    }

    public char[][] executeMove(char [][] board, int x, int y, char symbol) throws Exception {

        if (!(x >= 0 && x < 3 && y >= 0 && y < 3)) throw new Exception("Out of range");

        if (!(board[x][y] == ' ')) throw new Exception("Coordinate already occupied");

        board[x][y] = symbol;
        return board;

    }

    public abstract Number[] getPlayerCoordinate(char[][] board, TelegramBot bot) throws InterruptedException;

    public void playGame() throws Exception {

        char [][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        System.out.print("Hi Player. Select X or O : ");

        Scanner scanner = new Scanner(System.in);

        char computerSymbol = ' ';
        char playerSymbol = scanner.next().charAt(0);


        while ( ! ((playerSymbol == 'X') || (playerSymbol == 'O') )) {

            System.out.println("You must pick either X or O ! ");
            System.out.println("Player, please select X or O: ");

            playerSymbol = scanner.next().charAt(0);
        }

        displayBoard(board);

        if (playerSymbol == 'X') {
            computerSymbol = 'O';
        } else {
            computerSymbol = 'X';
        }

        while ( getWinner(board) == '$' && isAnyMovePossible(board) ) {

            System.out.println("Player is your turn..");

            Number[] playerCoordinates = getPlayerCoordinate(board);

            int coordinateX = (int) playerCoordinates[0];
            int coordinateY = (int) playerCoordinates[1];

            executeMove(board, coordinateX,  coordinateY, playerSymbol);
            displayBoard(board);

            if ( getWinner(board) == '$' && isAnyMovePossible(board) ) {
                System.out.println("My turn...");
                Number[] computerCoordinates = getComputerCoordinte(board);

                coordinateX = (int) computerCoordinates[0];
                coordinateY = (int) computerCoordinates[1];

                executeMove(board, coordinateX,  coordinateY, computerSymbol);
                displayBoard(board);
            }
        }

        char winner = getWinner(board);
        System.out.println("winner : " + winner);

        if (winner == playerSymbol) {
            System.out.println("You win!");

        } else if (winner == computerSymbol) {
                System.out.println("I win!");

        } else if (winner == '$') {
                System.out.println("The match ends in a Draw!");

        }

        System.out.println("Game end!");

    }

        public void playTicTacToe() throws Exception {

            while (true) {

                System.out.println();

                System.out.println("-------- NEW MATCH --------");
                System.out.println();
                this.playGame();

            }

        }

    }
