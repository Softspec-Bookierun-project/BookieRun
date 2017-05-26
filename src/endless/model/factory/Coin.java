package endless.model.factory;


import java.awt.Graphics;
import java.security.Timestamp;
import java.sql.Time;
import java.util.Observable;
import java.awt.Color;



public class Coin extends Observable implements Shape{

	public static final int WIDTH = 20;

	private int x;
	private int y;
	private int vY = 5;
	private int width;
	private Color color;
	private boolean visible = true;
	private boolean checkDraw = false;
	private int count = 0;
	
	// TODO: Add variables you need.

	public Coin(Color color) {
		this.x = 1000;
		this.y = (int)(Math.random() * 200)+100;
		this.color = color;
		this.width = WIDTH;
		// TODO: Initialize variables you need
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}
	
	public boolean getVisible(){
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public Color getColor(){
		return this.color;
	}	
	
	public void setCheckDraw(boolean checkDraw){
		this.checkDraw = checkDraw;
	}

	public void update() {
		// TODO: Complete this
		if(this.visible == true){
			this.x -= vY;
			if(this.x <= 860 && this.checkDraw == false){
				this.checkDraw = true;
				setChanged();
				notifyObservers();
			}
		}
		if(this.x < 80 && this.x > 50){
			setChanged();
			notifyObservers(this);
		}
		if(this.x < -50){
			this.x = 1000;
			this.y = (int)(Math.random() * 200)+100;
			this.visible = false;
			this.checkDraw = false;
			setChanged();
			notifyObservers("delete");
		}
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(color);
		g.fillOval(x, y, width, width);
	}

}