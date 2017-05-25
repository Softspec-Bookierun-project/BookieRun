package endless.state;

import endless.model.Player;

public class StateCrawl extends State{

	public StateCrawl(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pressJump() {
		System.out.println("Can't Jump");
	}

	@Override
	public void pressCrawl() {
		System.out.println("already crawl");
	}
	
	@Override
	public void stand() {
		System.out.println("stand");
		player.setHeight(Player.NORMAL_HEIGHT);
		StateNormal nor = new StateNormal(player);
		player.setState(nor);
	}

}
