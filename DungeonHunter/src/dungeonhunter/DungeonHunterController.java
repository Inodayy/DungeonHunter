/**
 * Class Name: DungeonHunterController
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: This class handles all the controls of the dungeon hunter game, the collisions, power-up shop, levels, gameplay, music etc.
 */

package dungeonhunter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class DungeonHunterController {

	@FXML
	Canvas gameCanvas;

	Scene gameScene;
	GraphicsContext gc;

	// gets the FXML for the buttons
	@FXML
	Button Buy1;
	@FXML
	Button Buy2;
	@FXML
	Button Buy3;
	@FXML
	Button Menu;

	// initializes and declares variables to be used later in the program
	int trigger=0;
	int endBatWave=0;
	int batDead1=0;
	int batDead2=0;
	int batDead3=0;
	int gold=0;
	int buyPowerUp=0;
	int buyPowerUp2=0;
	int endLevel1=0;
	int setBullet=0;
	int setMonster=0;
	static int endLevel2=0;
	int setBoss=0;
	int setBossAttack=0;
	int endGame=0;



	public void buttonClickHandler1(ActionEvent evt) throws IOException {

		// stores the value of the label when it has been clicked
		Button clickedLabel = (Button) evt.getTarget();
		String buttonLabel = clickedLabel.getText();

		// changes to the game over screen
		if ("PROCEED TO MENU".equals(buttonLabel)&& Menu.isVisible()) {
			changeToGameOverScreen(evt);
		}

		// power-up option to increase speed
		if ("BUY SPEED POWER-UP".equals(buttonLabel) && Buy1.isVisible() && Score.gold>=3) {
			Hunter.speed= (Hunter.speed+2);
			Score.gold = (Score.gold-3);
		}

		// power-up option to increase life by one
		else if ("BUY EXTRA LIFE x1".equals(buttonLabel) && Buy2.isVisible()&& Score.gold>=3) {
			Hunter.lives= (Hunter.lives+1);
			Score.gold = (Score.gold-3);
		}

		// power-up option to increase life by one
		else if ("BUY EXTRA LIFE x3".equals(buttonLabel) && Buy3.isVisible()&& Score.gold==6) {

			Hunter.lives= (Hunter.lives+3);
			Score.gold = (Score.gold-6);
		}
	}

	public void changeToGameOverScreen(ActionEvent evt) throws IOException {

		// loads the FXML file for the game over screen
		BorderPane sceneParent = (BorderPane) FXMLLoader.load(getClass().getResource("GameOver.fxml"));
		Scene scene = new Scene(sceneParent);

		// gets stage
		Stage stage = (Stage) ((Node) evt.getSource()).getScene().getWindow();

		// changes scene
		stage.setScene(scene);
		stage.show();
	}

	// gets scene
	public void getScene(Stage primaryStage) {
		gameScene = primaryStage.getScene();
	}

	// includes all the components that run when the game starts
	public void gameLoop(){

		// initially makes the buttons in canvas invisible
		Buy1.setVisible(false);
		Buy2.setVisible(false);
		Buy3.setVisible(false);
		Menu.setVisible(false);

		gc = gameCanvas.getGraphicsContext2D();

		// initializes arrays to be used later in the program
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<WallTrap> wallList = new ArrayList<WallTrap>();
		ArrayList<BuzzSaw> buzzSawList = new ArrayList<BuzzSaw>();
		ArrayList<Monster> monsterList = new ArrayList<Monster>();
		ArrayList<MonsterRangedAttack> bulletList = new ArrayList<MonsterRangedAttack>();
		ArrayList<Bat> batList = new ArrayList<Bat>();



		String back1= ("images/background.gif");
		String back2= ("images/background2.gif");
		String back3= ("images/background3.gif");
		Image background = new Image(back1); 
		Image background2= new Image(back2);
		Image background3= new Image(back3);

		Image powerUp=new Image("images/powerups.png");

String backgroundMusicFile = ("src/sounds/background.mp3");
	Media backgroundMusic = new Media(new File(backgroundMusicFile).toURI().toString());
MediaPlayer backgroundMediaPlayer = new MediaPlayer(backgroundMusic);
backgroundMediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
backgroundMediaPlayer.play();
		// adds objects in the game
		Boss boss = new Boss(gc, gameCanvas);
		BossAttack bossAttack= new BossAttack(gc,gameCanvas);
		Hunter hunter = new Hunter(gc, gameCanvas, input);
		HunterRangedAttack hunterRangedAttack = new HunterRangedAttack(gc, gameCanvas);
		Score score = new Score(gc, gameCanvas);

		// adds objects for the game in array lists
		for (int i = 0; i < Monster.numMonster; i++) {
			monsterList.add(new Monster(gc, gameCanvas));
		}

		for (int i = 0; i < MonsterRangedAttack.numBullet; i++) {
			bulletList.add(new MonsterRangedAttack(gc, gameCanvas));
		}

		for (int i = 0; i < BuzzSaw.numBuzzSaw; i++) {
			buzzSawList.add(new BuzzSaw(gc, gameCanvas));
		}

		for (int i = 0; i < WallTrap.numWalls; i++) {
			wallList.add(new WallTrap(gc, gameCanvas));
		}

		for (int i = 0; i < Bat.numBats; i++) {
			batList.add(new Bat(gc, gameCanvas));
		}

		// handles key user input for the hunter 
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {


				String code = e.getCode().toString();
				if (!input.contains(code)) {
					input.add(code);
				}
				if (input.contains(hunter.attack)) {

					hunter.select=(int)(Math.random()*3)+5;
				}

				if (input.contains(hunter.rangeAttack)) {
					trigger=1;
					hunter.select=8;
					hunterRangedAttack.setX(hunter.getX());
					hunterRangedAttack.setY(hunter.getY()+18);
				}

				if (input.contains(hunter.right)) {
					hunter.select=2;
				}

				if(input.contains(hunter.left)) {
					hunter.select=3;
				}

				if(input.contains(hunter.up)) {
					hunter.select=4;
				}

				if(input.contains(hunter.down)) {
					hunter.select=4;
				}
				if (input.contains(hunter.dash)){
					hunter.select=1;
					Hunter.speed=30;
					Timer t = new java.util.Timer();
					t.schedule(new java.util.TimerTask() {
						@Override
						public void run() {

							hunter.select=0;
							Hunter.speed = 3;
							t.cancel();
						}
					}, 100);
				}
			}
		});

		// the input removes code when the input does contain code 
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (input.contains(code))
					input.remove(code);
				hunter.select=0;
			}
		});

		// sets the locations of the wall traps on the canvas
		wallList.get(0).setX(600);
		wallList.get(0).setY(200);
		wallList.get(1).setX(600);
		wallList.get(1).setY(500);

		// sets the locations of the buzz saw on the canvas
		buzzSawList.get(0).setX(400);
		buzzSawList.get(0).setY(50);
		buzzSawList.get(1).setX(400);
		buzzSawList.get(1).setY(250);
		buzzSawList.get(2).setX(400);
		buzzSawList.get(2).setY(450);
		buzzSawList.get(3).setX(400);
		buzzSawList.get(3).setY(650);




		// actual game loop that repeats
		new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {

				gc = gameCanvas.getGraphicsContext2D();
				gc.drawImage(background, 0, 0);

				// draws the image and lets the hunter be moveable
				hunter.move();

				// draws the objects
				for (int i = 0; i < WallTrap.numWalls; i++) {
					wallList.get(i).drawWall();
				}

				for (int i = 0; i < BuzzSaw.numBuzzSaw; i++) {
					buzzSawList.get(i).drawBuzzSaw();
				}

				// collision detection for buzz saw and all side of the wall traps
				if ((hunterRangedAttack.collisionWall(wallList.get(0))||hunterRangedAttack.collisionWall(wallList.get(1)))&&trigger==1) {
					hunterRangedAttack.setY(2000);
				}

				if (hunter.collisionTopWall(wallList.get(0))||hunter.collisionTopWall(wallList.get(1))) {
					hunter.setY(hunter.getY() - hunter.getDy());
				}

				if (hunter.collisionBottomWall(wallList.get(0))||hunter.collisionBottomWall(wallList.get(1))) {
					// hunter loses one life, shows the damage taken image, and sets the position to original starting position
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				if (hunter.collisionLeftWall(wallList.get(0))||hunter.collisionLeftWall(wallList.get(1))) {
					hunter.setX(hunter.getX() - hunter.getDx());
				}

				if (hunter.collisionRightWall(wallList.get(0))||hunter.collisionRightWall(wallList.get(1))) {
					hunter.setX(hunter.getX() - hunter.getDx());
				}

				if (hunter.collisionBuzzSaw(buzzSawList.get(0))||hunter.collisionBuzzSaw(buzzSawList.get(1))||hunter.collisionBuzzSaw(buzzSawList.get(2))
						||hunter.collisionBuzzSaw(buzzSawList.get(3))) {
					// hunter loses one life, shows the damage taken image, and sets the position to original starting position
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				// this was created so when 3 gold is used to buy a power up the powerUp image won't disappear
				if (Score.gold==6) {
					buyPowerUp=1;
				}

				// makes the buying buttons visible, shows image for the power-up options, and sets hunter to starting position
				if (buyPowerUp==1) {
					gc.drawImage(powerUp,320,200);
					Buy1.setVisible(true);
					Buy2.setVisible(true);
					Buy3.setVisible(true);

					hunter.setX(50);
					hunter.setY(50);
				}

				// triggers the start of the second level
				if (Score.gold==0&& buyPowerUp==1) {
					endLevel1=1;
				}

				// ****************LEVEL 2 STARTS****************
				if (endLevel1==1) {

					Buy1.setVisible(false);
					Buy2.setVisible(false);
					Buy3.setVisible(false);
					buyPowerUp=0;
					gc.drawImage(background2, 0, 0);

					hunter.move();
				
					// draws the objects for the second level
					for (int i = 0; i < Monster.numMonster; i++) {
						monsterList.get(i).drawMonster();
					}
					for (int i = 0; i < MonsterRangedAttack.numBullet; i++) {
						bulletList.get(i).move();
					}

					// sets the monster location,
					// setMonster variable used so the monsters only start from that location
					// and not stay there even when they die
					if (setMonster==0) {
						monsterList.get(0).setX(800);
						monsterList.get(0).setY(200);

						monsterList.get(1).setX(700);
						monsterList.get(1).setY(400);

						monsterList.get(2).setX(1200);
						monsterList.get(2).setY(300);

						monsterList.get(3).setX(1200);
						monsterList.get(3).setY(620);

						setMonster=1;
					}

					// sets the bullet location to the same location as the monster,
					// setBullet variable used so the bullets only start from that location
					// and not stay there without moving
					if(setBullet==0) {

						bulletList.get(0).setX(700);
						bulletList.get(0).setY(220);

						bulletList.get(1).setX(700);
						bulletList.get(1).setY(420);

						bulletList.get(2).setX(1200);
						bulletList.get(2).setY(320);

						bulletList.get(3).setX(1200);
						bulletList.get(3).setY(640);

						setBullet=1;
					}

					// removes the traps outside of the canvas from previous levels
					buzzSawList.get(0).setY(1000);
					buzzSawList.get(1).setY(1000);
					buzzSawList.get(2).setY(1000);
					buzzSawList.get(3).setY(1000);		
					wallList.get(0).setY(1000);
					wallList.get(1).setY(1000);


					// draws new buzz saws and sets their locations  
					for (int i = 0; i < 2; i++) {

						buzzSawList.get(0).drawBuzzSaw();
						buzzSawList.get(0).setX(300);
						buzzSawList.get(0).setY(50);

						buzzSawList.get(1).drawBuzzSaw();
						buzzSawList.get(1).setX(300);
						buzzSawList.get(1).setY(450);

						buzzSawList.get(2).drawBuzzSaw();
						buzzSawList.get(2).setX(300);
						buzzSawList.get(2).setY(300);

						buzzSawList.get(3).drawBuzzSaw();
						buzzSawList.get(3).setX(700);
						buzzSawList.get(3).setY(670);
					}

					// draws new wall traps and sets their locations  
					for (int i = 0; i < 2; i++) {

						wallList.get(0).drawWall();
						wallList.get(0).setX(600);
						wallList.get(0).setY(50);

						wallList.get(1).drawWall();
						wallList.get(1).setX(750);
						wallList.get(1).setY(550);
					}

					// makes the bullets return to the starting position when they are out of the canvas
					// so it seems as if the monsters are repeatedly shooting
					if (bulletList.get(0).getX()==-500) {
						bulletList.get(0).setX(monsterList.get(0).getX());
					}

					if (bulletList.get(1).getX()==-500) {
						bulletList.get(1).setX(monsterList.get(1).getX());
					}

					if (bulletList.get(2).getX()==-900) {
						bulletList.get(2).setX(monsterList.get(2).getX());
					}

					if (bulletList.get(3).getX()==-900) {
						bulletList.get(3).setX(monsterList.get(3).getX());
					}

					// this was created so when 3 gold is used to buy a power up the powerUp image won't disappear
					if (Score.gold==6) {
						buyPowerUp2=1;
					}

					// makes the buying buttons visible, shows image for the power-up options, and sets hunter to starting position
					if (buyPowerUp2==1) {

						gc.drawImage(powerUp,320,200);
						Buy1.setVisible(true);
						Buy2.setVisible(true);
						Buy3.setVisible(true);

						hunter.setX(50);
						hunter.setY(50);

						// triggers the start of the final level
						if (Score.gold==0) {

							endLevel2=1;
						}
					}

					// ***************FINAL LEVEL*******************
					if (endLevel2==1) {
						Buy1.setVisible(false);
						Buy2.setVisible(false);
						Buy3.setVisible(false);
						buyPowerUp2=0;
						gc.drawImage(background3, 0, 0);

						// removes the traps from the previous level outside of the canvas
						buzzSawList.get(0).setY(1000);
						buzzSawList.get(1).setY(1000);
						buzzSawList.get(2).setY(1000);
						buzzSawList.get(3).setY(1000);		
						wallList.get(0).setY(1000);
						wallList.get(1).setY(1000);

						// draws/allows objects to move for final level
						boss.drawBoss();
						bossAttack.move();
						hunter.move();

						// sets the boss's position in the canvas,
						// setBoss variable used so the boss doesn't stay in that location
						// even when dead
						if (setBoss==0) {
							boss.setX(1000);
							boss.setY(300);
							setBoss=1;
						}

						// sets the boss attack position in the canvas,
						// setBossAttack variable used so the attack doesn't stay in that location
						// without moving left
						if (setBossAttack==0) {	
							bossAttack.setX(1000);
							bossAttack.setY(320);
							setBossAttack=1;
						}

						// sets the attack location to the original location when outside of the canvas width
						// so it seems as if the boss is repeatedly doing attacks
						if (bossAttack.getX()==-1000) {
							bossAttack.setX(boss.getX());
						}	

						// draws new buzz saws in the canvas and sets their location
						for (int i = 0; i < 2; i++) {

							buzzSawList.get(0).drawBuzzSaw();
							buzzSawList.get(0).setX(300);
							buzzSawList.get(0).setY(50);

							buzzSawList.get(1).drawBuzzSaw();
							buzzSawList.get(1).setX(300);
							buzzSawList.get(1).setY(450);

							buzzSawList.get(2).drawBuzzSaw();
							buzzSawList.get(2).setX(300);
							buzzSawList.get(2).setY(250);

							buzzSawList.get(3).drawBuzzSaw();
							buzzSawList.get(3).setX(700);
							buzzSawList.get(3).setY(670);
						}

						// draws new wall traps in the canvas and sets their location
						for (int i = 0; i < 2; i++) {

							wallList.get(0).drawWall();
							wallList.get(0).setX(600);
							wallList.get(0).setY(400);

							wallList.get(1).drawWall();
							wallList.get(1).setX(600);
							wallList.get(1).setY(500);
						}
					}
				}

				// the hunter loses a life and respawns if the boss attack collides with the hunter
				if (bossAttack.collisionHunter(hunter)) {
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				// the boss loses a life if the hunter attack collides with it,
				// if the boss lives remaining is 0 then it is moved out of the canvas
				// including the boss attack
				if (hunterRangedAttack.collisionBoss(boss)) {
					hunterRangedAttack.setY(2000);
					score.bossLives=(score.bossLives-1);
					if (score.bossLives==0) {
						boss.setY(3000);
						bossAttack.setY(3000);
					}
				}

				// if hunter uses close range attack the monster dies, 2 gold is earned
				// and the monster is moved out of the canvas with the bullet
				if (input.contains(hunter.attack)&& hunter.collisionMonster(monsterList.get(0))){
					score.gold=(score.gold+2);
					monsterList.get(0).setY(1500);
					bulletList.get(0).setY(1500);
				}

				// if hunter uses close range attack the monster dies, 2 gold is earned
				// and the monster is moved out of the canvas with the bullet
				if (input.contains(hunter.attack)&& hunter.collisionMonster(monsterList.get(1))){
					score.gold=(score.gold+2);
					monsterList.get(1).setY(1500);
					bulletList.get(1).setY(1500);
				}

				// if hunter uses close range attack the monster dies, 1 gold is earned
				// and the monster is moved out of the canvas with the bullet
				if (input.contains(hunter.attack)&& hunter.collisionMonster(monsterList.get(2))){
					score.gold=(score.gold+1);
					monsterList.get(2).setY(1500);
					bulletList.get(2).setY(1500);
				}

				// if hunter uses close range attack the monster dies, 1 gold is earned
				// and the monster is moved out of the canvas with the bullet
				if (input.contains(hunter.attack)&& hunter.collisionMonster(monsterList.get(3))){
					score.gold=(score.gold+1);
					monsterList.get(3).setY(1500);
					bulletList.get(3).setY(1500);
				}

				// if bullet collides with hunter, hunter loses a life and respawns
				if (bulletList.get(0).collisionHunter(hunter)) {
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				// if bullet collides with hunter, hunter loses a life and respawns
				if (bulletList.get(1).collisionHunter(hunter)) {
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				// if bullet collides with hunter, hunter loses a life and respawns
				if (bulletList.get(2).collisionHunter(hunter)) {
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				// if bullet collides with hunter, hunter loses a life and respawns
				if (bulletList.get(3).collisionHunter(hunter)) {
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				// if all three bats are dead, three more bats are created
				if (batDead1==1&&batDead2==1&&batDead3==1&&endBatWave==0) {
					batDead1=0;
					batDead2=0;
					batDead3=0;
					endBatWave=1;
					batList.get(0).randomStart();
					batList.get(1).randomStart();
					batList.get(2).randomStart();
				}

				if  (trigger==1) {
					hunterRangedAttack.move();
				}
				
				// bat 1 will not move when its dead
				if (batDead1!=1) {

					batList.get(0).move();
				}

				// bat 2 will not move when its dead
				if (batDead2!=1) {
					batList.get(1).move();
				}

				// bat 3 will not move when its dead
				if (batDead3!=1) {
					batList.get(2).move();
				}

				// trigger variable is used so the the attack doesn't move at the start of the game
				// when the hunter ranged attack button was never pressed
				

				// if the hunter ranged attack collides with bat the bat and attack is moved out of the canvas
				// and 1 gold is earned
				if (hunterRangedAttack.collisionBat(batList.get(0))&&trigger==1) {
					hunterRangedAttack.setY(2000);
					score.gold=(score.gold+1);
					batDead1=1;
					batList.get(0).setY(1500);

				}

				// if the hunter ranged attack collides with bat the bat and attack is moved out of the canvas
				// and 1 gold is earned
				if (hunterRangedAttack.collisionBat(batList.get(1))&&trigger==1) {
					hunterRangedAttack.setY(2000);
					score.gold=(score.gold+1);
					batDead2=1;
					batList.get(1).setY(1500);

				}

				// if the hunter ranged attack collides with bat the bat and attack is moved out of the canvas
				// and 1 gold is earned
				if ( hunterRangedAttack.collisionBat(batList.get(2))&&trigger==1) {
					hunterRangedAttack.setY(2000);
					score.gold=(score.gold+1);
					batDead3=1;
					batList.get(2).setY(1500);

				}

				// if the bat collides with the hunter, the hunter loses a life and respawns at original starting position
				if (hunter.collisionHunterBat(batList.get(0))){
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				// if the bat collides with the hunter, the hunter loses a life and respawns at original starting position
				if (hunter.collisionHunterBat(batList.get(1))){
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				// if the bat collides with the hunter, the hunter loses a life and respawns at original starting position
				if (hunter.collisionHunterBat(batList.get(2))){
					hunter.select=9;
					hunter.lives=(hunter.lives-1);
					hunter.setX(50);
					hunter.setY(100);
				}

				// displays the lives, gold or boss lives on the screen
				score.display(hunter);

				// triggers the game to end, this was made separately so when game is played again the game over is not dependent
				// on the hunter lives from the previous game
				if (Hunter.lives<=0|| Score.bossLives==0) {
					endGame=1;
				}

				// resets all the variables and stops the music when the game ends
				if (endGame==1) {
					backgroundMediaPlayer.stop();
					Menu.setVisible(true);
					Hunter.lives=3;
					Hunter.speed=4;
					Bat.numBats=3;
					BuzzSaw.numBuzzSaw=4;		
					WallTrap.numWalls=2;
					Score.bossLives=8;
					Score.gold=0;
					Monster.numMonster=4;
					MonsterRangedAttack.numBullet=4;
					trigger=0;
					endBatWave=0;
					batDead1=0;
					batDead2=0;
					batDead3=0;
					gold=0;
					buyPowerUp=0;
					endLevel1=0;
					setBullet=0;
					setMonster=0;
					buyPowerUp2=0;
					endLevel2=0;
					setBoss=0;
					setBossAttack=0;
					endGame=0;
				}
			}

			// starts the game loop 
		}.start();
	}
}