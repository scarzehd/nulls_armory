package com.scarzehd.otherworldldlyweapons.item.custom;

import com.scarzehd.otherworldldlyweapons.ModTags;
import com.scarzehd.otherworldldlyweapons.OtherworldlyWeapons;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

public class CrucibleSwordItem extends SwordItem {
    private static final float NETHER_MOB_DAMAGE_MODIFIER = 1.5f; // the damage against nether mobs expressed as a multiplier of the base damage

    public CrucibleSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.getType().isIn(ModTags.NETHER_MOBS)) {
            OtherworldlyWeapons.LOGGER.info("Is in nether mobs");
            target.damage(attacker.getDamageSources().create(DamageTypes.GENERIC), getAttackDamage() * NETHER_MOB_DAMAGE_MODIFIER);
        }

        return super.postHit(stack, target, attacker);
    }
}
