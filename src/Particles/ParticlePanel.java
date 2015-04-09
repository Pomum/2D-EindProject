package Particles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ParticlePanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	ArrayList<Particle> particles = new ArrayList<Particle>(); 
	private Point2D target;
	
	public ParticlePanel()
	{
		setBackground(Color.white);
		setPreferredSize(new Dimension(1500,900));
		mouse();
		new Timer(1000/60, this).start();
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
				Point2D clickPoint = e.getPoint();
				target = new Point2D.Double(clickPoint.getX(), clickPoint.getY()-100);
				Particle part = new Particle(clickPoint, target);
				particles.add(part);
			}
		});
	}
	
	public void paint(Graphics2D g2)
	{
		for(Particle p : particles)
		{
			Shape s = p.getCircle();
			int hLenght = p.getLenght()/2;
			Point2D point = p.getPosition();
			
			g2.setPaint(new GradientPaint((int)(point.getX()-hLenght),(int)(point.getY()+hLenght),Color.cyan,(int)(point.getX()+hLenght),(int)(point.getX()-hLenght),Color.white));
			g2.fill(s);
			g2.setPaint(new GradientPaint((int)(point.getX()-hLenght),(int)(point.getY()+hLenght),Color.white,(int)(point.getX()+hLenght),(int)(point.getX()-hLenght),new Color(255, 255, 0, 0)));
			g2.fill(s);
		}
		repaint();
	}
}