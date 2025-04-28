package com.scarzehd.nullsarmory.item.custom;

import com.scarzehd.nullsarmory.effect.ModStatusEffects;
import com.scarzehd.nullsarmory.item.ModToolMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.util.math.random.Random;

import java.util.Collection;

public class OrthosPrimeSwordItem extends Item {
    private final float statusChance = .25f;

    private final float statusDamageModifier = .25f;

    public OrthosPrimeSwordItem(Settings settings) {
        super(settings);
    }

//    @Override
//    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
//        Collection<StatusEffectInstance> targetEffects = target.getStatusEffects();
//
//        float damageMultiplier = 0f;
//
//        for (StatusEffectInstance effect : targetEffects) {
//            damageMultiplier += statusDamageModifier * (effect.getAmplifier() + 1);
//            NullsArmory.LOGGER.info(effect.getTranslationKey());
//        }
//
//        Random random = Random.createLocal();
//        if (random.nextFloat() < statusChance) {
//            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 20, 1));
//        }
//
//        target.damage(attacker.getDamageSources().generic(), getAttackDamage() * damageMultiplier);
//
//        NullsArmory.LOGGER.info("Attack Damage: " + getAttackDamage() + ", Extra Damage: " + getAttackDamage() * (damageMultiplier + 1)); // Add 1 to the damage multiplier because it will only recognize the higher between the original hit's damage and the extra damage
//
//        return super.postHit(stack, target, attacker);
//    }
    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        if (target.isInvulnerableTo(damageSource)) return 0;

        if (target instanceof LivingEntity living) {
            Collection<StatusEffectInstance> targetEffects = living.getStatusEffects();

            float damageMultiplier = 0f;

            for (StatusEffectInstance effect : targetEffects) {
                damageMultiplier += statusDamageModifier * (effect.getAmplifier() + 1);
            }

            Random random = Random.createLocal();
            if (random.nextFloat() < statusChance) {
                living.addStatusEffect(new StatusEffectInstance(ModStatusEffects.BLEED, 160, 0)); // BUFFED
            }

            return baseAttackDamage * damageMultiplier;
        }

        return 0;
    }
}
