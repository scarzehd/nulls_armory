package com.scarzehd.nullsarmory;

import com.scarzehd.nullsarmory.block.ModBlocks;
import com.scarzehd.nullsarmory.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
    public static final ItemGroup VOID_ITEMS = Registry.register(Registries.ITEM_GROUP, Identifier.of(NullsArmory.MOD_ID, "void_items"), FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.VOIDSHELL_INGOT))
            .displayName(Text.translatable("itemGroup.nulls_armory.void_items"))
            .entries((context, entries) -> {
                entries.add(ModItems.VOIDGEL_ORB);
                entries.add(ModItems.VOIDSHELL_INGOT);
                entries.add(ModItems.CRUCIBLE);
                entries.add(ModItems.ORTHOS_PRIME);
                entries.add(ModItems.SHIELD_BELT);
                entries.add(ModItems.TRUE_KNIFE);

                entries.add(ModItems.STARMAN_HELMET);
                entries.add(ModItems.STARMAN_CHESTPLATE);
                entries.add(ModItems.STARMAN_LEGGINGS);
                entries.add(ModItems.STARMAN_BOOTS);

                entries.add(ModItems.SCOUT_HELMET);
                entries.add(ModItems.SCOUT_CHESTPLATE);
                entries.add(ModItems.SCOUT_LEGGINGS);
                entries.add(ModItems.SCOUT_BOOTS);

                entries.add(ModItems.SCOUT_SALVAGE_HELMET);
                entries.add(ModItems.SCOUT_SALVAGE_CHESTPLATE);
                entries.add(ModItems.SCOUT_SALVAGE_LEGGINGS);
                entries.add(ModItems.SCOUT_SALVAGE_BOOTS);

                entries.add(ModItems.TRENCH_HELMET);
                entries.add(ModItems.TRENCH_CHESTPLATE);
                entries.add(ModItems.TRENCH_LEGGINGS);
                entries.add(ModItems.TRENCH_BOOTS);

                entries.add(ModBlocks.VOID_ORE.asItem());
                entries.add(ModBlocks.NULL_MARROW.asItem());
            }).build());

    public static void registerItemGroups() {
        NullsArmory.LOGGER.info("Registering Item Groups");
    }
}
