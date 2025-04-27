package com.scarzehd.nullsarmory.components;

import com.scarzehd.nullsarmory.attribute.ModAttributes;
import com.scarzehd.nullsarmory.sound.ModSounds;
import com.scarzehd.nullsarmory.sound.RechargeSoundHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundCategory;

public class ShieldsComponent implements IShieldsComponent {
    private double shields = 0;
    private int rechargeDelay = 0;

    private int rechargeTimer = 0;

    private final LivingEntity provider;

    public ShieldsComponent(Object provider) {
        this.provider = (LivingEntity)provider;
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
                rechargeTimer = (int)Math.max(Math.round(provider.getAttributeValue(ModAttributes.SHIELDS_RECHARGE_RATE)), 1);
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
        rechargeDelay = Math.max(1, delay);
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

        if (shields == oldShields) return;

        if (shields < oldShields)
            RechargeSoundHandler.instance.stop();

        if (shields >= provider.getAttributeValue(ModAttributes.MAX_SHIELDS)) {
            provider.getWorld().playSound(provider, provider.getBlockPos(), ModSounds.SHIELDS_RECHARGE_COMPLETE, SoundCategory.PLAYERS, 1.0f, 1.0f);
            RechargeSoundHandler.instance.stop();
        } else if (shields > oldShields) {
            if (provider instanceof PlayerEntity player) {
                if (player.isMainPlayer()) {
                    RechargeSoundHandler.instance.start();
                }
            }
        } else if (shields <= 0 && shields < oldShields) {
            provider.getWorld().playSound(provider, provider.getBlockPos(), ModSounds.SHIELDS_BREAK, SoundCategory.PLAYERS, 1.0f, 1.0f);
        }
    }
}
