package ktbyte.assistant.app.todo;

import java.util.List;
import java.util.ArrayList;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class ViewAction extends Action {
	
	List<String> todoList;

	public ViewAction(ArrayList<String> todoList) {
		this.todoList = todoList;
	}

	@Override
	public void doCommand(String command) {
		// TODO Auto-generated method stub
		Assistant assistant = Assistant.getInstance();
		assistant.displayItem(new Response(todoList.toString()));
		
		
	}

	@Override
	public double getLikelihood(String command) {
		// TODO Auto-generated method stub
		if(command.startsWith("view")) {
			return 100;
		}
		return 0;
	}

	
}
