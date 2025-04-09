package com.scarzehd.nullsarmory.mixin;

import com.scarzehd.nullsarmory.attribute.ModAttributes;
import com.scarzehd.nullsarmory.util.ShieldsUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
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

    @Inject(method = "createLivingAttributes()Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;", at = @At("RETURN"))
    private static void addAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        info.getReturnValue().add(ModAttributes.MAX_SHIELDS);
        info.getReturnValue().add(ModAttributes.SHIELDS_RECHARGE_DELAY);
        info.getReturnValue().add(ModAttributes.SHIELDS_RECHARGE_RATE);
        info.getReturnValue().add(ModAttributes.SHIELDS_UNDERFLOW);
    }

    @ModifyVariable(method = "applyDamage", at = @At("HEAD"))
    private float modifyDamage(float amount, DamageSource source) {
        LivingEntity target = (LivingEntity)(Object)this;
        return ShieldsUtilities.modifyDamage(amount, target, source);
    }
}