package dragonball.controller;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;

import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.model.world.World;
import dragonball.view.DragonView;
import dragonball.view.GameView;
import dragonball.view.ImageStorage;
import dragonball.view.VictoryView;
import dragonball.view.WorldView;



public class GameController implements ActionListener, GameListener {

	private Game game;
	private WorldView worldview;
	private ArrayList<JButton> btnsCells;
	private DefaultListModel<String> charList;
	private ListListener LL;
	private BattleController BC;
	private VictoryView VV;
	private ImageStorage IS;
	
	public GameController(Game game) throws MissingFieldException, UnknownAttackTypeException {
		this.IS = new ImageStorage();
		 this.game = game;
		this.game.setListener((GameListener) this);
		//worldview  = new WorldView();
		//btnsCells = new ArrayList<>();
		this.updateworld();

		this.makeList();
		this.updateList();
		//this.game.getPlayer().setDragonBalls(6);
		
		
	
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
		JButton btnCell = (JButton) e.getSource();
		
		if(btnCell.getText().equals("Try Again"))
		{
			this.updateworld();
			BC.getBV().getMainDefeat().setVisible(false);
			BC.getBV().removeAll();
			this.VV = null; this.BC = null; 
			this.worldview.setVisible(true);
			this.makeList();
			this.updateList();
		}
		
		else if( btnCell.getText().equals("Continue"))
		{
			
			VV.setVisible(false);
			VV.removeAll();
			this.VV = null;
			this.worldview.setVisible(true);
		}
		
		else if(btnCell.getText().equals("Create new Fighter"))
		{
			try {
				CreateFighterController CFC = new CreateFighterController(this.game,this, IS);
				CFC.getCreatefighterview().setVisible(true);
				this.worldview.setVisible(false);
			} catch (IOException e1) {
				
			}
		
		}
		else if (btnCell.getText().equals("Save Game"))
		{
			try {
				this.game.save("src/resources/SaveFolder/s.bin");
			} catch (IOException e1) {
				System.out.println("exception occured");
			}
		}
		
		else if(btnCell.getText().equals("Upgrade Current Fighter"))
		{
			UpgradeController UC = new UpgradeController(this.game,this,IS);
			this.worldview.setVisible(false);
		}
		
		else  if(btnCell.getText().equals("Assign Attacks"))
		{
			AssignAttackController AAC = new AssignAttackController(this,IS);
			this.worldview.setVisible(false);
		}
		
		
		else{
		//in case of moving on the map
		String u =btnCell.getActionCommand();
		int goingTo = Integer.parseInt(u);
		int i =game.getWorld().getPlayerColumn();
		int j = game.getWorld().getPlayerRow();
		int curLocation = j*10 +i;
		
		//System.out.println("curLocation " + curLocation);
		//System.out.println("Going to " + goingTo);
		
		if ( (goingTo + 10 != curLocation) && (goingTo -10 != curLocation) &&
				(goingTo+1 != curLocation) && (goingTo-1 != curLocation))
		{if(goingTo == curLocation)
		{
			worldview.updateTxtEvent(worldview.getTxt() +
					" \n you are already standing there");
		}
		else	{System.out.println("You must move in a square next to the one you are on right now");
			worldview.updateTxtEvent(worldview.getTxt() +
					" \n you can only move one square at a time buddy \n , even heroes have limitations");
		}
			return;
		}
		
		if((goingTo +10)== curLocation)
		{ 
			
			btnCell.setIcon(btnsCells.get(curLocation).getIcon());
			btnCell.setText("");
			btnsCells.get(curLocation ).setIcon(null);
			
			worldview.updateTxtEvent(worldview.getTxt() + " \n you moved north");
			
			Image map = getImage("src/resources/MapImage/" + curLocation + ".Png");
			Image newmap = map.getScaledInstance( 187,81,  java.awt.Image.SCALE_SMOOTH ) ;
			btnsCells.get(curLocation).setIcon(new ImageIcon(newmap));
			
			game.getWorld().moveUp();
		}
		
		if((goingTo +1)== curLocation)
		{ 
			//System.out.println("move left");
			
			btnCell.setIcon(btnsCells.get(curLocation).getIcon());
			btnCell.setText("");
			btnsCells.get(curLocation ).setIcon(null);
			//btnsCells.get(curLocation ).setText(String.valueOf(curLocation));
			worldview.updateTxtEvent(worldview.getTxt() + " \n you moved west");
			
			Image map = getImage("src/resources/MapImage/" + curLocation + ".Png");
			Image newmap = map.getScaledInstance( 187,81,  java.awt.Image.SCALE_SMOOTH ) ;
			btnsCells.get(curLocation).setIcon(new ImageIcon(newmap));
			
			game.getWorld().moveLeft();
		}
		if((goingTo -10)== curLocation)
		{ 
			//System.out.println("move down");
			
			btnCell.setIcon(btnsCells.get(curLocation).getIcon());
			btnCell.setText("");
			btnsCells.get(curLocation ).setIcon(null);
			//btnsCells.get(curLocation ).setText(String.valueOf(curLocation));
			worldview.updateTxtEvent(worldview.getTxt() + " \n you moved south");
			
			Image map = getImage("src/resources/MapImage/" + curLocation + ".Png");
			Image newmap = map.getScaledInstance( 187,81,  java.awt.Image.SCALE_SMOOTH ) ;
			btnsCells.get(curLocation).setIcon(new ImageIcon(newmap));
			
			game.getWorld().moveDown();
		}
		if((goingTo -1)== curLocation)
		{ 
			//System.out.println("move Right");
			
			btnCell.setIcon(btnsCells.get(curLocation).getIcon());
			btnCell.setText("");
			btnsCells.get(curLocation ).setIcon(null);
			//btnsCells.get(curLocation ).setText(String.valueOf(curLocation));
			worldview.updateTxtEvent(worldview.getTxt() + " \n you moved east");
			
			Image map = getImage("src/resources/MapImage/" + curLocation + ".Png");
			Image newmap = map.getScaledInstance( 187,81,  java.awt.Image.SCALE_SMOOTH ) ;
			btnsCells.get(curLocation).setIcon(new ImageIcon(newmap));
			
			game.getWorld().moveRight();
		}
		
		
			
			
		}
			}
		
		
	
	
	public static void main (String [] args) throws MissingFieldException, UnknownAttackTypeException
	{
	
		
	}
	
	
	
