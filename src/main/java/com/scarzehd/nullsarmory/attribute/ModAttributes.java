package com.scarzehd.nullsarmory.attribute;

import com.scarzehd.nullsarmory.NullsArmory;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

public class ModAttributes {
    public static final RegistryEntry<EntityAttribute> MAX_SHIELDS = registerEntityAttribute("generic.max_shields", new CustomEntityAttribute("attribute.nulls_armory.max_shields", 0), true);

    public static final RegistryEntry<EntityAttribute> SHIELDS_RECHARGE_DELAY = registerEntityAttribute("generic.shields_recharge_delay",  new CustomEntityAttribute("attribute.nulls_armory.shields_recharge_delay", 40), true);

    public static final RegistryEntry<EntityAttribute> SHIELDS_RECHARGE_RATE = registerEntityAttribute("generic.shields_recharge_rate", new CustomEntityAttribute("attribute.nulls_armory.shields_recharge_rate", 2), true);

    public static void registerAttributes()  {
        NullsArmory.LOGGER.info("Registering Mod Attributes");
    }

    private static RegistryEntry<EntityAttribute> registerEntityAttribute(String name, EntityAttribute attribute, boolean tracked) {
        return Registry.registerReference(Registries.ATTRIBUTE, Identifier.of(NullsArmory.MOD_ID, name), attribute.setTracked(tracked));
    }
}
