package dragonball.controller;

import java.io.Serializable;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dragonball.view.WorldView;

public class ListListener implements ListSelectionListener, Serializable {

	GameController GC;
	public  ListListener(GameController GC)
	{
		this.GC = GC;
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
            	  GC.getGame().getPlayer().setActiveFighter(GC.getGame().getPlayer().getFighters().get(i));
        		  GC.MiniUpdateWorld();
                  
              }
          }
		  
		
		
	}

}
