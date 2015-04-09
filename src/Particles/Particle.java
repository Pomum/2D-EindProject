package Particles;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Particle 
{
	private Point2D position;
	private Point2D oldPosition;
	private Point2D target;
	private double speed;
	private double rotation;
	private Shape circle = new Ellipse2D.Double();
	private int lenght = 20;
	
	public Particle(Point2D position, Point2D target)
	{
		this.position = position;
		this.target = target;
		this.speed = 5;
		circle = new Ellipse2D.Double(position.getX() - lenght/2, position.getY() - lenght/2, lenght, lenght);
	}
	public void update()
	{
		position = new Point2D.Double(position.getX(), position.getY() + speed);
	}
	
	public Shape getCircle()
	{
		return circle;
	}
}
