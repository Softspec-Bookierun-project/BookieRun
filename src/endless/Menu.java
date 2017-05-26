package endless;

import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame{
	public static int WIDTH = 736;
	public static int HEIGHT = 414;
	public static int VIEWOFFSET = 50;
	
	public Rectangle playButton = new Rectangle((WIDTH/2) - 65, 120, 100, 50);
	public Rectangle helpButton = new Rectangle((WIDTH/2) - 65, 220, 100, 50);
	public Rectangle quitButton = new Rectangle((WIDTH/2) - 65, 320, 100, 50);
	
	private JPanel menuPanel;
	private Image image;

	
	public Menu(){
		setTitle("BookieRun");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initMenu();
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}
	
	private void initMenu() {
		menuPanel = new JPanel() {
			{
				setPreferredSize(new Dimension(WIDTH, HEIGHT));
			}

			@Override
			public void paint(Graphics g) {
					super.paint(g);
					renderMenu(g);
			}

		};
		add(menuPanel);
		this.addMouseListener(new MouseInput());
	}

	public void renderMenu(Graphics g){	
		
		try {                
	          image = ImageIO.read(new File("../EndlessRunner/res/images/main.jpg"));
	       } catch (IOException ex) {
	            ex.printStackTrace();
	       }
		
		g.drawImage(image, 0, 0, null);
	}
	
	class MouseInput implements MouseListener {

	
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			
			if(mx >= 330 && mx <= 425){
				if(my >= 185 && my <= 210){
					dispose();
					Window w = new Window();
					w.setVisible(true);
					w.start();
				}
			}
			
			if(mx >= 270 && mx <= 480){
				if(my >= 250 && my <= 270){
					dispose();
					Help h = new Help();
					h.setVisible(true);
				}
			}
			
			if(mx >= 340 && mx <= 410 ){
				if(my >= 315 && my <= 335){
					System.exit(0);
				}
			}
		}
			

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}
	}

}


