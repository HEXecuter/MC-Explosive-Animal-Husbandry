package com.hexecuter.minecraft.explosiveanimalhusbandry.helpers;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;

import java.util.Collection;
import java.util.Random;

public class HelperFunctions {
    public static Collection<ItemStack> getLootList(Animals entity) {
        LootContext.Builder lootContextBuilder = new LootContext.Builder(entity.getLocation());
        lootContextBuilder.lootingModifier(10);
        lootContextBuilder.lootedEntity(entity);
        LootContext lootContext = lootContextBuilder.build();

        return entity.getLootTable().populateLoot(new Random(), lootContext);
    }

    public static void createExplosions(Location location, int count) {
        World world = location.getWorld();
        world.spawnParticle(Particle.EXPLOSION_NORMAL, location, count, 2, 2, 2);
        world.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
    }
}
