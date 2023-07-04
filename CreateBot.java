import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class CreateBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        System.out.println(update.getMessage().getText());
        System.out.println(update.getMessage().getChatId().toString()); // get chatID to use in your config 
      
    }

    @Override
    public String getBotUsername() {
        //
        return "@yourBotUserame";
    }

    @Override
    public String getBotToken() {
        //
        return "your bot token";
    }

}
