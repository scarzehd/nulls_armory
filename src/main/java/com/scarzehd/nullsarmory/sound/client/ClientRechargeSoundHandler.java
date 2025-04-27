package com.scarzehd.nullsarmory.sound.client;

import com.scarzehd.nullsarmory.sound.ModSounds;
import com.scarzehd.nullsarmory.sound.RechargeSoundHandler;
import com.scarzehd.nullsarmory.sound.ShieldsRechargeSound;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.sound.SoundCategory;

@Environment(EnvType.CLIENT)
public class ClientRechargeSoundHandler extends RechargeSoundHandler {
    private ShieldsRechargeSound rechargeSound;

    public ClientRechargeSoundHandler() {
        rechargeSound = new ShieldsRechargeSound(1.0f, 1.0f, 1);
    }

    @Override
    public void start() {
        SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
        if (soundManager.isPlaying(rechargeSound)) return;
        soundManager.play(rechargeSound);
    }

    @Override
    public void stop() {
        SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();
        soundManager.stopSounds(ModSounds.SHIELDS_RECHARGE.getId(), SoundCategory.PLAYERS);
    }
}
