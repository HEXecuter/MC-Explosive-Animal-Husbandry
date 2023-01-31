package com.hexecuter.minecraft.explosiveanimalhusbandry;

import com.hexecuter.minecraft.explosiveanimalhusbandry.listeners.EntityBreedEventListener;
import org.bukkit.plugin.java.JavaPlugin;


public final class ExplosiveAnimalHusbandry extends JavaPlugin {
    public static ExplosiveAnimalHusbandry instance;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new EntityBreedEventListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
