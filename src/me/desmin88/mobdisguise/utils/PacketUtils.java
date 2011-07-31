package me.desmin88.mobdisguise.utils;

import java.lang.reflect.Field;

import me.desmin88.mobdisguise.MobDisguise;
import net.minecraft.server.DataWatcher;
import net.minecraft.server.MathHelper;
import net.minecraft.server.Packet20NamedEntitySpawn;
import net.minecraft.server.Packet24MobSpawn;
import net.minecraft.server.Packet29DestroyEntity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class PacketUtils {
    
    public PacketUtils() {}

    
    public void killCarcass(Player p1) {
        //Make packets out of loop!
        CraftPlayer p22 = (CraftPlayer) p1;
        Packet29DestroyEntity p29 = new Packet29DestroyEntity(p22.getEntityId());
        for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
            if (p2 == p1) {
                continue;
            }
            ((CraftPlayer) p2).getHandle().netServerHandler.sendPacket(p29);
            
        }
    }
    
    public void undisguiseToAll(Player p1) {
        //Make packets out of loop!
        CraftPlayer p22 = (CraftPlayer) p1;
        Packet29DestroyEntity p29 = new Packet29DestroyEntity(p22.getEntityId());
        Packet20NamedEntitySpawn p20 = new Packet20NamedEntitySpawn(p22.getHandle());
        
        for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
            if (p2 == p1) {
                continue;
            }
            ((CraftPlayer) p2).getHandle().netServerHandler.sendPacket(p29);
            ((CraftPlayer) p2).getHandle().netServerHandler.sendPacket(p20);
        }
    }
    
    public void disguiseToAll(Player p1) {
        //Make packets out of loop!
        Packet24MobSpawn p24 = packetMaker(p1, MobDisguise.playerMobId.get(p1.getName()));
        for (Player p2 : Bukkit.getServer().getOnlinePlayers()) {
            if (p2 == p1) {
                continue;
            }
            ((CraftPlayer) p2).getHandle().netServerHandler.sendPacket(p24);
        }
    }

    public Packet24MobSpawn packetMaker(Player p1, Byte id) {
        Location loc = p1.getLocation();
        Packet24MobSpawn packet = new Packet24MobSpawn();
        packet.a = ((CraftPlayer) p1).getEntityId();
        packet.b = id.byteValue();
        packet.c = MathHelper.floor(loc.getX() * 32.0D);
        packet.d = MathHelper.floor(loc.getY() * 32.0D);
        packet.e = MathHelper.floor(loc.getZ() * 32.0D);
        packet.f = (byte) ((int) loc.getYaw() * 256.0F / 360.0F);
        packet.g = (byte) ((int) (loc.getPitch() * 256.0F / 360.0F));
        Field datawatcher;
        try {
            datawatcher = packet.getClass().getDeclaredField("h");
            datawatcher.setAccessible(true);
            datawatcher.set(packet, new DataWatcher());
            datawatcher.setAccessible(false);
        } catch (Exception e) {
            System.out.println(MobDisguise.pref + "Error making packet?!");
            return null;
        } 
        return packet;
    }

}
