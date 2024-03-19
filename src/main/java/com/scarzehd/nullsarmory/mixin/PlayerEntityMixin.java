package com.scarzehd.nullsarmory.mixin;

import com.scarzehd.nullsarmory.components.IShieldsComponent;
import com.scarzehd.nullsarmory.components.ModComponents;
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

        IShieldsComponent shieldsComponent = ModComponents.SHIELDS.get(target);

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
            shieldsComponent.resetRechargeDelay();
        }

        return amount;
    }
}
