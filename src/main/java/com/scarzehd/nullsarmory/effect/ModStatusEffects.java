package com.scarzehd.nullsarmory.effect;

import com.scarzehd.nullsarmory.NullsArmory;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static void registerStatusEffects() {}

    private static RegistryEntry<StatusEffect> register(String id, StatusEffect effect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(NullsArmory.MOD_ID, id), effect);
    }

    public static final RegistryEntry<StatusEffect> BLEED = register("bleed", new BleedEffect());
}
