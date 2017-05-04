package dragonball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;

import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.game.Game;
import dragonball.view.ImageStorage;
import dragonball.view.UpgradeView;

public class UpgradeController implements ActionListener {
	
	private Game game;
	private GameController GC;
	
	private UpgradeView upgradeview;
	private JTextArea JTA1;
	private JTextArea JTA2;
	private JTextArea JTA3;
	private JTextArea JTA4;
	private JTextArea JTA5;
	
	private int Health;
	private int Stamina;
	private int KI;
	private int Blast;
	private int Physical;
	private int ability;
	private  int abilityE;
	public UpgradeController(Game game, GameController GC, ImageStorage IS)
	{
		this.GC = GC;
		this.game = game;
		//this.game.getPlayer().getActiveFighter().setAbilityPoints(20); // remove this later
		
		upgradeview = new UpgradeView(this,IS);
		JTA1 = new JTextArea();
		JTA2 = new JTextArea();
		JTA3 = new JTextArea();
		JTA4 = new JTextArea();
		JTA5 = new JTextArea();
		Health =0;
		Stamina =0;
		KI = 0;
		Blast = 0;
		Physical = 0;
		ability = game.getPlayer().getActiveFighter().getAbilityPoints();
		abilityE = game.getPlayer().getActiveFighter().getAbilityPoints();
		
		
		upgradeview.getAbilityPoints().setText
		("Abillity Points : "+Integer.toString(game.getPlayer().getActiveFighter().getAbilityPoints()));
		
		upgradeview.makeSquare("Health", game.getPlayer().getActiveFighter().getMaxHealthPoints(),JTA1);
		upgradeview.makeSquare("Physical \n Damage", game.getPlayer().getActiveFighter().getPhysicalDamage(),JTA2);
		upgradeview.makeSquare("Stamina", game.getPlayer().getActiveFighter().getMaxStamina(),JTA3);
		upgradeview.makeSquare("KI",game.getPlayer().getActiveFighter().getMaxKi(),JTA4);
		upgradeview.makeSquare("Blast \n Damage", game.getPlayer().getActiveFighter().getBlastDamage(),JTA5);
		upgradeview.setVisible(true);
	}

	
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		String state = btn.getActionCommand();
		
		switch(state)
		{
		case "Healthup": 
			if(ability>0)
			{
				ability --;
				Health ++;
				JTA1.setText("Health \n" +  (game.getPlayer().getActiveFighter().getMaxHealthPoints()+ (50*Health)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
				
			}
			break;
		
		case "Healthdown":
			if(Health>0 && ability< abilityE)
			{
				Health--;
				ability++;
				JTA1.setText("Health \n" + (game.getPlayer().getActiveFighter().getMaxHealthPoints()+ (50*Health)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
			}
			break;
			
		case "Physical \n Damageup": 
			if(ability>0)
			{
				ability --;
				Physical ++;
				JTA2.setText("Physical \n Damage \n" +  (game.getPlayer().getActiveFighter().getPhysicalDamage()+ (50*Physical)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
				
			}
			break;
		
		case "Physical \n Damagedown":
			if(Physical>0 && ability< abilityE)
			{
				Physical--;
				ability++;
				JTA2.setText("Physical \n Damage \n" + (game.getPlayer().getActiveFighter().getPhysicalDamage()+ (50*Physical)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
			}
			break;
			
		case "Staminaup": 
			if(ability>0)
			{
				ability --;
				Stamina ++;
				JTA3.setText("Stamina \n" +  (game.getPlayer().getActiveFighter().getMaxStamina()+ (1*Stamina)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
				
			}
			break;
		
		case "Staminadown":
			if(Stamina>0 && ability< abilityE)
			{
				Stamina--;
				ability++;
				JTA3.setText("Stamina \n" + (game.getPlayer().getActiveFighter().getMaxStamina()+ (1*Stamina)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
			}
			break;
			
		case "KIup": 
			if(ability>0)
			{
				ability --;
				KI ++;
				JTA4.setText("KI \n" +  (game.getPlayer().getActiveFighter().getMaxKi()+ (1*KI)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
				
			}
			break;
		
		case "KIdown":
			if(KI>0 && ability< abilityE)
			{
				KI--;
				ability++;
				JTA4.setText("KI \n" + (game.getPlayer().getActiveFighter().getMaxKi()+ (1*KI)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
			}
			break;
			
		case "Blast \n Damageup": 
			if(ability>0)
			{
				ability --;
				Blast ++;
				JTA5.setText("Blast \n Damage \n" +  (game.getPlayer().getActiveFighter().getBlastDamage()+ (50*Blast)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
				
			}
			break;
		
		case "Blast \n Damagedown":
			if(Blast>0 && ability< abilityE)
			{
				Blast--;
				ability++;
				JTA5.setText("Blast \n Damage \n" + (game.getPlayer().getActiveFighter().getBlastDamage()+ (50*Blast)) );
				upgradeview.getAbilityPoints().setText("Ability Points : " + ability);
			}
			break;
			
		case "Done" :
			while(Health>0)
			{
				Health--;
				try {
					game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'H');
				} catch (NotEnoughAbilityPointsException e1) {
					// can never happen.
					
				}
			}
			while(Stamina>0)
			{
				Stamina--;
				try {
					game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'S');
				} catch (NotEnoughAbilityPointsException e1) {
					// can never happen.
					
				}
			}
			while(KI>0)
			{
				KI--;
				try {
					game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'K');
				} catch (NotEnoughAbilityPointsException e1) {
					// can never happen.
					
				}
			}
			while(Physical>0)
			{
				Physical--;
				try {
					game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'P');
				} catch (NotEnoughAbilityPointsException e1) {
					// can never happen.
					
				}
			}
			while(Blast>0)
			{
				Blast--;
				try {
					game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(), 'B');
				} catch (NotEnoughAbilityPointsException e1) {
					// can never happen.
					
				}
			}
			//System.out.println(game.getPlayer().getActiveFighter().getMaxHealthPoints() + "");
			this.upgradeview.setVisible(false);
			this.GC.getWorldview().setVisible(true);
		}
		
	}
	
	

	public Game getGame() {
		return game;
	}

	public UpgradeView getUpgradeview() {
		return upgradeview;
	}
	
	

}
