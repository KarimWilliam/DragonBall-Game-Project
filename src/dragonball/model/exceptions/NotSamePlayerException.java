package dragonball.model.exceptions;

import dragonball.model.player.Player;

public class NotSamePlayerException extends DragonBallException {
	private Player player;
	
	public NotSamePlayerException(Player player)
	{
		
		super("we are starting a new game for you  because u are not " + player.getName());
		this.player = player;
	}
	
	public NotSamePlayerException(String message, Player player)
	{
		super(message);
		this.player = player;
		
	}

	public Player getPlayer() {
		return player;
	}
	

}
