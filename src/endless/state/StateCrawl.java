package endless.state;

import endless.character.Character;

public class StateCrawl extends State{

	public StateCrawl(Character player) {
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
		player.setHeight(player.getNormalHeight());
		StateNormal nor = new StateNormal(player);
		player.setState(nor);
	}

}
