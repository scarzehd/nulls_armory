package com.scarzehd.nullsarmory.util;

import com.scarzehd.nullsarmory.components.IShieldsComponent;
import com.scarzehd.nullsarmory.components.ModComponents;
import com.scarzehd.nullsarmory.event.ShieldsDamageCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;

public class ShieldsUtilities {
    public static float modifyDamage(float amount, LivingEntity entity) {

        IShieldsComponent shieldsComponent = ModComponents.SHIELDS.get(entity);

        double shields = shieldsComponent.getCurrentShields();

        if (shields > 0) {
            ActionResult result = ShieldsDamageCallback.EVENT.invoker().callback(entity, amount);

            if (result == ActionResult.FAIL) {
                return amount;
            }

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
