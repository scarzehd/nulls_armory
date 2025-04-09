package com.scarzehd.nullsarmory.sound;

import com.scarzehd.nullsarmory.NullsArmory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static void registerSounds() {}

    private static SoundEvent registerSound(String id) {
        Identifier identifier = Identifier.of(NullsArmory.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, Identifier.of(NullsArmory.MOD_ID, id), SoundEvent.of(identifier));
    }

    public static final SoundEvent SHIELDS_BREAK = registerSound("shields_break");
    public static final SoundEvent SHIELDS_RECHARGE = registerSound("shields_recharge");
    public static final SoundEvent SHIELDS_RECHARGE_COMPLETE = registerSound("shields_recharge_complete");
}
