package Particles;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ParticlePanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	public ParticlePanel()
	{
		setBackground(Color.white);
		setPreferredSize(new Dimension(800,800));
		
		new Timer(1000/60, this).start();
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.translate(getWidth()/2, getHeight()/2);
		g2.scale(1, -1);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		/* Hier moet een update komen */
	}
}
