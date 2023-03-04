/** 
 * Class Name: Hunter
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: Handles the functions of the hunter, collisions, and draws the hunter in the game canvas
 */

package dungeonhunter;

import java.util.ArrayList;
import java.util.Timer;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Hunter {
	// declare fields
	double x = 50;
	double y = 100;
	static int speed = 4;
	double dx = 0;
	double dy = 0;

	static int lives = 3;
	int select=0;
	
	// sets user control variables for the monkey
	String left = "LEFT";
	String right = "RIGHT";
	String up = "UP";
	String down = "DOWN";
	String dash= "SPACE";
	String attack="X";
	String rangeAttack="Z";

	// adds in the desired images/gifs 
	String imageName[] = { "images/hunteridle.gif", "images/hunterdash.png", "images/moveright.png", "images/moveleft.png", "images/hunterupdown.png",
			"images/basicattack1.png", "images/basicattack2.png", "images/basicattack3.png", "images/rangemove.png", "images/damageTaken.png"};
	Image hunterIdle = new Image(imageName[0]);
	Image dashImage = new Image(imageName[1]);
	Image moveRight = new Image(imageName[2]);
	Image moveLeft = new Image(imageName[3]);
	Image upDown = new Image(imageName[4]);
	Image basicAttack1=new Image(imageName[5]);
	Image basicAttack2=new Image(imageName[6]);
	Image basicAttack3=new Image(imageName[7]);
	Image rangeMove= new Image(imageName[8]);
	Image damageTaken= new Image(imageName[9]);
	Image[] image = {hunterIdle, dashImage, moveRight, moveLeft, upDown, basicAttack1, basicAttack2, basicAttack3, rangeMove, damageTaken};

	

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	ArrayList<String> input;

	// constructors 
	public Hunter(GraphicsContext gc, Canvas gameCanvas, ArrayList<String> input) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.input = input;
	}

	public Hunter(String[] imageName, GraphicsContext gc, Canvas gameCanvas, ArrayList<String> input) {
		this.imageName = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.input = input;
	}


	// all the getters and setters 
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public String[] getImageName(Image[] image) {
		return imageName;
	}

	public void setImageName(String[] imageName) {
		this.imageName = imageName;
	}
	public Image[] getImage(Image[] image) {
		return image;
	}

	public void setImage(Image[] image) {
		this.image = image;
	}
	
	// deals with the movement of the hunter
	public void move() {

		// for left button
		if (this.input.contains(this.left)) {
			this.dx = -this.speed;

			// for right button
		} else if (this.input.contains(this.right)) {
			this.dx = this.speed;

		} else {
			this.dx = 0;

		}

		// for up button
		if (this.input.contains(this.up)) {
			this.dy = -this.speed;
			// for down button
		} else if (this.input.contains(this.down)) {
			this.dy = this.speed;
		} 
		else {
			this.dy = 0;
		}


		// save old position
		double x = this.x;
		double y = this.y;

		// move the hunter
		this.x += this.dx;
		this.y += this.dy;



		if (this.x < 0 || this.x > gameCanvas.getWidth() - this.image[select].getWidth()) {
			this.x = x;
			this.y = y;
		}
		if (this.y < 0 || this.y > gameCanvas.getHeight() - this.image[select].getHeight()) {
			this.x = x;
			this.y = y;
		}

		this.gc.drawImage(this.image[select], this.x, this.y);
	}
	
	// collision detection for whole image
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image[select].getWidth(), this.image[select].getHeight());
	}
	
	// collision detection for colliding with bat
	public boolean collisionHunterBat(Bat bat) {
		boolean collide = this.getBoundary().intersects(bat.getBoundary());
		return collide;
	}
	
	// collision detection for colliding with monster
	public boolean collisionMonster(Monster monster) {
		boolean collide = this.getBoundary().intersects(monster.getBoundary());
		return collide;
	}
	
	// collision detection for colliding with the top side of the wall trap
	public boolean collisionTopWall(WallTrap wall) {
		boolean collide = this.getBoundary().intersects(wall.getTopBoundary());
		return collide;
	}

	// collision detection for colliding with the bottom side of the wall trap
	public boolean collisionBottomWall(WallTrap wall) {
		boolean collide = this.getBoundary().intersects(wall.getBottomBoundary());
		return collide;
	}

	// collision detection for colliding with the left side of the wall trap
	public boolean collisionLeftWall(WallTrap wall) {
		boolean collide = this.getBoundary().intersects(wall.getLeftBoundary());
		return collide;
	}

	// collision detection for colliding with the right side of the wall trap
	public boolean collisionRightWall(WallTrap wall) {
		boolean collide = this.getBoundary().intersects(wall.getRightBoundary());
		return collide;
	}
	
	// collision detection for colliding with the buzz saw
	public boolean collisionBuzzSaw(BuzzSaw buzzSaw) {
		boolean collide = this.getBoundary().intersects(buzzSaw.getBoundary());
		return collide;
	}

}
