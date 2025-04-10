package com.scarzehd.nullsarmory.sound;

import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class ShieldsRechargeSound extends PositionedSoundInstance {
    public ShieldsRechargeSound(float volume, float pitch, long seed) {
        super(ModSounds.SHIELDS_RECHARGE, SoundCategory.PLAYERS, volume, pitch, Random.create(), new BlockPos(0, 0, 0));
        this.repeat = true;
        this.relative = true;
    }

    public boolean shouldAlwaysPlay() {
        return true;
    }
}
