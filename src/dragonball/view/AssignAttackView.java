package dragonball.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import dragonball.controller.AssignAttackController;
import dragonball.controller.FighterSuperListListener;
import dragonball.controller.FighterUltimateListListener;
import dragonball.controller.PlayerSuperListListener;
import dragonball.controller.PlayerUltimateListListener;
import dragonball.model.attack.SuperAttack;

public class AssignAttackView extends GameView {
	
	private AssignAttackController AAC;
	private DefaultListModel<String> FighterSuperList;
	private DefaultListModel<String> FighterUltimateList;
	private DefaultListModel<String> PlayerSuperList;
	private DefaultListModel<String> PlayerUltimateList;
	private JTextArea exceptions;
	private ImageStorage IS;
	
	public AssignAttackView(AssignAttackController AAC,ImageStorage IS)
	{
		this.setVisible(true);
		
		this.AAC = AAC;
		this.IS =IS;
	setTitle("Assigning Attack to current Fighter");
	setDefaultCloseOperation(EXIT_ON_CLOSE);
	setBounds(0, 0, 1900, 1000);
	
	// make main panel
	JPanel mainPanel = new JPanel();
	mainPanel.setLayout(new GridLayout(1, 3));
	this.setLayout(new GridLayout(1,0));
	
	// make first Column
	JPanel firstCol = new JPanel();
	firstCol.setLayout(new GridLayout(2,1));
	add(firstCol);
	JPanel upFirstCol = new JPanel();
	//upFirstCol.setLayout(new GridLayout(2,1));
	JPanel downFirstCol = new JPanel();
	//downFirstCol.setLayout(new GridLayout(2,1));
	firstCol.add(upFirstCol);
	firstCol.add(downFirstCol);
	JTextArea superPlayer = new JTextArea();
	superPlayer.setFont(new Font("Arial",Font.BOLD,20));
	superPlayer.setText(" assign a Super Attack ");
	superPlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	superPlayer.setWrapStyleWord(true);
	superPlayer.setEditable(false);
	JTextArea ultimatePlayer = new JTextArea();
	
	ultimatePlayer.setFont(new Font("Arial",Font.BOLD,20));
	ultimatePlayer.setText(" assign an Ultimate Attack ");
	ultimatePlayer.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	ultimatePlayer.setWrapStyleWord(true);
	ultimatePlayer.setLineWrap(true);
	ultimatePlayer.setEditable(false);
	superPlayer.setPreferredSize(new Dimension(250,50));
	ultimatePlayer.setPreferredSize(new Dimension(250,50));
	upFirstCol.add(superPlayer, BorderLayout.NORTH);
	downFirstCol.add(ultimatePlayer, BorderLayout.NORTH);
	
	//making first Column Lists
	PlayerSuperList = new DefaultListModel();
	PlayerUltimateList = new DefaultListModel();
	JList PlayerSuper = new JList(PlayerSuperList);
	JList PlayerUltimate = new JList(PlayerUltimateList);
	PlayerSuper.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	PlayerUltimate.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
	ListSelectionModel LSMPS = PlayerSuper.getSelectionModel();
	ListSelectionModel LSMPU = PlayerUltimate.getSelectionModel();
	PlayerSuper.setFont(new Font("Arial",Font.BOLD,20));
	PlayerUltimate.setFont(new Font("Arial",Font.BOLD,20));
	JScrollPane scrollPlayerSuper = new JScrollPane(PlayerSuper);
	JScrollPane scrollPlayerUltimate = new JScrollPane(PlayerUltimate);
	upFirstCol.add(scrollPlayerSuper, BorderLayout.SOUTH);
	downFirstCol.add(scrollPlayerUltimate, BorderLayout.SOUTH);
	
	//Assign Listeners to first col lists
	PlayerSuperListListener PSLL = new PlayerSuperListListener(this.AAC);
	PlayerUltimateListListener PULL = new PlayerUltimateListListener(this.AAC);
	LSMPS.addListSelectionListener(PSLL);
	LSMPU.addListSelectionListener(PULL);

	//make second Column
	JPanel secondCol = new JPanel();
	//secondCol.setLayout(new GridLayout(2,1));
	add(secondCol);
	JLabel Pic = new JLabel();
	//Image thePic = getImage(PicClass());
	Image newthePic = PicClass().getScaledInstance( 450,700,  java.awt.Image.SCALE_SMOOTH ) ;
	Pic.setIcon(new ImageIcon(newthePic));
	secondCol.add(Pic, BorderLayout.NORTH);
	JButton Done = new JButton();
	Done.addActionListener(this.AAC);
	Done.setText("Done");
	secondCol.add(Done, BorderLayout.PAGE_END);
		
	//make third Column
	JPanel thirdCol = new JPanel();
	thirdCol.setLayout(new GridLayout(3,1));
	add(thirdCol);
	
	JPanel upthirdCol = new JPanel();
	JPanel middleThirdCol = new JPanel();
	thirdCol.add(upthirdCol);
	thirdCol.add(middleThirdCol);
	
	JTextArea superFighter = new JTextArea();
	superFighter.setFont(new Font("Arial",Font.BOLD,20));
	superFighter.setText(" Remove an assigned Super Attack ");
	superFighter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	superFighter.setWrapStyleWord(true);
	superFighter.setLineWrap(true);
	superPlayer.setEditable(false);
	JTextArea ultimateFighter = new JTextArea();
	ultimateFighter.setText(" Remove an assigned Ultimate Attack ");
	ultimateFighter.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	ultimateFighter.setWrapStyleWord(true);
	ultimateFighter.setLineWrap(true);
	ultimateFighter.setFont(new Font("Arial",Font.BOLD,20));
	ultimateFighter.setEditable(false);
	
	superFighter.setPreferredSize(new Dimension(250,50));
	ultimateFighter.setPreferredSize(new Dimension(250,50));
	upthirdCol.add(superFighter, BorderLayout.NORTH);
	middleThirdCol.add(ultimateFighter, BorderLayout.NORTH);
	exceptions = new JTextArea();
	exceptions.setText(" I will let you know if you are \n doing something illegal here");
	exceptions.setWrapStyleWord(true);
	exceptions.setLineWrap(true);
	exceptions.setEditable(false);
	exceptions.setFont(new Font("Arial",Font.BOLD,20));
	exceptions.setPreferredSize(new Dimension(250,300));
	exceptions.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
	thirdCol.add(exceptions);
	
	//make third Column Lists
		FighterSuperList = new DefaultListModel();
		FighterUltimateList = new DefaultListModel();
		JList FighterSuper = new JList(FighterSuperList);
		JList FighterUltimate = new JList(FighterUltimateList);
		FighterSuper.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		FighterUltimate.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		ListSelectionModel LSMFS = FighterSuper.getSelectionModel();
		ListSelectionModel LSMFU = FighterUltimate.getSelectionModel();
		FighterSuper.setFont(new Font("Arial",Font.BOLD,20));
		FighterUltimate.setFont(new Font("Arial",Font.BOLD,20));
		JScrollPane scrollFighterSuper = new JScrollPane(FighterSuper);
		JScrollPane scrollFighterUltimate = new JScrollPane(FighterUltimate);
		upthirdCol.add(scrollFighterSuper);
		middleThirdCol.add(scrollFighterUltimate,BorderLayout.SOUTH);
		
		
		//Assign Listeners to third col lists
		FighterSuperListListener FSLL = new FighterSuperListListener(this.AAC);
		FighterUltimateListListener FULL = new FighterUltimateListListener(this.AAC);
		LSMFS.addListSelectionListener(FSLL);
		LSMFU.addListSelectionListener(FULL);
		
		//testing
		
		//this.AAC.getGC().getGame().getPlayer().getActiveFighter().getSuperAttacks().add((SuperAttack) this.AAC.getGC().getGame().getAttacks().get(0));
		//this.AAC.getGC().getGame().getPlayer().getActiveFighter().getSuperAttacks().add((SuperAttack) this.AAC.getGC().getGame().getAttacks().get(1));
		//this.AAC.getGC().getGame().getPlayer().getSuperAttacks().add((SuperAttack) this.AAC.getGC().getGame().getAttacks().get(2));
		//this.AAC.getGC().getGame().getPlayer().getSuperAttacks().add((SuperAttack) this.AAC.getGC().getGame().getAttacks().get(3));
		
		
		//update all the lists
		updatePlayerSuper();
		updatePlayerUltimate();
		updateFighterSuper();
		updateFighterUltimate();
	
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
	
	public Image PicClass ()
	{
		
		String s = this.AAC.getGC().getGame().getPlayer().getActiveFighter().getClass().getSimpleName();
		switch(s)
		{
		case  "Saiyan" :
			return  IS.getGoku();	
		case "Earthling" :
			return IS.getEarthling();
		case "Frieza" :
			return IS.getFrieza();
		case "Majin" :
			return IS.getMajin();
		case "Namekian" :
			return  IS.getNamekian();
			
		}
		return IS.getGoku();
	}
	
	public void updatePlayerSuper ()
	{
		PlayerSuperList.clear();
		
		for(int i=0;i<AAC.getGC().getGame().getPlayer().getSuperAttacks().size();i++)
		{
			
			PlayerSuperList.addElement(this.AAC.getGC().getGame().getPlayer().getSuperAttacks().get(i).getName());
		}
	}
	
	public void updatePlayerUltimate ()
	{
		PlayerUltimateList.clear();
		
		for(int i=0;i<AAC.getGC().getGame().getPlayer().getUltimateAttacks().size();i++)
		{
			
			PlayerUltimateList.addElement(this.AAC.getGC().getGame().getPlayer().getUltimateAttacks().get(i).getName());
		}
	}
	
	public void updateFighterSuper ()
	{
		
		FighterSuperList.clear();
	
		for(int i=0;i<AAC.getGC().getGame().getPlayer().getActiveFighter().getSuperAttacks().size();i++)
		{
			
			FighterSuperList.addElement(this.AAC.getGC().getGame()
					.getPlayer().getActiveFighter().getSuperAttacks().get(i).getName());
		}
	}
	
	public void updateFighterUltimate ()
	{
		FighterUltimateList.clear();
		for(int i=0;i<AAC.getGC().getGame().getPlayer().getActiveFighter().getUltimateAttacks().size();i++)
		{
			
			FighterUltimateList.addElement(this.AAC.getGC().getGame().getPlayer()
					.getActiveFighter().getUltimateAttacks().get(i).getName());
		}
	}
	
	public static void main(String [] args)
	{
		
	}


	public JTextArea getExceptions() {
		return exceptions;
	}
	
	
	
	

}
