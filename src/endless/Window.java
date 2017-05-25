package endless;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import endless.model.Game;

public class Window extends JFrame implements Observer {

	private int width = 736;
	private int height = 414;
	private int viewOffset = 50;

	private JPanel drawPanel;
	private Game game;

	public Window() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addKeyListener(new Controller());
		initComponents();
		game = new Game();
		game.addObserver(this);
		pack();
	}

	public void start() {
		game.start();
	}

	private void initComponents() {
		drawPanel = new JPanel() {
			{
				setPreferredSize(new Dimension(width, height));
			}

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				paintBackground(g);
				drawCharacter(g);
			}

		};
		add(drawPanel);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
	}

	private void drawCharacter(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(viewOffset + game.getPlayerX(), 
				reversedY(viewOffset + game.getPlayerY() + game.getPlayerHeight()),
				game.getPlayerWidth(), 
				game.getPlayerHeight());
		
		g.fillOval(viewOffset + game.getBallX(), 
				reversedY(viewOffset + game.getBallY() + game.getBallHeight()),
				game.getBallWidth(), 
				game.getBallHeight());
		
		g.setColor(Color.black);
		
		for(int i=0;i<5;i++){
			g.fillRect(viewOffset + game.getFloorX(i), 
					reversedY(viewOffset + game.getFloorY(i) + game.getFloorHeight(i)),
					game.getFloorWidth(i), 
					game.getFloorHeight(i));
		}
	}

	private int reversedY(int y) {
		return height - y;
	}

	@Override
	public void update(Observable o, Object arg) {
		repaint();
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
