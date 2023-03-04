/** 
 * Class Name: WallTrap
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: Handles the functions of the wall trap, collisions, and draws the wall trap in the game canvas
 */
package dungeonhunter;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class WallTrap {

	// controls the number of wall traps in the game
	static int numWalls = 2;

	// adds in the desired images/gifs
	String imageName[] = {"images/walltrap.png"};
	Image wall = new Image(imageName[0]);
	Image[] image = { wall };
	
	int selection = 0;
	
	// adds the necessary fields
	double x=0;
	double y=0;

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;

	// constructors
	public WallTrap(GraphicsContext gc, Canvas gameCanvas) {
		super();
		this.gc = gc;
		this.gameCanvas = gameCanvas;
	}

	public WallTrap(String[] imageName, GraphicsContext gc, Canvas gameCanvas) {
		super();
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.imageName = imageName;
	}

	// draws the image
	public void drawWall() {
		this.gc.drawImage(this.image[selection], this.x, this.y);
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

	public String[] getImageName() {
		return imageName;
	}

	public void setimageName(String[] imageName) {
		this.imageName = imageName;
	}

	// collision detection for the top of the wall trap
	public Rectangle2D getTopBoundary() {
		return new Rectangle2D(this.x + this.image[selection].getWidth() / 6, this.y, 2 * this.image[selection].getWidth() / 3,
				this.image[selection].getHeight() / 2);
	}

	// collision detection for the bottom of the wall trap
	public Rectangle2D getBottomBoundary() {
		return new Rectangle2D(this.x + this.image[selection].getWidth() / 6, this.y + this.image[selection].getHeight() / 2,
				2 * this.image[selection].getWidth() / 3, this.image[selection].getHeight() / 2);
	}

	// collision detection for the left side of the wall trap
	public Rectangle2D getLeftBoundary() {
		return new Rectangle2D(this.x, this.y + this.image[selection].getHeight() / 4, this.image[selection].getWidth() / 4,
				this.image[selection].getHeight() / 2);
	}

	// collision detection for the right side of the wall trap
	public Rectangle2D getRightBoundary() {
		return new Rectangle2D(this.x + 3 * this.image[selection].getWidth() / 4, this.y + this.image[selection].getHeight() / 4,
				this.image[selection].getWidth() / 4, this.image[selection].getHeight() / 2);
	}

	// collision detection for the whole image
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image[selection].getWidth(), this.image[selection].getHeight());
	}
}
