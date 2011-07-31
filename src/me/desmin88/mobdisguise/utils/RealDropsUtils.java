package me.desmin88.mobdisguise.utils;

import java.util.Random;

import me.desmin88.mobdisguise.MobDisguise;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RealDropsUtils {
    private static final Random r = new Random();
    
    public static ItemStack getDrop(Player p) {
        switch(MobDisguise.playerMobId.get(p)) {
        case 50: //Creeper
            return new ItemStack(Material.SULPHUR, r.nextInt(2));
        case 51: // Skeleton
            if(r.nextInt(1) == 0)
                return new ItemStack(Material.ARROW, r.nextInt(2));
            else
                return new ItemStack(Material.BONE, r.nextInt(2));
        case 52: // Spider
            return new ItemStack(Material.STRING, r.nextInt(2));
        case 53: // Giant
            return null;
        case 54: // Zombie
            return new ItemStack(Material.FEATHER, r.nextInt(2));
        case 55: // Slime
            return new ItemStack(Material.SLIME_BALL, r.nextInt(2));
        case 56: // Ghast
            return new ItemStack(Material.SULPHUR, r.nextInt(2));
        case 57: // Pigman
            return new ItemStack(Material.GRILLED_PORK, r.nextInt(3));
        case 90: // Pig
            return new ItemStack(Material.PORK, r.nextInt(2));
            
        case 91: // Sheep
            return new ItemStack(Material.WOOL, r.nextInt(2) + 2);
            
        case 92: // Cow
            return new ItemStack(Material.LEATHER, r.nextInt(3));
        case 93: // Chicken
            if(r.nextInt(8) != 8)
                return new ItemStack(Material.FEATHER, r.nextInt(2));
            else 
                return new ItemStack(Material.EGG, r.nextInt(1));
        case 94: // Squid   
            return new ItemStack(Material.INK_SACK, r.nextInt(2) + 1);
        case 95: // Wolf
            return null;
            
            
        }
        
        
        
        return new ItemStack(null);
        
    }
}
