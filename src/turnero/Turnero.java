/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package turnero;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import np.com.ngopal.animation.AnimationType;
import np.com.ngopal.control.cell.AnimatedListCell;
import oracle.jrockit.jfr.events.EventHandler;
 
/**
 * @web http://java-buddy.blogspot.com/
 */
public class Turnero extends Application {
    URL resource = getClass().getResource("resources/doorbell-1.mp3");
    Media media;
    MediaPlayer mediaPlayer;
    
    ObservableList<String> myObservableList;
    ListView<String> listView; 
    class MyObject{
        String day;
        int number;
         
        MyObject(String d, int n){
            day = d;
            number = n;
        }
         
        String getDay(){
            return day;
        }
         
        int getNumber(){
            return number;
        }
    }
     
    List<String> myList;
     
    //Create dummy list of MyObject
    private void prepareMyList(){
        myList = new ArrayList<>();
        /*myList.add("Turno 1");
        myList.add("Turno 2");
        myList.add("Turno 3");
        myList.add("Turno 4");
        myList.add("Turno 5");
        myList.add("Turno 6");
        */
        /*myList.add(new MyObject("Wednesday", 90));
        myList.add(new MyObject("Thursday", 30));
        myList.add(new MyObject("Friday", 62));
        myList.add(new MyObject("Saturday", 65));
                */
    }
    
    public void runTask() {
		//for(int i = 1; i <= 10; i++) {
                while(true){
			try {
				//String status = "Processing " + i + " of " + 10;

				// Update the Label on the JavaFx Application Thread
				Platform.runLater(() -> 
                                    {//colar codigo aqui
                                        myObservableList.add("VALE");
                                        listView.getSelectionModel().selectLast();
                                        listView.scrollTo(listView.getItems().size()-1);
                                        Media media = new Media(resource.toString());
                                        MediaPlayer mediaPlayer = new MediaPlayer(media);
                                        mediaPlayer.setOnError(() -> {
                                            String errorMessage = media.getError().getMessage();
                                        });
                                        
                                        mediaPlayer.play();
                                        
                                    }
                                );
			
				//System.out.println(status);
				Thread.sleep(5000);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    }    
 
    @Override
    public void start(Stage primaryStage) {

        /*------------------*/
        Runnable task = () -> runTask();
        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);

        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);

        // Start the thread
        backgroundThread.start();
        
        
        /*--------------------*/
 
        primaryStage.setTitle("Turnero");
        prepareMyList();
        listView = new ListView<>();
        listView.setCellFactory(AnimatedListCell.forListView(AnimationType.ROTATE_RIGHT, AnimationType.FADE_OUT));
        
        myObservableList = FXCollections.observableList(myList);
        listView.setItems(myObservableList);
         
        Font.loadFont(getClass().getResource("segoesc.ttf").toExternalForm(), 12);
 
        StackPane root = new StackPane();
        
        root.getChildren().add(listView);
        Button button = new Button();
        button.setText("Agregar Objeto");
        button.setOnAction((ActionEvent e) -> {
            //myList.add("VALE");
            
            //listView.setCellFactory(AnimatedListCell.forListView(AnimationType.ROTATE_RIGHT, AnimationType.FADE_OUT));
            //listView.setItems(myObservableList);
            
        });
        root.getChildren().add(button);
        Scene scene = new Scene(root, 300, 250);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}