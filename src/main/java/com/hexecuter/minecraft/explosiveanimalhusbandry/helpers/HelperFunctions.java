package com.hexecuter.minecraft.explosiveanimalhusbandry.helpers;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Mob;
import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.LootContext;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class HelperFunctions {
    public static ArrayList<ItemStack> getLootList(Mob entity) {
        LootContext.Builder lootContextBuilder = new LootContext.Builder(entity.getLocation());
        lootContextBuilder.lootingModifier(10);
        lootContextBuilder.lootedEntity(entity);
        LootContext lootContext = lootContextBuilder.build();

        return new ArrayList<>(entity.getLootTable().populateLoot(new Random(), lootContext));
    }

    public static void createExplosions(Location location, int count) {
        World world = location.getWorld();
        world.spawnParticle(Particle.EXPLOSION_HUGE, location, count, 1, 0, 1);
        world.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
    }

    public static void spawnItems(Location location, ArrayList<ItemStack> itemsToSpawn) {
        World world = location.getWorld();
        Random random = new Random();
        int numOfDrops = random.nextInt(15) + 5;


        for (int i = 0; i < numOfDrops; i++) {
            ItemStack itemToDrop = getRandomItem(itemsToSpawn);
            itemToDrop.setAmount(random.nextInt(16) + 1);
            double xVelocity = random.nextDouble(.3) * (random.nextBoolean() ? -1 : 1);
            double zVelocity = random.nextDouble(.3) * (random.nextBoolean() ? -1 : 1);
            double yVelocity = 1;

            world.dropItem(location, itemToDrop).setVelocity(new Vector(xVelocity, yVelocity, zVelocity));
            ExperienceOrb xpOrb = (ExperienceOrb) world.spawnEntity(location, EntityType.EXPERIENCE_ORB);
            xpOrb.setExperience(10);
            xpOrb.setVelocity(new Vector(xVelocity, yVelocity, zVelocity));
        }

    }

    public static ItemStack getRandomItem(ArrayList<ItemStack> arrayList) {
        Random random = new Random();
        int randomIndex = random.nextInt(arrayList.size());
        return arrayList.get(randomIndex);
    }
}
