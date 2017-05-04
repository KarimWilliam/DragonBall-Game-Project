package dragonball.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import dragonball.controller.BattleController;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.BattleEvent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;

public class BattleView extends GameView implements ActionListener {
	
	private BattleController BC;
	private JProgressBar playerHealth;
	private JProgressBar playerStamina;
	private JProgressBar playerKI;
	private JTextArea playerName;
	private JTextArea OpponentName;
	private BattleEvent BE;
	private JProgressBar OpponentHealth;
	private JProgressBar OpponentStamina;
	private JProgressBar OpponentKI;
	private JTextPane events;
	private JPanel Skills;
	private JButton PhysicalAttack;
	private JButton Block;
	private JButton Use;
	private JButton SuperAttack;
	private JButton UltimateAttack;
	private JPanel Super;
	private JPanel Ultimate;
	private JLayeredPane main;
	private JLabel PlayerPic;
	private int wasLevel;
	private int wasXP;
	private ArrayList<SuperAttack> SAList;
	private ArrayList<UltimateAttack> UAList;
	private Timer timer;
	private JLayeredPane Defeat;
	private JFrame mainDefeat;
	private Image newFighter;
	private Boolean isRunning;
	private ImageStorage IS;
	public BattleView(BattleController BC, BattleEvent BE, ImageStorage IS)
	{
		this.IS = IS;
		this.SAList = new ArrayList<SuperAttack>();
		this.UAList = new ArrayList<UltimateAttack>();
		
		
		wasXP =  BC.getGC().getGame().getPlayer().getActiveFighter().getXp();
		wasLevel = BC.getGC().getGame().getPlayer().getActiveFighter().getLevel();
		this.BE = BE;
		this.BC = BC;
		setTitle("Battle");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1000);
		this.setVisible(true);
		//making the main layout
		 main = new JLayeredPane();
		add(main);
		
		for (int i=0;i<BC.getGC().getGame().getPlayer().getSuperAttacks().size();i++)
		{
			SAList.add(BC.getGC().getGame().getPlayer().getSuperAttacks().get(i));
			
		}
		
		for (int i=0;i<BC.getGC().getGame().getPlayer().getUltimateAttacks().size();i++)
		{
			UAList.add(BC.getGC().getGame().getPlayer().getUltimateAttacks().get(i));
			
		}
		
		//BC.getGC().getGame().getPlayer().getActiveFighter().setPhysicalDamage(10000);
		//making Super thing
		Super = new JPanel();
		Super.setBounds(20, 650, 400, 300);
        Super.setLayout(new GridLayout(0,1));
        Super.setBorder( BorderFactory.createLineBorder(Color.black,4));
        main.add(Super, new Integer(4));
        Super.setVisible(false);
        
        //Making Ultimate Thing
    	Ultimate = new JPanel();
		Ultimate.setBounds(20, 650, 400, 300);
        Ultimate.setLayout(new GridLayout(0,1));
        Ultimate.setBorder( BorderFactory.createLineBorder(Color.black,4));
        main.add(Ultimate, new Integer(4));
        Ultimate.setVisible(false);
		
		//forest thing
		JLabel forestCont = new JLabel();
		//Image forest = getImage("src/resources/forest.Png");
		Image newforest = IS.getForest().getScaledInstance( 1920,1080,  java.awt.Image.SCALE_SMOOTH ) ;
		forestCont.setIcon(new ImageIcon(newforest));
		forestCont.setBounds(0, 0, 1920, 1080);
		main.add(forestCont, 0);
		
		//Player PIC
		Image s = this.PicClass();
		 PlayerPic = new JLabel();
		//Image Fi = getImage(s);
		PlayerPic.setBounds(300, 300, 400, 600);
		 newFighter = s.getScaledInstance( 400,600,  java.awt.Image.SCALE_SMOOTH ) ;
		PlayerPic.setIcon(new ImageIcon(newFighter));
		main.add(PlayerPic, new Integer(2));
		
		//ghost thing	
				JLabel ghost = new JLabel();
				//Image kkk = getImage("src/resources/Cloak.Png");
				ghost.setBounds(1350, 300, 400, 600);
				Image newkkk = IS.getGhost().getScaledInstance( 400,600,  java.awt.Image.SCALE_SMOOTH ) ;
				ghost.setIcon(new ImageIcon(newkkk));
				main.add(ghost, new Integer(2));

