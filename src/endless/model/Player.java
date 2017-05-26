package endless.model;


import java.security.Timestamp;
import java.sql.Time;

import endless.Menu;
import endless.Window;
import endless.state.State;
import endless.state.StateCrawl;
import endless.state.StateJumpOne;
import endless.state.StateJumpTwo;
import endless.state.StateNormal;


public class Player {

	
	public static final int WIDTH = 30;
	public static final int NORMAL_HEIGHT = 60;
	public static final int CRAWL_HEIGHT = 30;
	public static final int HEALTH = 200;

	private int x;
	private int y;
	private int vY;
	private int width;
	private int height;
	private double hp;
	private boolean onTheFloor = true;
	private boolean death = false;
	private State state;
	private int jumpSpeed = 600;
	
	
	public void setState(State state) {
		this.state = state;
	}

	// TODO: Add variables you need.
	private boolean crawling;
	private long jumpTime;
	private int jumpY;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.hp = HEALTH;
		this.width = WIDTH;
		this.setHeight(NORMAL_HEIGHT);
		setState(new StateNormal(this));
		// TODO: Initialize variables you need
	}
	
	public void setFloor(boolean onTheFloor){
		this.onTheFloor = onTheFloor;
	}

	public int getX() {
		return x;
	}
	
	public void setHp(double hp) {
		this.hp = hp;
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

	public void jumpPressed() {
		state.pressJump();
	}

	public void crawlPressed() {
		state.pressCrawl();
	}

	public void crawlReleased() {
		if(getState() instanceof StateCrawl)
			 state.stand();
	}

	public void death(){
		death = true;
		Menu m = new Menu();
		m.setVisible(true);
	}
	
	public void update() {
		// TODO: Complete this
		if(hp <= 0 || y < -550) death();
		hp -= 0.1;
		if (state instanceof StateJumpOne || state instanceof StateJumpTwo || onTheFloor == false) {
  			float t = (System.currentTimeMillis() - getJumpTime()) / 1000.0f;
  			y = (int) (getJumpY() + this.getJumpSpeed() * t + 0.5f * Game.GRAVITY * t * t);
 			if(y <0 && onTheFloor == true){
  				state.stand();
  			}
 			if(y > 0)
 				setFloor(true);
  		}
	}

	public long getJumpTime() {
		return jumpTime;
	}

	public void setJumpTime(long jumpTime) {
		this.jumpTime = jumpTime;
	}
	
	public State getState() {
		return state;
	}

	public int getJumpY() {
		return jumpY;
	}
	
	public int getJumpSpeed() {
 		return jumpSpeed;
 	}

	public void setJumpY(int jumpY) {
		this.jumpY = jumpY;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public double getHp(){
		return hp;
	}
	
	public boolean isDeath() {
		return death;
	}
	
	public void setJumpSpeed(int jumpSpeed) {
  		this.jumpSpeed = jumpSpeed;
  	}
}