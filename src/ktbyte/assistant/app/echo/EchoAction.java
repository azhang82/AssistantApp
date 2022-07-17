package ktbyte.assistant.app.echo;

import ktbyte.assistant.Assistant;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.Response;

public class EchoAction extends Action{

	@Override
	public void doCommand(String command) {
		// TODO Auto-generated method stub
		Assistant assistant = Assistant.getInstance();
		assistant.displayItem(new Response(command));
	}

	@Override
	public double getLikelihood(String command) {
		if(command.startsWith("echo")) {
				return 100;
	}
		return 0;
	}

}
