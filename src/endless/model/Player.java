package endless.model;


import java.security.Timestamp;
import java.sql.Time;

import endless.state.State;
import endless.state.StateCrawl;
import endless.state.StateJumpOne;
import endless.state.StateJumpTwo;
import endless.state.StateNormal;


public class Player {

	public static final int WIDTH = 30;
	public static final int NORMAL_HEIGHT = 60;
	public static final int CRAWL_HEIGHT = 30;

	private int x;
	private int y;
	private int vY;
	private int width;
	private int height;
	private boolean onTheFloor = true;
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
		this.width = WIDTH;
		this.setHeight(NORMAL_HEIGHT);
		state = new StateNormal(this);
		// TODO: Initialize variables you need
	}
	
	public void setFloor(boolean onTheFloor){
		this.onTheFloor = onTheFloor;
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

	public void jumpPressed() {
		// TODO: Complete this
		state.pressJump();
	}

	public void crawlPressed() {
		// TODO: Complete this Pressed");
		state.pressCrawl();
	}

	public void crawlReleased() {
		// TODO: Complete this
		if(state instanceof StateCrawl)
			state.stand();
	}

	public void update() {
		// TODO: Complete this
		if (state instanceof StateJumpOne || state instanceof StateJumpTwo || onTheFloor == false) {
			float t = (System.currentTimeMillis() - getJumpTime()) / 1000.0f;
			y = (int) (getJumpY() + this.getJumpSpeed() * t + 0.5f * Game.GRAVITY * t * t);
			if (onTheFloor == false) {
				if(y > 0)
					setFloor(true);
			}
			else if(y <=0){
				state.stand();
			}
		}
	}

	public long getJumpTime() {
		return jumpTime;
	}

	public void setJumpTime(long jumpTime) {
		this.jumpTime = jumpTime;
	}

	public int getJumpY() {
		return jumpY;
	}

	public void setJumpY(int jumpY) {
		this.jumpY = jumpY;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getJumpSpeed() {
		return jumpSpeed;
	}

	public void setJumpSpeed(int jumpSpeed) {
		this.jumpSpeed = jumpSpeed;
	}
	
	

}