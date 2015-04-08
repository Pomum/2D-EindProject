package Particles;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Launch 
{
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame("Particle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new ParticlePanel();
		
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
