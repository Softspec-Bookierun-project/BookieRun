package endless.character;



import java.util.Observable;

import endless.Menu;
import endless.state.State;
import endless.state.StateCrawl;
import endless.state.StateNormal;


public class Hero extends Observable implements Character{

	public static final int WIDTH = 175;
	public static final int SCORE = 10;
	public static final int NORMAL_HEIGHT = 350;
	public static final int CRAWL_HEIGHT = 30;
	public static final int HEALTH = 200;
	public static final int JUMPPY = 300;

	private int x;
	private int y;
	private int vY;
	private int width;
	private int height;
	private double hp;
	private boolean onTheFloor = true;
	private boolean death = false;
	private State state;
	private int jumpSpeed = 200;
	private int time;
	private int score;
	
	
	public void setState(State state) {
		this.state = state;
	}

	// TODO: Add variables you need.
	private long jumpTime;
	private int jumpY;

	public Hero(int x,int score) {
		this.score = score;
		this.x = x;
		this.y = 0;
		this.hp = HEALTH;
		this.width = WIDTH;
		this.setHeight(NORMAL_HEIGHT);
		this.time = 150;
		setState(new StateNormal(this));
		// TODO: Initialize variables you need
	}
	
	public void setFloor(boolean onTheFloor){
		this.onTheFloor = onTheFloor;
	}
	
	public boolean getIsFloor(){
		return onTheFloor;
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
		this.time -= 1;
		if(this.time < 0){
			setChanged();
			notifyObservers("return");
		}
		setFloor(true);
		state.update();
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

	@Override
	public int getCrawlHeight() {
		// TODO Auto-generated method stub
		return CRAWL_HEIGHT;
	}

	@Override
	public int getNormalHeight() {
		// TODO Auto-generated method stub
		return NORMAL_HEIGHT;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return SCORE;
	}

	@Override
	public int getjumppy() {
		// TODO Auto-generated method stub
		return JUMPPY;
	}

	@Override
	public int getScores() {
		// TODO Auto-generated method stub
		return this.score;
	}

	@Override
	public void plusScore() {
		// TODO Auto-generated method stub
		this.score += SCORE;
	}
	
}