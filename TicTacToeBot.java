import java.util.Arrays;
import java.util.InputMismatchException;

public class TicTacToeBot extends TicTacToe {

    @Override
    public String displayBoard(char[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (j == board[i].length - 1) System.out.print(board[i][j]);
                else System.out.print(board[i][j] + " | ");
            }

            //System.out.println();
        }

        return Arrays.deepToString(board);
    }

    @Override
    public Number[] GetPlayerCoordinate(char [][] board, TelegramBot botTelegram) throws InterruptedException {

        Number[] playerMoveCoordinates = new Number[2];

        int coordinateX;
        int coordinateY;

        while (true) {

            try {

                botTelegram.sendMessage("Enter coordinate X ...");

                String inputCoordinateX = botTelegram.receiveMessage();

                coordinateX = Integer.parseInt(inputCoordinateX);

                botTelegram.sendMessage("Enter coordinate Y ...");

                String inputCoordinateY = botTelegram.receiveMessage();

                coordinateY = Integer.parseInt(inputCoordinateY);


            } catch (InputMismatchException | InterruptedException e) {
                botTelegram.sendMessage("input must be a number");
                botTelegram.receiveMessage();
                continue;
            }

            if (coordinateX >= 0 && coordinateX < 3 && coordinateY >= 0 && coordinateY < 3) {

                if (board[coordinateX][coordinateY] == ' ') {
                    playerMoveCoordinates[0] = coordinateX;
                    playerMoveCoordinates[1] = coordinateY;
                    return playerMoveCoordinates;
                }
                botTelegram.sendMessage("Already taken!");
            }
            botTelegram.sendMessage("Wrong coordinate.Please enter a coordinate between 0 and 2 :) ");
        }
    }


    @Override
    public void playGame() throws Exception {

        TelegramBot botTelegram = new TelegramBot();


        char [][] board = {
                {' ', ' ', ' '},
                {' ', ' ', ' '},
                {' ', ' ', ' '}
        };

        botTelegram.sendMessage(displayBoard(board));

        botTelegram.sendMessage("Hi Player. Select X or O : ");

        String inputSymbol = botTelegram.receiveMessage();
        System.out.println("inputSymbol string == " + inputSymbol.toString());

        char[] charsSymbolFromString = inputSymbol.toCharArray();
        char playerSymbol = charsSymbolFromString[0];
        System.out.println("playerSymbol char == " + playerSymbol);

        char computerSymbol = ' ';

        while ( ! ((playerSymbol == 'X') || (playerSymbol == 'O') )) {

            botTelegram.sendMessage("You must pick either X or O ! ");
            botTelegram.sendMessage("Player, please select X or O: ");

            inputSymbol = botTelegram.receiveMessage();
            charsSymbolFromString = inputSymbol.toCharArray();
            playerSymbol = charsSymbolFromString[0];
        }

        botTelegram.sendMessage(displayBoard(board));

        if (playerSymbol == 'X') {
            computerSymbol = 'O';
        } else {
            computerSymbol = 'X';
        }

        while ( getWinner(board) == '$' && isAnyMovePossible(board) ) {

            botTelegram.sendMessage("Player is your turn..");

            Number[] playerCoordinates = GetPlayerCoordinate(board, botTelegram);

            int coordinateX = (int) playerCoordinates[0];
            int coordinateY = (int) playerCoordinates[1];

            executeMove(board, coordinateX,  coordinateY, playerSymbol);
            botTelegram.sendMessage(displayBoard(board));

            if ( getWinner(board) == '$' && isAnyMovePossible(board) ) {
                botTelegram.sendMessage("My turn...");
                Number[] computerCoordinates = getComputerCoordinte(board);

                coordinateX = (int) computerCoordinates[0];
                coordinateY = (int) computerCoordinates[1];

                executeMove(board, coordinateX,  coordinateY, computerSymbol);
                botTelegram.sendMessage(displayBoard(board));
            }
        }

        char winner = getWinner(board);
        botTelegram.sendMessage("winner : " + winner);

        if (winner == playerSymbol) {
            botTelegram.sendMessage("You win!");

        } else if (winner == computerSymbol) {
            botTelegram.sendMessage("I win!");

        } else if (winner == '$') {
            botTelegram.sendMessage("The match ends in a Draw!");
        }

        botTelegram.sendMessage("Game end!");
    }
}
