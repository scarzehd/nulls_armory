package com.scarzehd.nullsarmory.mixin;

import com.scarzehd.nullsarmory.util.ShieldsUtilities;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @ModifyVariable(method = "applyDamage", at = @At("HEAD"))
    private float modifyDamage(float amount) {
        LivingEntity target = (LivingEntity)(Object)this;
        return ShieldsUtilities.modifyDamage(amount, target);
    }
}
