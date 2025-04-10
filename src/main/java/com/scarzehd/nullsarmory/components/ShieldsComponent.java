package com.scarzehd.nullsarmory.components;

import com.scarzehd.nullsarmory.sound.ModSounds;
import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.attribute.ModAttributes;
import com.scarzehd.nullsarmory.sound.ShieldsRechargeSound;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.sound.EntityTrackingSoundInstance;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class ShieldsComponent implements IShieldsComponent {
    private double shields = 0;
    private int rechargeDelay = 0;

    private int rechargeTimer = 0;

    private final LivingEntity provider;

    @Environment(EnvType.CLIENT)
    private ShieldsRechargeSound rechargeSound;

    public ShieldsComponent(Object provider) {
        this.provider = (LivingEntity)provider;
        rechargeSound = new ShieldsRechargeSound(1.0f, 1.0f, 1);
    }

    @Override
    public void readFromNbt(NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup) {
        shields = tag.getDouble("Shields");
        rechargeDelay = tag.getInt("RechargeDelay");
    }

    @Override
    public void writeToNbt(NbtCompound tag, RegistryWrapper.WrapperLookup wrapperLookup) {
        tag.putDouble("Shields", shields);
        tag.putInt("RechargeDelay", rechargeDelay);
    }

    @Override
    public void serverTick() {
        double maxShields = provider.getAttributeValue(ModAttributes.MAX_SHIELDS);

        if (rechargeDelay > 0) {
            rechargeDelay--;
        } else if (shields < maxShields) {
            if (rechargeTimer > 0) {
                rechargeTimer--;
            } else {
                setCurrentShields(shields + 1);
                rechargeTimer = (int)Math.round(provider.getAttributeValue(ModAttributes.SHIELDS_RECHARGE_RATE));
            }
        }

        if (shields > maxShields) {
            setCurrentShields(maxShields);
        }
    }

    @Override
    public double getCurrentShields() {
        return shields;
    }

    @Override
    public void setCurrentShields(double shields) {
        this.shields = Math.min(shields, provider.getAttributeValue(ModAttributes.MAX_SHIELDS));
        ModComponents.SHIELDS.sync(provider);
    }

    @Override
    public int getCurrentRechargeDelay() {
        return rechargeDelay;
    }

    @Override
    public void setCurrentRechargeDelay(int delay) {
        rechargeDelay = delay;
        ModComponents.SHIELDS.sync(provider);
    }

    @Override
    public void resetRechargeDelay() {
        setCurrentRechargeDelay((int)Math.round(provider.getAttributeValue(ModAttributes.SHIELDS_RECHARGE_DELAY)));
    }

    @Override
    public boolean isCharging() {
        return rechargeDelay <= 0;
    }

    @Override
    public void applySyncPacket(RegistryByteBuf buf) {
        double oldShields = shields;
        IShieldsComponent.super.applySyncPacket(buf);

//        if (!provider.getWorld().isClient()) return;

        SoundManager soundManager = MinecraftClient.getInstance().getSoundManager();

        if (shields == oldShields) return;

        if (shields < oldShields)
            soundManager.stopSounds(ModSounds.SHIELDS_RECHARGE.getId(), SoundCategory.PLAYERS);

        if (shields >= provider.getAttributeValue(ModAttributes.MAX_SHIELDS)) {
            soundManager.play(new PositionedSoundInstance(ModSounds.SHIELDS_RECHARGE_COMPLETE, SoundCategory.PLAYERS, 1f, 1f, Random.create(), new BlockPos(0, 0, 0)) {{
                this.relative = true;
            }}, 3);
            soundManager.stopSounds(ModSounds.SHIELDS_RECHARGE.getId(), SoundCategory.PLAYERS);
        } else if (shields > oldShields && !soundManager.isPlaying(rechargeSound)) {
            soundManager.play(rechargeSound);
        } else if (shields <= 0 && shields < oldShields) {
            provider.playSound(ModSounds.SHIELDS_BREAK);
//            soundManager.play(new PositionedSoundInstance(ModSounds.SHIELDS_BREAK, SoundCategory.PLAYERS, 1f, 1f, Random.create(), new BlockPos(0, 0, 0)) {{
//                this.relative = true;
//            }});
        }
    }
}
