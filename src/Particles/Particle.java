package Particles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class Particle 
{
	private Point2D position;
	private Shape circle = new Ellipse2D.Double();
	private int lenght = 100;
	private Color color;
	private int life = 255;
	private double speedX;
	private double speedY;
	
	public Particle(Point2D p, Color color, double speedX, double speedY)
	{
		this.position = new Point2D.Double(p.getX() - lenght/2, p.getY() - lenght/2);
		this.color = color;
		circle = new Ellipse2D.Double(position.getX() , position.getY(), lenght, lenght);
		this.speedX = speedX;
		this.speedY = speedY;
	}
	
	public void update()
	{
		position = new Point2D.Double(position.getX() + speedX, position.getY() + speedY);
		circle = new Ellipse2D.Double(position.getX(), position.getY(), lenght, lenght);
		if(life >= 5)
		{
			life -= 5;
		}
	}
	
	public void getGradient(Graphics2D g2)
	{
		Shape s = circle;
		float h = lenght/2;
		Point2D point = position;
		Color[] colors = new Color[]{new Color(color.getRed(), color.getGreen(), color.getBlue(), life),new Color(255,255,255,0)};
		float[] fl = new float[] {0.0f, 0.5f };
		
		g2.setPaint(new RadialGradientPaint(new Point2D.Double(point.getX()+h,point.getY()+h), h, fl, colors));
		g2.fill(s);
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public int getLife()
	{
		return life;
	}
}
