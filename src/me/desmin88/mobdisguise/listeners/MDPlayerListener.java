package me.desmin88.mobdisguise.listeners;

import me.desmin88.mobdisguise.MobDisguise;
import me.desmin88.mobdisguise.utils.DisguiseTask;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class MDPlayerListener extends PlayerListener {
    
    private final MobDisguise plugin;

    public MDPlayerListener(MobDisguise instance) {
        this.plugin = instance;
    }

    public void onPlayerPickupItem(PlayerPickupItemEvent event){
        if(MobDisguise.disList.contains(event.getPlayer().getName()) && MobDisguise.cfg.getBoolean("DisableItemPickup.enabled", true) && !MobDisguise.playerdislist.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
        }
    }
    
    
    public void onPlayerQuit(final PlayerQuitEvent event) {
        if(MobDisguise.disList.contains(event.getPlayer().getName())) {
            MobDisguise.playerEntIds.remove(event.getPlayer().getEntityId());
            //Should fix the "carcass" mob when disguised
           Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
               public void run() {
                   MobDisguise.pu.killCarcass(event.getPlayer());
                }
            }, 5);
            
        }
    }
    
    //Waiting for my stinking pull.
    public void onPlayerAnimation(PlayerAnimationEvent event) {
        if (MobDisguise.disList.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
            return;
        }
    }

    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (MobDisguise.disList.contains(event.getPlayer().getName())) {
            event.setCancelled(true);
            return;
        }
    }
    
    
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if(MobDisguise.telelist.contains(event.getPlayer().getName())) {
            MobDisguise.telelist.remove(event.getPlayer().getName());
            return;
        }
        if (!MobDisguise.disList.contains(event.getPlayer().getName())) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new DisguiseTask(plugin), 8);
        }
        if (MobDisguise.disList.contains(event.getPlayer().getName())) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new DisguiseTask(plugin), 8);
            if(!MobDisguise.apiList.contains(event.getPlayer().getName())) {
                event.getPlayer().sendMessage(MobDisguise.pref + "You have been disguised because you teleported");
            }
        }
    }
    
    
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
       
        if (!MobDisguise.disList.contains(event.getPlayer().getName())) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new DisguiseTask(plugin), 8 );
        }
        if (MobDisguise.disList.contains(event.getPlayer().getName())) {
            if(!MobDisguise.apiList.contains(event.getPlayer().getName())) {
                event.getPlayer().sendMessage(MobDisguise.pref + "You have been disguised because you died");
            }
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new DisguiseTask(plugin), 8 );
        }
    }

    public void onPlayerJoin(PlayerJoinEvent event) {
        if (MobDisguise.disList.contains(event.getPlayer().getName())) {
            MobDisguise.telelist.add(event.getPlayer().getName());
            MobDisguise.playerEntIds.add(Integer.valueOf(event.getPlayer().getEntityId()));
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new DisguiseTask(plugin), 20);
            if(!MobDisguise.apiList.contains(event.getPlayer().getName())) {
                event.getPlayer().sendMessage(MobDisguise.pref + "You have been disguised because you relogged");
            }
        }
        if(!MobDisguise.disList.contains(event.getPlayer().getName())) {
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new DisguiseTask(plugin), 20 );
        }
        
    }
}
