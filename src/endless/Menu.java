package endless;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Menu extends JFrame{
	private int width = 736;
	private int height = 414;
	
	public Rectangle playButton = new Rectangle((width/2) - 65, 120, 100, 50);
	public Rectangle helpButton = new Rectangle((width/2) - 65, 220, 100, 50);
	public Rectangle quitButton = new Rectangle((width/2) - 65, 320, 100, 50);
	
	private JPanel menuPanel;
	
	public Menu(){
		setTitle("BookieRun");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initMenu();
		setResizable(false);

		pack();
		setLocationRelativeTo(null);
	}
	
	private void initMenu() {
		menuPanel = new JPanel() {
			{
				setPreferredSize(new Dimension(width, height));
			}

			@Override
			public void paint(Graphics g) {
				
				super.paint(g);
				paintMenuBackground(g);
				renderMenu(g);
			}

		};
		add(menuPanel);
		this.addMouseListener(new MouseInput());
	}

	public void renderMenu(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		
		Font fnt0 = new Font("Arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.WHITE);
		g.drawString("BookieRun", (width/3)-30, 75);
		
		Font fnt1 = new Font("Arial", Font.BOLD, 30);
		g.setFont(fnt1);
		g.drawString("Play", playButton.x + 20, playButton.y + 35);
		g.drawString("Help", helpButton.x + 20, helpButton.y + 35);
		g.drawString("Quit", quitButton.x + 20, quitButton.y + 35);
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(quitButton);
	}
	
	private void paintMenuBackground(Graphics g){
		g.setColor(Color.black);
		g.fillRect(0, 0, width, height);
	}
	
	class MouseInput implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			int mx = e.getX();
			int my = e.getY();
			
			if(mx >= width/2 - 65 && mx <= width/2 + 35 ){
				if(my >= 153 && my <= 202){
					setVisible(false);
					Window w = new Window();
					w.setVisible(true);
					w.start();
				}
			}
			
			if(mx >= width/2 - 65 && mx <= width/2 + 35 ){
				if(my >= 352 && my <= 402){
					System.exit(1);
				}
			}
			System.out.println(mx);
			System.out.println(my);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}


