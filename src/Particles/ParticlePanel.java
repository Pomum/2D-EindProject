package Particles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class ParticlePanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	ArrayList<Particle> particles = new ArrayList<Particle>(); 
	private Point2D clickPoint;
	Color color = Color.white;
	
	public ParticlePanel()
	{
		setBackground(new Color(180, 180, 180));
		setPreferredSize(new Dimension(1500,900));
		mouse();
		new Timer(100/6, this).start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		paint(g2);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		ListIterator<Particle> list = particles.listIterator();
		while(list.hasNext())
		{
			Particle p = list.next();
			if(p.getLife() <= 0)
			{
				list.remove();
			}
		}
		for(Particle p : particles)
		{
			p.update();
		}
	}
	
	public void mouse()
	{
		addMouseListener(new MouseAdapter() 
		{
			public void mousePressed(MouseEvent e) 
			{
				if (SwingUtilities.isLeftMouseButton(e))
				{
					clickPoint = e.getPoint();
					particles.add(new Particle(clickPoint, color, 0, -1));
				}
			}
		});
		
		addMouseMotionListener(new MouseMotionAdapter() 
		{
			public void mouseDragged(MouseEvent e) 
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					clickPoint = e.getPoint();
					
					double[] dArray = new double[] {0.5, 0.7, 1};
					for(double d : dArray)
					{
						int random = (int)(Math.random()*10);
						if(random == 1){particles.add(new Particle(clickPoint, color, 0, -d));}
						if(random == 3){particles.add(new Particle(clickPoint, color, -d, 0));}
						if(random == 5){particles.add(new Particle(clickPoint, color, 0, d));}
						if(random == 7){particles.add(new Particle(clickPoint, color, d, 0));}
						
						if(random == 2){particles.add(new Particle(clickPoint, color, -d, -d));}
						if(random == 4){particles.add(new Particle(clickPoint, color, d, d));}
						if(random == 6){particles.add(new Particle(clickPoint, color, -d, d));}
						if(random == 8){particles.add(new Particle(clickPoint, color, d, -d));}
					}
				}
			}
		});
	}
	
	public void paint(Graphics2D g2)
	{
		for(Particle p : particles)
		{
			gradient(g2, p);
		}
		repaint();
	}
	
	public void gradient(Graphics2D g2, Particle p)
	{
		Shape s = p.getCircle();
		float h = p.getLenght()/2;
		Point2D point = p.getPosition();
		Color[] colors = new Color[]{new Color(p.getColor().getRed(), p.getColor().getGreen(), p.getColor().getBlue(), p.getLife()),new Color(255,255,255,0)};
		float[] fl = new float[] {0.0f, 0.5f };
		
		g2.setPaint(new RadialGradientPaint(new Point2D.Double(point.getX()+h,point.getY()+h), h, fl, colors));
		g2.fill(s);
	}
}