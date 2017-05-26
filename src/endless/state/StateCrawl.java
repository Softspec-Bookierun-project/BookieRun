package endless.state;

import endless.model.Player;

public class StateCrawl extends State{

	public StateCrawl(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pressJump() {
	}

	@Override
	public void pressCrawl() {
	}
	
	@Override
	public void stand() {
		player.setHeight(Player.NORMAL_HEIGHT);
		StateNormal nor = new StateNormal(player);
		player.setState(nor);
	}
	
	public void update(){
		if(!player.getIsFloor()){
//			player.setHeight(Player.NORMAL_HEIGHT);
			StateNormal nor = new StateNormal(player);
			player.setState(nor);
		}
	}

}
