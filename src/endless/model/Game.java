package endless.model;
import java.util.ArrayList;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import endless.Menu;
import endless.Window;

public class Game extends Observable {

	public static final int FPS = 60;
	public static final float GRAVITY = -1500;
	
	private Player player;
	private Ball ball;
	private ArrayList<Floor> floor = new ArrayList<Floor>();
	private int useFloor = 0;
	
	private boolean running;
	private Thread gameThread;
	
	
	public Game() {
		player = new Player(0, 0);
		ball = new Ball(1000, 0);
		
		int j= -50;
		for(int i=0; i< 5;i++){
			floor.add(new Floor(j, -30));
			j += 1100;
		}
		
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
		
		if(floor.get(useFloor+1).getX() <= -50)
			useFloor++;
		
		if(((player.getX() < (floor.get(useFloor+1).getX()))&&(player.getX() > (floor.get(useFloor).getX())+ floor.get(useFloor).getWidth())) && (player.getY() == 0))
				player.setFloor(false);
		
		System.out.println(useFloor);
		
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
