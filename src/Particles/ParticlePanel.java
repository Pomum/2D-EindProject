package Particles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
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
	
	public ParticlePanel()
	{
		setBackground(Color.white);
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
				clickPoint = e.getPoint();
				Particle part;
				part = new Particle(clickPoint, Color.cyan);
				particles.add(part);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() 
		{
			public void mouseDragged(MouseEvent e) 
			{
				if (SwingUtilities.isRightMouseButton(e))
				{
					clickPoint = e.getPoint();
					Particle part;
					part = new Particle(clickPoint, Color.cyan);
					particles.add(part);
				}
			}
		});
	}
	
	public void paint(Graphics2D g2)
	{
		for(Particle p : particles)
		{
			//bubble(g2, p);
			gradient(g2, p);
		}
		repaint();
	}
	
	public void bubble(Graphics2D g2, Particle p) 
	{
		Shape s = p.getCircle();
		int h = p.getLenght()/2;
		Point2D point = p.getPosition();
		g2.setPaint(Color.white);
		g2.fill(s);
		g2.setPaint(new GradientPaint((int)point.getX()-h/2+h,(int)point.getY()-h/2+h,p.getColor(),(int)point.getX()+h/2+h,(int)point.getY()+h/2+h,Color.white));
		g2.fill(s);
		g2.setPaint(new GradientPaint((int)point.getX()-h/2+h,(int)point.getY()-h/2+h,Color.white,(int)point.getX()+h/2+h,(int)point.getY()+h/2+h,new Color(255, 255, 0, 0)));
		g2.fill(s);
		g2.setPaint(p.getColor());
		g2.draw(s);
	}
	
	public void gradient(Graphics2D g2, Particle p)
	{
		Shape s = p.getCircle();
		float h = p.getLenght()/2;
		Point2D point = p.getPosition();
		Color[] colors = new Color[]{new Color(0, 255, 255, p.getLife()),new Color(255,255,255,0)};
		float[] fl = new float[] {0.0f, 0.5f };
		
		g2.setPaint(new RadialGradientPaint(new Point2D.Double(point.getX()+h,point.getY()+h), h, fl, colors));
		g2.fill(s);
	}
	

	public Color rainbow()
	{
		int random = (int)(Math.random()*10);
		if(random <= 1)
		{
			return new Color(255, 193, 43); // orange
		} 
		else if(random <= 3)
		{
			return new Color(249, 247, 70); // yellow
		}
		else if(random <= 5)
		{
			return new Color(79, 249, 70); // green
		}
		else if(random <= 7)
		{
			return new Color(255, 12, 12); // red
		}
		else
		{
			return Color.cyan; // blue
		}
	}
}