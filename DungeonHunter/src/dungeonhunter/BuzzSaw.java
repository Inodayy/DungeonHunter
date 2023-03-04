/** 
 * Class Name: BuzzSaw
 * Author: Inoday Yadav
 * Date: February 1, 2021
 * Purpose: Handles the functions of the buzz saw, collisions, and draws the buzz saw in the game canvas
 */
package dungeonhunter;

import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BuzzSaw {
	
	// controls number of buzz saws that start in the game
	static int numBuzzSaw = 4;
	
	// declare fields
	double x;
	double y;

	// adds in the desired images/gifs
	String imageName = "images/buzzsaw.gif";
	Image image = new Image(imageName);

	GraphicsContext gc;
	@FXML
	Canvas gameCanvas;
	
	// constructors
	public BuzzSaw(GraphicsContext gc, Canvas gameCanvas) {
		super();
		this.gc = gc;
		this.gameCanvas = gameCanvas;
	}

	public BuzzSaw(String imageName, GraphicsContext gc, Canvas gameCanvas) {
		super();
		this.gc = gc;
		this.gameCanvas = gameCanvas;
		this.imageName = imageName;
	}


	// draws the image 
	public void drawBuzzSaw() {
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	// collision detection for whole image
	public Rectangle2D getBoundary() {
		return new Rectangle2D(this.x, this.y, this.image.getWidth(), this.image.getHeight());
	}
}
