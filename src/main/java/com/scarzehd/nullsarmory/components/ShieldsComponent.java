package com.scarzehd.nullsarmory.components;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.attribute.ModAttributes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;

public class ShieldsComponent implements IShieldsComponent {
    private double shields = 0;
    private int rechargeDelay = 0;

    private int rechargeTimer = 0;

    private final LivingEntity provider;

    public ShieldsComponent(Object provider) {
        this.provider = (LivingEntity)provider;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        shields = tag.getDouble("Shields");
        rechargeDelay = tag.getInt("RechargeDelay");
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
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
                rechargeTimer = (int)Math.round(20 / provider.getAttributeValue(ModAttributes.SHIELDS_RECHARGE_RATE));
                NullsArmory.LOGGER.info("Shields: " + shields + ", Timer: " + rechargeTimer);
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
}
