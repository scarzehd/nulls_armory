package com.scarzehd.nullsarmory.effect;

import com.scarzehd.nullsarmory.ModDamageTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.RegistryKeys;

public class BleedEffect extends StatusEffect {
    protected BleedEffect() {
        super(StatusEffectCategory.HARMFUL, 0x8F0000);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.damage(new DamageSource(entity.getWorld().getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(ModDamageTypes.BLEED)), 1.0F);
        return true;
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        int i = 20 >> amplifier;
        return i > 0 ? duration % i == 0 : true;
    }
}
