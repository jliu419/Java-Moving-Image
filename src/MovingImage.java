import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

public class MovingImage {
	
	// FIELDS
	
	private int x, y;
	private int width, height;
	private Image image;
	private boolean isVisible;
	
	// CONSTRUCTORS
	
	public MovingImage(String filename, int x, int y, int w, int h) {
		this((new ImageIcon(filename)).getImage(),x,y,w,h);   // calling the constructor below it. 
	}
	
	public MovingImage(Image img, int x, int y, int w, int h) {
		image = img;
		this.x = x;
		this.y = y;
		width = w;
		height = h;
		isVisible = true;
	}
	
	
	// METHODS
	
	public void toggleVisibility() {  // make it visible
		isVisible = !isVisible;
	}
	
	public void moveToLocation(int x, int y) {   // teleport to the specific location
		this.x = x;
		this.y = y;
	}
	
	public void moveByAmount(int x, int y) {     // move the image by a specific amount
		this.x += x;
		this.y += y;
	}

	public boolean isPointInImage(double mouseX, double mouseY) {   // check if the point in image
		if (mouseX >= x && mouseY >= y && mouseX <= x + width && mouseY <= y + height)
			return true;
		return false;
	}
	
	public void resize(int w, int h) {    // change the size of the screen
		width = w;
		height = h;
	}
	
	public void draw(Graphics g, ImageObserver io) { // draw the image
		if (isVisible)
			g.drawImage(image,x,y,width,height,io);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
}