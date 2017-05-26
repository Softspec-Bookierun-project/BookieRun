package endless.state;

import endless.character.Character;

public class StateNormal extends State{

	public StateNormal(Character player) {
		super(player);
	}
	
	@Override
	public void pressJump() {
		player.setJumpSpeed(player.getjumppy());
		player.setJumpTime(System.currentTimeMillis());
		player.setJumpY(player.getY());
		
		StateJumpOne fir = new StateJumpOne(player);
		player.setState(fir);
	}

	@Override
	public void pressCrawl() {
		player.setHeight(player.getCrawlHeight());
		StateCrawl cra = new StateCrawl(player);
		player.setState(cra);
	}
	
	@Override
	public void update() {
		if(!player.getIsFloor()){
			player.setJumpSpeed(0);
			player.setJumpTime(System.currentTimeMillis());
			player.setJumpY(player.getY());
			
			StateDrop dop = new StateDrop(player);
			player.setState(dop);
		}
	}

}
