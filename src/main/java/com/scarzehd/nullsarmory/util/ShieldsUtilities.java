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

            shieldsComponent.setCurrentShields(newShields);

            shieldsComponent.resetRechargeDelay();
            return 0;
        } else {
            shieldsComponent.resetRechargeDelay();
        }

        return amount;
    }
}
