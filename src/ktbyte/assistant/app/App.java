package ktbyte.assistant.app;

import java.util.List;

// an App is a collections of related features supported by 
// the Virtual Assistant
// Known Apps: Time app, Weather App, Reminders App, Spotify App, 
public abstract class App {
  // gets a list of actions associated with this app
  public abstract List<Action> getActions();
}
