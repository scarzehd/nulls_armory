package com.scarzehd.nullsarmory.util;

import com.scarzehd.nullsarmory.components.IShieldsComponent;
import com.scarzehd.nullsarmory.components.ModComponents;
import net.minecraft.entity.LivingEntity;

public class ShieldsUtilities {
    public static float modifyDamage(float amount, LivingEntity entity) {

        IShieldsComponent shieldsComponent = ModComponents.SHIELDS.get(entity);

        double shields = shieldsComponent.getCurrentShields();

        if (shields > 0) {
            double newShields = shields - amount;
            if (newShields < 0) {
                amount -= shields;
                newShields = 0;
            } else {
                amount = 0;
            }

            shieldsComponent.setCurrentShields(newShields);
        }

        shieldsComponent.resetRechargeDelay();

        return amount;
    }
}
