package com.hexecuter.minecraft.explosiveanimalhusbandry.helpers;

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
}
