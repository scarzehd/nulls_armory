package com.scarzehd.nullsarmory.mixin;

import com.scarzehd.nullsarmory.item.ModItems;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ExperienceOrbEntity.class)
public abstract class ExperienceOrbEntityMixin {

    @Shadow
    protected int amount;

    @Inject(method = "onPlayerCollision", at = @At("HEAD"))
    public void playerCollisionMixin(PlayerEntity player, CallbackInfo info) {
        if (player instanceof ServerPlayerEntity serverPlayer) {
            for (ItemStack stack : serverPlayer.getHandItems()) {
                if (stack.getItem().equals(ModItems.YOSHIMITSU_BANNER)) {
                    amount = (int)Math.ceil(amount * 1.25f);
                    return;
                }
            }
        }
    }
}
