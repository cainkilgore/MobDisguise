package me.desmin88.mobdisguise.utils;

import java.util.HashMap;
import java.util.Map;

public class MobIdEnum {
    public static String types = "creeper,skeleton,spider,giant,zombie,slime,pigman,pig,sheep,cow,chicken,squid,wolf,enderman,cavespider,silverfish";
    
    public static String getTypeFromByte(Integer in) {
        for(String s : map.keySet()) {
            if(map.get(s) == in) {
                return s;
            }
        }
        return null;
    }
    
    public static Map<String, Integer> map = new HashMap<String, Integer>();
    static {
        map.put("steve", 49);
        map.put("creeper", 50);
        map.put("skeleton", 51);
        map.put("spider", 52);
        map.put("giant", 53);
        map.put("zombie", 54);
        map.put("slime", 55);
        map.put("ghast", 56);
        map.put("pigman", 57);
        map.put("enderman", 58);
        map.put("cavespider", 59);
        map.put("silverfish", 60);
        map.put("pig", 90);
        map.put("sheep", 91);
        map.put("cow", 92);
        map.put("chicken", 93);
        map.put("squid", 94);
        map.put("wolf", 95);
        map.put("enderdragon", 63);
        map.put("snowgolem", 97);
        map.put("blaze", 61);
        map.put("magmacube", 62);
        map.put("mooshroom", 96);
        map.put("villager", 120);

    }
}
