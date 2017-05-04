package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DragonView extends GameView {

	private JLabel DragonPanel;
	private JPanel ChoicePanel;
	
	public DragonView (ImageStorage IS)
	{
		setTitle("Dragon");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 900);
		ChoicePanel = new JPanel();
		ChoicePanel.setLayout(new GridLayout(1, 0));
		add(ChoicePanel, BorderLayout.SOUTH);
	
		//Image Dragon =  getImage ("src/resources/Dragony.Png");
		JLabel DragonPanel = new JLabel();
		Image Dragony = IS.getDragon().getScaledInstance(getWidth(), 700,  java.awt.Image.SCALE_SMOOTH);
		DragonPanel.setIcon(new ImageIcon( Dragony));
		this.add( DragonPanel, BorderLayout.NORTH );
		DragonPanel.setPreferredSize(new Dimension (getWidth(),800));
		this.pack();
	}
	
	public static void main (String [] args)
	{
	
	}
	
	public Image getImage (String path)
	{
		Image img;
		try {
			img = ImageIO.read(new File(path));
			return img;
			
		} catch (IOException e) {
			System.out.println("file not found");
			return null;
		}
	}
	
	public  void addChoice(JButton Choice) {
		ChoicePanel.add(Choice);
	}
}
