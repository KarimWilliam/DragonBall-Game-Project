package dragonball.controller;

import java.io.Serializable;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FighterUltimateListListener implements ListSelectionListener, Serializable {

	AssignAttackController AAC;
	public  FighterUltimateListListener (AssignAttackController AAC)
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
            	  AAC.getGC().getGame().getPlayer().removeUltimateAttack(AAC.getGC().getGame()
            			  .getPlayer().getActiveFighter().getUltimateAttacks().get(i));
            	  AAC.AAV.updateFighterUltimate();
            	  AAC.AAV.updateFighterSuper();
              }
          }
		  
		
		
	}

}
