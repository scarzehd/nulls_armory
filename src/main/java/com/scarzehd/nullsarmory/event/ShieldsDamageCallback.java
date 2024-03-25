package com.scarzehd.nullsarmory.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;

public interface ShieldsDamageCallback {
    Event<ShieldsDamageCallback> EVENT = EventFactory.createArrayBacked(ShieldsDamageCallback.class,
            (listeners) -> (entity, amount) -> {
                for (ShieldsDamageCallback listener : listeners) {
                    ActionResult result = listener.callback(entity, amount);

                    if (result != ActionResult.PASS) {
                        return result;
                    }
                }

                return ActionResult.PASS;
            });

    ActionResult callback(LivingEntity entity, float amount);
}
