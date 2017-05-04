package dragonball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import dragonball.view.AssignAttackView;
import dragonball.view.ImageStorage;

public class AssignAttackController implements ActionListener {

	GameController GC;
	AssignAttackView AAV;
	
	public AssignAttackController(GameController GC,ImageStorage IS)
	{
		this.GC = GC;
		 AAV = new AssignAttackView(this,IS);
		
	}
	
	
	
	
	
	
	
	
	
	public GameController getGC() {
		return GC;
	}









	@Override
	public void actionPerformed(ActionEvent e) {
		GC.getWorldview().setVisible(true);
		AAV.setVisible(false);
		
	}
}
