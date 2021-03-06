package dragonball.controller;

import java.io.Serializable;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FighterSuperListListener implements ListSelectionListener, Serializable{

	AssignAttackController AAC;
	public  FighterSuperListListener (AssignAttackController AAC)
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
            	  AAC.getGC().getGame().getPlayer().removeSuperAttack(AAC.getGC().getGame()
            			  .getPlayer().getActiveFighter().getSuperAttacks().get(i));
            	  AAC.AAV.updatePlayerSuper();
            	  AAC.AAV.updateFighterSuper();
                  
              }
          }
		  
		
		
	}

}

