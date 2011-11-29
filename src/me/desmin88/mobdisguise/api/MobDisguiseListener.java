package me.desmin88.mobdisguise.api;

import me.desmin88.mobdisguise.api.event.DisguiseAsMobEvent;
import me.desmin88.mobdisguise.api.event.DisguiseAsPlayerEvent;
import me.desmin88.mobdisguise.api.event.DisguiseCommandEvent;
import me.desmin88.mobdisguise.api.event.UnDisguiseEvent;

import org.bukkit.event.CustomEventListener;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

/**
 * MobDisguiseListener for events of MobDisguise.
 * 
 * @author iffa
 * 
 */
public class MobDisguiseListener extends CustomEventListener implements
		Listener {
	/**
	 * Called when a player disguises as a mob.
	 * 
	 * @param event
	 *            Event data
	 */
	public void onDisguiseAsMob(DisguiseAsMobEvent event) {
	}

	/**
	 * Called when a player disguises as another player.
	 * 
	 * @param event
	 *            Event data
	 */
	public void onDisguiseAsPlayer(DisguiseAsPlayerEvent event) {
	}

	/**
	 * Called when a player undisguises.
	 * 
	 * @param event
	 *            Event data
	 */
	public void onUnDisguise(UnDisguiseEvent event) {
	}

	/**
	 * Called when the MobDisguise command is used.
	 * 
	 * @param event
	 *            Event data
	 */
	public void onMobDisguiseCommand(DisguiseCommandEvent event) {
	}

	/**
	 * Handles the events.
	 * 
	 * @param event
	 *            Event data
	 */
	@Override
	public void onCustomEvent(Event event) {
		if (event instanceof DisguiseAsMobEvent) {
			onDisguiseAsMob((DisguiseAsMobEvent) event);
		} else if (event instanceof DisguiseAsPlayerEvent) {
			onDisguiseAsPlayer((DisguiseAsPlayerEvent) event);
		} else if (event instanceof UnDisguiseEvent) {
			onUnDisguise((UnDisguiseEvent) event);
		} else if (event instanceof DisguiseCommandEvent) {
			onMobDisguiseCommand((DisguiseCommandEvent) event);
		}
	}
}
