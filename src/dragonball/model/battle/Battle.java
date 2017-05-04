package dragonball.model.battle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.player.Player;

public class Battle implements Serializable{
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;
	private String Opponentmove;
	private boolean AION;
	public Battle(BattleOpponent me, BattleOpponent foe, boolean AION) {
		this.me = me;
		this.foe = foe;
		this.attacker = me;
		this.AION = AION;
		
		// set current values appropriately
		Fighter meFighter = (Fighter) me;
		meFighter.setHealthPoints(meFighter.getMaxHealthPoints());
		meFighter.setKi(0);
		meFighter.setStamina(meFighter.getMaxStamina());
		// reset a saiyan's transformation state in case it was transformed in a previous battle
		if (me instanceof Saiyan) {
			Saiyan meSaiyan = (Saiyan) me;
			meSaiyan.setTransformed(false);
		}

		Fighter foeFighter = (Fighter) foe;
		foeFighter.setHealthPoints(foeFighter.getMaxHealthPoints());
		foeFighter.setKi(0);
		foeFighter.setStamina(foeFighter.getMaxStamina());
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}

	public BattleOpponent getDefender() {
		return attacker == me ? foe : me;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

	public ArrayList<Attack> getAssignedAttacks() {
		Fighter attackerFighter = (Fighter) attacker;

		ArrayList<Attack> attacks = new ArrayList<>();
		// make sure to include the physical attack as well
		attacks.add(new PhysicalAttack());
		attacks.addAll(attackerFighter.getSuperAttacks());
		attacks.addAll(attackerFighter.getUltimateAttacks());
		return attacks;
	}

	public void switchTurn() {
		attacker = getDefender();
	}

	public void endTurn() {
		// reset block mode
		if (attacker == me && foeBlocking) {
			foeBlocking = false;
		} else if (attacker == foe && meBlocking) {
			meBlocking = false;
		}

		// if i'm dead
		if (((Fighter) me).getHealthPoints() == 0) {
			// tell everyone my opponent won
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, foe));
			// if my opponent is dead
		} else if (((Fighter) foe).getHealthPoints() == 0) {
			// tell everyone i won
			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ENDED, me));
		} else {
			switchTurn();

			getAttacker().onDefenderTurn();
			getDefender().onAttackerTurn();

			notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
		}
	}

	public void start() {
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.STARTED));
		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.NEW_TURN));
	}

	// used to automate turn for opponent a.k.a. ai
	public void play() throws NotEnoughKiException {
		
		if(AION ==false) {
		if (new Random().nextInt(100) > 15) {
			ArrayList<Attack> attacks = getAssignedAttacks();
			Attack randomAttack;
			Fighter attackerFighter = (Fighter) attacker;

			do {
			randomAttack = attacks.get(new Random().nextInt(attacks.size()));

				if (randomAttack instanceof PhysicalAttack
						|| (randomAttack instanceof SuperAttack && attackerFighter.getKi() >= 1)
					|| (randomAttack instanceof UltimateAttack && attackerFighter.getKi() >= 3)) {
					break;
				}

		} while (true);

			Opponentmove = ("==> " + randomAttack.getClass().getSimpleName() + ": " + randomAttack.getName());

			if(randomAttack.getName().equals("Physical Attack")) 
			{
				Opponentmove = "==> Physical Attack";
			}
			attack(randomAttack);
		} else {
			Opponentmove = "Block";
			block();
		}
	}
		// AI VERSION 
		else {
			
			//Get best skills 
			SuperAttack BestSuper =null; UltimateAttack BestUltimate =null; 
			SuperAttack MC =null; UltimateAttack SS= null;
			boolean HasMC = false;  boolean HasSS = false; 
			int MyKI = ((Fighter)foe).getKi();
			int MyMaxKI = ((Fighter)foe).getMaxKi();
			int myHP = ((Fighter)foe).getHealthPoints();
			int maxPh = (new PhysicalAttack()).getAppliedDamage(foe);
			int mostPower = 0;
			Attack mostPowerA = null;
			int maxS =0; int maxU =0; 
			int PlayerHP = ((Fighter) me).getHealthPoints();
			int PlayerStamina = ((Fighter) me).getStamina();
			int PlayerKI = ((Fighter) me).getKi();
			int myStamina = ((Fighter)foe).getStamina();
			boolean NotGreen = true;
			if (((Fighter) me) instanceof Namekian)
			{
				NotGreen = false;
			}
			boolean isTransformed = false;
			boolean PlayerCanTransform = false;
			if(((Fighter) me) instanceof Saiyan)
			{
				if (((Saiyan) me).isTransformed()== false)
					PlayerCanTransform = true;
			}
			if(((Fighter) foe) instanceof Saiyan)
			{
				if (((Saiyan) foe).isTransformed()== true)
					isTransformed = true;
			}
			for(int i=0;i<((Fighter) foe).getSuperAttacks().size();i++)
			{
				if (((Fighter) foe).getSuperAttacks().get(i).getName().equals("Maximum Charge"))
					{
					HasMC = true;
					MC = ((Fighter) foe).getSuperAttacks().get(i);
					}
				
				if (((Fighter) foe).getSuperAttacks().get(i).getAppliedDamage(foe)>maxS)
				{
					
				maxS =((Fighter) foe).getSuperAttacks().get(i).getAppliedDamage(foe);
				BestSuper = ((Fighter) foe).getSuperAttacks().get(i);
				}
				
			}
			
			for(int i=0;i<((Fighter) foe).getUltimateAttacks().size();i++)
			{
				if (((Fighter) foe).getUltimateAttacks().get(i).getName().equals("Super Saiyan")) 
			{
					HasSS = true;
					SS = ((Fighter) foe).getUltimateAttacks().get(i);
			}
				if (((Fighter) foe).getUltimateAttacks().get(i).getAppliedDamage(foe)>maxU)
				{
				maxU =((Fighter) foe).getUltimateAttacks().get(i).getAppliedDamage(foe);
				BestUltimate = ((Fighter) foe).getUltimateAttacks().get(i);
				}
				
			}
			//getting most powerful move
	
			if(MyKI ==0)
			{
				mostPower = maxPh;
				mostPowerA = new PhysicalAttack();
			}
			else if (MyKI <3)
			{
				if (maxS > maxPh)
				{
					mostPower = maxS;
					mostPowerA = BestSuper;
				}
				else 
					mostPower = maxPh;
				mostPowerA = new PhysicalAttack();
			}
			else if(MyKI > 2)
			{
				if(maxU>maxS && maxU>maxPh )
				{
					mostPower = maxU;
					mostPowerA = BestUltimate;
				} 
				else if (maxS>maxU && maxS>maxPh)
				{
					mostPower = maxS;
					mostPowerA = BestSuper;
				}
				else
				{
					mostPower = maxPh;
					mostPowerA = new PhysicalAttack();
				}
			}
			
			
			
			
			// THE MOVE if opponent is blocking
			if(  meBlocking ==true )
			{
				if (PlayerHP + PlayerStamina< mostPower ) 
					
				{
					Opponentmove = ("==> " + mostPowerA.getClass().getSimpleName() + ": " + mostPowerA.getName());
				attack(mostPowerA);
				}
				else if(PlayerHP< mostPower && myHP < (((Fighter)foe).getMaxHealthPoints()*20/100))
				{
					block();
					Opponentmove = "Block";
				}
				
				else if(MyKI ==MyMaxKI && HasSS == false  )
				{
					if(mostPower != maxPh)
					{
						Opponentmove = ("==> " + BestSuper.getClass().getSimpleName() + ": " + BestSuper.getName());
						attack(BestSuper);
					}
					else 
						{
						Opponentmove = "==> Physical Attack";
						attack(new PhysicalAttack());
						
						}
				}
				else if(HasSS == true && isTransformed == false && MyKI>3)
				{
					Opponentmove = ("==> " + SS.getClass().getSimpleName() + ": " + SS.getName());
					attack(SS);
					
				}
				else if(HasMC ==true && MyKI<3 && mostPower !=maxPh)
				{
					Opponentmove = ("==> " + MC.getClass().getSimpleName() + ": " + MC.getName());
					attack(MC);
				}
				else if (PlayerStamina<3 && MyKI>=((Fighter)foe).getKi()*80/100 && PlayerHP< (20/100) * ((Fighter)me).getMaxHealthPoints()  ) 
				{
					Opponentmove = ("==> " + BestSuper.getClass().getSimpleName() + ": " + BestSuper.getName());
					attack(BestSuper);
				}
				else if (PlayerStamina<2 && MyKI>=((Fighter)foe).getKi()*65/100 && PlayerHP< (20/100) * ((Fighter)me).getMaxHealthPoints())
				{
					Opponentmove = ("==> " + BestSuper.getClass().getSimpleName() + ": " + BestSuper.getName());
					attack(BestSuper);
				}
				else {
					Opponentmove = "==> Physical Attack";
					attack(new PhysicalAttack());
					
				}

			}
			
			
			
			// THE MOVE if opponent is not blocking
			else if(meBlocking == false)
			{
				if (PlayerHP < mostPower ) 
					
					attack(mostPowerA);
				else if(MyKI >4 && myHP>(((Fighter)foe).getMaxHealthPoints()*75/100) && HasSS == true)
				{
					Opponentmove = ("==> " + SS.getClass().getSimpleName() + ": " + SS.getName());
					attack(SS);
				}
				
				else if(MyKI ==MyMaxKI)
				{
					Opponentmove = ("==> " + mostPowerA.getClass().getSimpleName() + ": " + mostPowerA.getName());
					attack(mostPowerA);
				}
				else if(myStamina ==((Fighter)foe).getMaxStamina() && (PlayerCanTransform == false) && PlayerKI>2 && NotGreen == true )
				{
					int s = new Random().nextInt(3);
					if (s==0)
					{
					block();		
					Opponentmove = "Block";
					}
					else return;
				}
				
				else if (HasMC = true && maxS *2  < maxU  && MyKI <3)
				{
					Opponentmove = ("==> " + MC.getClass().getSimpleName() + ": " + MC.getName());
					attack(MC);
				}
				else if((maxS > 2* maxPh || maxU > 2* maxPh  )&& HasMC == true && MyKI<3)
				{
					Opponentmove = ("==> " + MC.getClass().getSimpleName() + ": " + MC.getName());
					attack(MC);
				}
				else if(maxU> 2* maxS && maxU > 2* maxPh && MyKI>2 && maxU +(maxPh *3) > (2* maxS )+ (2* maxPh))
				{
					Opponentmove = ("==> " + BestUltimate.getClass().getSimpleName() + ": " + BestUltimate.getName());
					attack(BestUltimate);
				}
				else if(maxS>2*maxPh && MyKI>0)
				{
					Opponentmove = ("==> " + BestSuper.getClass().getSimpleName() + ": " + BestSuper.getName());
					attack(BestSuper);
				}
				else if (maxU< maxS*3 && MyKI>0 )
				{
					Opponentmove = ("==> " + BestSuper.getClass().getSimpleName() + ": " + BestSuper.getName());
					attack(BestSuper);
				}
				else if (myHP<15/100 *((Fighter)foe).getMaxHealthPoints() && MyKI==1)
				{

					Opponentmove = ("==> " + BestSuper.getClass().getSimpleName() + ": " + BestSuper.getName());
					attack(BestSuper);
				}
				
					else 
				
					{
					Opponentmove = "==> Physical Attack";
					attack(new PhysicalAttack());
					
					}
					
				
				
				
			}
			
			
			
			
			
			
		}
			
	}

	// perform an attack and end turn
	public void attack(Attack attack) throws NotEnoughKiException {
		attack.onUse(attacker, getDefender(),
				(attacker == me && foeBlocking) || (attacker == foe && meBlocking));

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.ATTACK, attack));

		endTurn();
	}

	// perform a block and end turn
	public void block() {
		if (attacker == me) {
			meBlocking = true;
		} else if (attacker == foe) {
			foeBlocking = true;
		}

		notifyOnBattleEvent(new BattleEvent(this, BattleEventType.BLOCK));

		endTurn();
	}

	// use a collectible and end turn
	public void use(Player player, Collectible collectible) throws NotEnoughSenzuBeansException {
		switch (collectible) {
		case SENZU_BEAN:
			if (player.getSenzuBeans() > 0) {
				PlayableFighter activeFighter = player.getActiveFighter();
				activeFighter.setHealthPoints(activeFighter.getMaxHealthPoints());
				activeFighter.setStamina(activeFighter.getMaxStamina());

				player.setSenzuBeans(player.getSenzuBeans() - 1);

				notifyOnBattleEvent(new BattleEvent(this, BattleEventType.USE, collectible));
			} else {
				throw new NotEnoughSenzuBeansException();
			}
			break;
		default:
			break;
		}

		endTurn();
	}

	public void setListener(BattleListener listener) {
		this.listener = listener;
	}

	public void notifyOnBattleEvent(BattleEvent e) {
		if (listener != null) {
			listener.onBattleEvent(e);
		}
	}

	public String getOpponentmove() {
		return Opponentmove;
	}
	
	
}
