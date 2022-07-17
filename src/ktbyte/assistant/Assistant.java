package ktbyte.assistant;


import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField; import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import javafx.util.Callback;
import ktbyte.assistant.app.Action;
import ktbyte.assistant.app.App;
import ktbyte.assistant.app.Displayable;
import ktbyte.assistant.app.Response;
import ktbyte.assistant.app.echo.EchoApp;
import ktbyte.assistant.app.lyrics.LyricsApp;
import ktbyte.assistant.app.todo.ToDoApp;
import ktbyte.assistant.app.weather.WeatherApp;

/**
 * Main class for our Virtual Assistant. Include application UI elements
 * and provides functionality to handle command by delegating
 * it to different apps. 
 */
public class Assistant extends Application {

  private static Assistant instance;

  /**
   * Provide a way to get the instance of the only Assistant
   * object created through the lifecycle of the application
   */
  public static Assistant getInstance() {
    return instance;
  }
  
  public static void main(String[] args) {
    launch();
  }
  
  public Assistant() {
    instance = this;
  }
  
  private TextField commandTextField = new TextField();
  private Button goButton = new Button("Go");
  private ObservableList<Displayable> displayItems = FXCollections.observableArrayList();
  
  private List<Action> actions = new ArrayList<>();
  private ListView<Displayable> displayItemsListView = new ListView<Displayable>(displayItems);
  
  private App[] apps = getAvailableApps();

  @Override
  public void start(Stage stage) {
    initUILayout(stage);
    initUIListener();
    initAppActions();
  }
  
  /**
   * Registers available actions from each app
   */
  private void initAppActions() {
    for(App app : apps) {
      actions.addAll(app.getActions());
    }
  }
  
  private void initUIListener() {
    
    EventHandler<ActionEvent> doCommand = new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        String command = commandTextField.getText().toLowerCase();
        commandTextField.setText("");
        handleCommand(command);
      }
    };
    
    goButton.setOnAction(doCommand);
    commandTextField.setOnAction(doCommand);
    
    displayItemsListView.setCellFactory(new Callback<ListView<Displayable>, ListCell<Displayable>>() {
      
      @Override
      public ListCell<Displayable> call(ListView<Displayable> response) {
        return new ListCell<Displayable>() {
          @Override 
          protected void updateItem(Displayable response, boolean empty) {
            super.updateItem(response, empty);
            super.setText("");
            super.setGraphic(null);
            if(response != null) {
              response.update(this);
            }
          }
        };
      }
    });
  }
  
  private void initUILayout(Stage stage) {
    
    BorderPane root = new BorderPane();
    root.setPadding(new Insets(10, 10, 10, 10));
    root.setCenter(displayItemsListView);
    
    HBox hbox = new HBox();
    hbox.setSpacing(10);
    hbox.setPrefHeight(50);
    hbox.setAlignment(Pos.CENTER);
    
    HBox.setHgrow(commandTextField, Priority.ALWAYS);
    hbox.getChildren().addAll(new Label("Command:"), commandTextField, goButton);
    root.setBottom(hbox);
    stage.setTitle("KTByte Virtual Assistant");
    stage.setScene(new Scene(root, 600, 400));
    stage.show();
  }
  
  /**
   * Handle an inputed command by 
   * finding the most relevant AppAction to run it.
   * If most relevant AppAction does not have enough relevance score, 
   * respond by indicating so
   * 
   * @param command
   */
  private void handleCommand(String command) {
    // 
    Action bestAction = null;
    double bestActionScore = 0;
    for(Action action : actions) {
      double actionScore = action.getLikelihood(command);
      if(actionScore >= bestActionScore) {
        bestAction = action;
        bestActionScore = actionScore;
      }
    }
    
    if(bestAction != null) {
      System.out.println(command + ", best action: " + bestAction.getClass() + ", score: " + bestActionScore);
    }
    displayItems.add(new EnteredCommand(command));
    if(bestActionScore > 0.5) {
      try {
        bestAction.doCommand(command);
      } catch(Exception e) {
	e.printStackTrace();
        displayItem(new Response("Sorry, there was an error"));
      }
    } else {
      displayItem(new Response("Sorry, I can't understand your command"));
    }
    
  }
  
  /**
   * Adds an item to the displayable list
   * @param item
   */
  public void displayItem(Displayable item) {
    displayItems.add(item);
    displayItemsListView.scrollTo(item);
  }
  
  private static App[] getAvailableApps(){
    // TODO: add more apps avilable to the Virtual Assistant here
    return new App[]{new WeatherApp(), new EchoApp(), new LyricsApp(), new ToDoApp()};
  }
}


