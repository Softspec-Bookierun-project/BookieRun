package endless;

import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Help extends JFrame{
	private JPanel helpPanel;
	private Image image;
	
	public Help(){
		setTitle("BookieRun");
		setPreferredSize(new Dimension(Menu.WIDTH, Menu.HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initHelp();
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}
	
	private void initHelp() {
		helpPanel = new JPanel() {
			{
				setPreferredSize(new Dimension(Menu.WIDTH, Menu.HEIGHT));
			}

			@Override
			public void paint(Graphics g) {
				
				super.paint(g);
				paintBackground(g);

			}
		};
		add(helpPanel);
		this.addMouseListener(new MouseInput());
	}

	private void paintBackground(Graphics g){
	
	
		try {                
	          image = ImageIO.read(new File("../EndlessRunner/res/images/Help.jpg"));
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
			if(mx >= 43 && mx <= 159 ){
				if(my >= 342 && my <= 373){
					Menu m = new Menu();
					m.setVisible(true);
					dispose();
				}
			}
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int mx = e.getX();
			int my = e.getY();
			if(mx >= 43 && mx <= 159 ){
				if(my >= 342 && my <= 373){
					Menu m = new Menu();
					m.setVisible(true);
					dispose();
				}
			}
		}
		
	}
}
