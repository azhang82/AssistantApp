package ktbyte.assistant.app.todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.App;

public class ToDoApp extends App {
	
	ArrayList<String> todoList = new ArrayList<>();

	@Override
	public List<Action> getActions() {
		return Arrays.asList(new ViewAction(todoList), new DeleteAction(todoList), new AddAction(todoList));
	}
	 
}
