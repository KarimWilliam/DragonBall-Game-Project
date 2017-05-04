package dragonball.view;
import javax.swing.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
public class Testing {

	
	public static void main (String [] args)
	{
		JFrame myWindow = new JFrame();
		myWindow.setSize(1000,600);
		JLabel myLabel = new JLabel("Testing things here");
		myWindow.setTitle("hmmm");
		
		myWindow.getContentPane().setBackground(Color.black);
		myWindow.add(myLabel);
		myWindow.getContentPane().setLayout(new GridLayout(2,3));
		myWindow.setVisible(true);
		myWindow.getContentPane().setLayout(new FlowLayout());
		JButton button1 = new JButton("Red");
		button1.setSize(100,101);
		myWindow.add(button1);
		button1.setVisible(true);
		
		JButton button2 = new JButton("Black");
		button2.setSize(100,101);
		myWindow.add(button2);
		button2.setVisible(true);
		
		button1.addActionListener((ActionListener) myWindow);
	}
}
