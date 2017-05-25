package endless.state;

import endless.model.Player;

public class StateJumpTwo extends State{

	public StateJumpTwo(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pressJump() {
	}

	@Override
	public void pressCrawl() {
		System.out.println("Can't crawl");
	}
	
	@Override
	public void stand() {
		player.setY(0);
		StateNormal nor = new StateNormal(player);
		player.setState(nor);
	}

}
