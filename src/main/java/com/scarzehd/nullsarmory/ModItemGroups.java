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
                entries.add(ModItems.ELDER_INGOT);
                entries.add(ModItems.KINETIC_CONVERTER);

                entries.add(ModItems.RISING_STAR_HELMET);
                entries.add(ModItems.RISING_STAR_CHESTPLATE);
                entries.add(ModItems.RISING_STAR_LEGGINGS);
                entries.add(ModItems.RISING_STAR_BOOTS);

                entries.add(ModBlocks.ELDER_BLOCK.asItem());
                entries.add(ModBlocks.VOID_ORE.asItem());
                entries.add(ModBlocks.ELDRITCH_LECTERN.asItem());
            }).build());

    public static void registerItemGroups() {
        NullsArmory.LOGGER.info("Registering Item Groups");
    }
}
