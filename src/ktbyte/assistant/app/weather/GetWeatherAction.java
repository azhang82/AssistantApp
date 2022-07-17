 
package ktbyte.assistant.app.weather;
 
import java.util.Arrays;
import java.util.List;
 
import org.json.JSONObject;
 
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
 
import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;
 
public class GetWeatherAction extends Action {
 
	String[] keywords = { "weather", "what", "is", "in" };
	double[] scores =   { 3,          0.2,    0.2,  0.2 };
 
	private static final String API_KEY = "a53af5e648cd61f1249fbe858fd34f71";
 
	@Override
	public void doCommand(String command) {
 
		// remove keywords from the command to extract the location
		// we want to query the weather for
		List<String> words = Arrays.asList(command.split(" "));
 
		HttpRequest req = null;
		String location = words.get(words.size() - 1);
		
		if (words.contains("zip") || words.contains("zipcode")) {
			req = Unirest.get("https://api.openweathermap.org/data/2.5/weather")
			             .queryString("zip", location)
					         .queryString("appid", API_KEY)
					         .queryString("units", "imperial");
			
		} else {
			req = Unirest.get("https://api.openweathermap.org/data/2.5/weather")
			    .queryString("q", location)
					.queryString("appid", API_KEY)
					.queryString("units", "imperial");
		}
 
    System.out.println(req.getUrl());
		try {
		  HttpResponse<JsonNode> boom = req.asJson();
		  System.out.println(boom);
			JsonNode node = boom.getBody();
			System.out.println(node);
			handleResult(node);
		} catch (UnirestException e) {
			System.out.println("request error occurred: " + e);
		}
 
	}
 
	@Override
	public double getLikelihood(String command) {
		double score = 0;
		for (int i = 0; i < keywords.length; i++) {
			String keyword = keywords[i];
			if (command.contains(keyword)) {
				score += scores[i];
			}
		}
		return score;
	}
 
	private static void handleResult(JsonNode node) {
	  
	  Assistant assistant = Assistant.getInstance();
	  
 
		JSONObject json = node.getObject();
		
		String city = json.getString("name");
		double temp = json.optJSONObject("main").getDouble("temp");
		String description = json.optJSONArray("weather").optJSONObject(0).optString("description");
 
		
		System.out.println("temperature: " + temp + " " + description);
		assistant.displayItem(
				new Response("The temperature in " + city + " is " + temp + " degrees with " + description));
	}
}
 
 
 
 
 

 
 
 
// CS01b (full-semester Spring classes) today: try to finish everything!
//    ESSENTIAL to have asked any questions and tried making a request to your Web API
 
// if you finish early:
//   explore other APIs
//   chosen API may allow other things
//   look at using PrintWriter and Scanner to save data (for TodoList, or API-related)
/*
 
 
test image link:
static final String nyanUrl = "https://upload.wikimedia.org/wikipedia/en/thumb/e/ed/Nyan_cat_250px_frame.PNG/220px-Nyan_cat_250px_frame.PNG";
 
 
 
classes for images (javafx):
   Image
   ImageView
 
   (some kind of Displayable type)
 
    ListCell.setGraphic
 
 
HOW TO ZIP UP YOUR PROJECT
 
open Terminal Emulator in VM applications
type ls, enter            at any time to see what folders you can access
type cd [folder], enter   to change directory to that folder (dont include []s when you enter it)
 
navigate to your workspace folder, so that when you type ls, you see AssistantApp folder listed
 
then:
 
zip -r Assistant.zip AssistantApp
  (or, your folder name if you didnt call it/renamed it other than AssistantApp)
*/