		//Player Info
		JPanel playerInfo = new JPanel();
		playerInfo.setBounds(50, 20, 500,200 );
		playerInfo.setLayout(new BoxLayout(playerInfo, BoxLayout.PAGE_AXIS));
		main.add(playerInfo, new Integer(1));
		 playerName = new JTextArea();
		playerName.setEditable(false);
		 playerName.setBorder(BorderFactory.createLineBorder(Color.black,4));
		playerName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		playerName.setText("  " +(this.BC.getGC().getGame().getPlayer().getActiveFighter().getName()) +
				" lvl " + this.BC.getGC().getGame().getPlayer().getActiveFighter().getLevel());
		
		playerInfo.setLayout(new GridLayout(4,1));
		playerInfo.setBorder( BorderFactory.createLineBorder(Color.black));
		playerInfo.add(playerName);
		playerInfo.setOpaque(true);
		playerInfo.setBackground(Color.black);
		playerInfo.setForeground(Color.BLACK);
	
		
		//player Health bar
		 playerHealth = new JProgressBar(0,BC.getGC().getGame().getPlayer().getActiveFighter().getMaxHealthPoints());
		 playerHealth.setValue(BC.getGC().getGame().getPlayer().getActiveFighter().getMaxHealthPoints());
		 playerHealth.setStringPainted(true );
		 playerHealth.setString("HP             " + (BC.getGC().getGame().getPlayer().getActiveFighter().getMaxHealthPoints()
				 / BC.getGC().getGame().getPlayer().getActiveFighter().getHealthPoints() *100) + " %"  );
		 playerHealth.setForeground(Color.RED);
		 playerHealth.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		 playerHealth.setMaximumSize(new Dimension(300,45));
		 playerHealth.setAlignmentX(playerHealth.CENTER_ALIGNMENT);
		 playerHealth.setBorder(BorderFactory.createLineBorder(Color.black,4));
		 playerInfo.add(playerHealth);
		
		// player Stamina Bar
		 playerStamina = new JProgressBar(0,BC.getGC().getGame().getPlayer().getActiveFighter().getMaxStamina());
		 playerInfo.add(playerStamina);
		 playerStamina.setValue(BC.getGC().getGame().getPlayer().getActiveFighter().getMaxStamina());
		 playerStamina.setStringPainted(true );
		
		 playerStamina.setString("Stamina         " + (BC.getGC().getGame().getPlayer().getActiveFighter().getStamina()
				+ "/" +  BC.getGC().getGame().getPlayer().getActiveFighter().getMaxStamina() )  );
		 playerStamina.setForeground(Color.ORANGE);
		 playerStamina.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		 playerStamina.setAlignmentX(playerStamina.CENTER_ALIGNMENT);
		 playerStamina.setBorder(BorderFactory.createLineBorder(Color.black,4));
		 
		 //player KI Bar
		 playerKI = new JProgressBar(0,BC.getGC().getGame().getPlayer().getActiveFighter().getMaxKi());
		 playerInfo.add(playerKI);
		 playerKI.setValue(BC.getGC().getGame().getPlayer().getActiveFighter().getKi());
		 playerKI.setStringPainted(true );
		
		 playerKI.setString("KI             " + ( BC.getGC().getGame().getPlayer().getActiveFighter().getKi())
				+ "/" +  BC.getGC().getGame().getPlayer().getActiveFighter().getMaxKi()   );
		 playerKI.setForeground(Color.blue);
		 playerKI.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		 playerKI.setAlignmentX(playerKI.CENTER_ALIGNMENT);
		 playerKI.setBorder(BorderFactory.createLineBorder(Color.black,4));
		
		
		 //Opponent Info
		 JPanel OpponentInfo = new JPanel();
		 OpponentInfo.setBounds(1370, 20, 500,200 );
		 OpponentInfo.setLayout(new BoxLayout(OpponentInfo, BoxLayout.PAGE_AXIS));
			main.add(OpponentInfo, new Integer(1));
			OpponentName = new JTextArea();
			OpponentName.setEditable(false);
			OpponentName.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			OpponentName.setText("  " +((Fighter) this.BE.getBattle().getFoe()).getName() +
					" lvl " + ((Fighter)this.BE.getBattle().getFoe()).getLevel());
			OpponentName.setBorder(BorderFactory.createLineBorder(Color.black,4));
			
