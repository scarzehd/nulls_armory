package com.scarzehd.nullsarmory;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModDamageTypes {
    public static void registerDamageTypes() {}

    private static RegistryKey<DamageType> register(String id) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(NullsArmory.MOD_ID, id));
    }

    public static RegistryKey<DamageType> BLOOD_RAGE = register("blood_rage");

    public static RegistryKey<DamageType> BLEED = register("bleed");
}
