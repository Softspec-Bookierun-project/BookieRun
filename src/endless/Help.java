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
	          image = ImageIO.read(new File("../EndlessRunner/res/Help.jpg"));
	       } catch (IOException ex) {
	            // handle exception...
	       }
		
		g.drawImage(image, 0, 0, null);
//		g.setColor(Color.black);
//		g.fillRect(0, 0, Menu.WIDTH, Menu.HEIGHT);
		
		
		
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
			// TODO Auto-generated method stub
			int mx = e.getX();
			int my = e.getY();
			if(mx >= 43 && mx <= 159 ){
				if(my >= 342 && my <= 373){
					Menu m = new Menu();
					m.setVisible(true);
					dispose();
				}
			}
			
			System.out.println(mx);
			System.out.println(my);
		}
		
	}
}
