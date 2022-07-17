package ktbyte.assistant.app;

import javafx.scene.control.ListCell;

public interface Displayable {
  void update(ListCell<Displayable> cell);
}
