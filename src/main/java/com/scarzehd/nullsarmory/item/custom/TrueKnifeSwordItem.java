package com.scarzehd.nullsarmory.item.custom;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.item.ModToolMaterials;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class TrueKnifeSwordItem extends Item {
    public TrueKnifeSwordItem() {
        super(new Item.Settings().maxCount(1)
                .attributeModifiers(AttributeModifiersComponent.builder()
                        .add(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, 2.5, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND)
                        .build()));
    }

    private static final double curveScaleX = 0.4;
    private static final double curveScaleY = 7;

    public static final Identifier modifierId = Identifier.of(NullsArmory.MOD_ID, "monster_killer");

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                AttributeModifiersComponent attributeModifiers = stack.getOrDefault(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT);

                double value = Math.floor(curveScaleY * Math.log10((curveScaleX * player.experienceLevel) + 1) * 2)/2;

                EntityAttributeModifier modifier = new EntityAttributeModifier(modifierId, value, EntityAttributeModifier.Operation.ADD_VALUE);

                AttributeModifiersComponent newModifiers = attributeModifiers.with(EntityAttributes.GENERIC_ATTACK_DAMAGE, modifier, AttributeModifierSlot.MAINHAND);

                stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS, newModifiers);
            }
        }

        super.inventoryTick(stack, world, entity, slot, selected);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.nulls_armory.true_knife"));

        super.appendTooltip(stack, context, tooltip, type);
    }
}
