package com.hexecuter.minecraft.explosiveanimalhusbandry.listeners;

import com.hexecuter.minecraft.explosiveanimalhusbandry.ExplosiveAnimalHusbandry;
import com.hexecuter.minecraft.explosiveanimalhusbandry.helpers.HelperFunctions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
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

        Bukkit.getScheduler().runTaskLater(ExplosiveAnimalHusbandry.instance, () -> {
            ArrayList<ItemStack> lootFromEntity = HelperFunctions.getLootList((Animals) baby);
            HelperFunctions.createExplosions(baby.getLocation(), 3);
            HelperFunctions.spawnItems(baby.getLocation(), lootFromEntity);
            baby.damage(100);

            player.sendMessage("No holding hands before marriage!");
            ((Breedable) father).setBreed(true);
            ((Breedable) mother).setBreed(true);
        }, 60L);
    }
}
