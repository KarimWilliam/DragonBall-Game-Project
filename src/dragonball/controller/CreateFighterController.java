package dragonball.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;

import dragonball.model.game.Game;
import dragonball.view.CreateFighterView;
import dragonball.view.ImageStorage;

public class CreateFighterController implements ActionListener {
	
	private CreateFighterView createfighterview;
	private Game game;
	private GameController GC;
	

	public CreateFighterController (Game game, GameController GC, ImageStorage IS) throws IOException
	{
		this.game = game;
		this.GC = GC;
		createfighterview = new CreateFighterView ();
		JButton Saiyan = new JButton();
		Saiyan.addActionListener(this);
		createfighterview.addFighter(Saiyan);
		//Image Goku = getImage("src/resources/Goku.Png");
		Image newGoku = IS.getGoku().getScaledInstance( 450,550,  java.awt.Image.SCALE_SMOOTH ) ;
		Saiyan.setIcon(new ImageIcon(newGoku));
		Saiyan.setActionCommand("Saiyan");
		Saiyan.setBackground(Color.CYAN);
		
		JButton Earthling = new JButton();
		Earthling.addActionListener(this);
		createfighterview.addFighter(Earthling);
		//Image Earth = getImage("src/resources/Earthling.Png");
		Image newEarthling = IS.getEarthling().getScaledInstance( 400,400,  java.awt.Image.SCALE_SMOOTH ) ;
		Earthling.setIcon(new ImageIcon(newEarthling));
		Earthling.setActionCommand("Earthling");
		Earthling.setBackground(Color.ORANGE);
		
		JButton Frieze = new JButton();
		Frieze.addActionListener(this);
		createfighterview.addFighter(Frieze);
		//Image Fri = getImage("src/resources/Frieza2.Png");
		Image newFrieze = IS.getFrieza().getScaledInstance( 400,500,  java.awt.Image.SCALE_SMOOTH ) ;
		Frieze.setIcon(new ImageIcon(newFrieze));
		Frieze.setActionCommand("Frieza");
		Frieze.setBackground(Color.lightGray);
		
		JButton Majin = new JButton();
		Majin.addActionListener(this);
		createfighterview.addFighter(Majin);
		//Image Maj = getImage("src/resources/Majin.Png");
		Image newMajin = IS.getMajin().getScaledInstance( 300,650,  java.awt.Image.SCALE_SMOOTH ) ;
		Majin.setIcon(new ImageIcon(newMajin));
		Majin.setActionCommand("Majin");
		Majin.setBackground(Color.YELLOW);
	
		JButton Namekian = new JButton();
		Namekian.addActionListener(this);
		createfighterview.addFighter(Namekian);
		//Image Nam = getImage("src/resources/Namekian.Png");
		Image newNamekian = IS.getNamekian().getScaledInstance( 450,500,  java.awt.Image.SCALE_SMOOTH ) ;
		Namekian.setIcon(new ImageIcon(newNamekian));
		Namekian.setActionCommand("Namekian");
		Namekian.setBackground(Color.green);
		
		JTextArea SaiyanText = new JTextArea();
		SaiyanText.setEditable(false);
		SaiyanText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		SaiyanText.setText("Saiyan \n * Can transform  \n in to a  super \n Sayian \n * High Damage \n * Very Low HP \n * Low Stamina \n * High KI");
		SaiyanText.setBackground(Color.cyan);
		createfighterview.addText(SaiyanText);
		SaiyanText.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JTextArea EarthlingText = new JTextArea();
		EarthlingText.setEditable(false);
		EarthlingText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		EarthlingText.setText("Earthling \n * Is a Human \n * Low Damage \n * Average Health \n * Average Stamina \n * Average KI");
		EarthlingText.setBackground(Color.cyan);
		createfighterview.addText(EarthlingText);
		EarthlingText.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JTextArea FriezeText = new JTextArea();
		FriezeText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		FriezeText.setEditable(false);
		FriezeText.setText("Frieze \n * Evil Race \n * Low Health \n * Average Damage \n  * Average Stamina \n * Average KI ");
		FriezeText.setBackground(Color.cyan);
		createfighterview.addText(FriezeText);
		FriezeText.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JTextArea MajinText = new JTextArea();
		MajinText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		MajinText.setEditable(false);
		MajinText.setText("Majin \n Evil Wizard \n  Spawn \n * Low Damage \n * Massive Health \n * High Stamina \n * low KI");
		MajinText.setBackground(Color.cyan);
		createfighterview.addText(MajinText);
		MajinText.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JTextArea NamekianText = new JTextArea();
		NamekianText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		NamekianText.setEditable(false);
		NamekianText.setText("Namekian \n * Peaceful Race \n * Average Health \n * Has no Blast \n Capabilities \n * Low Damage \n * High Stamina \n * Low KI");
		NamekianText.setBackground(Color.cyan);
		createfighterview.addText(NamekianText);
		NamekianText.setBorder(BorderFactory.createLineBorder(Color.black));
		
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
	
	
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String Race = btn.getActionCommand();
		switch(Race)
		{
		case "Saiyan" :
			if(this.createfighterview.getEnterName().getText().equals(""))
			{
				break;
			}
			else {
				this.game.getPlayer().createFighter('S',this.createfighterview.getEnterName().getText() );
			this.createfighterview.setVisible(false);
			//this.createfighterview = null;
			this.GC.getWorldview().setVisible(true);
			this.GC.updateList();
			break;}
			case "Earthling" :
				if(this.createfighterview.getEnterName().getText().equals(""))
				{
					break;
				}
				else {
					this.game.getPlayer().createFighter('E',this.createfighterview.getEnterName().getText() );
				this.createfighterview.setVisible(false);
				//this.createfighterview = null;
				this.GC.getWorldview().setVisible(true);
				this.GC.updateList();
				break;}
				
			case "Frieza": 
				if(this.createfighterview.getEnterName().getText().equals(""))
				{
					break;
				}
				else {
					this.game.getPlayer().createFighter('F',this.createfighterview.getEnterName().getText() );
				this.createfighterview.setVisible(false);
				//this.createfighterview = null;
				this.GC.getWorldview().setVisible(true);
				this.GC.updateList();
				break;}
			case "Majin":
				if(this.createfighterview.getEnterName().getText().equals(""))
				{
					break;
				}
				else {
					this.game.getPlayer().createFighter('M',this.createfighterview.getEnterName().getText() );
					//this.createfighterview = null;
				this.createfighterview.setVisible(false);
				this.GC.getWorldview().setVisible(true);
				this.GC.updateList();
				
				break;}
			case "Namekian":
				if(this.createfighterview.getEnterName().getText().equals(""))
				{
					break;
				}
				else {
					this.game.getPlayer().createFighter('N',this.createfighterview.getEnterName().getText() );
				this.createfighterview.setVisible(false);
				//this.createfighterview = null;
				this.GC.getWorldview().setVisible(true);
				this.GC.updateList();
				break;}
			
			
		}
		
	}
	
	public static void main(String [] args) throws IOException
	{
		
	}
	public CreateFighterView getCreatefighterview() {
		return createfighterview;
	}
	public void setCreatefighterview(CreateFighterView createfighterview) {
		this.createfighterview = createfighterview;
	}
	

	
}
