package com.scarzehd.otherworldldlyweapons.item.custom;

import com.scarzehd.otherworldldlyweapons.OtherworldlyWeapons;
import net.fabricmc.fabric.mixin.registry.sync.RegistriesAccessor;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.math.random.Random;

import java.util.Collection;

public class OrthosPrimeSwordItem extends SwordItem {
    private final float statusChance = .25f;

    private final float statusDamageModifier = .25f;

    public OrthosPrimeSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Collection<StatusEffectInstance> targetEffects = target.getStatusEffects();

        float damageMultiplier = 0f;

        for (StatusEffectInstance effect : targetEffects) {
            damageMultiplier += statusDamageModifier * (effect.getAmplifier() + 1);
            OtherworldlyWeapons.LOGGER.info(effect.getTranslationKey());
        }

        Random random = Random.createLocal();
        if (random.nextFloat() < statusChance) {
            target.addStatusEffect(new StatusEffectInstance(StatusEffects.WITHER, 20, 1));
        }

        target.damage(attacker.getDamageSources().create(DamageTypes.GENERIC), getAttackDamage() * damageMultiplier);

        OtherworldlyWeapons.LOGGER.info("Attack Damage: " + getAttackDamage() + ", Extra Damage: " + getAttackDamage() * (damageMultiplier + 1)); // Add 1 to the damage multiplier because it will only recognize the higher between the original hit's damage and the extra damage

        return super.postHit(stack, target, attacker);
    }
}
