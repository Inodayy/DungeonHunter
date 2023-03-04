/** 
 * Class Name: Bat
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: Handles the functions of the Bat, collisions, draws the Bats in the game canvas,
 * and spawns bats random location when game starts
 */

package dungeonhunter;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class Bat {
	
	// controls number of bats that start in the game
	static int numBats = 3;
	
	// declares fields
	double x = 1200;
	double y = 100;
	int speed = 1;
	double dx = speed;
	double dy = -speed;
	
	int lives = 3;
	int select1=0;
	
	// adds in the desired images/gifs
	String imageName[] = {"images/enemy1.gif", "images/erase.png"};
	Image bat = new Image(imageName[0]);
	Image erase = new Image(imageName[1]);
	Image[] image = {bat, erase};

	

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;



	// constructors 
	public Bat(GraphicsContext gc, Canvas gameCanvas) {
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomStart();
	}

	public Bat(String[] imageName, GraphicsContext gc, Canvas gameCanvas) {
		this.imageName = imageName;
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		randomStart();
	}


	// all the getters and setters 
	public void randomStart() {
		
		this.x = (int) (Math.random() * (this.gameCanvas.getWidth() - this.image[select1].getWidth()));
		if (0<x&&x<300) {
			x=1000;
		}
		this.y = (int) (Math.random() * (this.gameCanvas.getHeight() - this.image[select1].getHeight()));
	}


	public int getSpeed() {
		return speed;
	}


	public void setSpeed(int speed) {
		this.speed = speed;
	}

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
	
	// deals with the movement of the bats
	public void move() {
		this.x += this.dx;
		this.y += this.dy;

		if (this.x <= 0 || this.x >= this.gameCanvas.getWidth() - this.image[select1].getWidth()) {
			this.dx = -this.dx;
		}
		if (this.y <= 0 || this.y >= this.gameCanvas.getHeight() - this.image[select1].getHeight()) {
			this.dy = -this.dy;
		}
	
		this.gc.drawImage(this.image[select1], this.x, this.y);
	}
	
	// collision detection for whole image
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image[select1].getWidth(), this.image[select1].getHeight());
	}
}
