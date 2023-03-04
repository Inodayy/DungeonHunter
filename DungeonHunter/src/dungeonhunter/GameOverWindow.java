/** 
 * Class Name: GameOverWindow
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: Controller class for the the game over screen pop up window that includes options
 * for playing again and quitting game
 */

package dungeonhunter;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GameOverWindow {

	// this method allows a certain output to be displayed based on which button is clicked
	public void buttonClickHandler(ActionEvent evt) throws IOException{

		// stores the value of the label when it has been clicked
		Button clickedLabel = (Button) evt.getTarget();
		String buttonLabel = clickedLabel.getText();

		// opens up the game screen when the button to play again is clicked
		if ("PLAY AGAIN".equals(buttonLabel)) {
			changeToGameScreen(evt);
		}

		// closes the current window
		else if ("QUIT".equals(buttonLabel)) {
			Platform.exit();
		}
	}

	// method that creates the main game screen window
	public void changeToGameScreen(ActionEvent evt) throws IOException {

		// loads the correct FXML file 
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("DungeonHunter.fxml"));
		BorderPane sceneParent = (BorderPane) loader.load();

		// creates new scene 
		Scene scene = new Scene(sceneParent, 1399, 766);

		// loads the controller class 
		DungeonHunterController controller = loader.getController();

		// get stage
		Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();

		// shows scene and loads controller methods 
		stage.setScene(scene);
		controller.getScene(stage);
		controller.gameLoop();
		stage.show();
	}
}
