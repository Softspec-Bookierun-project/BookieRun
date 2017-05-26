package endless;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import endless.model.Game;


public class Window extends JFrame implements Observer {

	
	
	private JPanel drawPanel;

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
		g.setColor(Color.white);
		g.fillRect(0, 0, Menu.WIDTH, Menu.HEIGHT);
	}

	private void drawObject(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(Menu.VIEWOFFSET + game.getPlayerX(), 
				reversedY(Menu.VIEWOFFSET + game.getPlayerY() + game.getPlayerHeight()),
				game.getPlayerWidth(), 
				game.getPlayerHeight());
		
		game.drawCoin(g);
			
		g.setColor(Color.black);
		for(int i=0;i<5;i++){
			g.fillRect(Menu.VIEWOFFSET + game.getFloorX(i), 
					reversedY(Menu.VIEWOFFSET + game.getFloorY(i) + game.getFloorHeight(i)),
					game.getFloorWidth(i), 
					game.getFloorHeight(i));
		}
		
//		g.setColor(Color.gray);
//		g.fillRect(width/3, 5, 200, 25);
		
		g.setColor(Color.yellow);
		((Graphics2D) g).fill( new Rectangle2D.Double(Menu.WIDTH/3, 5,game.getPlayerHp(),25));
		
		g.setColor(Color.white);
		g.drawRect(Menu.WIDTH/3, 5, 200, 25);
		
		g.setColor(Color.black);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.drawString("hp", (Menu.WIDTH/3)-40, 20);
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
	
