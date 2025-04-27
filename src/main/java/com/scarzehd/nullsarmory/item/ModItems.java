package com.scarzehd.nullsarmory.item;

import com.scarzehd.nullsarmory.ModArmorMaterials;
import com.scarzehd.nullsarmory.NullsArmory;
import com.scarzehd.nullsarmory.item.custom.*;
import com.scarzehd.nullsarmory.util.ShieldsUtilities;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.item.*;
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

    public static final Item TRUE_KNIFE = registerItem("true_knife", new TrueKnifeSwordItem());

    public static final Item CRUCIBLE = registerItem("crucible", new CrucibleSwordItem(new Item.Settings().maxCount(1).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 8, -3f))));

    public static final Item ORTHOS_PRIME = registerItem("orthos_prime", new OrthosPrimeSwordItem(new Item.Settings().maxCount(1).maxDamage(1000).attributeModifiers(SwordItem.createAttributeModifiers(ToolMaterials.DIAMOND, 5, -1f))));

    public static final Item YOSHIMITSU_BANNER = registerItem("yoshimitsu_banner", new YoshimitsuBannerItem());

    public static final Item HELLKITE_CLAWS = registerItem("hellkite_claws", new HellkiteClawsItem());

    public static final Item VOIDGEL_ORB = registerItem("voidgel_orb", new Item(new Item.Settings()));

    public static final Item VOIDSHELL_INGOT = registerItem("voidshell_ingot", new Item(new Item.Settings()));

    public static final Item SHIELD_BELT = registerItem("shield_belt", new ShieldBeltItem(new Item.Settings().maxCount(1)));

    public static final Item CAPACITOR_BELT = registerItem("capacitor_belt", new CapacitorBeltItem(new Item.Settings().maxCount(1)));

    public static final Item VOID_COMPASS = registerItem("void_compass", new VoidCompassItem());

    public static final Item STARMAN_HELMET = registerItem("starman_helmet", new ArmorItem(ModArmorMaterials.STARMAN, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(4, 0, 0, 0, "starman_helmet", AttributeModifierSlot.HEAD))));
    public static final Item STARMAN_CHESTPLATE = registerItem("starman_chestplate", new ArmorItem(ModArmorMaterials.STARMAN, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(5, 0, 0, 0, "starman_chestplate", AttributeModifierSlot.CHEST))));
    public static final Item STARMAN_LEGGINGS = registerItem("starman_leggings", new ArmorItem(ModArmorMaterials.STARMAN, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(4, 0, 0, 0, "starman_leggings", AttributeModifierSlot.LEGS))));
    public static final Item STARMAN_BOOTS = registerItem("starman_boots", new ArmorItem(ModArmorMaterials.STARMAN, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(3, 0, 0, 0, "starman_boots", AttributeModifierSlot.FEET))));

    public static final Item TRENCH_HELMET = registerItem("trench_helmet", new ArmorItem(ModArmorMaterials.TRENCH, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(8, 5, 1, 2, "trench_helmet", AttributeModifierSlot.HEAD))));
    public static final Item TRENCH_CHESTPLATE = registerItem("trench_chestplate", new ArmorItem(ModArmorMaterials.TRENCH, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(10, 5, 2, 3, "trench_chestplate", AttributeModifierSlot.CHEST))));
    public static final Item TRENCH_LEGGINGS = registerItem("trench_leggings", new ArmorItem(ModArmorMaterials.TRENCH, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(8, 5, 1, 3, "trench_leggings", AttributeModifierSlot.LEGS))));
    public static final Item TRENCH_BOOTS = registerItem("trench_boots", new ArmorItem(ModArmorMaterials.TRENCH, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(6, 5, 1, 2, "trench_boots", AttributeModifierSlot.FEET))));

    public static final Item SCOUT_HELMET = registerItem("scout_helmet", new ArmorItem(ModArmorMaterials.SCOUT, ArmorItem.Type.HELMET, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(2, -5, -1, 1, "scout_helmet", AttributeModifierSlot.HEAD))));
    public static final Item SCOUT_CHESTPLATE = registerItem("scout_chestplate", new ArmorItem(ModArmorMaterials.SCOUT, ArmorItem.Type.CHESTPLATE, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(2, -5, 0, 2, "scout_chestplate", AttributeModifierSlot.CHEST))));
    public static final Item SCOUT_LEGGINGS = registerItem("scout_leggings", new ArmorItem(ModArmorMaterials.SCOUT, ArmorItem.Type.LEGGINGS, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(2, -5, 0, 2, "scout_leggings", AttributeModifierSlot.LEGS))));
    public static final Item SCOUT_BOOTS = registerItem("scout_boots", new ArmorItem(ModArmorMaterials.SCOUT, ArmorItem.Type.BOOTS, new Item.Settings().maxCount(1).attributeModifiers(ShieldsUtilities.createShieldAttributeModifierComponent(2, -5, -1, 1, "scout_boots", AttributeModifierSlot.FEET))));

    public static final Item SCOUT_SALVAGE_HELMET = registerItem("scout_salvage_helmet", new Item(new Item.Settings().maxCount(1)));
    public static final Item SCOUT_SALVAGE_CHESTPLATE = registerItem("scout_salvage_chestplate", new Item(new Item.Settings().maxCount(1)));
    public static final Item SCOUT_SALVAGE_LEGGINGS = registerItem("scout_salvage_leggings", new Item(new Item.Settings().maxCount(1)));
    public static final Item SCOUT_SALVAGE_BOOTS = registerItem("scout_salvage_boots", new Item(new Item.Settings().maxCount(1)));
}