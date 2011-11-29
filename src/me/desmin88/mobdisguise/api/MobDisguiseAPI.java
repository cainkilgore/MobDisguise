package me.desmin88.mobdisguise.api;

import me.desmin88.mobdisguise.MobDisguise;
import me.desmin88.mobdisguise.api.event.DisguiseAsMobEvent;
import me.desmin88.mobdisguise.api.event.DisguiseAsPlayerEvent;
import me.desmin88.mobdisguise.api.event.UnDisguiseEvent;
import me.desmin88.mobdisguise.utils.MobIdEnum;
import net.minecraft.server.DataWatcher;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Basic API to disguise players.
 * 
 * @author desmin88
 * @author iffa
 *
 */
public class MobDisguiseAPI {
	/**
	 * Disguises a player as a player.
	 * 
	 * @param p Player to disguise
	 * @param name Player name to disguise as
	 * 
	 * @return true if succesful
	 */
	public static boolean disguisePlayerAsPlayer(Player p, String name) {
		if (isDisguised(p)) {
			return false;
		}
		if (name.length() > 16) {
			System.out.println(MobDisguise.pref + "Error, some other plugin is setting a name over 16 characters, truncating.");
			String tmp = name.substring(0, 16);
			name = tmp;
		}
		/* Listener notify start */
		DisguiseAsPlayerEvent e = new DisguiseAsPlayerEvent("DisguiseAsPlayerEvent", p, name);
		Bukkit.getServer().getPluginManager().callEvent(e);
		if(e.isCancelled()){
			return false;
		}
		/* Listener notify end */
		MobDisguise.apiList.add(p.getName());
		MobDisguise.disList.add(p.getName());
		MobDisguise.playerdislist.add(p.getName());
		MobDisguise.pu.disguisep2pToAll(p, name);
		MobDisguise.p2p.put(p.getName(), name);
		return true;
	}

	/**
	 * Undisguises a player who is disguised as another player.
	 * @param p Player to undisguise
	 * @param name ???
	 * @return true if successful
	 */
	public static boolean undisguisePlayerAsPlayer(Player p, String name) {
		if (!isDisguised(p)) {
			return false;
		}
		/* Listener notify start */
		UnDisguiseEvent e = new UnDisguiseEvent("UnDisguiseEvent", p, false);
		Bukkit.getServer().getPluginManager().callEvent(e);
		if(e.isCancelled()){
			return false;
		}
		/* Listener notify end */
		MobDisguise.apiList.remove(p.getName());
		MobDisguise.disList.remove(p.getName());
		MobDisguise.playerdislist.remove(p.getName());
		MobDisguise.pu.undisguisep2pToAll(p);
		MobDisguise.p2p.put(p.getName(), null);
		return true;
	}

	/**
	 * Disguises a player as a mob.
	 * 
	 * @param p Player to disguise
	 * @param mobtype Mob to disguise as
	 * 
	 * @return true if successful
	 */
	public static boolean disguisePlayer(Player p, String mobtype) {
		if (!MobIdEnum.map.containsKey(mobtype)) {
			return false;
		}
		if (isDisguised(p)) {
			return false;
		}
		/* Listener notify start */
		DisguiseAsMobEvent e = new DisguiseAsMobEvent("DisguiseAsMobEvent", p, mobtype);
		Bukkit.getServer().getPluginManager().callEvent(e);
		if(e.isCancelled()){
			return false;
		}
		/* Listener notify end */
		MobDisguise.apiList.add(p.getName());
		MobDisguise.disList.add(p.getName());
		MobDisguise.playerMobId.put(p.getName(), (byte) MobIdEnum.map.get(mobtype).intValue());
		MobDisguise.playerEntIds.add(Integer.valueOf(p.getEntityId()));
		MobDisguise.pu.disguiseToAll(p);
		return true;
	}

	/**
	 * Undisguises a player who is disguised as a mob.
	 * 
	 * @param p Player to undisguise
	 *  
	 * @return true if successful
	 */
	public static boolean undisguisePlayer(Player p) {
		if (!isDisguised(p)) {
			return false;
		}
		/* Listener notify start */
		UnDisguiseEvent e = new UnDisguiseEvent("UnDisguiseEvent", p, true);
		Bukkit.getServer().getPluginManager().callEvent(e);
		if(e.isCancelled()){
			return false;
		}
		/* Listener notify end */
		MobDisguise.pu.undisguiseToAll(p);
		MobDisguise.disList.remove(p.getName());
		MobDisguise.apiList.remove(p.getName());
		MobDisguise.playerMobId.put(p.getName(), null);
		MobDisguise.playerEntIds.remove(Integer.valueOf(p.getEntityId()));
		return true;

	}

	/**
	 * Checks if a player is disguised.
	 * 
	 * @param p Player
	 * 
	 * @return true if disguised
	 */
	public static boolean isDisguised(Player p) {
		return MobDisguise.disList.contains(p.getName());
	}
	
	 /**
     * Gets a Player's DataWatcher
     * 
     * @param p Player
     * 
     * @return Datawatcher 
     */
    public static DataWatcher getPlayerDataWatcher(Player p) {
        if(MobDisguise.data.get(p.getName()) == null) {
            return null;
        }
        else {
            return MobDisguise.data.get(p.getName());
        }
    }

}
