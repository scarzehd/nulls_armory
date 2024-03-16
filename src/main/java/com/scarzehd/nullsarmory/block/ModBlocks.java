package com.scarzehd.nullsarmory.block;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.item.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static void registerBlocks() {
        NullsArmory.LOGGER.info("Registering Mod Blocks");
    }

    public static Block registerBlock(String name, Block block) {
        Block registered_block = Registry.register(Registries.BLOCK, new Identifier(NullsArmory.MOD_ID, name), block);
        ModItems.registerItem(name, new BlockItem(registered_block, new FabricItemSettings()));
        return registered_block;
    }

    public static final Block VOID_ORE = registerBlock("void_ore", new Block(FabricBlockSettings.create().hardness(4.5f).resistance(-1f))); // WHY is it .create() but FabricItemSettings is just a regular-ass constructor AAAAGH

    public static final Block ELDER_BLOCK = registerBlock("elder_block", new Block(FabricBlockSettings.create().hardness(5).resistance(-1)));

    public static final  Block ELDRITCH_LECTERN =registerBlock("eldritch_lectern", new Block(FabricBlockSettings.create().hardness(3).resistance(-1))); // this is gonna be the main fuctional block of elder ore
    //note to self: looks weird in hand but fine when placed, find out why from ezra
}
