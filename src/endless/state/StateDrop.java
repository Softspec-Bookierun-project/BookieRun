package endless.state;

import endless.model.Game;
import endless.model.Player;

public class StateDrop extends State{

	public StateDrop(Player player) {
		super(player);
	}
	
	@Override
	public void pressJump() {
		player.setJumpSpeed(600);
		player.setJumpTime(System.currentTimeMillis());
		player.setJumpY(player.getY());
		
		StateJumpTwo fir = new StateJumpTwo(player);
		player.setState(fir);
	}
	
	public void update(){
		float t = (System.currentTimeMillis() - player.getJumpTime()) / 1000.0f;
		player.setY((int) (player.getJumpY() + player.getJumpSpeed() * t + 0.5f * Game.GRAVITY * t * t));
//		if(player.getY() < 0 && player.getIsFloor()){
//			player.setY(0);
//			StateNormal nor = new StateNormal(player);
//			player.setState(nor);
//		}
		if(player.getY() > 0 && player.getIsFloor()){
			StateJumpTwo fir = new StateJumpTwo(player);
			player.setState(fir);
		}
	}

}
