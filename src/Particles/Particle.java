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
	private Shape circle;
	private int lenght = 20;
	
	public Particle(Point2D position, Point2D target)
	{
		this.position = position;
		this.target = target;
		this.speed = 5;
		Shape circle = new Ellipse2D.Double(position.getX() - lenght/2, position.getY() - lenght/2, lenght, lenght);
	}
	public void update()
	{
		rotations();
		position = new Point2D.Double(position.getX() + speed , position.getY() + speed);
	}
	
	public void rotations()
	{
		rotation+=0.01;
		
		Point2D difference = new Point2D.Double(
				target.getX() - position.getX(),
				target.getY() - position.getY()
				);
		
		double newRotation = Math.atan2(difference.getY(), difference.getX());
		double rotDifference = rotation - newRotation;
		
		while(rotDifference > Math.PI)
		{
			rotDifference -= 2 * Math.PI;
		}
		while(rotDifference < -Math.PI)
		{
			rotDifference += 2 * Math.PI;
		}
		if(Math.abs(rotDifference) < 0.1)
		{
			rotation = newRotation;
		}
		else if(rotDifference < 0)
		{
			rotation += 0.1;
		}
		else if(rotDifference > 0)
		{
			rotation -= 0.1;
		}
	}
	
	void paint(Graphics2D g2)
	{
		//AffineTransform tx = new AffineTransform();
		//tx.translate(position.getX()-lenght/2, position.getY()-lenght/2);
		//tx.rotate(rotation, 8, 8);
		
		g2.draw(circle);
	}
}
