package endless.state;

import endless.character.Character;

public class StateCrawl extends State{

	public StateCrawl(Character player) {
		super(player);
	}
	
	@Override
	public void pressJump() {

	}

	@Override
	public void pressCrawl() {

	}
	
	@Override
	public void stand() {
		player.setHeight(player.getNormalHeight());
		StateNormal nor = new StateNormal(player);
		player.setState(nor);
	}
	
	public void update(){
		if(!player.getIsFloor()){
		 		StateNormal nor = new StateNormal(player);
		 		player.setState(nor);
		 }
	}

}
