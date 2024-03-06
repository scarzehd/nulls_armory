package com.scarzehd.otherworldldlyweapons;

import com.scarzehd.otherworldldlyweapons.block.ModBlocks;
import com.scarzehd.otherworldldlyweapons.item.ModItems;
import com.scarzehd.otherworldldlyweapons.world.ModWorldGen;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtherworldlyWeapons implements ModInitializer {
	public static final String MOD_ID = "otherworldly-weapons";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModWorldGen.registerWorldGen();
		ModTags.registerTags();
	}
}