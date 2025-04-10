package com.scarzehd.nullsarmory;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static void registerTags() {
        NullsArmory.LOGGER.info("Registering Mod Tags");
    }

    public static final TagKey<EntityType<?>> NETHER_MOBS = TagKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(NullsArmory.MOD_ID, "nether_mobs"));

    public static final TagKey<DamageType> BYPASSES_SHIELDS = TagKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(NullsArmory.MOD_ID, "bypasses_shields"));
}
