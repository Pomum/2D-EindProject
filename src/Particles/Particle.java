package Particles;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Particle 
{
	private Point2D position;
	private Point2D target;
	private double speed;
	private Shape circle = new Ellipse2D.Double();
	private int lenght = 10;
	private Color color;
	
	public Particle(Point2D p, Point2D target, Color color)
	{
		this.position = new Point2D.Double(p.getX() - lenght/2, p.getY() - lenght/2);
		this.target = target;
		this.speed = 1;
		this.color = color;
		circle = new Ellipse2D.Double(position.getX() , position.getY(), lenght, lenght);
	}
	
	public void update()
	{
			position = new Point2D.Double(position.getX(), position.getY() - speed);
			circle = new Ellipse2D.Double(position.getX(), position.getY(), lenght, lenght);
	}
	
	public Shape getCircle()
	{
		return circle;
	}
	
	public Point2D getPosition()
	{
		return position;
	}
	
	public Point2D getTarget()
	{
		return target;
	}
	
	public int getLenght()
	{
		return lenght;
	}
	
	public Color getColor()
	{
		return color;
	}
}
