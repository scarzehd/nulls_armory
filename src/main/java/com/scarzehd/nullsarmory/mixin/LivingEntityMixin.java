package com.scarzehd.nullsarmory.mixin;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.attribute.ModAttributes;
import com.scarzehd.nullsarmory.components.IShieldsComponent;
import com.scarzehd.nullsarmory.components.ModComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
abstract class LivingEntityMixin extends Entity {
    LivingEntityMixin(final EntityType<?> type, final World world) {
        super(type, world);
    }

    @Inject(
            method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;",
            require = 1, allow = 1, at = @At("RETURN"))
    private static void addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        info.getReturnValue().add(ModAttributes.MAX_SHIELDS);
        info.getReturnValue().add(ModAttributes.SHIELDS_RECHARGE_DELAY);
        info.getReturnValue().add(ModAttributes.SHIELDS_RECHARGE_RATE);
    }

    @ModifyVariable(method = "applyDamage", at = @At("HEAD"))
    private float modifyDamage(float amount) {
        LivingEntity target = (LivingEntity)(Object)this;

        IShieldsComponent shieldsComponent = ModComponents.SHIELDS.get(target);

        double shields = shieldsComponent.getCurrentShields();

        NullsArmory.LOGGER.info("Mixin");

        if (shields > 0) {
            double newShields = shields - amount;
            if (newShields < 0) {
                amount -= shields;
                newShields = 0;
            } else {
                amount = 0;
            }

            shieldsComponent.setCurrentShields(newShields);
            shieldsComponent.resetRechargeDelay();
        }

        return amount;
    }
}