package dragonball.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;

import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.view.CreateFighterView;
import dragonball.view.HintTextField;

public class CreateFirstFighterController implements ActionListener {

	private CreateFighterView createfighterview;
	private HintTextField PlayerName;
	private Game game;
	private GameController GC;
	private Boolean AION;
	public CreateFirstFighterController (boolean AION) throws IOException
	{
		this.AION = AION;
		createfighterview = new CreateFighterView ();
		JButton Saiyan = new JButton();
		Saiyan.addActionListener(this);
		createfighterview.addFighter(Saiyan);
		Image Goku = getImage("src/resources/Goku.png");
		Image newGoku = Goku.getScaledInstance( 450,550,  java.awt.Image.SCALE_SMOOTH ) ;
		Saiyan.setIcon(new ImageIcon(newGoku));
		Saiyan.setActionCommand("Saiyan");
		Saiyan.setBackground(Color.cyan);
		
		JButton Earthling = new JButton();
		Earthling.addActionListener(this);
		createfighterview.addFighter(Earthling);
		Image Earth = getImage("src/resources/Earthling.Png");
		Image newEarthling = Earth.getScaledInstance( 400,400,  java.awt.Image.SCALE_SMOOTH ) ;
		Earthling.setIcon(new ImageIcon(newEarthling));
		Earthling.setActionCommand("Earthling");
		Earthling.setBackground(Color.ORANGE);
		
		JButton Frieze = new JButton();
		Frieze.addActionListener(this);
		createfighterview.addFighter(Frieze);
		Image Fri = getImage("src/resources/Frieza2.Png");
		Image newFrieze = Fri.getScaledInstance( 400,500,  java.awt.Image.SCALE_SMOOTH ) ;
		Frieze.setIcon(new ImageIcon(newFrieze));
		Frieze.setActionCommand("Frieza");
		Frieze.setBackground(Color.lightGray);
		
		JButton Majin = new JButton();
		Majin.addActionListener(this);
		createfighterview.addFighter(Majin);
		Image Maj = getImage("src/resources/Majin.Png");
		Image newMajin = Maj.getScaledInstance( 300,650,  java.awt.Image.SCALE_SMOOTH ) ;
		Majin.setIcon(new ImageIcon(newMajin));
		Majin.setActionCommand("Majin");
		Majin.setBackground(Color.yellow);
	
		JButton Namekian = new JButton();
		Namekian.addActionListener(this);
		createfighterview.addFighter(Namekian);
		Image Nam = getImage("src/resources/Namekian.Png");
		Image newNamekian = Nam.getScaledInstance( 450,500,  java.awt.Image.SCALE_SMOOTH ) ;
		Namekian.setIcon(new ImageIcon(newNamekian));
		Namekian.setActionCommand("Namekian");
		Namekian.setBackground(Color.GREEN);
		
		JTextArea SaiyanText = new JTextArea();
		SaiyanText.setEditable(false);
		SaiyanText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		SaiyanText.setText("Saiyan \n * Can transform  \n in to a  super \n Sayian \n * High Damage \n * Very Low HP \n * Low Stamina \n * High KI");
		SaiyanText.setBackground(Color.CYAN);
		SaiyanText.setBorder(BorderFactory.createLineBorder(Color.black));
		createfighterview.addText(SaiyanText);
		
		JTextArea EarthlingText = new JTextArea();
		EarthlingText.setEditable(false);
		EarthlingText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		EarthlingText.setText("Earthling \n * Is a Human \n * Low Damage \n * Average Health \n * Average Stamina \n * Average KI");
		EarthlingText.setBackground(Color.orange);
		EarthlingText.setBorder(BorderFactory.createLineBorder(Color.black));
		createfighterview.addText(EarthlingText);
		
		JTextArea FriezeText = new JTextArea();
		FriezeText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		FriezeText.setEditable(false);
		FriezeText.setText("Frieze \n * Evil Race \n * Low Health \n * Average Damage \n  * Average Stamina \n * Average KI ");
		FriezeText.setBackground(Color.lightGray);
		FriezeText.setBorder(BorderFactory.createLineBorder(Color.black));
		createfighterview.addText(FriezeText);
		
		JTextArea MajinText = new JTextArea();
		MajinText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		MajinText.setEditable(false);
		MajinText.setText("Majin \n Evil Wizard \n  Spawn \n * Low Damage \n * Massive Health \n * High Stamina \n * low KI");
		MajinText.setBackground(Color.yellow);
		MajinText.setBorder(BorderFactory.createLineBorder(Color.black));
		createfighterview.addText(MajinText);
		
		JTextArea NamekianText = new JTextArea();
		NamekianText.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		NamekianText.setEditable(false);
		NamekianText.setText("Namekian \n * Peaceful Race \n * Average Health \n * Has no Blast \n Capabilities \n * Low Damage \n * High Stamina \n * Low KI");
		NamekianText.setBackground(Color.green);
		NamekianText.setBorder(BorderFactory.createLineBorder(Color.black));
		createfighterview.addText(NamekianText);
		
		 PlayerName = new HintTextField("Please Enter your Name here");
		//PlayerName = new JTextArea();
		//PlayerName.setEditable(true);
		//PlayerName.setPreferredSize(new Dimension(createfighterview.getWidth()/2,50));
		//PlayerName.setText("please Enter your  name here");
		//createfighterview.add(PlayerName, BorderLayout.SOUTH);
		createfighterview.getTextGrid().add(PlayerName);
		//PlayerName.setText("hii");
		//System.out.println("player name is" + PlayerName.getText());
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
				try {
					game = new Game(PlayerName.getText(),AION);
				} catch (MissingFieldException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
					e1.printStackTrace();
				} catch (UnknownAttackTypeException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
					e1.printStackTrace();
				}
				
			
			this.game.getPlayer().createFighter('S',this.createfighterview.getEnterName().getText() );
			//System.out.println(this.game.getPlayer().getActiveFighter().getClass().getSimpleName());
		
			
			try {
				this.GC = new GameController(this.game);
			} catch (MissingFieldException e1) {
				JTextArea ops = new JTextArea();
				ops.setText("there is a problem with your csv file, please close and reinstall");
				ops.setVisible(true);
				e1.printStackTrace();
			} catch (UnknownAttackTypeException e1) {
				JTextArea ops = new JTextArea();
				ops.setText("there is a problem with your csv file, please close and reinstall");
				ops.setVisible(true);
				e1.printStackTrace();
			}
			
			this.createfighterview.setVisible(false);
			this.GC.getWorldview().setVisible(true);
			break;}
			case "Earthling" :
				if(this.createfighterview.getEnterName().getText().equals(""))
				{
					break;
				}
				else {
					try {
						game = new Game(PlayerName.getText(),AION);
					} catch (MissingFieldException e1) {
						JTextArea ops = new JTextArea();
						ops.setText("there is a problem with your csv file, please close and reinstall");
						ops.setVisible(true);
						e1.printStackTrace();
						e1.printStackTrace();
					} catch (UnknownAttackTypeException e1) {
						JTextArea ops = new JTextArea();
						ops.setText("there is a problem with your csv file, please close and reinstall");
						ops.setVisible(true);
						e1.printStackTrace();
						e1.printStackTrace();
					}
					
				
				this.game.getPlayer().createFighter('E',this.createfighterview.getEnterName().getText() );
				//System.out.println(this.game.getPlayer().getActiveFighter().getClass().getSimpleName());
			
				
				try {
					this.GC = new GameController(this.game);
				} catch (MissingFieldException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
				} catch (UnknownAttackTypeException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
				}
				
				this.createfighterview.setVisible(false);
				this.GC.getWorldview().setVisible(true);
				break;}
				
