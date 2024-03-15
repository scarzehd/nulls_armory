package com.scarzehd.nullsarmory.item.custom;

import com.google.common.collect.Multimap;
import com.scarzehd.nullsarmory.attribute.ModAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class ShieldBeltItem extends TrinketItem {
    public ShieldBeltItem(Settings settings) {
        super(settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        Multimap<EntityAttribute, EntityAttributeModifier> modifiers = super.getModifiers(stack, slot, entity, uuid);

        modifiers.put(ModAttributes.MAX_SHIELDS, new EntityAttributeModifier(uuid, "nulls_armory:max_shields", 4, EntityAttributeModifier.Operation.ADDITION));

        return modifiers;
    }
}
