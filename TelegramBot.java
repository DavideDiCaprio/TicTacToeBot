import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import static java.lang.Thread.sleep;

public class TelegramBot {

    public JSONObject getReceivedMessage() {

        String apiToken = "";

        String request = "https://api.telegram.org/bot" + apiToken + "/getUpdates"; 

        String jsonText = null;

        try {

            StringBuilder result = new StringBuilder();
            URL url = new URL(request);
            URLConnection conn = url.openConnection();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

            jsonText = String.valueOf(result);
            rd.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        JSONObject jsonObject = new JSONObject(jsonText);
        JSONArray resultArray = jsonObject.getJSONArray("result");

        if (resultArray.length() == 0) {
            return null;
        }

        // Get the last message from the result list
        JSONObject lastMessageObject = resultArray.getJSONObject(resultArray.length() - 1);
        JSONObject messageObject = lastMessageObject.getJSONObject("message");

        return messageObject;
    }

    public void sendMessage(String message) {

        //Add chatId
        String userChatID = "";

        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";

        //Add Telegram token
        String apiToken = "";

        urlString = String.format(urlString, apiToken, userChatID, message);

        try {
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection();
            InputStream is = new BufferedInputStream(conn.getInputStream());

            //getting text, we can set it to any TextView
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String inputLine = "";
            StringBuilder sb = new StringBuilder();
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            //You can set this String to any TextView
            String response = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String receiveMessage() throws InterruptedException {

        JSONObject data = getReceivedMessage();
        int lastMessageID = data.getInt("message_id");

        while (true) {

            // TO DO fix sleep 
            sleep(5000); // 

            JSONObject newData = getReceivedMessage();
            int newMessageID = newData.getInt("message_id");

            if (newMessageID > lastMessageID) {

                lastMessageID = newMessageID;

                String lastMessageText = newData.getString("text");

                return lastMessageText;

            }
        }
    }

}
