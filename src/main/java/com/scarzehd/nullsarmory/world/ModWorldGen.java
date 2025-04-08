package com.scarzehd.nullsarmory.world;

import com.scarzehd.nullsarmory.NullsArmory;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class ModWorldGen {
    public static void registerWorldGen() {
        NullsArmory.LOGGER.info("Register Mod Worldgen");
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, VOID_ORE_PLACED_KEY);
    }

    public static final RegistryKey<PlacedFeature> VOID_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(NullsArmory.MOD_ID, "void_ore"));
}
