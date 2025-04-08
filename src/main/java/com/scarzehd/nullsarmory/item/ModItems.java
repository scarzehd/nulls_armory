package com.scarzehd.nullsarmory.item;

import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.item.custom.*;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NullsArmory.MOD_ID, name), item);
    }

    public static void registerItems() {
        NullsArmory.LOGGER.info("Registering Mod Items");
    }

    public static final Item TRUE_KNIFE = registerItem("true_knife", new TrueKnifeSwordItem(ToolMaterials.DIAMOND, new Item.Settings().maxCount(1).maxDamage(1000).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 1, 2.5f))));

    public static final Item CRUCIBLE = registerItem("crucible", new CrucibleSwordItem(ToolMaterials.DIAMOND, new Item.Settings().maxCount(1).maxDamage(1000).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 7, -2f))));

    public static final Item ORTHOS_PRIME = registerItem("orthos_prime", new OrthosPrimeSwordItem(ToolMaterials.DIAMOND, new Item.Settings().maxCount(1).maxDamage(1000).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 5, -1f))));

    public static final Item VOIDGEL_ORB = registerItem("voidgel_orb", new Item(new Item.Settings()));

    public static final Item VOIDSHELL_INGOT = registerItem("voidshell_ingot", new Item(new Item.Settings()));

    public static final Item SHIELD_BELT = registerItem("shield_belt", new ShieldBeltItem(new Item.Settings().maxCount(1)));

    public static final Item KINETIC_CONVERTER = registerItem("kinetic_converter", new KineticConverterItem(new Item.Settings().maxCount(1)));

    public static final Item ELDER_INGOT = registerItem("elder_ingot", new Item(new Item.Settings()));
}