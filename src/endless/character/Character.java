package endless.character;

import endless.state.State;

public interface Character {
	public int getX();
	public int getY();
	public int getHeight();
	public int getWidth();
	public double getHp();
	public void setHp(double hp);
	public void jumpPressed();
	public void crawlPressed();
	public void setFloor(boolean onTheFloor);
	public boolean isDeath();
	public void setJumpSpeed(int jumpSpeed);
	public void setJumpTime(long jumpTime);
	public int getJumpY();
	public int getJumpSpeed();
	public void setJumpY(int jumpY);
	public long getJumpTime();
	public boolean getIsFloor();
	public void setY(int y);
	public void setState(State state);
	public void update();
	public void crawlReleased();
	public void setHeight(int height);
	public int getCrawlHeight();
	public int getNormalHeight();
	public int getjumppy();
	public State getState();
	public int getScores();
	public void plusScore();
}
