package endless.model;

public class SpecialCoin {
	
	public int x;
	public int y;
	public int width;
	public int heigh;
	public int point;
	
	public SpecialCoin(int x,int y) {
		this.x = x;
		this.y = y;
		this.width = 20;
		this.heigh = 20;
		this.point = 10;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeigh() {
		return heigh;
	}

	public void setHeigh(int heigh) {
		this.heigh = heigh;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	public void update(){
//		x-=10;
	}

}
