package endless;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import endless.model.Game;

public class GameOver extends JFrame{
	private static final String FILE = "../EndlessRunner/res/file/HighScore.txt";
	
	private JPanel helpPanel;
	private Image image;

	private int score;
	private int highScore;
	
	
	public GameOver(int score){
		setTitle("BookieRun");
		setPreferredSize(new Dimension(Menu.WIDTH, Menu.HEIGHT));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initHelp();
		this.score = score;
		highScore = 0;
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
		BufferedReader br = null;
		FileReader fr = null;
		
		try {

			fr = new FileReader(FILE);
			br = new BufferedReader(fr);

			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILE));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
				highScore = Integer.parseInt(sCurrentLine);
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	
		try {                
	          image = ImageIO.read(new File("../EndlessRunner/res/images/over.jpg"));
	       } catch (IOException ex) {
	            ex.printStackTrace();
	       }
		
		g.drawImage(image, 0, 0, null);
		
		g.setFont(new Font("Courier", Font.BOLD, 30));
		g.drawString(Integer.toString(score), 300, 172);
		
		g.setFont(new Font("Courier", Font.BOLD, 30));
		g.drawString(Integer.toString(highScore), 300, 248);
		
		
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
