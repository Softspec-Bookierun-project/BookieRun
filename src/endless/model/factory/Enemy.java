package endless.model.factory;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.Observable;
import java.awt.Color;



public class Enemy extends Observable implements Shapes{

	public static final int WIDTH = 60;

	private int x;
	private int y;
	private int vY = 7;
	private int width;
	private boolean hit = false;
	
	
	// TODO: Add variables you need.

	public Enemy() {
		this.x = 1000;
		this.y = 270;
		this.width = WIDTH;
		
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getvY() {
		return vY;
	}

	public int getWidth() {
		return width;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setvY(int vY) {
		this.vY = vY;
	}
	
	public void setHit(boolean hit){
		this.hit = hit;
	}
	
	public void update() {
		// TODO: Complete this
		this.x -= vY;
		if(this.x < 100 && this.x > 0 && this.hit == false){
			setChanged();
			notifyObservers(this);
		}
		if(this.x < -50){
			int k = (int)(Math.random() * 3);
			this.hit = false;
			if(k == 0)
				this.y = 300;
			else if(k == 1)
				this.y = 270;
			else
				this.y = 200;
			this.x = 1000;
		}
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D gr = (Graphics2D) g;
        gr.setPaint(new RadialGradientPaint(
                new Point(this.x, this.y), 60, new float[] { 0, 1 }, 
                new Color[] { Color.RED, Color.YELLOW }));
        gr.fill(createStar(this.x, this.y, 20, this.width, 10, 0));
	}
	
    private static Shape createStar(double centerX, double centerY,
            double innerRadius, double outerRadius, int numRays,
            double startAngleRad)
        {
            Path2D path = new Path2D.Double();
            double deltaAngleRad = Math.PI / numRays;
            for (int i = 0; i < numRays * 2; i++)
            {
                double angleRad = startAngleRad + i * deltaAngleRad;
                double ca = Math.cos(angleRad);
                double sa = Math.sin(angleRad);
                double relX = ca;
                double relY = sa;
                if ((i & 1) == 0)
                {
                    relX *= outerRadius;
                    relY *= outerRadius;
                }
                else
                {
                    relX *= innerRadius;
                    relY *= innerRadius;
                }
                if (i == 0)
                {
                    path.moveTo(centerX + relX, centerY + relY);
                }
                else
                {
                    path.lineTo(centerX + relX, centerY + relY);
                }
            }
            path.closePath();
            return path;
        }

}