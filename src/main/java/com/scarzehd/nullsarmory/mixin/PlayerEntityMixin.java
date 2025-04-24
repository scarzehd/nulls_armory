package com.scarzehd.nullsarmory.mixin;

import com.scarzehd.nullsarmory.ModDamageTypes;
import com.scarzehd.nullsarmory.item.ModItems;
import com.scarzehd.nullsarmory.util.ShieldsUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKeys;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @ModifyVariable(method = "applyDamage", at = @At("HEAD"))
    private float modifyDamage(float amount, DamageSource source) {
        LivingEntity target = (LivingEntity)(Object)this;
        return ShieldsUtilities.modifyDamage(amount, target, source);
    }

    @Inject(method = "attack", at = @At("HEAD"))
    private void attack(Entity target, CallbackInfo info) {
        PlayerEntity self = (PlayerEntity)(Object)this;

        if (self.getMainHandStack().getItem().equals(ModItems.HELLKITE_CLAWS) && self.getOffHandStack().getItem().equals(ModItems.HELLKITE_CLAWS)) {
            DamageSource source = new DamageSource(self.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(ModDamageTypes.BLOOD_RAGE));
            self.damage(source, 1.0f);
        }
    }
}