	public void updateworld ()
	{
		worldview = new WorldView();
		if (btnsCells != null)
		{
		btnsCells.clear();
		}
		else btnsCells = new ArrayList<JButton>();
		for (int i = 0; i<World.MAP_SIZE; i++) {
			for (int j =0; j<World.MAP_SIZE; j++) {
			// create a JButton for each cell
			JButton btnCell = new JButton();
			// set its text to the cell's info
			
			//System.out.println(btnCell.getWidth());
			Image map = getImage("src/resources/MapImage/" + (10 * i + j) + ".Png");
			Image newmap = map.getScaledInstance( 187,81,  java.awt.Image.SCALE_SMOOTH ) ;
			btnCell.setIcon(new ImageIcon(newmap));
			
			
			//btnCell.setText(String.valueOf(i) + String.valueOf(j));
			
			btnCell.setActionCommand(String.valueOf(i) + String.valueOf(j));
			// add the controller as its ActionListener
			btnCell.addActionListener(this);
			// add it to the products buy buttons panel
			worldview.addcell(btnCell);

			// and also add it to the ArrayList for later use
			btnsCells.add(btnCell);
			}
				}
		
		
		
		

		// Saving button
		JButton saveBtn = new JButton();
		saveBtn.setText("Save Game");
		saveBtn.addActionListener(this);
		worldview.addMenuButton(saveBtn);
		
		// Creating a new fighter button 
		JButton CreateFighter = new JButton();
		CreateFighter.setText("Create new Fighter");
		CreateFighter.addActionListener(this);
		worldview.addMenuButton(CreateFighter);
		
		//upgrading current fighter button
		JButton UpgradeFighter = new JButton();
		UpgradeFighter.setText("Upgrade Current Fighter");
		UpgradeFighter.addActionListener(this);
		worldview.addMenuButton(UpgradeFighter);
		
		//Assign Attack to current fighter button
		JButton AssignAttack = new JButton();
		AssignAttack.setText("Assign Attacks");
		AssignAttack.addActionListener(this);
		worldview.addMenuButton(AssignAttack);
		
		//Image boss = getImage("src/resources/boss.Png");
		Image newboss = IS.getBoss().getScaledInstance( 100,70,  java.awt.Image.SCALE_SMOOTH ) ;
		btnsCells.get(0).setIcon((new ImageIcon(newboss)));
		btnsCells.get(0).setBackground(Color.black);
		
			this.worldview.getPlayerInfo().setText("Player :" + this.game.getPlayer().getName() + "\n Active Fighter :" 
		+ this.game.getPlayer().getActiveFighter().getName() + " lvl " + this.game.getPlayer().getActiveFighter().getLevel()
		+ "\n Senzu Beans : " + this.game.getPlayer().getSenzuBeans() +
		"\n Dragon Balls : " + this.game.getPlayer().getDragonBalls());
			
			this.worldview.getPlayerInfo().setFont((new Font(Font.MONOSPACED, Font.PLAIN, 15)));
			
			this.MiniUpdateWorld();
			
	
		
	}
	
