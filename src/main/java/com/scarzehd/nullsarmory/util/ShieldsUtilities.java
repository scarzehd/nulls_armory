package com.scarzehd.nullsarmory.util;

import com.scarzehd.nullsarmory.ModTags;
import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.attribute.ModAttributes;
import com.scarzehd.nullsarmory.components.IShieldsComponent;
import com.scarzehd.nullsarmory.components.ModComponents;
import com.scarzehd.nullsarmory.event.ShieldsDamageCallback;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;

public class ShieldsUtilities {
    public static float modifyDamage(float amount, LivingEntity entity, DamageSource source) {
        if (source.isIn(ModTags.BYPASSES_SHIELDS))
            return amount;

        IShieldsComponent shieldsComponent = ModComponents.SHIELDS.get(entity);

        double shields = shieldsComponent.getCurrentShields();

        if (shields > 0) {
            ActionResult result = ShieldsDamageCallback.EVENT.invoker().callback(entity, amount);

            if (result == ActionResult.FAIL) {
                return amount;
            }

            double newShields = shields - amount;

            double maxUnderflow = entity.getAttributeValue(ModAttributes.SHIELDS_UNDERFLOW);

            if (newShields < maxUnderflow) newShields = maxUnderflow;

            shieldsComponent.setCurrentShields(newShields);

            shieldsComponent.resetRechargeDelay();

            entity.damageArmor(source, 1);

            return 0;
        } else {
            shieldsComponent.resetRechargeDelay();
        }

        return amount;
    }

    public static AttributeModifiersComponent createShieldAttributeModifierComponent(int shields, int rechargeDelay, int rechargeRate, int underflow, String id, AttributeModifierSlot slot) {
        return AttributeModifiersComponent.builder()
                .add(ModAttributes.MAX_SHIELDS, new EntityAttributeModifier(Identifier.of(NullsArmory.MOD_ID, id + "_max-shields_" + slot.asString()), shields, EntityAttributeModifier.Operation.ADD_VALUE), slot)
                .add(ModAttributes.SHIELDS_RECHARGE_DELAY, new EntityAttributeModifier(Identifier.of(NullsArmory.MOD_ID, id + "_shields-delay_" + slot.asString()), rechargeDelay, EntityAttributeModifier.Operation.ADD_VALUE), slot)
                .add(ModAttributes.SHIELDS_RECHARGE_RATE, new EntityAttributeModifier(Identifier.of(NullsArmory.MOD_ID, id + "_shields-rate_" + slot.asString()), rechargeRate, EntityAttributeModifier.Operation.ADD_VALUE), slot)
                .add(ModAttributes.SHIELDS_UNDERFLOW, new EntityAttributeModifier(Identifier.of(NullsArmory.MOD_ID, id + "_shields-underflow_" + slot.asString()), underflow, EntityAttributeModifier.Operation.ADD_VALUE), slot)
                .build();
    }
}
