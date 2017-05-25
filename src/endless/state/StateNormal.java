package endless.state;

import endless.model.Player;

public class StateNormal extends State{

	public StateNormal(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pressJump() {
		player.setJumpTime(System.currentTimeMillis());
		player.setJumpY(player.getY());
		
		StateJumpOne fir = new StateJumpOne(player);
		player.setState(fir);
	}

	@Override
	public void pressCrawl() {
		player.setHeight(Player.CRAWL_HEIGHT);
		StateCrawl cra = new StateCrawl(player);
		player.setState(cra);
	}

}
