package me.ezpzstreamz.rde;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class RandomDamageEffect extends JavaPlugin implements Listener {

    FileConfiguration config;

    int timeMin;
    int timeMax;
    int ampMin;
    int ampMax;

    @Override
    public void onEnable() {
        super.onEnable();
        getLogger().info("Potion affects will be applied upon taking damage.");
        this.saveDefaultConfig();
        config = getConfig();
        timeMin = config.getInt("potion.time.min");
        timeMax = config.getInt("potion.time.max");
        ampMin = config.getInt("potion.amp.min");
        ampMax = config.getInt("potion.amp.max");
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if(e instanceof Player) {
            int index = (int)(Math.random() * (PotionEffectType.values().length));
            int time = (int)(Math.random() * (timeMax - timeMin + 1) + timeMin);
            int amp = (int)(Math.random() * (ampMax - ampMin + 1) + ampMin);
            ((Player) e).addPotionEffect(new PotionEffect(PotionEffectType.values()[index], time, amp, true, true));
        }
    }


}
