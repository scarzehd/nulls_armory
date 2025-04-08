package com.scarzehd.nullsarmory.item.custom;

import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.scarzehd.nullsarmory.NullsArmory;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.UUID;

public class KineticConverterItem extends Item {

    private static final Identifier buffId = Identifier.of(NullsArmory.MOD_ID, "convert_energy");

    public KineticConverterItem(Settings settings) {
        super(settings.attributeModifiers(createAttributeModifiers()));
    }

    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder()
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(buffId, 2, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.OFFHAND)
                .build();
    }
}
