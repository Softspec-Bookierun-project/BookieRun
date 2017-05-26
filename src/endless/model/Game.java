package endless.model;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import endless.Menu;
import endless.Window;
import endless.model.factory.Coin;
import endless.model.factory.ShapeFactory;
import endless.state.StateJumpOne;
import endless.state.StateJumpTwo;
import java.awt.Color;

public class Game extends Observable implements Observer{

	public static final int FPS = 60;
	public static final float GRAVITY = -1500;
	
	private Player player;
	private ArrayList<Floor> floor = new ArrayList<Floor>();
	private ArrayList<Coin> coins = new ArrayList<Coin>();
	private int useFloor = 0;
	private int endOfFloor;
	private static final Color colors[] = { Color.BLUE, Color.GREEN, Color.ORANGE, Color.PINK, Color.BLACK };
	
	private boolean running;
	private Thread gameThread;
	
	
	public Game() {
		player = new Player(0, 0);
		
		coins.add((Coin)ShapeFactory.getCoin(getRandomColor()));
		coins.get(0).addObserver(this);
		
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
		
		for(int i=0; i<coins.size();i++){
			coins.get(i).update();
			
		}
		for(int i=0;i<5;i++){
			floor.get(i).update();
		}
		
		setChanged();
		notifyObservers();
	}
	
	public int getPlayerX() {
		return player.getX();
	}
	
	public void drawCoin(Graphics g){
		for(int i=0; i<coins.size();i++)
			coins.get(i).draw(g);
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
	
	public int getCoinX() {
		return coins.get(0).getX();
	}
	
	public int getCoinY() {
		return coins.get(0).getY();
	}
	
	public int getCoinWidth() {
		return coins.get(0).getWidth();
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
	
	public void jumpPressed() {
		player.jumpPressed();
	}
	
	public void crawlPressed() {
		player.crawlPressed();
	}
	
	public void crawlReleased() {
		player.crawlReleased();
	}
	
   private static Color getRandomColor() {
	      return colors[(int)(Math.random()*colors.length)];
	   }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg instanceof String){
			ShapeFactory.store(coins.get(0));
			coins.get(0).deleteObserver(this);
			coins.remove(0);
		}
		else if(arg != null){
			if(((364-((Coin)arg).getY()- 20) < player.getY() + player.getHeight()) && (364 - ((Coin)arg).getY() > player.getY())){
				coins.get(0).setX(1000);
				coins.get(0).setY((int)(Math.random() * 200)+100);
				coins.get(0).setVisible(false);
				coins.get(0).setCheckDraw(false);
				ShapeFactory.store((Coin)arg);
				coins.get(0).deleteObserver(this);
				coins.remove(0);
			}
		}
		else{
			coins.add((Coin)ShapeFactory.getCoin(getRandomColor()));
			coins.get(coins.size()-1).addObserver(this);
		}
	}
	
}
