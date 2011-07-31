package me.desmin88.mobdisguise.utils;

import me.desmin88.mobdisguise.MobDisguise;

import org.bukkit.entity.Player;

public class DisguiseTask implements Runnable {
    public MobDisguise plugin;

    public DisguiseTask(MobDisguise instance) {
        plugin = instance;
    }

    @Override
    public void run() {
        for (String s : MobDisguise.disList) {
            MobDisguise.pu.disguiseToAll(plugin.getServer().getPlayer(s));
        }
    }
}