			OpponentInfo.setLayout(new GridLayout(4,1));
			OpponentInfo.setBorder( BorderFactory.createLineBorder(Color.black));
			OpponentInfo.add(OpponentName);
			OpponentInfo.setOpaque(true);
			OpponentInfo.setBackground(Color.black);
			OpponentInfo.setForeground(Color.BLACK);
			
			
			//Opponent Health bar
			 OpponentHealth = new JProgressBar(0,((Fighter) this.BE.getBattle().getFoe()).getMaxHealthPoints());
			 OpponentHealth.setValue(((Fighter) this.BE.getBattle().getFoe()).getMaxHealthPoints());
			 OpponentHealth.setStringPainted(true );
			 OpponentHealth.setString("HP             " + (((Fighter) this.BE.getBattle().getFoe()).getHealthPoints()
					 / ((Fighter) this.BE.getBattle().getFoe()).getMaxHealthPoints() *100) + " %"  );
			 OpponentHealth.setForeground(Color.RED);
			 OpponentHealth.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			 OpponentHealth.setMaximumSize(new Dimension(300,45));
			 OpponentHealth.setAlignmentX(OpponentHealth.CENTER_ALIGNMENT);
			 OpponentHealth.setBorder(BorderFactory.createLineBorder(Color.black,4));
			 OpponentInfo.add(OpponentHealth);
			 
			 
			// Opponent Stamina Bar
			 OpponentStamina = new JProgressBar(0,((Fighter) this.BE.getBattle().getFoe()).getMaxStamina());
			 OpponentInfo.add(OpponentStamina);
			 OpponentStamina.setValue(((Fighter) this.BE.getBattle().getFoe()).getMaxStamina());
			 OpponentStamina.setStringPainted(true );
			
			 OpponentStamina.setString("Stamina         " + (((Fighter) this.BE.getBattle().getFoe()).getStamina()
					+ "/" +  ((Fighter) this.BE.getBattle().getFoe()).getMaxStamina() )  );
			 OpponentStamina.setForeground(Color.ORANGE);
			 OpponentStamina.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			 OpponentStamina.setAlignmentX(playerStamina.CENTER_ALIGNMENT);
			 OpponentStamina.setBorder(BorderFactory.createLineBorder(Color.black,4));
			 
			 //Opponent KI Bar
			 OpponentKI = new JProgressBar(0,((Fighter) this.BE.getBattle().getFoe()).getMaxKi());
			 OpponentInfo.add(OpponentKI);
			 OpponentKI.setValue(((Fighter) this.BE.getBattle().getFoe()).getKi());
			 OpponentKI.setStringPainted(true );
			
			 OpponentKI.setString("KI             " + ( ((Fighter) this.BE.getBattle().getFoe()).getKi())
					+ "/" +  ((Fighter) this.BE.getBattle().getFoe()).getMaxKi()   );
			 OpponentKI.setForeground(Color.blue);
			 OpponentKI.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
			 OpponentKI.setAlignmentX(playerKI.CENTER_ALIGNMENT);
			 OpponentKI.setBorder(BorderFactory.createLineBorder(Color.black,4));
		 
			 
			 // TIMER initilization
			 timer = new Timer(0, this);
			 timer.setInitialDelay(0);
			
			 timer.setActionCommand("HealthChange");
		 // VS Sign
		 JLabel vs = new JLabel();
		 vs.setBounds(860, 40, 200,200 );
		 main.add(vs, new Integer(1));
		Image vss = getImage("src/resources/VS1.Png");
		Image newvs = vss.getScaledInstance( 100,100,  java.awt.Image.SCALE_SMOOTH ) ;
		vs.setIcon(new ImageIcon(newvs));
		
		
		// events thing 
		events = new JTextPane();
		events.setEditable(false);
		events.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		
		
