package endless.state;

import endless.model.Game;
import endless.model.Player;

public class StateDrop extends State{

	public StateDrop(Player player) {
		super(player);
	}
	
	public void update(){
		System.out.println("is drop");
		float t = (System.currentTimeMillis() - player.getJumpTime()) / 1000.0f;
		player.setY((int) (player.getJumpY() + player.getJumpSpeed() * t + 0.5f * Game.GRAVITY * t * t));
	}

}
