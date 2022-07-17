package ktbyte.assistant.app.todo;

import java.util.ArrayList;
import java.util.List;


import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;


public class AddAction extends Action{

	List<String> todoList;
	
	public AddAction(ArrayList<String> todoList) {
		this.todoList = todoList;
	}


	@Override
	public void doCommand(String command) {
		// TODO Auto-generated method stub
		Assistant assistant = Assistant.getInstance();
		assistant.displayItem(new Response("added"));
		todoList.add(command.replace("add ", ""));

		
	}

	@Override
	public double getLikelihood(String command) {
		// TODO Auto-generated method stub
		if(command.startsWith("add")) {
			return 100;
		}
		return 0;
	}

}
