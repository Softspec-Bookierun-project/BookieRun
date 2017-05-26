package endless.state;

import endless.character.Character;
import endless.model.Game;

public class StateDrop extends State{

 	public StateDrop(Character player) {
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
  		if(player.getY() > 0 && player.getIsFloor()){
 			player.setHeight(player.getNormalHeight());
 			StateJumpTwo fir = new StateJumpTwo(player);
  			player.setState(fir);
  		}
 	}
 
 }
