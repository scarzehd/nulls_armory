package com.scarzehd.nullsarmory.mixin;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.components.IShieldsComponent;
import com.scarzehd.nullsarmory.components.ModComponents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PlayerEntity.class)
abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @ModifyVariable(method = "applyDamage", at = @At("HEAD"))
    private float modifyDamage(float amount) {
        LivingEntity target = (PlayerEntity)(Object)this;

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

    //    @Inject(method = "applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V", at = @At("HEAD"))
}
