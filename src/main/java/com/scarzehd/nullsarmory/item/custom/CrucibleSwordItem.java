package com.scarzehd.nullsarmory.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class CrucibleSwordItem extends SwordItem {
    private static final float NETHER_MOB_DAMAGE_MODIFIER = 2f; // the damage against nether mobs expressed as a multiplier of the base damage

    public CrucibleSwordItem(ToolMaterial toolMaterial, Settings settings) {
        super(toolMaterial, settings);
    }

    @Override
    public float getBonusAttackDamage(Entity target, float baseAttackDamage, DamageSource damageSource) {
        if (target.getEntityWorld().getRegistryKey() == World.NETHER) {
            return baseAttackDamage * (NETHER_MOB_DAMAGE_MODIFIER - 1);
        }

        return 0f;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.nulls_armory.crucible"));

        super.appendTooltip(stack, context, tooltip, type);
    }
}
