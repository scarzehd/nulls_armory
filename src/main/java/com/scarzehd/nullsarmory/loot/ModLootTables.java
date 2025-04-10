package com.scarzehd.nullsarmory.loot;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.item.ModItems;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.block.Blocks;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.LootConditionTypes;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTables {
    private static RegistryKey<LootTable> register(String id) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(NullsArmory.MOD_ID, id));
    }

    public static RegistryKey<LootTable> SCOUT_SALVAGE_TABLE = register("scout_salvage");

    public static void modifyLootTables() {
        LootTableEvents.MODIFY.register(((registryKey, builder, source, wrapperLookup) -> {
            if (source.isBuiltin()) {
                if (registryKey.equals(LootTables.TRIAL_CHAMBERS_REWARD_CHEST)) {
                    LootPool.Builder poolBuilder = LootPool.builder().with(LootTableEntry.builder(SCOUT_SALVAGE_TABLE));
                    builder.pool(poolBuilder);
                }
                if (registryKey.equals(LootTables.JUNGLE_TEMPLE_CHEST)) {
                    LootPool.Builder poolBuilder = LootPool.builder().with(ItemEntry.builder(ModItems.SHIELD_BELT).conditionally(RandomChanceLootCondition.builder(.7f)));
                    builder.pool(poolBuilder);
                }
                if (registryKey.equals(LootTables.ABANDONED_MINESHAFT_CHEST)) {
                    LootPool.Builder poolBuilder = LootPool.builder().with(ItemEntry.builder(ModItems.SHIELD_BELT).conditionally(RandomChanceLootCondition.builder(0.1f)));
                    builder.pool(poolBuilder);
                }
                if (registryKey.equals(LootTables.ANCIENT_CITY_CHEST)) {
                    LootPool.Builder poolBuilder = LootPool.builder().with(ItemEntry.builder(ModItems.SHIELD_BELT).conditionally(RandomChanceLootCondition.builder(0.15f)));
                    builder.pool(poolBuilder);
                }
            }
        }));
    }
}
