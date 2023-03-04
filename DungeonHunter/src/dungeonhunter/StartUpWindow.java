/** 
 * Class Name: StartUpWindow
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: Controller class for the the start up screen pop up window that includes options
 *  for playing the game, exiting game, and look at how to play game instructions
 */

package dungeonhunter;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class StartUpWindow {
	static Stage secondaryStage;

	// this method allows a certain output to be displayed based on which button is clicked
	public void buttonClickHandler(ActionEvent evt) throws IOException {

		// stores the value of the label when it has been clicked
		Button clickedLabel = (Button) evt.getTarget();
		String buttonLabel = clickedLabel.getText();

		// starts the game 
		if ("PLAY".equals(buttonLabel)) {
			changeSceneToGame(evt);
		}

		// opens up the how to play pop up window 
		else if ("HOW TO PLAY".equals(buttonLabel)) {
			openHowToPlayWindow();
		}

		// closes the current window
		else if ("EXIT".equals(buttonLabel)) {
			Platform.exit();
		}
	}

	// method that loads up the how to play pop up window
	private void openHowToPlayWindow() {
		try {

			Pane about = (Pane) FXMLLoader.load(getClass().getResource("HowToPlay.fxml"));
			// builds new scene
			Scene aboutScene = new Scene(about, 600, 400);
			aboutScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// creates pop up window
			secondaryStage = new Stage();
			secondaryStage.setScene(aboutScene);
			secondaryStage.setResizable(false);
			secondaryStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// method that loads the main game screen to be played
	public void changeSceneToGame(ActionEvent evt) throws IOException {

		// loads the FXML to start the game 
		FXMLLoader loader = new FXMLLoader(); 
		loader.setLocation(getClass().getResource("DungeonHunter.fxml"));
		BorderPane sceneParent = (BorderPane) loader.load();

		// creates new scene 
		Scene scene = new Scene(sceneParent, 1399, 766);

		// loads the controller associated with the FXML file 
		DungeonHunterController controller = loader.getController();

		// get stage
		Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();

		// changes scene and gets the controller methods to be able to play the game 
		stage.setScene(scene);
		controller.getScene(stage);
		controller.gameLoop();
		stage.show();
	}
	
	// closes the current pop up window
	public void closeCurrentWindow(final ActionEvent evt) {

		final Node source = (Node) evt.getSource();
		final Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
	}
}
