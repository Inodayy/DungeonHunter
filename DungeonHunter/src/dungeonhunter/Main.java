/** 
 * Class Name: Main
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: the main class where the program has to be run from and loads up the start up menu screen
 */

package dungeonhunter;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("StartUp.fxml"));
			BorderPane root = (BorderPane)loader.load();
			Scene scene = new Scene(root,600,267);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
