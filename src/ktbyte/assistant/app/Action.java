package ktbyte.assistant.app;

public abstract class Action {
  
  // performs action on the input command
  public abstract void doCommand(String command);
  
  // returns the likelihood score that the input command should be
  // handled by this AppAction
  public abstract double getLikelihood(String command);
}
