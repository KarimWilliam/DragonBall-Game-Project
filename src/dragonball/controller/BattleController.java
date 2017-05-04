package dragonball.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import dragonball.model.attack.PhysicalAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.cell.Collectible;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.view.BattleView;
import dragonball.view.ImageStorage;

public class BattleController implements ActionListener {

	private GameController GC;
	private BattleView BV;
	private BattleEvent e;
	
	public BattleController(GameController GC, BattleEvent e, ImageStorage IS)
	{
		this.GC = GC;
		this.BV = new BattleView(this,e,IS);
		this.e=e;
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
		if(btn.getText().equals("Attack"))
		{
			try {
				BV.getEvents().setText(BV.getEvents().getText() + "you performed a Basic Attack \n ------------------- \n");
				((Battle) this.e.getSource()).attack(new PhysicalAttack());
				
			} catch (NotEnoughKiException e1) {
				// can never happen
			}
		}
		if(btn.getText().equals("Block"))
		{
			BV.getEvents().setText(BV.getEvents().getText() + "you used Block! \n ------------------- \n");
			((Battle) this.e.getSource()).block();
		}
		if(btn.getText().equals("Use Senzu Bean"))
		{
			try {
				if(this.GC.getGame().getPlayer().getSenzuBeans()>0)
				{
				BV.getEvents().setText(BV.getEvents().getText() + "You ate a senzu Bean and have been Healed \n ------------------- \n");
				}
				((Battle) this.e.getSource()).use(GC.getGame().getPlayer(), Collectible.SENZU_BEAN);
			} catch (NotEnoughSenzuBeansException e1) {
				StyledDocument doc = BV.getEvents().getStyledDocument();

				 Style style = BV.getEvents().addStyle("I'm a Style", null);
			        StyleConstants.setForeground(style, Color.red);

			        try { doc.insertString(doc.getLength(), " -You Dont have any senzu Beans! \n",style); }
			        catch (BadLocationException f){}
			    }
		}
		
		if(btn.getActionCommand().equals("0") ||btn.getActionCommand().equals("1") || btn.getActionCommand().equals("2")||
				btn.getActionCommand().equals("3"))
		{
			
			try {
				((Battle) this.e.getSource()).attack(GC.getGame().getPlayer().getActiveFighter()
						.getSuperAttacks().get(Integer.parseInt(btn.getActionCommand())));
				if(this.GC.getGame().getPlayer().getActiveFighter().getKi()>0)
				{
				BV.getEvents().setText(BV.getEvents().getText() + "you used a Super Attack! \n ------------------- \n");
				}
			} catch (NotEnoughKiException e1) {
				
				this.BV.getSuper().setVisible(false);
				this.BV.getSkills().setVisible(true);
				StyledDocument doc = BV.getEvents().getStyledDocument();

				 Style style = BV.getEvents().addStyle("I'm a Style", null);
			        StyleConstants.setForeground(style, Color.red);

			        try { doc.insertString(doc.getLength(), " -You Dont have Enough KI! \n",style); }
			        catch (BadLocationException f){}
				
			} catch (NumberFormatException e1) {
				
				System.out.println("PANIC");
			}
		}
		if(btn.getActionCommand().equals("10") || btn.getActionCommand().equals("11"))
				
		{
			try {
				((Battle) this.e.getSource()).attack(GC.getGame().getPlayer().getActiveFighter()
						.getUltimateAttacks().get((Integer.parseInt(btn.getActionCommand()))-10));
				if(this.GC.getGame().getPlayer().getActiveFighter().getKi()>2)
				{
				BV.getEvents().setText(BV.getEvents().getText() + "you used an Ultimate Attack! \n ------------------- \n");
				}
			} catch (NotEnoughKiException e1) {
				this.BV.getUltimate().setVisible(false);
				this.BV.getSkills().setVisible(true);
				StyledDocument doc = BV.getEvents().getStyledDocument();

				 Style style = BV.getEvents().addStyle("I'm a Style", null);
			        StyleConstants.setForeground(style, Color.red);

			        try { doc.insertString(doc.getLength(), " -You Dont have Enough KI! You Need 3 KI for an Ultimate Attack \n",style); }
			        catch (BadLocationException f){}
				
			} catch (NumberFormatException e1) {
				// never happens
				
			}
			
		}
		
		
		
	}


	public GameController getGC() {
		return GC;
	}


	public BattleView getBV() {
		return BV;
	}
	
	
}
