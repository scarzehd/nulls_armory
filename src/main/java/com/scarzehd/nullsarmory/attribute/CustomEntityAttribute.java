package com.scarzehd.nullsarmory.attribute;

import net.minecraft.entity.attribute.EntityAttribute;

public class CustomEntityAttribute extends EntityAttribute {
    public CustomEntityAttribute(String translationKey, double fallback) {
        super(translationKey, fallback);
    }
}
