package com.hexecuter.minecraft.explosiveanimalhusbandry.listeners;

import com.hexecuter.minecraft.explosiveanimalhusbandry.helpers.HelperFunctions;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityBreedEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class EntityBreedEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityBreedEvent(EntityBreedEvent event) {
        LivingEntity baby = event.getEntity();
        boolean isAnimal = baby instanceof Animals;

        if (!isAnimal) return;

        ArrayList<ItemStack> lootFromEntity = HelperFunctions.getLootList((Animals) baby);
        HelperFunctions.createExplosions(baby.getLocation(), 5);


    }
}
