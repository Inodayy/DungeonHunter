/** 
 * Class Name: Score
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: Handles the score of the game by storing the amount of lives the hunter has,
 * gold from killing enemies, and boss lives 
 */

package dungeonhunter;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Score {
	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;
	
	// declares and initializes variables to be used later in the program
	static int gold=0;
	static int bossLives=8;
	String scoreString;
	
	// constructor
	public Score(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
	}

	public void display(Hunter hunter) {

		// stores number of gold earned by killing enemies
		scoreString = "Gold: " + gold;

		// layout of the gold and boss lives
		if (DungeonHunterController.endLevel2!=1) {
			gc.setFont(Font.font("ComicSansMS", FontWeight.BOLD, 36));
			gc.setFill(Color.GOLD);
			gc.fillText(scoreString, 200, 50);
		}
		if (DungeonHunterController.endLevel2==1) {
			gc.setFont(Font.font("ComicSansMS", FontWeight.BOLD, 36));
			gc.setFill(Color.GREEN);
			gc.fillText("Boss lives Remaining: " + bossLives, 200, 50);
		}

		// layout of lives and stores the amount of lives
		String livesString = "Lives: " + hunter.getLives();
		gc.setFill(Color.RED);
		gc.fillText(livesString, 20, 50);
	}
}