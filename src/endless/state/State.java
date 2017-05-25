package endless.state;

import endless.model.Player;

public abstract class State {

	protected Player player;
	
	public State(Player player) {
		this.player = player;
	}
	
	public void pressJump() { }
	
	public void pressCrawl() { }
	
	public void stand(){ }
	
}
