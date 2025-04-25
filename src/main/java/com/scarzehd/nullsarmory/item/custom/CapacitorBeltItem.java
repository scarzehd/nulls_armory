package com.scarzehd.nullsarmory.item.custom;

import com.google.common.collect.Multimap;
import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.attribute.ModAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class CapacitorBeltItem extends TrinketItem {
    public CapacitorBeltItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);

        modifiers.put(ModAttributes.SHIELDS_RECHARGE_DELAY, new EntityAttributeModifier(Identifier.of(NullsArmory.MOD_ID, "shield_belt"), -10, EntityAttributeModifier.Operation.ADD_VALUE));
        modifiers.put(ModAttributes.SHIELDS_RECHARGE_RATE, new EntityAttributeModifier(Identifier.of(NullsArmory.MOD_ID, "shield_belt"), -1, EntityAttributeModifier.Operation.ADD_VALUE));
        modifiers.put(ModAttributes.SHIELDS_UNDERFLOW, new EntityAttributeModifier(Identifier.of(NullsArmory.MOD_ID, "shield_belt"), -2, EntityAttributeModifier.Operation.ADD_VALUE));

        return modifiers;
    }
}
