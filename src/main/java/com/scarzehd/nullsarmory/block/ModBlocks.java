package com.scarzehd.nullsarmory.block;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static void registerBlocks() {
        NullsArmory.LOGGER.info("Registering Mod Blocks");
    }

    public static Block registerBlock(String name, Block block) {
        Block registered_block = Registry.register(Registries.BLOCK, Identifier.of(NullsArmory.MOD_ID, name), block);
        ModItems.registerItem(name, new BlockItem(registered_block, new Item.Settings()));
        return registered_block;
    }

    public static final Block VOID_ORE = registerBlock("void_ore", new Block(AbstractBlock.Settings.create().hardness(4.5f).resistance(-1f))); // WHY is it .create() but FabricItemSettings is just a regular-ass constructor AAAAGH

//    public static final Block ELDER_BLOCK = registerBlock("elder_block", new Block(AbstractBlock.Settings.create().hardness(5).resistance(-1)));

//    public static final  Block ELDRITCH_LECTERN = registerBlock("eldritch_lectern", new Block(AbstractBlock.Settings.create().hardness(3).resistance(-1))); // this is gonna be the main functional block of elder ore
    //note to self: looks weird in hand but fine when placed, find out why from ezra
}
