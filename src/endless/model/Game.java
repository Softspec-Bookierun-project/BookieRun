package endless.model;
import java.util.ArrayList;
import java.util.Observable;

import endless.state.StateJumpOne;


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
		
		floor.add(new Floor(-50, -30, ((int)(Math.random()* 400))+200));
		for(int i=1; i< 5;i++){
			floor.add(new Floor(floor.get(i-1).getX()+floor.get(i-1).getWidth()+(int)(Math.random()* 300)+100, -30, ((int)(Math.random()* 700))+200));
		}
		endOfFloor = floor.get(4).getX()+floor.get(4).getWidth() - floor.get(0).getWidth() + 100;
		
	}
	
	public void start() {
		running = true;
		gameThread = new Thread() {
			@Override
			public void run() {
				super.run();
				while(running) {
					singleFrame();
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
		
		if((floor.get(useFloor).getX()+floor.get(useFloor).getWidth()) <= -50){
			floor.get(useFloor).setX(endOfFloor);
			floor.get(useFloor).setWidth((int)(Math.random()* 700)+200);
			
			if(useFloor+1 < 5){
				endOfFloor = floor.get(useFloor).getX()+floor.get(useFloor).getWidth() - floor.get(useFloor+1).getWidth();
				useFloor++;
			}
			else{
				endOfFloor = floor.get(useFloor).getX()+floor.get(useFloor).getWidth() - floor.get(0).getWidth();
				System.out.println("======================================");
				useFloor = 0;
			}
		}
		
		if(useFloor+1 < 5){
			if(((player.getX() < (floor.get(useFloor+1).getX()))&&(player.getX() > (floor.get(useFloor).getX())+ floor.get(useFloor).getWidth())) && (player.getY() == 0)){
				player.setJumpTime(System.currentTimeMillis());
				player.setJumpY(player.getY());
				player.setJumpSpeed(0);
				player.setState(new StateJumpOne(this.player));
				player.setFloor(false);
			}
		}else {
			if(((player.getX() < (floor.get(0).getX()))&&(player.getX() > (floor.get(useFloor).getX())+ floor.get(useFloor).getWidth())) && (player.getY() == 0)){
				player.setJumpTime(System.currentTimeMillis());
				player.setJumpY(player.getY());
				player.setJumpSpeed(0);
				player.setState(new StateJumpOne(this.player));
				player.setFloor(false);
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
