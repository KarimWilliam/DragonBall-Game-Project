package dragonball.controller;

import java.io.Serializable;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;

public class PlayerUltimateListListener implements ListSelectionListener, Serializable {

	AssignAttackController AAC;
	public  PlayerUltimateListListener (AssignAttackController AAC)
	{
		this.AAC = AAC;
	}
	
	public void valueChanged(ListSelectionEvent e) {
		
		ListSelectionModel lsm = (ListSelectionModel)e.getSource();
		  int l = e.getLastIndex();
		  int f = e.getFirstIndex();
		  //System.out.println(l + " " + f);
		  int minIndex = lsm.getMinSelectionIndex();
          int maxIndex = lsm.getMaxSelectionIndex();
          for (int i = minIndex; i <= maxIndex; i++) {
              if (lsm.isSelectedIndex(i)) {
            	  if (lsm.isSelectedIndex(i)) {
                	  try {
    					AAC.getGC().getGame().getPlayer().assignAttack(AAC.getGC().getGame().getPlayer().getActiveFighter(),
    							  AAC.getGC().getGame().getPlayer().getUltimateAttacks().get(i), null);
    					AAC.AAV.updatePlayerUltimate();
    					AAC.AAV.updateFighterUltimate();
    				} catch (MaximumAttacksLearnedException e1) {
    					AAC.AAV.getExceptions().setText("Maximum Ultimate Attacks Learned");
    				} catch (DuplicateAttackException e1) {
    					AAC.AAV.getExceptions().setText("you already have this attack");
    					e1.printStackTrace();
    				} catch (NotASaiyanException e1) {
    					AAC.AAV.getExceptions().setText("Only a Saiyan can learn this attack");
    					e1.printStackTrace();
    				}
                      
                  }
                  
              }
          }
		  
		
		
	}

}
