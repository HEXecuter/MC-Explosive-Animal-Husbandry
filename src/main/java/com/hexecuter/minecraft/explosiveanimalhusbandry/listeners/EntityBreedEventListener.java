package com.hexecuter.minecraft.explosiveanimalhusbandry.listeners;

import com.hexecuter.minecraft.explosiveanimalhusbandry.ExplosiveAnimalHusbandry;
import com.hexecuter.minecraft.explosiveanimalhusbandry.helpers.HelperFunctions;
import org.bukkit.Bukkit;
import org.bukkit.entity.*;
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
        LivingEntity player = event.getBreeder();
        LivingEntity father = event.getFather();
        LivingEntity mother = event.getMother();
        boolean isAnimal = baby instanceof Animals;
        boolean isPlayer = player instanceof Player;

        if (!isAnimal || !isPlayer) return;

        ((Player) player).sendTitle("Ewwww That's Gross", "No holding hands before marriage!", 10, 30, 10);
        Bukkit.getScheduler().runTaskLater(ExplosiveAnimalHusbandry.instance, () -> {
            ArrayList<ItemStack> lootFromEntity = HelperFunctions.getLootList((Mob) baby);
            HelperFunctions.createExplosions(baby.getLocation(), 3);
            HelperFunctions.spawnItems(baby.getLocation(), lootFromEntity);
            HelperFunctions.lightningStrike(baby.getLocation(), 5);
            baby.damage(100);

            ((Breedable) father).setBreed(true);
            ((Breedable) mother).setBreed(true);
        }, 60L);
    }
}
