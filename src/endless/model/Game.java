package endless.model;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import endless.Menu;
import endless.Window;
import endless.state.StateJumpOne;
import endless.state.StateJumpTwo;

public class Game extends Observable {

	public static final int FPS = 60;
	public static final float GRAVITY = -1500;
	
	private Player player;
	private Ball ball;
	private ArrayList<Floor> floor = new ArrayList<Floor>();
	private int useFloor = 0;
	private int endOfFloor;
	
	private boolean running;
	private Thread gameThread;
	
	
	public Game() {
		player = new Player(0, 0);
		ball = new Ball(1000, 0);
		
		floor.add(new Floor(-50, -30, ((int)(Math.random()* 700))+200, (int)(Math.random()* 300)+100));		
		
		for(int i=1; i< 5;i++){
			floor.add(new Floor(floor.get(i-1).getX()+floor.get(i-1).getWidth() + floor.get(i-1).getBlank(), -30, ((int)(Math.random()* 700))+200, (int)(Math.random()* 300)+100));
		}
		endOfFloor = floor.get(4).getX() + floor.get(4).getWidth() + floor.get(4).getBlank() - (floor.get(0).getWidth());
	}
	
	public void start() {
		running = true;
		gameThread = new Thread() {
			@Override
			public void run() {
				super.run();
				while(running) {
					singleFrame();
					if(player.isDeath()) {
						break;
					}
					try {
						Thread.sleep(1000 / FPS);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}	
				}
			}
		};
		gameThread.start();
	}
	
	private void singleFrame() {
		
		if((floor.get(useFloor).getX()+floor.get(useFloor).getWidth())+ floor.get(useFloor).getBlank() -30 <= 0){
			floor.get(useFloor).setX(endOfFloor);
  			floor.get(useFloor).setWidth((int)(Math.random()* 700)+200);
 			floor.get(useFloor).setBlank((int)(Math.random()* 300)+100);
 			if(useFloor+1 < 5){
 				endOfFloor = floor.get(useFloor).getX() + floor.get(useFloor).getWidth() + floor.get(useFloor).getBlank() - (floor.get(useFloor+1).getWidth() + floor.get(useFloor+1).getBlank());
  				useFloor++;
 			} else{
 				 endOfFloor = floor.get(useFloor).getX() + floor.get(useFloor).getWidth() + floor.get(useFloor).getBlank() - (floor.get(0).getWidth() + floor.get(0).getBlank());
   				System.out.println("======================================");
   				useFloor = 0;
   			}
		}
		if(((player.getX() > (floor.get(useFloor).getX())+ floor.get(useFloor).getWidth()))){
			 player.setFloor(false);
			 if(!(player.getState() instanceof StateJumpOne) && !(player.getState() instanceof StateJumpTwo)){
				player.setJumpSpeed(0);
	  			player.setState(new StateJumpOne(this.player));
	  			player.setJumpTime(System.currentTimeMillis());
  				player.setJumpY(player.getY());
			 }
		}
		
		player.update();
		ball.update();
		for(int i=0;i<5;i++){
			floor.get(i).update();
		}
		
		setChanged();
		notifyObservers();
	}
	
	public int getPlayerX() {
		return player.getX();
	}
	
	public int getPlayerY() {
		return player.getY();
	}
	
	public int getPlayerWidth() {
		return player.getWidth();
	}
	
	public int getPlayerHeight() {
		return player.getHeight();
	}
	
	public double getPlayerHp(){
		return player.getHp();
	}
	
	public int getBallX() {
		return ball.getX();
	}
	
	public int getBallY() {
		return ball.getY();
	}
	
	public int getBallWidth() {
		return ball.getWidth();
	}
	
	public int getFloorHeight(int i) {
		return floor.get(i).getHeight();
	}
	
	public int getFloorX(int i) {
		return floor.get(i).getX();
	}
	
	public int getFloorY(int i) {
		return floor.get(i).getY();
	}
	
	public int getFloorWidth(int i) {
		return floor.get(i).getWidth();
	}
	
	public int getBallHeight() {
		return ball.getHeight();
	}
	
	public void jumpPressed() {
		player.jumpPressed();
	}
	
	public void crawlPressed() {
		player.crawlPressed();
	}
	
	public void crawlReleased() {
		player.crawlReleased();
	}
	
}
