package me.desmin88.mobdisguise.listeners;

import me.desmin88.mobdisguise.MobDisguise;
import me.desmin88.mobdisguise.utils.RealDropsUtils;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;


public class MDEntityListener extends EntityListener{
    @SuppressWarnings("unused")
    private final MobDisguise plugin;
    public MDEntityListener(MobDisguise instance) {
        this.plugin = instance;
    }
    
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity() instanceof Player) {
            Player p = (Player) event.getEntity();
            if(MobDisguise.cfg.getBoolean("RealDrops.enabled", false)) {
                event.getDrops().clear();
                if (RealDropsUtils.getDrop(p) != null)
                    p.getWorld().dropItemNaturally(p.getLocation(), RealDropsUtils.getDrop(p));
                
            }
        }
    }
}