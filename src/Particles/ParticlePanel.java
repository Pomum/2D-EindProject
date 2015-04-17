package Particles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		ListIterator<Particle> particleIterator = particles.listIterator();
		while(particleIterator.hasNext())
		{
			Particle p = particleIterator.next();
			if(p.getLife() <= 0)
			{
				particleIterator.remove();
			}
		}
		for(Particle p : particles)
		{
			p.update();
		}
	}
	
	public void mouse()
	{
		addMouseMotionListener(new MouseMotionAdapter() 
		{
			public void mouseDragged(MouseEvent e) 
			{
				if (SwingUtilities.isLeftMouseButton(e))
				{
					clickPoint = e.getPoint();
					double[] dArray = new double[] {0.5, 1, 1.5};
					addParticles(dArray);
				}
				if (SwingUtilities.isRightMouseButton(e))
				{
					clickPoint = e.getPoint();
					double[] dArray = new double[] {0.5, 1, 1.5, 2, 2.5};
					addParticles(dArray);
				}
			}
		});
	}
	
	public void paint(Graphics2D g2)
	{
		for(Particle p : particles)
		{
			p.getGradient(g2);
		}
		repaint();
	}
	
	public void addParticles(double[] dArray)
	{
		for(double d : dArray)
		{
			double randomDirection = Math.random()*2*Math.PI;
			particles.add(new Particle(clickPoint, color, d * Math.cos(randomDirection), d * Math.sin(randomDirection)));
			randomDirection = Math.random()*2*Math.PI;
			particles.add(new Particle(clickPoint, color, d * Math.cos(randomDirection), d * Math.sin(randomDirection)));
		}
	}
}