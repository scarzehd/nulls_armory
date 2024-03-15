package com.scarzehd.otherworldlyweapons.block;

import com.scarzehd.otherworldlyweapons.OtherworldlyWeapons;
import com.scarzehd.otherworldlyweapons.item.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static void registerBlocks() {
        OtherworldlyWeapons.LOGGER.info("Registering Mod Blocks");
    }

    public static Block registerBlock(String name, Block block) {
        Block registered_block = Registry.register(Registries.BLOCK, new Identifier(OtherworldlyWeapons.MOD_ID, name), block);
        ModItems.registerItem(name, new BlockItem(registered_block, new FabricItemSettings()));
        return registered_block;
    }

    public static final Block VOID_ORE = registerBlock("void_ore", new Block(FabricBlockSettings.create().hardness(4.5f).resistance(-1f))); // WHY is it .create() but FabricItemSettings is just a regular-ass constructor AAAAGH
}
