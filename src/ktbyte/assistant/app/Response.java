package ktbyte.assistant.app;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;

public class Response implements Displayable {
  
  private String response;
  
  public Response(String response) {
    this.response = response;
  }

  @Override
  public void update(ListCell<Displayable> cell) {
    
    cell.setAlignment(Pos.CENTER_RIGHT);
    cell.setText(response);
  }

}


