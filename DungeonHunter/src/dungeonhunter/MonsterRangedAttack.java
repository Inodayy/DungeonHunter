/**
 * Class Name: MonsterRangedAttack
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: Handles the functions of the ranged attack, collisions, and draws the attack in the game canvas
 */

package dungeonhunter;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class MonsterRangedAttack {
	
	// controls the number of bullets in the game
	static int numBullet = 4;
	
	// declaring the necessary fields 
	double speed = 4;
    double x;
	double y=2500;
	double dx = speed;

	// adds in the desired images/gifs
	String imageName = "images/bullet.png";
	Image image = new Image(imageName);

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// constructors
	public MonsterRangedAttack(GraphicsContext gc, Canvas gameCanvas) {
		super();
		this.gc = gc;
		this.gameCanvas = gameCanvas;
	}

	public MonsterRangedAttack(String imageName, GraphicsContext gc, Canvas gameCanvas) {
		super();
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.imageName = imageName;
	}

	// draws image
	public void drawBullet() {
		gc.drawImage(this.image, this.x, this.y);
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	// draws the image and moves it to the left
	public void move() {

		this.x -= this.dx;

		this.gc.drawImage(this.image, this.x, this.y);
	}

	// collision detection for the whole image
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
	
	// collision detection for colliding with hunter
	public boolean collisionHunter(Hunter hunter) {
		boolean collide = this.getBoundary().intersects(hunter.getBoundary());
		return collide;
	}
}