		JScrollPane scroll = new JScrollPane (events, 
				   JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			main.add(scroll, new Integer(3));
			scroll.setBounds(1520,750 , 400, 200);
			scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_NEVER);
			scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_NEVER);
			scroll.setBorder( BorderFactory.createLineBorder(Color.black,4));
		
			StyledDocument doc = events.getStyledDocument();

			 Style style = events.addStyle("I'm a Style", null);
		        StyleConstants.setForeground(style, Color.green);

		        try { doc.insertString(doc.getLength(), " -Battle Has Started \n",style); }
		        catch (BadLocationException e){}

		
		        //skill bar
		        Skills = new JPanel();
		        Skills.setBounds(20, 650, 400, 300);
		        Skills.setLayout(new GridLayout(0,1));
		        Skills.setBorder( BorderFactory.createLineBorder(Color.black,4));
		        main.add(Skills, new Integer(4));
		        
		        PhysicalAttack = new JButton();
		        PhysicalAttack.setText("Attack");
		        PhysicalAttack.addActionListener(this.BC);
		        Skills.add(PhysicalAttack);
		        
		        Block = new JButton();
		        Block.setText("Block");
		        Block.addActionListener(this.BC);
		        Skills.add(Block);
		        
		        Use = new JButton();
		        Use.setText("Use Senzu Bean");
		        Use.addActionListener(this.BC);
		        Skills.add(Use);
		        
		        SuperAttack = new JButton();
		        SuperAttack.setText("Pick a Super Attack");
		        SuperAttack.addActionListener(this);
		        Skills.add(SuperAttack);
		        
		        UltimateAttack = new JButton();
		        UltimateAttack.setText("Pick an Ultimate Attack");
		        UltimateAttack.addActionListener(this);
		        Skills.add(UltimateAttack);
		        }
	
	public void Defeated()
	{
		
		 mainDefeat = new JFrame();
		mainDefeat.setVisible(true);
		mainDefeat.setTitle("Defeated");
		mainDefeat.setBounds(750, 300, 400, 200);
		mainDefeat.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(false);
		 Defeat = new JLayeredPane();
		mainDefeat.add(Defeat);
		
		Defeat.setBounds(0, 0, 400, 200);
		
		JButton Defeated = new JButton();
		Defeated.setBackground(Color.red);
		Defeated.setText("Try Again");
		Defeated.addActionListener(this.BC.getGC());
		Defeated.setBounds(0, 100, 100, 60);
		Defeated.setBorder( BorderFactory.createLineBorder(Color.black,4));
		Defeat.add(Defeated, new Integer(1));
		
		JLabel DefLabel = new JLabel();
		Image Def = getImage("src/resources/Defeat.Png");
		Image newDef = Def.getScaledInstance( 400,100,  java.awt.Image.SCALE_SMOOTH ) ;
		DefLabel.setIcon(new ImageIcon(newDef));
		DefLabel.setBounds(0, 0, 400, 100);
		Defeat.add(DefLabel, new Integer(2));
		
		JTextArea D = new JTextArea();
		D.setText(" Back to Square one with you");
		D.setEditable(false);
		D.setBorder( BorderFactory.createLineBorder(Color.black,4));
		D.setBackground(Color.RED);
		D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		D.setBounds(100, 100, 290, 60);
		Defeat.add(D, new Integer(1));
		
	}
	
	
	public void Refresh(String WH)
	{
	
		 //playerHealth.setValue(BC.getGC().getGame().getPlayer().getActiveFighter().getHealthPoints());
		// playerHealth.setString("HP             " + (BC.getGC().getGame().getPlayer().getActiveFighter().getHealthPoints() *100)
		//		 / BC.getGC().getGame().getPlayer().getActiveFighter().getMaxHealthPoints() + " %"  );
		 playerStamina.setValue(BC.getGC().getGame().getPlayer().getActiveFighter().getStamina());
		 playerStamina.setString("Stamina         " + (BC.getGC().getGame().getPlayer().getActiveFighter().getMaxStamina() )
					+ "/" +  BC.getGC().getGame().getPlayer().getActiveFighter().getStamina()  );
		 playerKI.setValue(BC.getGC().getGame().getPlayer().getActiveFighter().getKi());
		 playerKI.setString("KI             " + ( BC.getGC().getGame().getPlayer().getActiveFighter().getKi())
					+ "/" +  BC.getGC().getGame().getPlayer().getActiveFighter().getMaxKi()   );
		// OpponentHealth.setValue(((Fighter) this.BE.getBattle().getFoe()).getHealthPoints());
		// OpponentHealth.setString("HP             " + (((Fighter) this.BE.getBattle().getFoe()).getHealthPoints() *100
		//		 / ((Fighter) this.BE.getBattle().getFoe()).getMaxHealthPoints())  + " %"  );
		 OpponentStamina.setValue(((Fighter) this.BE.getBattle().getFoe()).getStamina());
		 OpponentStamina.setString("Stamina         " + (((Fighter) this.BE.getBattle().getFoe()).getMaxStamina()
					+ "/" +  ((Fighter) this.BE.getBattle().getFoe()).getStamina() )  );
		 OpponentKI.setValue(((Fighter) this.BE.getBattle().getFoe()).getKi());
		 OpponentKI.setString("KI             " + ( ((Fighter) this.BE.getBattle().getFoe()).getKi())
					+ "/" +  ((Fighter) this.BE.getBattle().getFoe()).getMaxKi()   );
		 
		 timer.start(); 
		
		if(this.BC.getGC().getGame().getPlayer().getActiveFighter() instanceof Saiyan)
		{

			
			if (((Saiyan) this.BC.getGC().getGame().getPlayer().getActiveFighter()).isTransformed() == true)
			{
				playerName.setText("  " +(this.BC.getGC().getGame().getPlayer().getActiveFighter().getName()) +
						" lvl " + this.BC.getGC().getGame().getPlayer().getActiveFighter().getLevel()
						+ "  (Transformed) ");
				
				
				
				JLabel Aura = new JLabel();
				Image AuraP = getImage("src/resources/Aura.Png");
				Aura.setBounds(300, 300, 400, 600);
				Image newAura = AuraP.getScaledInstance( 400,600,  java.awt.Image.SCALE_SMOOTH ) ;
				PlayerPic.setIcon(new ImageIcon(newAura));
				//main.add(Aura, new Integer(2));	
				
				Image Goku = getImage("src/resources/Gokus.Png");
				Image Gokus = Goku.getScaledInstance( 400,600,  java.awt.Image.SCALE_SMOOTH ) ;
				PlayerPic.setIcon(new ImageIcon(Gokus));
				
			}
			else {
				playerName.setText("  " +(this.BC.getGC().getGame().getPlayer().getActiveFighter().getName()) +
						" lvl " + this.BC.getGC().getGame().getPlayer().getActiveFighter().getLevel());
				
				PlayerPic.setIcon(new ImageIcon (newFighter));
			}
		}
		
		
		if(this.BE.getBattle().getFoe() instanceof Saiyan)
		{
			if (((Saiyan) this.BE.getBattle().getFoe()).isTransformed() == true)
			{
				OpponentName.setText("  " +((Fighter) this.BE.getBattle().getFoe()).getName() +
						" lvl " + ((Fighter)this.BE.getBattle().getFoe()).getLevel() + "  (Transformed) ");
			}
			else {
				OpponentName.setText("  " +((Fighter) this.BE.getBattle().getFoe()).getName() +
						" lvl " + ((Fighter)this.BE.getBattle().getFoe()).getLevel());
			}
			
		}
	}
	
	public Image PicClass ()
	{
		
		String s = this.BC.getGC().getGame().getPlayer().getActiveFighter().getClass().getSimpleName();
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

	@Override
	public void actionPerformed(ActionEvent e) {


		if( ! e.getActionCommand().equals("HealthChange"))
		{
		JButton btn = (JButton) e.getSource();
		
		if(btn.getText().equals("Back"))
		{
			Ultimate.setVisible(false);
			Super.setVisible(false);
			Skills.setVisible(true);
		}
		
		if(btn.getText().equals("Pick a Super Attack"))
		{
			Super.removeAll();
			//this.BC.getGC().getGame().getPlayer().getActiveFighter().getSuperAttacks()
			//.add((dragonball.model.attack.SuperAttack) this.BC.getGC().getGame().getAttacks().get(1));
			//this.BC.getGC().getGame().getPlayer().getActiveFighter().
			//getSuperAttacks().add((dragonball.model.attack.SuperAttack) this.BC.getGC().getGame().getAttacks().get(0));
			for(int i = 0;i<this.BC.getGC().getGame().getPlayer().getActiveFighter().getSuperAttacks().size();i++)
			{
				
				JButton SA = new JButton();
				SA.setText(this.BC.getGC().getGame().getPlayer().getActiveFighter().getSuperAttacks().get(i).getName());
				SA.addActionListener(this.BC);
				SA.setActionCommand(Integer.toString(i));
				SA.setBackground(Color.green);
				Super.add(SA);
				
				
			}
			JButton BackS = new JButton();
			BackS.setText("Back");
			BackS.addActionListener(this);
			Super.add(BackS);
			Skills.setVisible(false);
			Super.setVisible(true);
		}
		else if(btn.getText().equals("Pick an Ultimate Attack"))
		{
			

			Ultimate.removeAll();
			
			for(int i = 0;i<this.BC.getGC().getGame().getPlayer().getActiveFighter().getUltimateAttacks().size();i++)
			{
				
				JButton UA = new JButton();
				UA.setText(this.BC.getGC().getGame().getPlayer().getActiveFighter().getUltimateAttacks().get(i).getName());
				UA.addActionListener(this.BC);
				UA.setActionCommand(Integer.toString(i +10));
				UA.setBackground(Color.red);
				Ultimate.add(UA);
				
				
			}
			JButton BackS = new JButton();
			BackS.setText("Back");
			BackS.addActionListener(this);
			Ultimate.add(BackS);
			Skills.setVisible(false);
			Ultimate.setVisible(true);
		}
		}
		
		if(e.getActionCommand().equals("HealthChange"))
		{
			
			if(OpponentHealth.getValue()>((Fighter) this.BE.getBattle().getFoe()).getHealthPoints())
			{
				OpponentHealth.setValue(OpponentHealth.getValue()-1);
				OpponentHealth.setString("HP             " + (OpponentHealth.getValue() *100)
						 / ((Fighter) this.BE.getBattle().getFoe()).getMaxHealthPoints() + " %"  );
				isRunning = true;
			}
			
			if(OpponentHealth.getValue()<((Fighter) this.BE.getBattle().getFoe()).getHealthPoints())
			{
				OpponentHealth.setValue(OpponentHealth.getValue()+1);
				OpponentHealth.setString("HP             " + (OpponentHealth.getValue() *100)
						 / ((Fighter) this.BE.getBattle().getFoe()).getMaxHealthPoints() + " %" );
				isRunning = true;
			}
			// health bar gui 
			
			
			if(OpponentHealth.getValue()==((Fighter) this.BE.getBattle().getFoe()).getHealthPoints())
				isRunning = false;
			
			if(isRunning == true)
			{
				timer.restart();
			}
			else {
				
				if(playerHealth.getValue()>BC.getGC().getGame().getPlayer().getActiveFighter().getHealthPoints())
				{
					playerHealth.setValue(playerHealth.getValue()-1);
					playerHealth.setString("HP             " + (playerHealth.getValue() *100)
							 / BC.getGC().getGame().getPlayer().getActiveFighter().getMaxHealthPoints() + " %"  );
					
					
					
				}
				
				if(playerHealth.getValue()<BC.getGC().getGame().getPlayer().getActiveFighter().getHealthPoints())
				{
					playerHealth.setValue(playerHealth.getValue()+1);
					playerHealth.setString("HP             " + (playerHealth.getValue() *100)
							 / BC.getGC().getGame().getPlayer().getActiveFighter().getMaxHealthPoints() + " %" );
					
					
							 
				}
			
			
			
			if(OpponentHealth.getValue() !=((Fighter) this.BE.getBattle().getFoe()).getHealthPoints() ||
					playerHealth.getValue()!=BC.getGC().getGame().getPlayer().getActiveFighter().getHealthPoints())
			{
				timer.restart();
			}}
			  
		}
		
		if(e.getActionCommand().equals("OpponentDamper"))
		{
			//do nothing
		}
	}


	public JTextPane getEvents() {
		return events;
	}


	


	public JPanel getSuper() {
		return Super;
	}


	public void setSuper(JPanel super1) {
		Super = super1;
	}


	public JPanel getUltimate() {
		return Ultimate;
	}


	public void setUltimate(JPanel ultimate) {
		Ultimate = ultimate;
	}


	public JPanel getSkills() {
		return Skills;
	}


	public int getWasLevel() {
		return wasLevel;
	}


	public int getWasXP() {
		return wasXP;
	}


	public void setWasXP(int wasXP) {
		this.wasXP = wasXP;
	}


	public ArrayList<dragonball.model.attack.SuperAttack> getSAList() {
		return SAList;
	}


	public ArrayList<dragonball.model.attack.UltimateAttack> getUAList() {
		return UAList;
	}

	public JLayeredPane getDefeat() {
		return Defeat;
	}

	public JFrame getMainDefeat() {
		return mainDefeat;
	}
	
	
	
	
	
	
	
	

	
}
