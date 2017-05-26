package endless.state;

import endless.model.Game;
import endless.model.Player;

public class StateJumpOne extends State{

	public StateJumpOne(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void pressJump() {
		player.setJumpSpeed(600);
		player.setJumpTime(System.currentTimeMillis());
		player.setJumpY(player.getY());
		System.out.println("Jump pressed");
		StateJumpTwo dou = new StateJumpTwo(player);
		player.setState(dou);
	}
	
	@Override
	public void update() {
		float t = (System.currentTimeMillis() - player.getJumpTime()) / 1000.0f;
		player.setY((int) (player.getJumpY() + player.getJumpSpeed() * t + 0.5f * Game.GRAVITY * t * t));
		
		if(player.getY() <= 1 && !player.getIsFloor()){
			System.out.println("not floor");
			player.setJumpSpeed(0);
			player.setJumpTime(System.currentTimeMillis());
			player.setJumpY(player.getY());
			StateDrop dop = new StateDrop(player);
			player.setState(dop);
		}
		if(player.getY() <= 0 && player.getIsFloor()){
			player.setY(0);
			StateNormal nor = new StateNormal(player);
			player.setState(nor);
		}
	}
}
