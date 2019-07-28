import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.*;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class Main extends Application{
	
	Scene home;
	
	public void start(Stage primaryStage) {
		try {
			
			BorderPane root = new BorderPane();
			
			Text header = new Text("Study Abroad Ranker");
			header.setFont(new Font(20));
			HBox headerBox = new HBox();
			headerBox.getChildren().add(header);
			root.setTop(headerBox);
			headerBox.setAlignment(Pos.CENTER);
			
			ListView<String> colleges = new ListView<String>();
			ObservableList<String> collegeNames = FXCollections.observableArrayList();
			Ranker ranker = new Ranker();
			Button addFile = new Button("+ Add new File");
			addFile.setOnAction(event->{
				FileChooser choose = new FileChooser();
				choose.setTitle("Load new food list");
				File file = choose.showOpenDialog(primaryStage);
				if (file != null) {
					int index = file.getAbsolutePath().length();
					if (file.getAbsolutePath().substring(index - 4, index).equals(".csv")) {
						try {
							ranker.loadFile(file.getAbsolutePath()); 
							collegeNames.addAll(ranker.getCollegeNames());
							colleges.setItems(collegeNames);
						}
						catch(FileNotFoundException except) {
							Alert error = new Alert(AlertType.ERROR, "File could not be opened!", ButtonType.CLOSE);
							error.show();
						}
					}
					else
					{
						Alert error = new Alert(AlertType.ERROR, "Select a valid .csv file!", ButtonType.CLOSE);
						error.show();
					}
				} 
			});
			
			VBox center = new VBox();
			center.getChildren().addAll(colleges, addFile);
			root.setCenter(center);
			
			home = new Scene (root, 1000, 640);
			primaryStage.setScene(home);
			primaryStage.show();
			
		}
		catch (Exception E) {
			E.printStackTrace();
		}
	}
	
	public static void main(String [] args) {
		launch(args);
	}
}
