package endless;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import endless.model.Game;


public class Window extends JFrame implements Observer {

	
	
	private JPanel drawPanel;
	private Image image;

	private Game game;
	
	public Window() {
		setTitle("BookieRun");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new Controller());
		setPreferredSize(new Dimension(Menu.WIDTH, Menu.HEIGHT));
		initComponents();
		setResizable(false);
		
		game = new Game();
		game.addObserver(this);
		pack();
		setLocationRelativeTo(null);
	}


	public void start() {
		game.start();
	}
	

	private void initComponents() {
		drawPanel = new JPanel() {
			{
				setPreferredSize(new Dimension(Menu.WIDTH, Menu.HEIGHT));
			}

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				paintBackground(g);
				drawObject(g);
			}

		};
		add(drawPanel);
	}
	
	
	private void paintBackground(Graphics g) {
		
		try {                
	          image = ImageIO.read(new File("../EndlessRunner/res/images/stage.jpg"));
	       } catch (IOException ex) {
	            ex.printStackTrace();
	       }
		
		g.drawImage(image, 0, 0, null);
	}

	private void drawObject(Graphics g) {
		g.setColor(Color.blue);
		

		try {
			image = ImageIO.read(new File("../EndlessRunner/res/images/cookie.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		g.drawImage(image,Menu.VIEWOFFSET + game.getPlayerX(),
				reversedY(Menu.VIEWOFFSET + game.getPlayerY() + game.getPlayerHeight()),
				game.getPlayerWidth(), 
				game.getPlayerHeight(), null);
		
		game.drawCoin(g);
		game.drawEnemy(g);
			
		g.setColor(Color.black);
		for(int i=0;i<5;i++){
			g.fillRect(Menu.VIEWOFFSET + game.getFloorX(i), 
					reversedY(Menu.VIEWOFFSET + game.getFloorY(i) + game.getFloorHeight(i)),
					game.getFloorWidth(i), 
					game.getFloorHeight(i));
			
			
		}
		
		g.setColor(new Color(0, 0, 0, 80));
		g.fillRect(68, 50, 200, 15);

		g.setColor(Color.gray);
		g.setColor(Color.white);

	

		try {                
	          image = ImageIO.read(new File("../EndlessRunner/res/images/bar.jpg"));
	       } catch (IOException ex) {
	            ex.printStackTrace();
	       }
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Courier", Font.BOLD, 30));
		g.drawImage(image ,68, 50, (int)game.getPlayerHp(),15, null);
		g.drawString(Integer.toString(game.getPlayerScore()), 650, 65);
	}

	private int reversedY(int y) {
		return Menu.HEIGHT - y;
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
		if(game.getPlayerState()){
			dispose();
		}
	}

	class Controller extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			if (e.getKeyCode() == KeyEvent.VK_Z) {
				game.jumpPressed();
			} else if (e.getKeyCode() == KeyEvent.VK_X) {
				game.crawlPressed();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			super.keyReleased(e);
			if (e.getKeyCode() == KeyEvent.VK_X) {
				game.crawlReleased();
			}
		}
	}
	
}
	
