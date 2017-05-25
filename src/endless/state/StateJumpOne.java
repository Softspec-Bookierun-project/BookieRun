package endless.state;

import endless.model.Player;

public class StateJumpOne extends State{

	public StateJumpOne(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pressJump() {
		player.setJumpTime(System.currentTimeMillis());
		player.setJumpY(player.getY());
		
		StateJumpTwo dou = new StateJumpTwo(player);
		player.setState(dou);
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
