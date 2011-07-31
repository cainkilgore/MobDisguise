package me.desmin88.mobdisguise.commands;

import me.desmin88.mobdisguise.MobDisguise;
import me.desmin88.mobdisguise.utils.MobIdEnum;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class MDCommand implements CommandExecutor {
	 
    @SuppressWarnings("unused")
    private final MobDisguise plugin;

    public MDCommand(MobDisguise instance) {
        this.plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (sender instanceof Player) {
            Player s = (Player) sender;
            if (args.length == 0) {
                s.sendMessage(MobDisguise.pref + "You can't have zero arguments!");
                return true;
            }

            if (args[0].equalsIgnoreCase("undisguise")) {
                MobDisguise.pu.undisguiseToAll(s);
                MobDisguise.disList.remove(s.getName());
                MobDisguise.playerMobId.put(s.getName(), null);
                MobDisguise.playerEntIds.remove(Integer.valueOf(s.getEntityId()));
                s.sendMessage(MobDisguise.pref + "You have been changed back!");
                return true;
            }

            if (args[0].equalsIgnoreCase("types")) {
                for (String key : MobIdEnum.map.keySet()) {
                    s.sendMessage(MobDisguise.pref + key);
                }
            }

            if (args[0].equalsIgnoreCase("disguise")) {
                if(args.length != 2) {
                    s.sendMessage(MobDisguise.pref + "No mobtype specified!");
                    return true;
                }
                String mobtype = args[1].toLowerCase();
                if (!MobIdEnum.map.containsKey(mobtype)) {
                    s.sendMessage(MobDisguise.pref + "Invalid mob type!");
                    return true;
                }
                
                if(MobDisguise.PermissionCheck("mobdisguise.use", s) && !s.isOp()){
                    if(!MobDisguise.PermissionCheck("mobdiguise." + mobtype, s) ) {
                        s.sendMessage(MobDisguise.pref + "You don't have permission for this mob!");
                        return true;
                    }
                }
                if(!MobDisguise.PermissionCheck("mobdisguise.use", s) && !s.isOp()) {
                    s.sendMessage(MobDisguise.pref + "You are not an OP and perms are disabled!");
                    return true;
                }
                if (!MobDisguise.cfg.getBoolean("BlackList." + mobtype, true)) {
                    s.sendMessage(MobDisguise.pref + "This mob type has been restricted!");
                    return true;
                }
                MobDisguise.disList.add(s.getName());
                MobDisguise.playerMobId.put(s.getName(), (byte) MobIdEnum.map.get(mobtype).intValue());
                MobDisguise.playerEntIds.add(Integer.valueOf(s.getEntityId()));
                MobDisguise.pu.disguiseToAll(s);
                s.sendMessage(MobDisguise.pref + "You have been disguised as a " + args[1].toLowerCase() + "!");
                return true;
            }

        }
        return false;
    }
}
