package dragonball.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Collectible;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.view.DragonView;
import dragonball.view.GameView;
import dragonball.view.ImageStorage;
import dragonball.view.WorldView;

public class DragonController implements ActionListener, GameListener{
	

	private DragonView dragonview;
	private ArrayList<JButton> btnsChoices;
	private GameController GC;
	
	DragonController (GameController GC, ImageStorage IS)
	{
		this.GC = GC;
		this.GC.getGame().setListener((GameListener) this);
		dragonview = new DragonView(IS);
		btnsChoices = new ArrayList<>();
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
	
	
	
	
	

	public void onDragonCalled(Dragon dragon) {
		DragonWish[] DW = dragon.getWishes();
		
		for(int i=0;i<DW.length;i++)
		{
			
			JButton btnChoice = new JButton();
			switch(DW[i].getType())
			{
			case SENZU_BEANS:
				int s =DW[i].getSenzuBeans();
				btnChoice.setText( String.valueOf(s) + " senzu Beans");
				btnChoice.addActionListener(this);
				dragonview.addChoice(btnChoice);
				btnChoice.setActionCommand(String.valueOf(s));
				btnChoice.setBackground(Color.GREEN);
				System.out.println(btnChoice.getBackground().toString());
				break;
			case ABILITY_POINTS: 
				btnChoice.setText( String.valueOf(DW[i].getAbilityPoints()) + " ability points");
				btnChoice.addActionListener(this);
				dragonview.addChoice(btnChoice);
				btnChoice.setActionCommand(String.valueOf(DW[i].getAbilityPoints()));
				btnChoice.setBackground(Color.cyan);
				System.out.println(btnChoice.getBackground().toString());
				break;
			case SUPER_ATTACK:
				btnChoice.setText(DW [i].getSuperAttack().getName());
				btnChoice.addActionListener(this);
				dragonview.addChoice(btnChoice);
				btnChoice.setBackground(Color.orange);
				btnChoice.setActionCommand(DW[i].getSuperAttack().getName());
				System.out.println(btnChoice.getBackground().toString());
				break;
			case ULTIMATE_ATTACK:
				btnChoice.setText(DW[i].getUltimateAttack().getName());
				btnChoice.addActionListener(this);
				dragonview.addChoice(btnChoice);
				btnChoice.setBackground(Color.red);
				btnChoice.setActionCommand(DW[i].getUltimateAttack().getName());
				System.out.println(btnChoice.getBackground().toString());
			
				break;
			}
			
		}
		
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onBattleEvent(BattleEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	public void actionPerformed(ActionEvent e) {
		JButton btnChoice = (JButton) e.getSource();
		
		switch(btnChoice.getBackground().toString())
		{
		case "java.awt.Color[r=0,g=255,b=0]" :
			this.GC.getGame().getPlayer().setSenzuBeans(Integer.parseInt(btnChoice.getActionCommand())
					+this.GC.getGame().getPlayer().getSenzuBeans());
			this.dragonview.setVisible(false);
			this.GC.getWorldview().setVisible(true);
			
			break;
		case "java.awt.Color[r=0,g=255,b=255]": 
			this.GC.getGame().getPlayer().getActiveFighter().setAbilityPoints
			(this.GC.getGame().getPlayer().getActiveFighter().getAbilityPoints() +Integer.parseInt(btnChoice.getActionCommand()));
			this.dragonview.setVisible(false);
			this.GC.getWorldview().setVisible(true);
			break;
		case "java.awt.Color[r=255,g=200,b=0]":
		for(int i=0;i<GC.getGame().getAttacks().size();i++)
		{
			if(GC.getGame().getAttacks().get(i).getName().equals(btnChoice.getActionCommand()))
			{
				GC.getGame().getPlayer().getSuperAttacks().add((SuperAttack) GC.getGame().getAttacks().get(i));
				
			}
		}
		this.dragonview.setVisible(false);
		this.GC.getWorldview().setVisible(true);
			break;
		case "java.awt.Color[r=255,g=0,b=0]":
			for(int i=0;i<GC.getGame().getAttacks().size();i++)
			{
				if(GC.getGame().getAttacks().get(i).getName().equals(btnChoice.getActionCommand()))
				{
					GC.getGame().getPlayer().getUltimateAttacks().add((UltimateAttack) GC.getGame().getAttacks().get(i));
					
				}
			}
			this.dragonview.setVisible(false);
			this.GC.getWorldview().setVisible(true);
			break;}
		
	}
	
	public static void main (String [] args) throws MissingFieldException, UnknownAttackTypeException
	{
		//DragonController DC = new DragonController();
		//DC.onDragonCalled(DC.game.getDragons().get(0));
		//DC.dragonview.setVisible(true);
	}

	public DragonView getDragonview() {
		return dragonview;
	}

	public void setDragonview(DragonView dragonview) {
		this.dragonview = dragonview;
	}
	
	

}