			case "Frieza": 
				if(this.createfighterview.getEnterName().getText().equals(""))
				{
					break;
				}
				else {
					try {
						game = new Game(PlayerName.getText(),AION);
					} catch (MissingFieldException e1) {
						JTextArea ops = new JTextArea();
						ops.setText("there is a problem with your csv file, please close and reinstall");
						ops.setVisible(true);
						e1.printStackTrace();
						e1.printStackTrace();
					} catch (UnknownAttackTypeException e1) {
						JTextArea ops = new JTextArea();
						ops.setText("there is a problem with your csv file, please close and reinstall");
						ops.setVisible(true);
						e1.printStackTrace();
						e1.printStackTrace();
					}
					
				
				this.game.getPlayer().createFighter('F',this.createfighterview.getEnterName().getText() );
				//System.out.println(this.game.getPlayer().getActiveFighter().getClass().getSimpleName());
			
				
				try {
					this.GC = new GameController(this.game);
				} catch (MissingFieldException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
				} catch (UnknownAttackTypeException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
				}
				
				this.createfighterview.setVisible(false);
				this.GC.getWorldview().setVisible(true);
				break;}
			case "Majin":
				if(this.createfighterview.getEnterName().getText().equals(""))
				{
					break;
				}
				else {
					try {
						game = new Game(PlayerName.getText(),AION);
					} catch (MissingFieldException e1) {
						JTextArea ops = new JTextArea();
						ops.setText("there is a problem with your csv file, please close and reinstall");
						ops.setVisible(true);
						e1.printStackTrace();
						e1.printStackTrace();
					} catch (UnknownAttackTypeException e1) {
						JTextArea ops = new JTextArea();
						ops.setText("there is a problem with your csv file, please close and reinstall");
						ops.setVisible(true);
						e1.printStackTrace();
						e1.printStackTrace();
					}
					
				
				this.game.getPlayer().createFighter('M',this.createfighterview.getEnterName().getText() );
				//System.out.println(this.game.getPlayer().getActiveFighter().getClass().getSimpleName());
			
				
				try {
					this.GC = new GameController(this.game);
				} catch (MissingFieldException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
				} catch (UnknownAttackTypeException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
				}
				
				this.createfighterview.setVisible(false);
				this.GC.getWorldview().setVisible(true);
				break;}
			case "Namekian":
				if(this.createfighterview.getEnterName().getText().equals(""))
				{
					break;
				}
				else {
					try {
						game = new Game(PlayerName.getText(),AION);
					} catch (MissingFieldException e1) {
						JTextArea ops = new JTextArea();
						ops.setText("there is a problem with your csv file, please close and reinstall");
						ops.setVisible(true);
						e1.printStackTrace();
						e1.printStackTrace();
					} catch (UnknownAttackTypeException e1) {
						JTextArea ops = new JTextArea();
						ops.setText("there is a problem with your csv file, please close and reinstall");
						ops.setVisible(true);
						e1.printStackTrace();
						e1.printStackTrace();
					}
					
				
				this.game.getPlayer().createFighter('N',this.createfighterview.getEnterName().getText() );
				//System.out.println(this.game.getPlayer().getActiveFighter().getClass().getSimpleName());
			
				
				try {
					this.GC = new GameController(this.game);
				} catch (MissingFieldException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
				} catch (UnknownAttackTypeException e1) {
					JTextArea ops = new JTextArea();
					ops.setText("there is a problem with your csv file, please close and reinstall");
					ops.setVisible(true);
					e1.printStackTrace();
				}
				
				this.createfighterview.setVisible(false);
				this.GC.getWorldview().setVisible(true);
				break;}
			
			
		}
		
	}
	
	
	public CreateFighterView getCreatefighterview() {
		return createfighterview;
	}
	public void setCreatefighterview(CreateFighterView createfighterview) {
		this.createfighterview = createfighterview;
	}
	public static void main (String [] args) throws IOException
	{
		//CreateFirstFighterController CFFC = new CreateFirstFighterController();
		
	}
}
