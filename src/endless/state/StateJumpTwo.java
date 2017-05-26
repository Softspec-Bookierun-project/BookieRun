package endless.state;

import endless.model.Game;
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
	}
	
	@Override
	public void update() {
		float t = (System.currentTimeMillis() - player.getJumpTime()) / 1000.0f;
		player.setY((int) (player.getJumpY() + player.getJumpSpeed() * t + 0.5f * Game.GRAVITY * t * t));
		if(player.getY() <= 0){
			player.setY(0);
			StateNormal nor = new StateNormal(player);
			player.setState(nor);
		}
	}

}
