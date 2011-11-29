package me.desmin88.mobdisguise.api.event;

import org.bukkit.entity.Player;

/**
 * Event data for when a player disguises as another player.
 * 
 * @author iffa
 *
 */
public class DisguiseAsPlayerEvent extends DisguiseEvent {
	private static final long serialVersionUID = 8569104603707264482L;
	private String name;

	public DisguiseAsPlayerEvent(String event, Player player, String name) {
		super(event, player);
		this.name = name;
	}
	
	/**
	 * Gets the player name the player is disguising as.
	 * 
	 * @return Player name
	 */
	public String getName() {
		return this.name;
	}

}