	public void MiniUpdateWorld()
	{
		this.worldview.getPlayerInfo().setText("Player :" + this.game.getPlayer().getName() + "\n Active Fighter :" 
				+ this.game.getPlayer().getActiveFighter().getName() +
				" lvl " + this.game.getPlayer().getActiveFighter().getLevel()
				+ "\n Senzu Beans : " + this.game.getPlayer().getSenzuBeans() +
				"\n Dragon Balls : " + this.game.getPlayer().getDragonBalls() +
				"\n Maps Cleared : " + this.game.getPlayer().getExploredMaps()+
				"\n Current Exp : " + this.game.getPlayer().getActiveFighter().getXp() +
				"\n Exp until Next Level : " + this.game.getPlayer().getActiveFighter().getTargetXp());
					
					this.worldview.getPlayerInfo().setFont((new Font(Font.MONOSPACED, Font.PLAIN, 15)));
					String s = this.game.getPlayer().getActiveFighter().getClass().getSimpleName();
					//System.out.println(s);
					int i =this.game.getWorld().getPlayerColumn();
					int j =this.game.getWorld().getPlayerRow();
					int curLocation = j*10 +i;
					btnsCells.get(curLocation).setText("");
					
					switch(s)
					{
					case  "Saiyan" :
						//Image Goku = getImage("src/resources/Goku.Png");
						Image newGoku = IS.getGoku().getScaledInstance( 70,70,  java.awt.Image.SCALE_SMOOTH ) ;
						btnsCells.get(curLocation).setIcon(new ImageIcon(newGoku));
						break;
					case "Earthling" :
						//Image Earthling = getImage("src/resources/Earthling.Png");
						Image newEarthling = IS.getEarthling().getScaledInstance( 70,70,  java.awt.Image.SCALE_SMOOTH ) ;
						btnsCells.get(curLocation).setIcon(new ImageIcon(newEarthling));
						break;
					case "Frieza" :
						//Image Frieza = getImage("src/resources/Frieza2.Png");
						Image newFrieza = IS.getFrieza().getScaledInstance( 70,70,  java.awt.Image.SCALE_SMOOTH ) ;
						btnsCells.get(curLocation).setIcon(new ImageIcon(newFrieza));
						break;
					case "Majin" :
						//Image Majin = getImage("src/resources/Majin.Png");
						Image newMajin = IS.getMajin().getScaledInstance( 70,70,  java.awt.Image.SCALE_SMOOTH ) ;
						btnsCells.get(curLocation).setIcon(new ImageIcon(newMajin));
						break;
					case "Namekian" :
						//Image Namekian = getImage("src/resources/Namekian.Png");
						Image newNamekian = IS.getNamekian().getScaledInstance( 70,70,  java.awt.Image.SCALE_SMOOTH ) ;
						btnsCells.get(curLocation).setIcon(new ImageIcon(newNamekian));
						break;
						
					}
					
					//Image boss = getImage("src/resources/boss.Png");
					Image newboss = IS.getBoss().getScaledInstance( 100,70,  java.awt.Image.SCALE_SMOOTH ) ;
					btnsCells.get(0).setIcon((new ImageIcon(newboss)));
					btnsCells.get(0).setBackground(Color.black);
					this.game.getWorld().toString();
					
	}
	
	
	
