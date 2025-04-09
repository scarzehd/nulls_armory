package com.scarzehd.nullsarmory;

import com.scarzehd.nullsarmory.attribute.ModAttributes;
import com.scarzehd.nullsarmory.block.ModBlocks;
import com.scarzehd.nullsarmory.item.ModItems;
import com.scarzehd.nullsarmory.sound.ModSounds;
import com.scarzehd.nullsarmory.world.ModWorldGen;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NullsArmory implements ModInitializer {
	public static final String MOD_ID = "nulls_armory";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModArmorMaterials.registerArmorMaterials();
		ModItems.registerItems();
		ModBlocks.registerBlocks();
		ModWorldGen.registerWorldGen();
		ModTags.registerTags();
		ModAttributes.registerAttributes();
		ModItemGroups.registerItemGroups();
		ModSounds.registerSounds();
	}
}