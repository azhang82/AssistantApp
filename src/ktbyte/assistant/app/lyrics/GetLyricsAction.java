package ktbyte.assistant.app.lyrics;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Displayable;
import ktbyte.assistant.app.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONString;

import java.util.Arrays;
import java.util.List;

public class GetLyricsAction extends Action {

    @Override
    public void doCommand(String command) {
//        List<String> words = Arrays.asList(command.split(" "));
        HttpRequest req = null;
//        String artist = words.get(words.size() - 2);

        int byIndex = command.indexOf("by");
        int ofIndex = command.indexOf("of");
        String artist = command.substring(byIndex + 3);
        String title = command.substring(ofIndex + 3, byIndex -1);
//        for(int i = 0; i < words.size(); i++) {
//            if(words.get(i).equals("by")) {
//                byIndex = i;
//            }
//            if(words.get(i).equals("of")) {
//                ofIndex = i;
//            }
//        }

        if (command.contains("lyrics")){
            req = Unirest.get("https://api.lyrics.ovh/v1/"+artist+"/"+title);
            try {
                HttpResponse<JsonNode> response = req.asJson();
                if(response != null) {
                    String lyrics = response.getBody().getObject().getString("lyrics");
                    Assistant.getInstance().displayItem(new Response(lyrics));
                }

            } catch (UnirestException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }

    // get lyrics of The DUck Dong by YYY
    @Override
    public double getLikelihood(String command) {
        if (command.startsWith("get lyrics of")) {
            return 100;
        }
        return 0;
    }
}
