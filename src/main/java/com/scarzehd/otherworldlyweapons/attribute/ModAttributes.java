package com.scarzehd.otherworldlyweapons.attribute;

import com.scarzehd.otherworldlyweapons.OtherworldlyWeapons;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModAttributes {
    public static final EntityAttribute MAX_SHIELDS = registerEntityAttribute("generic.max_shields", new CustomEntityAttriubte("attribute.otherworldly-weapons.max_shields", 0));

    public static final EntityAttribute SHIELDS_RECHARGE_DELAY = registerEntityAttribute("generic.shields_recharge_delay",  new CustomEntityAttriubte("attribute.otherworldly-weapons.shields_recharge_delay", 40));

    public static final EntityAttribute SHIELDS_RECHARGE_RATE = registerEntityAttribute("generic.shields_recharge_rate", new CustomEntityAttriubte("attribute.otherworldly-weapons.shields_recharge_rate", 2));

    public static void registerAttributes()  {
        OtherworldlyWeapons.LOGGER.info("Registering Mod Attributes");
    }

    private static EntityAttribute registerEntityAttribute(String name, EntityAttribute attribute) {
        return Registry.register(Registries.ATTRIBUTE, new Identifier(OtherworldlyWeapons.MOD_ID, name), attribute);
    }
}
