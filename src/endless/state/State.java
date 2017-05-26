package endless.state;

import endless.character.Character;

public abstract class State {

	protected Character player;
	
	public State(Character player) {
		this.player = player;
	}
	
	public void pressJump() { }
	
	public void pressCrawl() { }
	
	public void stand(){ }
	
	public void hero(){ }
	
	public void update(){ }
	
	public void drop(){ }
	
}
