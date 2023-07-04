import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.LongPollingBot;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Main {

    public static void main (String [] args) throws Exception {

        TicTacToeBot myGame = new TicTacToeBot();
        myGame.PlayGame();

        //code for register new bot, us it once

        /* try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot((LongPollingBot) new CreateBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } */
    }
}
