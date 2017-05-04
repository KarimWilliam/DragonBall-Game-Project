package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Test {
	
	public static void main(String [] args)
	{
		int a =4;
		int b =6;
		swap(a,b);
		System.out.println(a);
		System.out.println(b);
	}
	
	public static Image getImage (String path)
	{
		Image img;
		try {
			img = ImageIO.read(new File(path));
			return img;
			
		} catch (IOException e) {
			System.out.println("picture not found");
			return null;
		}
	}
	public static void swap (int a,int b)
	{
		int c =a;
	
		a=b;
		b=c;
		
	}

}
