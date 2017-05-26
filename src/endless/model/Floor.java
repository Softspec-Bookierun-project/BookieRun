package endless.model;

public class Floor {

	public static final int WIDTH = 1000;
	public static final int NORMAL_HEIGHT = 30;

	private int x;
	private int y;
	private int vY = 5;
	private int width;
	private int height;
	private int blank;

	public Floor(int x, int y, int width, int blank) {
		this.x = x;
		this.y = y;
		this.blank = blank;
		this.width = width;
		this.width = WIDTH;
		this.height = NORMAL_HEIGHT;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getvY() {
		return vY;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}

	public void update() {
		// TODO: Complete this
		this.x -= this.vY;
	}
	
	public void setWidth(int width) {
  		this.width = width;
  	}
  
 	public int getBlank() {
 		return blank;
 	}
 
 	public void setBlank(int blank) {
 		this.blank = blank;
 	}

}