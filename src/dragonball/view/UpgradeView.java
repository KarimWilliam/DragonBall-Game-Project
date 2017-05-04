package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import dragonball.controller.UpgradeController;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;

public class UpgradeView extends GameView {
	
	private JPanel panelCells;
	private JPanel pics;
	private MyTextArea abilityPoints; 
	private JPanel bottomPart;
	private ImageStorage IS;
	private ArrayList<JPanel> Squares;
	private JButton Done;
	
	
	// make array of strings to hold values 
	private UpgradeController UC;
	public UpgradeView( UpgradeController UC, ImageStorage IS)
	{
		this.UC = UC;
		this.IS = IS;
		setTitle("Upgrade stats");
		setBounds(0,0,1900,1000);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setBackground(Color.GREEN);
		panelCells = new JPanel();
		panelCells.setLayout(new GridLayout(1,0));
		add(panelCells, BorderLayout.NORTH);
		panelCells.setPreferredSize(new Dimension(getWidth(),200));
		panelCells.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		
		
		pics = new JPanel();
		pics.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		pics.setLayout(new GridLayout(1,5));
		add(pics, BorderLayout.CENTER);
		pics.setBackground(Color.GREEN);
		
		JLabel P1 = new JLabel();
		//Image pic1 = getImage("src/resources/Health.Png");
		Image newpic1 = IS.getHealth().getScaledInstance( 230,250,  java.awt.Image.SCALE_SMOOTH ) ;
		P1.setIcon(new ImageIcon(newpic1));
		P1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		pics.add(P1);
		
		JLabel P2 = new JLabel();
		//Image pic2 = getImage("src/resources/Damage.Png");
		Image newpic2 = IS.getDamage().getScaledInstance( 200,300,  java.awt.Image.SCALE_SMOOTH ) ;
		P2.setIcon(new ImageIcon(newpic2));
		pics.add(P2);
		P2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		JLabel P3 = new JLabel();
		//Image pic3 = getImage("src/resources/Stamina.Png");
		Image newpic3 = IS.getStamina().getScaledInstance( 200,300,  java.awt.Image.SCALE_SMOOTH ) ;
		P3.setIcon(new ImageIcon(newpic3));
		pics.add(P3);
		P3.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		JLabel P4 = new JLabel();
		Image pic4 = getImage("src/resources/KI.Png");
		Image newpic4 = IS.getKI().getScaledInstance( 250,300,  java.awt.Image.SCALE_SMOOTH ) ;
		P4.setIcon(new ImageIcon(newpic4));
		pics.add(P4);
		P4.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		JLabel P5 = new JLabel();
		Image pic5 = getImage("src/resources/Blast.Png");
		Image newpic5 = IS.getBlast().getScaledInstance( 300,300,  java.awt.Image.SCALE_SMOOTH ) ;
		P5.setIcon(new ImageIcon(newpic5));
		pics.add(P5);
		P5.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		
		
		abilityPoints = new MyTextArea();
		abilityPoints.setEditable(false);
		abilityPoints.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
		
		abilityPoints.setBackground(Color.green);
		abilityPoints.setBackground(new Color(1,1,1, (float) 0.01));
		//abilityPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		abilityPoints.setBackground(Color.green);
		//add(abilityPoints, BorderLayout.SOUTH);
		
		Done = new JButton();
		Done.setBackground(Color.green);
		Done.setText("Done");
		Done.addActionListener(UC);
		Done.setActionCommand("Done");
		Done.setPreferredSize(new Dimension(200, 100));
		Done.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
		Done.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 50));
		//add(Done, BorderLayout.SOUTH);
		
		abilityPoints.setPreferredSize(new Dimension(900, 100));
		
		bottomPart = new JPanel();
		bottomPart.setLayout(new FlowLayout());
		add(bottomPart,BorderLayout.SOUTH);
		bottomPart.setPreferredSize(new Dimension(getWidth(),110));
		bottomPart.add(abilityPoints);
		bottomPart.add(Done);
		bottomPart.setBackground(Color.green);
		bottomPart.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
	}
	
	public void makeSquare (String state,int originalValue, JTextArea JTA)
	{
		JButton up = new JButton();
		JButton down = new JButton();
		
		//Image aup = getImage("src/resources/blue.Png");
		Image newaup = IS.getUP().getScaledInstance( 50,50,  java.awt.Image.SCALE_SMOOTH ) ;
		up.setIcon(new ImageIcon(newaup));
		up.addActionListener(this.UC);
		down.addActionListener(this.UC);
		up.setActionCommand(state + "up");
		down.setActionCommand(state + "down");
		

		//Image adown = getImage("src/resources/red.Png");
		Image newadown = IS.getDown().getScaledInstance( 50,50,  java.awt.Image.SCALE_SMOOTH ) ;
		down.setIcon(new ImageIcon(newadown));
		

		
		JTA.setEditable(false);
		JTA.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		JTA.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 30));
		JTA.setText(state + "\n" + originalValue);
		JTA.setPreferredSize(new Dimension(150,120));
		//btnsAr.add(up);
		//btnsAr.add(down);
		
		JPanel Square = new JPanel();
		Square.setLayout(new GridBagLayout());
		
		//text area
		GridBagConstraints c = new GridBagConstraints();
		c.gridheight = 2;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		Square.add(JTA, c);
		
		// up button
		GridBagConstraints a = new GridBagConstraints();
		a.gridx = 1;
		a.gridy = 0;
		a.gridheight = 1;
		a.gridwidth = 1;
		Square.add(up, a);
		
		//down button
		GridBagConstraints b = new GridBagConstraints();
		b.gridx = 1;
		b.gridy = 1;
		b.gridheight = 1;
		b.gridwidth = 1;
		Square.add(down, b);
		
		Square.setBackground(Color.green);
		abilityPoints.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.panelCells.add(Square);
		//Squares.add(Square);
		
	}
	
	public void addStateSquare (JPanel JP)
	{
		panelCells.add(JP);
	}

	public JPanel getPanelCells() {
		return panelCells;
	}

	public void setPanelCells(JPanel panelCells) {
		this.panelCells = panelCells;
	}
	
	public Image getImage (String path)
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
	
	public static void main (String [] args) throws MissingFieldException, UnknownAttackTypeException
	{
	
	}

	public MyTextArea getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(MyTextArea abilityPoints) {
		this.abilityPoints = abilityPoints;
	}

	public ArrayList<JPanel> getSquares() {
		return Squares;
	}


	
	
	

}
