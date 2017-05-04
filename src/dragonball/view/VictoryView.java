package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.Timer;

import dragonball.controller.GameController;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.NonPlayableFighter;

public class VictoryView extends GameView implements ActionListener {
	
	private GameController GC;
	private JProgressBar XpBar;
	private int totalGain = 0;
	private int Target = 0;
	private int CurXp = 0;
	private int prevLevel;
	private Timer timer;
	private int GainedXp;
	private int levelC;
	private int wasXP;
	private int r=0;
	private ImageStorage IS;
	private ArrayList<SuperAttack> SA;
	private ArrayList<UltimateAttack> UA;
	public VictoryView(GameController GC, int prevLevel,int wasXP, boolean isBoss, NonPlayableFighter Loser,
			 ArrayList<SuperAttack> SA, ArrayList<UltimateAttack> UA, ImageStorage IS)
	{
		this.IS = IS;
		this.GC=GC;
		this.wasXP = wasXP;
		this.SA = SA;
		this.UA = UA;
		System.out.println(SA.size());
		CurXp = GC.getGame().getPlayer().getActiveFighter().getXp();
		Target = GC.getGame().getPlayer().getActiveFighter().getTargetXp();
		if (Target ==-10) Target = 10;
		totalGain = Loser.getLevel()*5;
		int curLevel = GC.getGame().getPlayer().getActiveFighter().getLevel();
		//System.out.println(" CurXp: "+ CurXp + " Target :" + Target +" TotalGain " + totalGain  );
		this.prevLevel = prevLevel;
		levelC = curLevel - prevLevel;
		this.GC = GC;
		setTitle("Victory");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(500, 100, 800, 900);
		this.setVisible(true);
		JLayeredPane main = new JLayeredPane();
		add(main);
		
		//EXP Bar Numbers
		 GainedXp =  Loser.getLevel()*5;
	
		
		if(GainedXp > CurXp) 
			{
			GainedXp = GainedXp - CurXp;
			Target -= 20;
			}
		// System.out.println( wasXP);
		while (GainedXp >= Target)
				{
				GainedXp = GainedXp- Target;
				if(Target!=10){
				Target -= 20;
				}
				}
	//	System.out.println("Target" + Target);
			
			//System.out.println( "Gained Xp: " + GainedXp);
			
			//System.out.println("final Target: " + Target);
		System.out.println(GainedXp +"<-- Gained Xp");
		
		//player EXP Bar
		XpBar = new JProgressBar(0,GC.getGame().getPlayer().getActiveFighter().getTargetXp()-(20* levelC));
		XpBar.setValue(wasXP);
		XpBar.setStringPainted(true );
		XpBar.setString("EXP " + wasXP + " / " + Target);
		XpBar.setForeground(Color.blue);
		XpBar.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		XpBar.setBorder(BorderFactory.createLineBorder(Color.black,4));
		XpBar.setBounds(20,750 , 600, 40);
		 main.add(XpBar);
		 
		 timer = new Timer(300, this);
		 timer.setInitialDelay(300);
		 timer.start(); 
		
		
		 //TEXT
		 JTextArea events = new JTextArea();
			events.setEditable(false);
			events.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			events.setBorder( BorderFactory.createLineBorder(Color.black,4));
			events.setBounds(400,150 , 380, 550);
			events.setWrapStyleWord(true);
			events.setLineWrap(true);
			main.add(events);
			
			if(isBoss == true)
			{
				events.setText("You Defeated a Boss! You will be transported to a new Map now \n \n  ");
			}
			if(prevLevel != curLevel)
			{
				events.setText(events.getText() + "- You Leveled Up from level " + prevLevel+ " to level " + curLevel + " ! \n \n");
				events.setText(events.getText() + "- You Gained " + ((curLevel-prevLevel)*2) + " Ability Points \n \n");
			}
			
			// Skills part of TEXT
			int s = 0; int u =0;

			for (int i =0;i<Loser.getSuperAttacks().size();i++)
			{
				for(int j =0; j<SA.size();j++ )
				{
					if(SA.get(j).getName().equals(Loser.getSuperAttacks().get(i).getName()))
						s++;
				}
			}
			System.out.println(s + " " + Loser.getSuperAttacks().size());
			if( s != Loser.getSuperAttacks().size()) 
				r++;
			
			
			for (int i =0;i<Loser.getUltimateAttacks().size();i++)
			{
				for(int j =0; j<UA.size();j++ )
				{
					if(UA.get(j).getName().equals(Loser.getUltimateAttacks().get(i).getName()))
						u++;
				}
			}
			if( s != Loser.getSuperAttacks().size()) 
				r++;
			
			if( u != Loser.getUltimateAttacks().size()) 
				r++;
			
			
			if(r>0)
			{
			events.setText(events.getText() + "- you gained these skill(s): \n \n ");
			
			for (int i =0;i<Loser.getSuperAttacks().size();i++)
			{
				events.setText(events.getText() + "- " + Loser.getSuperAttacks().get(i).getName() + "\n ");
			}
			for (int i =0;i<Loser.getUltimateAttacks().size();i++)
			{
				events.setText(events.getText() + "- "+ Loser.getUltimateAttacks().get(i).getName() + "\n");
			}
			}
			
			//Making Button
			
			JButton Continue = new JButton();
			Continue.setText("Continue");
			Continue.addActionListener(this.GC);
			Continue.setFont(new Font(Font.MONOSPACED, Font.BOLD, 10));
			Continue.setBorder( BorderFactory.createLineBorder(Color.black,4));
			Continue.setBounds(620,750 , 140, 40);
			main.add(Continue);
			
			
			// Player Pic
			//String po = this.PicClass();
			JLabel  PlayerPic = new JLabel();
			Image Fi = this.PicClass();
			PlayerPic.setBounds(0,150 , 380, 550);
			Image newFighter = Fi.getScaledInstance( 380,550,  java.awt.Image.SCALE_SMOOTH ) ;
			PlayerPic.setIcon(new ImageIcon(newFighter));
			main.add(PlayerPic);
			
			// Victory sign
			
			JLabel Vic = new JLabel();
			//Image Victory = getImage("src/resources/Victory.Png");
			Vic.setBounds(10,0 , 750, 130);
			Image newVictory = IS.getVictory().getScaledInstance( 750,130,  java.awt.Image.SCALE_SMOOTH ) ;
			Vic.setIcon(new ImageIcon(newVictory));
			main.add(Vic);
		
	}
	public static void main(String [] args)
	{
	
			
	}
	@Override
	public void actionPerformed(ActionEvent e) {


		
		if(totalGain ==0) return;
		XpBar.setValue(XpBar.getValue()+1);
		totalGain--;
		wasXP++;
		XpBar.setString("EXP " + wasXP + " / " + Target);
		if(wasXP == Target )
		{
			
			Target += 20;
			wasXP =0;
			XpBar.setMaximum(Target);
			XpBar.setMinimum(0);
			XpBar.setValue(0);
			XpBar.setString("EXP " + 0 + " / " + Target);
			if(Target>100)
			{
			timer.setInitialDelay(Target/10);
			}
			if(Target>12)
			{
			timer.setInitialDelay(200);
			}
			if(Target>32)
			{
			timer.setInitialDelay(100);
			}
			if(Target>52)
			{
			timer.setInitialDelay(50);
			}
		}
		if(  totalGain >0)
		{
		  timer.restart();
		}
		
	}
	
	
	public Image PicClass ()
	{
		
		String s = this.GC.getGame().getPlayer().getActiveFighter().getClass().getSimpleName();
		switch(s)
		{
		case  "Saiyan" :
			return  IS.getGokuf();
		case "Earthling" :
			return IS.getEarthling();
		case "Frieza" :
			return IS.getFrieza();
		case "Majin" :
			return IS.getMajinf();
		case "Namekian" :
			return  IS.getNamekianf();
			
		}
		return IS.getGokuf();
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

}
