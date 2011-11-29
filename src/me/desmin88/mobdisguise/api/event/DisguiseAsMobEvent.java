package me.desmin88.mobdisguise.api.event;

import org.bukkit.entity.Player;

/**
 * Event data for when a player disguises as a mob.
 * @author iffa
 *
 */
public class DisguiseAsMobEvent extends DisguiseEvent {
	private static final long serialVersionUID = 1706630423687514665L;
	private String mobtype;

	public DisguiseAsMobEvent(String event, Player player, String mobtype) {
		super(event, player);
		this.mobtype = mobtype;
	}
	
	/**
	 * Gets the mobtype the player is disguising as.
	 * 
	 * @return Mobtype
	 */
	public String getMobType() {
		return this.mobtype;
	}

}