	public void updateList ()
	{
		
		charList.clear();
		for(int i=0;i<this.game.getPlayer().getFighters().size();i++)
		{
			
			charList.addElement(this.game.getPlayer().getFighters().get(i).getName());
		}
	}

	
	public void makeList ()
	{
		charList = new DefaultListModel();
		JList chars = new JList(charList);
		chars.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		ListSelectionModel LSM = chars.getSelectionModel();
		LL = new ListListener(this);
		LSM.addListSelectionListener(LL);
		JScrollPane scrollList = new JScrollPane(chars);
		chars.setFont(new Font("Arial",Font.BOLD,20));
		this.worldview.getPanelInfo().add(scrollList);
	}







	@Override
	public void onDragonCalled(Dragon dragon) {
		game.getPlayer().setDragonBalls(0);
		worldview.updateTxtEvent(worldview.getTxt() + " \n you Collected 7 DragonBalls! Congratulations, you got a wish");
		worldview.setVisible(false);
		DragonController DC = new DragonController(this,IS);
		DC.onDragonCalled(dragon);
		DC.getDragonview().setVisible(true);
	
	}







	@Override
	public void onCollectibleFound(Collectible collectible) {
		//System.out.println(collectible.toString());
		if(collectible.toString().equals("Senzu Bean"))
		{
			//System.out.println(this.game.getPlayer().getSenzuBeans());
			//this.game.getPlayer().setSenzuBeans(this.game.getPlayer().getSenzuBeans()+1);
			//System.out.println(this.game.getPlayer().getSenzuBeans());
			this.MiniUpdateWorld();
			worldview.updateTxtEvent(worldview.getTxt() + "\n you found a senzu Bean!");
		}
		else {
			//this.game.getPlayer().setDragonBalls(this.game.getPlayer().getDragonBalls()+1);
			this.MiniUpdateWorld();
			worldview.updateTxtEvent(worldview.getTxt() + "\n you found a Dragon Ball!");
			//if(this.game.getPlayer().getDragonBalls() == 7)
			//{
			//	int i = new Random().nextInt(this.game.getDragons().size());
			//	this.onDragonCalled(this.game.getDragons().get(i));
			//}
		}
		
		
	}
	
	public void worldviewon()
	{
		this.worldview.setVisible(true);
	}







	@Override
	public void onBattleEvent(BattleEvent e) {
		if(e.getType() == BattleEventType.STARTED )
		{
		this.worldview.setVisible(false);
		this.BC = new BattleController(this, e,IS);
		}
		else if( e.getType() == BattleEventType.NEW_TURN && 
				((Battle) e.getSource()).getFoe().toString().equals(((Battle) e.getSource()).getAttacker().toString()) )
		{
			
			try {
				BC.getBV().Refresh("");
				((Battle) e.getSource()).play();
				BC.getBV().getEvents().setText(BC.getBV().getEvents().getText()+ "Opponent used: "+
				((Battle) e.getSource()).getOpponentmove() + " \n ------------------- "+" \n" + "it is you turn now \n");
				BC.getBV().Refresh("");
				
				
			} catch (NotEnoughKiException e1) {
				
				//can never happen
			}
		}
		else if(e.getType() == BattleEventType.ENDED)
		{
			//call the win screen
			if(e.getWinner() == e.getBattle().getMe())
			{
			BC.getBV().setVisible(false);
			//BC = null;
			boolean s = ((NonPlayableFighter )e.getBattle().getFoe()).isStrong();
			if(s == true)
			{
				MiniUpdateWorld();
			}
			
			VV = new VictoryView(this,this.BC.getBV().getWasLevel(),this.BC.getBV().getWasXP(),s,
					((NonPlayableFighter )e.getBattle().getFoe()),BC.getBV().getSAList(),BC.getBV().getUAList(),IS);
			
			BC = null;
			}
			else {
				//call Defeat screen 
				BC.getBV().Defeated();
				
			}
		}
		
		
		
	}

	
	
	
	public WorldView getWorldview() {
		return worldview;
	}

	public void setWorldview(WorldView worldview) {
		this.worldview = worldview;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
	
	

}