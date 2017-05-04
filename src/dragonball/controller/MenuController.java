package dragonball.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;

public class MenuController implements ActionListener  {
	
	private Game game;
	
	public MenuController () throws MissingFieldException, UnknownAttackTypeException
	{
		//game = new Game();
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		
	}

}
