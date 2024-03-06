package com.scarzehd.otherworldldlyweapons;

import net.minecraft.entity.EntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static void registerTags() {
        OtherworldlyWeapons.LOGGER.info("Registering Mod Tags");
    }

    public static final TagKey<EntityType<?>> NETHER_MOBS = TagKey.of(RegistryKeys.ENTITY_TYPE, new Identifier(OtherworldlyWeapons.MOD_ID, "nether_mobs"));
}
