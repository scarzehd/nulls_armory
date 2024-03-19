package com.scarzehd.nullsarmory.item.custom;

import com.scarzehd.nullsarmory.ModTags;
import com.scarzehd.nullsarmory.NullsArmory;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CrucibleSwordItem extends SwordItem {
    private static final float NETHER_MOB_DAMAGE_MODIFIER = 1.5f; // the damage against nether mobs expressed as a multiplier of the base damage

    public CrucibleSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker.getEntityWorld().getRegistryKey() == World.NETHER) {
            target.damage(attacker.getDamageSources().generic(), getAttackDamage() * NETHER_MOB_DAMAGE_MODIFIER);
        }

        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.nulls_armory.crucible"));

        super.appendTooltip(stack, world, tooltip, context);
    }
}
