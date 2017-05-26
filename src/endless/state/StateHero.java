package endless.state;

import endless.model.Game;
import endless.model.Player;

public class StateHero extends State{

	public StateHero(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pressJump() {
		if(player.getY()<200){
			player.setY(player.getY()+30);
		}
	}

	@Override
	public void pressCrawl() {
		if(player.getY()>=20){
			player.setY(player.getY()-30);
		}
	}
	
	@Override
	public void stand() {
		
	}
	
	public void update() {
		
	}

}
