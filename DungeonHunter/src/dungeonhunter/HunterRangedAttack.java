/** 
 * Class Name: HunterRangedAttack
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: Handles the functions of the ranged attack for the hunter, collisions, and draws the attack in the game canvas,
 */

package dungeonhunter;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


public class HunterRangedAttack {
	
	// declaring the necessary fields 
	double speed = 20;
	double x;
	double y=1000;
	double dx = speed;

	// adds in the desired images/gifs 
	String imageName = "images/rangeattack.png";
	Image image = new Image(imageName);

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// constructors
	public HunterRangedAttack(GraphicsContext gc, Canvas gameCanvas) {
		super();
		this.gc = gc;
		this.gameCanvas = gameCanvas;
	}

	public HunterRangedAttack(String imageName, GraphicsContext gc, Canvas gameCanvas) {
		super();
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.imageName = imageName;
	}

	// draws image
	public void drawAttack() {
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

	// draws the image and moves it to the right
	public void move() {

		this.x += this.dx;

		this.gc.drawImage(this.image, this.x, this.y);
	}

	// collision detection for the whole image
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
	
	// collision detection for colliding with boss
	public boolean collisionBoss(Boss boss) {
		boolean collide = this.getBoundary().intersects(boss.getBoundary());
		return collide;
	}
	
	// collision detection for colliding with bat
	public boolean collisionBat(Bat bat) {
		boolean collide = this.getBoundary().intersects(bat.getBoundary());
		return collide;
	}
	
	// collision detection for colliding with wall trap
	public boolean collisionWall(WallTrap wall) {
		boolean collide = this.getBoundary().intersects(wall.getBoundary());
		return collide;
	}
}
