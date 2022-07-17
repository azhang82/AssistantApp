package ktbyte.assistant;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import ktbyte.assistant.app.Displayable;


class EnteredCommand implements Displayable {
  
  private String command;
  
  public EnteredCommand(String command) {
    this.command = command;
  }
  
  public void update(ListCell<Displayable> cell) {
    cell.setText("Command: " + command);
    cell.setAlignment(Pos.CENTER_LEFT);
  }
}
